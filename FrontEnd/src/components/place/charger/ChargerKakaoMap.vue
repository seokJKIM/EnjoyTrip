<template>
    <div>
        <center>
            <div id="map"></div>
        </center>
        <b-modal id="bv-modal-example" hide-footer>
            <template #modal-title>
                <code>{{ trip.title }}</code>
            </template>
            <div class="d-block text-center">
                <img :src="trip.first_image" style="width: 200px" />
                <hr />
                <h4 style="text-align: left">Preview</h4>
                <hr />
                {{ trip.overview }}
            </div>
        </b-modal>

        <b-modal id="bv-modal-example1" hide-footer>
            <template #modal-title1>
                <code>{{ selectedInfo.statNm }}</code>
            </template>
            <div class="d-block text-center">
                <h4 style="text-align: left; color:rgba(221, 59, 227);">{{ selectedInfo.statNm }}</h4>
                <!-- <h4 style="text-align: left">Preview</h4> -->
                <hr />
                <b-table stacked :items="items"></b-table>
            </div>
        </b-modal>
    </div>
</template>

<script>
export default {
    name: "ChargerKakaoMap",
    components: {},
    props: {
        chargers: Array,
        trip: Object,
    },
    data() {
        return {
            map: null,
            mapTypeControl: null,
            zoomControl: null,
            positions: [], //데이터 들어감(title, latlng)
            markers: [],
            items: [],
            selectedInfo: {},
        };
    },
    created() {},
    methods: {
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
            
            const tripImgSrc = "http://t1.daumcdn.net/localimg/localimages/07/2018/pc/img/marker_spot.png";
            const tripMarkerImage = new kakao.maps.MarkerImage(tripImgSrc, imgSize);

            // 마커를 생성합니다
            this.markers = [];

            for(let i=0; i<this.positions.length; i++){
                if(this.positions[i].chgerType==='01') this.positions[i].chgerType = "DC차데모"
                else if(this.positions[i].chgerType==='02') this.positions[i].chgerType = "DC차데모"
                else if(this.positions[i].chgerType==='03') this.positions[i].chgerType = "DC차데모"
                else if(this.positions[i].chgerType==='04') this.positions[i].chgerType = "DC차데모"
                else if(this.positions[i].chgerType==='05') this.positions[i].chgerType = "DC차데모"
                else if(this.positions[i].chgerType==='06') this.positions[i].chgerType = "DC차데모"
                else this.positions[i].chgerType = "DC차데모"
            }

            this.positions.forEach((position) => {
                const marker = new kakao.maps.Marker({
                    map: this.map, // 마커를 표시할 지도
                    position: position.latlng, // 마커를 표시할 위치
                    title: position.statNm, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
                    image: markerImage, // 마커의 이미지
                });

                kakao.maps.event.addListener(marker, "click", () => {
                    let obj = {}
                    obj.충전소ID = position.statId;
                    obj.충전기타입 = position.chgerType;
                    obj.충전소명 = position.statNm;
                    obj.주소 = position.addr;
                    this.showInfo(obj, position);
                });

                const overlay = new kakao.maps.CustomOverlay({
                    //removable: true,
                    // content: `<span class="info-title">${position.statNm}</span>`
                    content:  `<div class="wrap"> 
                                    <div class="info">
                                        <div class="title">
                                            ${position.statNm}
                                            <div class="close" title="닫기"></div>
                                        </div>
                                        <div class="body">
                                            <div class="img">
                                                <img src="https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMjAyMjVfMjQw%2FMDAxNjQ1Nzc4ODc4Mjkz.PFRrM3KrwyCXsUc373zuFxrtaOaBvk_FJUKmml0adDsg.fIc77VmVOSIAXpy09lD8u3OOSv9bAPx4-cvzl0R9iAQg.PNG.bodyguard40%2F%25C0%25FC%25B1%25E2%25C2%25F7_%25C3%25E6%25C0%25FC%25B1%25E2%25B7%25BB%25C5%25BB.png&type=sc960_832" width="73" height="70">
                                        </div>
                                            <div class="desc">
                                                <div class="ellipsis">${position.chgerType}</div>
                                                <div class="jibun ellipsis">${position.addr}</div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                `,
                    //map: this.map,
                    position: marker.getPosition()
                });

                kakao.maps.event.addListener(marker, "mouseover", () => {
                    overlay.setMap(this.map);
                });
                kakao.maps.event.addListener(marker, "mouseout", () => {
                    overlay.setMap(null);
                });

                this.markers.push(marker);
            });

            const marker = new kakao.maps.Marker({
                map: this.map,
                position: this.trip.latlng,
                title: this.trip.title,
                image: tripMarkerImage,
            })

            kakao.maps.event.addListener(marker, "click", () => {
                this.showTripInfo();
            });

            this.markers.push(marker);

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
        showInfo(info, position) {
            this.items = [info];
            this.selectedInfo = position;
            this.$bvModal.show("bv-modal-example1");
        },
        showTripInfo(){
            this.$bvModal.show("bv-modal-example");
        },
    },
    watch:{
        chargers(){
            console.log("chargersKakaoMap", this.chargers);
            this.positions = [];
            this.chargers.forEach((position) => {
                let obj = {};
                obj.statId = position.statId;
                obj.chgerType = position.chgerType;
                obj.statNm = position.statNm;
                obj.addr = position.addr;
                obj.lat = position.lat,
                obj.lng = position.lng,
                obj.latlng = new kakao.maps.LatLng(position.lat, position.lng),
                this.positions.push(obj);
            });
            //console.log("positions",this.positions)
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

<style>
#map {
    width: 100%;
    height: 500px;
}

.wrap {position: absolute;left: 0;bottom: 40px;width: 288px;height: 132px;margin-left: -144px;text-align: left;overflow: hidden;font-size: 12px;font-family: 'Malgun Gothic', dotum, '돋움', sans-serif;line-height: 1.5;}
.wrap * {padding: 0;margin: 0;}
.wrap .info {width: 286px;height: 120px;border-radius: 5px;border-bottom: 2px solid #ccc;border-right: 1px solid #ccc;overflow: hidden;background: #fff;}
.wrap .info:nth-child(1) {border: 0;box-shadow: 0px 1px 2px #888;}
.info .title {padding: 5px 0 0 10px;height: 30px;background: #eee;border-bottom: 1px solid #ddd;font-size: 18px;font-weight: bold;}
.info .close {position: absolute;top: 10px;right: 10px;color: #888;width: 17px;height: 17px;background: url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/overlay_close.png');}
.info .close:hover {cursor: pointer;}
.info .body {position: relative;overflow: hidden;}
.info .desc {position: relative;margin: 13px 0 0 90px;height: 75px;}
.desc .ellipsis {overflow: hidden;text-overflow: ellipsis;white-space: nowrap;}
.desc .jibun {font-size: 11px;color: #888;margin-top: -2px;}
.info .img {position: absolute;top: 6px;left: 5px;width: 73px;height: 71px;border: 1px solid #ddd;color: #888;overflow: hidden;}
.info:after {content: '';position: absolute;margin-left: -12px;left: 50%;bottom: 0;width: 22px;height: 12px;background: url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png')}
.info .link {color: #5085BB;}
</style>
