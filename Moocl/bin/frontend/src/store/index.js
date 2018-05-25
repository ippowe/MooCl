import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

const enhanceAccessToeken = () => {
  const {accessToken} = sessionStorage
  if (!accessToken) return
  axios.defaults.headers.common['Authorization'] = `Bearer ${accessToken}`;
}
enhanceAccessToeken()

export default new Vuex.Store({
  state: {
    accessToken: null,
    reviewList: [],
    reviewCount: 0,
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
      state.accessToken = null
      delete sessionStorage.token
      delete sessionStorage.userNo
    },
    SETREVIEW (state, temp_list) {
      state.reviewList = [];
      let name ="";
      let review = "";
      let score = 0;
      let temp_review_object ={};
      for(var i=0; i<temp_list.length; i++){
        temp_review_object = {
            name: temp_list[i].user_id,
            review: temp_list[i].review_contents,
            score: temp_list[i].user_grade
        }
        state.reviewList.push(temp_review_object);
      }
    },
    SETREVIEWCOUNT (state, temp_count){
      state.reviewCount = temp_count;
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
      axios.defaults.headers.common['Authorization'] = undefined
      commit('LOGOUT')
    },
    GETREVIEW ({commit}, movieId) {
      axios.get("/api/reviewlist", {
        params : {
          movieId : movieId,
          pageNo : 1
        }
      })
      .then((result) => {
        let temp_list = result.data;
        commit('SETREVIEW', temp_list);
        return temp_list
      })
      .catch((error) => console.log(error))
    },
    GETREVIEWCOUNT ({commit}, movieId){
      axios.get("/api/reviewcount", {
        params : {
          movieId : movieId
        }
      })
      .then((result) => {
        let temp_count = result.data;
        commit('SETREVIEWCOUNT', temp_count);
        return temp_count;
      })
    }
  }
})
