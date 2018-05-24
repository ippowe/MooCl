<template lang="html">
 <v-container class="pa-0">
   <v-card class="ma-0" @mouseenter="showButton" @mouseleave="hideButton" depressed width="126" height="180" :img ="movieinfo.poster">
     <v-card-media>
       <v-flex xs8></v-flex>   <!-- 하트를 오른쪽 보내기 위한 테그 -->
       <v-flex xs4>
         <!--버튼 눌렀을때 favorite 목록에 추가되는 기능 필요  -->
         <v-btn :ripple="false" v-show="showFB" flat icon small @click="putMovieList" depressed>
             <v-icon color="red accent-3">{{heart ? "favorite" : "favorite_border"}}</v-icon>
         </v-btn>
       </v-flex>
     </v-card-media>
   </v-card>
   <v-card width="126" flat class="pt-1">
     <v-card-title class="pa-0 ma-0 text-xs-left">
       <strong>{{ trimTitle }}</strong>
     </v-card-title>
     <div v-for="score in scores">
       <v-tooltip bottom color="white">
         <v-progress-linear slot="activator" :color="score.color" :value="score.score * 10"></v-progress-linear>
         <span>
           <v-icon color="red">star</v-icon>
           <strong class="black--text body-1">{{score.score}}</strong>
         </span>
       </v-tooltip>
     </div>
   </v-card>
 </v-container>

</template>

<script>
export default {
  name : "XsMoviePoster",
  props: ['movieinfo'],
  beforeMount () {
    this.setSiteScore()
  },
  data : function() {
    return{
      showFB : false,
      favorite : false,
      isActor : true,
      scores : {
        naver : {score: 0, color: "green"},
        daum : {score: 0, color: "yellow"},
        cgv :{score: 0, color: "red"}
      },
      role: "주연: 캡틴아메리카"
    }
  },
  methods : {
    showButton : function(e) {
      this.showFB =true;
    },
    hideButton : function(e) {
      this.showFB = false;
    },
    setSiteScore : function() {
      let temp_site_scores = this.movieinfo.score;
      for(var i=0; i<temp_site_scores.length; i++){
        if(temp_site_scores[i].site == "naver"){
          this.scores.naver.score = temp_site_scores[i].grade;
        } else if (temp_site_scores[i].site == "daum"){
          this.scores.daum.score = temp_site_scores[i].grade;
        } else {
          this.scores.cgv.score = temp_site_scores[i].grade;
        }
      }
    },
    putMovieList : function () {
      if(sessionStorage.token){
        //로그인 함
        this. heart = !this.heart
        if(this.heart) {
          //좋아요 추가 요청
          if(this.$state.favMovieList.indexOf(this.movieid) == -1){
            //좋아요 목록에 없을 떄
            console.log(this.movieid);
            console.log(sessionStorage.userNo);
          }
        } else {
          // 좋아요 삭제 요청
          if(this.$state.favMovieList.indexOf(this.movieid) != -1){
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
  },
  computed : {
    trimTitle : function() {
      let temp_title = this.movieinfo.movie_title;
      let str = "";
      if( temp_title.length > 8){
        str = temp_title.slice(0, 9);
        return str;
      } else {
        return temp_title;
      }
    },
    heart : function () {
      let movieChecker = this.$state.favMovieList.indexOf(this.movieid);
      if (movieChecker != -1 ){
        return true;
      } else {
        return false;
      }
    }
  }
}
</script>

<style lang="css">
</style>
