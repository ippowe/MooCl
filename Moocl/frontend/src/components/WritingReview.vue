<template lang="html">
  <v-card flat>
     <v-card-text>
       <v-subheader class="title black--text">{{movietitle}} 리뷰
         <v-spacer></v-spacer>
         <span v-for="i in 5" :key="i">
            <v-icon  color="red" @click="fillstar(i)" class="noselect" >{{score > i-1  ? "star" : "star_border"}}</v-icon>
         </span>
       </v-subheader>
       <v-container fluid class="px-3 py-0">
         <v-layout row>
           <v-flex xs12>
             <v-text-field class="pa-0" placeholder="리뷰를 작성해주세요" textarea no-resize rows="10" v-model="review"></v-text-field>
           </v-flex>
         </v-layout>
         <v-layout justify-end>
          <v-btn class="black ma-0" outline @click="saveReview">저장</v-btn>
         </v-layout>
       </v-container>
     </v-card-text>
   </v-card>
</template>

<script>
export default {
  name: "WritingReview",
  props: ["movietitle", "movieid"],
  data : function() {
    return {
      score : 0,
      review : "",
      userNo : ""
    }
  },
  methods : {
    saveReview : function() {
      this.userNo = sessionStorage.userNo;
      let parameter = {
        score : this.score,
        review : this.review,
        movieId : this.movieid,
        email : sessionStorage.email
      }

      console.log(parameter);
      this.$axios.post('/api/saveReview', {
        score: parameter.score,
        review: parameter.review,
        movieId: parameter.movieId,
        email: parameter.email
      }).then((result) => {
        this.joinResult = true
      }).catch((error) => {
        this.errorMsg= error
        this.errorMsgShow=true
      })



    },
    fillstar(i) {
      if(this.score != i){
         this.score= i
      } else {
        this.score = 0;
      }
    }
  }

}
</script>

<style lang="css">
  .noselect { -webkit-touch-callout: none; -webkit-user-select: none; -khtml-user-select: none; -moz-user-select: none; -ms-user-select: none; user-select: none; cursor: pointer}
</style>
