const tourismStore = {
  namespaced: true,
  state: {
    path: [],
    detailStatus: "test",
    pathId: null,
  },
  getters: {},
  mutations: {
    CLEAR_PATH_LIST(state) {
      state.path = [];
    },
    SET_PATH_LIST(state, pathList){
        state.path = pathList;
    },
    ADD_PATH_LIST(state, path){
        state.path.push(path);
    },
    REMOVE_PATH_LIST(state, index){
      state.path.splice(index, 1);
    },
    UPDATE_DETAIL(state, detailStatus){
      state.detailStatus = detailStatus;
    },
    SET_PATH_ID(state, pathId){
      state.pathId = pathId;
    }
  },
  actions: {
    makePathList({commit}, pathList){
        commit("SET_PATH_LIST", pathList);
    },
    removePath({commit}, index){
      commit("REMOVE_PATH_LIST", index);
    },
  },
};
export default tourismStore;