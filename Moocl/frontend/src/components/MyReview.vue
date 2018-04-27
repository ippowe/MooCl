<template lang="html">
  <v-container>
    <v-layout class=" my-0">
      <v-flex class="ml-5 pl-5">
        <ReviewCard moviename="메이즈러너"></ReviewCard>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import ReviewCard from "./ReviewCard.vue"
export default {
  name : "MyReview",
  components : {
    ReviewCard
  }
}
</script>

<style lang="css">
</style>
