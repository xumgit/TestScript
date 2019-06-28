import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import TestVue from '@/components/TestVue'
import ListGrid from '@/components/ListGrid'
import SearchStore from '@/components/SearchStore'

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
    },
    {
      path: '/listGrid',
      name: 'ListGrid',
      component: ListGrid
    },
    {
      path: '/searchStore',
      name: 'SearchStore',
      component: SearchStore
    }
  ]
})
