import axios from "axios";

const BASE_URL = process.env.VUE_APP_BASE_URL;
//const BASE_URL = "http://localhost:80";
let tripAxios = axios.create({
  baseURL: BASE_URL,
  headers: {
    "Content-Type": "application/json;charset=utf-8",
  },
});

const KAKAO_BASE_URL = process.env.VUE_APP_KAKAO_MOBILITY_BASE_URL;
const KAKAO_MAP_API_REST = process.env.VUE_APP_KAKAO_MAP_API_REST_KEY;
let kakaoAxios = axios.create({
  baseURL: KAKAO_BASE_URL,
  headers: {
    "Authorization": "KakaoAK" + " " + KAKAO_MAP_API_REST,
    "Content-Type": "application/json",
  },
});

export {tripAxios, kakaoAxios};
