<template>
  <v-app>
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
import MainContent from "./MainContent.vue"
import MovieChart from "./MovieChart.vue"
import MovieCard from"./MovieCard.vue"

export default {
  components:{
    MainContent,
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
