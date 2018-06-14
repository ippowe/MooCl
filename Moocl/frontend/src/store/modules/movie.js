//Movie 정보 관련 vuex
import axios from 'axios'

const state = {
  normalInfoList : [],
  detailInfo: {}

}

const getters = {
  getNormalInfo () {
    return state.normalInfoList;
  },
  getDetailInfo () {
    return state.detailInfo
  }
}

const actions = {
  GETMOVIEINFO({commit}, word){
    return new Promise(function(resolve, reject) {
      let temp_word = word
      if(temp_word == undefined){
        temp_word = ""
      }

      axios.get('/api/search', {
        params : {
          keyword : temp_word
        }})
      .then((response) => {
        let normalInfoList = response.data
        console.log(normalInfoList);
        commit('SETMOVIEINFO', normalInfoList)
        resolve(normalInfoList);
      }, error =>{
        reject(error);
      })
    });
  },
  GETDETAILINFO({commit}, movieId){
    return new Promise(function(resolve, reject) {
      let temp_movieId = movieId
      axios.get("/api/detailinfo", {
        params : {
          movieId : temp_movieId
        }
      })
      .then((result) => {
        let detailInfo = result.data
        commit("SETDETAILINFO", detailInfo)
        resolve(detailInfo);
      }, error => {
        reject(error)
      })
    });
  }
}

const mutations = {
  SETMOVIEINFO(state, normalInfoList){
    let temp_list = normalInfoList;

    for(var i=0; i<temp_list.length; i++){
        state.normalInfoList.push(temp_list[i])
    }
  },
  SETDETAILINFO(state, detailInfo){
    let temp_info = detailInfo;
    state.detailInfo = temp_info;
  }
}

export default {
  state,
  getters,
  actions,
  mutations
}
