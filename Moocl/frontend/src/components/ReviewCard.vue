<template lang="html">
  <v-layout>
  <v-flex>
    <v-checkbox v-model="selected" style="width: 30px"> </v-checkbox>
  </v-flex>
  <v-flex xs12>
    <v-card width="400px" slot="activator">
      <v-layout>
        <v-flex>
          <CardForModal></CardForModal>
        </v-flex>
        <v-flex>
          <v-card-text class="pa-0">
            <v-card-title primary-title class="pa-2 title">
              <v-avatar class="mr-2" size=30>
                <img src="../assets/전체관람가.jpg">
              </v-avatar>영화 제목
            </v-card-title>
            <v-card-media class="pa-3 subheading">
              영화 기본 정보
            </v-card-media>
              <p style="text-align: left;" class="pa-2" >
                {{review}}
              </p>
          </v-card-text>
        </v-flex>
      </v-layout>
    </v-card>
  </v-flex>

  </v-layout>



</template>

<script>
import CardForModal from "./CardForModal.vue"
export default {
  name : "ReviewCard",
  props: ['moviename'],
  components : {
    CardForModal
  },
  data : function() {
    return {
      review : "리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용...[더보기]",
      selected : []
    }
  }
}
</script>

<style lang="css">
</style>
