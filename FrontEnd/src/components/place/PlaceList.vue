<template>
  <div>
    <b-row>
      <b-card-group
        class="col-md-4"
        v-for="(place, index) in trips"
        :key="index"
      >
        <place-list-item :place="place" @placeItem="getPlace"></place-list-item>
      </b-card-group>

      <!-- <b-modal id="modal-1" title="description">
        <p class="my-4">{{ this.title }}</p>
        <p class="my-4">{{ this.content }}</p>
      </b-modal> -->
      <b-modal id="modal-1" hide-footer>
        <template #modal-title>
          <code>{{ info.title }}</code>
        </template>
        <div class="d-block text-center">
          <img :src="this.info.img" style="width: 200px" />
          <hr />
          <h4 style="text-align: left">Preview</h4>
          <hr />
          {{ this.info.content }}
        </div>
      </b-modal>
    </b-row>
  </div>
</template>

<script>
import PlaceListItem from "./PlaceListItem.vue";

export default {
  name: "PlaceList",
  components: {
    PlaceListItem,
  },
  props: {
    trips: [],
  },
  data() {
    return {
      info: {
        title: "",
        content: "",
        img: "",
      },
    };
  },
  created() {},
  methods: {
    getPlace(place) {
      console.log(place);
      this.info.title = place.title;
      this.info.content = place.overview;
      this.info.img = place.first_image;
    },
  },
};
</script>

<style scoped></style>
