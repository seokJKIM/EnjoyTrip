<template>
  <div>
    <h1 class="underline">{{trip.title}} 인근 2km 이내 위치한 전기차 충전소</h1>
    <b-row v-if="chargers">
      <b-card-group
        class="col-md-4"
        v-for="(charger, index) in chargers"
        :key="index"
      >
        <charger-list-item :charger="charger" @chargerItem="getCharger"></charger-list-item>
      </b-card-group>
    </b-row>
    <h3 v-else> {{trip.title}} 인근 2km 내에 위치한 전기차 충전소는 없습니다. </h3>

    <b-modal id="modal-1" hide-footer>
      <template #modal-title>
        <code>{{ place.충전소명 }}</code>
      </template>
      <div class="d-block text-center">
        <!-- <h4 style="text-align: left; color:rgba(221, 59, 227);">{{ place.충전소명 }}</h4> -->
        <b-table stacked :items="[place]"></b-table>
      </div>
    </b-modal>
  </div>
</template>

<script>
import ChargerListItem from "@/components/place/charger/ChargerListItem.vue"

export default {
  name: "ChargerList",
  components: {
    ChargerListItem,
  },
  props:{
    chargers: Array,
    trip: Object,
  },
  data() {
    return {
      place : {},
    };
  },
  created() {
  },
  methods: {
    getCharger(place){
      this.place = place;
    }
  },
};
</script>

<style scoped></style>
