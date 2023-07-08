import { tripAxios } from "@/api/index";

//place로 요청 보내는 메소드를 여기서 작성
async function getSido(success, fail) {
    await tripAxios.get("/place/sido").then(success).catch(fail);
}

async function getGugun(sidoCode, success, fail) {
    const params = { code: sidoCode };
    await tripAxios.get("/place/gugun", { params }).then(success).catch(fail);
}

async function getTripList(payload, success, fail) {
    //console.log(payload.gugunCode, payload.contentTypeId, payload.keyword);
    const params = {
      sidoCode: payload.sidoCode,
      gugunCode: payload.gugunCode,
      contentTypeId: payload.contentTypeId,
      keyword: payload.keyword,
    }
    tripAxios.get("/place/searchPlace", { params }).then(success).catch(fail);
}

  export { getSido, getGugun, getTripList };










