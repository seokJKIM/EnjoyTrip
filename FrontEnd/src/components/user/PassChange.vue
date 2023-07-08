<template>
  <div>
    <h3>비밀번호 변경</h3>
    <hr />
    <br />
    <b-container fluid>
      <b-row class="my-1">
        <b-col>
          <b-form-group
            id="input-group-2"
            class="input"
            label="비밀번호:"
            label-for="password"
          >
            <b-form-input
              id="password"
              v-model="newPass"
              type="password"
              placeholder="비밀번호 입력"
              @keyup="pwdCheck"
              required
            ></b-form-input>
            <div ref="password" :class="{ blue: pwdState, red: !pwdState }">
              {{ msg2 }}
            </div>
          </b-form-group>

          <b-form-group
            id="input-group-3"
            class="input"
            label="비밀번호 확인:"
            label-for="passwordCheck"
          >
            <b-form-input
              id="passwordCheck"
              v-model="passwordCheck"
              type="password"
              placeholder="동일한 비밀번호 입력"
              @keyup="isSamePwd"
              required
            ></b-form-input>
            <div
              ref="passwordCheck"
              :class="{ blue: samePwdState, red: !samePwdState }"
            >
              {{ msg3 }}
            </div>
          </b-form-group>
        </b-col>
      </b-row>
      <b-button variant="primary" @click="changeInfo" :disabled="!samePwdState"
        >변경</b-button
      >
    </b-container>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
import { changePass } from "@/api/user";
const userStore = "userStore";

export default {
  name: "MyInfo",
  components: {},
  data() {
    return {
      newPass: "",
      pass: "",
      check: true,
      msg2: "",
      msg3: "",
      pwdState: false,
      samePwdState: false,
      passwordCheck: "",
      emailState: false,
    };
  },
  computed: {
    ...mapState(userStore, ["isLogin", "userInfo"]),
  },
  created() {
    this.pass = this.$route.params.pwd;
    console.log(this.pass);
  },
  methods: {
    ...mapActions(userStore, ["userLogout"]),

    changeInfo() {
      let user = {
        userPass: this.newPass,
        userId: this.userInfo.userId,
      };

      console.log(user);

      changePass(
        user,
        () => {},
        (error) => {
          console.log(error);
        }
      );

      alert("비밀번호 변경 완료! 다시 로그인해주세요");
      this.userLogout(this.userInfo.userId);
      sessionStorage.removeItem("access-token"); //저장된 토큰 없애기
      sessionStorage.removeItem("refresh-token"); //저장된 토큰 없애기
      if (this.$route.path != "/")
        this.$router.push({ name: "home" }).catch(() => {});
    },
    isSamePwd() {
      this.samePwdState = false;
      if (this.newPass === this.passwordCheck) {
        this.msg3 = "동일하게 입력하였습니다.";
        this.samePwdState = true;
      } else {
        this.msg3 = "원래의 비밀번호와 다르게 입력하셨습니다.";
        this.samePwdState = false;
      }
    },
    pwdCheck() {
      this.pwdState = false;
      if (8 <= this.newPass.length) {
        let regex2 = /^[0-9]+$/;
        let regex3 = /^[a-z]+$/;
        // eslint-disable-next-line
        let regex4 = /[`~!@#$%^&*|\\\'\";:\/?]/gi;
        if (!regex2.test(this.newPass) && !regex3.test(this.newPass)) {
          if (regex4.test(this.newPass)) {
            this.msg2 = "사용 가능한 비밀번호입니다.";
            this.pwdState = true;
            this.samePwdState = true;
          } else {
            this.msg2 =
              "비밀번호는 반드시 1개 이상의 특수문자가 포함되어야 합니다.";
            this.samePwdState = false;
          }
        } else {
          this.msg2 =
            "비밀번호는 영어 소문자, 숫자, 특수문자가 포함되어야 합니다.";
          this.samePwdState = false;
        }
      } else {
        this.msg2 = "비밀번호는 8자 이상이어야 합니다.";
        this.samePwdState = false;
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
