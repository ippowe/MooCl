<template lang="html">
      <v-card class="ma-0 text-xs-left indigo lighten-5" @mouseenter="showButton" @mouseleave="hideButton" depressed width="130" height="200" flat>
          <v-card-media height ="139"  :src="resizeUrl" >
            <v-flex @click="openPersonInfo" xs8></v-flex>   <!-- 하트를 오른쪽 보내기 위한 테그 -->
            <v-flex @click="openPersonInfo" xs5  class="pa-0 ma-0">
              <!--버튼 눌렀을때 favorite 목록에 추가되는 기능 필요  -->
              <v-btn :ripple="false" v-show="showFB" flat icon small @click.stop="putPersonList" depressed>
                  <v-icon color="red accent-3">{{heart ? "favorite" : "favorite_border"}}</v-icon>
              </v-btn>
            </v-flex>
          </v-card-media>
          <v-card-title class="pa-0 ma-0 text-xs-left"  style="background-color : #EFF2FB;">
            <strong>{{ person.person_name }}</strong>
          </v-card-title>
          <v-card-title class="pa-0 ma-0 text-xs-left" v-if=" person.part != '' " style="background-color : #EFF2FB;" >
            {{ person.role }}
          </v-card-title>
          <v-card-title class="pa-0 ma-0 text-xs-left" v-if=" person.role != '' "  style="background-color : #EFF2FB;">
            {{ person.part }}
          </v-card-title>
      </v-card>
</template>

<script>
export default {
  name: "PersonCard",
  props: ['person'],
  created () {
    this.checkFavList();
  },
  updated(){
    this.checkFavList();
  },
  data : function() {
    return{
      heart: false,
      showFB: false,
    }
  },
  computed : {
    resizeUrl : function() {
      var url = this.person.people_img;
      var tempUrl = "";

      if(url == "hasNoImg"){
          tempUrl = "https://ssl.pstatic.net/static/movie/2012/06/dft_img77x96_1.png"
      } else {
          var size = '111_139';
          tempUrl = url.replace(/77_96/, size);
      }
      return tempUrl;
    }
  },
  methods : {
    showButton : function(e) {
      this.showFB =true;
    },
    hideButton : function(e) {
      this.showFB = false;
    },
    openPersonInfo : function() {
      this.$emit('openPersonInfo')
    },
    putPersonList : function () {
      if(sessionStorage.token){
        //로그인 함
          let favPersonIndex = this.$store.getters.getFavPersonList.indexOf(this.person.person_id.toString());
          //좋아요 추가 요청
          if (favPersonIndex == -1){
            let personId = this.person.person_id;
            let userId = sessionStorage.userNo;
            this.$axios.post('/api/addfavpeople', {personId, userId})
            .then(() => {
              this.$store.getters.getFavPersonList.push(personId.toString());
              this.heart = true;
            })
          } else if (favPersonIndex != -1 || personIntCheker != -1){
          //좋아요 목록에 있을 떄
          // 좋아요 삭제 요청
            let personId = this.person.person_id;
            let userId = sessionStorage.userNo;
            this.$axios.post('/api/delfavpeople', {personId, userId})
            .then(() => {
              //this.$store.getters.getFavPersonList.splice(favPersonIndex, 1);
              this.$eventBus.$emit('delInPage', personId, favPersonIndex);
              this.heart = false;
            })
        }
      } else {
        //로그인 안함
        alert("로그인이 필요합니다.");
      }
    },
    checkFavList : function () {
      let personChecker = this.$store.getters.getFavPersonList.indexOf(this.person.person_id.toString());
      let personIntCheker = this.$store.getters.getFavPersonList.indexOf(this.person.person_id);
      if (personChecker != -1 || personIntCheker != -1){
        this.heart =  true;
      } else {
        this.heart = false;
      }
    }
  }
}
</script>

<style lang="css">
</style>
