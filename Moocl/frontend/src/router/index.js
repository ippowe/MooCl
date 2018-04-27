import Vue from 'vue'
import Router from 'vue-router'
import JoinForm from '@/components/JoinForm'
import LoginForm from '@/components/LoginForm'
import MainPage from '@/components/MainPage'
import TestPage from '@/components/test.vue' //마지막에 삭제해야 합니다.
import MyPage from '@/components/MyPage.vue'


Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/login',
      name: 'LoginForm',
      component: LoginForm
    },
    {
      path: '/join',
      name: 'JoinForm',
      component: JoinForm
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
    },
    {  //마지막에 삭제해야 합니다.
      path: '/test',
      name: 'TestPage',
      component: TestPage
    },
  ]
})
