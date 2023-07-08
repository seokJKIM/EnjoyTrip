<template>
  <div>
    <h1>공지사항</h1>
    <div v-if="articles.length">
      <div class="mb-3" id="searchBar">
        <b-form-select
          v-model="selected"
          :options="options"
          id="condition"
          class="m-1"
        ></b-form-select>
        <b-form-input
          v-model="text"
          placeholder="검색어를 입력해주세요"
          id="content"
          class="m-1"
        ></b-form-input>
        <b-button variant="outline-primary" class="m-1" @click="searchBoard"
          >검색</b-button
        >
      </div>
      <table class="table table-hover" id="board">
        <colgroup>
          <col width="10%" />
          <col width="60%" />
          <col width="20%" />
          <col width="10%" />
        </colgroup>
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
    <b-row class="mb-5">
      <b-col></b-col>
      <b-col></b-col>
      <b-col>
        <b-row>
          <b-col></b-col>
          <b-col>
            <b-button
              variant="primary"
              @click="moveWrite"
              v-if="this.userInfo.userId == 'admin'"
              >글 등록</b-button
            >
          </b-col>
        </b-row>
      </b-col>
    </b-row>
  </div>
</template>
<script>
//v-if="this.userInfo.userId === 'admin'"
import { getSearchList, getArticleList } from "@/api/boardAxios";
import BoardPage from "@/components/board/BoardPage";
import BoardListItem from "@/components/board/BoardListItem";
import { mapState } from "vuex";
const userStore = "userStore";

export default {
  name: "NoticeBoard",
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
      searchInfo: {
        boardId: 1,
        condition: 0,
        keyword: "",
      },
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
    getArticleList(
      1,
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
  updated() {
    if (!this.check) {
      getArticleList(
        1,
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
          this.len = this.selectedArticles.len;
        },
        (error) => {
          console.log(error);
        }
      );
      this.check = true;
    }
  },
  methods: {
    moveWrite() {
      this.$router
        .push({ name: "BoardWrite", params: { boardId: 1 } })
        .catch(() => {});
    },

    async searchBoard() {
      this.searchInfo = {
        boardId: 1,
        condition: this.selected,
        keyword: this.text,
      };

      getSearchList(
        this.searchInfo,
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
        },
        (error) => {
          console.log(error);
        }
      );
    },

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

<style scoped>
#searchBar {
  display: flex;
  justify-content: right;
}

#pageBar {
  display: flex;
  justify-content: center;
}

#condition {
  width: 100px;
}

#content {
  width: 200px;
}
</style>
