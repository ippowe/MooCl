<template lang="html">
 <v-container class="pa-0">
   <v-card class="ma-0" @mouseenter="showButton" @mouseleave="hideButton" depressed width="126" height="180" img ="https://dhgywazgeek0d.cloudfront.net/watcha/image/upload/c_fill,h_400,q_80,w_280/v1515038416/j1lacfu7iq7mrz2ti3xt.jpg">
     <v-card-media>
       <v-flex xs8></v-flex>   <!-- 하트를 오른쪽 보내기 위한 테그 -->
       <v-flex xs4>
         <!--버튼 눌렀을때 favorite 목록에 추가되는 기능 필요  -->
         <v-btn :ripple="false" v-show="showFB" flat icon small @click="favorite = !favorite" depressed>
             <v-icon color="red accent-3">{{favorite ? "favorite" : "favorite_border"}}</v-icon>
         </v-btn>
       </v-flex>
     </v-card-media>
   </v-card>
   <v-card width="126" flat class="pt-1">
     <v-card-title v-if="isActor" class="pa-0"> {{ role }}</v-card-title>
     <div v-for="score in scores">
       <v-tooltip bottom color="white">
         <v-progress-linear slot="activator" :color="score.color" :value="score.score * 10"></v-progress-linear>
         <span>
           <v-icon color="red">star</v-icon>
           <strong class="black--text body-1">{{score.score}}</strong>
         </span>
       </v-tooltip>
     </div>
   </v-card>
 </v-container>

</template>

<script>
export default {
  name : "XsMoviePoster",
  props: ['moviename'],
  data : function() {
    return{
      showFB : false,
      favorite : false,
      isActor : true,
      scores : {
        naver : {score: 8.3, color: "green"},
        daum : {score: 5.7, color: "yellow"},
        cgv :{score: 7.6, color: "red"}
      },
      role: "주연: 캡틴아메리카"
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
