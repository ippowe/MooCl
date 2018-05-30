// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import axios from 'axios'
import  Vuetify from 'vuetify'
import 'vuetify/dist/vuetify.min.css'
import 'material-design-icons-iconfont/dist/material-design-icons.css'
import 'babel-polyfill'
import 'expose-loader?$!expose-loader?jQuery!jquery'
import jQuery from 'jqcloud2/dist/jqcloud.js'
import 'jqcloud2/dist/jqcloud.css'
import store from './store/store'


Vue.config.productionTip = false
Vue.prototype.$axios = axios;
Vue.prototype.$eventBus = new Vue();
Vue.use(Vuetify)

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>',
  store
})
