<template lang="html">
      <v-card class="ma-0 text-xs-left" @mouseenter="showButton" @mouseleave="hideButton" depressed width="130" height="190" flat>
        <v-card-media height ="139"  :src="resizeUrl" @click="openPersonInfo">
          <v-flex xs8></v-flex>   <!-- 하트를 오른쪽 보내기 위한 테그 -->
          <v-flex xs5  class="pa-0 ma-0">
            <!--버튼 눌렀을때 favorite 목록에 추가되는 기능 필요  -->
            <v-btn :ripple="false" v-show="showFB" flat icon small @click="putPersonList" depressed>
                <v-icon color="red accent-3">{{heart ? "favorite" : "favorite_border"}}</v-icon>
            </v-btn>
          </v-flex>
        </v-card-media>
        <v-card-title class="pa-0 ma-0 text-xs-left">
          <strong>{{ person.person_name }}</strong>
        </v-card-title>
        <v-card-title class="pa-0 ma-0 text-xs-left" v-if=" person.part != '' "  >
          {{ person.role }}
        </v-card-title>
        <v-card-title class="pa-0 ma-0 text-xs-left" v-if=" person.role != '' ">
          {{ person.part }}
        </v-card-title>
      </v-card>
</template>

<script>
export default {
  name: "PersonCard",
  props: ['person'],
  data : function() {
    return{
      heart: false,
      showFB: false,
    }
  },
  computed : {
    resizeUrl : function() {
      var url = this.person.people_img;
      var size = '111_139';
      var tempUrl = url.replace(/77_96/, size);
      return tempUrl;
    },
    heart : function () {
      let movieChecker = this.$state.favMovieList.indexOf(this.movieid);
      if (movieChecker != -1 ){
        return true;
      } else {
        return false;
      }
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
        this. heart = !this.heart
        if(this.heart) {
          //좋아요 추가 요청
          if(this.$state.favPersonList.indexOf(this.movieid) == -1){
            //좋아요 목록에 없을 떄
            console.log(this.movieid);
            console.log(sessionStorage.userNo);
          }
        } else {
          // 좋아요 삭제 요청
          if(this.$state.favPersonList.indexOf(this.movieid) != -1){
            //좋아요 목록에 있을 떄
            console.log(this.movieid);
            console.log(sessionStorage.userNo);
          }
        }
      } else {
        //로그인 안함
        alert("로그인이 필요합니다.");
      }
    }
  }
}
</script>

<style lang="css">
</style>
