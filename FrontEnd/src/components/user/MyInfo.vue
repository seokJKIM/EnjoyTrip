<template>
  <div>
    <h3>회원 정보</h3>
    <hr />
    <br />
    <b-container fluid>
      <b-row class="my-1">
        <b-col sm="3"> 아이디 </b-col>
        <b-col sm="9">
          <b-form-input readonly :value="userInfo.userId"></b-form-input>
        </b-col>
      </b-row>
      <br /><br />
      <b-row class="my-1">
        <b-col sm="3"> 이름 </b-col>
        <b-col sm="9">
          <b-form-input readonly :value="userInfo.userName"></b-form-input>
        </b-col>
      </b-row>
      <br /><br />
      <b-row class="my-1">
        <b-col sm="3"> 이메일 </b-col>
        <b-col sm="9">
          <b-form-input readonly :value="userInfo.userEmail"></b-form-input>
        </b-col>
      </b-row>
    </b-container>
    <b-row class="mt-5">
      <b-col>
        <b-button variant="success" @click="moveModify" class="ml-5 mr-3"
          >수정하기</b-button
        >
        <b-button variant="success" @click="movePassChange"
          >비밀번호 변경</b-button
        >
      </b-col>
    </b-row>
  </div>
</template>

<script>
import { mapState } from "vuex";
const userStore = "userStore";

export default {
  name: "MyInfo",
  components: {},
  data() {
    return {
      message: "",
    };
  },
  computed: {
    ...mapState(userStore, ["isLogin", "userInfo"]),
  },
  created() {
    console.log(this.userInfo);
  },
  methods: {
    moveModify() {
      this.$router.push({ name: "MyInfoModify" }).catch(() => {});
    },

    movePassChange() {
      this.$router.push({ name: "CheckPass" }).catch(() => {});
    },
  },
};
</script>

<style scoped></style>
