<template lang="html">
<v-container>
  <v-layout justify-center>
    <v-flex  v-for="n in 5" :key="n" xs2>
    <v-btn icon :ripple="false" block><v-icon color="red" v-for="i in n" :key="i">star</v-icon></v-btn>
    </v-flex>
  </v-layout>
  <v-layout justify-center>
      <v-card width="1000">
        <v-data-table :headers="headers" :items="items" hide-actions class="elevation-1 transparent">
           <template slot="items" slot-scope="props">
             <td>{{ props.item.name }}</td>
             <td class="text-xs-left">{{ props.item.calories }}</td>
             <td class="text-xs-left">
               <v-icon color="red">{{ props.item.fat }}</v-icon></td>
           </template>
         </v-data-table>
      </v-card>

  </v-layout>

</v-container>


</template>

<script>
export default {
  data () {
     return {
       headers: [
         { text: '아이디', align: 'center', sortable: false, value: 'ID', width : '200'},
         { text: '리뷰', value: 'review', sortable: false, align: 'center', width: '600'},
         { text: '평점', value: 'score',align: 'center', width:'200'},
       ],
       items: [
         {name: "jae7415", calories:"리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용", fat:"star"},
         {name: "jae7415", calories:"리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용", fat:"star"},
         {name: "jae7415", calories:"리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용", fat:"star"},
         {name: "jae7415", calories:"리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용", fat:"star"},
         {name: "jae7415", calories:"리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용", fat:"star"},
         {name: "jae7415", calories:"리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용", fat:"star"},
         {name: "jae7415", calories:"리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용", fat:"star"},
         {name: "jae7415", calories:"리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용", fat:"star"},
         {name: "jae7415", calories:"리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용", fat:"star"},
         {name: "jae7415", calories:"리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용", fat:"star"},  
       ]
     }
   }
}
</script>

<style lang="css">
</style>
