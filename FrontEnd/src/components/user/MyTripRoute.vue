<template>
  <div>
    <h3>내 여행 루트</h3>
    <b-row>
      <b-card-group
        class="col-md-4"
        v-for="(tour, index) in path"
        :key="index"
      >
        <my-trip-route-item :tour="tour"></my-trip-route-item>
      </b-card-group>
    </b-row>
  </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from 'vuex';
import { listPath } from "@/api/pathAxios";
import MyTripRouteItem from "./MyTripRouteItem";

const userStore = "userStore";
const tourismStore = "tourismStore";

export default {
  name: "MyTripRoute",
  components: {
    MyTripRouteItem,
  },
  data() {
    return {
      message: "",
      path: [], //각 경로 - sequence, pathId, contentId, userId, title
      info: [], //각 경로에 해당하는 장소에 대한 정보 - 각 경로 id, type, title, addr1, first_image, latitude, longitude, overview, sidoCode
      place: [],//경로 정보를 담고 있는 하나의 배열에 다 넣어서 그것을 보내주기
    };
  },
  created() {
    let payload = {
      userId : this.userInfo.userId,
    };
    listPath(
      payload,
      ({data}) => {
          console.log(data);
          if(data.message === "success"){
              console.log(data);
              this.path = data.path;
              this.info = data.info;
              console.log("this");
              console.log(this);
              this.makeData();
          }else{
              console.log("데이터 없음")
          }
      },
      (error) => {
          console.log(error);
      }
    );
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
    ...mapState(tourismStore, ["detailStatus"]),
  },
  methods: {
    makeData(){
      for(let i = 0; i<this.path.length; i++){
        for(let j =0; j<this.path[i].length; j++){
          this.path[i][j].type = this.info[i][j].type;
          this.path[i][j].tourTitle = this.path[i][j].title;
          this.path[i][j].title = this.info[i][j].title;
          this.path[i][j].addr1 = this.info[i][j].addr1;
          this.path[i][j].first_image = this.info[i][j].first_image;
          this.path[i][j].lat = this.info[i][j].latitude;
          this.path[i][j].lng = this.info[i][j].longitude;
          this.path[i][j].overview = this.info[i][j].overview;
        }
      }
      console.log("path")
      console.log(this.path);
    }
  },
};
</script>

<style scoped></style>
