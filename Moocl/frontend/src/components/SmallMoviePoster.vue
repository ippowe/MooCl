<template lang="html">
      <v-card class="ma-0 ml-3" @mouseenter="showButton" @mouseleave="hideButton" depressed width="168" height="240" :img ="posterUrl">
        <v-card-media>
          <v-flex xs10></v-flex>   <!-- 하트를 오른쪽 보내기 위한 테그 -->
          <v-flex xs3>
            <!--버튼 눌렀을때 favorite 목록에 추가되는 기능 필요  -->
            <v-btn :ripple="false" v-show="showFB" flat icon small @click="putMovieList" depressed>
                <v-icon color="red accent-3">{{heart ? "favorite" : "favorite_border"}}</v-icon>
            </v-btn>
          </v-flex>
        </v-card-media>
      </v-card>
</template>

<script>
export default {
  name: "SmallMoviePoster",
  props: ['posterUrl'],
  data : function() {
    return{
      heart: false,
      showFB: false,
    }
  },
  methods : {
    showButton : function(e) {
      this.showFB =true;
    },
    hideButton : function(e) {
      this.showFB = false;
    },
    putMovieList : function () {
      if(sessionStorage.token){
        //로그인 함
        this. heart = !this.heart
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
