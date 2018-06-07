import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
import login from './modules/login'
import favlist from './modules/favlist'
import reviewlist from './modules/reviewlist'
import cloud from './modules/cloud'
import movie from './modules/movie'
import recommend from './modules/recommend'

Vue.use(Vuex)

export default new Vuex.Store ({
  modules :{
    login,
    reviewlist,
    favlist,
    cloud,
    movie,
    recommend
  }
})
