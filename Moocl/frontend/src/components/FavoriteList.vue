<template lang="html">
  <v-container>
    <v-layout class="my-0">
      <v-flex class="ml-5 pl-5">
        <SmallModal style="float : left" v-for="moviename in movienames" :key="moviename" :moviename="moviename" class="my-4 mx-3"></SmallModal>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import SmallModal from"./SmallModal.vue"
export default {
  name : "FavoriteList",
  components : {
    SmallModal
  },
  data () {
    return{
      movienames : ["어벤져스", "아이언맨", "1987", "기억의_밤", "영화제목_1", "영화제목_2", "영화제목_3","영화제목_4"] //띄어쓰기 하면 Cloud못불러옴
    }
  }
}
</script>

<style lang="css">
</style>
