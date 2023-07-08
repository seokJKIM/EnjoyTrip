<template>
  <tr>
    <td>
      {{ this.index + 1 }}
    </td>
    <td>
      <router-link :to="{ name: 'NoteView', params: { num: article.num } }">
        {{ this.article.title }}
      </router-link>
      <span v-if="this.article.open == 0">
        <b-badge variant="info">new</b-badge>
      </span>
    </td>
    <td>{{ article.sender }}</td>
    <td>{{ article.createtime }}</td>
  </tr>
</template>

<script>
export default {
  name: "NoteListItem",
  components: {},
  props: {
    article: Object,
    index: Number,
  },
  created() {},
  methods: {},
};
</script>

<style scoped></style>
