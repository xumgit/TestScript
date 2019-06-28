<template>
  <div id="appT">
    <button v-on:click="getAllData">getData</button>
    <h1 v-html = "title"></h1>
    <input v-model="newItem" v-on:keyup.enter="addNew" />
    <ul>
      <li v-for="item in items"
          v-bind:class="{finished: item.isFinished}"
          v-on:click="toggleFinish(item)"
          v-bind:key="item.label">{{item.label}}</li>
    </ul>
  </div>

</template>

<script>
import Store from '../store'

export default {
  name: 'SearchStore',
  created () {
    console.log('constructor')
  },
  data: function(){
    return {
      title: "This is a Todolist",
      items: Store.fetch(),
      newItem: ""
    }
  },
  watch: {
    items: {
      handler: function(items) {
        Store.save(items)
      },
      deep: true
    }
  },
  methods: {
    toggleFinish: function (item) {
      item.isFinished = !item.isFinished
    },
    addNew: function () {
      console.log('addNew method')
      this.items.push({
        label: this.newItem,
        "isFinished": false
      })
      this.newItem = ""
    },
    getDataFromRemote: function () {
      const options = {
        method: 'GET',
        headers: {'X-Requested-With': 'XMLHttpRequest'},
        //data: qs.stringify(data),
        params: {
          ID: 12345
        },
        data: {
          firstName: 'Fred'
        },
        url: 'http://localhost:8080/static/test/test.json',
        timeout: 5000,
        responseType: 'json',
        responseEncoding: 'utf8',
        onUploadProgress: function (progressEvent) {
          console.log('onUploadProgress')
        },
        onDownloadProgress: function (progressEvent) {
          console.log('onDownloadProgress')
        },
        transformResponse: [function (data) {
          console.log('transformResponse')
          return data;
        }]
      };
      this.$axios(options)
        .then((response) => (
          console.log(response.data.dependencies)
        ))
        .catch(function (error) {
          console.log(error);
        })
    },
    getDataFromAnother: function () {
      this.$axios.get('http://localhost:8080/static/test/test.json')
        .then((response) => (
          console.log(response.data.version)
        ))
        .catch(function (error) {
          console.log(error);
        })
    },
    getAllData: function () {
      //this.getDataFromRemote()
      //this.getDataFromAnother()
      this.$axios.all([this.getDataFromRemote(), this.getDataFromAnother()])
          .then(this.$axios.spread(function () {
            console.log('all send success')
      }))
    }
  }
}
</script>

<style>
.finished{
  text-decoration:line-through;
}
li{
  list-style:none;
  font-size:1.6em;
  margin-top:10px;
}

#appT {
  background-image:url(../assets/test.jpg);
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

input{
  width:230px;
  height:40px;
  border-radius:20px;
  padding: 0.4em 0.35em;
  border:3px solid #CFCFCF;
  font-size: 1.55em;
}
</style>
