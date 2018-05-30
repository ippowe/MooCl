//reviewlist를 위한 Vuex
import axios from 'axios'


const state = {
  reviewList: [],
  reviewCount: {}
}

const getters ={
  getReviewList (state) {
    return state.reviewList;
  },
  getReviewCount (state) {
    return state.reviewCount;
  }
}

const actions = {
  GETREVIEW ({commit}, parameters) {
    return axios.get("/api/reviewlist", {
      params : {
        movieId : parameters.movieId,
        pageNo : parameters.pageNo,
        score : parameters.score,
      }
    })
    .then((result) => {
      let temp_list = result.data;
      commit('SETREVIEW', temp_list);
      return temp_list
    })
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

const mutations = {
  SETREVIEW (state, temp_list) {
    state.reviewList = [];
    let temp_review_object ={};

    for(var i=0; i<temp_list.length; i++){
      let temp_name = "";
      if(temp_list[i].site == "daum"){
        temp_name = temp_list[i].user_id.slice(7, 10);
        temp_name = temp_name + "***";
      } else if (temp_list[i].site == "cgv"){
        temp_name = temp_list[i].user_id.slice(0,3);
        temp_name = temp_name + "***";
      } else {
        temp_name = temp_list[i].user_id;
      }

      temp_review_object = {
          name: temp_name,
          review: temp_list[i].review_contents,
          score: Math.ceil(temp_list[i].user_grade / 2)
      }
      state.reviewList.push(temp_review_object);
    }
  },
  SETREVIEWCOUNT (state, temp_count){
    state.reviewCount = temp_count;
  },
}

export default {
  state,
  actions,
  mutations,
  getters
}
