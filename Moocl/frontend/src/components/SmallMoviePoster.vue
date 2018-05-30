<template lang="html">
      <v-card class="ma-0 ml-3" @mouseenter="showButton" @mouseleave="hideButton" depressed width="168" height="240" :img ="checkedPosterUrl">
        <v-card-media>
          <v-flex xs10></v-flex>   <!-- 하트를 오른쪽 보내기 위한 테그 -->
          <v-flex xs3>
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
  props: ['posterUrl', 'movieid'],
  created () {
    this.checkFavList();
  },
  beforeUpdate () {
    this.checkFavList();
  },
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
          let favMovieIndex = this.$store.state.favMovieList.indexOf(this.movieid);
          //좋아요 추가 요청
          if(favMovieIndex == -1){
            let movieId = this.movieid;
            let userId = sessionStorage.userNo;
            this.$axios.post('/api/addfavmovie', {movieId, userId})
            .then(() => {
              this.$store.state.favMovieList.push(movieId);
              this.heart = true;
            })
            .catch((error) => console.log(error));
          } else if( favMovieIndex != -1) {
          // 좋아요 삭제 요청
          //좋아요 목록에 있을 떄
          let movieId = this.movieid;
          let userId = sessionStorage.userNo;
          this.$axios.post('/api/delfavmovie', {movieId, userId})
          .then(() => {
            //this.$store.state.favMovieList.splice(favMovieIndex, 1);
            this.$eventBus.$emit('delInPage', movieId, favMovieIndex);
            this.heart = false;
          })
        }
      } else {
        //로그인 안함
        alert("로그인이 필요합니다.");
      }
    },
    checkFavList : function() {
      let movieChecker = this.$store.state.favMovieList.indexOf(this.movieid);
      if (movieChecker != -1 ){
        this.heart =  true;
      } else {
        this.heart = false;
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
  }
}
</script>

<style lang="css">
</style>
