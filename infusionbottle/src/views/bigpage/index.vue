<template>
    <div id="bigpageBox">
        <div class="pageTitle"><span  @click="$router.go(-1)" style="cursor: pointer;">输液瓶（袋）回收溯源系统</span></div>
        <div class="tabBox" v-if="hospitalIf">
          <span @click="tabType('塑料')" :class="{active:flag=='塑料'}">塑料</span>
          <span @click="tabType('玻璃')" :class="{active:flag=='玻璃'}">玻璃</span>
        </div>
        <div class="contentBox">
          <div id="pieMap1">
            <bingMap :flag="flag" :mapdata='mapData'></bingMap>
          </div>
          <div id="mapBox">
            <echartMap :flag="flag" @mapData="getMapData"></echartMap>
          </div>
          <div class="allData">
            <div class="fourData">
              <div>
                <p v-if="hospitalIf">医疗机构总数</p>
                <p v-if="!hospitalIf">科室总数</p>
                <P>{{hospitalnum}}</P>
              </div>
              <div>
                <p>塑料总量（千克）</p>
                <P>{{plasticweight}}</P>
              </div>
              <div>
                <p>玻璃总量（千克）</p>
                <P>{{glassweight}}</P>
              </div>
              <div>
                <p>CO2减排量（吨）</p>
                <P>{{Math.round(plasticweight/1000*0.032 * 100) / 100}}</P>
              </div>
            </div>
            <div class="dataList">
              <vue-seamless-scroll :data="list" :class-option="classOption" class="table-content">
                <ul>
                  <li v-for="(item,index) in list"  :key="index">
                          <!-- {{item}} -->
                        {{item.hospital.name}}运走塑料：{{item.plasticweight}}kg 玻璃：{{item.glassweight}}kg
                  </li>
                </ul>
              </vue-seamless-scroll>
            </div>
          </div>
          <div id="pieMap">
            <zhuzhuang :mapdata='mapData'></zhuzhuang>
          </div>
          <div id="lineMap">
            <zhexian :mapdata='mapData'></zhexian>
          </div>
        </div>
    </div>
</template>

<script>
import echartMap from '../../components/map'
import bingMap from '../../components/bingmap'
import zhexian from '../../components/zhexian'
import zhuzhuang from '../../components/zhuzhuang'
  export default {
    components:{
      echartMap,
      bingMap,
      zhuzhuang,
      zhexian,
    },
    computed: {
      classOption () {
        return {
          singleHeight: 57,
          waitTime: 2000,
          step: 0.5,
          hoverStop: false 
        }
      }
    },
    data() {
      return {
        flag:'塑料',
        hospitalIf: true,
        hospitalnum: '10',
        glassweight: '28837',
        plasticweight: '25326',
       list:[],
       mapData:[]
      };
    },
    mounted() {
      var params;
      if(sessionStorage.getItem('userType') == '医疗机构'){
        this.hospitalIf = false;
        params = {
          hospitalid: sessionStorage.getItem('belongId'),
        }
      }else{
        params = {
            address: sessionStorage.getItem('userAddress'),
            paddress: sessionStorage.getItem('userFatherAddress'),
            level: sessionStorage.getItem('addressLevel'),
          };
      }
      this.getFourData(params);
    },
    methods: {
        tabType(type){
          this.flag = type;
        },
        getMapData(data){
          this.mapData = data;
          this.hospitalIf = true;
          console.log('ishadkjaksjdkjak',data)
          if(data.level == '县级'){
            var params = {
              address: data.name,
              paddress: data.father,
              level: '区',
            };
          }else if(data.level == '全国'){
            var params = {
              address: data.name,
              level: data.level,
            };
          }else if(data.id){
            var params = {
              hospitalid: data.id
            };
            this.hospitalIf = false;
          }else{
            var params = {
              address: data.name,
              level: data.level.substr(0,1),
            };
          }
          this.getFourData(params);
        },
        getFourData(params){
          //发送请求
            var that = this;
            this.$ajax.get('/recovery/statistics/numberStatistics',{
                params:params
            }).then(function (response) {
                console.log("统计信息",response);
                if(response.data.status == 260){
                    that.hospitalnum = response.data.hospitalnum;
                    that.glassweight = response.data.glassweight;
                    that.plasticweight = response.data.plasticweight;
                }
            });
            this.$ajax.get('/recovery/statistics/newData',{
                params:params
            }).then(function (response) {
                console.log("最新列表",response);
                if(response.data.status == 260){
                  that.list = response.data.list
                }else{
                    // Toast(response.data.message);
                }
            })
        }
    
    }
  }
</script>

<style scoped>
#bigpageBox {
    width: 100%;
    height: 100vh;
    background: url("../../assets/bigpagebg.png");
    background-size: 100% 100%;
}
.pageTitle{
  width: 100%;
  height: 60px;
  line-height: 60px;
  text-align: center;
  /* font-weight: bold; */
  font-size: 36px;
  color: #bbd9ff;
  padding-top: 20px;
}
.contentBox{
  width: 100%;
  height: 90%;
  box-sizing: border-box;
  padding: 1% 2% 0;
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  justify-content: space-between;
}
#pieMap1{
  width: 30%;
  height: 50%;
  box-sizing: border-box;
  padding-right: 0.5%;
}
#mapBox{
  width: 40%;
  height: 55%;
}
.allData{
  width: 30%;
  height: 55%;
}
#pieMap{
  width: 50%;
  height: 45%;
}
#lineMap{
  width: 50%;
  height: 45%;
}
.fourData{
  width:98%;
  margin: 0 auto;
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  justify-content: space-between;
  color: #bbd9ff;
}
.fourData > div{
  width: 49.5%;
  height: 100px;
  margin-bottom: 1%;
  background:rgba(27, 88, 167,.2);
}
.fourData > div > p{
  width: 100%;
}
.fourData > div > p:first-child{
  padding-left: 5%;
  height: 40px;
  line-height: 40px;
  font-size: 16px;
}
.fourData > div > p:last-child{
  height: 60px;
  line-height: 50px;
  font-size: 30px;
  text-align: center;
  color:#f8b422
}
.dataList{
  width: 100%;
  height:150px;

}
.table-content {
    width: 98%;
    margin: 0 auto;
    background: rgba(27, 88, 167,.2);
    height: 100%;
    color: #bbd9ff;
    overflow: hidden;
    box-sizing: border-box;
    padding: 1%;
}
.table-content  li{
  height: 30px;
}
.tabBox{
  position:fixed;
  top: 30px;
  right: 20px;
  color: #bbd9ff;
  cursor: pointer;
  font-size: 20px;
}
.active{
  color: rgba(35, 125, 243);
}
</style>
