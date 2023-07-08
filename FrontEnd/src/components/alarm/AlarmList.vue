<template>
  <div>
    <alarm-item
      v-for="(article, index) in articleList"
      :key="index"
      :article="article"
    ></alarm-item>
  </div>
</template>

<script>
import { mapState } from "vuex";
import AlarmItem from "@/components/alarm/AlarmItem";
import { getList } from "@/api/alarmAxios";

const userStore = "userStore";

export default {
  name: "AlarmList",
  components: { AlarmItem },
  data() {
    return {
      articleList: [],
    };
  },
  props: {
    articles: Array,
  },
  created() {
    this.articleList = this.articles;
  },
  updated() {},
  methods: {},
  computed: {
    ...mapState(userStore, ["isLogin", "userInfo"]),
  },
  mounted() {
    getList(
      this.userInfo.userId,
      ({ data }) => {
        this.articleList = data;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  watch: {
    articles(nv) {
      this.articleList = nv;
    },
  },
};
</script>

<style scoped></style>
