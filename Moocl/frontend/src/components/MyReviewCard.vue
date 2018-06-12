<template lang="html">
  <v-flex class="pa-0 ma-0">
    <v-card width="400px" flat color="transparent">
      <v-layout align-start>
        <v-checkbox @click.native="selected" v-model="selection" style="width: 30px" class="mr-3" :ripple="false" > </v-checkbox>
        <table>
          <tr>
              <v-flex class="pa-1">
                <SmallMoviePoster :posterUrl="reviewInfo._id.movie_ref.poster" :movieid="reviewInfo.movie_id" ></SmallMoviePoster>
              </v-flex>
            <td style="vertical-align: top;">
              <v-flex class="pa-1">
                <v-card-text class="pa-0">
                  <v-card-title primary-title class="pa-0 title">
                    <strong>{{ reviewInfo._id.movie_ref.movie_title }}</strong>
                  </v-card-title>
                  <v-card-media class="py-2 subheading text-xs-left ">
                    {{ reviewInfo._id.movie_ref.genre }}
                  </v-card-media>
                    <p style="text-align: left;" class="py-2" >
                      {{ trimedReview }}
                      <span @click="openMyReviewDetail" style="cursor: pointer;" v-if=" reviewInfo.review_contents.length > 100">
                        <strong>더보기</strong>
                      </span>
                      <v-dialog v-model="myReviewDetail" max-width="800px" v-if=" reviewInfo.review_contents.length > 100">
                        <v-card class="pa-3">
                          <v-layout>
                            <SmallMoviePoster :posterUrl="reviewInfo._id.movie_ref.poster" :movieid="reviewInfo.movie_id"></SmallMoviePoster>
                            <div :id="reviewInfo.movie_id"></div>
                          </v-layout>
                          <v-card-text>{{ reviewInfo.review_contents }}</v-card-text>
                          <v-card-media>
                            <v-flex xs8></v-flex>
                            <v-flex xs4 class="pl-4">
                              <v-btn class="error"> 삭제 </v-btn>
                            </v-flex>
                          </v-card-media>
                        </v-card>
                      </v-dialog>
                    </p>
                </v-card-text>

              </v-flex>
            </td>
          </tr>
        </table>
      </v-layout>
    </v-card>
  </v-flex>

</template>

<script>
import SmallMoviePoster from "./SmallMoviePoster.vue"
import WordCloud from "./WordCloud.vue"
export default {
  name: "MyReviewCard",
  props: ['reviewInfo'],
  components: {
    SmallMoviePoster,
    WordCloud
  },
  data: function() {
    return {
      selection: [],
      myReviewDetail: false,
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
    let select_id = "#" + this.reviewInfo.movie_id;
    $(function() {
      $(select_id).jQCloud(word_array, {
        height: 300,
        width : 600,
        fontSize : {
          from : 0.01,
          to : 0.05
        },
        autoResize : true,
      });
    });
  },
  updated() {
    this.$eventBus.$on('reSelection', () => {
      this.selection = []
    })
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
  methods: {
    selected() {
      this.$eventBus.$emit('selected', this.reviewInfo)
    },
    openMyReviewDetail() {
      this.myReviewDetail = true;
    },
    setCloudData: function() {
      let tempArray = this.reviewInfo.gurumi_word;
      let data = this.$data;
      let movieKey = this.reviewInfo.movie_id;
      let store = this.$store;
      let returnArray = [];
      let length = 0;

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
      if(tempArray.length > 100){
        length = 100
      } else {
        length = tempArray.length
      }

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
  },
  computed: {
    trimedReview () {
      let temp_content = ""
      if(this.reviewInfo.review_contents.length > 100){
        temp_content = this.reviewInfo.review_contents.substring(0, 101);
      } else {
        temp_content = this.reviewInfo.review_contents
      }
      return temp_content
    }
  }
}
</script>

<style lang="css">
</style>
