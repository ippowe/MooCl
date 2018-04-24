import Vue from 'vue'
import Router from 'vue-router'
import JoinForm from '@/components/JoinForm'
import LoginForm from '@/components/LoginForm'
import MainPage from '@/components/MainPage'
import TestPage from '@/components/test.vue'


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
      path: '/test',
      name: 'TestPage',
      component: TestPage
    },
  ]
})
