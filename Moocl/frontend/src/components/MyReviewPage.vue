<template lang="html">
  <table>
    <tr>
      <v-container class="pa-0">
        <v-layout class="mr-5 ml-0 pb-5 pt-0 mt-0"  style="float: left" v-for="i in slicedReviewList.length" :key="i">
          <MyReviewCard class="mx-3 pa-0" v-for="n in slicedReviewList[i-1].length" :key="n" :review-info="slicedReviewList[i-1][n-1]"></MyReviewCard>
        </v-layout>
      </v-container>
    </tr>
    <tr>
      <v-flex style="float: right;" class="mr-5 mb-5">
          <v-btn block @click.native="deleteList" large :ripple="false" class="elevation-3 mr-5" outline>삭제</v-btn>
          <v-snackbar v-model="askDelete" :vertical="true" v-if =" selected.length > 0" class="title">
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
  created () {
    setTimeout(function() {
      this.myReviewList = this.$store.getters.getMyPageData.myReviewData;
      this.row();
      this.slicedMovieList();
    }.bind(this), 500);
  },
  mounted() {
    this.$eventBus.$on('selected', (reviewObject) => {
      var elementIndex = this.selected.indexOf(reviewObject);
        if(elementIndex  == -1){
          this.selected.push(reviewObject)
        } else {
          this.selected.splice(reviewObject, 1)
        }
      })
    },
  data : function() {
    return {
      myReviewList: [],
      selected: [],
      askDelete: false,
      reviewRow : 0,
      slicedReviewList : []
    }
  },
  methods: {
    deleteList : function() {
      this.askDelete = true
    },
    confirmDelete : function() {
      let reivewList = this.selected;
      this.$axios.post("/api/delreview", {reivewList})
      .then((result) => {
        this.askDelete = false;
        let reviewList = this.selected
        alert(result.data + "개의 리뷰가 삭제되었습니다.")
        for (let i=0; i<reviewList.length; i++){
          let delIndex = this.myReviewList.indexOf(reviewList[i]);
          if( delIndex != -1 ){
            let row_index = parseInt(delIndex-1 / 4);
            let index = delIndex % 4;
            this.slicedReviewList[row_index].splice(index, 1);
            this.$eventBus.$emit("reSelection")
          }
        }
      })
    },
    row () {
      var temp_row = 0;
      temp_row =  parseInt((this.myReviewList.length-1)/4);
      temp_row = temp_row + 1;
      this.reviewRow = temp_row
      },
    slicedMovieList () {
      var temp_movieList = [];
      var slice_list = [];
      for(var i =1; i<this.reviewRow+1; i++){
        slice_list = this.myReviewList.slice((i-1)*4, (i*4))
        temp_movieList.push(slice_list)
      }
      this.slicedReviewList = temp_movieList;
    }
  },
}
</script>

<style lang="css">
</style>
