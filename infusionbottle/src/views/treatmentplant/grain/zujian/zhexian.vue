<template>
    <div class="zhexianBox">
        <div class="suyin">
            <p v-for="(item,index) in echartsData" :key="index"  :class="{active:flag == item.name}" @click="getZheXianData(index)">{{item.value}} </p>
        </div>
        <div id="zhexianEchart"></div>
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
              dataList:[
                ['product', '1月', '2月', '3月', '4月', '5月', '6月','7月','8月','9月','10月','11月','12月'],
              ],
              flag: 0,
          }
    },
    mounted(){
        this.getZheXianData(0);
    },
    methods: {
        getZheXianData(flag){
            this.flag = flag;
            //发送请求
            var that = this;
            this.dataList = [
                ['product', '1月', '2月', '3月', '4月', '5月', '6月','7月','8月','9月','10月','11月','12月'],
              ];
            this.$ajax.get("/recovery/gmaterialin/sumWeightGroupByMonth",{
                params:{
                    treatmentplantid: sessionStorage.getItem('belongId'),
                    flag: flag,
                }
            }).then(function (response) {
                console.log("折线图数据",response);
                if(response.data.status == 260){
                    for(var i = 0 ; i < response.data.categorylist.length ; i++){
                        that.dataList.push([response.data.categorylist[i].name]);
                        var name = response.data.categorylist[i].name;
                        var arr = response.data.object[name];
                        for(var j = 0 ; j < response.data.object[name].length ; j++){
                            that.dataList[i+1].push(response.data.object[name][j].value)
                        }
                    }
                    console.log(that.dataList)
                    that.creatEchart();
                }else{

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
            var myChart = echarts.init(document.getElementById('zhexianEchart'));
            // 绘制图表
            myChart.setOption({
                backgroundColor: "#ecf3ee",
                legend: {},
                tooltip: {
                    trigger: 'axis',
                    showContent: false
                },
                dataset: {
                    source: this.dataList
                },
                xAxis: {type: 'category'},
                yAxis: {gridIndex: 0},
                grid: {top: '55%'},
                series: [
                    {type: 'line', smooth: true, seriesLayoutBy: 'row'},
                    {type: 'line', smooth: true, seriesLayoutBy: 'row'},
                    {type: 'line', smooth: true, seriesLayoutBy: 'row'},
                    {type: 'line', smooth: true, seriesLayoutBy: 'row'},
                    {type: 'line', smooth: true, seriesLayoutBy: 'row'},
                    {type: 'line', smooth: true, seriesLayoutBy: 'row'},
                    {type: 'line', smooth: true, seriesLayoutBy: 'row'},
                    {type: 'line', smooth: true, seriesLayoutBy: 'row'},
                    {
                        type: 'pie',
                        id: 'pie',
                        radius: '30%',
                        center: ['50%', '25%'],
                        label: {
                            formatter: '{b}: {@1月} ({d}%)'
                        },
                        encode: {
                            itemName: 'product',
                            value: '1月',
                            tooltip: '1月'
                        }
                    }
                ]
            })
            myChart.on('updateAxisPointer', function (event) {
                var xAxisInfo = event.axesInfo[0];
                if (xAxisInfo) {
                    var dimension = xAxisInfo.value + 1;
                    myChart.setOption({
                        series: {
                            id: 'pie',
                            label: {
                                formatter: '{b}: {@[' + dimension + ']} ({d}%)'
                            },
                            encode: {
                                value: dimension,
                                tooltip: dimension
                            }
                        }
                    });
                }
            });
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
    top: 50px;
    left: 10px;
    color: #333;
    font-size: 20px;
    z-index: 9;
}
.suyin>p:hover{
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


