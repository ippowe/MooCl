<template lang="html">
<v-layout>
  <v-card height=200 flat class="transparent mt-4">
    <v-tabs hide-slider :ripple="false" right style="padding-bottom : 10px;">
      <v-tab @click="naverData">
        <v-avatar size="22" ><img src="../assets/naver_icon.png" alt=""></v-avatar>
      </v-tab>
      <v-tab @click="cgvData">
        <v-avatar size="42"><img src="../assets/cgv_icon.png" alt=""></v-avatar>
      </v-tab>
    </v-tabs>
    <v-tooltip v-for="data in genderscore" :key="data.gender" bottom :color="data.color" style="padding-left: 22px;">
      <v-progress-circular slot="activator" :value="data.value" :color="data.color" size="128" :rotate="-90" :width="18" class="mx-2">
      </v-progress-circular>
      <span><v-icon>person</v-icon> {{data.value}}%</span>
    </v-tooltip>
    <v-card flat class="transparent py-0" height="200">
      <v-list class="transparent" v-for="inform in agescore" :key="inform.age * 10" style="height: 34px;">
        <v-tooltip right :color="inform.color">
        <v-list-tile slot="activator">
          <v-list-tile-action>
            <v-avatar size="50"><p style="font-size: 14px; font-weight: bold; padding: 16px 0px 0px 16px;">{{inform.age}}대</p>
            </v-avatar>
          </v-list-tile-action>
            <v-progress-linear height="11" style="border-radius : 22px;" :value="inform.percent*1.5" :color="inform.color"></v-progress-linear>
        </v-list-tile>
        <span><v-icon>person</v-icon> {{inform.percent}}%</span>
      </v-tooltip>
      </v-list>
    </v-card>
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
      agescore : [],
      naverEmptGender : {
        "site" : "naver",
        "male" : 0
      },
      cgvEmptGender : {
        "site" : "cgv",
        "male" : 0
      },
      naverEmptAge: {
        "site" : "naver",
        "teen" : 0,
        "twenty" : 0,
        "thirty" : 0,
        "forty" : 0
      },
      cgvEmptAge: {
        "site" : "cgv",
        "teen" : 0,
        "twenty" : 0,
        "thirty" : 0,
        "forty" : 0
      }
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
          temp_female["color"] = "pink accent-2"
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
          temp_teen["color"] = "deep-purple lighten-2 ";

          temp_twenty["age"] = 20;
          temp_twenty["percent"] = this.classscore[1][i].twenty;
          temp_twenty["color"] = "pink lighten-4";

          temp_thirty["age"] = 30;
          temp_thirty["percent"] = this.classscore[1][i].thirty;
          temp_thirty["color"] = "teal lighten-3";

          temp_forty["age"] = 40;
          temp_forty["percent"] = this.classscore[1][i].forty;
          temp_forty["color"] = "amber lighten-1";

          this.agescore = [temp_teen, temp_twenty, temp_thirty, temp_forty];
        }
      }
    }
  },
  mounted () {
    for(var i=0; i<this.classscore.length; i++){
      let gender = [this.naverEmptGender, this.cgvEmptGender]
      let age = [this.naverEmptAge, this.cgvEmptAge]

      if(this.classscore[i] == null){
        this.classscore = [[],[]];
        this.classscore[0].push(gender[i]);
        this.classscore[1].push(age[i]);
      } else if( this.classscore[0].length < 2 ){
        this.classscore[0].push(this.cgvEmptGender);
        this.classscore[1].push(this.cgvEmptAge);


        this.setGenderRatio();
        this.setAgeRatio();
      } else {
        this.setGenderRatio();
        this.setAgeRatio();
      }
    }
  }
}
</script>

<style lang="css">


</style>
