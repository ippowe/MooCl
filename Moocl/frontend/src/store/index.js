import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

const resourceHost = 'http://localhost:8090'

const enhanceAccessToeken = () => {
  const {accessToken} = sessionStorage
  if (!accessToken) return
  axios.defaults.headers.common['Authorization'] = `Bearer ${accessToken}`;
}
enhanceAccessToeken()

export default new Vuex.Store({
  state: {
    accessToken: null
  },
  getters: {
    isAuthenticated (state) {
      state.accessToken = state.accessToken || sessionStorage.token
      return state.accessToken
    }
  },
  mutations: {
    LOGIN (state, data) {
      state.accessToken = data
      sessionStorage.token = data.token
      sessionStorage.userNo = data.userNo
      return sessionStorage
    },
    LOGOUT (state) {
      console.log("store logout mutation")
      state.accessToken = null
      delete sessionStorage.token
      delete sessionStorage.userNo
    }
  },
  actions: {
    LOGIN ({commit}, {email, password}) {
      return axios.post('/api/login', {email, password})
        .then(({data}) => {
          commit('LOGIN', data)
          axios.defaults.headers.common['Authorization'] = data;
          return data
        })
    },
    LOGOUT ({commit}) {
      console.log("store logout action")
      axios.defaults.headers.common['Authorization'] = undefined
      commit('LOGOUT')
    },
  }
})
