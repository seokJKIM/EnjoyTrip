<template>
  <div class="overflow-auto">
    <!-- Use text in props -->
    <b-pagination
      v-model="currentPage"
      :total-rows="rows"
      :perPage="perPage"
      first-text="<<"
      prev-text="<"
      next-text=">"
      last-text=">>"
    ></b-pagination>
  </div>
</template>

<script>
export default {
  name: "BoardPage",
  props: {
    rows: Number,
    perPage: Number,
  },
  components: {},
  data() {
    return {
      currentPage: 1,
    };
  },
  created() {},
  updated() {
    this.$emit("change", this.currentPage);
  },
  methods: {},
};
</script>

<style scoped></style>
