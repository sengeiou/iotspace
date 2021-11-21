<template>
    <div class="mapBox">
        <div class="suyin">
            <span v-for="(item,index) in echartsData" :key="index" @click="backTop(item)">{{item}} </span>
        </div>
        <div id="mainId" v-show="!baiduIfMap"></div>
        <div id="baiDuMap" v-show="baiduIfMap">

        </div>
    </div>
</template>
<script>
import $ from 'jquery'
var echarts = require('echarts');
export default {
    props:["flag"],
    data(){
          return{
              baiduIfMap: false,
              arrayData:[
                {name: '中国',path:'../../static/json/china.json',level:'全国'},
                {name: '内蒙古',path:'../../../static/json/province/neimenggu.json',level:'省级'},
                {name: '新疆',path:'../../../static/json/province/xinjiang.json',level:'省级'},
                {name: '西藏',path:'../../../static/json/province/xizang.json',level:'省级'},
                {name: '贵州',path:'../../../static/json/province/guizhou.json',level:'省级'},
                {name: '青海',path:'../../../static/json/province/qinghai.json',level:'省级'},
                {name: '宁夏',path:'../../../static/json/province/ningxia.json',level:'省级'},
                {name: '甘肃',path:'../../../static/json/province/gansu.json',level:'省级'},
                {name: '四川',path:'../../../static/json/province/sichuan.json',level:'省级'},
                {name: '重庆',path:'../../../static/json/province/chongqing.json',level:'省级'},
                {name: '广西',path:'../../../static/json/province/guangxi.json',level:'省级'},
                {name: '广东',path:'../../../static/json/province/guangdong.json',level:'省级'},
                {name: '福建',path:'../../../static/json/province/fujian.json',level:'省级'},
                {name: '澳门',path:'../../../static/json/province/aomen.json',level:'省级'},
                {name: '海南',path:'../../../static/json/province/hainan.json',level:'省级'},
                {name: '香港',path:'../../../static/json/province/xianggang.json',level:'省级'},
                {name: '湖南',path:'../../../static/json/province/hunan.json',level:'省级'},
                {name: '江西',path:'../../../static/json/province/jiangxi.json',level:'省级'},
                {name: '台湾',path:'../../../static/json/province/taiwan.json',level:'省级'},
                {name: '安徽',path:'../../../static/json/province/anhui.json',level:'省级'},
                {name: '湖北',path:'../../../static/json/province/hubei.json',level:'省级'},
                {name: '河南',path:'../../../static/json/province/henan.json',level:'省级'},
                {name: '河北',path:'../../../static/json/province/hebei.json',level:'省级'},
                {name: '山西',path:'../../../static/json/province/shanxi.json',level:'省级'},
                {name: '北京',path:'../../../static/json/province/beijing.json',level:'省级'},
                {name: '天津',path:'../../../static/json/province/tianjin.json',level:'省级'},
                {name: '黑龙江',path:'../../../static/json/province/heilongjiang.json',level:'省级'},
                {name: '吉林',path:'../../../static/json/province/jilin.json',level:'省级'},
                {name: '辽宁',path:'../../../static/json/province/liaoning.json',level:'省级'},
                {name: '山东',path:'../../../static/json/province/shandong.json',level:'省级'},
                {name: '江苏',path:'../../../static/json/province/jiangsu.json',level:'省级'},
                {name: '浙江',path:'../../../static/json/province/zhejiang.json',level:'省级'},
                {name: '上海',path:'../../../static/json/province/shanghai.json',level:'省级'},
                {name: '云南',path:'../../../static/json/province/yunnan.json',level:'省级'},
                {name: '陕西',path:'../../../static/json/province/shanxi1.json',level:'省级'},
              ],
              title:'',
              echartsData:['中国'],
              chainPath: "/recovery/truckhospital/statisticProvincePlasticWeight",
              provincePath: '/recovery/truckhospital/statisticCityPlasticWeight',
              cityPath: '/recovery/truckhospital/statisticPlatPlasticWeight',
              platPath: '/recovery/truckhospital/statisticHospitalPlasticWeight',
              makerDetail:[]
          }
    },
    watch: {  
        flag(newValue, oldValue) {  
            this.echartsData = ['中国']
            console.log(newValue, oldValue);
            if(newValue == '玻璃'){
                this.chainPath = '/recovery/truckhospital/statisticProvinceGlassWeight';
                this.provincePath = '/recovery/truckhospital/statisticCityGlassWeight';
                this.cityPath = '/recovery/truckhospital/statisticPlatGlassWeight';
                this.platPath = '/recovery/truckhospital/statisticHospitalGlassWeight';
            }else{
                this.chainPath = "/recovery/truckhospital/statisticProvincePlasticWeight";
                this.provincePath = '/recovery/truckhospital/statisticCityPlasticWeight';
                this.cityPath = '/recovery/truckhospital/statisticPlatPlasticWeight';
                this.platPath = '/recovery/truckhospital/statisticHospitalPlasticWeight';
            }
            this.creatEchart('中国','../../../static/json/china.json',this.chainPath);
        }  
    },
    mounted(){
        var userType= sessionStorage.getItem('userType');
        var url;
        if(sessionStorage.getItem('userType') == '国家管理单位'){
            url = this.chainPath;
        }else if(sessionStorage.getItem('userType') == '省管理单位'){
            url = this.provincePath;
        }else if(sessionStorage.getItem('userType') == '市管理单位'){
            url = this.cityPath;
        }
        this.$nextTick(() => {
            this.creatEchart('中国','../../../static/json/china.json',this.chainPath);
        });
    },
    methods: {
        backTop(name){
            console.log(name)
            var that = this;

            that.baiduIfMap = false;
            if(this.echartsData.length > 1){
                var obj = {};
                obj = this.arrayData.find(function(item) {//obj是选中的对象
                    return item.name === name;
                })
                console.log(obj)
                if(obj.level == '全国'){
                    that.creatEchart('中国','../../static/json/china.json',that.chainPath);
                    that.echartsData = ["中国"];
                }else if(obj.level == '省级'){
                    var json = obj.path;
                    var url = that.provincePath;
                    var params = {
                        name: obj.name
                    };
                    that.creatEchart(obj.name,json,url,params)
                    that.echartsData = ['中国',name];
                }else if(obj.level == '市级'){
                    var json = obj.path;
                    var url = that.cityPath;
                    var params = {
                        name: obj.name
                    };
                    that.creatEchart(obj.name,json,url,params)
                    that.echartsData.indexOf(obj.name);
                    for(var i = 0 ; i < that.echartsData.length ; i++){
                        if(that.echartsData[i.name] == obj.name){
                            that.echartsData.splice(i,1)
                        }
                    }
                }
            }
        },
        creatEchart(addressName,json,url,params){
            console.log("params",json,url,params)
            var that = this;
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('mainId'));

            // 先解绑事件再添加事件
            myChart.off("click");
            myChart.on("click", function(params) {
                console.log('点击事件',params)
                console.log(params.name.split(""));
                if(that.echartsData.length >= 3){
                    that.echartsData.push(params.name)
                    that.getBaiduNews(params.name);
                    return;
                }
                var obj = {};
                obj = that.arrayData.find(function(item) {//obj是选中的对象
                    return item.name === params.name;
                })
                
                var mapData = obj;
                that.$emit("mapData",mapData)
                console.log(obj)
                if(obj.level == '省级'){
                    var json = obj.path;
                    var url = that.provincePath;
                    var params = {
                        name: obj.name
                    };
                    that.creatEchart(obj.name,json,url,params)
                    that.echartsData.push(obj.name)
                }else if(obj.level == '市级'){
                    var json = obj.path;
                    var url = that.cityPath;
                    var params = {
                        name: obj.name
                    };
                    that.creatEchart(obj.name,json,url,params)
                    if(that.echartsData.indexOf(obj.name) == -1){
                        that.echartsData.push(obj.name)
                    }
                }
            });
            $.getJSON(json, function (usaJson) {
                console.log('json数据',usaJson)
                echarts.registerMap(addressName, usaJson);
                var tooltip ={
                    trigger: 'item',
                    showDelay: 0,
                    transitionDuration: 0.2,
                    formatter: function (params) {
                        var value = (params.value + '').split('.');
                        value = value[0].replace(/(\d{1,3})(?=(?:\d{3})+(?!\d))/g, '$1,');
                        return params.seriesName + '<br/>' + params.name + ': ' + value;
                    }
                };
                var visualMap ={
                    left: 'left',
                    min: 0,
                    max: 38000000,
                    inRange: {
                        color: ['#313695', '#4575b4', '#74add1', '#abd9e9', '#e0f3f8', '#ffffbf', '#fee090', '#fdae61', '#f46d43', '#d73027', '#a50026']
                    },
                    text: ['High', 'Low'],           // 文本，默认为数值文本
                    calculable: true
                };
                var toolbox ={
                    show: true,
                    //orient: 'vertical',
                    left: 'left',
                    top: 'top',
                    feature: {
                        // dataView: {readOnly: false},
                        // restore: {},
                        // saveAsImage: {}
                    }
                };
                that.$ajax.get(url,{
                    params:params
                }).then(function(response){
                    console.log("12312312",response)
                    myChart.setOption({
                        tooltip: tooltip,
                        visualMap: visualMap,
                        toolbox: toolbox,
                        series: [
                            {
                                name: '全国医废',
                                type: 'map',
                                roam: true,
                                map: addressName,
                                emphasis: {
                                    label: {
                                        show: false
                                    }
                                },
                                // 文本位置修正
                                textFixed: {
                                    Alaska: [20, -20]
                                },
                                data:response.data.list
                            }
                        ]
                    });
                })
                console.log('url',url)
                if(url == '/recovery/truckhospital/statisticCityPlasticWeight' || url == '/recovery/truckhospital/statisticCityGlassWeightString'){
                    for(var i = 0 ; i < usaJson.features.length; i++){
                        let obj = {
                            name: usaJson.features[i].properties.name,
                            path: '../../static/json/citys/'+usaJson.features[i].id+'.json',
                            level: '市级'
                        }
                        that.arrayData.push(obj);
                    }
                    console.log('arrayData',that.arrayData)
                }
            });
            window.addEventListener("resize", () => { myChart.resize();});
        },
        getBaiduNews(name){
            //发送请求
            var that = this;
            this.$ajax.get(this.platPath,{
                params:{
                    name: name
                }
            }).then(function (response) {
                console.log("百度地图信息",response);
                if(response.data.status == 260){
                    that.baiduIfMap = true;
                    that.makerDetail = response.data.list;
                    that.mapLoad();
                }else{
                    // Toast(response.data.message);
                }
            })
        },
        mapLoad(){
            var _this = this;
            // 百度地图API功能
            var map = new BMap.Map("baiDuMap");
            //可缩放
            map.enableScrollWheelZoom(true);
            var address = this.echartsData[0]+this.echartsData[1]+"省"+this.echartsData[2]
            map.centerAndZoom(address, 15);
        
            //遍历创建点和信息框
            for(var i = 0 ; i < _this.makerDetail.length ; i++){
                // var pt = new BMap.Point(_this.makerDetail[i].longitude,_this.makerDetail[i].latitude);
                // var marker = new BMap.Marker(pt);
                // var markerNews = _this.makerDetail[i];
                // var content = "<h5 style='margin:0 0 5px 0;padding:0.2em 0'>"+ _this.makerDetail[i].item +"</h5>" +
                // "<p style='margin:0;line-height:1.5;font-size:13px;padding:0 5px;'>当前状态："+ _this.makerDetail[i].status +"&nbsp;&nbsp;&nbsp;当前等级："+ _this.makerDetail[i].level +"</p>"
                // // "<p style='margin-top:10px;width:80px;height:28px;border-radius:4px;color:#fff;font-size:14px;float:right;background:#2a82ec;line-height:28px;text-align: center;' onclick='lookMaker("+_this.makerDetail[i].id+")'>前往查看</p>";
                // map.addOverlay(marker);               // 将标注添加到地图中
                // //标注添加到点
                // addClickHandler(content,markerNews,marker);
                var localSearch = new BMap.LocalSearch(map);
                // map.clearOverlays();//清空原来的标注
                localSearch.setSearchCompleteCallback(function (searchResult) {
                    var poi = searchResult.getPoi(0);
                    map.centerAndZoom(poi.point, 13);
                    var marker = new BMap.Marker(new BMap.Point(poi.point.lng, poi.point.lat));  // 创建标注，为要查询的地址对应的经纬度
                    map.addOverlay(marker);
                });
                localSearch.search(_this.makerDetail[i].address);
            }
            
            map.setMapStyle({style:'midnight'});
            // //监听点的点击
            // function addClickHandler(content,markerNews,marker){
            //     marker.addEventListener("click",function(e){
            //     openInfo(content,e)//触发详情面板
            //     console.log(markerNews)
            //     _this.getroomAndDevice(markerNews.id)
            //     });
            // }
            // function openInfo(content,e){
            //     var p = e.target;
            //     var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat);
            //     var sContent = content;
            //     var infoWindow = new BMap.InfoWindow(sContent);  // 创建信息窗口对象
            //     map.openInfoWindow(infoWindow,point); //开启信息窗口
            // }
        }
    }
}
</script>
<style scoped>
.mapBox{
    width: 100%;
    height: 100%;
    position: relative;
}
.suyin{
    position: absolute;
    top: 30px;
    left: 4px;
    color: #bbd9ff;
    font-size: 20px;
    z-index: 9;
}
.suyin>span:hover{
    cursor: pointer;
}
#mainId{
  width: 100%;
  height: 100%;
}
#baiDuMap{
    width: 100%;
    height: 95%;
}
</style>


