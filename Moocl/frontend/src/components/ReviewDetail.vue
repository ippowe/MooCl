<template lang="html">
<v-dialog :max-width="800">
  <v-btn class="primary" slot="activator">open dialog</v-btn>
  <v-layout class="white pa-2" fill-height align-center>
    <table class="my-3 py-3" >
      <tr>
        <td class="title"><strong>{{ writer }}님의 클라우드</strong></td>
      </tr>
      <tr>
        <td :id="writer" class="noselect"></td>
      </tr>
    </table>
    <v-flex text-xs-left class="my-3 py-5 white black--text subheading" xs7>
      <td class="reviewarea pl-3">{{ review }}</td>
    </v-flex>
  </v-layout>
</v-dialog>

</template>

<script>
import WordCloud from "./WordCloud.vue"
export default {
  name: "ReviewDetail",
  components: {
    WordCloud
  },
  data : function() {
    return {
      writer: "작성자이름",
      review: "컴포넌트는 부모-자식 관계에서 가장 일반적으로 함께 사용하기 위한 것입니다. 컴포넌트 A는 자체 템플릿에서 컴포넌트 B를 사용할 수 있습니다. 그들은 필연적으로 서로 의사 소통이 필요합니다. 부모는 자식에게 데이터를 전달해야 할 수도 있으며, 자식은 자신에게 일어난 일을 부모에게 알릴 필요가 있습니다. 그러나 부모와 자식이 명확하게 정의된 인터페이스를 통해 가능한한 분리된 상태로 유지하는 것도 매우 중요합니다. 이렇게하면 각 컴포넌트의 코드를 상대적으로 격리 할 수 있도록 작성하고 추론할 수 있으므로 유지 관리가 쉽고 잠재적으로 쉽게 재사용 할 수 있습니다. 컴포넌트는 부모-자식 관계에서 가장 일반적으로 함께 사용하기 위한 것입니다. 컴포넌트 A는 자체 템플릿에서 컴포넌트 B를 사용할 수 있습니다. 그들은 필연적으로 서로 의사 소통이 필요합니다. 부모는 자식에게 데이터를 전달해야 할 수도 있으며, 자식은 자신에게 일어난 일을 부모에게 알릴 필요가 있습니다. 그러나 부모와 자식이 명확하게 정의된 인터페이스를 통해 가능한한 분리된 상태로 유지하는 것도 매우 중요합니다. 이렇게하면 각 컴포넌트의 코드를 상대적으로 격리 할 수 있도록 작성하고 추론할 수 있으므로 유지 관리가 쉽고 잠재적으로 쉽게 재사용 할 수 있습니다. 관계에서 관계에서 관계에서 관계에서 관계에서"
    }
  },
  computed: {
    reviewArray: function() {
      return this.review.split(' ')
    },
    nameTag: function() {
      return new Set(this.reviewArray)
    },
    countSet: function() {
      var temp = this.reviewArray.reduce(function(x, y) {
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
          weight: this.countSet[name],
          handlers : {
            mouseover : function(){
              console.log(this)
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
    var select_id = "#" + this.writer
    console.log(select_id)
    $(function() {
      // When DOM is ready, select the container element and call the jQCloud method, passing the array of words as the first argument.
      $(select_id).jQCloud(word_array, {
        height: 235,
        width : 288,
        autoResize: true,
        colors : ["#F44336", "#9C27B0", "#3F51B5", "#03A9F4", "#009688", "#64DD17", "#795548"]
      });
    });
  }
}
</script>

<style lang="css">
.noselect { -webkit-touch-callout: none; -webkit-user-select: none; -khtml-user-select: none; -moz-user-select: none; -ms-user-select: none; user-select: none; cursor: pointer}
.reviewarea {border-left: 2px solid grey;}
</style>
