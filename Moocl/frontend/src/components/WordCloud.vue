<template lang="html">
  <div>
    <v-card :id="moviename" class="transparent noselect cloudFont" flat>
    </v-card>
  </div>

</template>

<script>
import MovieRecommend from "./MovieRecommend.vue"

export default {
  name: "WordCloud",
  props: ['moviename', 'movieid'],
  components : {
    MovieRecommend
  },
  data: function() {
    return {
      RecommendView: false
    }
  },
  methods : {
    replaceAll : function(str, searchStr, replaceStr){
      return str.split(searchStr).join(replaceStr);
    }
  },
  computed: {
    wordList : function() {
      let wordSets = this.$store.getters.getWordCloudList;
      let length = wordSets.length
      let temp_cloud = {}
      for(var i=0; i<length; i++){
        if(this.movieid == wordSets[i].movieId){
          temp_cloud = wordSets[i].words;
        }
      }

      return temp_cloud;
    },
    cloudData: function() {
      let tempArray = this.wordList;
      let setType = typeof this.wordList[0];
      let returnArray = []

      if(setType == "string"){
        let length = tempArray.length
        for(let i=0; i<length; i++){
          let tempObject = JSON.parse(tempArray[i]);

          let cloudData = {
            text : tempObject.term,
            weight : tempObject.count,
            handlers : {
              mouseover : function(){
                //키워드 추가 제거
              },
              click : function () {
                //영화 추천 함수
                console.log("클릭 이벤트: " + this.innerHTML);
              }
            }
          }
          returnArray.push(cloudData);
        }
      } else {
        let length = tempArray.length
        for(let i=0; i<length; i++){

          let cloudData = {
            text : tempArray[i].term,
            weight : tempArray[i].count,
            handlers : {
              mouseover : function(){
                //키워드 추가 제거
              },
              click : function () {
                //영화 추천 함수
                console.log("클릭 이벤트: " + this.innerHTML);
              }
            }
          }

          returnArray.push(cloudData);
        }
      }
       return returnArray;
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
        autoResize : true,
        fontSize : {
          from : 0.07,
          to : 0.02
        }
      });
    });
  }
}
</script>

<style lang="css">
.noselect { -webkit-touch-callout: none; -webkit-user-select: none; -khtml-user-select: none; -moz-user-select: none; -ms-user-select: none; user-select: none; cursor: pointer}
.cloudFont { font-family: 'Binggrae' !important;}
</style>
