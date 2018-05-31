<template>
  <v-app class=" indigo lighten-5 ma-0">
      <ToolBar v-if="mainPageChecker"></ToolBar>
      <router-view class= "indigo lighten-5"/>
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

  },
}
</script>

<style>
@import './assets/Font/Font.css';

#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif,;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}


</style>
