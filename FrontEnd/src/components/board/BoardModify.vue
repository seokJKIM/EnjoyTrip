<template>
  <div>
    <h1 class="underline">게시글 수정</h1>
    <div class="regist_form">
      <label for="title"></label>
      <b-form-input
        v-model="article.title"
        placeholder="제목을 입력해주세요..."
      ></b-form-input>
      <label for="content">내용</label>
      <br />
      <b-form-textarea
        id="textarea-rows"
        placeholder="내용을 입력해주세요..."
        rows="15"
        v-model="article.content"
      ></b-form-textarea
      ><br />
      <b-button variant="primary" @click="modify">등록</b-button>
      <b-button variant="success" @click="moveList">목록</b-button>
    </div>
  </div>
</template>

<script>
import { modifyArticle } from "@/api/boardAxios";

export default {
  name: "BoardModify",
  components: {},
  data() {
    return {
      article: Object,
    };
  },
  created() {
    this.article = this.$route.params.article;
  },
  methods: {
    async modify() {
      let boardDto = {
        num: this.article.num,
        createTime: this.article.createTime,
        title: this.article.title,
        content: this.article.content,
        writerId: this.article.writerId,
        hits: this.article.hits,
        boardId: this.article.boardId,
      };
      modifyArticle(
        boardDto,
        () => {},
        (error) => {
          console.log(error);
        }
      );
      this.moveList();
    },
    moveList() {
      if (this.article.boardId === 1) {
        this.$router.push({ name: "NoticeBoard" }).catch(() => {});
      } else if (this.article.boardId === 2) {
        this.$router.push({ name: "ReviewBoard" }).catch(() => {});
      } else if (this.article.boardId === 3) {
        this.$router.push({ name: "FreeBoard" }).catch(() => {});
      } else if (this.article.boardId === 4) {
        this.$router.push({ name: "TeamBoard" }).catch(() => {});
      }
    },
  },
};
</script>

<style scoped></style>
