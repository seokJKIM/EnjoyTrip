<template>
  <div>
    <h1 class="underline">게시글 작성</h1>
    <div class="regist_form">
      <label for="title">제목</label>
      <b-form-input
        v-model="title"
        placeholder="제목을 입력해주세요..."
      ></b-form-input>
      <label for="content">내용</label>
      <br />
      <b-form-textarea
        id="textarea-rows"
        placeholder="내용을 입력해주세요..."
        rows="15"
        v-model="content"
      ></b-form-textarea
      ><br />
      <b-button variant="primary" @click="insertArticle" class="ml-2 mr-2 mb-3"
        >등록</b-button
      >
      <b-button variant="success" @click="moveList" class="ml-2 mr-2 mb-3"
        >목록</b-button
      >
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import { registArticle } from "@/api/boardAxios";

const userStore = "userStore";

export default {
  name: "BoardWrite",
  components: {},
  data() {
    return {
      num: 0,
      createTime: null,
      title: null,
      content: null,
      writerId: null,
      hits: 0,
      boardId: 0,
    };
  },
  created() {
    this.boardId = this.$route.params.boardId;
    console.log("여기");
    console.log(this.userInfo);
    console.log(this.boardId);
  },
  computed: {
    ...mapState(userStore, ["isLogin", "userInfo"]),
  },
  methods: {
    async insertArticle() {
      let boardDto = {
        num: 0,
        createTime: null,
        title: this.title,
        content: this.content,
        writerId: this.userInfo.userId,
        hits: 0,
        boardId: this.boardId,
      };

      console.log(this.userInfo.userId);

      registArticle(
        boardDto,
        () => {},
        (error) => {
          console.log(error);
        }
      );
      this.moveList();
    },

    moveList() {
      if (this.boardId === 1) {
        this.$router.push({ name: "NoticeBoard" }).catch(() => {});
      } else if (this.boardId === 2) {
        this.$router.push({ name: "ReviewBoard" }).catch(() => {});
      } else if (this.boardId === 3) {
        this.$router.push({ name: "FreeBoard" }).catch(() => {});
      } else if (this.boardId === 4) {
        this.$router.push({ name: "TeamBoard" }).catch(() => {});
      }
    },
  },
};
</script>

<style scoped></style>
