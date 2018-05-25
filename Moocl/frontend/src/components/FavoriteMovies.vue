<template lang="html">
  <v-container class="mb-5 ml-4">
    <v-layout v-for="i in slicedFavMovieList.length" :key="i" justify-start class="mb-5 ml-5">
      <MovieInfo class="mx-3" v-for="k in slicedFavMovieList[i-1].length" :key="k" :movietag="slicedFavMovieList[i-1][k-1]" ></MovieInfo>
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
  created () {
    setTimeout(function() {
      this.favMovieList = this.$store.state.myPageData.movieInfoList;
      this.row();
      this.slicedMovieList();
    }.bind(this), 500);
  },
  data () {
    return{
      favRow: 0,
      favMovieList: [],
      slicedFavMovieList: [],
    }
  },
  methods : {
    row () {
      var temp_row = 0;
      temp_row =  parseInt((this.favMovieList.length-1)/5);
      temp_row = temp_row + 1;
      this.favRow = temp_row
    },
    slicedMovieList () {
      var temp_movieList = [];
      var slice_list = [];
      for(var i =1; i<this.favRow+1; i++){
        slice_list = this.favMovieList.slice((i-1)*5, (i*5))
        temp_movieList.push(slice_list)
      }
      this.slicedFavMovieList =  temp_movieList;
    }
  }
}
</script>

<style lang="css">
</style>
