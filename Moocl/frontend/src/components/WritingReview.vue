<template lang="html">
<v-dialog :max-width="800">
  <v-btn class="primary" slot="activator">Open Dialog</v-btn>
  <v-card flat>
     <v-card-text>
       <v-subheader class="title black--text">{{moviename}} 리뷰</v-subheader>
       <v-container fluid class="px-3 py-0">
         <v-layout row>
           <v-flex xs12>
             <v-text-field class="pa-0" placeholder="리뷰를 작성해주세요" textarea no-resize rows="10"></v-text-field>
           </v-flex>
         </v-layout>
         <v-layout justify-end>
          <v-btn class="black ma-0" outline @click="saveReview">저장</v-btn>
         </v-layout>
       </v-container>
     </v-card-text>
   </v-card>
</v-dialog>
</template>

<script>
export default {
  name: "WritingRevie",
  data : function() {
    return {
      moviename: "영화제목"
    }
  },
  methods : {
    saveReview : function() {
      console.log("리뷰저장")
    }
  }

}
</script>

<style lang="css">
</style>
