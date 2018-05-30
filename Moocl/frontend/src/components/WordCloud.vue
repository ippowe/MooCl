<template lang="html">
  <div>
    <v-card :id="moviename" class="transparent noselect" flat>
    </v-card>
  </div>

</template>

<script>
import MovieRecommend from "./MovieRecommend.vue"

export default {
  name: "WordCloud",
  props: ['moviename'],
  components : {
    MovieRecommend
  },
  data: function() {
    return {
      RecommendView: false
    }
  },
  computed: {
    cloudData: function() {
      let tempArray = []
      for (name of this.nameTag) {
        var temp = {
          text: name,
          weight: this.countSet[name],
          handlers : {
            mouseover : function(){
            },
            click : function() {
              console.log("클릭 이벤트: " + this.innerHTML)
            }
          }
        }
        tempArray.push(temp)
      }
      return tempArray
    }
  },
  mounted() {
    var word_array = this.cloudData
    var select_id = "#" + this.moviename
    $(function() {
      // When DOM is ready, select the container element and call the jQCloud method, passing the array of words as the first argument.
      $(select_id).jQCloud(word_array, {
        height: 200,
        width : 600,
        colors : ["#F44336", "#9C27B0", "#3F51B5", "#03A9F4", "#009688", "#64DD17", "#795548"]
      });
    });
  }
}
</script>

<style lang="css">
.noselect { -webkit-touch-callout: none; -webkit-user-select: none; -khtml-user-select: none; -moz-user-select: none; -ms-user-select: none; user-select: none; cursor: pointer}
</style>
