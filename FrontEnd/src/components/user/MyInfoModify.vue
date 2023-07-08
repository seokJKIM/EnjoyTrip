<template>
  <div>
    <h3>회원 정보</h3>
    <hr />
    <br />
    <b-container fluid>
      <b-row class="my-1">
        <b-col sm="3"> 아이디 </b-col>
        <b-col sm="9">
          <b-form-input :value="userInfo.userId" readonly></b-form-input>
        </b-col>
      </b-row>
      <br /><br />
      <b-row class="my-1">
        <b-col sm="3"> 이름 </b-col>
        <b-col sm="9">
          <b-form-input v-model="name"></b-form-input>
        </b-col>
      </b-row>
      <br /><br />
      <b-row class="my-1">
        <b-col sm="3"> 이메일 </b-col>
        <b-col sm="9">
          <b-form-input v-model="email" @keyup="emailCheck"></b-form-input>
          <div ref="email" :class="{ blue: emailState, red: !emailState }">
            {{ msg4 }}
          </div>
        </b-col>
      </b-row>
      <br /><br />
      <b-button
        variant="primary"
        @click="modifyInfo"
        :disabled="!this.emailState"
        >변경</b-button
      >
    </b-container>
  </div>
</template>

<script>
import { mapState } from "vuex";
import { modify } from "@/api/user";
const userStore = "userStore";

export default {
  name: "MyInfo",
  components: {},
  data() {
    return {
      name: "",
      email: "",
      msg4: "",
      emailState: false,
    };
  },
  computed: {
    ...mapState(userStore, ["isLogin", "userInfo"]),
  },
  created() {
    this.name = this.userInfo.userName;
    this.email = this.userInfo.userEmail;
  },
  methods: {
    modifyInfo() {
      let user = {
        userName: this.name,
        userEmail: this.email,
        userId: this.userInfo.userId,
      };
      modify(
        user,
        () => {},
        (error) => {
          console.log(error);
        }
      );

      alert("변경 완료!");
      this.$router.go();
    },
    emailCheck() {
      this.emailState = false;
      let regex = /^([a-z0-9_.-]+)@([\da-z.-]+)\.([a-z.]{2,6})$/;
      if (regex.test(this.email)) {
        this.msg4 = "정확한 양식의 이메일입니다.";
        this.emailState = true;
      } else {
        this.msg4 = "잘못된 이메일입니다.";
      }
    },
  },
};
</script>

<style scoped>
.red {
  color: red;
}
.blue {
  color: blue;
}
</style>
