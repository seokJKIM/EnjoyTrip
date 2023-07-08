<template>
    <b-card
        :img-src="imgSrc"
        img-alt=""
        img-top
        tag="article"
        style="max-width: 20rem"
        class="mb-4 mt-4"
    >
    <b-card-text>
    <h5>{{ tour[0].tourTitle }}</h5>
    </b-card-text>

    <b-button v-b-modal.modal-1 variant="primary" @click="showData"
        >정보 보기</b-button>
    <!-- <b-icon icon="x-circle" scale="2" variant="danger" @click="deleteData" hover style="margin-left: 20px;"></b-icon> -->
    <b-button v-b-modal.modal-1 variant="danger" @click="deleteData" style="margin-left: 10px;"
    >삭제</b-button>
    </b-card>
</template>

<script>
import { mapState, mapActions, mapMutations } from 'vuex';
import { deletePath } from "@/api/pathAxios";

const tourismStore = "tourismStore";
const userStore = "userStore";

export default {
    name: "PlaceListItem",
    props: {
        tour: Array,
    },
    components: {},
    data() {
        return {
        imgSrc : "",
        };
    },
    created() {
        console.log("item");
        console.log(this.tour);
        if(this.tour[0].first_image){
            this.imgSrc = this.tour[0].first_image;
        }else{
            this.imgSrc = "http://tong.visitkorea.or.kr/cms/resource/62/219162_image3_1.jpg";
        }
    },
    computed: {
        ...mapState(tourismStore, ["path"]),
        ...mapState(userStore, ["userInfo"]),
    },
    methods: {
        ...mapActions(tourismStore, ["makePathList"]),
        ...mapMutations(tourismStore, ["UPDATE_DETAIL","CLEAR_PATH_LIST", "SET_PATH_LIST", "ADD_PATH_LIST", "SET_PATH_ID"]),
        // PlaceList에 emit으로 place 전달
        showData() {
            this.CLEAR_PATH_LIST();
            this.makePathList(this.tour);
            console.log(this.tour);
            this.UPDATE_DETAIL(false);
            this.SET_PATH_ID(this.tour[0].pathId);
            //this.UPDATE_DETAIL(true);
            this.$router.push({name: "TheMakePath", params:{detail:"true"}});
        },
        deleteData() {
            let payload = {};
            payload.userId = this.userInfo.userId;
            payload.pathId = this.tour[0].pathId;
            deletePath(
                payload,
                ({data}) => {
                    console.log(data);
                    alert(`${this.tour[0].tourTitle} 여행계획이 삭제되었습니다.`);
                    this.$router.push({ name: "mypage" }).catch(() => {});
                },
                (error) => {
                    console.log(error);
                }
            )
        },
    },
};
</script>
  
<style scoped></style>
  