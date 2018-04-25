<template lang="html">
  <v-app>
    <TestArea></TestArea>
  </v-app>
</template>

<script>
import TestArea from './PersonDetail.vue'
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
