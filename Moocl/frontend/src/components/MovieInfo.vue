<template lang="html">
  <!-- 영화 제목에 따른 알맞은 정보(포스터, 제목, 평점, 감독, 배우들, 워드 클라우드, 개봉일, 관객수, 좋아요 목록 추가 여부) 불러오기 -->
  <v-dialog :max-width="width ? 1200 : 800" v-model="dialog">
    <BigMoviePoster :movietitle="sliceTitle" slot="activator" :posterUrl="movietag.posterUrl"></BigMoviePoster>
    <NormalInfo v-if="detail == false " :movietag="movietag" v-on:viewdetail="changeInfo"></NormalInfo>
    <MovieDetailInfo v-if ="detail == true" :movietag="movietag"></MovieDetailInfo>

  </v-dialog>
</template>

<script>
import BigMoviePoster from "./BigMoviePoster.vue"
import SmallMoviePoster from "./SmallMoviePoster.vue"
import ScoreBySite from "./ScoreBySite.vue"
import WordCloud from "./WordCloud.vue"
import WritingReview from "./WritingReview.vue"
import NormalInfo from "./NormalInfo.vue"
import MovieDetailInfo from "./MovieDetailInfo.vue"

export default {
  name: "MovieInfo",
  props: ['movietag'],
  components:{
    SmallMoviePoster,
    ScoreBySite,
    WordCloud,
    BigMoviePoster,
    WritingReview,
    NormalInfo,
    MovieDetailInfo
  },
  watch : {
    dialog: function (val) {
      if(this.detail == true){
        this.detail = false;
        this.width = false;
      }
    }
  },
  data : function() {
    return {
      dialog: false,
      detail: false,
      width: false
    }
  },
  methods : {
    changeInfo () {
      console.log("viewDetail");
      this.detail = !this.detail;
      this.width = !this.width;
      console.log(this.width);
    }
  },
  computed : {
    sliceTitle () {
      if(this.movietag.movieTitle.length > 5){
        let temp_title = this.movietag.movieTitle.slice(0,6) + "...";
        return temp_title
      } else {
        return this.movietag.movieTitle
      }
    }
  }

}
</script>

<style lang="css">
</style>
