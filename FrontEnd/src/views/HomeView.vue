<template>
  <div>
    <b-carousel
      id="carousel-1"
      v-model="slide"
      :interval="4000"
      controls
      indicators
      background="#ababab"
      style="text-shadow: 1px 1px 2px #333"
      @sliding-start="onSlideStart"
      @sliding-end="onSlideEnd"
    >
      <!-- Text slides with image -->
      <b-carousel-slide
        caption="테마 여행"
        text="맛집 여행, 관광 여행 등 테마별로 관광지를 확인해보세요"
        img-src="@/assets/img/001.jpg"
      ></b-carousel-slide>

      <!-- Text slides with image -->
      <b-carousel-slide
        caption="나의 여행 계획"
        text="나만의 여행 계획을 세우고 저장해보세요"
        img-src="@/assets/img/002.jpg"
      ></b-carousel-slide>

      <!-- Text slides with image -->
      <b-carousel-slide
        caption="게시판"
        text="다른 사람들의 여행 꿀팁을 확인하세요"
        img-src="@/assets/img/005.jpg"
      ></b-carousel-slide>
    </b-carousel>
  </div>
</template>

<script>
export default {
  name: "TheHome",
  components: {},
  data() {
    return {
      slide: 0,
      sliding: null,
      msg: "",
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
  },
};
</script>

<style scoped>
div {
  width: 100%;
  height: auto;
  max-height: 550px;
}
</style>
