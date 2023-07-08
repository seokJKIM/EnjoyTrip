import { tripAxios } from "@/api/index";

async function login(user, success, fail) {
  await tripAxios
    .post(`/user/login`, JSON.stringify(user))
    .then(success)
    .catch(fail);
}

async function findById(userid, success, fail) {
  tripAxios.defaults.headers["access-token"] =
    sessionStorage.getItem("access-token");
  await tripAxios.get(`/user/info/${userid}`).then(success).catch(fail);
}

async function tokenRegeneration(user, success, fail) {
  tripAxios.defaults.headers["refresh-token"] =
    sessionStorage.getItem("refresh-token"); //axios header에 refresh-token 셋팅
  await tripAxios.post(`/user/refresh`, user).then(success).catch(fail);
}

async function alreadyUser(userid, success, fail) {
  await tripAxios.get(`/user/check/${userid}`).then(success).catch(fail);
}

async function logout(userid, success, fail) {
  await tripAxios.get(`/user/logout/${userid}`).then(success).catch(fail);
}

async function join(user, success, fail) {
  await tripAxios.post(`/user/join`, user).then(success).catch(fail);
}

async function checkPass(user, success, fail) {
  await tripAxios.post(`/user/pass`, user).then(success).catch(fail);
}

async function changePass(user, success, fail) {
  await tripAxios.post(`/user/changePass`, user).then(success).catch(fail);
}

async function modify(user, success, fail) {
  await tripAxios.post(`/user/modify`, user).then(success).catch(fail);
}

export {
  login,
  findById,
  tokenRegeneration,
  alreadyUser,
  logout,
  join,
  checkPass,
  modify,
  changePass,
};
