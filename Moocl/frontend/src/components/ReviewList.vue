<template lang="html">
<v-container>
  <v-layout justify-end class="mx-5">
    <v-flex xs2>
      <span v-for="i in 5" :key="i">
        <v-icon  color="red" @click="fillstar(i)" class="noselect" >{{score > i-1  ? "star" : "star_border"}}</v-icon>
      </span>
      <span>
        <v-icon color="brack" class="noselect" @click="refreshScore">refresh</v-icon>
      </span>
    </v-flex>
    <v-spacer></v-spacer>
    <v-dialog max-width="600px" v-model="writeDialog">
      <v-avatar slot="activator" @click="checkLogin"  class="my-0 mr-4 noselect"><v-icon color="light-blue">create</v-icon></v-avatar>
      <WritingReview v-show="validUser" :movietitle="detailinfo.movie_title" :movieid="detailinfo._id"  @finWriting="writeDialog = !writeDialog"></WritingReview>
      <NeedLogin v-show="!validUser"></NeedLogin>
    </v-dialog>
  </v-layout>
  <v-layout justify-center>
      <v-card width="1000" flat>
        <v-data-table light :headers="headers" :items="movieItems" :total-items="totalItems" hide-actions class="elevation-1 transparent" :pagination.sync="pagination">
           <template slot="items" slot-scope="props">
             <td><strong>{{ props.item.name }}</strong></td>
             <td class="text-xs-left">
               {{ props.item.slicedReview ? props.item.slicedReview : props.item.review }}
                 <span @click="dialog = true" class="noselect" v-if="props.item.readMore">
                   <strong>더보기</strong>
                 </span>
              <v-dialog :max-width="800" v-model='dialog' v-if="props.item.readMore">
                 <ReviewDetail :movieinfo="detailinfo" :reviewcontent="props.item"></ReviewDetail>
               </v-dialog>
             </td>
             <td class="text-xs-left">
               <v-icon color="red" v-for="i in props.item.score" :key="i">star</v-icon>
             </td>
           </template>
         </v-data-table>
          <v-pagination v-model="pagination.page" :length="pages" :total-visible="10" @click="getPage"></v-pagination>
      </v-card>
  </v-layout>

</v-container>


</template>

<script>
import ReviewDetail from "./ReviewDetail.vue"
import WritingReview from "./WritingReview.vue"
import NeedLogin from "./NeedLogin.vue"

export default {
  name : "ReviewList",
  components : {
    NeedLogin,
    WritingReview,
    ReviewDetail,
  },
  created () {
    this.totalItems = this.$store.state.reviewCount.total;
  },
  watch : {
    pagination : {
      handler () {
        this.getReviewList();
      },
      deep : true  //pagination 객체 안에 있는 요소들의 변경을 확인 해줌
    },
    score : {
      handler () {
        if(this.score > 0){
          var prev_score = 2*this.score-1;
          var next_score = 2*this.score;
          this.pagination.totalItems = this.$store.state.reviewCount[prev_score] + this.$store.state.reviewCount[next_score];
        } else if( this.score == -1){
          this.pagination.totalItems = this.$store.state.reviewCount["total"];
        } else {
          this.pagination.totalItems = this.$store.state.reviewCount[0];
        }
        this.getReviewList();
      }
    },
  },
  props :["detailinfo"],
  data () {
     return {
       headers: [
         { text: '아이디', align: 'center', sortable: false, value: 'ID', width : '200'},
         { text: '리뷰', value: 'review', sortable: false, align: 'center', width: '600'},
         { text: '평점', value: 'score', sortable: false, align: 'center', width:'200'},
       ],
       movieItems: [],
       totalItems: 0,
       score : -1,
       pagination: {},
       selected: [],
       rawReviewList: [],
       pageNo: 0,
       dialog: false,
       writeDialog: false,
     }
   },
   methods :{
     fillstar(i) {
       if(this.score != i){
          this.score= i
       } else {
         this.score = 0;
       }
     },
     checkLogin : function() {
       if(sessionStorage.length == 0){
         this.validUser = false;
       } else {
         this.validUser = true;
       }
     },
     getReviewList : function() {
       let movieId = this.detailinfo._id;
       let pageNo = this.pagination.page;
       let score = this.score;
       let parameters = { movieId, pageNo, score };

       this.$store.dispatch("GETREVIEW", parameters)
       .then((response) => {
         let temp_reviewList= this.$store.state.reviewList;
         let str = "";

         for(var i=0; i<temp_reviewList.length; i++){
           temp_reviewList[i]["readMore"] = false;

           let temp_review = temp_reviewList[i].review;
           temp_review = temp_review.replace(' ', '');

           if(temp_review.length > 100){
             str = "";
             str += temp_reviewList[i].review.slice(0,85);
             str += "...";
             temp_reviewList[i]["slicedReview"] = str;
             temp_reviewList[i].readMore = true;
           }
         }
         this.movieItems = temp_reviewList;
       })
     },
     refreshScore : function() {
       this.score = -1;
       this.getReviewList();
     },
   },
   computed : {
     pages : function (){
       if (this.pagination.rowsPerPage == null ||
         this.pagination.totalItems == null
       ) return 0

         return Math.ceil(this.pagination.totalItems / this.pagination.rowsPerPage)
     },
     validUser : function() {
       if(sessionStorage.userNo != undefined) {
         return false
       } else {
         return true
       }
     }
   }
}
</script>

<style lang="css">
.noselect { -webkit-touch-callout: none; -webkit-user-select: none; -khtml-user-select: none; -moz-user-select: none; -ms-user-select: none; user-select: none; cursor: pointer}
</style>
