<template>
  <div id="app">
    <img src="./assets/logo.png">
    <div>
      <router-link to="/">Go to Navigation</router-link> &nbsp;&nbsp;
      <router-link to="/root">Go to Root</router-link> &nbsp;&nbsp;
      <router-link to="/testVue">Go to Test</router-link> &nbsp;&nbsp;
      <router-link to="/listGrid">Go to ListGrid</router-link> &nbsp;&nbsp;
      <router-link to="/searchStore">Go to SearchStore</router-link> &nbsp;&nbsp;
      <!-- resultUrl: /register?plan=private -->
      <!--<router-link :to="{ path: 'register', query: { plan: 'private' }}">Register</router-link>-->
    </div>
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
