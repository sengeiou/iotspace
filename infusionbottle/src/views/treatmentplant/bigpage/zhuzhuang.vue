<template>
    <div class="zhuzhuang">
        <div class="suyin">
            <span v-for="(item,index) in echartsData" :key="index" :class="{active:flag== item.name}" @click="getZhuzhuangData(index)">{{item.value}} </span>
        </div>
        <div id="zhuMap"></div>
    </div>
</template>
<script>

var echarts = require('echarts');
export default {
    data(){
          return{
              echartsData:[
                {
                    name: 0,
                    value: new Date().getFullYear()
              },{
                  name: 1,
                  value: new Date().getFullYear()-1
              },{
                  name: 2,
                  value: new Date().getFullYear()-2
              }],
              suliaoList:[],
              glassList:[],
              flag: 0
          }
    },
    mounted(){
        this.getZhuzhuangData(0);
    },
    methods: {
        getZhuzhuangData(flag){
            this.flag = flag;
            this.suliaoList = [];
            this.glassList = [];
            //发送请求
            var that = this;
            this.$ajax.get("/recovery/treatmentplant/yearStatistics",{
                params:{
                    treatmentplantid: sessionStorage.getItem('belongId'),
                    flag: flag,
                }
            }).then(function (response) {
                console.log("柱状数据111",response);
                if(response.data.status == 260){
                    if(response.data.plasticnumber != null){
                        for (var i = 0; i < response.data.plasticnumber.length; i++) {
                            that.suliaoList.push(response.data.plasticnumber[i].value);
                        }
                    }else{
                        that.suliaoList=[]
                    }
                    if(response.data.glassnumber != null){
                        for (var i = 0; i < response.data.glassnumber.length; i++) {
                            that.glassList.push(response.data.glassnumber[i].value);
                        }
                    }else{
                        that.glassList = [];
                    }
                    

                        that.creatEchart();
                }else{
                    
                    that.suliaoList=[];
                    that.glassList=[];
                    that.creatEchart();
                }
            })
        },
        creatEchart(){
            var that = this;
            var xData = function() {
                var that = this;
                var data = [];
                for (var i = 1; i <= 12; i++) {
                    data.push(i + "月");
                }
                return data;
            }();
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('zhuMap'));
            // 绘制图表
            myChart.setOption({
                backgroundColor: "rgba(27, 88, 167,.2)",
                title : {
                        text: '可追溯垃圾袋数量',
                        left: 'left',
                        top: 40,
                        textStyle: {
                            fontSize: 20,
                            color: '#bbd9ff'
                        }
                    },
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: "shadow",
                        textStyle: {
                            color: "#fff"
                        }
                    },
                },
                grid: {
                    borderWidth: 0,
                    top: 110,
                    bottom: 95,
                    textStyle: {
                        color: "#fff"
                    }
                },
                legend: {
                    data: ['塑料', '玻璃'],
                    x: '46%',
                    top: '11%',
                    textStyle: {
                        color: '#bbd9ff',
                        fontSize: 15,
                    },
                },
                calculable: true,
                xAxis: {
                    type: 'category',
                    data: xData,
                    axisLine: {
                        lineStyle: {
                        color: '#bbd9ff'

                        }
                    },
                    splitLine: {
                        show: false
                    },
                    axisTick: {
                        show: false
                    }
                },

                yAxis: {
                    type: 'value',
                    splitLine: {
                        show: false
                    },
                    axisLine: {
                        lineStyle: {
                            color: '#bbd9ff',
                        }
                    },
                    axisLabel: {}
                },
                "dataZoom": [{
                    "show": true,
                    "height": 15,
                    "xAxisIndex": [
                        0
                    ],
                    bottom:'8%',
                    "start": 0,
                    "end": 100,
                    handleIcon: 'path://M306.1,413c0,2.2-1.8,4-4,4h-59.8c-2.2,0-4-1.8-4-4V200.8c0-2.2,1.8-4,4-4h59.8c2.2,0,4,1.8,4,4V413z',
                    handleSize: '110%',
                    handleStyle:{
                        color:"#5B3AAE",
                    },
                    textStyle:{
                        color:"#bbd9ff",
                    },
                    fillerColor:"rgba(67,55,160,0.4)",
                    borderColor: "rgba(204,187,225,0.5)",
                }, {
                    "type": "inside",
                    "show": true,
                    "height": 15,
                    "start": 1,
                    "end": 35
                }],
                series: [
                {
                    name: '塑料',
                    type: 'bar',
                    barWidth: '20%',
                    itemStyle: {
                        normal: {
                            color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                                offset: 0,
                                color: '#f177c9'
                            }, {
                                offset: 1,
                                color: '#c257F6'
                            }]),
                            barBorderRadius: 11,
                        }
                        
                    },
                    data: that.suliaoList
                },
                {
                    name: '玻璃',
                    type: 'bar',
                    barWidth: '20%',
                    itemStyle: {
                        normal: {
                            color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                                offset: 0,
                                color: '#248ff7'
                            }, {
                                offset: 1,
                                color: '#6851f1'
                            }]),
                        barBorderRadius: 11,
                        }
                    },
                    data: that.glassList
                }]
            })
            window.addEventListener("resize", () => { myChart.resize();});
        }
    }
}
</script>
<style scoped>
.zhuzhuang{
    width: 100%;
    height: 100%;
    position: relative;
}
.suyin{
    position: absolute;
    top: 10px;
    left: 10px;
    color: #bbd9ff;
    font-size: 20px;
    z-index: 9;
}
.suyin>span:hover{
    cursor: pointer;
}
#zhuMap{
  width: 100%;
  height: 100%;
}
.active{
  color: rgba(35, 125, 243);
}
</style>


