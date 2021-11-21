<template>
    <div class="bingBox">
        <div id="bing" v-if="ifIsShow"></div>
    </div>
</template>
<script>

var echarts = require('echarts');
export default {
    props:['flag','mapdata'],
    data(){
          return{
            chainPath: '/recovery/truckhospital/statisticProvincePlasticWeight',
            provincePath: '/recovery/truckhospital/statisticCityPlasticWeight',
            cityPath: '/recovery/truckhospital/statisticPlatPlasticWeight',
            platPath: '/recovery/truckhospital/statisticHospitalPlasticWeight',
            hospitalPath: '/recovery/garbagebag/countGroupByDepartment',
            suliaoData:[],
            paramConfig : {
                url: null,
                val: null
            },
            ifIsShow: true,
            title:''
          }
    },
    mounted(){
        var url;
        var val;
        if(sessionStorage.getItem('userType') == '国家管理单位'){
            url = this.chainPath;
            this.getBingData(url,sessionStorage.getItem('userAddress'));
        }else if(sessionStorage.getItem('userType') == '省管理单位' && sessionStorage.getItem('userAddress')!='北京' && sessionStorage.getItem('userAddress')!='重庆' && sessionStorage.getItem('userAddress')!='天津' && sessionStorage.getItem('userAddress')!='上海'){
            url = this.provincePath;
            val = {
                    name: sessionStorage.getItem('userAddress')
                }
            this.getBingData(url,val);
        }else if(sessionStorage.getItem('userType') == '市管理单位' || sessionStorage.getItem('userAddress')=='北京' || sessionStorage.getItem('userAddress')=='重庆' || sessionStorage.getItem('userAddress')=='天津' || sessionStorage.getItem('userAddress')=='上海'){

            url = this.cityPath;
            if(sessionStorage.getItem('userAddress')=='北京'){
                val = {
                    name: '北京',
                }
            }else if(sessionStorage.getItem('userAddress')=='重庆'){
                val = {
                    name: '重庆',
                }
            }else if(sessionStorage.getItem('userAddress')=='上海'){
                val = {
                    name: '上海',
                }
            }else if(sessionStorage.getItem('userAddress')=='天津'){
                val = {
                    name: '天津',
                }
            }else{
                val = {
                    name: sessionStorage.getItem('userAddress')
                }
            }
            
            this.getBingData(url,val);
        }else if(sessionStorage.getItem('userType') == '区管理单位'){
            url = this.platPath;
            val={
                name: sessionStorage.getItem('userAddress'),
                father: sessionStorage.getItem('userFatherAddress')
            }
            this.getBingData(url,val);
        }else if(sessionStorage.getItem('userType') == '医疗机构'){
            url = this.hospitalPath;
            val={
                id: sessionStorage.getItem('belongId') 
            }
            this.getBingData(url,val);
        }
        
    },
    watch: {  
        flag(newValue, oldValue) {  
            console.log(newValue, oldValue);
            if(newValue == '玻璃'){
                this.chainPath = '/recovery/truckhospital/statisticProvinceGlassWeight';
                this.provincePath = '/recovery/truckhospital/statisticCityGlassWeight';
                this.cityPath = '/recovery/truckhospital/statisticPlatGlassWeight';
                this.platPath = '/recovery/truckhospital/statisticHospitalGlassWeight';
            }else{
                this.chainPath = '/recovery/truckhospital/statisticProvincePlasticWeight';
                this.provincePath = '/recovery/truckhospital/statisticCityPlasticWeight';
                this.cityPath = '/recovery/truckhospital/statisticPlatPlasticWeight';
                this.platPath = '/recovery/truckhospital/statisticHospitalPlasticWeight';
            }
            var url;
            console.log(this.paramConfig.url)
            if(this.paramConfig.url == '/recovery/truckhospital/statisticCityPlasticWeight' || this.paramConfig.url == '/recovery/truckhospital/statisticCityGlassWeight'){
                url = this.provincePath;
                this.getBingData(url,this.paramConfig.val);
            }else if(this.paramConfig.url == '/recovery/truckhospital/statisticPlatPlasticWeight' || this.paramConfig.url == '/recovery/truckhospital/statisticPlatGlassWeight'){
                url = this.cityPath
                this.getBingData(url,this.paramConfig.val);
            }else if(this.paramConfig.url == '/recovery/garbagebag/countGroupByDepartment'){
                url = this.cityPath
            }else if(this.paramConfig.url == '/recovery/truckhospital/statisticProvinceGlassWeight' || this.paramConfig.url == '/recovery/truckhospital/statisticProvincePlasticWeight'){
                url = this.chainPath
                this.getBingData(url,this.paramConfig.val);
            }
        },
        'mapdata':{
            handler:function(val,oldval){
                console.log(val)
                var url;
                if(val.level == '全国'){
                    url = this.chainPath;
                }else if(val.level == '省级'){
                    url = this.provincePath;
                }else if(val.level == '市级'){
                    url = this.cityPath;
                }else if(val.level == '县级'){
                    url = this.platPath;
                }else if(val.id){
                    url = this.hospitalPath;
                }
                this.getBingData(url,val);
            },   
        },
    },
    methods: {
        getBingData(url,val){
            console.log("饼图",url,val)
            this.paramConfig = {
                url:url,
                val:val
            }
            this.ifIsShow = true;
            var that = this;
            if(url == "/recovery/truckhospital/statisticProvincePlasticWeight" || url == '/recovery/truckhospital/statisticProvinceGlassWeight'){
                this.$ajax.get(url).then(function (response) {
                    console.log("饼图数据",response);
                    if(response.data.status == 260){
                        that.suliaoData = response.data.list;
                        that.title = '各单位重量占比'
                        that.creatEchart();
                    }else{
                        that.suliaoData = [];
                        that.ifIsShow = false;
                    }
                })
            }else if(url == "/recovery/truckhospital/statisticHospitalPlasticWeight"|| url == '/recovery/truckhospital/statisticHospitalGlassWeight'){
                console.log(sessionStorage.getItem('userAddress'))
                this.$ajax.get(url,{
                    params:{
                        address:val.name,
                        paddress: val.father
                    }
                }).then(function (response) {
                    console.log("饼图1数据",response);
                    if(response.data.status == 260){
                        that.suliaoData = response.data.list;
                        that.title = '各单位重量占比'
                        that.creatEchart();
                    }else{
                        that.suliaoData = [];
                        that.ifIsShow = false;
                    }
                })
            }else if(url == "/recovery/garbagebag/countGroupByDepartment"){
                this.$ajax.get(url,{
                    params:{
                        hospitalid: val.id
                    }
                }).then(function (response) {
                    console.log("饼图医疗机构数据",response);
                    if(response.data.status == 260){
                        that.suliaoData = response.data.list;
                        that.title = '各科室垃圾袋数占比'
                        that.creatEchart();
                    }else{
                        that.suliaoData = [];
                        that.ifIsShow = false;
                    }
                })
            }else{
                this.$ajax.get(url,{
                    params:{
                        address:val.name
                    }
                }).then(function (response) {
                    console.log("饼图3数据",response);
                    if(response.data.status == 260){
                        that.suliaoData = response.data.list;
                        that.title = '各单位重量占比'
                        that.creatEchart();
                    }else{
                        that.suliaoData = [];
                        that.creatEchart();
                    }
                })
            }
            
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
                        text: that.title,
                        left: 'center',
                        top: 30,
                        textStyle: {
                            fontSize: 20,
                            color: '#bbd9ff'
                        }
                    },
                    tooltip : {
                        trigger: 'item',
                        formatter: "{b} : {c} ({d}%)"
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
    height: 95%;
    position: relative;
  background:rgba(27, 88, 167,.2);
}
#bing{
  width: 100%;
  height: 100%;
}
</style>


