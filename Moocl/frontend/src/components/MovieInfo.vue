<template lang="html">
  <!-- 영화 제목에 따른 알맞은 정보(포스터, 제목, 평점, 감독, 배우들, 워드 클라우드, 개봉일, 관객수, 좋아요 목록 추가 여부) 불러오기 -->
  <div style="background-color : #EFF2FB;">
    <v-dialog :max-width="width ? 1200 : 800" v-model="dialog">
      <NormalInfo v-if="!detail" :movietag="movietag" v-on:viewdetail="changeInfo"></NormalInfo>
      <MovieDetailInfo class="pt-3" v-if="detail" :detailinfo="detailInfo"></MovieDetailInfo>
    </v-dialog>
  </div>

</template>

<script>
import BigMoviePoster from "./BigMoviePoster.vue"
import NormalInfo from "./NormalInfo.vue"
import MovieDetailInfo from "./MovieDetailInfo.vue"

export default {
  name: "MovieInfo",
  props: ['movietag', 'dialog'],
  components:{
    BigMoviePoster,
    NormalInfo,
    MovieDetailInfo
  },
  beforeMount() {
    this.getDetailInfo();
    this.getReviewCount();
  },
  watch : {
    dialog: function (val) {
      this.$emit('closeMovieInfo')
      this.detail = false;
      this.width = false;
    },
  },
  data : function() {
    return {
      detail: false,
      width: false,
      detailInfo: [],
    }
  },
  methods : {
    changeInfo () {
      this.detail = true;
      this.width = true;
    },
    viewNormal () {
      this.detail = false;
      this.width = false;
    },
    getDetailInfo () {
      let movieId = this.movietag.movieId;

      this.$store.dispatch("GETDETAILINFO", movieId)
      .then((result) => {
        this.detailInfo = result;
      }).catch((err) => console.log(err))
    },
    getReviewCount () {
      let movieId = this.movietag.movieId;

      this.$store.dispatch('GETREVIEWCOUNT', movieId)
      .catch((error) => console.log(error));
    },
  },
  computed : {
  }

}
</script>

<style lang="css">
</style>
