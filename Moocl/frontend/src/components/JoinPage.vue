<template>
  <!-- 나이를 입력안해도 진행되는거 수정필요-->
    <v-container fill-height>
      <v-layout align-center>
        <v-flex xs4 offset-xs4 class="pt-0 pb-5">
          <router-link :to="{ name: 'MainPage' }"><v-avatar size="300"><img src="../assets/moocl_logo.jpg" style=""></v-avatar></router-link>
          <v-form v-model="valid" ref="form" lazy-validation>
            <v-text-field label="Nickname" v-model="nickname" :rules="nicknameRules" :counter="4" required></v-text-field>
            <v-layout class="ma-0" row>
              <v-text-field ref= "email" label="E-mail" v-model="email" :rules="emailRules" required></v-text-field>
              <v-btn outline @click="checkEmail" class="ma-0">중복확인</v-btn> <br/>
            </v-layout>
            <v-alert :type="emailMsgType " :value="emailValidShow" transition="scale-transition" outline @click="emailValidShow = false">
              {{ validEmailMsg }}
            </v-alert>
            <v-text-field label="Password" v-model="password" :rules="passwordRules" :append-icon="e1 ? 'visibility' : 'visibility_off'" :append-icon-cb="() => (e1 = !e1)" :type="e1 ? 'password' : 'text'":counter="8" required></v-text-field>
            <v-text-field label="Password Confrim" v-model="validPassword" :rules="passwordConfirmRule" :append-icon="e2 ? 'visibility' : 'visibility_off'" :append-icon-cb="() => (e2 = !e2)" :type="e2 ? 'password' : 'text'" required></v-text-field>
            <v-radio-group label="Gender" v-model="gender" :rules="genderRules" row>
              <v-radio label="남자" value="M" ></v-radio>
              <v-radio label="여자" value="F"></v-radio>
            </v-radio-group>
            <v-select :items="ages" item-text="birthYear" item-value="age" v-model="age" label="Age" single-line :rules="ageRules" required auto> </v-select>
            <v-btn @click="submit" :disabled="!valid"> submit </v-btn>
            <v-alert type="error" :value="errorMsgShow" transition="scale-transition" outline @click="errorMsgShow = false">
              {{ errorMsg }}
            </v-alert>
          </v-form>
        </v-flex>
      </v-layout>
      <v-dialog max-width="400" v-model="joinResult">
        <v-card>
          <v-card-title class="headline">회원가입을 축하합니다!!</v-card-title>
          <v-card-actions>
            <v-spacer></v-spacer>
           <v-btn color="blue-grey darken-3" flat="flat" @click.native="dialog = false" to="login">확인</v-btn>
         </v-card-actions>
        </v-card>
      </v-dialog>
    </v-container>
</template>


<script>
export default {
  name: 'JoinPage',
  created () {
    this.$eventBus.$emit('notMainPage');
  },
  data: () => ({
       valid: false, //Submit 가능 불가능
       e1 : true,    //비밀번호 보기
       e2 : true,    //비밀번호확인 보기
       //닉네임 관련 변수
       nickname: '',
       nicknameRules: [
         v => !!v || '닉네임을 입력해주세요',
         v => /[a-z_A-Z0-9_가-힣ㄱ-ㅎㅏ-ㅣ]{3,20}$/.test(v) || '닉네임이 너무 짧습니다.',
         v => /^[a-z0-9_가-힣ㄱ-ㅎㅏ-ㅣ]/.test(v) || '특수문자는 사용할수 없습니다.'
       ],
       //이메일 관련 변수
       email: '',
       emailRules: [
         v => !!v || 'Email을 입력해주세요',
         v => /^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/.test(v) || '정확하지 않은 Email입니다.'
       ],
       validEmailMsg: "",
       emailValidShow: false, //이메일 중복확인 메세지 출력여부
       emailMsgType: "info",
       emailValid: false, //이메일 중복 체크 여부
       //비밀번호 관련 변수
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
       //성별 관련 변수
       gender: '',
       genderRules: [
         v =>  !!v || '성별을 선택해 주세요.'
       ],
       //나이 관련 변수
       age: 0,
       ageRules: [
         v => !!v || '나이를 입력해 주세요.'
       ],
       //최종 확인 관련
       errorMsg: "",
       errorMsgShow: false,
       joinResult: false

     }),
   computed : {
     ages : function() {
       let temp_ages = []
       let min_year = 1930;
       let current_year = new Date().getFullYear();
       let birth ="";
       let temp_age = 0;
       let temp_option = {};

       for ( let i = current_year; i>min_year-1; i--){
         birth = i + "년생";
         temp_age = current_year-i+1;

         temp_option["birthYear"] = birth
         temp_option["age"] = temp_age

         temp_ages.push(temp_option)

         temp_option = {}
       }
       return temp_ages
     }
   },
  methods: {
    submit () {
      if(this.emailValid) {
        if (this.$refs.form.validate()) {
          this.$axios.post('/api/join', {
            nickname: this.nickname,
            email: this.email,
            password: this.password,
            gender: this.gender,
            age: this.age
          }).then((result) => {
            this.joinResult = true
          }).catch((error) => {
            this.errorMsg= error
            this.errorMsgShow=true
          })
        }
      } else {
        this.errorMsg="이메일 중복 체크를 해주세요"
        this.errorMsgShow=true
      }
    },
    checkEmail() {
      this.validEmailMsg="사용 가능한 이메일 입니다."
      if(this.$refs.email.validate()){
        this.$axios.post('/api/checkEmail', {
          email: document.forms[0][1]._value
        }).then((result) => {
          if(result.data){ // result == true : 이메일 중복 아님
            this.validEmailMsg="사용 가능한 이메일 입니다."
            this.emailValidShow=true
            this.emailMsgType="info";
            this.emailValid=true
            return result
          } else {
            this.validEmailMsg="이미 사용중인 이메일 입니다.";
            this.emailValidShow=true;
            this.emailMsgType="error";
            this.emailValid=false
            return result
          }
        })
      }

    }
  }
}
</script>


<style lang="css">
</style>
