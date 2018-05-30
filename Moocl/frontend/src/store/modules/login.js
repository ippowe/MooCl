//Login관련 Vuex
import axios from 'axios'

const enhanceAccessToeken = () => {
  const {accessToken} = sessionStorage
  if (!accessToken) return
  axios.defaults.headers.common['Authorization'] = `Bearer ${accessToken}`;
}
enhanceAccessToeken()

const state = {
  accessToken: null
}

const getters = {
  isAuthenticated (state) {
    state.accessToken = state.accessToken || sessionStorage.token
    return state.accessToken
  }
}

const actions = {
    LOGIN ({commit}, {email, password}) {
      return axios.post('/api/login', {email, password})
        .then(({data}) => {
          commit('LOGIN', data)
          axios.defaults.headers.common['Authorization'] = data;
          return data
        })
    },

    LOGOUT ({commit}) {
      axios.defaults.headers.common['Authorization'] = undefined
      commit('LOGOUT')
    }
}


const mutations = {
  LOGIN (state, data) {
    state.accessToken = data
    sessionStorage.token = data.token
    sessionStorage.userNo = data.userNo
    sessionStorage.email = data.email
    return sessionStorage
  },
  LOGOUT (state) {
    state.accessToken = null;
    state.myPageData ={};
    delete sessionStorage.token
    delete sessionStorage.userNo
    delete sessionStorage.email
  },
}


export default {
  state,
  getters,
  actions,
  mutations
}
