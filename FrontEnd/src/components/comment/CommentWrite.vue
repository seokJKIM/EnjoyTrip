<template>
  <div>
    <b-row>
      <b-col>
        <b-form-input
          v-model="value"
          type="text"
          debounce="500"
          placeholder="내용을 입력해주세요..."
        ></b-form-input>
      </b-col>
      <b-col cols="2">
        <b-button variant="outline-primary" @click="insertComment"
          >댓글</b-button
        >
      </b-col>
    </b-row>
  </div>
</template>

<script>
import { mapState } from "vuex";
import { insertComment } from "@/api/commentAxios";
import { insertAlarm } from "@/api/alarmAxios";
const userStore = "userStore";

export default {
  name: "CommentWrite",
  components: {},
  data() {
    return {
      value: "",
    };
  },
  computed: {
    ...mapState(userStore, ["isLogin", "userInfo"]),
  },

  props: {
    articleno: Number,
    writerid: String,
  },

  created() {
    console.log("글쓴이 아이디" + this.writerid);
  },
  updated() {
    this.$router
      .push({ name: "BoardView", params: { num: Number(this.articleno) } })
      .catch(() => {});
  },
  methods: {
    async insertComment() {
      let CommentDto = {
        articleno: Number(this.articleno),
        writerid: this.userInfo.userId,
        content: this.value,
      };
      insertComment(
        CommentDto,
        () => {},
        (error) => {
          console.log(error);
        }
      );

      let alarm = {
        sep: 0,
        articleId: this.articleno,
        userId: this.writerid,
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

      window.location.reload(true);
    },
  },
};
</script>

<style scoped></style>
