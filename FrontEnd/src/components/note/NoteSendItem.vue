<template>
  <tr>
    <td>
      {{ this.index + 1 }}
    </td>
    <td>
      <router-link :to="{ name: 'NoteView', params: { num: article.num } }">
        {{ article.title }}
      </router-link>
    </td>
    <td>{{ article.receiver }}</td>
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
