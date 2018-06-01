<template lang="html">
  <v-dialog max-width="800" v-model="dialog">
    <v-layout class="white"> <!-- 인물 사진 및 기본 정보 -->
      <v-flex class="ma-2 ml-3 text-xs-left" xs3>   <!-- 인물 사진 -->
        <v-card class="ma-0" depressed width="150" flat>
          <v-card-media height ="139"  :src="person.people_img"  @mouseenter="showButton" @mouseleave="hideButton">
            <v-flex xs8></v-flex>   <!-- 하트를 오른쪽 보내기 위한 테그 -->
            <v-flex xs5  class="pa-0 ma-0">
              <!--버튼 눌렀을때 favorite 목록에 추가되는 기능 필요  -->
              <v-btn :ripple="false" v-show="showFB" flat icon small @click.stop="putPersonList" depressed>
                  <v-icon color="red accent-3">{{heart ? "favorite" : "favorite_border"}}</v-icon>
              </v-btn>
            </v-flex>
          </v-card-media>

          <v-card-title class="pa-0 ma-0 text-xs-left">
            <p class="ma-0 pa-0"><strong>{{ person.person_name }}</strong></p>
          </v-card-title
          <v-card-title class="pa-0 ma-0 text-xs-left" v-if=" person.part != '' "  >
            <p class="ma-0 pa-0">{{ person.role }}</p>
          </v-card-title>
          <v-card-title class="pa-0 ma-0 text-xs-left" v-if=" person.role != '' ">
            <p class="ma-0 pa-0">{{ person.part }}</p>
          </v-card-title>

          <v-tooltip top color="white">   <!-- 인물 평균 평점-->
              <span slot="activator"><v-progress-linear :value="avgScore * 10" height="10"></v-progress-linear></span>
              <span class= "text-xs-center">
                <v-icon color="red">star</v-icon>
                <strong class="black--text body-1">{{ avgScore }}</strong>
              </span>
          </v-tooltip>
        </v-card>

      </v-flex> <!-- 리뷰 클라우드 -->
      <v-flex class="mr-3 mb-3">
        <WordCloud :moviename="person.person_id"></WordCloud>
      </v-flex>
    </v-layout>

    <v-layout> <!-- 영화 목록 부분-->
      <RelatedMovie :movieinfo="relatedmovie" @getAvgGrade="getAvgGrade"></RelatedMovie>
    </v-layout>
  </v-dialog>
</template>

<script>
import PersonCard from "./PersonCard.vue"
import WordCloud from "./WordCloud.vue"
import RelatedMovie from "./RelatedMovie.vue"

export default {
  name: "PersonInfo",
  components: {
    PersonCard,
    WordCloud,
    RelatedMovie
  },
  created () {
    this.checkFavList();

  },
  watch : {
    dialog : function() {
      if(this.dialog){
        this.$emit('closePersonInfo');
      } else {
        this.dialog = true;
      }
    }
  },
  props: ['person', 'relatedmovie','dialog'],
  data : function() {
    return {
      showFB: false,
      favorite: false,
      avgScore : 0,
      heart: false
    }
  },
  methods : {
    showButton : function(e) {
      this.showFB =true;
    },
    hideButton : function(e) {
      this.showFB = false;
    },
    getAvgGrade : function() {
      let temp_avg_garde = this.calAvgScore(this.getAllGrades());
      if(temp_avg_garde == undefined){
          this.avgScore = 0;
      } else {
        this.avgScore = temp_avg_garde;
      }
    },
    getAllGrades : function() {
      let temp_movieScore_list = [];
      for(var i=0; i<this.relatedmovie.length; i++){
        for(var j=0; j<this.relatedmovie[i].score.length; j++){
          let temp_grade = this.relatedmovie[i].score[j].grade;
          if(temp_grade != 0){
            temp_movieScore_list.push(temp_grade);
          }
        }
      }
      return temp_movieScore_list;
    },
    calAvgScore : function(grade_list) {
      let temp_grade_sum = 0;
      let temp_avg_grade = 0;
      for(var i=0; i<grade_list.length; i++){
        temp_grade_sum = temp_grade_sum + grade_list[i]
      }
      temp_avg_grade = temp_grade_sum / grade_list.length;
      temp_avg_grade = temp_avg_grade.toFixed(1);
      return temp_avg_grade
    },
    putPersonList : function () {
      if(sessionStorage.token){
        //로그인 함
          //좋아요 추가 요청
          let favPersonIndex = this.$store.getters.getFavPersonList.indexOf(this.person.person_id.toString());
          if (favPersonIndex == -1){
            let personId = this.person.person_id;
            let userId = sessionStorage.userNo;
            this.$axios.post('/api/addfavpeople', {personId, userId})
            .then(() => {
              this.$store.getters.getFavPersonList.push(personId.toString());
              this.heart = true;
            })
          } else if(favPersonIndex != -1 ){
          // 좋아요 삭제 요청
            //좋아요 목록에 있을 떄
            let personId = this.person.person_id;
            let userId = sessionStorage.userNo;
            this.$axios.post('/api/delfavpeople', {personId, userId})
            .then(() => {
              this.$store.getters.getFavPersonList.splice(favPersonIndex, 1);
              this.heart = false;
            })
            .catch((error) => console.log(error))
        }
      } else {
        //로그인 안함
        alert("로그인이 필요합니다.");
      }
    },
    checkFavList : function () {
      let personChecker = this.$store.getters.getFavPersonList.indexOf(this.person.person_id.toString());
      if (personChecker != -1){
        this.heart =  true;
      } else {
        this.heart = false;
      }
    }
  }
}
</script>

<style lang="css">
</style>
