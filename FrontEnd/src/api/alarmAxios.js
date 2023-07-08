import { tripAxios } from "@/api/index";

async function insertAlarm(alarm, success, fail) {
  await tripAxios.post("/alarm/insert", alarm).then(success).catch(fail);
}

async function getCount(userid, success, fail) {
  const params = { userId: userid };
  await tripAxios.get("/alarm/count", { params }).then(success).catch(fail);
}

async function getList(userid, success, fail) {
  const params = { userId: userid };
  await tripAxios.get("/alarm/list", { params }).then(success).catch(fail);
}

async function deleteNoteAlarm(articleId, success, fail) {
  const params = { articleId: articleId };
  await tripAxios
    .delete("/alarm/deleteNote", { params })
    .then(success)
    .catch(fail);
}

async function deleteCommentAlarm(articleId, success, fail) {
  // console.log("삭제");
  const params = { articleId: articleId };
  await tripAxios
    .delete("/alarm/deleteComment", { params })
    .then(success)
    .catch(fail);
}

export { insertAlarm, getCount, getList, deleteNoteAlarm, deleteCommentAlarm };
