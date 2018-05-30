<template lang="html">
  <v-container class="mb-5 ml-4">
    <v-layout v-for="i in slicedfavMovieInfoList.length" :key="i" justify-start class="mb-5 ml-5">
      <MovieInfo class="mx-3" v-for="k in slicedfavMovieInfoList[i-1].length" :key="k" :movietag="slicedfavMovieInfoList[i-1][k-1]" ></MovieInfo>
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
      this.favMovieInfoList = this.$store.getters.getMyPageData.movieInfoList;
      this.favMovieList =  this.$store.getters.getFavMovieList;
      this.row();
      this.slicedMovieList();
    }.bind(this), 500);

    this.$eventBus.$on('delInPage', (movieId, favMovieIndex) => {
      let length = this.favMovieInfoList.length
      for(var i=0; i<length; i++){
        if(movieId == this.favMovieInfoList[i].movieId){
          this.$store.getters.getFavMovieList.splice(favMovieIndex, 1);
          this.favMovieInfoList.splice(i, 1);
          let temp_row = parseInt(i-1 / 5);
          let temp_index = i % 5;
          this.slicedfavMovieInfoList[temp_row].splice(temp_index, 1);
        }
      }
    })
  },
  data () {
    return{
      favRow: 0,
      favMovieInfoList: [],
      favMovieList: [],
      slicedfavMovieInfoList: [],
    }
  },
  methods : {
    row () {
      var temp_row = 0;
      temp_row =  parseInt((this.favMovieInfoList.length-1)/5);
      temp_row = temp_row + 1;
      this.favRow = temp_row
    },
    slicedMovieList () {
      var temp_movieList = [];
      var slice_list = [];
      for(var i =1; i<this.favRow+1; i++){
        slice_list = this.favMovieInfoList.slice((i-1)*5, (i*5))
        temp_movieList.push(slice_list)
      }
      this.slicedfavMovieInfoList =  temp_movieList;
    },
  }
}
</script>

<style lang="css">
</style>
