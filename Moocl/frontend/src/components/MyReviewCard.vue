<template lang="html">
  <v-flex class="pa-0 ma-0">
    <v-card width="400px" flat>
      <v-layout align-start>
        <v-checkbox @click.native="selected" v-model="selection" style="width: 30px" class="mr-3" :ripple="false" color="grey lighten-1"> </v-checkbox>
        <table>
          <tr>
              <v-flex class="pa-1">
                <SmallMoviePoster></SmallMoviePoster>
              </v-flex>
            <td>
              <v-flex class="pa-1">
                <v-card-text class="pa-0">
                  <v-card-title primary-title class="pa-0 title">
                    <v-avatar class="mr-2" size=30>
                      <img src="../assets/전체관람가.png">
                    </v-avatar>
                    <strong>{{ moviename }}</strong>
                  </v-card-title>
                  <v-card-media class="py-2 subheading">
                    영화 기본 정보
                  </v-card-media>

                    <p style="text-align: left;" class="py-2" >
                      {{review}}
                      <span @click="myReviewDetail = true" style="cursor: pointer;"><strong>더보기</strong></span>
                      <v-dialog v-model="myReviewDetail" max-width="800px">
                        <v-card class="pa-3">
                          <v-layout>
                            <SmallMoviePoster></SmallMoviePoster>
                            <WordCloud :moviename="moviename"></WordCloud>
                          </v-layout>
                          <v-card-text>{{ review }}</v-card-text>
                          <v-card-media>
                            <v-flex xs8></v-flex>
                            <v-flex xs4 class="pl-4">
                              <v-btn class="primary" > 수정 </v-btn>
                              <v-btn class="error"> 삭제 </v-btn>
                            </v-flex>
                          </v-card-media>
                        </v-card>
                      </v-dialog>
                    </p>
                </v-card-text>

              </v-flex>
            </td>
          </tr>
        </table>
      </v-layout>
    </v-card>
  </v-flex>

</template>

<script>
import SmallMoviePoster from "./SmallMoviePoster.vue"
import WordCloud from "./WordCloud.vue"
export default {
  name: "MyReviewCard",
  props: ['moviename'],
  components: {
    SmallMoviePoster,
    WordCloud
  },
  data: function() {
    return {
      review: "리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용리뷰내용...",
      selection: [],
      myReviewDetail: false
    }
  },
  methods: {
    selected() {
      this.$eventBus.$emit('selected', this.moviename)
      console.log("보낸 제목:" + this.moviename)
    }
  }
}
</script>

<style lang="css">
</style>
