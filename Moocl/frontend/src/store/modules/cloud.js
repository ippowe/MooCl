//wordcloud 용 vuex
import axios from 'axios'

const state = {
    wordCloudList: []
}

const getters = {
    getWordCloudList (state) {
      return state.wordCloudList;
    }
}

const actions = {
    GETCLOUDDATA : function({commit}, movieId){
      return new Promise(function(resolve, reject) {
        axios.get("/api/getWord", {
          params : {
            movieId : movieId
          }
        })
        .then((result) => {
          let wordInfo = {
            words : result.data,
            movie : movieId
          }
          commit('SETCLOUDDATA', wordInfo);
          resolve(wordInfo);
        }, error => {
          reject(error);
        })
      });
    }
}

const mutations = {
  SETCLOUDDATA : function(state, wordInfo){

    let wordCloud = {
      movieId: wordInfo.movie,
      words : wordInfo.words
    }

    if(state.wordCloudList.length == 0){
      state.wordCloudList.push(wordCloud);
    } else {
      let length = state.wordCloudList.length
      for(var i=0; i<length; i++){
        if(state.wordCloudList.indexOf(wordCloud) == -1){
          state.wordCloudList.push(wordCloud);
        }
      }
    }
  }
}

export default {
  state,
  getters,
  actions,
  mutations
}
