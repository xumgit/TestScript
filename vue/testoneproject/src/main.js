// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'

Vue.config.productionTip = false

Vue.component('runoob', {
  props: ['message'],
  template: '<h1>Customer-{{message}}</h1>'
})

Vue.component('IpItems', {
  props: ['item'],
  template: '<div class="hightlight">{{item.name}}</div>'
})

Vue.component('buttonClick', {
  template: '<div>{{count}}<button v-on:click="plus">+</button><button v-on:click="minus">-</button></div>',
  data: function () {
    return {
      count: 0
    }
  },
  methods: {
    plus: function () {
      this.count += 1
      this.$emit('plusChangeValue')
    },
    minus: function () {
      if (this.count > 0) {
        this.count -= 1
      }
    }
  }
})

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
