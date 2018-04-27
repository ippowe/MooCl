<template lang="html">
      <v-card class="ma-0" @mouseenter="showButton" @mouseleave="hideButton" depressed width="130" height="190" flat>
        <v-card-media @click="emitMethod" height ="139"  :src="resizeUrl">
          <v-flex xs8></v-flex>   <!-- 하트를 오른쪽 보내기 위한 테그 -->
          <v-flex xs5  class="pa-0 ma-0">
            <!--버튼 눌렀을때 favorite 목록에 추가되는 기능 필요  -->
            <v-btn :ripple="false" v-show="showFB" flat icon small @click="favorite = !favorite" depressed>
                <v-icon color="red accent-3">{{favorite ? "favorite" : "favorite_border"}}</v-icon>
            </v-btn>
          </v-flex>
        </v-card-media>
        <v-card-title class="pa-0 ma-0">
            <p class="ma-0 pa-0"><strong>{{ name }}</strong></p>
            <p class="ma-0 pa-0">{{ role }}</p>
        </v-card-title>
      </v-card>
</template>

<script>
export default {
  data : function() {
    return{
      favorite: false,
      showFB: false,
      role: "주연: 캡틴아메리카",
      name: "스티븐 스필버그",
      url: this.resizeUrl
    }
  },
  computed : {
    resizeUrl : function() {
      var url = 'https://search.pstatic.net/common/?src=http%3A%2F%2Fimgmovie.naver.net%2Fmdi%2Fpi%2F000000000%2F00000000025-t.jpg&type=u77_96&quality=95';
      var size = '111_139';
      var tempUrl = url.replace(/77_96/, size);
      return tempUrl;
    }
  },
  methods : {
    showButton : function(e) {
      this.showFB =true;
    },
    hideButton : function(e) {
      this.showFB = false;
    },
    emitMethod : function () {
      console.log("emitMethod")
      this.$eventBus.$emit('sendInfo', this.role, this.name, this.resizeUrl);
    }
  }
}
</script>

<style lang="css">
</style>
