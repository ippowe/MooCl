<template lang="html">
  <v-menu open-on-hover attach bottom offset-y transition="scale-transition" origin="center center" :close-on-content-click="false">
    <v-card hover :ripple="true" slot="activator" width="280" height="400" img ="https://dhgywazgeek0d.cloudfront.net/watcha/image/upload/c_fill,h_700,q_80,w_490/v1521689621/i3utanr7qh41xtggl2mu.jpg"></v-card>
    <v-card-media class="transparent">
      <v-container fulid class="pa-0 ma-0">
        <v-flex class="pa-0" row>
          <v-toolbar color="black">
            <v-avatar size="35" class="mx-3">
              <img src="../assets/전체관람가.jpg">
            </v-avatar>
            <v-spacer></v-spacer>
            <v-tab :ripple="false" class="white--text  pa-0" style="font-size: 20px; font-weight: bold;">영화제목5자</v-tab>
            <v-spacer></v-spacer>
            <v-avatar class="mr-1">
              <v-btn flat icon @click="el = !el">
                  <v-icon color="red accent-3">{{el ? "favorite" : "favorite_border"}}</v-icon>
              </v-btn>
            </v-avatar>
          </v-toolbar>
        </v-flex>
      </v-container>
    </v-card-media>
  </v-menu>
</template>

<script>
export default {
  name: 'MovieCard',
  data: function() {
    return {
      el: false
    }
  }
}
</script>

<style lang="css">
</style>
