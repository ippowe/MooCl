<template>
    <!-- 로그인후에 영화 차트순으로 정렬해서 포스터 보여주기 -->
    <v-container class="mb-5 ml-5 px-5 " color= "indigo lighten-5">
      <!-- <MovieChart style="position: absolute" class="ml-5" ></MovieChart> -->
      <v-layout v-for="i in mainMovies.length" :key="i" justify-left class="mb-3 mx-5 pl-4">
        <BigMoviePoster class="mb-5 mx-3 pt-4" :movietitle="trimTitle(i, l)" @openMovieInfo="getCloudData(i, l)":posterUrl="mainMovies[i-1][l-1].posterUrl"
          v-for="l in mainMovies[i-1].length" :key="l" :movieid="mainMovies[i-1][l-1].movieId"></BigMoviePoster>
        <MovieInfo v-if="openInfoSwitch[i-1][k-1]" :dialog="dialog" v-for="k in mainMovies[i-1].length" :key="k"
                  :movietag="mainMovies[i-1][k-1]" @closeMovieInfo="closeInfo(i, k)"></MovieInfo>
      </v-layout>
    </v-container>

</template>

<script>
import MovieChart from "./MovieChart.vue"
import MovieInfo from"./MovieInfo.vue"
import BigMoviePoster from "./BigMoviePoster.vue"

export default {
  name: "MainPage",
  components:{
    MovieChart,
    MovieInfo,
    BigMoviePoster
  },
  created () {
    this.$store.state.movie.normalInfoList = [];
    this.$eventBus.$emit('MainPage');
    this.searching();

    if(sessionStorage.userNo != undefined) {
      this.$store.dispatch("GETFAVLIST", sessionStorage.userNo)
    }
  },
  beforeMount () {
      this.$eventBus.$on("SearchMovie", (keyword) => {
      this.modifiedKeyword = keyword;
      this.searching();
    })
  },
  watch : {
    dialog : {
      handler : function() {
        this.setPageView(this.movieTagList);
      },
      deep : true
    }
  },
  data : function() {
    return{
      movieTagList : [],
      mainRow: 0,
      mainMovies: [],
      modifiedKeyword: "",
      dialog:false,
      openInfoSwitch: [[false, false, false, false, false], [false, false, false, false, false]]
    }
  },
  methods : {
    row () {
      var temp_row = 0;
      temp_row =  parseInt((this.movieTagList.length-1)/5);
      temp_row = temp_row + 1;
      this.mainRow = temp_row
    },
    slicedMovieList () {
      var temp_movieList = [];
      var slice_list = [];
      for(var i =1; i<this.mainRow+1; i++){
        slice_list = this.movieTagList.slice((i-1)*5, (i*5))
        temp_movieList.push(slice_list)
      }
      this.mainMovies = temp_movieList;
    },
    searching () {
      let temp_keyword = "";
      if(this.$router.currentRoute.params['keyword'] == ""){
        if(this.modifiedKeyword == ""){
          temp_keyword = "";
        } else {
          temp_keyword = this.modifiedKeyword;
        }
      } else {
          temp_keyword = this.$router.currentRoute.params['keyword'];
      }

      this.$store.dispatch('GETMOVIEINFO', temp_keyword)
      .then((result) => {
        let normalInfoList = result;
        this.setPageView(normalInfoList);
      })
      .catch((error) => console.log(error))

    },
    setPageView (normalInfoList) {
      this.movieTagList = normalInfoList;
      this.row();
      this.slicedMovieList();
    },
    openInfo(i, l) {
      this.dialog = true;
      this.openInfoSwitch[i-1][l-1] = true;
    },
    trimTitle (i, l) {
      if(this.mainMovies[i-1][l-1].movieTitle.length > 5){
        let temp_title = this.mainMovies[i-1][l-1].movieTitle.slice(0,6) + "...";
        return temp_title
      } else {
        return this.mainMovies[i-1][l-1].movieTitle
      }
    },
    closeInfo(i, k){
      this.dialog = false;
      this.openInfoSwitch[i-1][k-1] = false;
    },
    getCloudData (i, k) {
      let movieId = this.mainMovies[i-1][k-1].movieId;
      this.$store.dispatch('GETCLOUDDATA', movieId)
      .then((result) => {
        this.openInfo(i, k);
      })
      .catch((error) => console.log(error));
    }
  },


}
</script>

<style lang="css">
</style>
