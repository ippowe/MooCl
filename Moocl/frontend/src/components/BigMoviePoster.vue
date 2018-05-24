<template lang="html">
  <!-- 하트 눌렀을 때 좋아요 목록에 추가 -->
  <!-- 포스터 클릭시 모달창 불러오기 -->
  <!-- 영화에 알 맞은 정보(포스터, 제목, 관람등급) 불러오기  -->
  <v-menu open-on-hover attach bottom offset-y transition="scale-transition" origin="center center" :close-on-content-click="false">
    <v-card class="ma-0" :ripple="true" slot="activator" width="280" height="400" :img ="checkedPosterUrl"></v-card>
    <v-card-media class="transparent">
      <v-container fulid class="pa-0 ma-0">
        <v-flex class="pa-0" row>
          <v-toolbar color="black" class="elevation-3">
            <v-avatar size="35" class="mx-3">
              <img src="../assets/전체관람가.png">
            </v-avatar>
            <v-spacer></v-spacer>
            <strong style="font-size: 20px; font-weight: bold; color: white; cursor: default">
              {{ movietitle }}
            </strong>
            <v-spacer></v-spacer>
            <v-avatar class="mr-1">
              <v-btn flat icon @click="putMovieList">
                  <v-icon color="red accent-3">{{heart ? "favorite" : "favorite_border"}}</v-icon>
              </v-btn>
            </v-avatar>
          </v-toolbar>
        </v-flex>
      </v-container>
    </v-card-media>
  </v-menu>
</template>

<script>
export default {
  props: ['movietitle', 'posterUrl', 'movieid'],
  name: 'MovieCard',
  data: function() {
    return {
      heart: false
    }
  },
  methods : {
    viewNormalInfo : function() {
      console.log("viewNormalInfo");
      this.$emit("viewNormalInfo");
    },
    putMovieList : function () {
      if(sessionStorage.token){
        //로그인 함
        this.heart = !this.heart
        if(this.heart) {
          //좋아요 추가 요청
          if(this.$state.favMovieList.indexOf(this.movieid) == -1){
            //좋아요 목록에 없을 떄
            console.log(this.movieid);
            console.log(sessionStorage.userNo);
          }
        } else {
          // 좋아요 삭제 요청
          if(this.$state.favMovieList.indexOf(this.movieid) != -1){
            //좋아요 목록에 있을 떄
            console.log(this.movieid);
            console.log(sessionStorage.userNo);
          }
        }
      } else {
        //로그인 안함
        alert("로그인이 필요합니다.");
      }
    }
  },
  computed : {
    checkedPosterUrl : function() {
      if(this.posterUrl == null || this.posterUrl == "https://ssl.pstatic.net/static/movie/2012/06/dft_img203x290.png") {
        return "http://dytk.co.kr/twb_images/no.jpg";   //로컬파일에서 이미지 가져올 수 있게 코딩 필요
      } else {
        return this.posterUrl;
      }
    },
    heart : function () {
      let movieChecker = this.$state.favMovieList.indexOf(this.movieid);
      if (movieChecker != -1 ){
        return true;
      } else {
        return false;
      }
    }
  }
}
</script>

<style lang="css">
</style>
