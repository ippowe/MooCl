<template>
    <!-- 로그인후에 영화 차트순으로 정렬해서 포스터 보여주기 -->
    <v-container class="mb-5 ml-5 px-5">
      <!-- <MovieChart style="position: absolute" class="ml-5" ></MovieChart> -->
      <v-layout v-for="i in mainMovies.length" :key="i" justify-left class="mb-3 mx-5 pl-4">
        <MovieInfo class="mb-5 mx-3" v-for="k in mainMovies[i-1].length" :key="k" :movietag="mainMovies[i-1][k-1]"></MovieInfo>
      </v-layout>
    </v-container>

</template>

<script>
import MovieChart from "./MovieChart.vue"
import MovieInfo from"./MovieInfo.vue"
import MovieDetailInfo from "./MovieDetailInfo.vue"

export default {
  name: "MainPage",
  components:{
    MovieChart,
    MovieInfo,
    MovieDetailInfo
  },
  created () {
    this.$eventBus.$emit('MainPage');
    this.searching();
    if(sessionStorage.userNo != undefined) {
      this.$store.dispatch("GETINFOLIST", sessionStorage.userNo)
    }
  },
  beforeMount () {
    this.$eventBus.$on("SearchMovie", (keyword) => {
      this.modifiedKeyword = keyword;
      this.searching();
    })
  },
  data : function() {
    return{
      movieTagList : [],
      mainRow: 0,
      mainMovies: [],
      modifiedKeyword: ""
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
      if(this.$router.currentRoute.params['keyword'] == undefined){
        if(this.modifiedKeyword == ""){
          temp_keyword = "";
        } else {
          temp_keyword = this.modifiedKeyword;
        }
      } else {
          temp_keyword = this.$router.currentRoute.params['keyword'];
      }
      this.$axios.get('/api/search', {
        params : {
          keyword : temp_keyword
        }
      })
      .then((result) => {
        this.movieTagList = result.data;
        this.row();
        this.slicedMovieList();
      })
      .catch((error) => {
        console.log(error)
      })
    }
  },

}
</script>

<style lang="css">
</style>
