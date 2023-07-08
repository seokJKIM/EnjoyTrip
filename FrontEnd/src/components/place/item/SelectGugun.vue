<template>
    <b-col class="sm-3">
        <b-form-select v-model="gugunCode" :options="guguns" @change="changeGugun"></b-form-select>
    </b-col>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";

const placeStore = "placeStore";

export default {
    name: "SelectGugun",
    data() {
        return {
        gugunCode: null,
        };
    },
    props: {
        sidoCode: Number,
    },
    watch: {
        sidoCode() {
            this.CLEAR_GUGUN_LIST();
            this.gugunCode = null;
            if (this.sidoCode) this.makeGugunList(this.sidoCode);
        },
    },
    computed: {
        ...mapState(placeStore, ["guguns"]),
    },
    created() {},
    methods: {
        ...mapActions(placeStore, ["makeGugunList"]),
        ...mapMutations(placeStore, ["CLEAR_GUGUN_LIST", "SET_GUGUN", "SET_FLAG"]),
        changeGugun() {
            this.SET_GUGUN(this.gugunCode);
            this.SET_FLAG(false);
            this.$emit("select-gugun", this.gugunCode);
        },
    },
};
</script>

<style scoped></style>