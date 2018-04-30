import Vue from 'vue'
import Router from 'vue-router'
import JoinPage from '@/components/JoinPage.vue'
import LoginPage from '@/components/LoginPage.vue'
import MainPage from '@/components/MainPage.vue'
import TestPage from '@/components/test.vue' //마지막에 삭제해야 합니다.
import MyPage from '@/components/MyPage.vue'
import ModifyInfo from '@/components/ModifyInfo.vue'


Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/login',
      name: 'LoginPage',
      component: LoginPage
    },
    {
      path: '/join',
      name: 'JoinPage',
      component: JoinPage
    },
    {
      path: '/main',
      name: 'MainPage',
      component: MainPage
    },
    {
      path: '/mypage',
      name: 'MyPage',
      component: MyPage
    },{
      path: '/userinfo',
      name: 'ModifyInfo',
      component: ModifyInfo
    },
    {  //마지막에 삭제해야 합니다.
      path: '/test',
      name: 'TestPage',
      component: TestPage
    },
  ]
})
