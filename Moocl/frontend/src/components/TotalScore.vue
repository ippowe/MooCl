<template lang="html">
  <v-flex>
    <!-- 영화 사이트에 따른 평점 불러오기 -->
    <v-list  class="transparent">
      <v-list-tile avatar>
        <v-list-tile-action>
          <v-avatar size="40"><img src="../assets/naver_icon.png"></v-avatar>
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
            <v-progress-linear color="light-blue accent-4" :value="daumScore" height="20"></v-progress-linear>
          </v-btn>
        </v-list-tile-content>
      </v-list-tile>
      <v-list-tile avatar>
        <v-list-tile-action>
          <v-avatar size="40"><img src="../assets/cgv_icon.png"></v-avatar>
        </v-list-tile-action>
        <v-list-tile-content>
          <v-btn block disabled flat>
            <v-progress-linear color="yellow accent-4" :value="cgvScore" height="20"></v-progress-linear>
          </v-btn>
        </v-list-tile-content>
      </v-list-tile>
    </v-list>
  </v-flex>
</template>

<script>
export default {
  name: "TotalScore",
  data: function() {
    return{
      naverScore : 50,
      watchaScore : 40,
      daumScore : 70,
      cgvScore : 20
    }
  }
}
</script>

<style lang="css">
</style>
