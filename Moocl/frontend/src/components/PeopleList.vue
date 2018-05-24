  <template lang="html">
  <v-stepper v-model="stepNo" class="elevation-0" style="height: 250px">
    <v-stepper-items style="height: 250px">
      <v-stepper-content v-for="n in row" :key="n" :step="n" class="pa-0" style="height: 250px">
        <v-container text-xs-center fill-height class="pa-0" style="height: 250px">
          <v-layout wrap align-center align-content-center>
            <v-spacer></v-spacer>
            <v-btn :ripple="false" flat icon depressed @click="prevstep" ><v-icon>skip_previous</v-icon></v-btn>
            <v-spacer></v-spacer>
            <v-flex v-for="(item, index) in slicedperson[n-1]" :key ="index">
              <PersonCard :person="item" @openPersonInfo="openPersonInfo(n, index, item)"></PersonCard>
              <PersonInfo :person="item" :relatedmovie="relatedMovie" v-if="infoswitch[n-1][index]"
                          :dialog="dialog" @closePersonInfo="closePersonInfo(n, index)"></PersonInfo>
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
  props :['detailinfo', 'infoswitch', 'slicedperson', 'row'],
  data () {
      return {
        stepNo: 1,
        relatedMovie: [],
        dialog: false,
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
        this.infoswitch[n-1][index] = true;
        let sending_id = item.person_id;

        this.$axios.post("/api/removie", {
            personId : sending_id
          }).then((result) => {
          this.relatedMovie = result.data;
        }).catch((error) => console.log(error))
        this.dialog = true;
        this.reloadStepper();
    },
    closePersonInfo : function(n, index) {
      this.infoswitch[n-1][index] = false;
      this.dialog = false;
    },
    reloadStepper : function () {
      let current_step = this.stepNo
      let temp_step = this.stepNo-1;
      this.stepNo = temp_step;
      this.stepNo = current_step;
    }
  }
}
</script>

<style lang="css">
</style>
