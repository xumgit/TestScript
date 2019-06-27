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
    <div>
      kilometers: <input type = "text" v-model = "kilometers" /> <br />
      meters: <input type = "text" v-model = "meters" />
    </div>
    <div>
        <table>
    <tr>
        <th>Number</th>
        <th>Sale Name</th>
        <th>Sale Price</th>
        <th>Purchase Number</th>
        <th>Action</th>
    </tr>
    <tr v-for="iphone in Ip_Json" v-bind:key="iphone.id">
        <td>{{ iphone.id }}</td>
        <td>{{ iphone.name }}</td>
        <td>{{ iphone.price }}</td>
        <td>
        <button v-bind:disabled="iphone.count === 0" v-on:click="iphone.count-=1">-</button>
        {{ iphone.count }}
        <button v-on:click="iphone.count+=1">+</button>
        </td>
        <td>
        <button v-on:click="iphone.count=0">移除</button>
        </td>
    </tr>
    </table>
    总价：${{totalPrice()}}
    </div>
    <div v-bind:class="{hightlight: isActive}" v-bind:style="[bindStyle]">hightlight</div>
    <div v-on:keyup.up="keyUpMethod">keyUpMethod</div>
    <div>
        <input type="checkbox" id="runoob" value="Runoob" v-model="checkedNames">
        <label for="runoob">Runoob</label>
        <input type="checkbox" id="google" value="Google" v-model="checkedNames">
        <label for="google">Google</label>
        <input type="checkbox" id="taobao" value="Taobao" v-model="checkedNames">
        <label for="taobao">taobao</label>
        <br>
        <span>Select Value: {{ checkedNames }}</span>
    </div>
    <div>
      <runoob message="abcd"></runoob>
    </div>
    <div>
      <IpItems v-for="item in Ip_Json" v-bind:key="item.id" v-bind:item="item"></IpItems>
    </div>
    <div>
      {{totalCount}} <br />
      <buttonClick v-on:plusChangeValue="plusTotalCountValue"></buttonClick>
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
      },
      kilometers: 0,
      meters: 0,
      Ip_Json: [
        {id: 1, name: 'iphone 8', price: 5099, count: 1},
        {id: 2, name: 'iphone xs', price: 8699, count: 1},
        {id: 3, name: 'iphone xr', price: 6499, count: 1}
      ],
      isActive: true,
      bindStyle: {'background-color': '#00ff00'},
      checkedNames: [],
      totalCount: 0
    }
  },
  methods: {
    reverseStr: function (event) {
      this.message = this.message.split('').reverse()
      console.log(event.target.tagName)
    },
    totalPrice: function () {
      var totalP = 0
      for (var i = 0, len = this.Ip_Json.length; i < len; i++) {
        totalP += this.Ip_Json[i].price * this.Ip_Json[i].count
      }
      return totalP
    },
    keyUpMethod: function () {
      console.log('enter key')
    },
    plusTotalCountValue: function () {
      this.totalCount += 1
    }
  },
  filters: {
    capitalize: function (value) {
      if (!value) return ''
      value = value.toString()
      return value.charAt(0).toUpperCase() + value.slice(1)
    }
  },
  watch: {
    kilometers: function (val) {
      this.kilometers = val
      this.meters = this.kilometers * 1000
    },
    meters: function (val) {
      this.meters = val
      this.kilometers = val / 1000
    }
  }
}
</script>
>
<style scoped>
table {
    border: 1px solid black;
}
table {
    width: 100%;
}

th {
    height: 50px;
}
th, td {
    border-bottom: 1px solid #ddd;
}
.hightlight {
  color: #ff0000;
}
</style>
