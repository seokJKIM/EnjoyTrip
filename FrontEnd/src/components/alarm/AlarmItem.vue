<template>
  <b-dropdown-item v-if="this.article.sep === 0" @click="moveBoard">
    {{ this.article.articleId }}번 게시글에 댓글이 달렸습니다.
  </b-dropdown-item>
</template>

<script>
import { deleteCommentAlarm } from "@/api/alarmAxios";
export default {
  name: "AlarmItem",
  components: {},
  data() {
    return {
      message: "",
    };
  },
  props: {
    article: Object,
  },
  created() {},
  methods: {
    moveBoard() {
      /*:to="{
          name: 'BoardView',
          params: { num: num, writerId: writer },
        }" */

      deleteCommentAlarm(
        this.article.articleId,
        () => {},
        (error) => {
          console.log(error);
        }
      );

      this.$router
        .push({
          name: "BoardView",
          params: {
            num: this.article.articleId,
            writerId: this.article.userId,
          },
        })
        .catch(() => {});
    },

    moveNote() {
      this.$router.push({ name: "ReceiveNote" }).catch(() => {});
    },
  },
};
</script>

<style scoped></style>
