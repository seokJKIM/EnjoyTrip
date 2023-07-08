<template>
  <div>
    <h3>내 쪽지</h3>
    <div>
      <b-link @click="moveSendNote">보낸 쪽지함</b-link> |
      <b-link @click="moveReceiveNote">받은 쪽지함</b-link>

      <router-view></router-view>
    </div>
  </div>
</template>

<script>
export default {
  name: "MyNote",
  components: {},
  data() {
    return {
      message: "",
    };
  },
  created() {},
  methods: {
    moveSendNote() {
      this.$router.push({ name: "SendNote" }).catch(() => {});
    },
    moveReceiveNote() {
      this.$router.push({ name: "ReceiveNote" }).catch(() => {});
    },
  },
};
</script>

<style scoped></style>
