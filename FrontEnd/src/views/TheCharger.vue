<template>
  <b-container class="bv-example-row mt-3 text-center">
    <b-row class="mt-3">
      <b-col cols="12">
        <charger-kakao-map
          :chargers="chargers"
          :trip="trip"
        ></charger-kakao-map>
      </b-col>
    </b-row>
    <!-- <charger-list :chargers="chargers" :trip="trip"></charger-list> -->
  </b-container>
</template>

<script>
import { getCharger } from "@/api/chargerAxios.js";
//import ChargerList from "@/components/place/charger/ChargerList.vue";
import ChargerKakaoMap from "@/components/place/charger/ChargerKakaoMap.vue";

export default {
  name: "TheCharger",
  components: {
    //ChargerList,
    ChargerKakaoMap,
  },
  data() {
    return {
      chargers: [],
      trip: {},
    };
  },
  created() {
    this.trip = this.$route.params.info;
    console.log(this.trip);
    let chargerDto = {
      lat: this.trip.lat,
      lng: this.trip.lng,
    };
    getCharger(
      chargerDto,
      ({ data }) => {
        this.chargers = data.chargers;
      },
      (error) => {
        console.log(error);
      }
    );
    console.log("chargerList 출력");
    console.log(this.chargers);
  },
  methods: {},
};
</script>

<style scoped>
#keyword {
  margin-right: 20px;
}
</style>
