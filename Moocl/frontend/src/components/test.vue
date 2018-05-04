<template lang="html">
  <v-app class="white">
    <TestArea :movietag="MovieTag"></TestArea>
  </v-app>
</template>

<script>
import TestArea from './MovieInfo.vue'
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
      MovieTag : {
        movieTitle : "어벤져스",
        movieId : 123456,
        posterUrl : "https://movie-phinf.pstatic.net/20120426_172/1335428116411i030K_JPEG/movie_image.jpg?type=m203_290_2",
        inteTitle : "어벤져스",
        siteScore : [
           {site: "naver", grade: "7.5"},
           {site: "daum", grade: "3.5"},
           {site: "cgv", grade: "6.5"}
         ]
      }
    }
  }

}
</script>

<style lang="css">
</style>
