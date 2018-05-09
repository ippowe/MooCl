<template>
  <!-- 로그인 로그아웃에 따른 메뉴 내용 변경 -->
  <!-- 로그(side-icon) 눌렀을 때 홈으로 이동 -->
  <!-- 검색 기능 구현 -->
    <v-toolbar dark color="deep-purple">
      <v-toolbar-side-icon to="main"><img src="../assets/logo.png" style="height: 28px"></v-toolbar-side-icon>
      <v-spacer></v-spacer>
      <v-text-field label="Search" append-icon="search" :append-icon-cb="search" v-model="keyword" solo-inverted></v-text-field>
      <v-spacer></v-spacer>
      <v-toolbar-items>
        <v-tabs color="transparent" hide-slider>
          <v-tab v-if="loginChecker == 'false'" to="login">
            <strong color="text--white" >로그인</strong>
          </v-tab>
          <v-tab v-if="loginChecker == 'true'" @click="logoutMethod">
            <strong color="text--white" >로그아웃</strong>
          </v-tab>
          <v-tab v-if="loginChecker == 'true'" to="mypage">
            <strong color="text--white" >마이페이지</strong>
          </v-tab>
        </v-tabs>
      </v-toolbar-items>

    </v-toolbar>

</template>

<script>
export default {
  name : "ToolBar",
  mounted() {
     if(sessionStorage.length != 0) {
         this.loginChecker = sessionStorage.token
     } else {
        this.loginChecker = 'false'
     }
  },
  watch : {
    keyword(val) {
      //자동 완성 만드는 함수
    }
  },
  data : () => {
    return {
      loginChecker: 'true',
      keyword: ""
    }
  },
  methods : {
    logoutMethod () {
      this.$store.dispatch('LOGOUT')
      .then(() => this.$router.push('login'))
    },
    search () {
      let modifyKeyword = "";

      this.$axios.get('/api/search', {
        params : {
          keyword : this.keyword
        }
      })
      .then((result) => {
        let dataArray = [];
        dataArray = result.data;
        this.$eventBus.$emit('fowardResult', dataArray);
      })
      .catch((error) => {
        console.log(error)
      })
    }
  }
}
</script>

<style lang="css">

</style>
