<template lang="html">
      <!-- 영화 제목에 따른 알맞은 정보(포스터, 제목, 평점, 감독, 배우들, 워드 클라우드, 개봉일, 관객수, 좋아요 목록 추가 여부) 불러오기 -->
      <div>
        <v-layout align-content-center class="white">
          <v-flex  class="ma-3" xs2> <!-- 영화 정보 관련 flex-->
            <SmallMoviePoster :posterUrl="movietag.posterUrl"></SmallMoviePoster>
            <v-card  color ="transparent" flat>
              <v-card-title class="subheading pt-0">
                <table>
                  <tr>
                    <th class="subheading"><strong style="font-size: 15px">{{ movietag.movieTitle }}</strong></th>
                  </tr>
                  <tr>
                    <td class="text-xs-center">스릴러</td>
                  </tr>
                  <tr>
                    <td class="text-xs-center">150분</td>
                  </tr>
                  <tr>
                    <td class="text-xs-center">한국</td>
                  </tr>
                  <tr>
                    <td class="text-xs-center">2018.04.20 </td>
                  </tr>
                  <tr>
                    <td class="text-xs-center"> 100,000,000명</td>
                  </tr>
                </table>
              </v-card-title>
            </v-card>
          </v-flex>
          <v-flex xs6>  <!-- 영화 평점/ 리뷰 클라우드-->
            <ScoreBySite></ScoreBySite>
            <!-- 상영중 여부에 따라서 보여주기 -->
            <div>
              <table class="ml-2">
                <tr>
                  <td><strong>예매율: 00%</strong></td>
                  <td><strong>&emsp;개봉일: 2018.03.08</strong></td>
                  <td><strong>&emsp;관객수: 100,000,000</strong></td>
                </tr>
              </table>
            </div>
            <WordCloud :moviename="movietag.movieId"></WordCloud>
          </v-flex>
          <v-flex> <!--나이/ 연령별 평점  -->
            <ScoreByClass></ScoreByClass>
          </v-flex>
        </v-layout>
        <PeopleList class="ma-0"></PeopleList>
        <v-layout justify-center class="white">
          <ReviewList></ReviewList>
        </v-layout>
      </div>

  </template>

<script>
  import SmallMoviePoster from "./SmallMoviePoster.vue"
  import ScoreBySite from "./ScoreBySite.vue"
  import WordCloud from "./WordCloud.vue"
  import ScoreByClass from "./ScoreByClass.vue"
  import PeopleList from "./PeopleList.vue"
  import ReviewList from "./ReviewList.vue"

  export default {
    name:"MovieDetailInfo",
    components:{
      SmallMoviePoster,
      ScoreBySite,
      WordCloud,
      ScoreByClass,
      PeopleList,
      ReviewList
    },
    props: ['movietag'],
    mounted () {
      console.log(this.movietag);
      this.$axios.get("/api/detailinfo", {
        params : {
          movieId : this.movietag.movieId
        }
      })
      .then((result) => {
        console.log(result)
      })
      .catch((error) => console.log(error))
    },
    data : function() {
      return {
      }
    },
  }
</script>

<style lang="css">
</style>
