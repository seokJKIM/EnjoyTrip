<template>
  <!--  <tr>
        <td><img :src="place.first_image"></td>
        <td>{{place.title}}</td>
        <td>{{place.addr1}}</td>
        <td>{{place.latitude}}</td>
        <td>{{place.longitude}}</td>
    </tr> -->
  <b-card
    :img-src="place.first_image"
    img-alt="Image"
    img-top
    tag="article"
    style="max-width: 20rem"
    class="mb-4 mt-4"
  >
    <b-card-text>
      <h5>{{ place.title }}</h5>
      주소 : {{ place.addr1 }}
    </b-card-text>

    <b-button v-b-modal.modal-1 variant="primary" @click="updateData"
      >정보</b-button
    >
  </b-card>
</template>

<script>
export default {
  name: "PlaceListItem",
  props: {
    place: Object,
  },
  components: {},
  data() {
    return {};
  },
  created() {},
  methods: {
    // PlaceList에 emit으로 place 전달
    updateData() {
      this.$emit("placeItem", this.place);
    },
  },
};
</script>

<style scoped></style>
