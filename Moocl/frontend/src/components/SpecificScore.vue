<template lang="html">
<v-layout>
  <v-card height=200 flat class="transparent mt-4">
    <v-tooltip v-for="data in genderscore" :key="data.key" bottom :color="data.color">
      <v-progress-circular slot="activator" :value="data.value" :color="data.color" size="120" :rotate="-90" :width="20" class="mx-3">
        <v-card flat class="transparent">
          <span class="subheading" style="font-weight: bold;">{{data.gender}}</span>
          <table>
            <tr>
              <td>
                <v-icon color="red">star</v-icon>
              </td>
              <td class="subheading">
                <strong>{{data.rate}}</strong>
              </td>
            </tr>
          </table>
        </v-card>
      </v-progress-circular>
      <span><v-icon>person</v-icon> {{data.value}}%</span>
    </v-tooltip>
    <v-card flat class="transparent">
      <v-list class="transparent">
        <v-list-tile avatar v-for="inform in agescore" :key="inform.key * 10">
          <v-list-tile-action>
            <v-avatar size="40"><strong>{{inform.age}}대</strong></v-avatar>
          </v-list-tile-action>
          <v-list-tile-avatar size="150">
            <v-progress-linear height="20" :value="inform.percent" :color="inform.color"></v-progress-linear>
          </v-list-tile-avatar>
          <v-list-tile class="ml-2">
            <v-avatar size=20><v-icon color="red">star</v-icon></v-avatar>
            <v-list-tile class="ml-0" >
              <v-avatar><strong>{{inform.score}}</strong></v-avatar>
            </v-list-tile>
          </v-list-tile>

        </v-list-tile>
      </v-list>
    </v-card>

  </v-card>

</v-layout>

</template>

<script>

export default {
  data : function(){
    return{
      genderscore : [
        {
          key : "m",
          value : 60,
          color : "light-blue",
          rate : 7.38,
          gender: "남자",
        },
        {
          key : "f",
          value : 40,
          color : "pink",
          rate : 6.45,
          gender : "여자",
        }
      ],
      agescore : [
        {
          key : 1,
          age : 10,
          percent : 20,
          score : 7.03,
          color : "green ligthen-4"
        },
        {
          key : 2,
          age : 20,
          percent : 30,
          score : 8.77,
          color : "blue ligthen-4"
        },
        {
          key : 3,
          age : 30,
          percent : 30,
          score : 5.03,
          color : "purple ligthen-4"
        },
        {
          key : 4,
          age : 40,
          percent : 20,
          score : 6.03,
          color : "yellow ligthen-4"
        },
      ]
    }
  }
}
</script>

<style lang="css">
</style>
