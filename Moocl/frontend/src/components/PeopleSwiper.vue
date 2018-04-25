<template lang="html">
  <v-stepper v-model="stepNo" class="pb-3 elevation-0">
    <v-stepper-items >
      <v-stepper-content v-for="n in length" :key="n" :step="n" class="pa-0">
        <v-container text-xs-center fill-height class="pa-0">
          <v-layout wrap align-center align-content-center>
            <v-spacer></v-spacer>
            <v-btn :ripple="false" flat icon depressed @click="prevstep" ><v-icon>skip_previous</v-icon></v-btn>
            <v-spacer></v-spacer>
            <v-flex v-for="i in 5" :key ="i">
              <PersonCard></PersonCard>
            </v-flex>
            <v-btn :ripple="false" flat icon depressed @click="nextstep"> <v-icon>skip_next</v-icon></v-btn>
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
        stepNo: 1,
        length: 5,
      }
    },
  methods : {
    nextstep : function() {
      console.log(this.stepNo)
      if (this.stepNo != this.length){
        this.stepNo = ++this.stepNo;
      } else {
        this.stepNo = 1;
      }
    },
    prevstep : function() {
      console.log(this.stepNo)
      if(this.stepNo != 1){
        this.stepNo = --this.stepNo;
      } else {
        this.stepNo = this.length;
      }

    }
  }
}
</script>

<style lang="css">
</style>
