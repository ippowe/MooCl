<template lang="html">
<v-container>
  <v-layout justify-end class="mx-5">
    <v-flex xs2>
      <v-icon  color="red" @click="fillonestar" class="noselect" >{{score > 0  ? "star" : "star_border"}}</v-icon>
      <v-icon  color="red" @click="filltwostar" class="noselect">{{score > 1  ? "star" : "star_border"}}</v-icon>
      <v-icon  color="red" @click="fillthreestar" class="noselect">{{score > 2  ? "star" : "star_border"}}</v-icon>
      <v-icon  color="red" @click="fillfourstar" class="noselect">{{score > 3  ? "star" : "star_border"}}</v-icon>
      <v-icon  color="red" @click="fillfivestar" class="noselect">{{score > 4 ? "star" : "star_border"}}</v-icon>
    </v-flex>
    <v-spacer></v-spacer>
      <v-avatar  class="my-0 mr-4 noselect" @click="writeReview"><v-icon color="light-blue">create</v-icon></v-avatar>
  </v-layout>
  <v-layout justify-center>
      <v-card width="1000" flat>
        <v-data-table light :headers="headers" :items="items" hide-actions class="elevation-1 transparent" :pagination.sync="pagination">
           <template slot="items" slot-scope="props">
             <td><strong>{{ props.item.name }}</strong></td>
             <td class="text-xs-left">{{ props.item.review }}</td>
             <td class="text-xs-left">
               <v-icon color="red" v-for="i in props.item.score" :key="i">star</v-icon></td>
           </template>
         </v-data-table>
         <div class="text-xs-center pt-2">
          <v-pagination v-model="pagination.page" :length="pages"></v-pagination>
        </div>
      </v-card>

  </v-layout>

</v-container>


</template>

<script>
import ReviewDetail from "./ReviewDetail.vue"

export default {
  name : "ReviewList",
  compoenets : {
    ReviewDetail
  },
  data () {
     return {
       headers: [
         { text: '아이디', align: 'center', sortable: false, value: 'ID', width : '200'},
         { text: '리뷰', value: 'review', sortable: false, align: 'center', width: '600'},
         { text: '평점', value: 'score',align: 'center', width:'200'},
       ],
       items: [   // computed로 axios통해서 받아오기
         {name: "jae7415", review:"리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용", score:1},
         {name: "jae7415", review:"리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용", score:2},
         {name: "jae7415", review:"리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용", score:4},
         {name: "jae7415", review:"리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용", score:3},
         {name: "jae7415", review:"리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용", score:2},
         {name: "jae7415", review:"리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용", score:2},
         {name: "jae7415", review:"리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용", score:3},
         {name: "jae7415", review:"리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용", score:4},
         {name: "jae7415", review:"리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용", score:3},
         {name: "jae7415", review:"리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용", score:4},
       ],
       score : 0,
       pagination: {},
       selected: [],
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
     writeReview() {
       console.log("리뷰 작성")
     }
   },
   computed: {
      pages () {
        if (this.pagination.rowsPerPage == null ||
          this.pagination.totalItems == null
        ) return 0

        return Math.ceil(this.pagination.totalItems / this.pagination.rowsPerPage)
      }
    }
}
</script>

<style lang="css">
.noselect { -webkit-touch-callout: none; -webkit-user-select: none; -khtml-user-select: none; -moz-user-select: none; -ms-user-select: none; user-select: none; cursor: pointer}
</style>
