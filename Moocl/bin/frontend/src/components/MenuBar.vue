<template lang="html">
    <header id="fixed-menu">
      <img id="logoImg" src="../assets/logo.png">
      <input type="text" placeholder="검색어를 입력하세요" id="searchbar">
      <div class="HamberMenu">
        <span>로그인 마이페이지</span>
      </div>
      <hr/>
    </header>
</template>

<script>
export default {
}
</script>

<style lang="css">
#fixed-menu {  width: 100%; background-color: #ffffff; position: fixed; top: 0px; left: 0px;}
#logoImg {float: left; height: 30px; margin:auto;}
#searchbar { height: 20px; width: 300px; }
.HamberMenu {float: right;}
</style>
