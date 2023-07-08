<template>
  <div>
    <h1 class="underline">쪽지 보내기</h1>
    <label for="id">아이디 검색</label>
    <b-form-input
      v-model="userid"
      type="text"
      @change="isDuplicate"
      placeholder="보낼 사람의 아이디를 입력하세요..."
    ></b-form-input>
    <div>{{ message }}</div>
    <label for="title" class="mt-2">제목</label>
    <b-form-input
      v-model="title"
      placeholder="제목을 입력하세요..."
    ></b-form-input>
    <label for="content" class="mt-2">내용</label>
    <br />
    <b-form-textarea
      id="textarea-rows"
      placeholder="내용을 입력해주세요..."
      rows="15"
      v-model="content"
    ></b-form-textarea>
    <br />
    <b-button
      variant="primary"
      @click="insertNote"
      class="ml-2 mr-2 mb-3"
      :disabled="!isCheck"
      >보내기</b-button
    >
  </div>
</template>

<script>
import { registNote } from "@/api/noteAxios";
import { mapState } from "vuex";
import { insertAlarm } from "@/api/alarmAxios";
import { alreadyUser } from "@/api/user";
const userStore = "userStore";

export default {
  name: "NoteWrite",
  components: {},
  data() {
    return {
      message: "",
      num: 0,
      title: null,
      userid: null,
      sender: null,
      content: null,
      createtime: null,
      open: 0,
      isCheck: false,
    };
  },
  computed: {
    ...mapState(userStore, ["isLogin", "userInfo"]),
  },
  created() {},
  methods: {
    insertNote() {
      let Note = {
        title: this.title,
        sender: this.userInfo.userId,
        receiver: this.userid,
        content: this.content,
      };
      registNote(
        Note,
        () => {},
        (error) => {
          console.log(error);
        }
      );

      let alarm = {
        sep: 1,
        articleId: this.userInfo.userId,
        userId: this.userid,
      };
      insertAlarm(
        alarm,
        () => {
          console.log("성공");
        },
        (error) => {
          console.log(error);
        }
      );

      this.moveNote();
    },

    moveNote() {
      this.$router.push({ name: "SendNote" }).catch(() => {});
    },

    isDuplicate() {
      this.isCheck = false;
      alreadyUser(
        this.userid,
        ({ data }) => {
          if (data) {
            //이미 해당 아이디를 가진 사용자가 있음
            this.message = "보낼수있음";
            this.isCheck = true;
          } else {
            this.message = "없는 아이디입니다. 다시 입력해주세요";
            this.isCheck = false;
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
