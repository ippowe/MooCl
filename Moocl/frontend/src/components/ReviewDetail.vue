<template lang="html">
  <v-layout class="white pa-2" fill-height align-center>
    <table class="my-3 py-3" >
      <tr>
        <td class="title"><strong>{{ this.username }}님의 클라우드</strong></td>
      </tr>
      <tr>
        <td :id="reviewcontent.movie_id" class="noselect cloudFont"></td>
        <v-dialog  :max-width="800" v-model="recommendDialog">
          <MovieRecommend v-if="recommendMovieLength>0"></MovieRecommend>
          <HasNoRecommend v-else></HasNoRecommend>
        </v-dialog>
      </tr>
    </table>
    <v-flex text-xs-left class="my-3 py-5 white black--text subheading" xs7>
      <td class="reviewarea pl-3">{{ reviewcontent.review_contents }}</td>
    </v-flex>
  </v-layout>
</template>

<script>
import MovieRecommend from "./MovieRecommend.vue"
import HasNoRecommend from "./HasNoRecommend.vue"

export default {
  name: "ReviewDetail",
  components: {
    MovieRecommend,
    HasNoRecommend
  },
  props : ['reviewcontent', 'username'],
  data : function() {
    return {
      recommendView: false,
      recommendDialog : false,
      blackList : ["영화", "관람객", "있음", "없음", "같음","다름", "것같음", "때", "다만", "그렇음", "이렇음", "많음", "적음","하", "아","넘","텐데","큼", "말","정도","급"
                        ,"볼","감", "영화중", "어", "다", "편", "어떻음", "안", "진짜", "전", "글", "수없음", "애", "강", "열", "아", "\n", "설", "봉", "엔", "면", "예", "섯"
                        , "볼때", "존", "표", "이영화", "옆", "게", "밑", "거같음", "덕", "후", "부", "오", "루", "태", "김태", "김", "날", "속", "레", "에", "인", "일", "녀"
                        , "점대", "드", "크ㄹ도", "잔", "래", "쿠", "훌"],
      cloudData : [],
      wordList: [],
      recommendMovieLength: 0,
    }
  },
  beforeMount() {
    this.setCloudData();
  },
  mounted() {
    let word_array = this.cloudData;
    let select_id = "#" + this.reviewcontent.movie_id;
    $(function() {
      $(select_id).jQCloud(word_array, {
        height: 150,
        width : 300,
        fontSize : {
          from : 0.1,
          to : 0.03
        }
      });
    });
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
  methods : {
    setCloudData: function() {
      let tempArray = this.reviewcontent.gurumi_word;
      let data = this.$data;
      let movieKey = this.reviewcontent.movie_id;
      let store = this.$store;
      let returnArray = [];

      let getRecommend = function(keyword) {
        let keyValue = {
          movieId: movieKey,
          word: keyword
        }
        store.dispatch('GETRECOMMENDATION', keyValue)
        .then((response) => {
          data.recommendMovieLength = store.getters.getGoodMovies.length;
          data.recommendView = true;
          data.recommendDialog = true;
        })
      }

      let getRandomInt = function(){
        return Math.floor(Math.random() * 100);
      }

      let length = tempArray.length
      for(let i=0; i<length; i++){
        if(this.blackList.indexOf(tempArray[i]) == -1 ){
          let cloudData = {
            text : tempArray[i],
            weight : getRandomInt(),
            handlers : {
              mouseover : function() {

              },
              click : function() {
                let keyword = this.innerHTML
                getRecommend(keyword);
              }
            }
          }
          returnArray.push(cloudData);
        }
      }
      this.cloudData = returnArray;
    }
  }
}
</script>

<style lang="css">
.noselect { -webkit-touch-callout: none; -webkit-user-select: none; -khtml-user-select: none; -moz-user-select: none; -ms-user-select: none; user-select: none; cursor: pointer}
.reviewarea {border-left: 2px solid grey;}
.cloudFont { font-family: 'Binggrae' !important;}
</style>
