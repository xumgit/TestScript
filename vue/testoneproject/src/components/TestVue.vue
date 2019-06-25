<template>
  <div class="test">
    <h1>{{msg}}</h1>
    <h2>{{message | capitalize}}</h2>
    <button v-on:click="reverseStr">Reverse Message</button>
    <div v-if="type === 'A'">
      A
    </div>
    <div v-else-if="type === 'B'">
      B
    </div>
    <div v-else-if="type === 'C'">
      C
    </div>
    <div v-else>
      Not A/B/C
    </div>
    <div>
      <ul>
        <li v-for="(value, key, index) in object" v-bind:key="key">
          {{index}}-{{key}}-{{value}}
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  name: 'TestVue',
  data () {
    return {
      msg: 'Test Vue Page',
      message: 'abcde',
      seen: true,
      type: 'B',
      object: {
        name: 'ABC_Name',
        url: 'DEF_Url',
        slogan: 'Language_slogan'
      }
    }
  },
  methods: {
    reverseStr: function () {
      this.message = this.message.split('').reverse()
    }
  },
  filters: {
    capitalize: function (value) {
      if (!value) return ''
      value = value.toString()
      return value.charAt(0).toUpperCase() + value.slice(1)
    }
  }
}
</script>
>
