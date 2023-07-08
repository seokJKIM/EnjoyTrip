import { tripAxios } from "@/api/index";

async function registNote(article, success, fail) {
  await tripAxios.post("/note/insert", article).then(success).catch(fail);
}

async function selectReceiver(receiver, success, fail) {
  const params = { receiver: receiver };
  await tripAxios
    .get("/note/searchReceive", { params })
    .then(success)
    .catch(fail);
}

async function selectSender(sender, success, fail) {
  const params = { sender: sender };
  await tripAxios.get("/note/searchSend", { params }).then(success).catch(fail);
}

async function updateNote(articleno, success, fail) {
  let note = {
    num: articleno,
  };
  await tripAxios.put("/note/modify", note).then(success).catch(fail);
}

async function getNote(articleno, success, fail) {
  // console.log(articleno);
  const params = { num: articleno };
  await tripAxios.get("/note/detail", { params }).then(success).catch(fail);
}

async function getNoteCount(userid, success, fail) {
  // console.log("유저아이디" + userid);
  const params = { userId: userid };
  await tripAxios.get("/note/notecount", { params }).then(success).catch(fail);
}

export {
  registNote,
  selectReceiver,
  selectSender,
  updateNote,
  getNote,
  getNoteCount,
};
