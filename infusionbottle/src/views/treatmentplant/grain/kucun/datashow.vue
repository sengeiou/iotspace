<template>
    <div id="bigpageBox">
        <div class="mainBox">
            <!-- <div class="dateSelectBox">
                <span>日期：</span>
                <el-date-picker
                    v-model="dateSelect"
                    value-format="timestamp"
                    type="daterange"
                    range-separator="至"
                    start-placeholder="开始日期"
                    end-placeholder="结束日期"
                    @change="dateSelectFn"
                    :clearable="false">
                </el-date-picker>
                <el-button type="warning" size="small" @click="clearSelect" style="font-size:14px">清空筛选</el-button>
            </div> -->
            <div class="numberBox">
                <div v-for="(item,index) in typeCount" :key="index">
                    <p>{{item.name}}(kg)</p>
                    <P>{{item.stock}}</P>
                </div>
            </div>
            <div class="zhexianBox">
                <Bing></Bing>
            </div>
            
        </div>
    </div>
</template>

<script>
import Bing from './zujian/bing'

  export default {
    components:{
      Bing,
    },
    data() {
      return {
          false:false,
          numberData:{
            glassweight: 0,
            hospitalcount: 0,
            plasticweight: 0,
            status: 0,
            thismonthglassweight: 0,
            thismonthplasticweight: 0,
            vehiclecount: 0,
          },
          dateSelect:[],
          typeCount:[],
          sanjia:0
      };
    },
    mounted() {
      this.getNumberData();
    },
    methods: {
        clearSelect(){
            this.dateSelect = [];
        },
        dateSelectFn(){
            console.log(this.dateSelect)
            this.getNumberData();
        },
        getNumberData(){
          //发送请求
            var that = this;
            this.$ajax.get('/recovery/gmaterialcategory/selectByTreatmentplantid',{
                params:{
                    treatmentplantid: sessionStorage.getItem('belongId'),
                    // begintime: this.dateSelect[0],
                    // endtime: this.dateSelect[1],
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
    height: 90vh;
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
    height: 50%;
    margin-top: 10px;

    background: #ecf3ee;
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
