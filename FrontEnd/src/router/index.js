import Vue from "vue";
import VueRouter from "vue-router";
import store from "@/store";
import HomeView from "../views/HomeView.vue";
//import AppView from '@/App.vue';

// views
import TheBoard from "@/views/TheBoard.vue";
import TheSearchPlace from "@/views/TheSearchPlace";
import TheUser from "@/views/TheUser";
import MyPage from "@/views/MyPage";
import TheCharger from "@/views/TheCharger";
import TheMakePath from "@/views/TheMakePath";

// components
// board
import BoardType from "@/components/board/BoardType";
import NoticeBoard from "@/components/board/NoticeBoard";
import FreeBoard from "@/components/board/FreeBoard";
import ReviewBoard from "@/components/board/ReviewBoard";
import TeamBoard from "@/components/board/TeamBoard";
import BoardView from "@/components/board/BoardView";
import BoardModify from "@/components/board/BoardModify";
import BoardWrite from "@/components/board/BoardWrite";

// user
import UserLogin from "@/components/user/UserLogin";
import UserRegister from "@/components/user/UserRegister";

// mypage
import MyBoardContents from "@/components/user/MyBoardContents";
import MyInfo from "@/components/user/MyInfo";
import MyPageList from "@/components/user/MyPageList";
import MyTripRoute from "@/components/user/MyTripRoute";
import MyInfoModify from "@/components/user/MyInfoModify";
import CheckPass from "@/components/user/CheckPass";
import PassChange from "@/components/user/PassChange";

// note
import MyNote from "@/components/note/MyNote";
import SendNote from "@/components/note/SendNote";
import ReceiveNote from "@/components/note/ReceiveNote";
import NoteWrite from "@/components/note/NoteWrite";
import NoteView from "@/components/note/NoteView";

Vue.use(VueRouter);

// https://v3.router.vuejs.org/kr/guide/advanced/navigation-guards.html
const onlyAuthUser = async (to, from, next) => {
  const checkUserInfo = store.getters["userStore/checkUserInfo"];
  const checkToken = store.getters["userStore/checkToken"];
  let token = sessionStorage.getItem("access-token");
  console.log("로그인 처리 전", checkUserInfo, token);

  if (checkUserInfo != null && token) {
    console.log("토큰 유효성 체크하러 가자!!!!");
    await store.dispatch("userStore/getUserInfo", token);
  }
  if (!checkToken || checkUserInfo === null) {
    alert("로그인이 필요한 페이지입니다..");
    // next({ name: "login" });
    router.push({ name: "login" }).catch(() => {});
  } else {
    console.log("로그인 했다!!!!!!!!!!!!!.");
    next();
  }
};

const routes = [
  {
    path: "/",
    name: "home",
    component: HomeView,
  },
  {
    path: "/board",
    name: "board",
    component: TheBoard,
    children: [
      {
        path: "Type",
        name: "Type",
        component: BoardType,
      },
      {
        path: "Notice",
        name: "NoticeBoard",
        component: NoticeBoard,
      },
      {
        path: "Free",
        name: "FreeBoard",
        component: FreeBoard,
      },
      {
        path: "Review",
        name: "ReviewBoard",
        component: ReviewBoard,
      },
      {
        path: "Team",
        name: "TeamBoard",
        component: TeamBoard,
      },
      {
        path: "View/:num",
        name: "BoardView",
        beforeEnter: onlyAuthUser,
        component: BoardView,
      },
      {
        path: "write",
        name: "BoardWrite",
        beforeEnter: onlyAuthUser,
        component: BoardWrite,
      },
      {
        path: "Modify",
        name: "BoardModify",
        beforeEnter: onlyAuthUser,
        component: BoardModify,
      },
    ],
  },
  {
    path: "/search",
    name: "TheSearchPlace",
    beforeEnter: onlyAuthUser,
    component: TheSearchPlace,
  },
  {
    path: "/user",
    name: "user",
    component: TheUser,
    children: [
      {
        path: "join",
        name: "join",
        component: UserRegister,
      },
      {
        path: "login",
        name: "login",
        component: UserLogin,
      },
    ],
  },
  {
    path: "/mypage",
    name: "mypage",
    beforeEnter: onlyAuthUser,
    component: MyPage,
    children: [
      {
        path: "mynote",
        name: "mynote",
        beforeEnter: onlyAuthUser,
        component: MyNote,
        children: [
          {
            path: "SendNote",
            name: "SendNote",
            beforeEnter: onlyAuthUser,
            component: SendNote,
          },
          {
            path: "ReceiveNote",
            name: "ReceiveNote",
            beforeEnter: onlyAuthUser,
            component: ReceiveNote,
          },
          {
            path: "NoteView",
            name: "NoteView",
            component: NoteView,
          },
        ],
      },
      {
        path: "NoteWrite",
        name: "NoteWrite",
        component: NoteWrite,
      },
      {
        path: "MyBoardContents",
        name: "MyBoardContents",
        beforeEnter: onlyAuthUser,
        component: MyBoardContents,
      },
      {
        path: "MyInfo",
        name: "MyInfo",
        beforeEnter: onlyAuthUser,
        component: MyInfo,
      },
      {
        path: "MyPage",
        name: "MyPage",
        beforeEnter: onlyAuthUser,
        component: MyPageList,
      },
      {
        path: "mytriproute",
        name: "MyTripRoute",
        beforeEnter: onlyAuthUser,
        component: MyTripRoute,
      },
      {
        path: "MyInfoModify",
        name: "MyInfoModify",
        beforeEnter: onlyAuthUser,
        component: MyInfoModify,
      },
      {
        path: "CheckPass",
        name: "CheckPass",
        component: CheckPass,
      },
      {
        path: "PassChange",
        name: "PassChange",
        component: PassChange,
      },
    ],
  },
  {
    path: "/charger",
    name: "TheCharger",
    beforeEnter: onlyAuthUser,
    component: TheCharger,
  },
  {
    path: "/makePath",
    name: "TheMakePath",
    beforeEnter: onlyAuthUser,
    component: TheMakePath,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
