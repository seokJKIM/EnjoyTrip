import { tripAxios } from "@/api/index";

//board로 요청 보내는 메소드를 여기서 작성

// 글 등록
async function registArticle(article, success, fail) {
  await tripAxios.post("/board/insert", article).then(success).catch(fail);
}

// 글 수정
async function modifyArticle(article, success, fail) {
  await tripAxios.put("/board/modify", article).then(success).catch(fail);
}

// 글 삭제
async function deleteArticle(num, success, fail) {
  const params = { num: num };
  await tripAxios.delete("/board/delete", { params }).then(success).catch(fail);
}

// 게시판 리스트 가져오기
async function getArticleList(boardId, success, fail) {
  const params = { boardId: boardId };
  await tripAxios.get("/board/list", { params }).then(success).catch(fail);
}

// 게시판 검색
async function getSearchList(searchInfo, success, fail) {
  // console.log(searchInfo);
  await tripAxios.post("/board/search", searchInfo).then(success).catch(fail);
}

// 특정 글 보기
async function showArticle(num, success, fail) {
  const params = { num: num };
  await tripAxios.get("/board/detail", { params }).then(success).catch(fail);
}

// 마이페이지에서 게시글 보기
async function getMyPageArticle(userId, success, fail) {
  const params = { userId: userId };
  await tripAxios.get("/board/mypage", { params }).then(success).catch(fail);
}

// async function searchArticle(payload, success, fail){
//     const params = {
//         boardId: payload.boardId,
//         condition: payload.condition,
//         keyword: payload.keyword,
//     }
//     await tripAxios.get("/board/search", {params}).then(success).catch(fail);
// }

export {
  registArticle,
  modifyArticle,
  deleteArticle,
  getArticleList,
  getSearchList,
  showArticle,
  getMyPageArticle,
};
//export { registArticle, modifyArticle, deleteArticle, getArticleList, showArticle, searchArticle}
