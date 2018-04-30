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
            <v-flex v-for="i in slicedMovieList[n-1]" :key ="i" color="white">
                <XsMoviePoster :moviename="i"></XsMoviePoster>
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

export default {
  name : "RelatedMovie",
  components: {
    XsMoviePoster
  },
  props : ['personName'],
  data : function() {
    return {
      stepNo : 1,   // Stepper를 위한 변수
      movieList :["어벤져스", "메이즈러너", "인피니티워","데스큐어", "아이언맨","로건"]
    }
  },
  computed : {
    length () {
      var temp_length = 0;
      temp_length = parseInt((this.movieList.length - 1) / 5)
      temp_length = temp_length + 1
      return temp_length
    },
    slicedMovieList() {   //영화 페이징을 위한 알고리즘
      var temp_movieList = [];
      var slice_list =[];
      for(var i = 1; i<this.length+1; i++){
        slice_list = this.movieList.slice((i-1)*5, (i*5))
        temp_movieList.push(slice_list)
      }
      return temp_movieList
    }
  },
  methods : {
    nextstep : function() {
      console.log(this.stepNo)
      if (this.stepNo != this.length){
        this.stepNo = ++this.stepNo;
      } else {
        this.stepNo = 1;
      }
    },
    prevstep : function() {
      console.log(this.stepNo)
      if(this.stepNo != 1){
        this.stepNo = --this.stepNo;
      } else {
        this.stepNo = this.length;
      }
    }
  }
}
</script>

<style lang="css">
</style>
