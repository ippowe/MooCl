<template>
  <!-- 나이를 입력안해도 진행되는거 수정해야됨 -->
  <v-app>
    <v-container grid-list-xl>
      <img src="../assets/logo.png" style="">
      <v-layout>
        <v-flex xl4 offset-xl4>
          <v-form v-model="valid" ref="form" lazy-validation>
            <v-text-field label="Nickname" v-model="nickname" :rules="nicknameRules" :counter="4" required></v-text-field>
            <v-layout class="ma-0" row>
              <v-text-field ref= "email" label="E-mail" v-model="email" :rules="emailRules" required></v-text-field>
              <v-btn outline @click="checkEmail" class="ma-0">중복확인</v-btn>
            </v-layout>
            <v-text-field label="Password" v-model="password" :rules="passwordRules" :append-icon="e1 ? 'visibility' : 'visibility_off'" :append-icon-cb="() => (e1 = !e1)" :type="e1 ? 'password' : 'text'":counter="8" required></v-text-field>
            <v-text-field label="Password Confrim" v-model="validPassword" :rules="passwordConfirmRule" :append-icon="e2 ? 'visibility' : 'visibility_off'" :append-icon-cb="() => (e2 = !e2)" :type="e2 ? 'password' : 'text'" required></v-text-field>
            <v-radio-group v-model="gender" :rules="genderRules" row>
              <v-radio label="남자" value="M" ></v-radio>
              <v-radio label="여자" value="F"></v-radio>
            </v-radio-group>
            <v-slider label="Age" min="0" max="60" thumb-label v-model="age" :value="age" :rules="ageRules"></v-slider>
            <v-btn @click="submit" :disabled="!valid"> submit </v-btn>
          </v-form>
        </v-flex>
      </v-layout>
    </v-container>
  </v-app>
</template>


<script>
export default {
  name: 'JoinForm',
  created () {
    this.$eventBus.$emit('notMainPage');
  },
  data: () => ({
       valid: false, //Submit 가능 불가능
       e1 : true,    //비밀번호 보기
       e2 : true,    //비밀번호확인 보기
       nickname: '',
       nicknameRules: [
         v => !!v || '닉네임을 입력해주세요',
         v => v.length >= 4 || '닉네임이 너무 짧습니다.',
         v => /^[a-z0-9_가-힣ㄱ-ㅎㅏ-ㅣ]{4,20}$/.test(v) || '특수문자는 사용할수 없습니다.'
       ],
       email: '',
       emailRules: [
         v => !!v || 'Email을 입력해주세요',
         v => /^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/.test(v) || '정확하지 않은 Email입니다.'
       ],
       password: '',
       passwordRules: [
         v => !!v || '비밀번호를 입력해주세요',
         v => v.length >= 8 || '비밀번호는 8자리 이상이어야합니다.',
         v => /\W/.test(v) || '특수문자가 하나이상 포함되어야합니다.',
         v => /[a-zA-Z]/.test(v) || '문자가 하나이상 포함되어야합니다.'
       ],
       validPassword: '',
       passwordConfirmRule: [
         v => !!v || '비밀번호 확인',
         v => v == document.forms[0][3]._value || '비밀번호가 다릅니다.'
       ],
       gender: '',
       genderRules: [
         v =>  !!v || '성별을 선택해 주세요.'
       ],
       age: 0,


     }),
  methods: {
    submit () {
      if (this.$refs.form.validate()) {
        // Native form submission is not yet supported
        this.$axios.post('/api/login', {
          nickname: this.nickname,
          email: this.email,
          password: this.password,
          gender: this.gender,
          age: this.age
        })
      }
    },
    checkEmail() {
      if(this.$refs.email.validate()){
        this.$axios.post('/api/emailChecking', {
          email: document.forms[0][1]._value
        })
      }

    }
  }
}
</script>


<style lang="css">
  #joinform { margin : auto ;}
  #joinform table { margin : auto; }
</style>
