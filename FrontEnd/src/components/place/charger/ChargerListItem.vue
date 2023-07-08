<template>
  <div>
    <b-card
      img-src="@/assets/img/charger.png"
      img-alt="Image"
      img-top
      tag="article"
      style="max-width: 20rem"
      class="mb-4 mt-4"
    >
      <b-card-text>
        <h5>{{charger.statNm}}</h5>
        <!-- 주소 : {{ charger.addr }}
        충전기 타입 : {{charger.chgerType | chargerType}}
        충전소 ID : {{charger.statId}}
        충전소 명 : {{charger.statNm}} -->
      </b-card-text>
      <b-button v-b-modal.modal-1 variant="primary" @click="showInfo">상세정보</b-button>
    </b-card>
  </div>
</template>

<script>
export default {
  name: "ChargerListItem",
  props: {
    charger: Object,
  },
  components: {},
  data() {
    return {
      info: {},
    };
  },
  created() {
    this.info.충전소ID = this.charger.statId;
    if(this.charger.chgerType==='01') this.info.충전기타입 = "DC차데모"
    else if(this.charger.chgerType==='02') this.info.충전기타입 = "AC완속"
    else if(this.charger.chgerType==='03') this.info.충전기타입 = "DC차데모 + AC3상"
    else if(this.charger.chgerType==='04') this.info.충전기타입 = "DC콤보"
    else if(this.charger.chgerType==='05') this.info.충전기타입 = "DC차데모 + DC콤보"
    else if(this.charger.chgerType==='06') this.info.충전기타입 = "DC차데모 + AC3상 + DC 콤보"
    else this.info.충전기타입 = "AC3상"
    this.info.충전소명 = this.charger.statNm;
    this.info.주소 = this.charger.addr;
  },
  filters:{
    chargerType(chgerType){
      if(chgerType==='01') return "DC차데모"
      else if(chgerType==='02') return "AC완속"
      else if(chgerType==='03') return "DC차데모 + AC3상"
      else if(chgerType==='04') return "DC콤보"
      else if(chgerType==='05') return "DC차데모 + DC콤보"
      else if(chgerType==='06') return "DC차데모 + AC3상 + DC 콤보"
      else return "AC3상"
    }
  },
  methods: {
    showInfo(){
      this.$emit("chargerItem", this.info);
    },
  },
};
</script>

<style scoped></style>
