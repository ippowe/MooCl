<template lang="html">
    <v-container class="mb-5 ml-4 px-5 mt-4" >
      <v-layout v-for="i in slicedFavPersonList.length" :key="i"  class=" mb-2 mx-5 px-5">
        <v-flex v-for="(item, index) in slicedFavPersonList[i-1]" :key ="index">
          <PersonCard :person="item" @openPersonInfo="openPersonInfo(i, index, item)"></PersonCard>
          <PersonInfo :person="item" :relatedmovie="relatedMovie" v-if="infoSwitch[i-1][index]"
                      :dialog="dialog" @closePersonInfo="closePersonInfo(i, index)"></PersonInfo>
        </v-flex>
          <!-- <PersonCard @openPersonInfo="openPersonInfo(i, index, item)" class="mx-5 mb-3 px-1" v-for="(item, index) in slicedFavPersonList[i-1]" :key="index" :person="item"></PersonCard>
          <PersonInfo :person="item" :relatedmovie="relatedMovie" v-if="infoSwitch[i-1][index]" :dialog="dialog" @closePersonInfo="closePersonInfo(i, index)"></PersonInfo> -->
      </v-layout>
    </v-container>
</template>

<script>
import PersonCard from "./PersonCard.vue"
import PersonInfo from "./PersonInfo.vue"

export default {
  name: "FavoritePeople",
  components : {
    PersonCard,
    PersonInfo
  },
  created () {
    setTimeout(function() {
      this.favPersonList = this.$store.getters.getMyPageData.personInfoList;
      this.row();
      this.slicedPersonList();
      this.openSwitch();
    }.bind(this), 500);

    this.$eventBus.$on('delInPage', (personId, favPersonIndex) => {
      let length = this.favPersonList.length
      for(var i=0; i<length; i++){
        if(personId == this.favPersonList[i].person_id){
          this.$store.getters.getFavPersonList.splice(favPersonIndex, 1);
          this.favPersonList.splice(i, 1);
          let temp_row = parseInt(i-1 / 6);
          let temp_index = i % 6;
          this.slicedFavPersonList[temp_row].splice(temp_index, 1);
        }
      }
    })
  },
  data : function() {
    return {
      favRow : 0,
      favPersonList: [],
      slicedFavPersonList: [],
      dialog: false,
      relatedMovie: [],
      infoSwitch : []
    }
  },
  methods : {
    row () {
      var temp_row = 0;
      temp_row = parseInt(((this.favPersonList.length-1) / 6));
      temp_row = temp_row + 1;
      this.favRow = temp_row
    },
    slicedPersonList () {
        var temp_personList = [];
        var slice_list = [];
        for(var i =1; i<this.favRow+1; i++){
          slice_list = this.favPersonList.slice((i-1)*6, (i*6))
          temp_personList.push(slice_list)
        }
        this.slicedFavPersonList = temp_personList;
    },
    openPersonInfo : function(i, index, item) {
        this.infoSwitch[i-1][index] = true;
        let sending_id = item.person_id;
        this.$axios.post("/api/removie", {
            personId : sending_id
          }).then((result) => {
          this.relatedMovie = result.data;
        }).catch((error) => console.log(error))
        this.dialog = true;
        this.reloadPage();
    },
    closePersonInfo : function(n, index) {
      this.infoSwitch[n-1][index] = false;
      this.dialog = false;
    },
    openSwitch () {
      let temp_switch = [];
      let temp_inner_switch;
      for(let i=1; i<this.favRow+1; i++){
        temp_inner_switch = []
        for(let j=0; j<this.slicedFavPersonList[i-1].length; j++){
          temp_inner_switch.push(false);
        }
        temp_switch.push(temp_inner_switch);
      }
      this.infoSwitch = temp_switch;
    },
    reloadPage () {
      this.slicedPersonList();
    }
  }
}
</script>

<style lang="css">
</style>
