import { getSido, getGugun } from "@/api/placeAxios";

const placeStore = {
  namespaced: true,
  state: {
    sidos: [{ value: null, text: "시/도를 선택하세요" }],
    selectedSido : null,
    guguns: [{ value: null, text: "구/군을 선택하세요" }],
    selectedGugun : null,
    contentTypeList: [{ value: 0, text: "관광지 유형" },
    { value: 12, text: "관광지" },
    { value: 14, text: "문화시설" },
    { value: 15, text: "축제공연행사" },
    { value: 25, text: "여행코스" },
    { value: 28, text: "레포츠" },
    { value: 32, text: "숙박" },
    { value: 38, text: "쇼핑" },
    { value: 39, text: "여행코스" },
    { value: 25, text: "음식점" },],
    selectedContentTypeId: 0,
    selectedKeyword: "",
    flag: false,
  },
  getters: {},
  mutations: {
    //search 
    SET_SIDO_LIST(state, sidos) {
      sidos.forEach((sido) => {
        state.sidos.push({ value: sido.sidoCode, text: sido.sidoName });
      });
    },
    SET_SIDO(state, sido){
      state.selectedSido = sido;
    },
    SET_GUGUN_LIST(state, guguns) {
      guguns.forEach((gugun) => {
        state.guguns.push({ value: gugun.gugunCode, text: gugun.gugunName });
      });
    },
    SET_GUGUN(state, gugun){
      state.selectedGugun = gugun;
    },
    SET_CONTENT_TYPE_ID(state, contentTypeId){
      state.selectedContentTypeId = contentTypeId;
    },
    SET_KEYWORD(state, keyword){
      state.selectedKeyword = keyword;
    },
    SET_FLAG(state, flag){
      state.flag = flag;
    },
    CLEAR_SIDO_LIST(state) {
      state.sidos = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_TRIP_LIST(state) {
      state.tripList = [];
      state.trip = null;
    },
    CLEAR_GUGUN_LIST(state) {
      state.guguns = [{ value: null, text: "선택하세요" }];
    },
  },
  actions: {
    async makeSidoList({commit}){
      await getSido(
        ({data}) => {
          commit("SET_SIDO_LIST", data);
          //console.log(data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    async makeGugunList({commit}, sidoCode){
      await getGugun(
        sidoCode,
        ({data}) => {
          commit("SET_GUGUN_LIST", data);
          //console.log(data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};
export default placeStore;