<template>
  <tr>
    <td>
      {{ this.number - this.index }}
    </td>
    <td>
      <router-link
        :to="{
          name: 'BoardView',
          params: { num: this.article.num, writerId: writer },
        }"
      >
        {{ article.title }}
      </router-link>
    </td>
    <td>{{ article.writerId }}</td>
    <td>{{ article.hits }}</td>
  </tr>
</template>

<script>
export default {
  name: "BoardListItem",
  components: {},
  data() {
    return {
      writer: String,
      num: Number,
      number: Number,
    };
  },
  props: {
    article: Object,
    index: Number,
    len: Number,
  },
  created() {
    console.log(this.len);
    this.number = this.len;
  },
  updated() {
    this.writer = this.article.writerId;
    this.num = this.article.num;
  },
  methods: {},
};
</script>

<style scoped></style>
