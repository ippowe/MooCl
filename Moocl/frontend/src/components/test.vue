<template lang="html">
  <v-app class="white">
      <TestArea :movietag="MovieTag" :classscore="scores"></TestArea>
  </v-app>
</template>

<script>
import TestArea from './MainCarousel.vue'

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

      scores : [[{
        "site" : "naver",
        "male" : 30
      },{
        "site" : "cgv",
        "male" : 30
      }],[{
        "site" : "naver",
        "twenty" : 20,
        "thirty" : 50,
        "teen" : 10,
        "forty" : 40
      },{
        "site" : "cgv",
        "twenty" : 20,
        "thirty" : 30,
        "teen" : 10,
        "forty" : 40}]]
    }
  }

}
</script>

<style lang="css">
</style>
