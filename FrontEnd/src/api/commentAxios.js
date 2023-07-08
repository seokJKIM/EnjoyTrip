import { tripAxios } from "@/api/index";

// async function reigstComment(comment, success, fail){
//     await tripAxios.get().then(success).catch(fail);
// }

async function selectComment(articleno, success, fail) {
  const params = { articleno: articleno };
  await tripAxios.get("/comment/list", { params }).then(success).catch(fail);
}

async function insertComment(commentDto, success, fail) {
  await tripAxios.post("/comment/insert", commentDto).then(success).catch(fail);
}

async function updateComment(comment, success, fail) {
  await tripAxios.put("/comment/modify", comment).then(success).catch(fail);
}

async function deleteComment(num, success, fail) {
  const params = { num: num };
  await tripAxios
    .delete("/comment/delete", { params })
    .then(success)
    .catch(fail);
}

export { selectComment, insertComment, updateComment, deleteComment };
