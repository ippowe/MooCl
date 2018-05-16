<template lang="html">
<v-container>
  <v-layout justify-end class="mx-5">
    <v-flex xs2>
      <!-- 한개의 함수로 바꾸기  -->
      <v-icon  color="red" @click="fillonestar" class="noselect" >{{score > 0  ? "star" : "star_border"}}</v-icon>
      <v-icon  color="red" @click="filltwostar" class="noselect">{{score > 1  ? "star" : "star_border"}}</v-icon>
      <v-icon  color="red" @click="fillthreestar" class="noselect">{{score > 2  ? "star" : "star_border"}}</v-icon>
      <v-icon  color="red" @click="fillfourstar" class="noselect">{{score > 3  ? "star" : "star_border"}}</v-icon>
      <v-icon  color="red" @click="fillfivestar" class="noselect">{{score > 4 ? "star" : "star_border"}}</v-icon>
    </v-flex>
    <v-spacer></v-spacer>
    <v-dialog max-width="600px">
      <v-avatar slot="activator" @click="checkLogin"  class="my-0 mr-4 noselect"><v-icon color="light-blue">create</v-icon></v-avatar>
      <WritingReview v-show="validUser" :movietitle="detailinfo.movie_title" :movieid="detailinfo._id"></WritingReview>
      <NeedLogin v-show="!validUser"></NeedLogin>
    </v-dialog>
  </v-layout>
  <v-layout justify-center>
      <v-card width="1000" flat>
        <v-data-table light :headers="headers" :items="items" hide-actions class="elevation-1 transparent" :pagination.sync="pagination">
           <template slot="items" slot-scope="props">
             <td><strong>{{ props.item.name }}</strong></td>
             <td class="text-xs-left">{{ props.item.review }}</td>
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
    this.items = this.$store.state.reviewList;
    this.pagination.totalItems = this.$store.state.reviewCount;
  },
  watch : {
    pagination () {
      console.log(this.paginagtion);
    }
  },
  props :["detailinfo"],
  data () {
     return {
       headers: [
         { text: '아이디', align: 'center', sortable: false, value: 'ID', width : '200'},
         { text: '리뷰', value: 'review', sortable: false, align: 'center', width: '600'},
         { text: '평점', value: 'score',align: 'center', width:'200'},
       ],
       items: [],
       score : 0,
       pagination: {},
       selected: [],
       validUser: false,
     }
   },
   methods :{
     fillonestar() {
       if(this.score != 1){
          this.score= 1
       } else {
         this.score = 0;
       }
     },
     filltwostar() {
       if(this.score != 2){
          this.score= 2
       } else {
         this.score = 0;
       }
     },
     fillthreestar() {
       if(this.score != 3){
          this.score= 3
       } else {
         this.score = 0;
       }
     },
     fillfourstar() {
       if(this.score != 4){
          this.score= 4
       } else {
         this.score = 0;
       }
     },
     fillfivestar() {
       if(this.score != 5){
          this.score= 5
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
     getPage : function() {
       console.log(this.pagination);
     }
   },
   computed: {
      pages () {
        if (this.pagination.rowsPerPage == null ||
          this.pagination.totalItems == null
        ) return 0

        return Math.ceil(this.pagination.totalItems / this.pagination.rowsPerPage)
      },
    }
}
</script>

<style lang="css">
.noselect { -webkit-touch-callout: none; -webkit-user-select: none; -khtml-user-select: none; -moz-user-select: none; -ms-user-select: none; user-select: none; cursor: pointer}
</style>
