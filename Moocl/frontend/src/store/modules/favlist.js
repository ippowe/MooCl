//mypage 관련 vuex
import axios from 'axios'

const state = {
  favMovieList: [],
  favPersonList: [],
  myPageData: {}
}

const getters = {
  getFavMovieList (state) {
    return state.favMovieList;
  },
  getFavPersonList (state) {
    return state.favPersonList;
  },
  getMyPageData (state) {
    return state.myPageData;
  }
}

const actions ={
  GETINFOLIST ({commit}, user_no){
    axios.get("/api/favlist", {
      params : {
        userId : user_no
      }
    })
    .then((result) => {
      let temp_object = result.data;
      commit('SETINFOLIST', temp_object);
    })
  },
  MYPAGEDATA ({commit}, user_no){
    axios.get("/api/favdata", {
      params : {
        userId : user_no
      }
    })
    .then((result) => {
      commit('SETMYAPAGEDATA', result.data)
    })
  },
  MYREVIEWDATA({commit}, email){
    axios.post("/api/reviewsearch", {email})
    .then((result) => {
      commit('SETREVIEWDATA', result.data);
    })
  }
}

const mutations = {
  SETMYAPAGEDATA(state, mypage_data){
    state.myPageData = mypage_data;
  },
  SETREVIEWDATA(state, review_data){
    state.myPageData["myReviewData"] = review_data;
  },
  SETINFOLIST(state, temp_object) {
    state.favMovieList = temp_object.FavMovieList;
    state.favPersonList = temp_object.favpeopleList;
  }
}

export default {
  state,
  actions,
  getters,
  mutations
}
