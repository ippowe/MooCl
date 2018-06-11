<template lang="html">
  <div style="background-color : #EFF2FB;">
    <v-card :id="moviename" class="transparent noselect cloudFont" flat></v-card>
    <v-dialog  :max-width="800" v-model="recommendDialog">
      <MovieRecommend v-if="recommendView"></MovieRecommend>
    </v-dialog>
  </div>

</template>

<script>
import MovieRecommend from "./MovieRecommend.vue"
import HasNoRecommend from "./HasNoRecommend.vue"

export default {
  name: "WordCloud",
  props: ['moviename', 'movieid'],
  components : {
    MovieRecommend
  },
  data: function() {
    return {
      recommendView: false,
      recommendDialog : false,
      blackList : ["영화", "관람객", "있음", "없음", "같음","다름", "것같음", "때", "다만", "그렇음", "이렇음", "많음", "적음","하", "아","넘","텐데","큼", "말","정도","급"
                        ,"볼","감", "영화중", "어", "다", "편", "어떻음", "안", "진짜", "전", "글", "수없음", "애", "강", "열", "아", "\n", "설", "봉", "엔", "면", "예", "섯"
                        , "볼때", "존", "표", "이영화", "옆", "게", "밑", "거같음", "덕", "후", "부", "오", "루", "태", "김태", "김", "날", "속", "레", "에", "인", "일", "녀"
                        , "점대", "드", "크ㄹ도", "잔", "래", "쿠", "훌"],
      cloudData : [],
      wordList: []
    }
  },
  created () {
    this.$eventBus.$on('reloadNormalInfo', (movieTag) => {
      this.recommendDialog = false;
      this.setWordList(movieTag.movieId)
      this.setCloudData();
    })
  },
  updated () {
    var word_array = this.cloudData
    var select_id = "#" + this.moviename
    $(select_id).jQCloud('update',word_array)
  },
  methods : {
    replaceAll : function(str, searchStr, replaceStr){
      return str.split(searchStr).join(replaceStr);
    },
    setWordList : function(movieId) {
      let wordSets = this.$store.getters.getWordCloudList;
      let length = wordSets.length
      let temp_cloud = {}
      for(var i=0; i<length; i++){
        if(movieId == wordSets[i].movieId){
          temp_cloud = wordSets[i].words;
        }
      }
      this.wordList= temp_cloud;
    },
    setCloudData: function() {
      let tempArray = this.wordList;
      let setType = typeof this.wordList[0];
      let movieKey = this.movieid;
      let store = this.$store;
      let data = this.$data;
      let returnArray = [];


      if(setType == "string"){
        let length = tempArray.length
        for(let i=0; i<length; i++){
          let tempObject = JSON.parse(tempArray[i]);
          if(this.blackList.indexOf(tempObject.term) == -1){
            let cloudData = {
              text : tempObject.term,
              weight : tempObject.count,
              handlers : {
                mouseover : function(){
                  //키워드 추가 제거
                },
                click : function () {
                  //영화 추천 함수
                  let keyValue = {
                    movieId: movieKey,
                    word : this.innerHTML
                  }
                  store.dispatch('GETRECOMMENDATION', keyValue)
                  .then((response) => {
                    data.recommendView = true;
                    data.recommendDialog = true;
                  })
                }
              }
            }
            returnArray.push(cloudData);
          }
        }
      } else {
        let length = tempArray.length
        for(let i=0; i<length; i++){
          let value = Math.pow(tempArray[i].count, 2)
          if(this.blackList.indexOf(tempArray[i].term) == -1){
            let cloudData = {
              text : tempArray[i].term,
              weight : value,
              handlers : {
                mouseover : function(){
                  //키워드 추가 제거
                },
                click : function () {
                  //영화 추천 함수
                  let keyValue = {
                    movieId: movieKey,
                    word : this.innerHTML
                  }
                  store.dispatch('GETRECOMMENDATION', keyValue)
                  .then((response) => {
                    data.recommendView = true;
                    data.recommendDialog = true;
                  })
                }
              }
            }
            returnArray.push(cloudData);
          }
        }
      }
       this.cloudData = returnArray;
    }
  },
  watch : {
    recommendDialog : {
        handler : function () {
          if(this.recommendDialog == false){
            this.recommendView = false;
          }
        }
    }
  },
  computed: {


  },
  beforeMount() {
    this.setWordList(this.movieid)
    this.setCloudData();
  },
  mounted() {
    var word_array = this.cloudData
    var select_id = "#" + this.moviename
    $(function() {
      // When DOM is ready, select the container element and call the jQCloud method, passing the array of words as the first argument.
      $(select_id).jQCloud(word_array, {
        height: 200,
        width : 550,
        fontSize : {
          from : 0.1,
          to : 0.03
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
