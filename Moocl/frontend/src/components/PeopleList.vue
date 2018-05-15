<template lang="html">
  <v-stepper v-model="stepNo" class="elevation-0" style="height: 250px">
    <v-stepper-items style="height: 250px">
      <v-stepper-content v-for="n in row" :key="n" :step="n" class="pa-0" style="height: 250px">
        <v-container text-xs-center fill-height class="pa-0" style="height: 250px">
          <v-layout wrap align-center align-content-center>
            <v-spacer></v-spacer>
            <v-btn :ripple="false" flat icon depressed @click="prevstep" ><v-icon>skip_previous</v-icon></v-btn>
            <v-spacer></v-spacer>
            <v-flex v-for="(item, index) in slicedPersonList[n-1]" :key ="index">
              <PersonCard :person="item" @openPersonInfo="openPersonInfo(n, index, item)"></PersonCard>
              <PersonInfo :person="item" v-if="openSwitch[n-1][index]" :dialog="openSwitch[n-1][index]"></PersonInfo>
            </v-flex>
            <v-spacer></v-spacer>
            <v-btn :ripple="false" flat icon depressed @click="nextstep"> <v-icon>skip_next</v-icon></v-btn>
            <v-spacer></v-spacer>
          </v-layout>
        </v-container>
      </v-stepper-content>
    </v-stepper-items>
  </v-stepper>

</template>

<script>
import PersonInfo from "./PersonInfo.vue"
import PersonCard from "./PersonCard.vue"

export default {
  name: "PeopleList",
  components : {
    PersonInfo,
    PersonCard
  },
  props :['detailinfo', 'relatedmovie'],
  data () {
      return {
        stepNo: 1,
        relatedMovie: [],
      }
    },
  methods : {
    nextstep : function() {
      if (this.stepNo != this.row){
        this.stepNo = ++this.stepNo;
      } else {
        this.stepNo = 1;
      }
    },
    prevstep : function() {
      if(this.stepNo != 1){
        this.stepNo = --this.stepNo;
      } else {
        this.stepNo = this.row;
      }
    },
    openPersonInfo : function(n, index, item) {
      console.log(this.openSwitch);
      this.openSwitch[n-1][index] = true;
      console.log(item);

    }
  },
  computed : {
    row () {
      let temp_row = 0;
      temp_row = parseInt(((this.detailinfo.person.length - 1) / 5)) + 1
      return temp_row
    },
    slicedPersonList () {
      var temp_personList = [];
      var slice_list =[];
      for(var i = 1; i<this.row+1; i++){
        slice_list = this.detailinfo.person.slice((i-1)*5, (i*5))
        temp_personList.push(slice_list)
      }
      return temp_personList
    },
    openSwitch () {
      let temp_switch = [];
      let temp_inner_switch;
      for(let i=1; i<this.row+1; i++){
        temp_inner_switch = []
        for(let j=0; j<this.slicedPersonList[i-1].length; j++){
          temp_inner_switch.push(false);
        }
        temp_switch.push(temp_inner_switch);
      }
      return temp_switch;
    }
  }
}
</script>

<style lang="css">
</style>
