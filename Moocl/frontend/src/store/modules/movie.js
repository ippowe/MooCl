//Movie 정보 관련 vuex
import axios from 'axios'

const state = {
  normalInfoList : [],

}

const getters = {
  getNormalInfo () {
    return state.normalInfoList;
  }
}

const actions = {
  GETMOVIEINFO({commit}, word){
    axios.get('/api/search', {
      params : {
        keyword : word
      }})
    .then((response) => {
      let normalInfoList = response.data
      commit('SETMOVIEINFO', normalInfoList)

    })
  }
}

const mutations = {
  SETMOVIEINFO(state, normalInfoList){
    let temp_list = normalInfoList;

    for(var i=0; i<temp_list.length; i++){
        state.normalInfoList.push(temp_list[i])
    }

    console.log(state.normalInfoList);
  }
}

export default {
  state,
  getters,
  actions,
  mutations
}
