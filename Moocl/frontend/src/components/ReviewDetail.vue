<template lang="html">

  <v-layout class="white pa-2" fill-height align-center>
    <table class="my-3 py-3" >
      <tr>
        <td class="title" id="updateWords"><strong>{{ reviewcontent.name }}님의 클라우드</strong></td>
      </tr>
      <tr>
        <WordCloud :cloudstring="reviewcontent.review" :moviename="movieinfo._id + 'rvcloud'" cloudsize="1"></WordCloud>
      </tr>
    </table>
    <v-flex text-xs-left class="my-3 py-5 white black--text subheading" xs7>
      <td class="reviewarea pl-3">{{ reviewcontent.review }}</td>
    </v-flex>
  </v-layout>

</template>

<script>
import WordCloud from "./WordCloud.vue"

export default {
  name: "ReviewDetail",
  props:['movieinfo', 'reviewcontent'],
  components : {
    WordCloud
  },
  data : function() {
    return {
    }
  }
}
</script>

<style lang="css">
.noselect { -webkit-touch-callout: none; -webkit-user-select: none; -khtml-user-select: none; -moz-user-select: none; -ms-user-select: none; user-select: none; cursor: pointer}
.reviewarea {border-left: 2px solid grey;}
</style>
