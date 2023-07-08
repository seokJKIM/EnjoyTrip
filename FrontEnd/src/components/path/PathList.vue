<template>
    <div>
        <b-row id="btnRow">
            <b-button class="btn" v-if="detailStatus===true"  @click="updateTrip">경로 수정</b-button>
            <b-button class="btn" v-else  @click="insert">경로 등록</b-button>
        </b-row>
        <b-row>
            <input id="title" v-model="title" class="form-control me-2" type="text"
                placeholder="경로제목" aria-label="경로제목" name="title" style="width: 400px"/>
            <b-table-simple class="table-item" striped hover>
                <b-thead>
                    <!-- Static column Names -->
                    <b-th> # </b-th>
                    <b-th> 관광지명 </b-th>
                    <b-th></b-th>
                    <!-- <b-th colspan="4"> 관광지명 </b-th> -->
                </b-thead>

                <!-- Draggable rows -->
                <draggable
                    :class="{ [`cursor-grabbing`]: drag === true }"
                    v-model="copyPath"
                    group="copyPath"
                    ghost-class="ghost"
                    @start="drag = true"
                    @end="drag = false"
                    @change="changePath"
                    tag="tbody"
                >
                    <b-tr v-for="(tour, index) in copyPath" :key="index" class="item-row">
                        <b-td> {{ index+1 }}</b-td>
                        <b-td>{{ tour.title }}</b-td>
                        <button v-b-hover="hoverd[index]" @click="deleteTour(index)" style="border:none; margin-top: 15px;">
                            <b-icon v-if="flag[index]" icon="x-circle-fill" scale="2" variant="danger"></b-icon>
                            <b-icon v-else icon="x-circle" scale="2" variant="danger"></b-icon>
                        </button>
                    </b-tr>
                </draggable>
            </b-table-simple>

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
import draggable from'vuedraggable';
import { mapState, mapActions, mapMutations } from 'vuex';
import { insertPath, deletePath, getWayPoint } from "@/api/pathAxios";

const userStore = "userStore";
const tourismStore = "tourismStore";

export default {
    name: "PathList",
    components: {
        draggable,
    },
    data() {
        return {
            info: {
                title: "",
                content: "",
                img: "",
            },
            drag: false,
            copyPath: [],
            title: "",
            isHovered: false,
            flag : {},
        };
    },
    computed: {
        ...mapState(tourismStore, ["path", "detailStatus", "pathId"]),
        ...mapState(userStore, ["userInfo"]),
        hoverd() {
            let handler = []

            for(let idx in this.copyPath){
                let fun = (hover) =>{
                    let index = idx;
                    if(hover === true){
                        this.$set(this.flag, index, true);
                    }else{
                        this.$set(this.flag, index, false);
                    }
                }
                handler.push(fun);
            }

            return handler;
        },
    },
    created() {
        this.copyPath = this.path;
        console.log("여행경로길이")
        console.log(this.copyPath.length);
    },
    watch : {
        path: {
            handler(nv, ov){
                console.log("path 값 변화", nv, ov);
                //console.log(this);
                this.copyPath = nv;
                console.log("copyPath", this.copyPath);
            },
            deep: true,
        },
    },
    methods: {
        ...mapActions(tourismStore, ["makePathList", "removePath"]),
        ...mapMutations(tourismStore, ["CLEAR_PATH_LIST", "SET_PATH_LIST", "ADD_PATH_LIST", "REMOVE_PATH_LIST"]),
        changePath(){
            console.log("changePath", this.copyPath);
            this.makePathList(this.copyPath);
            this.$emit("change-order");
        },
        deleteTour(index){
            this.removePath(index);
            this.$emit("remove-tour");
        },
        insert(){
            let payload = [];
            for(let i = 0; i<this.path.length; i++){
                let obj = {};
                obj.sequence = i;
                obj.contentId = this.path[i].content_id;
                obj.title = this.title;
                payload.push(obj);
            }
            insertPath(
                payload,
                this.userInfo.userId,
                ({data}) => {
                    console.log(data);
                    if(data.message === "success"){
                        alert("여행 경로 등록 성공")
                        this.CLEAR_PATH_LIST();
                        this.$router.push({ name: "MyTripRoute" }).catch(() => {});
                    }else{
                        alert("여행 경로 등록 실패")
                    }
                },
                (error) => {
                    console.log(error);
                }
            )
        },
        updateTrip(){
            let payload = {};
            payload.userId = this.userInfo.userId;
            payload.pathId = this.pathId;
            deletePath(
                payload,
                ({data}) => {
                    console.log(data);
                },
                (error) => {
                    console.log(error);
                }
            )

            payload = [];
            for(let i = 0; i<this.path.length; i++){
                let obj = {};
                obj.sequence = i;
                if(this.path[i].content_id) obj.contentId = this.path[i].content_id;
                else obj.contentId = this.path[i].contentId;
                obj.title = this.title;
                payload.push(obj);
            }
            insertPath(
                payload,
                this.userInfo.userId,
                ({data}) => {
                    console.log(data);
                    if(data.message === "success"){
                        alert("여행 경로 수정 성공")
                        this.CLEAR_PATH_LIST();
                        this.$router.push({ name: "MyTripRoute" }).catch(() => {});
                    }else{
                        alert("여행 경로 등록 실패")
                    }
                },
                (error) => {
                    console.log(error);
                }
            )
        },
    },
};
</script>

<style scoped>
.items {
  width: 77%;
  margin: 0 auto;
  background: black;
  padding: 3rem;
  color: #8de541;
  border-radius: 1rem;
  box-shadow: rgba(99, 99, 99, 0.2) 0px 2px 8px 0px;
  box-shadow: rgba(17, 12, 46, 0.15) 0px 48px 100px 0px;
}

.table-item {
  margin: 0.5rem;
}
.model-header {
  font-size: 1.5rem;
  text-align: center;
  text-transform: uppercase;
  font-family: Verdana, Geneva, Tahoma, sans-serif;
}

.item-row {
	cursor: pointer;
}
.items pre {
	color: #fff;
	width: 200px;
	margin: auto;
}

#btnRow{
	display: flex;
  justify-content: right;
  margin-bottom: 20px;
}

.ghost{
  opacity: 0.5;
  background: #c8ebfb;
}
</style>
