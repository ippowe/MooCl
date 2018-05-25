<template lang="html">
  <v-container class="mb-5 ml-4">
    <v-layout v-for="i in slicedMovieList.length" :key="i" justify-start class="mb-5 ml-5">
      <MovieInfo class="mx-3" v-for="moviename in slicedMovieList[i-1]" :key="moviename" :moviename="moviename" ></MovieInfo>
    </v-layout>
  </v-container>
</template>

<script>
import MovieInfo from"./MovieInfo.vue"

export default {
  name : "FavoriteMovies",
  components : {
    MovieInfo
  },
  data () {
    return{
      movienames : ["어벤져스", "아이언맨", "1987", "기억의_밤", "영화제목_1", "영화제목_2", "영화제목_3","영화제목_4"] //띄어쓰기 하면 Cloud못불러옴
    }
  },
  computed : {
    row () {
      var temp_row = 0;
      temp_row =  parseInt((this.movienames.length-1)/5);
      temp_row = temp_row + 1;
      return temp_row
    },
    slicedMovieList () {
      var temp_movieList = [];
      var slice_list = [];
      for(var i =1; i<this.row+1; i++){
        slice_list = this.movienames.slice((i-1)*5, (i*5))
        temp_movieList.push(slice_list)
      }
      return temp_movieList;
    }
  }
}
</script>

<style lang="css">
</style>
