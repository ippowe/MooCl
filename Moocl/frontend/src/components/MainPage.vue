<template>
  <v-app>
    <!-- 로그인후에 영화 차트순으로 정렬해서 포스터 보여주기 -->
    <v-container fluid grid-list-md>
      <v-layout fill-height>
        <v-flex lg3>
          <MovieChart class="my-4"></MovieChart>
        </v-flex>
        <v-flex v-for="i in 5" :key="`3${i}`" xl4 >
          <MovieCard v-for="i in 2" :key="`3${i}`" class="py-4"></MovieCard>
        </v-flex>
      </v-layout>
    </v-container>
  </v-app>

</template>

<script>
import MovieChart from "./MovieChart.vue"
import MovieCard from"./MovieCard.vue"

export default {
  components:{
    MovieChart,
    MovieCard
  },
  created () {
    this.$eventBus.$emit('MainPage');
  },
  data () {
    return{

    }
  }
}
</script>

<style lang="css">
</style>
