<template lang="html">
  <v-dialog max-width="800" v-model="dialog">
    <PersonCard :person="person == undefined ? '기본이름' : person" :roleview="roleview" slot="activator"></PersonCard>
    <v-layout class="white"> <!-- 인물 사진 및 기본 정보 -->

      <v-flex class="ma-2 ml-3" xs3>   <!-- 인물 사진 -->
        <v-card class="ma-0" depressed width="130" height="190" flat>
          <v-card-media height ="139"  :src="person.people_img"  @mouseenter="showButton" @mouseleave="hideButton">
            <v-flex xs8></v-flex>   <!-- 하트를 오른쪽 보내기 위한 테그 -->
            <v-flex xs5  class="pa-0 ma-0">
              <!--버튼 눌렀을때 favorite 목록에 추가되는 기능 필요  -->
              <v-btn :ripple="false" v-show="showFB" flat icon small @click="favorite = !favorite" depressed>
                  <v-icon color="red accent-3">{{favorite ? "favorite" : "favorite_border"}}</v-icon>
              </v-btn>
            </v-flex>
          </v-card-media>

          <v-card-title class="pa-0 ma-0">
              <p class="ma-0 pa-0 body-2"><strong>{{ person.name }}</strong></p>
          </v-card-title>

          <v-tooltip top color="white">   <!-- 인물 평균 평점-->
              <span slot="activator"><v-progress-linear value="80" height="10"></v-progress-linear></span>
              <span class= "text-xs-center">
                <v-icon color="red">star</v-icon>
                <strong class="black--text body-1">8.3</strong>
              </span>
          </v-tooltip>
        </v-card>

      </v-flex> <!-- 리뷰 클라우드 -->
      <v-flex class="mr-3 mb-3">
        <WordCloud :moviename="person._id == undefined ? '영화제목' : person._id"></WordCloud>
      </v-flex>
    </v-layout>

    <v-layout> <!-- 영화 목록 부분-->
      <RelatedMovie :personid="person._id"></RelatedMovie>
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
  props: ['person'],
  data : function() {
    return {
      dialog: false,
      showFB : false,
      favorite : false
    }
  },
  methods : {
    showButton : function(e) {
      this.showFB =true;
    },
    hideButton : function(e) {
      this.showFB = false;
    }
  }
}
</script>

<style lang="css">
</style>
