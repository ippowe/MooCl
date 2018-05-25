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
    reviewCount: {},
    favMovieList: [],
    favPersonList: [],
    myReviewList: [],
    myPageData: {},
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
      sessionStorage.email = data.email
      return sessionStorage
    },
    LOGOUT (state) {
      state.accessToken = null;
      state.faveMovieList = [];
      state.favPersonList = [];
      state.myReviewList = [];
      delete sessionStorage.token
      delete sessionStorage.userNo
      delete sessionStorage.email
    },
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
    SETINFOLIST(state, temp_object){
      state.favMovieList= temp_object.FavMovieList;
      state.favPersonList= temp_object.favpeopleList;
      state.myReviewList= temp_object//내리뷰리스트;
    },
    SETMYAPAGEDATA(state, mypage_data){
      state.myPageData = mypage_data;
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
    },
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
    }
  }
})
