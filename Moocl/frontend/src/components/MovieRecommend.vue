<template lang="html">
  <div>
    <v-card class="elevation-0 ma-0">
      <v-card-title class="headline pb-2 pl-5 ml-2" style="background-color : #EFF2FB;">
        추천 영화
      </v-card-title>
    </v-card>
    <v-layout style="background-color : #EFF2FB;">
      <v-flex  style="background-color : #EFF2FB;">
        <v-stepper v-model="goodNo" class="pb-3 elevation-0"  style="background-color : #EFF2FB;">
          <v-stepper-items >
            <v-stepper-content v-for="n in 2" :key="n" :step="n" class="pa-0">
              <v-container text-xs-center fill-height class="pa-0">
                <v-layout wrap align-center align-content-center>
                  <v-spacer></v-spacer>
                  <v-btn :ripple="false" flat icon depressed @click="prevstep(true)" ><v-icon>keyboard_arrow_left</v-icon></v-btn>
                  <v-spacer></v-spacer>
                  <v-flex v-for="(movieinfo, index) in goodSlicedMovies[n-1]" :key ="i">
                      <XsMoviePoster :movieinfo="movieinfo.movie" @openNormal="openNormalInfo(n, index, movieinfo.movie)"></XsMoviePoster>
                  </v-flex>
                  <v-btn :ripple="false" flat icon depressed @click="nextstep(true)"> <v-icon>keyboard_arrow_right</v-icon></v-btn>
                  <v-spacer></v-spacer>
                </v-layout>
              </v-container>
            </v-stepper-content>
          </v-stepper-items>
        </v-stepper>
      </v-flex>
    </v-layout>
    <v-card align-start class="elevation-0 ma-0" style="background-color : #EFF2FB;">
      <v-card-title class="headline pb-2 pl-5 ml-2">
        비추천 영화
      </v-card-title>
    </v-card>
    <v-layout  style="background-color : #EFF2FB;">
      <v-flex style="background-color : #EFF2FB;">
        <v-stepper v-model="badNo" class="pb-3 elevation-0"  style="background-color : #EFF2FB;">
          <v-stepper-items >
            <v-stepper-content v-for="n in 2" :key="n" :step="n" class="pa-0">
              <v-container text-xs-center fill-height class="pa-0">
                <v-layout wrap align-center align-content-center>
                  <v-spacer></v-spacer>
                  <v-btn :ripple="false" flat icon depressed @click="prevstep(false)" ><v-icon>keyboard_arrow_left</v-icon></v-btn>
                  <v-spacer></v-spacer>
                  <v-flex v-for="(movieinfo, index) in badSlicedMovies[n-1]" :key ="i">
                      <XsMoviePoster :movieinfo="movieinfo.movie" @openNormal="openNormalInfo(n, index, movieinfo.movie)"></XsMoviePoster>
                  </v-flex>
                  <v-btn :ripple="false" flat icon depressed @click="nextstep(false)"> <v-icon>keyboard_arrow_right</v-icon></v-btn>
                  <v-spacer></v-spacer>
                </v-layout>
              </v-container>
            </v-stepper-content>
          </v-stepper-items>
        </v-stepper>
      </v-flex>
    </v-layout>
  </div>


</template>

<script>
import XsMoviePoster from "./XsMoviePoster.vue"

export default {
  name: "MovieRecommend",
  components: {
    XsMoviePoster
  },
  data () {
      return {
        goodNo: 1,  // 추천 영화 페이지 번호
        badNo: 1,  // 비추천 영화 페이지 번호
        goodSlicedMovies: [],
        badSlicedMovies: []
      }
    },
  beforeMount () {
    let goodMoives = this.goodMovieList();
    let badMovies = this.badMovieList();
    this.goodSlicedMovies = this.slicedMovieList(goodMoives);
    this.badSlicedMovies = this.slicedMovieList(badMovies);
  },
  methods : {
    nextstep : function(value) {
      if(value == true){
        if (this.goodNo < 2){
          this.goodNo = ++this.goodNo;
        } else {
          this.goodNo = 1;
        }
      } else {
        if (this.badNo < 2){
          this.badNo = ++this.badNo;
        } else {
          this.badNo = 1;
        }
      }
    },
    prevstep : function(value) {
      if(value == true) {
        if(this.goodNo > 1){
          this.goodNo = --this.goodNo;
        } else {
          this.goodNo = this.length;
        }
      } else {
        if(this.badNo > 1){
          this.badNo = --this.badNo;
        } else {
          this.badNo = this.length;
        }
      }
    },
    slicedMovieList(movieList) {
      var temp_movieList = [];
      var slice_list = [];
      for(var i=1; i<3; i++){
        slice_list = movieList.slice((i-1)*5, (i*5));
        temp_movieList.push(slice_list);
      }
      return temp_movieList;
    },
    openNormalInfo : function(n, index, movieinfo) {
      let movieTag = this.movieTag(movieinfo);
      let movieId = movieTag.movieId
      this.$store.dispatch('GETCLOUDDATA',movieId)
      .then((result) => {
        this.$eventBus.$emit('reloadNormalInfo', movieTag);
      })
    },
    movieTag : function(movieinfo) {
      let temp_tag = {
        inteTitle : movieinfo.inte_title,
        movieId : movieinfo._id,
        movieTitle : movieinfo.movie_title,
        posterUrl : movieinfo.poster,
        score : movieinfo.score,
        watchingRate : movieinfo.watching_rate
      }
      return temp_tag;
    },
    goodMovieList : function() {
      return this.$store.getters.getGoodMovies;
    },
    badMovieList : function() {
      return this.$store.getters.getBadMovies;
    }
  }
}
</script>

<style lang="css">
</style>
