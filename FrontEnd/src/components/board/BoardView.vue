<template>
  <div>
    <h4>제목 : {{ article.title }}</h4>
    <h6 align="right">작성자 : {{ article.writerId }}</h6>
    <hr />
    {{ article.content }}

    <!-- <table class="table table-bordered" id="board" style="text-align: center">
      <thead>
        <tr>
          <th>제목</th>
          <td colspan="5">{{ article.title }}</td>
        </tr>
        <tr>
          <th>번호</th>
          <td>{{ article.num }}</td>
          <th>작성자</th>
          <td>{{ article.writerId }}</td>
          <th>조회수</th>
          <td>{{ article.hits }}</td>
        </tr>
      </thead>
      <tbody>
        <tr style="height: 600px; text-align: left">
          <td colspan="6">{{ article.content }}</td>
        </tr>
      </tbody>
    </table> -->
    <div class="mt-3 mb-3">
      <b-button variant="outline-primary" @click="moveList">목록</b-button>
      <span v-if="this.userInfo.userId === this.article.writerId">
        <b-button variant="outline-warning" @click="moveModifyArticle"
          >수정</b-button
        >
        <b-button variant="outline-danger" @click="deleteArti">삭제</b-button>
      </span>
    </div>
    <hr />
    <comment-list
      :articleno="this.num"
      :writerId="this.writerId"
    ></comment-list>
  </div>
</template>

<script>
import { deleteArticle, showArticle } from "@/api/boardAxios";
import CommentList from "@/components/comment/CommentList";
import { mapState } from "vuex";
const userStore = "userStore";

export default {
  name: "BoardView",
  components: { CommentList },
  data() {
    return {
      num: Number,
      article: Object,
      check: false,
      writerId: String,
    };
  },
  computed: {
    ...mapState(userStore, ["isLogin", "userInfo"]),
  },
  updated() {},
  created() {
    this.num = this.$route.params.num;
    this.writerId = this.$route.params.writerId;
    console.log("num:" + typeof this.num);
    console.log("writerId:" + typeof this.writerId);
    showArticle(
      this.num,
      ({ data }) => {
        this.article = data;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {
    moveModifyArticle() {
      this.$router
        .push({
          name: "BoardModify",
          params: { article: this.article },
        })
        .catch(() => {});
    },
    async deleteArti() {
      deleteArticle(
        this.num,
        () => {},
        (error) => {
          console.log(error);
        }
      );
      this.moveList();
    },

    moveList() {
      // this.$router.go(-1);
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
