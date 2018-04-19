<template>
  <div id="app">
    <header is="MenuBar"></header>
    <div>
      <router-view/>
    </div>
  </div>
</template>

<script>
import MenuBar from './components/MenuBar.vue'

export default {
  name: 'App',
  components:{ MenuBar },

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
