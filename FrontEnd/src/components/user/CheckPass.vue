<template>
  <div>
    <b-form-group label="비밀번호:" label-for="userpwd">
      <b-form-input
        type="password"
        id="userpwd"
        v-model="user.userPass"
        required
        placeholder="비밀번호 입력...."
        @keyup.enter="confirm"
      ></b-form-input>
    </b-form-group>
    <b-button type="button" variant="primary" class="m-1" @click="confirm"
      >확인</b-button
    >
  </div>
</template>

<script>
import { mapState } from "vuex";
import { checkPass } from "@/api/user";

const userStore = "userStore";

export default {
  name: "CheckPass",
  components: {},
  data() {
    return {
      user: {
        userId: null,
        userPass: null,
      },
      msg: null,
    };
  },
  computed: {
    ...mapState(userStore, ["isLogin", "userInfo"]),
  },
  created() {
    console.log(this.userInfo);
    this.user.userId = this.userInfo.userId;
  },
  methods: {
    async confirm() {
      checkPass(
        this.user,
        ({ data }) => {
          this.msg = data.message;

          if (data.message == "fail") {
            alert("잘못된 비밀번호 입니다!");
          } else {
            this.$router.push({
              name: "PassChange",
              params: { pwd: this.user.userPass },
            });
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};
</script>

<style scoped></style>
