<template>
    <b-col class="sm-3">
        <b-form-select v-model="sidoCode" :options="sidos" @change="changeSido"></b-form-select>
    </b-col>
</template>

<script>
import { mapState, mapActions, mapMutations } from 'vuex';

const placeStore = "placeStore";

export default {
  name: "SelectSido",
  data() {
    return {
      sidoCode: null,
    };
  },
  computed: {
    ...mapState(placeStore, ["sidos"]),
  },
  created() {
    this.CLEAR_SIDO_LIST();
    this.makeSidoList();
  },
  methods: {
    ...mapActions(placeStore, ["makeSidoList"]),
    ...mapMutations(placeStore, ["CLEAR_SIDO_LIST", "SET_SIDO", "SET_FLAG"]),
    changeSido() {
      this.SET_SIDO(this.sidoCode);
      this.SET_FLAG(false);
      this.$emit("select-sido", this.sidoCode);
    },
  },
};
</script>

<style scoped></style>