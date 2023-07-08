<template>
  <div>
    <b-row>
      <b-col></b-col>
      <b-col cols="7">
        <b-nav tabs id="nav-div">
          <b-nav-item :active="step === 1" @click="moveSearch"
            >테마 여행</b-nav-item
          >
          <b-nav-item :active="step === 2" @click="movePlan"
            >나의 여행 계획</b-nav-item
          >
          <b-nav-item :active="step === 5" @click="moveBoard"
            >게시판</b-nav-item
          >
        </b-nav>
      </b-col>
      <b-col> </b-col>
    </b-row>
  </div>
</template>
<script>
export default {
  name: "TheNav",
  components: {},
  data() {
    return {
      message: "",
      slide: 0,
      sliding: null,
      step: 0,
    };
  },
  created() {},

  methods: {
    onSlideStart(slide) {
      this.sliding = true;
    },
    onSlideEnd(slide) {
      this.sliding = false;
    },
    moveSearch() {
      this.step = 1;
      this.$router.push({ name: "TheSearchPlace" }).catch(() => {});
    },
    movePlan() {
      this.step = 2;
      this.$router.push({ name: "TheMakePath" }).catch(() => {});
    },
    moveBoard() {
      this.step = 5;
      this.$router.push({ name: "NoticeBoard" }).catch(() => {});
    },
  },
};
</script>

<style scoped>
img {
  width: 100px;
}

#nav-div {
  justify-content: center;
}
</style>
