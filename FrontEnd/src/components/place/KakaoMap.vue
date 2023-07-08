<template>
  <div>
    <center>
      <div id="map"></div>
    </center>
    <b-modal id="bv-modal-example" hide-footer>
      <template #modal-title>
        <code>{{ selectedInfo.title }}</code>
      </template>
      <div class="d-block text-center">
        <img :src="selectedInfo.first_image" style="width: 200px" />
        <hr />
        <h4 style="text-align: left">Preview</h4>
        <hr />
        {{ selectedInfo.overview }}
      </div>
      <b-button
        variant="primary"
        size="sm"
        class="float-right"
        @click="showCharger"
      >
        전기차 충전소 정보
      </b-button>
    </b-modal>
  </div>
</template>

<script>
import { mapMutations } from "vuex";

const placeStore = "placeStore";

export default {
  name: "KakaoMap",
  components: {},
  props: {
    trips: Array,
  },
  data() {
    return {
      map: null,
      mapTypeControl: null,
      zoomControl: null,
      positions: [], //데이터 들어감(title, latlng)
      markers: [],
      selectedInfo: {},
    };
  },
  created() {},
  methods: {
    ...mapMutations(placeStore, ["SET_FLAG"]),
    //api 불러오기
    loadScript() {
      const script = document.createElement("script");
      script.src = `//dapi.kakao.com/v2/maps/sdk.js?appkey=${process.env.VUE_APP_KAKAO_MAP_API_KEY}&autoload=false`;
      /* global kakao */
      script.onload = () => window.kakao.maps.load(this.loadMap);

      document.head.appendChild(script);
    },
    //map 출력하기
    loadMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(37.2429362, 131.8624647, 16),
        level: 3,
      };
      this.map = new kakao.maps.Map(container, options);
      // this.mapTypeControl = new kakao.maps.mapTypeControl();
      // this.map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);
      // this.zoomControl = new kakao.maps.ZoomControl();
      // this.map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
    },
    // 지정한 위치에 마커 불러오기
    loadMarker() {
      //console.log("marker load")
      this.deleteMarker();

      // 마커 이미지를 생성합니다
      const imgSrc =
        "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
      // 마커 이미지의 이미지 크기 입니다
      const imgSize = new kakao.maps.Size(24, 35);
      const markerImage = new kakao.maps.MarkerImage(imgSrc, imgSize);

      // 마커를 생성합니다
      this.markers = [];
      this.positions.forEach((position) => {
        const marker = new kakao.maps.Marker({
          map: this.map, // 마커를 표시할 지도
          position: position.latlng, // 마커를 표시할 위치
          title: position.title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
          image: markerImage, // 마커의 이미지
        });

        kakao.maps.event.addListener(marker, "click", () => {
          this.showInfo(position);
        });

        this.markers.push(marker);
      });

      // 4. 지도를 이동시켜주기
      // 배열.reduce( (누적값, 현재값, 인덱스, 요소)=>{ return 결과값}, 초기값);
      const bounds = this.positions.reduce(
        (bounds, position) => bounds.extend(position.latlng),
        new kakao.maps.LatLngBounds()
      );

      this.map.setBounds(bounds);
      //console.log("marker load")
    },
    deleteMarker() {
      if (this.markers.length > 0) {
        this.markers.forEach((item) => {
          item.setMap(null);
        });
      }
    },
    showInfo(info) {
      this.selectedInfo = info;
      this.$bvModal.show("bv-modal-example");
    },
    showCharger(){
      this.SET_FLAG(true);
      this.$router.push({name: "TheCharger", params: {info : this.selectedInfo}}).catch(()=>{});
    }
  },
  watch:{
        trips(){
            console.log("trips", this.trips);
            this.positions = [];
            this.trips.forEach((position) => {
                let obj = {};
                obj.title = position.title;
                obj.addr1 = position.addr1;
                obj.first_image = position.first_image;
                obj.content_id = position.id;
                obj.lat = position.latitude,
                obj.lng = position.longitude,
                obj.latlng = new kakao.maps.LatLng(position.latitude, position.longitude),
                obj.overview = position.overview;
                obj.sidoCode = position.sidoCode;
                obj.title = position.title;
                obj.content_type_id = position.type;
                this.positions.push(obj);
            });
            console.log("positions",this.positions)
            this.loadMarker();
        },
    },
  mounted() {
    // api 스크립트 소스 불러오기 및 지도 출력
    if (window.kakao && window.kakao.maps) {
      this.loadMap();
    } else {
      this.loadScript();
    }
  },
};
</script>

<style scoped>
#map {
  width: 100%;
  height: 500px;
}
</style>
