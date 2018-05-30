<template lang="html">
  <v-app class="white">
      <TestArea :movietag="MovieTag" reviewcontent="reviewContent"></TestArea>
  </v-app>
</template>

<script>
import TestArea from './ScoreBySite.vue'
export default {
  name: 'TestPage',
  created () {
    this.$eventBus.$emit('notMainPage')
  },
  components:{
    TestArea
  },
  data : function() {
    return {
      // dialog: true,
      MovieTag : {
        movieTitle : "영화제목영화제목영화제목영화제목영화제목영화제목영화제목영화제목영화제목영화제목영화제목영화제목영화제목영화제목영화제목",
        movieId : 123456,
        posterUrl : "https://movie-phinf.pstatic.net/20120426_172/1335428116411i030K_JPEG/movie_image.jpg?type=m203_290_2",
        inteTitle : "어벤져스",
        siteScore : [
           {site: "naver", grade: "7.5"},
           {site: "daum", grade: "3.5"},
           {site: "cgv", grade: "6.5"}
         ]
      },
    }
  }

}
</script>

<style lang="css">
</style>
