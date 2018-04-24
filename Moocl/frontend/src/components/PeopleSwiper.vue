<template lang="html">
  <!-- 탭으로 바꿔보기 -->
  <v-stepper v-model="e1" class="pb-3">
    <v-stepper-items >
      <v-stepper-content v-for="n in 3" :key="n" :step="n" class="pa-0">
        <v-container text-xs-center fill-height class="pa-0">
          <v-layout wrap align-center align-content-center>
            <v-spacer></v-spacer>
            <v-btn :ripple="false" flat icon depressed ><v-icon>skip_previous</v-icon></v-btn>
            <v-spacer></v-spacer>
            <v-flex v-for="i in 5" :key ="i">
              <PersonCard></PersonCard>
            </v-flex>
            <v-btn :ripple="false" flat icon depressed> <v-icon>skip_next</v-icon></v-btn>
            <v-spacer></v-spacer>
          </v-layout>
        </v-container>
      </v-stepper-content>
    </v-stepper-items>
  </v-stepper>

</template>

<script>
import PersonCard from "./PersonCard.vue"

export default {
  components : {
    PersonCard
  },
  data () {
      return {
        e1: 1
      }
    }
}
</script>

<style lang="css">
</style>
