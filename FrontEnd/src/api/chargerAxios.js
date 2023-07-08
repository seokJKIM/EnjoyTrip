import { tripAxios } from "@/api/index";

//place로 요청 보내는 메소드를 여기서 작성
async function getCharger(location, success, fail) {
    await tripAxios.post("/charger/list", location).then(success).catch(fail);
}

export { getCharger };