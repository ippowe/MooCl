<template lang="html">
  <!-- 영화 제목에 따른 알맞은 정보(포스터, 제목, 평점, 감독, 배우들, 워드 클라우드, 개봉일, 관객수, 좋아요 목록 추가 여부) 불러오기 -->
  <v-dialog max-width="800" v-model="dialog">
    <v-btn color="primary" slot="activator">Open Dialog</v-btn>
    <v-layout class="grey darken-1">
      <v-flex class="ma-2 ml-3" xs3>
        <CardForModal></CardForModal>
      </v-flex>
      <v-flex xs8>
        <TotalScore></TotalScore>
        <v-card color ="transparent" flat>
          <v-card-title class="headline white--text">
            개봉일  2018.04.20
            <v-spacer></v-spacer>
            관객수 100,000,000명
          </v-card-title>
        </v-card>
      </v-flex>
    </v-layout>
    <v-layout class="grey darken-1 pl-3">
      <v-spacer></v-spacer>
      <v-flex xs3>
        <span class="headline white--text">영화 제목</span><br>
        <v-btn class="white--text" flat :ripple="false" block> 리뷰 쓰기 </v-btn>
        <v-btn class="white--text" flat :ripple="false" block> 더보기 </v-btn>
      </v-flex>
      <v-spacer></v-spacer>
      <v-flex class="mr-3 mb-3">
        <WordCloud></WordCloud>
      </v-flex>
    </v-layout>
  </v-dialog>
</template>

<script>
import CardForModal from "./CardForModal.vue"
import TotalScore from "./TotalScore.vue"
import WordCloud from "./WordCloud.vue"

export default {
  components:{
    CardForModal,
    TotalScore,
    WordCloud
  },
  name: "SmallModal",
  data : function() {
    return {
      dialog: false
    }
  }
}
</script>

<style lang="css">
</style>
