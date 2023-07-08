<template>
  <div>
    <b-list-group-item
      ><router-link to="/mypage/MyInfo"
        >회원 정보</router-link
      ></b-list-group-item
    >
    <b-list-group-item
      ><router-link to="/mypage/MyBoardContents"
        >게시글 관리</router-link
      ></b-list-group-item
    >
    <b-list-group-item
      ><router-link to="/mypage/MyTripRoute"
        >내 여행 루트</router-link
      ></b-list-group-item
    >
    <b-list-group-item
      ><router-link to="/mypage/mynote">쪽지</router-link></b-list-group-item
    >
  </div>
</template>

<script>
export default {
  name: "MyPageList",
  components: {},
  data() {
    return {
      message: "",
    };
  },
  created() {},
  methods: {},
};
</script>

<style scoped></style>
