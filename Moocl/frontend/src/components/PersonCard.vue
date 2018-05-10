<template lang="html">
      <v-card class="ma-0" @mouseenter="showButton" @mouseleave="hideButton" depressed width="130" height="190" flat>
        <v-card-media height ="139"  :src="resizeUrl">
          <v-flex xs8></v-flex>   <!-- 하트를 오른쪽 보내기 위한 테그 -->
          <v-flex xs5  class="pa-0 ma-0">
            <!--버튼 눌렀을때 favorite 목록에 추가되는 기능 필요  -->
            <v-btn :ripple="false" v-show="showFB" flat icon small @click="favorite = !favorite" depressed>
                <v-icon color="red accent-3">{{favorite ? "favorite" : "favorite_border"}}</v-icon>
            </v-btn>
          </v-flex>
        </v-card-media>
        <v-card-title class="pa-0 ma-0">
            <p class="ma-0 pa-0" :class="fontSize"><strong>{{ person.name }}</strong></p>
            <!-- 등장인물 정보 -->
            <p class="ma-0 pa-0" v-if="false">{{ role }}</p>
        </v-card-title>
      </v-card>
</template>

<script>
export default {
  name: "PersonCard",
  props: ['roleview', 'person'],
  data : function() {
    return{
      favorite: false,
      showFB: false,
    }
  },
  computed : {
    resizeUrl : function() {
      var url = this.person.people_img;
      var size = '111_139';
      var tempUrl = url.replace(/77_96/, size);
      return tempUrl;
    },
    // fontSize : function() {
    //   if(this.roleview){
    //     return 'body-1'
    //   } else {
    //     return 'subheading'
    //   }
    // }
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
