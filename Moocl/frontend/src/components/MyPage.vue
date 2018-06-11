<template lang="html">
  <v-container class="ml-5 py-0" justify-center>
    <v-toolbar tabs flat color="transparent">
      <v-tabs centered hide-slider color="transparent" >
        <v-tab :ripple="false" style="border-right: 7px dotted grey" @click="favoriteList" >
          <strong style="font-size : 32px">Like it</strong><v-icon color="red" class="pr-3">favorite</v-icon>
        </v-tab>
        <v-tab :ripple="false" class="title"  @click="myReview">
          <strong style="font-size : 32px; padding-left : 15px" >MY Review</strong>
        </v-tab>
      </v-tabs>
      <v-tabs slot="extension" v-if="page =='Favorite'" color="transparent" hide-slider centered style="padding-right:154px; padding-top : 25px">
        <v-tab :ripple="false" class="title" @click.native.stop="subpage = 'Movie'">
          <strong style="font-size : 18px">영화</strong>
        </v-tab>
        <v-tab :ripple="false" class="title"  @click.native.stop="subpage = 'People'">
          <strong style="font-size : 18px">인물</strong>
        </v-tab>
        <v-tab disabled></v-tab>  <!-- 위치 조절을 위한 탭  -->
      </v-tabs>
    </v-toolbar>


    <v-layout class="indigo lighten-5">
      <FavoriteMovies v-if="page=='Favorite' && subpage =='Movie'"></FavoriteMovies>
      <FavoritePeople v-if="page=='Favorite' && subpage =='People'"></FavoritePeople>
      <MyReviewPage style="padding-top : 30px" v-else-if="page == 'MyReviewPage'"></MyReviewPage>
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
    }
  },
  methods : {
    favoriteList () {
      this.page = "Favorite"
    },
    myReview () {
      this.page = "MyReviewPage"
    }
  }
}
</script>

<style lang="css">
</style>
