<template lang="html">
  <v-card>
    <v-card-title class="headline">
      로그인이 필요합니다.
    </v-card-title>
    <v-card-media>
      <v-card-text></v-card-text>
      <v-btn class="primary" to="/login">로그인</v-btn>
    </v-card-media>
  </v-card>
</template>

<script>
export default {
  name : "NeedLogin",
  data : function() {
    return {

    }
  }
}
</script>

<style lang="css">
</style>
