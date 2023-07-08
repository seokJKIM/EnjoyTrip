<template>
  <div>
    <div v-if="articles.length">
      <table class="table table-hover" id="board">
        <thead>
          <tr>
            <th>번호</th>
            <th>제목</th>
            <th>보낸 사람</th>
            <th>보낸 시간</th>
          </tr>
        </thead>
        <tbody id="noticeBoard">
          <note-receive-item
            v-for="(article, index) in selectedArticles"
            :key="index"
            :index="index"
            :article="article"
          ></note-receive-item>
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
    <div class="text-center" v-else>받은 쪽지가 없습니다.</div>
  </div>
</template>

<script>
import { selectReceiver } from "@/api/noteAxios";
import BoardPage from "@/components/board/BoardPage";
import NoteReceiveItem from "@/components/note/NoteReceiveItem";
import { mapState } from "vuex";
const userStore = "userStore";

export default {
  name: "ReceiveNote",
  components: { NoteReceiveItem, BoardPage },
  data() {
    return {
      articles: [],
      selected: 0,
      text: "",
      selectedArticles: [],
      perPage: 10,
      rows: 0,
    };
  },
  computed: {
    ...mapState(userStore, ["isLogin", "userInfo"]),
  },
  async created() {
    selectReceiver(
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
