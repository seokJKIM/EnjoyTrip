<template>
  <footer>
    <ul>
      <li><a href="#">개인정보처리방침</a></li>
      <li><a href="#">이용약관</a></li>
      <li><a href="#">오시는길</a></li>
      <li><label>&copy; SSAFY Corp.</label></li>
    </ul>
  </footer>
</template>

<script>
export default {
  name: "TheFooter",
  components: {},
  data() {
    return {
      message: "",
    };
  },
  created() {},
  methods: {},
};
</script>

<style scoped>
footer {
  margin-top: 20px;
}
ul {
  text-align: center;
}
li {
  list-style-type: none;
  margin: 10px 10px 10px 10px;
  display: inline-block;
}
</style>
