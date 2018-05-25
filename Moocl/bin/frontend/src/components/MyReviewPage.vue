<template lang="html">
  <table>
    <tr>
      <v-container class="pa-0">
        <v-layout class="mr-5 ml-0 pb-5 pt-0 mt-0"  style="float: left" v-for="i in slicedMovieList.length" :key="i">
            <MyReviewCard class="mx-3 pa-0" v-for="moviename in slicedMovieList[i-1]" :key="moviename" :moviename="moviename"></MyReviewCard>
        </v-layout>
      </v-container>
    </tr>
    <tr>
      <v-flex style="float: right;" class="mr-5 mb-5">
          <v-btn block @click.native="deleteList" large :ripple="false" class="elevation-3 mr-5" outline>삭제</v-btn>
          <v-snackbar v-model="askDelete" vertical="true" v-if =" selected.length > 0" class="title">
              {{ selected.length }} 개의 리스트를 삭제하시겠습니까?
              <v-layout>
                <v-flex>
                  <v-btn flat @click.native="confirmDelete" color="light-blue" class="subheading"><strong>확인</strong></v-btn>
                </v-flex>
                <v-flex>
                  <v-btn flat color="red" @click.native="askDelete = false" class="subheading"><strong>취소</strong></v-btn>
                </v-flex>
              </v-layout>
          </v-snackbar>
          <v-snackbar v-else  v-model="askDelete" :vertical="true" class="title" >
              선택된 리뷰가 없습니다.
              <v-btn flat color="red" @click.native="askDelete = false" class="subheading"><strong>취소</strong></v-btn>
          </v-snackbar>
      </v-flex>
    </tr>
  </table>

</template>

<script>
import MyReviewCard from "./MyReviewCard.vue"

export default {
  name : "MyReviewPage",
  components : {
    MyReviewCard
  },
  data : function() {
    return {
      movienames: ["어벤져스", "영화제목_3","영화제목_4",],
      selected: [],
      askDelete: false
    }
  },
  methods: {
    deleteList : function() {
      this.askDelete = true
    },
    confirmDelete : function() {
      this.askDelete = true
    }
  },
  mounted() {
    this.$eventBus.$on('selected', (moviename) => {
      console.log(moviename)
      var elementIndex = this.selected.indexOf(moviename);
      console.log(elementIndex)
      if(elementIndex  == -1){
        this.selected.push(moviename)
      } else {
        this.selected.splice(elementIndex, 1)
      }
    })
  },
  computed : {
    row () {
      var temp_row = 0;
      temp_row =  parseInt((this.movienames.length-1)/4);
      temp_row = temp_row + 1;
      return temp_row
    },
    slicedMovieList () {
      var temp_movieList = [];
      var slice_list = [];
      for(var i =1; i<this.row+1; i++){
        slice_list = this.movienames.slice((i-1)*4, (i*4))
        temp_movieList.push(slice_list)
      }
      return temp_movieList;
    }
  },
}
</script>

<style lang="css">
</style>
