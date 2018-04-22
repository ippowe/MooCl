<template lang="html">
  <v-stepper non-linear v-model="e1">
    <v-container row>
      <v-layout xs1 class="py-5 my-5">
        <v-btn flat large icon my-5><v-icon>skip_previous</v-icon></v-btn>
      </v-layout>
      <v-layout>
        <v-stepper-items xs10>
          <v-stepper-content step="1">
              <v-flex xs2>
                <v-card color="grey lighten-1" height="250px" width="200px">
                  <v-card-text>인물사진</v-card-text>
                </v-card>
              </v-flex>
                한효주 <br>
                주연
              <v-flex xs2>
                <v-card color="grey lighten-1" height="250px" width="200px">
                  <v-card-text>인물사진</v-card-text>
                </v-card>
              </v-flex>
              <v-flex xs2 >
                <v-card color="grey lighten-1" height="250px" width="200px">
                  <v-card-text>인물사진</v-card-text>
                </v-card>
              </v-flex>
          </v-stepper-content>
          <v-stepper-content step="2">
              <v-flex xs2>
                <v-card color="grey lighten-1" height="250px" width="200px">
                  <v-card-text>인물사진</v-card-text>
                </v-card>
              </v-flex>
              <v-flex xs2 >
                <v-card color="grey lighten-1" height="250px" width="200px">
                  <v-card-text>인물사진</v-card-text>
                </v-card>
              </v-flex>
              <v-flex xs2>
                <v-card color="grey lighten-1" height="250px" width="200px">
                  <v-card-text>인물사진</v-card-text>
                </v-card>
              </v-flex>
          </v-stepper-content>
        </v-stepper-items>
      </v-layout>

      <v-layout xs1 class="py-5 my-5">
        <v-btn flat large icon ><v-icon>skip_next</v-icon></v-btn>
      </v-layout>
    </v-container>

    <v-stepper-header>
      <v-spacer></v-spacer>
      <v-stepper-step editable step="1"></v-stepper-step>
      <v-stepper-step editable step="2"></v-stepper-step>
      <v-spacer></v-spacer>
    </v-stepper-header>
    </v-stepper>
</template>

<script>
export default {
  data () {
      return {
        e1: 0
      }
    }
}
</script>

<style lang="css">
</style>
