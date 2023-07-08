<template>
  <div>
    <b-row id="row">
      <b-col></b-col>
      <b-col cols="2"><board-type class="mt-5"></board-type></b-col>
      <b-col cols="8"><router-view class="mt-3"></router-view></b-col>
      <b-col></b-col>
    </b-row>
  </div>
</template>

<script>
import BoardType from "@/components/board/BoardType.vue";
export default {
  name: "TheBoard",
  components: {
    BoardType,
  },
  data() {
    return {
      message: "",
    };
  },
  created() {
    // this.$router.push("/board/Notice").catch(() => {});
  },
  methods: {},
};
</script>

<style scoped>
#row {
  /* border-bottom: 1px solid #dee2e6; */
}
</style>
