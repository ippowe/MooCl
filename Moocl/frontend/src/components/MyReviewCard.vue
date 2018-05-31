<template lang="html">
  <v-flex class="pa-0 ma-0">
    <v-card width="400px" flat>
      <v-layout align-start>
        <v-checkbox @click.native="selected" v-model="selection" style="width: 30px" class="mr-3" :ripple="false" color="grey lighten-1"> </v-checkbox>
        <table>
          <tr>
              <v-flex class="pa-1">
                <SmallMoviePoster :posterUrl="reviewInfo._id.movie_ref.poster" :movieid="reviewInfo.movie_id" ></SmallMoviePoster>
              </v-flex>
            <td style="vertical-align: top;">
              <v-flex class="pa-1">
                <v-card-text class="pa-0">
                  <v-card-title primary-title class="pa-0 title">
                    <strong>{{ reviewInfo._id.movie_ref.movie_title }}</strong>
                  </v-card-title>
                  <v-card-media class="py-2 subheading text-xs-left">
                    {{ reviewInfo._id.movie_ref.genre }}
                  </v-card-media>
                    <p style="text-align: left;" class="py-2" >
                      {{ trimedReview }}
                      <span @click="myReviewDetail = true" style="cursor: pointer;" v-if=" reviewInfo.review_contents.length > 100">
                        <strong>더보기</strong>
                      </span>
                      <v-dialog v-model="myReviewDetail" max-width="800px" v-if=" reviewInfo.review_contents.length > 100">
                        <v-card class="pa-3">
                          <v-layout>
                            <SmallMoviePoster :posterUrl="reviewInfo._id.movie_ref.poster" :movieid="reviewInfo.movie_id"></SmallMoviePoster>
                            <WordCloud :moviename="reviewInfo.movie_id"></WordCloud>
                          </v-layout>
                          <v-card-text>{{ reviewInfo.review_contents }}</v-card-text>
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
  props: ['reviewInfo'],
  components: {
    SmallMoviePoster,
    WordCloud
  },
  data: function() {
    return {
      selection: [],
      myReviewDetail: false
    }
  },
  updated() {
    this.$eventBus.$on('reSelection', () => {
      this.selection = []
    })
  },
  methods: {
    selected() {
      this.$eventBus.$emit('selected', this.reviewInfo)
    }
  },
  computed: {
    trimedReview () {
      let temp_content = ""
      if(this.reviewInfo.review_contents.length > 100){
        temp_content = this.reviewInfo.review_contents.substring(0, 101);
      } else {
        temp_content = this.reviewInfo.review_contents
      }
      return temp_content
    }
  }
}
</script>

<style lang="css">
</style>
