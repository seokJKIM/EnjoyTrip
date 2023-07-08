<template>
  <div>
    <comment-write
      :articleno="this.num"
      :writerid="this.writer"
    ></comment-write>
    <comment-item
      v-for="(article, index) in selectedArticles"
      :key="index"
      :index="index"
      :article="article"
    ></comment-item>
    <b-row>
      <b-col></b-col>
      <b-col id="pageBar" class="mt-3">
        <board-page :rows="rows" :perPage="perPage" @change="change" />
      </b-col>
      <b-col> </b-col>
    </b-row>
  </div>
</template>

<script>
import CommentItem from "@/components/comment/CommentItem";
import CommentWrite from "@/components/comment/CommentWrite";
import { selectComment } from "@/api/commentAxios";
import BoardPage from "@/components/board/BoardPage";
export default {
  name: "CommentList",
  components: { CommentItem, CommentWrite, BoardPage },
  data() {
    return {
      articles: [],
      selected: 0,
      text: "",
      selectedArticles: [],
      perPage: 10,
      rows: 0,
      check: false,
      num: this.articleno,
      writer: this.writerId,
    };
  },

  props: {
    articleno: Number,
    writerId: String,
  },

  created() {
    console.log("dd" + this.writerId);
    selectComment(
      this.articleno,
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
  methods: {
    change(currentPage) {
      this.selectedArticles = [];
      this.selectedArticles = this.articles.slice(
        (currentPage - 1) * this.perPage,
        currentPage * this.perPage
      );
    },
  },

  updated() {
    if (!this.check) {
      selectComment(
        this.articleno,
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

      this.check = true;
    }
  },
};
</script>

<style scoped>
#pageBar {
  display: flex;
  justify-content: center;
}
</style>
