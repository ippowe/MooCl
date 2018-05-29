<template lang="html">
  <v-container class="ml-5 py-0" justify-center>
    <v-toolbar tabs flat color="transparent">
      <v-tabs centered hide-slider class="mt-4" color="transparent" >
        <v-tab :ripple="false" style="border-right: 3px solid grey" @click="favoriteList" >
          <v-icon color="red">favorite</v-icon>
        </v-tab>
        <v-tab :ripple="false" class="title"  @click="myReview">
          <strong>내 리뷰</strong>
        </v-tab>
      </v-tabs>
      <v-tabs slot="extension" centered v-if="page =='Favorite'" color="transparent" hide-slider>
        <v-tab :ripple="false" class="title" @click.native.stop="subpage = 'Movie'">
          <strong>영화</strong>
        </v-tab>
        <v-tab :ripple="false" class="title"  @click.native.stop="subpage = 'People'">
          <strong>인물</strong>
        </v-tab>
        <v-tab disabled></v-tab>  <!-- 위치 조절을 위한 탭  -->
      </v-tabs>
    </v-toolbar>


    <v-layout>
      <FavoriteMovies v-if="page=='Favorite' && subpage =='Movie'"></FavoriteMovies>
      <FavoritePeople v-if="page=='Favorite' && subpage =='People'"></FavoritePeople>
      <MyReviewPage v-else-if="page == 'MyReviewPage'"></MyReviewPage>
    </v-layout>

  </v-container>


</template>

<script>
import FavoriteMovies from "./FavoriteMovies.vue"
import FavoritePeople from "./FavoritePeople.vue"
import MyReviewPage from "./MyReviewPage.vue"

export default {
  created () {
    this.$eventBus.$emit("MainPage")
    this.getFavData();
    this.getMyReview();
  },
  name : "MyPage",
  components : {
    FavoriteMovies,
    FavoritePeople,
    MyReviewPage

  },
  data : function() {
    return {
      page : "Favorite",
      subpage : "Movie",
      movieInfoList : [],
    }
  },
  methods : {
    favoriteList () {
      this.page = "Favorite"
    },
    myReview () {
      this.page = "MyReviewPage"
    },
    getFavData () {
      this.$store.dispatch("MYPAGEDATA", sessionStorage.userNo)
      .catch((error) => console.log(error))
    },
    getMyReview() {
      this.$store.dispatch("MYREVIEWDATA", sessionStorage.email)
      .catch((erro) => console.log(error))
    }
  }
}
</script>

<style lang="css">
</style>
