<template lang="html">
  <!-- 영화 제목에 따른 알맞은 정보(포스터, 제목, 평점, 감독, 배우들, 워드 클라우드, 개봉일, 관객수, 좋아요 목록 추가 여부) 불러오기 -->
  <v-dialog max-width="800" v-model="dialog">
    <BigMoviePoster :moviename="moviename == undefined ? '영화제목' : moviename" slot="activator"></BigMoviePoster>
    <v-layout class="white">
      <v-flex class="ma-2 ml-3" xs3>
            <SmallMoviePoster></SmallMoviePoster>
      </v-flex>
      <v-flex xs8>
        <ScoreBySite></ScoreBySite>
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
      <v-flex xs2>
        <span class="headline black--text">{{ moviename == undefined ? "영화제목" : moviename }}</span><br>
        <v-btn class="black--text" flat :ripple="false" block> 리뷰 쓰기 </v-btn>
        <v-btn class="black--text" flat :ripple="false" block> 더보기 </v-btn>
      </v-flex>
      <v-spacer></v-spacer>
      <v-flex class="mr-3 mb-3">
        <WordCloud :moviename="moviename == undefined ? '영화제목' : moviename"></WordCloud>
      </v-flex>
    </v-layout>
  </v-dialog>
</template>

<script>
import BigMoviePoster from "./BigMoviePoster.vue"
import SmallMoviePoster from "./SmallMoviePoster.vue"
import ScoreBySite from "./ScoreBySite.vue"
import WordCloud from "./WordCloud.vue"
import WritingReview from "./WritingReview.vue"

export default {
  name: "MovieInfo",
  props: ['moviename'],
  components:{
    SmallMoviePoster,
    ScoreBySite,
    WordCloud,
    BigMoviePoster,
    WritingReview
  },
  data : function() {
    return {
      dialog: false,
    }
  },

}
</script>

<style lang="css">
</style>
