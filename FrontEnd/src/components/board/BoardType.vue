<template>
  <div>
    <div class="mb-3">
      <b-list-group-item
        ><router-link to="/board/Notice"
          >공지사항</router-link
        ></b-list-group-item
      >
      <b-list-group-item
        ><router-link to="/board/Review"
          >여행 후기</router-link
        ></b-list-group-item
      >
      <b-list-group-item
        ><router-link to="/board/Free"
          >자유 게시판</router-link
        ></b-list-group-item
      >
      <b-list-group-item
        ><router-link to="/board/Team"
          >일행 구하기</router-link
        ></b-list-group-item
      >
    </div>
  </div>
</template>

<script>
export default {
  name: "BoardType",
  components: {},
  data() {
    return {
      message: "",
    };
  },
  created() {},
  methods: {
    moveList() {
      this.$router.push({ name: "boardlist" }).catch(() => {});
    },
  },
};
</script>

<style scoped></style>
