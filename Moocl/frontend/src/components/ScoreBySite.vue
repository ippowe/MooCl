<template lang="html">
  <v-flex>
    <!-- 영화 사이트에 따른 평점 불러오기 -->
    <v-list class="transparent">
        <v-list-tile avatar>
          <v-list-tile-action>
              <v-avatar :tile="true" class="pl-3 pt-4" size="48"><img src="../assets/naver_icon_2.png"></v-avatar>
          </v-list-tile-action>
          <v-list-tile-content>
            <v-btn block disabled flat class="pt-3">
              <v-progress-linear color="light-green accent-4" :value="naverScore * 10" height="20" style="border-radius :15px;">
              </v-progress-linear>
            </v-btn>
          </v-list-tile-content>
          <span class="light-green--text accent-4" style="font-weight: bold"><v-icon color= "red">star</v-icon> {{ naverScore }}</span>
        </v-list-tile>
        <v-list-tile avatar>
          <v-list-tile-action>
            <v-avatar :tile="true" class="pt-4 pl-3 daumlogo" size="42" width="20"><img src="../assets/daum_icon_2.png"></v-avatar>
          </v-list-tile-action>
          <v-list-tile-content>
            <v-btn block flat disabled class="pt-3">
              <v-progress-linear color="yellow accent-3" :value="daumScore * 10" height="20" style="border-radius :15px;">
              </v-progress-linear>
            </v-btn>
          </v-list-tile-content>
          <span class="yellow--text accent-3" style="font-weight: bold"><v-icon color= "red">star</v-icon> {{ daumScore }}</span>
        </v-list-tile>
        <v-list-tile avatar>
          <v-list-tile-action>
            <v-avatar :tile="true" class="pt-4" size="55"><img src="../assets/cgv_icon.png"></v-avatar>
          </v-list-tile-action>
          <v-list-tile-content>
            <v-btn block disabled flat class="pt-3">
              <v-progress-linear color="red darken-3" :value="cgvScore * 10" height="20" style="border-radius :15px;">
              </v-progress-linear>
            </v-btn>
          </v-list-tile-content>
            <span class="red--text darken-3" style="font-weight: bold;"><v-icon>star</v-icon color= "red"> {{ cgvScore }}</span>
        </v-list-tile>
    </v-list>
  </v-flex>
</template>

<script>
export default {
  name: "ScoreBySite",
  props: ["sitescore"],
  data: function() {
    return{
      naverScore : "0.0",
      daumScore : "0.0",
      cgvScore : "0.0"
    }
  },
  created () {
    for(let i=0; i<this.sitescore.length ; i++){
      let siteName = "";
      let temp_score = "0.0";

      siteName = this.sitescore[i].site;
      temp_score = this.sitescore[i].grade;

      if( siteName == "naver"){
        this.naverScore = temp_score;
      } else if (siteName == "daum"){
        this.daumScore = temp_score;
      } else {
        this.cgvScore = temp_score;
      }
    }
  }
}
</script>

<style lang="css">
.daumlogo{width : 100px; height: 3px;}
</style>
