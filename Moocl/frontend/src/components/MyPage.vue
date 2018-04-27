<template lang="html">
  <v-app>
    <v-tabs centered hide-slider class="mt-4" color="transparent" >
      <v-tab :ripple="false" style="border-right: 3px solid grey" @click="favoriteList" >
        <v-icon color="red">favorite</v-icon>
      </v-tab>
      <v-tab :ripple="false" class="title"  @click="myReview">
        <strong>내 리뷰</strong>
      </v-tab>
    </v-tabs>

      <FavoriteList v-if="page =='FavoriteList'"></FavoriteList>
      <MyReview v-else-if="page == 'myReview'"></MyReview>

  </v-app>
</template>

<script>
import FavoriteList from "./FavoriteList.vue"
import MyReview from "./MyReview.vue"

export default {
  created () {
    this.$eventBus.$emit("MainPage")
  },
  name : "MyPage",
  components : {
    FavoriteList,
    MyReview
  },
  data : function() {
    return {
      page : "FavoriteList"
    }
  },
  methods : {
    favoriteList () {
      this.page = "FavoriteList"
    },
    myReview () {
      this.page = "myReview"
    }
  }
}
</script>

<style lang="css">
</style>
