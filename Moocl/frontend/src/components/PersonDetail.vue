<template lang="html">
  <v-dialog max-width="800" v-model="dialog">
    <PersonCard :name="name == undefined ? '영화제목' : name" slot="activator"></PersonCard>
    <v-layout class="white">
      <v-flex class="ma-2 ml-3" xs3>
        <!-- <PersonCard></PersonCard> -->
        {{ url }}
      </v-flex>
      <v-flex class="mr-3 mb-3">
        <WordCloud :moviename="name == undefined ? '영화제목' : name"></WordCloud>
      </v-flex>
    </v-layout>
  </v-dialog>
</template>

<script>
import PersonCard from "./PersonCard.vue"
import WordCloud from "./WordCloud.vue"

export default {
  components: {
    PersonCard,
    WordCloud
  },
  beforeUpdated () {
    this.$eventBus.$on('sendInfo', function(temp_role, temp_name, resizeUrl){
      this.role = temp_role;
      this.name = temp_name;
      this.url = resizeUrl;
      console.log(resizeUrl)
    });
  },
  name: "SmallModal",
  data : function() {
    return {
      dialog: false,
      name: "default",
      role: "default",
      url: "default"
    }
  },
}
</script>

<style lang="css">
</style>
