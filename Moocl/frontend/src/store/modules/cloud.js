//wordcloud 용 vuex
import axios from 'axios'

const state = {
    wordCloud: []
}

const getters = {
    getWordCloud (state) {
      return state.wordCloud;
    }
}

const actions = {
    GETCLOUDDATA : function({commit}, movieId){
      axios.get("/api/getWord", {
        params : {
          movieId : movieId
        }
      })
      .then((result) => {
        // console.log(result.data);
      })
    }
}

const mutations = {

}

export default {
  state,
  getters,
  actions,
  mutations
}
