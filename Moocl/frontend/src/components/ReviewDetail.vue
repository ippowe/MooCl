<template lang="html">
  <v-layout class="white pa-2" fill-height align-center>
    <table class="my-3 py-3" >
      <tr>
        <td class="title"><strong>{{ reviewcontent.name }}님의 클라우드</strong></td>
      </tr>
      <tr>
        <td :id="writer" class="noselect"></td>
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
  components: {
    WordCloud
  },
  props : ['reviewcontent'],
  data : function() {
    return {
    }
  },
  computed: {
  //   reviewArray: function() {
  //     return this.review.split(' ')
  //   },
  //   nameTag: function() {
  //     return new Set(this.reviewArray)
  //   },
  //   countSet: function() {
  //     var temp = this.reviewArray.reduce(function(x, y) {
  //       x[y] = ++x[y] || 1;
  //       return x
  //     }, {})
  //     return temp
  //   },
  //   cloudData: function() {
  //     let tempArray = []
  //     for (name of this.nameTag) {
  //       var temp = {
  //         text: name,
  //         weight: this.countSet[name],
  //         handlers : {
  //           mouseover : function(){
  //             console.log(this)
  //           }
  //         }
  //       }
  //       tempArray.push(temp)
  //     }
  //     return tempArray
  //   }
  // },
  // mounted() {
  //   var word_array = this.cloudData
  //   var select_id = "#" + this.writer
  //   console.log(select_id)
  //   $(function() {
  //     // When DOM is ready, select the container element and call the jQCloud method, passing the array of words as the first argument.
  //     $(select_id).jQCloud(word_array, {
  //       height: 235,
  //       width : 288,
  //       autoResize: true,
  //       colors : ["#F44336", "#9C27B0", "#3F51B5", "#03A9F4", "#009688", "#64DD17", "#795548"]
  //     });
  //   });
  }
}
</script>

<style lang="css">
.noselect { -webkit-touch-callout: none; -webkit-user-select: none; -khtml-user-select: none; -moz-user-select: none; -ms-user-select: none; user-select: none; cursor: pointer}
.reviewarea {border-left: 2px solid grey;}
</style>
