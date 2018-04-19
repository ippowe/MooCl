<template lang="html">
  <CardForModal></CardForModal>
</template>

<script>
import CardForModal from './CardForModal.vue'
export default {
  created () {
    this.$eventBus.$emit('notMainPage')
  },
  components:{
    CardForModal
  },
  name: 'TestPage'
}
</script>

<style lang="css">
</style>
