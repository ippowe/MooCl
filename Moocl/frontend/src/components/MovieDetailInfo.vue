<template lang="html">
      <!-- 영화 제목에 따른 알맞은 정보(포스터, 제목, 평점, 감독, 배우들, 워드 클라우드, 개봉일, 관객수, 좋아요 목록 추가 여부) 불러오기 -->
      <div style="background-color : #EFF2FB;">
        <v-layout>
          <v-flex  style ="float: right;" class="ma-3" xs2> <!-- 영화 정보 관련 flex-->
            <SmallMoviePoster :posterUrl="detailinfo.poster"></SmallMoviePoster>
            <v-card  color ="transparent" flat class="ml-1" align-content-center>
              <v-card-title class="subheading pt-0 px-3">
                <table style="margin:auto;">
                  <tr>
                    <th style="font-size: 15px;">{{ movieTitle }}</th>
                  </tr>
                  <tr>
                    <td>{{ detailinfo.genre == null ? "장르" : detailinfo.genre}}</td>
                  </tr>
                  <tr>
                    <td>{{ detailinfo.running_time == null ? "러닝타임" : detailinfo.running_time + "분"}}</td>
                  </tr>
                  <tr>
                    <td>{{ detailinfo.nation == null ? "국적" : detailinfo.nation}}</td>
                  </tr>
                  <tr>
                    <td>{{ openDate }} </td>
                  </tr>
                  <!-- <tr>
                    <td class="text-xs-center"> 100,000,000명</td>
                  </tr> -->
                </table>
              </v-card-title>
            </v-card>
          </v-flex>
          <v-flex xs6>  <!-- 영화 평점/ 리뷰 클라우드-->
            <ScoreBySite :sitescore="detailinfo.score"></ScoreBySite>
            <!-- 상영중 여부에 따라서 보여주기 -->
            <!-- <div>
              <table class="ml-2">
                <tr>
                  <td><strong>예매율: 00%</strong></td>
                  <td><strong>&emsp;개봉일: 2018.03.08</strong></td>
                  <td><strong>&emsp;관객수: 100,000,000</strong></td>
                </tr>
              </table>
            </div> -->
            <WordCloud :moviename="detailinfo.inte_title" :movieid="detailinfo._id"></WordCloud>
          </v-flex>
          <v-flex style="background-color : #EFF2FB;"> <!--나이/ 연령별 평점  -->
            <ScoreByClass :classscore="classScore" style="background-color : #EFF2FB;"></ScoreByClass>
          </v-flex>
        </v-layout>
        <PeopleList class="ma-0" :infoswitch="openSwitch" :row="personRow" :slicedperson="slicedPersonList" :detailinfo="detailinfo"></PeopleList>
        <v-layout justify-center>
          <ReviewList :detailinfo="detailinfo"></ReviewList>
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
    props: ['detailinfo'],
    data : function() {
      return {
        detailInfo : [],
        personRow : 0,
        slicedPersonList: [],
        openSwitch: []
      }
    },
    created () {
      let personList = this.detailinfo.person;
      if( personList == null ) {
        this.personRow = 0;
        this.slicedPersonList = []
      } else {
        this.row();
        this.slicedList();
        this.switchSetting();
      }
    },
    methods : {
      parseDate : function(str) {
        let y = str.substr(0, 4);
        let m = str.substr(4, 2);
        let d = str.substr(6, 2);
        return new Date(y,m-1,d);
      },
      row () {
        let temp_row = 0;
        temp_row = parseInt(((this.detailinfo.person.length - 1) / 5)) + 1
        this.personRow = temp_row
      },
      slicedList () {
        var temp_personList = [];
        var slice_list =[];
        for(var i = 1; i<this.personRow+1; i++){
          slice_list = this.detailinfo.person.slice((i-1)*5, (i*5))
          temp_personList.push(slice_list)
        }
        this.slicedPersonList = temp_personList
      },
      switchSetting () {
        let temp_switch = [];
        let temp_inner_switch;
        for(let i=1; i<this.personRow +1; i++){
          temp_inner_switch = []
          for(let j=0; j<this.slicedPersonList[i-1].length; j++){
            temp_inner_switch.push(false);
          }
          temp_switch.push(temp_inner_switch);
        }
        this.openSwitch = temp_switch;
      },
    },
    computed : {
      openDate : function() {
        let temp_date
        if(this.detailinfo.open_date != null) {
          let parsingDate = this.parseDate(this.detailinfo.open_date);
          temp_date = parsingDate.getFullYear() + "." + parsingDate.getMonth() + "." + parsingDate.getDate();
          return temp_date
        } else {
          return "개봉일";
        }
      },
      classScore : function(){
        let temp_gender;
        let temp_age;
        let temp_array = [];

        temp_gender = this.detailinfo.gender_ratio;
        temp_age = this.detailinfo.age;

        temp_array.push(temp_gender);
        temp_array.push(temp_age);

        return temp_array;
      },

      movieTitle : function() {
        let temp_title = this.detailinfo.movie_title;
        let str = ""
        if(temp_title != null) {
          if(temp_title.length > 8){
            str = temp_title.substring(0,9) + "\n" + temp_title.substring(9);
            return str
          } else {
            return temp_title;
          }
        } else {
          return "영화제목"
        }
      }
    }
  }
</script>

<style lang="css">
</style>
