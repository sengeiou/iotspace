<template>
    <div class="bingBox">
        <!-- <div class="tabBox">
          <span @click="tabType('塑料')" :class="{active:flag=='塑料'}">塑料</span>
          <span @click="tabType('玻璃')" :class="{active:flag=='玻璃'}">玻璃</span>
        </div> -->
        <div id="bing"></div>
    </div>
</template>
<script>

var echarts = require('echarts');
export default {
    props:["startDate","endDate"],
    data(){
          return{
            typeCount:[],
          }
    },
    watch:{
        startDate:function(a,b){
            console.log(a,b);
            this.getBingData()
        },
        endDate:function(a,b){
            console.log(a,b);
            this.getBingData()
        },
    },
    mounted(){
        this.getBingData()
    },
    methods: {
        getBingData(url,index){
            console.log("饼图",url)
            var that = this;
            this.$ajax.get('/recovery/gmaterialin/sumWeightGroupBySource',{
                params:{
                    treatmentplantid: sessionStorage.getItem('belongId'),
                    begintime: this.startDate,
                    endtime: this.endDate,
                }
            }).then(function (response) {
                console.log("统计信息",response);
                if(response.data.status == 260){
                    that.typeCount = response.data.list;
                    that.creatEchart();
                }else{
                    that.typeCount = [];
                    that.creatEchart();
                }
            });
            
        },
        creatEchart(){
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('bing'));
            var that = this;
            
            let bgColor = 'transparent';
            let title = '总量';
            let color = ['#0E7CE2','#F8456B'];
            let echartData = this.typeCount

            let formatNumber = function(num) {
                let reg = /(?=(\B)(\d{3})+$)/g;
                return num.toString().replace(reg, ',');
            }
            let total = echartData.reduce((a, b) => {
                return a + b.value * 1
            }, 0);

            // 绘制图表
                myChart.setOption({
                    backgroundColor: bgColor,
                    color: color,
                    title: [{
                        text: '{name|' + title + '}\n{val|' + formatNumber(total) + '}',
                        top: 'center',
                        left: 'center',
                        textStyle: {
                            rich: {
                                name: {
                                    fontSize: 14,
                                    fontWeight: 'normal',
                                    color: '#666666',
                                    padding: [10, 0]
                                },
                                val: {
                                    fontSize: 32,
                                    fontWeight: 'bold',
                                    color: '#333333',
                                }
                            }
                        }
                    },{
                        text: '入库来源',
                        top: 20,
                        left: 20,
                        textStyle: {
                            fontSize: 14,
                            color:'#666666',
                            fontWeight: 400
                        }
                    }],
                    series: [{
                        type: 'pie',
                        radius: ['45%', '60%'],
                        center: ['50%', '50%'],
                        data: echartData,
                        hoverAnimation: false,
                        itemStyle: {
                            normal: {
                                borderColor: bgColor,
                                borderWidth: 2
                            }
                        },
                        labelLine: {
                            normal: {
                                length: 20,
                                length2: 120,
                                lineStyle: {
                                    color: '#e6e6e6'
                                }
                            }
                        },
                        label: {
                            normal: {
                                formatter: params => {
                                    return (
                                        '{icon|●}{name|' + params.name + '}{value|' +
                                        formatNumber(params.value) + '}'
                                    );
                                },
                                padding: [0 , -100, 25, -100],
                                rich: {
                                    icon: {
                                        fontSize: 16
                                    },
                                    name: {
                                        fontSize: 14,
                                        padding: [0, 10, 0, 4],
                                        color: '#666666'
                                    },
                                    value: {
                                        fontSize: 18,
                                        fontWeight: 'bold',
                                        color: '#333333'
                                    }
                                }
                            }
                        },
                    }]
                })
            
            window.addEventListener("resize", () => { myChart.resize();});
        }
    }
}
</script>
<style scoped>
.bingBox{
    width: 100%;
    height: 100%;
    position: relative;
}
#bing{
  width: 100%;
  height: 100%;
}
.tabBox{
  position:absolute;
  top: 5px;
  right: 10px;
  color: #bbd9ff;
  cursor: pointer;
  font-size: 20px;
  z-index: 99;
}
.active{
  color: rgba(35, 125, 243);
}
</style>


