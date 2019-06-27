import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import TestVue from '@/components/TestVue'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/root',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path: '/testVue',
      name: 'TestVue',
      component: TestVue
    }
  ]
})
