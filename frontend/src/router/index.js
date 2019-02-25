import Vue from 'vue'
import Router from 'vue-router'
import Employees from '../views/general/Employees'
import Employee from '../views/general/Employee'
import SignIn from '../views/auth/SignIn'
import SignUp from '../views/auth/SignUp'
import UserMe from '../views/user/UserMe'

Vue.use(Router)

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'employees',
      component: Employees,
    },
    {
      path: '/employee/:id',
      name: 'employee',
      component: Employee
    },
    {
      path: '/sign-in',
      name: 'sign-in',
      component: SignIn
    },
    {
      path: '/sign-up',
      name: 'sign-up',
      component: SignUp
    },
    {
      path: '/user/me',
      name: 'user-me',
      component: UserMe
    }
  ]
})
