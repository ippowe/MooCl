<template>
    <v-container fill-height>
      <v-layout align-center justify-center>
        <v-flex xs4 class="pt-0 mt-0 mb-5 pb-5">
          <router-link :to="{ name: 'MainPage' }"><v-avatar size="300"><img src="../assets/moocl_logo.jpg" style=""></v-avatar></router-link>
          <v-form v-model="valid" ref="form" lazy-validation>
            <v-text-field label="E-mail" v-model="email" :rules="emailRules"  required></v-text-field>
            <v-text-field label="Password" v-model="password" :rules="passwordRules" :append-icon="e1 ? 'visibility' : 'visibility_off'" :append-icon-cb="() => (e1 = !e1)" :type="e1 ? 'password' : 'text'":counter="8" required></v-text-field>
            <span class="red--text subheading"><strong>{{msg}}</strong></span> <br>
            <v-btn @click="submit" :disabled="!valid"> submit </v-btn>
            <v-btn to="join"> 회원가입 </v-btn>
          </v-form>
        </v-flex>
      </v-layout>
    </v-container>
</template>

<script>


export default {
  name: 'LoginPage',
  created () {
    this.$eventBus.$emit('notMainPage');
  },
  data: () => ({
       valid: false,
       password: '',
       passwordRules: [
         v => !!v || '비밀번호를 입력해주세요',
         // v => v.length >= 8 || '비밀번호는 8자리 이상이어야합니다.'
       ],
       email: '',
       emailRules: [
         v => !!v || 'Email을 입력해주세요',
         v => /^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/.test(v) || '정확하지 않은 Email입니다.'
       ],
       e1 : true,
       msg: ""
     }),
  methods: {
    submit () {
      var email = this.email
      var password = this.password
      this.$store.dispatch('LOGIN', {email, password})  //vuex의 LOGIN actions 실행
        .then((response) => {
          console.log(response)
          if(response.token){
            this.$router.push('/main')

          } else {
            this.msg = "아이디 또는 비밀번호가 틀렸습니다."
          }
        })
        .catch(({message}) => console.log(message))
    }
  }
}
</script>

<style lang="css">
</style>
