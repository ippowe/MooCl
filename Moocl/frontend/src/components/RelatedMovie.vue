<!-- 인물 정보 모달창에서 보여줄 영화 목록  -->
<template lang="html">
  <v-stepper v-model="stepNo" class="pb-3 elevation-0">
    <v-stepper-items >
      <v-stepper-content v-for="n in slicedMovieList.length" :key="n" :step="n" class="pa-0">
        <v-container text-xs-center fill-height class="pa-0">
          <v-layout wrap align-center style="width: 800px">
            <v-spacer></v-spacer>
            <v-btn :ripple="false" flat icon depressed @click="prevstep" ><v-icon>navigate_before</v-icon></v-btn>
            <v-spacer></v-spacer>
            <v-flex v-for="(movieinfo, index) in slicedMovieList[n-1]" :key ="index" color="white">
                <XsMoviePoster :movieinfo="movieinfo" @openNormal="openNormalInfo(n, index, movieinfo)"></XsMoviePoster>
            </v-flex>
            <v-btn :ripple="false" flat icon depressed @click="nextstep"> <v-icon>navigate_next</v-icon></v-btn>
            <v-spacer></v-spacer>
          </v-layout>
        </v-container>

      </v-stepper-content>
    </v-stepper-items>
  </v-stepper>
</template>

<script>
import XsMoviePoster from "./XsMoviePoster.vue"
import MovieInfo from "./MovieInfo.vue"

export default {
  name : "RelatedMovie",
  components: {
    XsMoviePoster,
    MovieInfo
  },
  updated () {
    this.$emit('getAvgGrade');
  },
  props : ['movieinfo'],
  data : function() {
    return {
      stepNo : 1,   // Stepper를 위한 변수
      movieDialog  : false,
      normalTag : {}
    }
  },
  computed : {
    length () {
      var temp_length = 0;
      temp_length = parseInt((this.movieinfo.length - 1) / 5)
      temp_length = temp_length + 1
      return temp_length
    },
    slicedMovieList() {   //영화 페이징을 위한 알고리즘
      var temp_movieList = [];
      var slice_list =[];
      for(var i = 1; i<this.length+1; i++){
        slice_list = this.movieinfo.slice((i-1)*5, (i*5))
        temp_movieList.push(slice_list)
      }
      return temp_movieList
    },
    infoSwitch () {
      let temp_switch = [];
      let temp_inner_switch = [];

      for(let i=1; i<this.length +1; i++){
        temp_inner_switch = [];
        for(let j=0; j<this.slicedMovieList[i-1].length; j++){
          temp_inner_switch[j] = false
        }
        temp_switch[i-1] = temp_inner_switch;
      }
      return temp_switch;
    }
  },
  methods : {
    nextstep : function() {
      if (this.stepNo != this.length){
        this.stepNo = ++this.stepNo;
      } else {
        this.stepNo = 1;
      }
    },
    prevstep : function() {
      if(this.stepNo != 1){
        this.stepNo = --this.stepNo;
      } else {
        this.stepNo = this.length;
      }
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
    openNormalInfo : function(n, index, movieinfo) {
      let movieTag = this.movieTag(movieinfo);
      let movieId = movieTag.movieId
      this.$store.dispatch('GETCLOUDDATA',movieId)
      .then((result) => {
        this.$eventBus.$emit('reloadMovieInfo', movieTag);
      })
    }
  }
}
</script>

<style lang="css">
</style>
