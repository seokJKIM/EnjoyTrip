<template>
  <div>
    <h3 class="underline-steelblue">
      <b-icon icon="person-lines-fill"></b-icon> 회원가입
    </h3>
    <div style="text-align: left">아이디:</div>
    <b-form inline class="mt-2 mb-3">
      <b-form-input
        id="id"
        v-model="form.id"
        type="text"
        placeholder="아이디 입력"
        class="mr-3"
        @keyup="idCheck"
        required
      ></b-form-input>
      <b-button variant="primary" @click="isDuplicate">중복확인</b-button>
    </b-form>
    <div
      ref="id"
      :class="{ blue: idState && idDuplicate, red: !idState || !idDuplicate }"
      style="text-align: left"
    >
      {{ msg1 }}
    </div>
    <b-form @submit="onSubmit" @reset="onReset" class="mt-2">
      <b-form-group
        id="input-group-2"
        class="input"
        label="비밀번호:"
        label-for="password"
      >
        <b-form-input
          id="password"
          v-model="form.password"
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
          v-model="form.passwordCheck"
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

      <b-form-group
        id="input-group-4"
        class="input"
        label="이름:"
        label-for="name"
      >
        <b-form-input
          id="name"
          v-model="form.name"
          type="text"
          placeholder="이름 입력"
          required
        ></b-form-input>
      </b-form-group>

      <b-form-group
        id="input-group-5"
        class="input"
        label="이메일:"
        label-for="email"
      >
        <b-form-input
          id="email"
          v-model="form.email"
          type="email"
          placeholder="이메일 입력"
          @keyup="emailCheck"
          required
        ></b-form-input>
        <div ref="email" :class="{ blue: emailState, red: !emailState }">
          {{ msg4 }}
        </div>
      </b-form-group>

      <b-button type="submit" variant="primary" class="mr-1">회원가입</b-button>
      <b-button type="reset" variant="danger" class="ml-1">초기화</b-button>
    </b-form>
  </div>
</template>

<script>
import { alreadyUser, join } from "@/api/user";
export default {
  data() {
    return {
      form: {
        id: "",
        password: "",
        passwordCheck: "",
        email: "",
        name: "",
      },
      msg1: "",
      msg2: "",
      msg3: "",
      msg4: "",
      idState: false,
      idDuplicate: false,
      pwdState: false,
      samePwdState: false,
      emailState: false,
    };
  },
  methods: {
    isDuplicate() {
      this.idDuplicate = false;
      alreadyUser(this.form.id, ({ data }) => {
        if (data) {
          //이미 해당 아이디를 가진 사용자가 있음
          this.msg1 = "이미 해당 아이디를 사용하는 사용자가 있습니다.";
        } else {
          this.msg1 = "사용 가능한 아이디입니다.";
          this.idDuplicate = true;
        }
      });
    },
    onSubmit(event) {
      event.preventDefault();
      //정규표현식을 통해서 아이디, 비밀번호, 이메일 유효성 검사
      if (
        this.idState &&
        this.idDuplicate &&
        this.pwdState &&
        this.samePwdState &&
        this.emailState
      ) {
        //회원가입 요청
        join(
          {
            userId: this.form.id,
            userName: this.form.name,
            userPass: this.form.password,
            userEmail: this.form.email,
          },
          ({ data }) => {
            //회원가입 성공 시
            if (data.message === "success") {
              alert("회원가입 성공");
              this.$router.push({ name: "login" }).catch(() => {});
            }
            //회원가입 실패 시
            else {
              alert("회원가입 실패");
            }
          },
          (error) => {
            console.log(error);
          }
        );
      }
    },
    onReset(event) {
      event.preventDefault();
      // Reset our form values
      this.form.id = "";
      this.form.password = "";
      this.form.passwordCheck = "";
      this.form.email = "";
      this.form.name = "";
    },
    idCheck() {
      this.idState = false;
      if (7 <= this.form.id.length) {
        let regex1 = /^[0-9a-z]+$/;
        let regex2 = /^[0-9]+$/;
        let regex3 = /^[a-z]+$/;
        if (
          regex1.test(this.form.id) &&
          !regex2.test(this.form.id) &&
          !regex3.test(this.form.id)
        ) {
          this.msg1 = "아이디 중복 검사를 진행해주시기 바랍니다.";
          this.idState = true;
        } else this.msg1 = "아이디는 영어 소문자와 숫자로 구성되어야 합니다.";
      } else {
        this.msg1 = "아이디는 7자 이상이어야 합니다.";
      }
    },
    pwdCheck() {
      this.pwdState = false;
      if (8 <= this.form.password.length) {
        let regex2 = /^[0-9]+$/;
        let regex3 = /^[a-z]+$/;
        // eslint-disable-next-line
        let regex4 = /[`~!@#$%^&*|\\\'\";:\/?]/gi;
        if (
          !regex2.test(this.form.password) &&
          !regex3.test(this.form.password)
        ) {
          if (regex4.test(this.form.password)) {
            this.msg2 = "사용 가능한 비밀번호입니다.";
            this.pwdState = true;
          } else {
            this.msg2 =
              "비밀번호는 반드시 1개 이상의 특수문자가 포함되어야 합니다.";
          }
        } else {
          this.msg2 =
            "비밀번호는 영어 소문자, 숫자, 특수문자가 포함되어야 합니다.";
        }
      } else {
        this.msg2 = "비밀번호는 8자 이상이어야 합니다.";
      }
    },
    isSamePwd() {
      this.samePwdState = false;
      if (this.form.password === this.form.passwordCheck) {
        this.msg3 = "동일하게 입력하였습니다.";
        this.samePwdState = true;
      } else {
        this.msg3 = "원래의 비밀번호와 다르게 입력하셨습니다.";
      }
    },
    emailCheck() {
      this.emailState = false;
      let regex = /^([a-z0-9_.-]+)@([\da-z.-]+)\.([a-z.]{2,6})$/;
      if (regex.test(this.form.email)) {
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
.input {
  text-align: left;
}
</style>
