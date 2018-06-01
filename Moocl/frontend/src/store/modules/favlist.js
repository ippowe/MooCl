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
  GETFAVLIST ({commit}, user_no){
    return new Promise(function(resolve, reject) {
      axios.get("/api/favlist", {
        params : {
          userId : user_no
        }
      })
      .then((result) => {
        let temp_object = result.data;
        commit('SETFAVLIST', temp_object);
        resolve(temp_object);
      }, error => {
        reject(error);
      })
    });
  },
  MYPAGEDATA ({commit}, user_no){
    return new Promise(function(resolve, reject) {
      axios.get("/api/favdata", {
        params : {
          userId : user_no
        }
      })
      .then((result) => {
        commit('SETMYAPAGEDATA', result.data)
        resolve(result.data);
      }, error => {
        reject(error);
      })
    });
  },
  MYREVIEWDATA({commit}, email){
    return new Promise(function(resolve, reject) {
      axios.post("/api/reviewsearch", {email})
      .then((result) => {
        commit('SETREVIEWDATA', result.data);
        resolve(result.data);
      }, err => {
        reject(err);
      })
    });

  }
}

const mutations = {
  SETMYAPAGEDATA(state, mypage_data){
    state.myPageData = mypage_data;
  },
  SETREVIEWDATA(state, review_data){
    state.myPageData["myReviewData"] = review_data;
  },
  SETFAVLIST(state, temp_object) {
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
