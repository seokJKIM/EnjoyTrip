<template>
  <div>
    <h3>작성한 게시글</h3>
    <hr />
    <br />
    <div v-if="articles.length">
      <table class="table table-hover" id="board">
        <thead>
          <tr>
            <th>번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>조회수</th>
          </tr>
        </thead>
        <tbody id="noticeBoard">
          <board-list-item
            v-for="(article, index) in selectedArticles"
            :key="index"
            :index="index"
            :article="article"
            :len="len"
          ></board-list-item>
        </tbody>
      </table>
      <b-row>
        <b-col></b-col>
        <b-col id="pageBar">
          <board-page :rows="rows" :perPage="perPage" @change="change" />
        </b-col>
        <b-col> </b-col>
      </b-row>
    </div>
    <div class="text-center" v-else>게시글이 없습니다.</div>
  </div>
</template>

<script>
import { getMyPageArticle } from "@/api/boardAxios";
import BoardListItem from "@/components/board/BoardListItem.vue";
import BoardPage from "@/components/board/BoardPage";
import { mapState } from "vuex";
const userStore = "userStore";

export default {
  name: "MyBoardContents",
  components: { BoardListItem, BoardPage },
  data() {
    return {
      articles: [],
      selected: 0,
      options: [
        { value: 0, text: "제목" },
        { value: 1, text: "내용" },
        { value: 2, text: "제목+내용" },
      ],
      text: "",
      selectedArticles: [],
      perPage: 10,
      rows: 0,
      check: false,
      len: 0,
    };
  },
  computed: {
    ...mapState(userStore, ["isLogin", "userInfo"]),
  },
  async created() {
    console.log(this.userInfo.userId);
    getMyPageArticle(
      this.userInfo.userId,
      ({ data }) => {
        this.articles = data;
        this.rows = this.articles.length;
        this.selectedArticles = [];
        if (this.rows < this.perPage) {
          this.selectedArticles = this.articles;
        } else {
          for (let i = 0; i < this.perPage; i++) {
            this.selectedArticles.push(this.articles[i]);
          }
        }
        this.len = this.selectedArticles.length;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {
    change(currentPage) {
      this.selectedArticles = [];
      this.selectedArticles = this.articles.slice(
        (currentPage - 1) * this.perPage,
        currentPage * this.perPage
      );
    },
  },
};
</script>

<style scoped></style>
