<template>
    <div id="bigpageBox">
        <div class="pageTitle"><span  @click="$router.go(-1)" style="cursor: pointer;">输液瓶（袋）回收溯源系统</span></div>
        <div class="mainBox">
            <div class="numberBox">
                <div>
                    <p>医疗机构总数</p>
                    <P v-if="numberData.hospitalcount != null">{{numberData.hospitalcount}}</P>
                    <P v-else>0</P>
                </div>
                <div>
                    <p>回收车辆总数</p>
                    <P v-if="numberData.vehiclecount != null">{{numberData.vehiclecount}}</P>
                    <P v-else>0</P>
                </div>
                <div>
                    <p>塑料总重量（千克）</p>
                    <P v-if="numberData.plasticweight != null">{{numberData.plasticweight}}</P>
                    <P v-else>0</P>
                </div>
                <div>
                    <p>玻璃总重量（千克）</p>
                    <P v-if="numberData.glassweight != null">{{numberData.glassweight}}</P>
                    <P v-else>0</P>
                </div>
                <div>
                    <p>当月塑料总重量（千克）</p>
                    <P v-if="numberData.thismonthplasticweight != null">{{numberData.thismonthplasticweight}}</P>
                    <P v-else>0</P>
                </div>
                <div>
                    <p>当月玻璃总重量（千克）</p>
                    <P v-if="numberData.thismonthglassweight != null">{{numberData.thismonthglassweight}}</P>
                    <P v-else>0</P>
                </div>
            </div>
            <div class="numberBox numberBoxs">
                <div v-for="(item,index) in typeCount" :key="index">
                    <p>{{item.type}}</p>
                    <P>{{item.count}}</P>
                </div>
                <div>
                    <p>三级医院总数</p>
                    <P>{{sanjia}}</P>
                </div>
            </div>
            <div class="mainCenterBox">
                <div>
                    <echartMap></echartMap>
                </div>
                <div class="centerLastBox">
                    <div>
                        <zhuzhuang></zhuzhuang>
                    </div>
                    <div>
                        <zhexian></zhexian>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import echartMap from './map'
import zhexian from './zhexian'
import zhuzhuang from './zhuzhuang'

  export default {
    components:{
      echartMap,
      zhexian,
      zhuzhuang
    },
    data() {
      return {
          numberData:{
            glassweight: 0,
            hospitalcount: 0,
            plasticweight: 0,
            status: 0,
            thismonthglassweight: 0,
            thismonthplasticweight: 0,
            vehiclecount: 0,
          },
          typeCount:[],
          sanjia:0
      };
    },
    mounted() {
      this.getNumberData();
    },
    methods: {
        getNumberData(){
          //发送请求
            var that = this;
            this.$ajax.get('/recovery/hospital/countByTransportcompany',{
                params:{
                  transportcompanyid: sessionStorage.getItem('belongId')
                }
            }).then(function (response) {
                console.log("统计信息",response);
                if(response.data.status == 260){
                    that.typeCount = response.data.typecount;
                    that.sanjia = response.data.levelcount.count;
                }
            });
            this.$ajax.get('/recovery/transportcompany/statistics',{
                params:{
                  transportcompanyid: sessionStorage.getItem('belongId')
                }
            }).then(function (response) {
                console.log("统计信息",response);
                if(response.data.status == 260){
                    that.numberData = response.data;
                }
            });
        }
    }
  }
</script>

<style scoped>
#bigpageBox {
    width: 100%;
    min-height: 870px;
    height: 100vh;
    background: url("../../../assets/bigpagebg.png");
    background-size: 100% 100%;
}
.pageTitle{
    width: 100%;
    min-height: 60px;
    height: 8vh;
    text-align: center;
    font-size: 36px;
    color: #bbd9ff;
    display: flex;
    align-items: center;
    justify-content: center;
}
.mainBox{
    width:98%;
    margin: 0 auto;
    min-height: 800px;
    height: 92vh;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: space-around;
}
.numberBox{
    width:100%;
    display: flex;
    flex-wrap: wrap;
    align-items: center;
    justify-content: space-between;
    color: #bbd9ff;
}
.numberBox > div{
    width: 16%;
    height: 110px;
    background:rgba(27, 88, 167,.2);
}
.numberBox > div > p{
    width: 100%;
}
.numberBox > div > p:first-child{
    padding-left: 5%;
    height: 40px;
    line-height: 40px;
    font-size: 16px;
}
.numberBox > div > p:last-child{
    height: 60px;
    line-height: 50px;
    font-size: 30px;
    text-align: center;
    color:#f8b422
}
.numberBoxs > div > p:last-child{
    font-size: 28px!important;
}
.mainCenterBox{
    width: 100%;
    height: 70%;
    display: flex;
    align-items: center;
    justify-content: space-between;
}
.mainCenterBox > div{
    width: 49.6%;
    height: 100%;
    background: rgba(27, 88, 167,.2);
}
.centerLastBox{
    width: 100%;
    height: 100%;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-items: center;
    background: transparent!important;
}
.centerLastBox > div{
    width: 100%;
    height: 50%;
}
</style>
