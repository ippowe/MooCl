<template>
<div id="app" class="pa-0">
  <router-view/>
  <div is="MenuBar" v-if="mainPageChecker"></div>
  <div is="FooterVue">

  </div>
</div>
</template>

<script>
import MenuBar from './components/MenuBar.vue'
import FooterVue from './components/Footer.vue'

export default {
  name: 'App',
  components: {
    MenuBar,
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
