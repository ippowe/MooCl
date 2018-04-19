<template>
  <div>
    <img src="../assets/logo.png">
    <div id="joinform">
      <table>
        <tr>
          <td>
            <input type="text" v-model="nickname" placeholder="닉네임">
          </td>
        </tr>
        <div class="validateNickname">
          <span v-if="nicknameWarn == 1"></span>
          <span v-else-if="nicknameWarn == 2">너무 짧거나 특수문자가 포함되어 있습니다.</span>
          <span v-else-if="nicknameWarn == 99">닉네임을 작성해주세요.</span>
        </div>
        <tr>
          <td>
            <input type="text" v-model="email" placeholder="이메일">
            <button @click='checkEmail'>중복확인</button>
          </td>
        </tr>
        <div class="validateEmail">
          <span v-if="emailWarn == 1"></span>
          <span v-else-if="emailWarn == 2">이메일 형식에 맞지않습니다.</span>
          <span v-else-if="emailWarn == 3">이메일 중복검사를 해주세요</span>
          <span v-else-if="emailWarn == 99">이메일을 작성해주세요.</span>
        </div>
        <tr v-show="!results.checkEamilUnique">사용중인 이메일 입니다.</tr>
        <tr>
          <td>
            <input type="password" v-model="password" placeholder="비밀번호">
          </td>
        </tr>
        <div class="validatePassword">
          <span v-if="passWarn == 1"></span>
          <span v-else-if="passWarn == 2">비밀번호 안정성 : 취약(비밀번호가 너무 짧습니다.)</span>
          <span v-else-if="passWarn == 3">비밀번호 안정성 : 취약(숫자로만 이루어져 있습니다.)</span>
          <span v-else-if="passWarn == 41">비밀번호 안정성 : 보통(문자가 포함되어 있지 않습니다.)</span>
          <span v-else-if="passWarn == 42">비밀번호 안정성 : 보통(특수문자가 포함되어 있지 않습니다.)</span>
          <span v-else-if="passWarn == 5">비밀번호 안정성 : 안전</span>
          <span v-else-if="passWarn == 99">비밀번호를 작성해주세요</span>
        </div>
        <tr>
          <td>
            <input type="password" v-model="checkPass" placeholder="비밀번호 확인">
          </td>
        </tr>
          <span v-show="!results.checkPassAggrement">비밀번호가 일치하지 않습니다.</span>
        <tr>
          <td>
            <input type="radio" v-model="gender"value="M">남자
            <input type="radio" v-model="gender"value="F">여자
          </td>
        </tr>
        <span v-if="genderWarn == 99">성별을 선택해주세요</span>
        <tr>
          <td>
            <select v-model="age">
              <option value= "" selected>나이</option>
              <option v-for="year in ages" :value ="year">{{ year }}</option>
            </select>
          </td>
        </tr>
        <span v-if="ageWarn == 99">나이를 선택해주세요</span>
        <tr>
          <td><button @click='join'>회원가입</button></td>
        </tr>
        <div class="validateJoin">
          <span v-if="joinWarn == 1"></span>
          <span v-else-if="joinWarn == 2">닉네임을 확인해주세요</span>
          <span v-else-if="joinWarn == 3">이메일을 확인해주세요</span>
          <span v-else-if="joinWarn == 4">비밀번호를 확인해주세요</span>
          <span v-else-if="joinWarn == 5">성별을 확인해주세요</span>
          <span v-else-if="joinWarn == 6">나이를 확인해주세요</span>
        </div>
      </table>
    </div>

    <span>Result 출력</span>
    <xmp> {{ results }} </xmp>


    <span>Data 출력</span>
    <xmp> {{ $data }} </xmp>

  </div>
</template>

<script>
export default {
  name: 'JoinForm',
  data: function() {
    return {
      nickname: '', email: '', password: '', gender: '', age: '',
      checkPass : '',
      results :{   //문제 없으면 true로 반환
        checkEmailResult: false,
        checkEamilUnique: true,  //이메일 중복확인 결과
        emailChecking : false,  // 이메일 중복 검사 여부
        checkPassAggrement:true, //비밀번호, 비밀번호 확인 일치,불일치 결과
        checkPassResult: false,
        checkNickName: false,
        joinResult: false ,
      },
      passWarn : 0,
      emailWarn : 0,
      nicknameWarn: 0,
      genderWarn: 0,
      ageWarn: 0,
      joinWarn: 0,
      ages : Array.from(new Array(40),(val,index)=>index+1)
    }
  },
  watch: {
    //email 관리
    email : function(){
      this.joinWarn = 1;

      var emailTemplate = /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/

      if(this.email.match(emailTemplate) == null){
        this.emailWarn = 2; //이메일 형식에 안맞음
      } else if (this.results.emailChecking == false){
        this.emailWarn = 3; //이메일 중복검사 안함
      }
    },

    //nickname 관리
    nickname : function(){
      this.joinWarn = 1;

      var nicknameTemplate = /^[a-z0-9_가-힣ㄱ-ㅎㅏ-ㅣ]{4,20}$/

      if(this.nickname.match(nicknameTemplate)){
        this.nicknameWarn = 1;
        this.results.checkNickName = true
      } else {
        this.nicknameWarn = 2;
        this.results.checkNickName = false
      }
    },

    // 비밀번호 관리
    checkPass: function(){ //비밀번호 확인
      this.joinWarn = 1;
      if(this.checkPass !== ""){
        if( this.checkPass !== this.password){
            this.results.checkPassAggrement = false;
        } else {
            this.results.checkPassAggrement = true;
        }
      } else {
          this.results.checkPassAggrement = true;
      }
    },

    password : function(){  //비밀번호 형식 맞추기

      this.joinWarn = 1;

      var symbol = /\W/   //특수 문자
      var alphabet =/[a-zA-Z]/ //영어 대소문자

      if(this.password.length < 8){
        this.passWarn = 2;  //비밀번호가 너무 짧음
        this.results.checkPassResult = false;
      } else if (!(isNaN(this.password))){
        this.passWarn = 3;  //비밀번호에 숫자밖에 없음
        this.results.checkPassResult = false;
      } else if (this.password.match(symbol) != null && this.password.match(alphabet) == null){
        this.passWarn = 41; //비밀번호에 특수문자 포함 안됨
        this.results.checkPassResult = true;
      } else if (this.password.match(alphabet) != null && this.password.match(symbol) == null){
        this.passWarn = 42; //비밀번호에 문자 포함 안됨
        this.results.checkPassResult = true;
      } else if (this.password.match(symbol) != null && this.password.match(alphabet) != null){
        this.passWarn = 5;
        this.results.checkPassResult = true;
      }
    },

    gender : function() {

      this.joinWarn = 1;

      if(this.gender == ""){
        this.genderWarn = 99;
      } else {
        this.genderWarn = 1;
      }
    },

    age : function() {

      this.joinWarn = 1;

      if(this.age == ""){
        this.ageWarn = 99;
      } else {
        this.ageWarn = 1;
      }
    }
  },
  methods: {
    join: function(){
      if(this.results.checkNickName == false){
        this.joinWarn = 2; //닉네임 오류
      } else if (this.results.checkEmailResult == false ){
        this.joinWarn = 3; // 이메일 오류
      } else if (this.results.checkPassResult == false){
        this.joinWarn = 4; //비밀번호 오류
      } else if ( this.gender == ""){
        this.joinWarn = 5; //성별 오류
        this.genderWarn = 99;
      } else if ( this.age == ""){
        this.joinWarn = 6; //나이 오류
        this.ageWarn = 99;
      } else {
        this.$axios.post('/api/join', {nickname: this.nickname, email: this.email, password: this.password, gender: this.gender, age: this.age})
          .then((response) => {
            console.log(response)
            this.results.joinresult = response.data
          })
      }
    },
    checkEmail: function(){
      this.$axios.post('/api/checkEmail', {email: this.email})
        .then((response) => {
          console.log(response)
          this.results.checkEamilUnique = response.data
          this.results.emailChecking = response.data

          if(this.results.emailChecking && this.results.checkEamilUnique){
            this.emailWarn = 1;
            this.results.checkEmailResult= true
          }
        })
    },
  }
}
</script>

<style lang="css">
  #joinform { margin : auto ;}
  #joinform table { margin : auto; }
</style>
