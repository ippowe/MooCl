<template lang="html">
  <v-dialog :max-width="800" v-model="RecommendView">
    <v-btn class="primary" slot="activator">openDialog</v-btn>
    <v-card class="elevation-0 ma-0">
      <v-card-title class="headline pb-2 pl-5 ml-2" style="background-color : #EFF2FB;">
        추천 영화
      </v-card-title>
    </v-card>
    <v-layout class= "white">
      <v-flex>
        <v-stepper v-model="goodNo" class="pb-3 elevation-0">
          <v-stepper-items >
            <v-stepper-content v-for="n in length" :key="n" :step="n" class="pa-0">
              <v-container text-xs-center fill-height class="pa-0">
                <v-layout wrap align-center align-content-center>
                  <v-spacer></v-spacer>
                  <v-btn :ripple="false" flat icon depressed @click="prevstep(true)" ><v-icon>keyboard_arrow_left</v-icon></v-btn>
                  <v-spacer></v-spacer>
                  <v-flex v-for="i in 4" :key ="i">
                    <SmallMoviePoster></SmallMoviePoster>
                  </v-flex>
                  <v-btn :ripple="false" flat icon depressed @click="nextstep(true)"> <v-icon>keyboard_arrow_right</v-icon></v-btn>
                  <v-spacer></v-spacer>
                </v-layout>
              </v-container>
            </v-stepper-content>
          </v-stepper-items>
        </v-stepper>
      </v-flex>
    </v-layout>
    <v-card align-start class="elevation-0 ma-0" style="background-color : #EFF2FB;">
      <v-card-title class="headline pb-2 pl-5 ml-2">
        비추천 영화
      </v-card-title>
    </v-card>
    <v-layout class="white">
      <v-flex style="background-color : #EFF2FB;">
        <v-stepper v-model="badNo" class="pb-3 elevation-0" >
          <v-stepper-items >
            <v-stepper-content v-for="n in length" :key="n" :step="n" class="pa-0">
              <v-container text-xs-center fill-height class="pa-0">
                <v-layout wrap align-center align-content-center>
                  <v-spacer></v-spacer>
                  <v-btn :ripple="false" flat icon depressed @click="prevstep(false)" ><v-icon>keyboard_arrow_left</v-icon></v-btn>
                  <v-spacer></v-spacer>
                  <v-flex v-for="i in 4" :key ="i">
                    <SmallMoviePoster></SmallMoviePoster>
                  </v-flex>
                  <v-btn :ripple="false" flat icon depressed @click="nextstep(false)"> <v-icon>keyboard_arrow_right</v-icon></v-btn>
                  <v-spacer></v-spacer>
                </v-layout>
              </v-container>
            </v-stepper-content>
          </v-stepper-items>
        </v-stepper>
      </v-flex>
    </v-layout>
  </v-dialog>


</template>

<script>
import SmallMoviePoster from "./SmallMoviePoster.vue"

export default {
  name: "MovieRecommend",
  components: {
    SmallMoviePoster
  },
  data () {
      return {
        goodNo: 1,  // 추천 영화 페이지 번호
        badNo: 1,  // 비추천 영화 페이지 번호
        length: 5, // 영화 총 갯수 = 4 * length
        dialog: false
      }
    },
  methods : {
    nextstep : function(value) {
      if(value == true){
        if (this.goodNo < this.length){
          this.goodNo = ++this.goodNo;
        } else {
          this.goodNo = 1;
        }
      } else {
        if (this.badNo < this.length){
          this.badNo = ++this.badNo;
        } else {
          this.badNo = 1;
        }
      }

    },
    prevstep : function(value) {
      if(value == true) {
        if(this.goodNo > 1){
          this.goodNo = --this.goodNo;
        } else {
          this.goodNo = this.length;
        }
      } else {
        if(this.badNo > 1){
          this.badNo = --this.badNo;
        } else {
          this.badNo = this.length;
        }
      }


    }
  }
}
</script>

<style lang="css">
</style>
