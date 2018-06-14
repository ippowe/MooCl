<template lang="html">
  <div style="background-color : #EFF2FB;">
    <v-layout>
      <v-flex class="ma-2 pt-4" xs3>
        <SmallMoviePoster :posterUrl="movietag.posterUrl" :movieid="movietag.movieId"></SmallMoviePoster>
      </v-flex>
      <v-flex xs8>
        <ScoreBySite :sitescore="movietag.score" class="pt-5"></ScoreBySite>
      </v-flex>
    </v-layout>
    <v-layout class="pl-3 pt-4">
      <v-spacer></v-spacer>
      <v-flex xs2 class="ml-4">
        <span><strong class="title black--text">{{sliceTitle == undefined ? "영화제목" : sliceTitle }}</strong></span><br>
        <span><strong class="black--text">{{movietag.openDate == undefined ? "2000.01.01": openDate}}</strong></span><br>
        <v-dialog max-width="600px" v-model="dialog">
          <v-btn slot="activator" class="black--text" flat :ripple="false" block  @click="checkLogin"> 리뷰 쓰기 </v-btn>
          <WritingReview v-show="validUser" :movietitle="movietag.movieTitle" :movieid="movietag.movieId" @finWriting="dialog = !dialog" ></WritingReview>
          <NeedLogin v-show="!validUser"></NeedLogin>
        </v-dialog>
        <v-btn class="black--text" flat :ripple="false" block @click="viewDetail"> 더보기 </v-btn>
      </v-flex>
      <v-spacer></v-spacer>
      <v-flex class="mr-3 mb-3">
        <WordCloud :moviename="movietag.movieId" :movieid="movietag.movieId"></WordCloud>
      </v-flex>
    </v-layout>
  </div>

</template>

<script>
import SmallMoviePoster from "./SmallMoviePoster.vue"
import ScoreBySite from "./ScoreBySite.vue"
import WordCloud from "./WordCloud.vue"
import WritingReview from "./WritingReview.vue"
import NeedLogin from "./NeedLogin.vue"

export default {
  name: "NormalInfo",
  props: ['movietag'],
  components:{
    SmallMoviePoster,
    ScoreBySite,
    WordCloud,
    WritingReview,
    NeedLogin
  },
  updated() {
      console.log(this.movietag);
  },
  data : function() {
    return {
      dialog: false,
      validUser: false,
    }
  },
  methods : {
    viewDetail : function() {
      this.$emit('viewdetail');
    },
    checkLogin : function() {
      if(sessionStorage.length == 0){
        this.validUser = false;
      } else {
        this.validUser = true;
      }
    }
  },
  computed : {
    sliceTitle () {
      if(this.movietag.movieTitle.length > 12){
        let temp_title = this.movietag.movieTitle.slice(0,11) + "...";
        return temp_title
      } else {
        return this.movietag.movieTitle
      }
    },
    openDate () {
      if(this.movietag.openDate != undefined){
        let year = this.movietag.openDate.slice(0,4);
        let month = this.movietag.openDate.slice(4,6);
        let day = this.movietag.openDate.slice(6, 9);
        let temp_date = year + "." + month + "." + day;
        return temp_date
      } else {
        let year = this.movietag.open_date.slice(0,4);
        let month = this.movietag.open_date.slice(4,6);
        let day = this.movietag.open_date.slice(6, 9);
        let temp_date = year + "." + month + "." + day;
        return temp_date
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
