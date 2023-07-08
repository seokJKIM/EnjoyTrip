import { tripAxios, kakaoAxios } from "@/api/index";
const userStore = "userStore";

//place로 요청 보내는 메소드를 여기서 작성
async function insertPath(payload, userId, success, fail) {
    //payload는 각 경로의 contentId를 배열로 가지고 있는 것
    //userId는 loginUser의 userId
    const params = [];
    for(let i=0; i<payload.length; i++){
        params.push({
            userId: userId,
            sequence: i,
            title: payload[i].title,
            contentId: payload[i].contentId,
        })
    }
    // console.log("params");
    // console.log(params);
    await tripAxios.post("/path/insert", params).then(success).catch(fail);
}

async function listPath(payload, success, fail) {
    //payload - loginUser(id만 있으면 됨)
    await tripAxios.post("/path/list", payload).then(success).catch(fail);
}

async function deletePath(payload, success, fail) {
    await tripAxios.delete("/path/delete?userId="+payload.userId+"&pathId="+payload.pathId).then(success).catch(fail);
}

async function getWayPoint(payload, success, fail){
    let waypointList = [];

    for(let i=1; i<payload.length-1; i++){
        let obj = {
            name: "name"+i,
            x: payload[i].lng,
            y: payload[i].lat
        }
        waypointList.push(obj);
    }

    let para = {
        "origin" :{
            x : payload[0].lng,
            y : payload[0].lat
        },
        "destination":{
            x: payload[payload.length-1].lng,
            y: payload[payload.length-1].lat
        },
    };

    if(waypointList.length>0){
        para = {
            "origin" :{
                x : payload[0].lng,
                y : payload[0].lat
            },
            "destination":{
                x: payload[payload.length-1].lng,
                y: payload[payload.length-1].lat
            },
            "waypoints": waypointList
        }
    }

    await kakaoAxios.post('/directions', para).then(success).catch(fail);
}

export { insertPath, listPath, deletePath, getWayPoint };