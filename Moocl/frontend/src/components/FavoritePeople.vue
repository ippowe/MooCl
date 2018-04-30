<template lang="html">
    <v-container class="mb-5 ml-4 px-5 mt-4" >
      <v-layout v-for="i in slicedPersonList.length" :key="i"  class=" mb-2 mx-5 px-5">
          <PersonInfo class="mx-5 mb-3 px-1" v-for="name in slicedPersonList[i-1]" :key="name" :personname="name" :roleview="true"></PersonInfo>
      </v-layout>
    </v-container>
</template>

<script>
import PersonInfo from "./PersonInfo.vue"

export default {
  name: "FavoritePeople",
  components : {
    PersonInfo
  },
  data : function() {
    return {
      names : ["이병현", "이동욱", "한효주", "수지", "로버트 다우 주니어", "휴 잭맨", "정우성", "이병현", "이동욱", "한효주", "수지",
      "로버트 다우 주니어", "휴 잭맨"],
    }
  },
  computed : {
    row () {
      var temp_row = 0;
      temp_row = parseInt((this.names.length-1 / 6));
      temp_row = temp_row + 1;
      return temp_row
    },
    slicedPersonList () {
        var temp_personList = [];
        var slice_list = [];
        for(var i =1; i<this.row+1; i++){
          slice_list = this.names.slice((i-1)*6, (i*6))
          temp_personList.push(slice_list)
        }
        return temp_personList;
    }
  }


}
</script>

<style lang="css">
</style>
