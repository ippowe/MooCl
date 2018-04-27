<template lang="html">
  <v-dialog max-width="800" v-model="dialog">
    <PersonCard :name="name == undefined ? '영화제목' : name" slot="activator"></PersonCard>
    <v-layout class="white"> <!-- 인물 사진 및 기본 정보 -->

      <v-flex class="ma-2 ml-3" xs3>   <!-- 인물 사진 -->
        <v-card class="ma-0" depressed width="130" height="190" flat>
          <v-card-media height ="139"  :src="url"  @mouseenter="showButton" @mouseleave="hideButton">
            <v-flex xs8></v-flex>   <!-- 하트를 오른쪽 보내기 위한 테그 -->
            <v-flex xs5  class="pa-0 ma-0">
              <!--버튼 눌렀을때 favorite 목록에 추가되는 기능 필요  -->
              <v-btn :ripple="false" v-show="showFB" flat icon small @click="favorite = !favorite" depressed>
                  <v-icon color="red accent-3">{{favorite ? "favorite" : "favorite_border"}}</v-icon>
              </v-btn>
            </v-flex>
          </v-card-media>
          <v-card-title class="pa-0 ma-0">
              <p class="ma-0 pa-0 subheading"><strong>{{ name }}</strong></p>
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
        <WordCloud :id="moviename" :moviename="name == undefined ? '영화제목' : name"></WordCloud>
      </v-flex>
    </v-layout>

    <v-layout> <!-- 영화 목록 부분-->
      <MovieForPerson :personName="name"></MovieForPerson>
    </v-layout>
  </v-dialog>
</template>

<script>
import PersonCard from "./PersonCard.vue"
import WordCloud from "./WordCloud.vue"
import MovieForPerson from "./MovieForPerson.vue"

export default {
  components: {
    PersonCard,
    WordCloud,
    MovieForPerson
  },
  props: ['moviename'],
  mounted () {
      this.$eventBus.$on('sendInfo', (temp_role, temp_name, resizeUrl) => {
        this.name = temp_name;
        this.role = temp_role;
        this.url = resizeUrl;
      });
  },
  name: "SmallModal",
  data : function() {
    return {
      dialog: false,
      name: "default",
      role: "default",
      url: "default",
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
