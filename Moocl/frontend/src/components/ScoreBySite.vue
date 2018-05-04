<template lang="html">
  <v-flex>
    <!-- 영화 사이트에 따른 평점 불러오기 -->
    <v-list  class="transparent">
      <v-list-tile avatar>
        <v-list-tile-action>
            <v-avatar slot="activator" size="40"><img src="../assets/naver_icon.png"></v-avatar>
        </v-list-tile-action>
        <v-list-tile-content>
          <v-btn block disabled flat>
            <v-progress-linear color="light-green accent-4" :value="naverScore" height="20"></v-progress-linear>
          </v-btn>
        </v-list-tile-content>
      </v-list-tile>
      <v-list-tile avatar>
        <v-list-tile-action>
          <v-avatar size="40"><img src="../assets/daum_icon.png"></v-avatar>
        </v-list-tile-action>
        <v-list-tile-content>
          <v-btn block flat disabled>
            <v-progress-linear color="yellow accent-3" :value="daumScore" height="20"></v-progress-linear>
          </v-btn>
        </v-list-tile-content>
      </v-list-tile>
      <v-list-tile avatar>
        <v-list-tile-action>
          <v-avatar size="40"><img src="../assets/cgv_icon.png"></v-avatar>
        </v-list-tile-action>
        <v-list-tile-content>
          <v-btn block disabled flat>
            <v-progress-linear color="red accent-3" :value="cgvScore" height="20"></v-progress-linear>
          </v-btn>
        </v-list-tile-content>
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
      naverScore : 0,
      daumScore : 0,
      cgvScore : 0
    }
  },
  mounted () {
    console.log("mounte: ");
    console.log(this.sitescore);
    for(let i=0; i<this.sitescore.length ; i++){
      let siteName = this.sitescore[i].site;
      let temp_score = this.sitescore[i].grade;

      if( siteName == "naver"){
        this.naverScore = temp_score * 10;
      } else if (siteName == "daum"){
        this.daumScore = temp_score * 10;
      } else {
        this.cgvScore = temp_score * 10;
      }
    }
    console.log("결과")
    console.log(this.naverScore);
    console.log(this.daumScore);
    console.log(this.cgvScore);
  }
}
</script>

<style lang="css">
</style>
