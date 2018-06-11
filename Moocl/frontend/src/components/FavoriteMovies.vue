<template lang="html">
  <v-container class="mb-5 ml-4">
    <v-layout v-for="i in slicedfavMovieInfoList.length" :key="i" justify-start class="mb-5 ml-5">
      <BigMoviePoster class="mb-5 mx-3 pt-4" :movietitle="trimTitle(i, l)" @openMovieInfo="getCloudData(i, l)":posterUrl="slicedfavMovieInfoList[i-1][l-1].posterUrl"
        v-for="l in slicedfavMovieInfoList[i-1].length" :key="l" :movieid="slicedfavMovieInfoList[i-1][l-1].movieId"></BigMoviePoster>
      <MovieInfo v-if="openInfoSwitch[i-1][k-1]" :dialog="dialog" v-for="k in slicedfavMovieInfoList[i-1].length" :key="k"
                :movietag="slicedfavMovieInfoList[i-1][k-1]" @closeMovieInfo="closeInfo(i, k)"></MovieInfo>
    </v-layout>
  </v-container>
</template>

<script>
import MovieInfo from"./MovieInfo.vue"
import BigMoviePoster from "./BigMoviePoster.vue"

export default {
  name : "FavoriteMovies",
  components : {
    MovieInfo,
    BigMoviePoster
  },
  created () {
    this.getFavData();

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
      dialog: false,
      favRow: 0,
      favMovieInfoList: [],
      favMovieList: [],
      slicedfavMovieInfoList: [],
      openInfoSwitch: [[false, false, false, false, false], [false, false, false, false, false]]
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
    getFavData() {
      this.$store.dispatch("GETFAVLIST", sessionStorage.userNo)
      .then((result) => {
        this.FavMovieList = this.$store.getters.getFavMovieList;
      })
      this.$store.dispatch("MYPAGEDATA", sessionStorage.userNo)
      .then((result) => {
          this.favMovieInfoList = result.movieInfoList;
          this.row();
          this.slicedMovieList();
      }).catch((err) => console.log(err));
    },
    openInfo(i, l) {
      this.dialog = true;
      this.openInfoSwitch[i-1][l-1] = true;
    },
    trimTitle (i, l) {
      if(this.slicedfavMovieInfoList[i-1][l-1].movieTitle.length > 5){
        let temp_title = this.slicedfavMovieInfoList[i-1][l-1].movieTitle.slice(0,6) + "...";
        return temp_title
      } else {
        return this.slicedfavMovieInfoList[i-1][l-1].movieTitle
      }
    },
    closeInfo(i, k){
      this.dialog = false;
      this.openInfoSwitch[i-1][k-1] = false;
    },
    getCloudData (i, k) {
      let movieId = this.slicedfavMovieInfoList[i-1][k-1].movieId;
      this.$store.dispatch('GETCLOUDDATA', movieId)
      .then((result) => {
        this.openInfo(i, k);
      })
      .catch((error) => console.log(error));
    }
  }
}
</script>

<style lang="css">
</style>
