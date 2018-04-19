<template>
  <div>
    <img src="../assets/logo.png">
    <div id="loginForm">
      <table>
        <tr>
          <td>
            <input type="text" v-model="user.email" placeholder="이메일"/>
          </td>
        </tr>
        <tr>
          <td>
            <input type="password" v-model="user.password" placeholder="PASSWORD" />
          </td>
        </tr>
        <tr>
          <button id="loginButton" @click="login">로그인</button>
        </tr>
        <tr>
          <td id="searchIdPass"> 이메일/비밀번호 찾기 </td>
        </tr>
      </table>

      <button id="loginButton">회원가입</button>
    </div>

    <span> JSON 출력 </span>
    <div id="result">
      <xmp> {{ result }} </xmp>
    </div>

    <span> data 출력 </span>
    <div id="result">
      <xmp> {{ $data }} </xmp>
    </div>

  </div>
</template>

<script>


export default {
  name: 'LoginForm',
  data () {
    return {
      user: {email: '', password: ''},
      result: null
    }
  },
  methods: {
    // JSON형태로 RequestBody에 들어가서 맵자료형으로 받아야 한다.
    login () {
      console.log(this.user);
      this.$axios.post('/api/login', {email: this.user['email'], password: this.user['password']})
        .then((response) => {
          console.log(response)
          this.result = response.data
        })
    }
  }
}
</script>

<style lang="css">
  #loginForm { margin : auto ;}
  #loginForm table { margin : auto; }
  #searchIdPass { font-size: 13px }
</style>
