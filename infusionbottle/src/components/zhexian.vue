<template>
    <div class="zhexianBox">
        <div class="suyin">
            <span v-for="(item,index) in echartsData" :key="index" :class="{active:flag== item.name}" @click="getZheXianData(index)">{{item.value}} </span>
        </div>
        <div id="zhexianEchart"></div>
    </div>
</template>
<script>

var echarts = require('echarts');
export default {
    props:["mainId",'mapdata'],
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
              address: null,
              paddress: null,
              level: null,
              hospitalid: null,
              flag: 0
          }
    },
    watch: {  
        'mapdata':{
            handler:function(val,oldval){
                this.address= null;
                this.paddress= null;
                this.level= null;
                this.hospitalid= null;
                console.log(val)
                if(val.level == '县级'){
                    this.address = val.name;
                    this.paddress = val.father;
                    this.level = '区'
                }else if(val.level == '全国'){
                    this.address = val.name;
                    this.level = val.level
                }else if(val.id){
                    this.hospitalid = val.id
                }else{
                    this.address = val.name;
                    this.level = val.level.substr(0,1)
                }
                this.getZheXianData(0);
            },   
        },
    },
    mounted(){
        if(sessionStorage.getItem('userType') == '医疗机构'){
            this.hospitalid = sessionStorage.getItem('belongId');
        }else if(sessionStorage.getItem('userType') == '区管理单位'){
            this.address = sessionStorage.getItem('userAddress');
            this.paddress = sessionStorage.getItem('userFatherAddress');
            this.level = sessionStorage.getItem('addressLevel')
        }else{
            this.address = sessionStorage.getItem('userAddress');
            this.level = sessionStorage.getItem('addressLevel')
        }
        
        this.getZheXianData(0);
    },
    methods: {
        getZheXianData(flag){
            this.flag = flag;
            this.suliaoList = [];
            this.glassList = [];
            console.log(flag)
            //发送请求
            var that = this;
            this.$ajax.get("/recovery/statistics/yearWeightStatistics",{
                params:{
                    address: this.address,
                    paddress: this.paddress,
                    level: this.level,
                    flag: flag,
                    hospitalid: this.hospitalid
                }
            }).then(function (response) {
                console.log("折线图数据",response);
                if(response.data.status == 260){
                    if(response.data.plasticweight != null){
                        for (var i = 0; i < response.data.plasticweight.length; i++) {
                            that.suliaoList.push(response.data.plasticweight[i].value);
                        }
                    }else{
                        that.suliaoList=[]
                    }
                    if(response.data.glassweight != null){
                        for (var i = 0; i < response.data.plasticweight.length; i++) {
                            that.glassList.push(response.data.glassweight[i].value);
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
            console.log(this.suliaoList)
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('zhexianEchart'));
            // 绘制图表
            myChart.setOption({
                backgroundColor: "rgba(27, 88, 167,.2)",
                title : {
                        text: '回收总重量',
                        left: 'left',
                        top: 40,
                        textStyle: {
                            fontSize: 20,
                            color: '#bbd9ff'
                        }
                    },
                tooltip: {
                    trigger: "axis",
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
                    x: '46%',
                    top: '11%',
                    textStyle: {
                        color: '#bbd9ff',
                        fontSize: 15,
                    },
                    data: ['塑料', '玻璃']
                },

                calculable: true,
                xAxis: [{
                    type: "category",
                    axisLine: {
                        lineStyle: {
                            color: '#bbd9ff',
                        }
                    },
                    splitLine: {
                        show: false
                    },
                    axisTick: {
                        show: false
                    },
                    data: xData,
                }],

                yAxis: [{
                    type: "value",
                    splitLine: {
                        show: false
                    },
                    axisLine: {
                        lineStyle: {
                            
                        color: '#bbd9ff',
                        }
                    },

                }],
                dataZoom: [{
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
                series: [{
                    name: "玻璃",
                    type: "line",
                    symbolSize: 10,
                    symbol: 'circle',
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
                    markPoint: {
                        label: {
                            normal: {
                                textStyle: {
                                    color: '#fff'
                                }
                            }
                        },
                        data: [{
                            type: 'max',
                            name: '最大值',

                        }, {
                            type: 'min',
                            name: '最小值'
                        }]
                    },
                    data: that.glassList,
                }, {
                    name: "塑料",
                    type: "line",
                    symbolSize: 10,
                    symbol: 'circle',
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
                    markPoint: {
                        label: {
                            normal: {
                                textStyle: {
                                    color: '#fff'
                                }
                            }
                        },
                        data: [{
                            type: 'max',
                            name: '最大值',

                        }, {
                            type: 'min',
                            name: '最小值'
                        }]
                    },
                    data: that.suliaoList
                }, ]
            })
            window.addEventListener("resize", () => { myChart.resize();});
        }
    }
}
</script>
<style scoped>
.zhexianBox{
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
#zhexianEchart{
  width: 100%;
  height: 100%;
}
.active{
  color: rgba(35, 125, 243);
}
</style>


