<template lang="html">
  <v-app class="white">
    <TestArea></TestArea>
  </v-app>
</template>

<script>
import TestArea from './MovieDetailInfo.vue'
export default {
  created () {
    this.$eventBus.$emit('notMainPage')
  },
  components:{
    TestArea
  },
  name: 'TestPage'
}
</script>

<style lang="css">
</style>
