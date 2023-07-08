<template>
  <div>
    <h4>제목 : {{ this.article.title }}</h4>
    <h6 align="right">보낸사람 : {{ article.sender }}</h6>
    <hr />
    {{ this.article.content }}
  </div>
</template>

<script>
import { getNote, updateNote } from "@/api/noteAxios";
export default {
  name: "NoteView",
  components: {},
  data() {
    return {
      num: "",
      article: "",
    };
  },

  created() {
    this.num = this.$route.params.num;
    getNote(
      this.num,
      ({ data }) => {
        this.article = data;
        console.log(this.article);
      },
      (error) => {
        console.log(error);
      }
    );
    updateNote(
      this.num,
      () => {},
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {},
};
</script>

<style scoped></style>
