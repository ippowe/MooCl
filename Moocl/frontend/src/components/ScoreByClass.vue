<template lang="html">
<v-layout>
  <v-card height=200 flat class="transparent mt-4">
    <v-tooltip v-for="data in genderscore" :key="data.gender" bottom :color="data.color">
      <v-progress-circular slot="activator" :value="data.value" :color="data.color" size="120" :rotate="-90" :width="20" class="mx-3">
      </v-progress-circular>
      <span><v-icon>person</v-icon> {{data.value}}%</span>
    </v-tooltip>
    <v-card flat class="transparent py-0" height="200">
      <v-list class="transparent" v-for="inform in agescore" :key="inform.age * 10" style="height: 45px;">
        <v-tooltip right :color="inform.color">
        <v-list-tile slot="activator">
          <v-list-tile-action>
            <v-avatar size="40"><strong>{{inform.age}}대</strong></v-avatar>
          </v-list-tile-action>
            <v-progress-linear height="20" :value="inform.percent*1.5" :color="inform.color"></v-progress-linear>
        </v-list-tile>
        <span><v-icon>person</v-icon> {{inform.percent}}%</span>
      </v-tooltip>
      </v-list>
    </v-card>
    <v-tabs hide-slider :ripple="false" centered>
      <v-tab @click="naverData">
        <v-avatar size="35"><img src="../assets/naver_icon.png" alt=""></v-avatar>
      </v-tab>
      <v-tab @click="cgvData">
        <v-avatar size="50"><img src="../assets/cgv_icon.png" alt=""></v-avatar>
      </v-tab>
    </v-tabs>
  </v-card>

</v-layout>

</template>

<script>

export default {
  name: "ScoreByClass",
  props: ['classscore'],
  data : function(){
    return{
      siteName : "naver",
      genderscore : [],
      agescore : []
    }
  },
  methods : {
    naverData : function() {
      this.siteName = "naver";
      this.setGenderRatio();
      this.setAgeRatio();
    },
    cgvData : function() {
      this.siteName = "cgv";
      this.setGenderRatio();
      this.setAgeRatio();
    },
    setGenderRatio : function() {
      this.genderscore = [];
      let temp_male = {};
      let temp_female = {};
      for(let i = 0; i<this.classscore[0].length; i++){
        if(this.classscore[0][i].site == this.siteName){
          temp_male["gender"] = "male";
          temp_male["value"] = this.classscore[0][i].male;
          temp_male["color"] = "blue";

          temp_female["gender"] = "female";
          temp_female["color"] = "pink"
          if(temp_male["value"] != 0) {
            temp_female["value"] = 100 - this.classscore[0][i].male;
          } else {
            temp_female["value"] = 0;
          }

          this.genderscore.push(temp_male);
          this.genderscore.push(temp_female);
        }
      }
    },
    setAgeRatio : function() {
      this.agescore = [];
      let temp_teen = {};
      let temp_twenty = {};
      let temp_thirty = {};
      let temp_forty = {};


      for(let i =0; i<this.classscore[1].length; i++){
        if(this.classscore[1][i].site == this.siteName){
          temp_teen["age"] = 10;
          temp_teen["percent"] = this.classscore[1][i].teen;
          temp_teen["color"] = "green";

          temp_twenty["age"] = 20;
          temp_twenty["percent"] = this.classscore[1][i].twenty;
          temp_twenty["color"] = "yellow";

          temp_thirty["age"] = 30;
          temp_thirty["percent"] = this.classscore[1][i].thirty;
          temp_thirty["color"] = "blue";

          temp_forty["age"] = 40;
          temp_forty["percent"] = this.classscore[1][i].forty;
          temp_forty["color"] = "cyan";

          this.agescore = [temp_teen, temp_twenty, temp_thirty, temp_forty];
        }
      }
    }
  },
  mounted () {
    if(this.classscore[0].length < 2){

      this.classscore[0].push({
        "site" : "cgv",
        "male" : 0
      });

      this.classscore[1].push({
        "site" : "cgv",
        "teen" : 0,
        "twenty" : 0,
        "thirty" : 0,
        "forty" : 0
      });

      console.log(this.classscore);

      this.setGenderRatio();
      this.setAgeRatio();
    } else{
      this.setGenderRatio();
      this.setAgeRatio();
    }
  }
}
</script>

<style lang="css">
</style>
