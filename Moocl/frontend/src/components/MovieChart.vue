<template lang="html">
  <v-card height="638">
    <!-- 영화진흥원에서 가져온 데이터를 주기적으로 적용시키기 -->
    <v-navigation-drawer class="pa-0 deep-purple lighten-2" floating permanent>
      <v-toolbar flat class="deep-purple transparent" height="70">
        <v-list class="pa-0">
            <v-list-tile-content class="pa-0">
              <v-list-tile-title class="text-lg-center display-1" style="height:35px; font-weight: bold;"> 영화 차트 </v-list-tile-title>
            </v-list-tile-content>
          </v-list-tile>
        </v-list>
      </v-toolbar>
      <v-list class="pt-0 deep-purple lighten-2">
        <v-list-tile avatar @click='' v-for="n in 10" :key='`3${i}`' class="text-lg-center ">
          <v-flex xs2>
              <v-tab :ripple="false" class="white--text transparent" style="font-size: 20px; font-weight: bold;" >{{n}}</v-tab>
          </v-flex>
          <v-flex xs10>
            <v-list-tile-action>
              <v-list-tile-title class="title mt-1 ml-1" style="font-weight: bold;">영화 제목</v-list-tile-title>
            </v-list-tile-action>
          </v-flex>
        </v-list-tile>
      </v-list>
    </v-navigation-drawer>
  </v-card>
</template>

<script>
export default {
  name: 'MovieChart',
  data: function() {
    return {
    }
  }
}
</script>

<style lang="css">
</style>
