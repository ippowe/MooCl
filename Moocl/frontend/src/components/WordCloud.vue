<template lang="html">
    <v-card id="demo" class="transparent" flat>


    </v-card>
</template>

<script>
export default {
  data: function() {
    return {
      foo: '농업생산성의 제고와 농지의 합리적인 이용을 위하거나 불가피한 사정으로 발생하는 농지의 임대차와 위탁경영은 법률이 정하는 바에 의하여 인정된다. 모든 국민은 종교의 자유를 가진다. 재판의 전심절차로서 행정심판을 할 수 있다. 행정심판의 절차는 법률로 정하되, 사법절차가 준용되어야 한다. 국가는 과학기술의 혁신과 정보 및 인력의 개발을 통하여 국민경제의 발전에 노력하여야 한다. 공개하지 아니한 회의내용의 공표에 관하여는 법률이 정하는 바에 의한다. 국가는 대외무역을 육성하며, 이를 규제·조정할 수 있다. 누구든지 병역의무의 이행으로 인하여 불이익한 처우를 받지 아니한다.농업생산성의 제고와 농지의 합리적인 이용을 위하거나 불가피한 사정으로 발생하는 농지의 임대차와 위탁경영은 법률이 정하는 바에 의하여 인정된다. 모든 국민은 종교의 자유를 가진다. 재판의 전심절차로서 행정심판을 할 수 있다. 행정심판의 절차는 법률로 정하되, 사법절차가 준용되어야 한다. 국가는 과학기술의 혁신과 정보 및 인력의 개발을 통하여 국민경제의 발전에 노력하여야 한다. 공개하지 아니한 회의내용의 공표에 관하여는 법률이 정하는 바에 의한다. 국가는 대외무역을 육성하며, 이를 규제·조정할 수 있다. 누구든지 병역의무의 이행으로 인하여 불이익한 처우를 받지 아니한다. 대통령은 법률이 정하는 바에 의하여 사면·감형 또는 복권을 명할 수 있다. 대통령은 법률이 정하는 바에 의하여 사면·감형 또는 복권을 명할 수 있다.농업생산성의 제고와 농지의 합리적인 이용을 위하거나 불가피한 사정으로 발생하는 농지의 임대차와 위탁경영은 법률이 정하는 바에 의하여 인정된다. 모든 국민은 종교의 자유를 가진다. 재판의 전심절차로서 행정심판을 할 수 있다. 행정심판의 절차는 법률로 정하되, 사법절차가 준용되어야 한다. 국가는 과학기술의 혁신과 정보 및 인력의 개발을 통하여 국민경제의 발전에 노력하여야 한다. 공개하지 아니한 회의내용의 공표에 관하여는 법률이 정하는 바에 의한다. 국가는 대외무역을 육성하며, 이를 규제·조정할 수 있다. 누구든지 병역의무의 이행으로 인하여 불이익한 처우를 받지 아니한다. 대통령은 법률이 정하는 바에 의하여 사면·감형 또는 복권을 명할 수 있다. '
    }
  },
  computed: {
    fooArray: function() {
      return this.foo.split(' ')
    },
    nameTag: function() {
      return new Set(this.fooArray)
    },
    countSet: function() {
      var temp = this.fooArray.reduce(function(x, y) {
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

    $(function() {
      // When DOM is ready, select the container element and call the jQCloud method, passing the array of words as the first argument.
      $("#demo").jQCloud(word_array, {
        height: 200,
        width : 600,
        colors : ["#F44336", "#9C27B0", "#3F51B5", "#03A9F4", "#009688", "#64DD17", "#795548"]
      });
    });
  }
}
</script>

<style lang="css">
</style>
