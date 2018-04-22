<template lang="html">
  <v-layout>
    <v-btn color="primary" dark @click.stop="dialog = true">Open Dialog 1</v-btn>
    <v-dialog max-width="800px" max-height="450" v-model="dialog" color="white">
      <v-layout>
        <v-flex class="ma-2" xs3>
          <CardForModal></CardForModal>
        </v-flex>
        <v-flex xs8>
          <TotalScore></TotalScore>
          <v-card color ="transparent" flat>
            <v-card-title class="headline">
              개봉일  2018.04.20
              <v-spacer></v-spacer>
              관객수 100,000,000명
            </v-card-title>
          </v-card>
        </v-flex>
      </v-layout>
    </v-dialog>
  </v-layout>
</template>

<script>
import CardForModal from "./CardForModal.vue"
import TotalScore from "./TotalScore.vue"
export default {
  components:{
    CardForModal,
    TotalScore
  },
  name: "SmallModal",
  data : function() {
    return {
      dialog: false
    }
  }
}
</script>

<style lang="css">
</style>
