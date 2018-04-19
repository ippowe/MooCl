<template lang="html">
  <!--마우스 위치감지해서 하트 v-show  -->
  <v-card depressed width="280" height="400" img ="https://dhgywazgeek0d.cloudfront.net/watcha/image/upload/c_fill,h_700,q_80,w_490/v1521689621/i3utanr7qh41xtggl2mu.jpg">
    <v-card-media>
      <v-flex xs10></v-flex>
      <v-flex xs2>
        <v-btn flat icon small @click="el = !el" depressed >
            <v-icon color="red accent-3">{{el ? "favorite" : "favorite_border"}}</v-icon>
        </v-btn>
      </v-flex>
    </v-card-media>
  </v-card>
</template>

<script>
export default {
  data : function() {
    return{
      el: false
    }
  }
}
</script>

<style lang="css">
</style>
