<template>
  <v-app>
    <!-- 로그인후에 영화 차트순으로 정렬해서 포스터 보여주기 -->
    <v-container class="ml-0">
      <v-layout>
        <v-flex lg2>
          <MovieChart class="my-4"></MovieChart>
        </v-flex>
        <v-flex>
          <SmallModal v-for="moviename in movienames" :key="moviename" :moviename="moviename" class="my-4 mx-3"></SmallModal>
        </v-flex>
      </v-layout>
    </v-container>
  </v-app>

</template>

<script>
import MovieChart from "./MovieChart.vue"
import SmallModal from"./SmallModal.vue"

export default {
  components:{
    MovieChart,
    SmallModal
  },
  created () {
    this.$eventBus.$emit('MainPage');
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
