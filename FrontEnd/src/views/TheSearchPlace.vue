<template>
    <b-container class="bv-example-row mt-3 text-center">
        <b-row class="mt-4 mb-4 text-center">
            <select-sido @select-sido="selectSido"></select-sido>
            <select-gugun :sidoCode="sidoCode" @select-gugun="selectGugun"></select-gugun>
            <b-col class="sm-3">
                <b-form-select v-model="contentTypeId" :options="contentTypeList" @change="selectContentTypeId"></b-form-select>
            </b-col>
            <input id="keyword" @keyup.enter="search" v-model="keyword" class="form-control me-2" type="text"
                placeholder="검색어" aria-label="검색어" name="keyword" style="width: 400px"/>
            <button @click="search"  id="btn-search" class="btn btn-outline-success" type="button" style="width: 120px">검색</button>
        </b-row>
        <b-row class="mt-3">
        <b-col cols="12">
            <kakao-map :trips="trips"></kakao-map>
        </b-col>
        </b-row>
        <place-list :trips="trips"></place-list>
    </b-container>
</template>

<script>
import { getTripList } from "@/api/placeAxios";
import SelectSido from "@/components/place/item/SelectSido.vue";
import SelectGugun from "@/components/place/item/SelectGugun.vue";
import PlaceList from "@/components/place/PlaceList.vue";
import KakaoMap from "@/components/place/KakaoMap.vue";
import { mapState, mapMutations } from "vuex";

const placeStore = "placeStore";

export default {
    name: 'TheSearchPlace',
    components: {
        KakaoMap,
        SelectSido,
        SelectGugun,
        PlaceList,
    },
    data() {
        return {
            sidoCode: null,
            gugunCode: null,
            contentTypeId: 0,
            keyword : "",
            trips : [],
        };
    },
    computed: {
        ...mapState(placeStore, ["contentTypeList", "selectedSido", "selectedGugun", "selectedContentTypeId", "selectedKeyword", "selectedKeyword", "flag"]),
    },
    created(){
        console.log("search place create")
        console.log(this.flag);
        if(this.flag){
            this.sidoCode = this.selectedSido;
            this.gugunCode = this.selectedGugun;
            this.contentTypeId = this.selectedContentTypeId;
            this.keyword = this.selectedKeyword;
            this.search();
            this.SET_FLAG(false);
        }
    },
    methods: {
        ...mapMutations(placeStore, ["SET_CONTENT_TYPE_ID","SET_FLAG"]),
        selectSido(sidoCode){
            this.sidoCode = sidoCode;
        },
        selectGugun(gugunCode){
            this.gugunCode = gugunCode;
        },
        selectContentTypeId(){
            this.SET_CONTENT_TYPE_ID(this.contentTypeId);
        },
        search(){
            this.SET_FLAG(false);
            if(this.sidoCode==null){
                alert("시도를 선택하시오");
                return
            }
            if(this.gugunCode==null){
                alert("구군을 선택하시오");
                return
            }
            if(this.contentTypeId==0){
                alert("관광지 유형을 선택하시오");
                return
            }
            
            console.log(this.sidoCode, this.gugunCode, this.contentTypeId, this.keyword);
            let params = {
                sidoCode:this.sidoCode, 
                gugunCode:this.gugunCode, 
                contentTypeId:this.contentTypeId, 
            }

            if(this.keyword==null){
                params.keyword = '';
            }else{
                params.keyword = this.keyword;
            }

            getTripList(
                params,
                ({data}) => {
                    this.trips = [];
                    data.forEach((position) => {
                        let obj = {};
                        obj.title = position.title;
                        obj.addr1 = position.addr1;
                        if(position.first_image) obj.first_image = position.first_image;
                        else obj.first_image =  require("@/assets/img/noImg.png");
                        obj.content_id = position.id;
                        obj.latitude = position.latitude;
                        obj.longitude = position.longitude;
                        obj.overview = position.overview;
                        obj.sidoCode = position.sidoCode;
                        obj.title = position.title;
                        obj.content_type_id = position.type;
                        this.trips.push(obj);
                    })
                },
                (error) => {
                    console.log(error);
                }
            );
        },
    },
};
</script>

<style scoped>
#keyword{
    margin-right: 20px;
}
</style>