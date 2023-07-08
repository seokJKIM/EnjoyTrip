<template>
  <b-row>
    <b-col>
      <router-link to="/">
        <img src="@/assets/img/trip_logo.png" />
      </router-link>
    </b-col>
    <b-col> </b-col>
    <b-col>
      <b-row>
        <b-col></b-col>
        <b-col class="mt-2" cols="8">
          <!-- after login -->
          <b-navbar-nav class="ml-auto" v-if="userInfo">
            <b-row class="mt-3">
              <b-avatar variant="primary" v-text="userInfo.userId"></b-avatar>
              <p class="ml-2 mt-2">
                {{ userInfo.userName }}({{ userInfo.userId }})님 환영합니다.
              </p>
            </b-row>
            <b-row>
              <b-col class="mt-2" cols="5">
                <b-nav-item-dropdown right>
                  <template #button-content>
                    <b-icon icon="people" font-scale="3"></b-icon>
                  </template>
                  <b-dropdown-item>
                    <router-link
                      :to="{ name: 'mypage' }"
                      class="link align-self-center"
                      >내정보보기</router-link
                    >
                  </b-dropdown-item>
                  <b-dropdown-item
                    class="align-self-center link"
                    @click.prevent="onClickLogout"
                    >로그아웃</b-dropdown-item
                  >
                </b-nav-item-dropdown>
              </b-col>
              <b-col class="mt-3" cols="5">
                <b-dropdown
                  size="lg"
                  variant="link"
                  toggle-class="text-decoration-none"
                  no-caret
                >
                  <template #button-content>
                    <b-avatar
                      v-bind:badge="count"
                      variant="light"
                      v-bind:badge-variant="value"
                      icon="alarm"
                    ></b-avatar>
                  </template>
                  <b-dropdown-item href="#" @click="moveNote"
                    >{{ this.noteCnt }}개의 새로운 쪽지가
                    있습니다</b-dropdown-item
                  >
                  <alarm-list :articles="articles"></alarm-list>
                </b-dropdown>
              </b-col>
              <b-col></b-col>
            </b-row>
          </b-navbar-nav>

          <!-- before login -->
          <b-row class="ml-auto mt-5" v-else>
            <b-col
              ><router-link :to="{ name: 'join' }" class="link">
                <b-icon icon="person-circle"></b-icon> 회원가입
              </router-link></b-col
            >
            <b-col
              ><router-link :to="{ name: 'login' }" class="link">
                <b-icon icon="key"></b-icon> 로그인
              </router-link></b-col
            >
          </b-row>
        </b-col>
      </b-row>
    </b-col>
  </b-row>
</template>

<script>
import { mapState, mapGetters, mapActions } from "vuex";
import { getCount, getList } from "@/api/alarmAxios";
import { getNoteCount } from "@/api/noteAxios";
import AlarmList from "../alarm/AlarmList.vue";

const userStore = "userStore";

export default {
  name: "TheHeader",
  components: { AlarmList },
  data() {
    return {
      cnt: 0,
      noteCnt: 0,
      count: 0,
      check: true,
      status: "",
      loading: null,
      articles: [],
      value: "",
    };
  },
  async created() {},
  updated() {
    if (this.userInfo !== null) {
      if (this.loading === null) {
        this.getStatus();
        this.loading = setInterval(this.getStatus, 2000);
      }
    }
  },
  computed: {
    ...mapState(userStore, ["isLogin", "userInfo"]),
    ...mapGetters(["checkUserInfo"]),
  },
  methods: {
    ...mapActions(userStore, ["userLogout"]),
    onClickLogout() {
      this.userLogout(this.userInfo.userId);
      sessionStorage.removeItem("access-token"); //저장된 토큰 없애기
      sessionStorage.removeItem("refresh-token"); //저장된 토큰 없애기
      if (this.$route.path != "/")
        this.$router.push({ name: "home" }).catch(() => {});
    },

    async getStatus() {
      const loginUser = this.userInfo;
      if (loginUser !== null) {
        await getCount(
          loginUser.userId,
          ({ data }) => {
            this.cnt = data;
          },
          (error) => {
            console.log(error);
          }
        );

        await getNoteCount(
          loginUser.userId,
          ({ data }) => {
            this.noteCnt = data;
          },
          (error) => {
            console.log(error);
          }
        );

        await getList(
          loginUser.userId,
          ({ data }) => {
            this.articles = data;
          },
          (error) => {
            console.log(error);
          }
        );
      } else {
        window.clearInterval(this.loading);
        this.loading = null;
        // this.status = loginUser.userId;
      }
      this.count = this.cnt + this.noteCnt;
      if (this.count == 0) this.value = "none";
      else this.value = "danger";
    },

    moveNote() {
      this.$router.push({ name: "ReceiveNote" }).catch(() => {});
    },
  },
  mounted() {
    if (this.userInfo !== null) {
      if (this.loading === null) {
        this.getStatus();
        this.loading = setInterval(this.getStatus, 2000);
      }
    }
  },
};
</script>

<style scoped>
* {
  margin: 0;
  font-size: 15px;
}

#search-bar {
  display: flex;
  justify-content: space-between;
  margin: 5px 0;
}

img {
  width: 150px;
}

.user {
  width: 150px;
}

#userIcon {
  /* height: 200px; */
}

.none {
  display: none;
}
</style>
