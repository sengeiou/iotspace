<template>
    <div class="bingBox">
        <div class="tabBox">
          <span @click="tabType('塑料')" :class="{active:flag=='塑料'}">塑料</span>
          <span @click="tabType('玻璃')" :class="{active:flag=='玻璃'}">玻璃</span>
        </div>
        <div id="bing"></div>
    </div>
</template>
<script>

var echarts = require('echarts');
export default {
    data(){
          return{
            suliaoData:[],
            flag:'塑料',
          }
    },
    mounted(){
        this.getBingData('/recovery/treatmentplant/plasticweightSumGroupTransportcompany',1)
    },
    methods: {
        tabType(type){
            this.flag = type;
            if(type == '塑料'){
                this.getBingData('/recovery/treatmentplant/plasticweightSumGroupTransportcompany',1)
            }else if(type == '玻璃'){
                this.getBingData('/recovery/treatmentplant/glassweightSumGroupTransportcompany',2)
            }
        },
        getBingData(url,index){
            console.log("饼图",url)
            var that = this;
            this.$ajax.get(url,{
                params:{
                    treatmentplantid: sessionStorage.getItem('belongId')
                }
            }).then(function (response) {
                console.log("饼图数据",response);
                if(response.data.status == 260){
                    if(index == 1){
                        that.suliaoData = response.data.plasticweight;
                    }else if(index == 2){
                        that.suliaoData = response.data.glassweight;
                    }
                    that.creatEchart();
                }else{
                    that.suliaoData = [];
                    that.creatEchart();
                }
            })
            
        },
        creatEchart(){
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('bing'));
            var that = this;
            var array = [];
            var min;
            var max;
            var ceshidata = that.suliaoData;
            // if(that.suliaoData.length > 1){
            if(ceshidata.length > 1){
                for(var i = 0 ; i < ceshidata.length ; i++){
                    array.push(ceshidata[i].value);
                }
                array.sort(function (a, b) {
                    return a-b;
                });
                min = array[0];
                max = array[array.length-1]
            }else{
                min = ceshidata[0].value/2;
                max = ceshidata[0].value;
            }
            
            // 绘制图表
                myChart.setOption({
                    backgroundColor: 'transparent',
                    title : {
                        text: '各暂存点重量占比',
                        left: 'center',
                        top: 30,
                        textStyle: {
                            fontSize: 20,
                            color: '#bbd9ff'
                        }
                    },
                    tooltip : {
                        trigger: 'item',
                        formatter: "{a} <br/>{b} : {c} ({d}%)"
                    },
                    visualMap:{
                        show: false,
                        left: 'left',
                        min: min,
                        max: max,
                        inRange: {
                            color: ['#abd9e9','#74add1', '#4575b4','#313695']
                        },
                        text: ['High', 'Low'],           // 文本，默认为数值文本
                        calculable: false
                    },
                    series : [
                        {
                            name: '访问来源',
                            type: 'pie',
                            radius : '55%',
                            center: ['50%', '60%'],
                            data: ceshidata,
                            label: {
                            normal: {
                                textStyle: {
                                    fontSize: 15,
                                    color: '#bbd9ff'
                                }
                            }
                        },
                        itemStyle: {
                            emphasis: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        }
                        }
                    ]
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


