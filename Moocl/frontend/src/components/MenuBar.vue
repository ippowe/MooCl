<template>
  <!-- 로그인 로그아웃에 따른 메뉴 내용 변경 -->
  <!-- 로그(side-icon) 눌렀을 때 홈으로 이동 -->
  <!-- 검색 기능 구현 -->
    <v-toolbar dark color="deep-purple" class="toolbar--fixed">
      <v-toolbar-side-icon><img src="../assets/logo.png" style="height: 28px"></v-toolbar-side-icon>
      <v-spacer></v-spacer>
      <v-text-field solo-inverted label="Search" append-icon="search"></v-text-field>
      <v-spacer></v-spacer>
        <v-tab v-if="loginChecker == false">
          <strong color="text--white" >로그인</strong>
        </v-tab>
        <v-tab v-if="loginChecker == true">
          <strong color="text--white" >로그아웃</strong>
        </v-tab>
        <v-tab v-if="loginChecker == true">
          <strong color="text--white" >마이페이지</strong>
        </v-tab>
    </v-toolbar>
</template>

<script>
export default {
  name : "MenuBar",
  data : () => {
    return {
      loginChecker: true
    }
  }
}
</script>

<style lang="css">

</style>
