<template>
  <div id="box">
    <b-row class="mt-2">
      <b-col cols="4">
        {{ this.article.writerid }}
        <span v-if="this.userInfo.userId === this.article.writerid"
          ><span @click="openModify">수정</span> |
          <span @click="deleteArticle">삭제</span>
          <span @click="modifyArticle" :hidden="hiddenCheck"> | 완료</span>
        </span>
      </b-col>
      <b-col></b-col>
      <b-col></b-col>
      <b-col>
        {{ this.article.createtime }}
      </b-col>
    </b-row>
    <div class="mt-2 mb-2 pl-5 pr-5" id="content">
      <b-form-input v-model="comment.content" :disabled="check"></b-form-input>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import { updateComment } from "@/api/commentAxios";
import { deleteComment } from "@/api/commentAxios";
const userStore = "userStore";

export default {
  name: "CommentItem",
  components: {},
  data() {
    return {
      articles: [],
      comment: null,
      check: true,
      hiddenCheck: true,
    };
  },
  computed: {
    ...mapState(userStore, ["isLogin", "userInfo"]),
  },
  props: {
    article: Object,
    index: Number,
  },
  created() {
    this.comment = this.article;
  },
  methods: {
    openModify() {
      this.check = false;
      this.hiddenCheck = false;
    },

    async deleteArticle() {
      console.log(typeof this.article.num);
      console.log(this.article.num);
      deleteComment(
        this.article.num,
        () => {},
        (error) => {
          console.log(error);
        }
      );
      window.location.reload(true);
    },

    async modifyArticle() {
      let CommentDto = {
        num: this.article.num,
        content: this.comment.content,
      };
      updateComment(
        CommentDto,
        () => {},
        (error) => {
          console.log(error);
        }
      );
      window.location.reload(true);
    },
  },
};
</script>

<style scoped>
#box {
  margin-top: 10px;
  border: 1px solid #ced4da;
  border-radius: 10px;
}

#content {
  text-align: left;
}

button {
  width: 50px;
  height: 50px;
}
</style>
