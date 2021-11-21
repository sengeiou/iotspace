<template>
    <div id="bigpageBox">
        <div class="pageTitle"><span  @click="$router.go(-1)" style="cursor: pointer;">输液瓶（袋）回收溯源系统</span></div>
        <div class="mainBox">
            <!-- <div class="numberBox">
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
                    <p>三甲医院总数</p>
                    <P>{{sanjia}}</P>
                </div>
            </div> -->
            <div class="mainTopBox">
                <div>
                    <bing></bing>
                </div>
                <div>
                    <echartMap></echartMap>
                </div>
                <div class="numberBox">
                    <div class="fourData">
                        <div>
                            <div>
                                <p>医疗机构总数</p>
                                <P v-if="numberData.hospitalcount != null">{{numberData.hospitalcount}}</P>
                                <P v-else>0</P>
                            </div>
                            <div>
                                <p>三级医院总数</p>
                                <P v-if="numberData.levelhospitalcount != null">{{numberData.levelhospitalcount}}</P>
                                <P v-else>0</P>
                            </div>
                        </div>
                        <div>
                            <div>
                                <p>暂存点个数</p>
                                <P v-if="numberData.transportcompanycount != null">{{numberData.transportcompanycount}}</P>
                                <P v-else>0</P>
                            </div>
                            <div>
                                <p>回收车辆总数</p>
                                <P v-if="numberData.vehiclecount != null">{{numberData.vehiclecount}}</P>
                                <P v-else>0</P>
                            </div>
                        </div>
                        <div>
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
                        </div>
                        <div>
                            <div>
                                <p>当月回收塑料总重量</p>
                                <P v-if="numberData.thismonthplasticweight != null">{{numberData.thismonthplasticweight}}</P>
                                <P v-else>0</P>
                            </div>
                            <div>
                                <p>当月回收玻璃总重量</p>
                                <P v-if="numberData.thismonthglassweight != null">{{numberData.thismonthglassweight}}</P>
                                <P v-else>0</P>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="mainBottomBox">
                <div>
                    <zhuzhuang></zhuzhuang>
                </div>
                <div>
                    <zhexian></zhexian>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import echartMap from './map'
import zhexian from './zhexian'
import zhuzhuang from './zhuzhuang'
import bing from './bing'

  export default {
    components:{
      echartMap,
      zhexian,
      zhuzhuang,
      bing
    },
    data() {
      return {
          numberData:{
            hospitalcount: 0,
            levelhospitalcount: 0,
            status: 0,
            thismonthglassweight: 0,
            thismonthplasticweight: 0,
            transportcompanycount: 0,
            vehiclecount: 0,
            plasticweight: 0,
            glassweight: 0,
          },
      };
    },
    mounted() {
      this.getNumberData();
    },
    methods: {
        getNumberData(){
          //发送请求
            var that = this;
            this.$ajax.get('/recovery/treatmentplant/statistics',{
                params:{
                  treatmentplantid: sessionStorage.getItem('belongId')
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
.mainTopBox{
    width: 100%;
    height: 55%;
    display: flex;
    align-items: center;
    justify-content: space-between;
}
.mainTopBox > div:nth-child(1){
    width: 29%;
    height: 100%;
    background: rgba(27, 88, 167,.2);
}
.mainTopBox > div:nth-child(2){
    width: 40%;
    height: 100%;
    background: rgba(27, 88, 167,.2);
}
.mainTopBox > div:nth-child(3){
    width: 30%;
    height: 100%;
    background: rgba(27, 88, 167,.2);
}
.mainBottomBox{
    width: 100%;
    height: 40%;
    display: flex;
    align-items: center;
    justify-content: space-between;
}
.mainBottomBox > div{
    width: 49.6%;
    height: 100%;
    background: rgba(27, 88, 167,.2);
}
.numberBox{
    background:transparent!important;
}
.fourData{
  width:100%;
  height: 100%;
  margin: 0 auto;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-between;
  color: #bbd9ff;
}
.fourData > div{
  width: 100%;
  height: 24%;
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  justify-content: space-between;
}
.fourData > div > div{
  width: 49.5%;
  height: 100%;
  margin-bottom: 1%;
  background:rgba(27, 88, 167,.2);
}
.fourData > div > div > p{
  width: 100%;
}
.fourData > div > div > p:first-child{
  padding-left: 5%;
  height: 30%;
  font-size: 16px;
  display: flex;
  align-items: flex-end;
}
.fourData > div > div > p:last-child{
  height: 70%;
  font-size: 30px;
  text-align: center;
  color:#f8b422;
  display: flex;
  align-items: center;
  justify-content:  center;
}
</style>
