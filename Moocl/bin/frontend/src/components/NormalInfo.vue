<template lang="html">
  <div>
    <v-layout class="white">
      <v-flex class="ma-2" xs3>
            <SmallMoviePoster :posterUrl="movietag.posterUrl"></SmallMoviePoster>
      </v-flex>
      <v-flex xs8>
        <ScoreBySite :sitescore="movietag.score"></ScoreBySite>
        <v-card color ="transparent" flat>
          <v-card-title class="headline black--text">
            개봉일  2018.04.20
            <v-spacer></v-spacer>
            관객수 100,000,000명
          </v-card-title>
        </v-card>
      </v-flex>
    </v-layout>
    <v-layout class="white pl-3">
      <v-spacer></v-spacer>
      <v-flex xs2 class="ml-4">
        <span><strong class="title black--text">{{sliceTitle == undefined ? "영화제목" : sliceTitle }}</strong></span><br>
        <v-dialog max-width="600px">
          <v-btn slot="activator" class="black--text" flat :ripple="false" block  @click="checkLogin"> 리뷰 쓰기 </v-btn>
          <WritingReview v-show="validUser" :movietitle="movietag.movieTitle" :movieid="movietag.movieId"></WritingReview>
          <NeedLogin v-show="!validUser"></NeedLogin>
        </v-dialog>
        <v-btn class="black--text" flat :ripple="false" block @click="viewDetail"> 더보기 </v-btn>
      </v-flex>
      <v-spacer></v-spacer>
      <v-flex class="mr-3 mb-3">
        <WordCloud :moviename="movietag.movieId == undefined ? '영화제목' : movietag.movieId"></WordCloud>
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
  create () {
    this.movietag.score = [];
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
  }

}
</script>

<style lang="css">
</style>
