<template lang="html">
  <v-flex>
    <!-- 영화 사이트에 따른 평점 불러오기 -->
    <v-list  class="transparent">
      <v-tooltip right color="light-green accent-4">
        <v-list-tile avatar slot="activator">
          <v-list-tile-action>
              <v-avatar size="40"><img src="../assets/naver_icon.png"></v-avatar>
          </v-list-tile-action>
          <v-list-tile-content>
            <v-btn block disabled flat>
              <v-progress-linear color="light-green accent-4" :value="naverScore * 10" height="20"></v-progress-linear>
            </v-btn>
          </v-list-tile-content>
        </v-list-tile>
        <span class="black--text" style="font-weight: bold"><v-icon>star</v-icon> {{ naverScore }}</span>
      </v-tooltip>
      <v-tooltip  right color="yellow accent-3">
        <v-list-tile avatar slot="activator">
          <v-list-tile-action>
            <v-avatar size="40"><img src="../assets/daum_icon.png"></v-avatar>
          </v-list-tile-action>
          <v-list-tile-content>
            <v-btn block flat disabled>
              <v-progress-linear color="yellow accent-3" :value="daumScore * 10" height="20"></v-progress-linear>
            </v-btn>
          </v-list-tile-content>
        </v-list-tile>
        <span class="black--text" style="font-weight: bold"><v-icon>star</v-icon> {{ daumScore }}</span>
      </v-tooltip>
      <v-tooltip  right color="red accent-3">
        <v-list-tile avatar slot="activator">
          <v-list-tile-action>
            <v-avatar size="40"><img src="../assets/cgv_icon.png"></v-avatar>
          </v-list-tile-action>
          <v-list-tile-content>
            <v-btn block disabled flat>
              <v-progress-linear color="red accent-3" :value="cgvScore * 10" height="20"></v-progress-linear>
            </v-btn>
          </v-list-tile-content>
        </v-list-tile>
        <span class="black--text" style="font-weight: bold"><v-icon>star</v-icon> {{ cgvScore }}</span>
      </v-tooltip>
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
</style>
