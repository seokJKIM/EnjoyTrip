<template>
  <div>
    <b-row id="row">
      <b-col></b-col>
      <b-col cols="2"><my-page-list class="mt-5"></my-page-list></b-col>
      <b-col cols="8"><router-view class="mt-3"></router-view></b-col>
      <b-col></b-col>
    </b-row>
  </div>
</template>

<script>
import MyPageList from "@/components/user/MyPageList.vue";
export default {
  name: "MyPage",
  components: { MyPageList },
  data() {
    return {
      message: "",
    };
  },
  created() {
    this.$router.push("/mypage/MyInfo").catch(() => {});
  },
  methods: {},
};
</script>

<style scoped></style>
