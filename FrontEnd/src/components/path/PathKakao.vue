<template>
    <div>
        <div id="map"></div>
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
            @click="addRoute"
        >
            여행 경로 추가
        </b-button>
        </b-modal>
    </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from 'vuex';
import { getWayPoint } from "@/api/pathAxios";

const tourismStore = "tourismStore";
export default {
    name: 'PathKakao',
    components: {},
    props: {
        trips: Array, //검색해서 나온 관광지들
        order: Boolean,
        detail: Boolean,
    },
    data() {
        return {
            map: null,
            mapTypeControl: null,
            zoomControl: null,
            positions: [], //데이터 들어감(title, latlng)
            markers: [],
            selectedInfo: {},
            kakaoPolyLine: [],
            linePath: null,
        };
    },
    created() {
        console.log("created")
        if(this.detail){
            this.UPDATE_DETAIL(true);
        }
    },
    computed: {
        ...mapState(tourismStore, ["path", "detailStatus"]),
    },
    methods: {
        ...mapActions(tourismStore, ["makePathList"]),
        ...mapMutations(tourismStore, ["UPDATE_DETAIL", "CLEAR_PATH_LIST", "SET_PATH_LIST", "ADD_PATH_LIST"]),
        //api 불러오기
        async loadScript() {
            console.log("loadScript")
            const script = document.createElement("script");
            script.src = `//dapi.kakao.com/v2/maps/sdk.js?appkey=${process.env.VUE_APP_KAKAO_MAP_API_KEY}&autoload=false`;
            /* global kakao */
            script.onload = () => window.kakao.maps.load(this.loadMap);

            document.head.appendChild(script);
        },
        //map 출력하기
        async loadMap() {
            console.log("loadMap")
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
            if((this.path!==null&&this.path.length>0)){
                console.log("경로 생성")
                if(this.path.length > 1){
                    this.makeKakaoPolyLine();
                }else{
                    console.log("경로 1개 마커 찍자")
                    this.loadMarker();
                }
            }else if(this.trips!==null&&this.trips.length>0){
                this.loadMarker();
            }
        },
        makeKakaoPolyLine(){
            this.kakaoPolyLine = [];
            getWayPoint(
                this.path,
                ({data}) => {
                    console.log("wayPoint", data);
                    let dist = data.routes[0].sections;
                    console.log("dist", dist);
                    this.kakaoPolyLine.push(new kakao.maps.LatLng(this.path[0].lat, this.path[0].lng));
                    console.log("총 경로",dist[0].guides);
                    for(let j=0; j<dist.length; j++){
                        for(let k=2; k<dist[j].guides.length; k++){
                            console.log(dist[j].guides[k].y, dist[j].guides[k].x)
                            this.kakaoPolyLine.push(new kakao.maps.LatLng(dist[j].guides[k].y, dist[j].guides[k].x))
                        }
                    }
                    this.loadMarker();
                },
                (error) => {
                    console.log(error);
                }
            )
        },
        // 지정한 위치에 마커 불러오기
        loadMarker() {
            //console.log('load marker', this.map);
            console.log("load Marker", this.map);
            //console.log("marker load")
            this.deleteMarker();
            console.log("delete marker over")
            // 마커 이미지를 생성합니다
            const imgSrc =
                "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
            // 마커 이미지의 이미지 크기 입니다
            const imgSize = new kakao.maps.Size(24, 35);
            const markerImage = new kakao.maps.MarkerImage(imgSrc, imgSize);

            const tripImgSrc = "http://t1.daumcdn.net/localimg/localimages/07/2018/pc/img/marker_spot.png";
            const tripMarkerImage = new kakao.maps.MarkerImage(tripImgSrc, imgSize);
            this.markers = [];
            //console.log("마커 다시 생성", this.path);
            // 마커를 생성합니다
            if(this.positions.length!=0){
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
            }

            //this.polyLine = [];
            //this.kakaoPolyLine = [];
            
            console.log("path: ", this.path);

            this.path.forEach((position) => {
                const marker = new kakao.maps.Marker({
                    map: this.map, // 마커를 표시할 지도
                    position: new kakao.maps.LatLng(position.lat, position.lng), // 마커를 표시할 위치
                    title: position.title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
                    image: tripMarkerImage, // 마커의 이미지
                });

                kakao.maps.event.addListener(marker, "click", () => {
                    this.showInfo(position);
                });

                this.markers.push(marker);
                //this.polyLine.push(new kakao.maps.LatLng(position.lat, position.lng));
                //console.log("------------------------")
                // console.log("여행경로");
                // console.log(position);
            })
            //console.log("foreach over")
            console.log("라인 그리기", this.kakaoPolyLine);
            if(this.path.length>1){
                this.linePath = new kakao.maps.Polyline({
                    map: this.map,
                    //path: this.polyLine, //선을 구성하는 좌표 배열
                    path: this.kakaoPolyLine,
                    strokeWeight: 5, //선의 두께
                    //strokeColor: '#FFAE00', //선의 색깔
                    strokeColor: 'skyblue', //선의 색깔
                    strokeOpacity: 1, //선의 불투명도, 0~1 사이의 값으로 0에 가까울 수록 투명하다.
                    strokeStyle: 'solid' //선의 스타일입니다.
                })
            }

            console.log("선 생성");

            // 4. 지도를 이동시켜주기
            // 배열.reduce( (누적값, 현재값, 인덱스, 요소)=>{ return 결과값}, 초기값);
            let bounds;
            let flag = true;
            if(this.positions.length!=0){
                bounds = this.positions.reduce(
                    (bounds, position) => bounds.extend(position.latlng),
                    new kakao.maps.LatLngBounds()
                );
                flag = false;
            }

            //console.log("경계선 생성");
            if(flag){
                bounds = this.path.reduce(
                    (bounds, position) => bounds.extend(new kakao.maps.LatLng(position.lat, position.lng)),
                    new kakao.maps.LatLngBounds()
                );
            }

            //console.log("map 범위 설정");
            if(this.path.length>1){
                this.linePath.setMap(this.map);
            }
            //console.log("map에 설정");
            this.map.setBounds(bounds);
            //console.log("map에 bounds 설정");
            //console.log("marker load")
        },
        deleteMarker() {
            if (this.markers.length > 0) {
                this.markers.forEach((item) => {
                    item.setMap(null);
                });
            }
            if(this.linePath!=null){
                this.linePath.setMap(null);
            }
        },
        showInfo(info) {
            this.selectedInfo = info;
            this.$bvModal.show("bv-modal-example");
        },
        addRoute(){
            console.log("관광지 추가")
            this.ADD_PATH_LIST(this.selectedInfo);
            this.loadMap();
            //this.loadMarker();
            this.$bvModal.hide("bv-modal-example");
        },
        async makeMap(){
            // api 스크립트 소스 불러오기 및 지도 출력
            if (window.kakao && window.kakao.maps) {
                await this.loadMap();
            } else {
                await this.loadScript();
            }
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
                obj.content_id = position.content_id;
                obj.lat = position.latitude;
                obj.lng = position.longitude;
                obj.latlng = new kakao.maps.LatLng(position.latitude, position.longitude);
                obj.overview = position.overview;
                obj.sidoCode = position.sidoCode;
                obj.title = position.title;
                obj.content_type_id = position.content_type_id;
                this.positions.push(obj);
            });
            //console.log("positions",this.positions)
            // console.log("calling load marker")

            if(this.map !== null)
                this.loadMarker();
        },
        order(nv){
            if(nv){
                if(this.map !== null){
                    this.loadMap();
                }
                this.$emit("end-order");
            }
        },
        detail(nv){
            if(nv){
                if(this.map !== null){
                    this.makeKakaoPolyLine();
                    //this.loadMarker();
                }
                console.log(nv);
                console.log("detail변경")
                this.$emit("end-detail");
            }
            console.log("detail변경")
        },
        detailStatus(nv){
            console.log("detailStatus watch detail변경", nv)
            if(nv){    
                this.makeMap();
                if(this.map !== null)
                    this.loadMarker();
            }
        }
    },
    mounted(){
        // api 스크립트 소스 불러오기 및 지도 출력
        if (window.kakao && window.kakao.maps) {
            console.log("다시 출력1")
            this.loadMap();
        } else {
            console.log("다시 출력2")
            this.loadScript();
        }
    }
};
</script>

<style scoped></style>