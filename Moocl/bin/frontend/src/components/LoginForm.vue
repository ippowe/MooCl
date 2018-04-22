<template>
  <v-app>
    <v-container grid-list-xl>
      <img src="../assets/logo.png">
      <v-layout>
        <v-flex xl4 offset-xl4>
          <v-form v-model="valid" ref="form" lazy-validation>
            <v-text-field label="E-mail" v-model="email" :rules="emailRules"  required></v-text-field>
            <v-text-field label="Password" v-model="password" :rules="passwordRules" :append-icon="e1 ? 'visibility' : 'visibility_off'" :append-icon-cb="() => (e1 = !e1)" :type="e1 ? 'password' : 'text'":counter="8" required></v-text-field>
            <v-btn @click="submit" :disabled="!valid"> submit </v-btn>
            <v-btn @click="submit"> 회원가입 </v-btn>
          </v-form>
        </v-flex>
      </v-layout>
    </v-container>
  </v-app>
</template>

<script>


export default {
  name: 'LoginForm',
  created () {
    this.$eventBus.$emit('notMainPage');
  },
  data: () => ({
       valid: false,
       password: '',
       passwordRules: [
         v => !!v || '비밀번호를 입력해주세요',
         v => v.length >= 8 || '비밀번호는 8자리 이상이어야합니다.'
       ],
       email: '',
       emailRules: [
         v => !!v || 'Email을 입력해주세요',
         v => /^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/.test(v) || '정확하지 않은 Email입니다.'
       ],
       e1 : true

     }),
  methods: {
    submit () {
      if (this.$refs.form.validate()) {
        // Native form submission is not yet supported
        this.$axios.post('/api/login', {
          name: this.name,
          email: this.email,
        })
      }
    }
  }
}
</script>

<style lang="css">
  #loginForm { margin : auto ;}
  #loginForm table { margin : auto; }
  #searchIdPass { font-size: 13px }
</style>
