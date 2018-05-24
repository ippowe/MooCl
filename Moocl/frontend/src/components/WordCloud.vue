<template lang="html">
  <div>
    <v-card :id="moviename" class="transparent noselect" flat>
    </v-card>
  </div>

</template>

<script>
import MovieRecommend from "./MovieRecommend.vue"

export default {
  name: "WordCloud",
  props: ['moviename', 'cloudsize', 'cloudstring'],
  components : {
    MovieRecommend
  },
  created() {
    if(this.cloudstring == ''){
      this.str = '70대 인턴과 30대 사장의 자리 바꿈 말고는 딱 예상대로 전개된다. 그 편안함이 좋다. 성숙한 인생 선배의 지혜로운 조언이 성공한 젊은 여성의 불안을 다독인다. 처음부터 끝까지 그저 흐뭇한데, 그렇다고 지루한 것도 아니다. 단, 70대 인턴이 우연히 지혜로운 사람이었던 거지 나이가 지혜를 담보하진 않는다. 노인=지혜라는 공식에 기대지 않는단 말이다. 세대 간에 서로 모자란 걸 채워주는, 착하고 따뜻한 낭만 동화.';
    } else {
      let temp_str = this.cloudstring
      this.str = temp_str;
    }
  },
  data: function() {
    return {
      str: ''
    }
  },
  computed: {
    stringArray: function() {
      return this.str.split(' ');
    },
    nameTag: function() {
      return new Set(this.stringArray)
    },
    countSet: function() {
      var temp = this.stringArray.reduce(function(x, y) {
        x[y] = ++x[y] || 1;
        return x
      }, {})
      return temp
    },
    cloudData: function() {
      let tempArray = []
      for (name of this.nameTag) {
        var temp = {
          text: name,
          weight: this.countSet[name] * 10,
          handlers : {
            mouseover : function(){
            },
            click : function() {
              console.log("클릭 이벤트: " + this.innerHTML)
            }
          }
        }
        tempArray.push(temp)
      }
      return tempArray
    }
  },
  mounted() {
    var word_array = this.cloudData
    var select_id = "#" + this.moviename
    var cloud_width

    if(this.cloudsize == 1){
      cloud_width = 300;
    } else {
      cloud_width = 600;
    }

    $(function() {
      // When DOM is ready, select the container element and call the jQCloud method, passing the array of words as the first argument.
      $(select_id).jQCloud(word_array, {
        height: 200,
        width : cloud_width,
      });
    });
  }
}
</script>

<style lang="css">
.noselect { -webkit-touch-callout: none; -webkit-user-select: none; -khtml-user-select: none; -moz-user-select: none; -ms-user-select: none; user-select: none; cursor: pointer}
</style>
