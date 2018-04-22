<template lang="html">
      <v-card class="ma-0" @mouseenter="showButton" @mouseleave="hideButton" depressed width="196" height="280" img ="../assets/naver_icon.png">
        <v-card-media>
          <v-flex xs10></v-flex>   <!-- 하트를 오른쪽 보내기 위한 테그 -->
          <v-flex xs3>
            <v-btn :ripple="false" v-show="showFB" flat icon small @click="favorite = !favorite" depressed>
                <v-icon color="red accent-3">{{favorite ? "favorite" : "favorite_border"}}</v-icon>
            </v-btn>
          </v-flex>
        </v-card-media>
      </v-card>
</template>

<script>
export default {
  data : function() {
    return{
      favorite: false,
      showFB: false,
    }
  },
  methods : {
    showButton : function(e) {
      this.showFB =true;
    },
    hideButton : function(e) {
      this.showFB = false;
    }
  }
}
</script>

<style lang="css">
</style>
