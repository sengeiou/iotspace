<template>
    <div id="bigpageBox">
        <div class="mainBox">
            <div class="dateSelectBox">
                <el-col style="width: 150px;">
                    <el-date-picker type="date" placeholder="起始日期" value-format="timestamp" v-model="dateSelect.starttime" @change="dateSelectFn" style="width:100%"></el-date-picker>
                </el-col>
                <el-col style="width:150px;margin-left:10px">
                    <el-date-picker type="date" placeholder="结束日期" value-format="timestamp" v-model="dateSelect.endtime" @change="dateSelectFn" style="width:100%"></el-date-picker>
                </el-col>
                <el-button type="warning" size="small" @click="clearSelect" style="font-size:14px;margin-left: 10px">清空筛选</el-button>
            </div>
            <div class="numberBox">
                <div v-for="(item,index) in typeCount" :key="index">
                    <p>{{item.name}}(kg)</p>
                    <P>{{item.value}}</P>
                </div>
            </div>
            <div class="mainCenterBox">
                <div>
                    <Bing :startDate="dateSelect.starttime" :endDate="dateSelect.endtime"></Bing>
                </div>
                <div>
                    <bingTwo :startDate="dateSelect.starttime" :endDate="dateSelect.endtime"></bingTwo>
                </div>
            </div>
            <div class="zhexianBox">
                <zhexian :startDate="dateSelect.starttime" :endDate="dateSelect.endtime"></zhexian>
            </div>
            
        </div>
    </div>
</template>

<script>
import Bing from './zujian/bing'
import zhexian from './zujian/zhexian'
import bingTwo from './zujian/bingtwo'

  export default {
    components:{
      Bing,
      zhexian,
      bingTwo
    },
    data() {
      return {
          false: false,
          numberData:{
            glassweight: 0,
            hospitalcount: 0,
            plasticweight: 0,
            status: 0,
            thismonthglassweight: 0,
            thismonthplasticweight: 0,
            vehiclecount: 0,
          },
          dateSelect:{
              starttime: null,
              endtime: null,
          },
          typeCount:[],
          sanjia:0
      };
    },
    mounted() {
      this.getNumberData();
    },
    methods: {
        clearSelect(){
            this.dateSelect = {
              starttime: null,
              endtime: null,
            };
            this.getNumberData();
        },
        dateSelectFn(){
            console.log(this.dateSelect)
            this.getNumberData();
        },
        getNumberData(){
          //发送请求
            var that = this;
            this.$ajax.get('/recovery/gmaterialin/sumWeightGroupByCategory',{
                params:{
                    treatmentplantid: sessionStorage.getItem('belongId'),
                    begintime: this.dateSelect.starttime,
                    endtime: this.dateSelect.endtime,
                }
            }).then(function (response) {
                console.log("统计信息",response);
                if(response.data.status == 260){
                    that.typeCount = response.data.list;
                }else{
                    that.typeCount = [];
                }
            });
        }
    }
  }
</script>

<style scoped>
#bigpageBox {
    width: 100%;
    background:#F5F7F9;
    /* min-height: 870px;
    height: 100vh; */
    /* background: url("../../../assets/bigpagebg.png");
    background-size: 100% 100%; */
}

.mainBox{
    width:95%;
    margin: 0 auto;
    margin-top: 10px;
}
.dateSelectBox{
    width: 100%;
    height: 50px;
}
.numberBox{
    width:100%;
    /* height: 120px; */
    display: flex;
    flex-wrap: wrap;
    align-items: center;
    /* justify-content: space-between; */
    color: #333;
}
.numberBox > div{
    width: 24%;
    height: 110px;
    margin-right: 1.33333%;
    margin-bottom: 10px;
    background:#ecf3ee;
}
.numberBox > div:nth-child(4n){
    margin-right: 0;
}
/* .numberBox > div:first-child{
    margin-right: 0;
} */
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
.zhexianBox{
    width: 100%;
    height: 700px;
    margin-top: 10px;
}
.mainCenterBox{
    width: 100%;
    height: 400px;
    margin-top: 5px;
    display: flex;
    align-items: center;
    justify-content: space-between;
}
.mainCenterBox > div{
    width: 49.65%;
    height: 100%;
    background: #ecf3ee;
}
/* .centerLastBox{
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
} */
</style>
