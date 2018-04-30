<template>
  <v-app class="white ma-0">
      <ToolBar v-if="mainPageChecker"></ToolBar>
      <router-view/>
      <div is="FooterVue"></div>
  </v-app>
</template>

<script>
import ToolBar from './components/ToolBar.vue'
import FooterVue from './components/Footer.vue'

export default {
  name: 'App',
  components: {
    ToolBar,
    FooterVue
  },
  data: function() {
    return {
      mainPageChecker: false
    }
  },
  created() {
    this.$eventBus.$on('notMainPage', () => {
      this.mainPageChecker = false;
    });

    this.$eventBus.$on('MainPage', () => {
      this.mainPageChecker = true;
    })

  }

}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

</style>
