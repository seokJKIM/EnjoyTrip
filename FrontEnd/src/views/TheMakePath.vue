<template>
    <b-container class="bv-example-row mt-3 text-center">
        <b-row class="mt-4 mb-4 text-center">
            <select-sido @select-sido="selectSido"></select-sido>
            <select-gugun :sidoCode="sidoCode" @select-gugun="selectGugun"></select-gugun>
            <b-col class="sm-3">
                <b-form-select v-model="contentTypeId" :options="contentTypeList" @change="selectContentTypeId"></b-form-select>
            </b-col>
            <input id="keyword" @keyup.enter="search" v-model="keyword" class="form-control me-2" type="text"
                placeholder="검색어" aria-label="검색어" name="keyword" style="width: 200px"/>
            <button @click="search"  id="btn-search" class="btn btn-outline-success" type="button" style="width: 100px; margin-left:20px;">검색</button>
        </b-row>
        <b-row class="mt-3">
            <b-col cols="8">
                <path-kakao :trips="trips" :order="orderFlag" @end-order="endOrder" :detail="detailFlag" @end-detail="endDetail"></path-kakao>
            </b-col>
            <b-col cols="4">
                <path-list @change-order="changeOrder" @remove-tour="changeOrder"></path-list>
            </b-col>
        </b-row>
    </b-container>
</template>

<script>
import { getTripList } from "@/api/placeAxios";
import SelectSido from "@/components/place/item/SelectSido.vue";
import SelectGugun from "@/components/place/item/SelectGugun.vue";
import PathKakao from '@/components/path/PathKakao.vue';
import PathList from '@/components/path/PathList.vue';
import { mapState, mapMutations } from "vuex";

const placeStore = "placeStore";
const tourismStore = "tourismStore";

export default {
    name: 'TheMakePath',
    components: {
        SelectSido,
        SelectGugun,
        PathKakao,
        PathList,
    },
    data() {
        return {
            sidoCode: null,
            gugunCode: null,
            contentTypeId: 0,
            keyword : "",
            trips : [],
            orderFlag : false,
            detailFlag : false,
        };
    },
    created() {
        if(this.$route.params.detail){
            this.detailFlag = true;
        }else{
            this.detailFlag = false;
            this.CLEAR_PATH_LIST();
        }
    },
    computed: {
        ...mapState(placeStore, ["contentTypeList", "selectedSido", "selectedGugun", "selectedContentTypeId", "selectedKeyword", "flag"]),
    },
    methods: {
        ...mapMutations(placeStore, ["SET_CONTENT_TYPE_ID","SET_FLAG"]),
        ...mapMutations(tourismStore, ["CLEAR_PATH_LIST"]),
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
        changeOrder(){
            this.orderFlag = true;
        },
        endOrder(){
            this.orderFlag = false;
        },
        endDetail(){
            this.detailFlag = false;
        }
    },
};
</script>

<style scoped></style>