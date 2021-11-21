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
                {name: '中国',path:'static/json/china.json',level:'全国'},
                {name: '内蒙古',path:'static/json/province/neimenggu.json',level:'省级'},
                {name: '新疆',path:'static/json/province/xinjiang.json',level:'省级'},
                {name: '西藏',path:'static/json/province/xizang.json',level:'省级'},
                {name: '贵州',path:'static/json/province/guizhou.json',level:'省级'},
                {name: '青海',path:'static/json/province/qinghai.json',level:'省级'},
                {name: '宁夏',path:'static/json/province/ningxia.json',level:'省级'},
                {name: '甘肃',path:'static/json/province/gansu.json',level:'省级'},
                {name: '四川',path:'static/json/province/sichuan.json',level:'省级'},
                {name: '重庆',path:'static/json/province/chongqing.json',level:'市级'},
                {name: '重庆市',path:'static/json/province/chongqing.json',level:'市级'},
                {name: '广西',path:'static/json/province/guangxi.json',level:'省级'},
                {name: '广东',path:'static/json/province/guangdong.json',level:'省级'},
                {name: '福建',path:'static/json/province/fujian.json',level:'省级'},
                {name: '澳门',path:'static/json/province/aomen.json',level:'省级'},
                {name: '海南',path:'static/json/province/hainan.json',level:'省级'},
                {name: '香港',path:'static/json/province/xianggang.json',level:'省级'},
                {name: '湖南',path:'static/json/province/hunan.json',level:'省级'},
                {name: '江西',path:'static/json/province/jiangxi.json',level:'省级'},
                {name: '台湾',path:'static/json/province/taiwan.json',level:'省级'},
                {name: '安徽',path:'static/json/province/anhui.json',level:'省级'},
                {name: '湖北',path:'static/json/province/hubei.json',level:'省级'},
                {name: '河南',path:'static/json/province/henan.json',level:'省级'},
                {name: '河北',path:'static/json/province/hebei.json',level:'省级'},
                {name: '山西',path:'static/json/province/shanxi.json',level:'省级'},
                {name: '北京',path:'static/json/province/beijing.json',level:'市级'},
                {name: '北京市',path:'static/json/province/beijing.json',level:'市级'},
                {name: '天津',path:'static/json/province/tianjin.json',level:'市级'},
                {name: '天津市',path:'static/json/province/tianjin.json',level:'市级'},
                {name: '黑龙江',path:'static/json/province/heilongjiang.json',level:'省级'},
                {name: '吉林',path:'static/json/province/jilin.json',level:'省级'},
                {name: '辽宁',path:'static/json/province/liaoning.json',level:'省级'},
                {name: '山东',path:'static/json/province/shandong.json',level:'省级'},
                {name: '江苏',path:'static/json/province/jiangsu.json',level:'省级'},
                {name: '浙江',path:'static/json/province/zhejiang.json',level:'省级'},
                {name: '上海',path:'static/json/province/shanghai.json',level:'市级'},
                {name: '上海市',path:'static/json/province/shanghai.json',level:'市级'},
                {name: '云南',path:'static/json/province/yunnan.json',level:'省级'},
                {name: '陕西',path:'static/json/province/shanxi1.json',level:'省级'},
              ],
              title:'',
              echartsData:[],
              chainPath: "/recovery/truckhospital/statisticProvincePlasticWeight",
              provincePath: '/recovery/truckhospital/statisticCityPlasticWeight',
              cityPath: '/recovery/truckhospital/statisticPlatPlasticWeight',
              platPath: '/recovery/truckhospital/statisticHospitalPlasticWeight',
              makerDetail:[],
              echartMap:null,
              getConfig:{
                addressName:null,
                json: null,
                url: null,
                params: null
            },
            index: null,
          }
    },
    watch: {  
        flag(newValue, oldValue) {  
            console.log("flag数据监听",newValue, oldValue);
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
            var url;
            if(this.getConfig.url == '/recovery/truckhospital/statisticCityPlasticWeight' || this.getConfig.url == '/recovery/truckhospital/statisticCityGlassWeight'){
                url = this.provincePath;
                this.creatEchart(this.getConfig.addressName,this.getConfig.json,url,this.getConfig.params);
            }else if(this.getConfig.url == '/recovery/truckhospital/statisticPlatPlasticWeight' || this.getConfig.url == '/recovery/truckhospital/statisticPlatGlassWeight'){
                url = this.cityPath
                this.creatEchart(this.getConfig.addressName,this.getConfig.json,url,this.getConfig.params);
            }else if(this.getConfig.url == '/recovery/truckhospital/statisticHospitalGlassWeight' || this.getConfig.url == '/recovery/truckhospital/statisticPlatGlassWeight'){
                url = this.cityPath
            }else if(this.getConfig.url == '/recovery/truckhospital/statisticProvinceGlassWeight' || this.getConfig.url == '/recovery/truckhospital/statisticProvincePlasticWeight'){
                url = this.chainPath
                this.creatEchart(this.getConfig.addressName,this.getConfig.json,url,this.getConfig.params);
            }

        }  
    },
    mounted(){
        this.$nextTick(() => {
            this.creatEchartMap();
            this.getMapAdmin();
        });
    },
    methods: {
        creatEchartMap(){
            this.echartMap = echarts.init(document.getElementById('mainId'));
        },
        //权限
        getMapAdmin(){
            var userType= sessionStorage.getItem('userType');
            var fatherName = sessionStorage.getItem('userFatherAddress');
            var name = sessionStorage.getItem('userAddress');
            var obj = {};
            var url;
            var that = this;
            this.baiduIfMap = false;
            if(sessionStorage.getItem('userType') == '国家管理单位'){
                this.echartsData = ["中国"];
                this.creatEchart('中国','static/json/china.json',this.chainPath);
            }else if(sessionStorage.getItem('userType') == '省管理单位' && name != '北京' && name != '上海' && name != '天津' && name != '重庆'){
                var addressName;
                if(name.indexOf('省') != -1){
                    addressName = name.substring(0, name.lastIndexOf('省'));
                    console.log(addressName)
                }else{
                    addressName = name;
                }
                obj = this.arrayData.find(function(item) {//obj匹配得到当前用户所属行政区的json文件
                    return item.name === addressName;
                })
                var params = {//请求接口参数
                    address: obj.name
                };
                this.echartsData = [addressName];
                this.creatEchart(addressName,obj.path,this.provincePath,params);
            }else if(sessionStorage.getItem('userType') == '市管理单位' || name == '北京' || name == '上海' || name == '天津' || name == '重庆'){
                console.log(name,fatherName)
                if(name.indexOf('北京') != -1 || name.indexOf('天津') != -1  || name.indexOf('上海') != -1  || name.indexOf('重庆') != -1 ){
                    obj = this.arrayData.find(function(item) {//obj匹配得到当前用户所属上级行政区的json文件
                        return item.name === name;
                    })
                    var params = {//请求接口参数
                        address: obj.name,
                    };
                    that.echartsData = [obj.name];
                    that.creatEchart(obj.name,obj.path,that.cityPath,params);
                }else if(name == '济源市'){
                    var objs = {
                            name: name,
                            level: '市级',
                            father: fatherName
                        }
                    that.getBaiduNews(name,fatherName,objs);
                }else{
                    obj = this.arrayData.find(function(item) {//obj匹配得到当前用户所属上级行政区的json文件
                        return item.name === fatherName;
                    })
                console.log('shi',obj)
                //根据上级行政区的json文件获取到当前的行政区的json
                $.getJSON(obj.path, function (json) {
                    for(var i = 0 ; i < json.features.length; i++){
                        if(json.features[i].properties.name == name){
                            var code = json.features[i].id;
                            var params = {//请求接口参数
                                address: name,
                            };
                            let obj = {
                                name: name,
                                path: 'static/json/citys/'+code+'.json',
                                level: '市级'
                            }
                            that.arrayData.push(obj)
                            that.echartsData = [name];
                            that.creatEchart(name,'static/json/citys/'+code+'.json',that.cityPath,params);
                        }
                    }
                })
                }
            }else if(sessionStorage.getItem('userType') == '区管理单位'){
                    console.log("asdasdasd")
                    var objs = {
                            name: name,
                            level: '县级',
                            father: fatherName
                        }
                that.getBaiduNews(name,fatherName,objs);
            }else if(sessionStorage.getItem('userType') == '医疗机构'){
                that.creatBaiduMap();
            }
        },
        backTop(name){
            var that = this;
            if(this.echartsData.length > 1){
                console.log(name,this.arrayData)
                var obj = {};
                obj = this.arrayData.find(function(item) {//obj匹配得到当前用户所属行政区的json文件
                    return item.name === name;
                })
                console.log(obj)
                var mapData = obj;
                that.$emit("mapData",mapData)
                if(obj.level == '全国'){
                    that.baiduIfMap = false;
                    that.creatEchart('中国','static/json/china.json',that.chainPath);
                }else if(obj.level == '省级' && name != '北京' && name != '上海' && name != '天津' && name != '重庆'){
                    that.baiduIfMap = false;
                    var json = obj.path;
                    var url = that.provincePath;
                    var params = {
                        address: obj.name
                    };
                    that.creatEchart(obj.name,json,url,params)
                }else if(obj.level == '市级' || name == '北京' || name == '上海' || name == '天津' || name == '重庆'){
                    if(name == '济源市'){
                        return;
                    }
                    that.baiduIfMap = false;
                    var json = obj.path;
                    var url = that.cityPath;
                    var params = {
                            address: obj.name
                        };
                    
                    that.creatEchart(obj.name,json,url,params)
                }
                var index = that.echartsData.indexOf(name)+1;
                that.echartsData.splice(index);
                
            }
        },
        creatEchart(addressName,json,url,params){
            this.getConfig={
                addressName:addressName,
                json: json,
                url: url,
                params: params
            }
            console.log("beijnqqqg",this.getConfig)
            var that = this;
            // 基于准备好的dom，初始化echarts实例
            // var myChart = echarts.init(document.getElementById('mainId'));
            $.getJSON(json, function (usaJson) {
                console.log(usaJson)
                echarts.registerMap(addressName, usaJson);
                var tooltip ={
                    trigger: 'item',
                    showDelay: 0,
                    transitionDuration: 0.5,
                    formatter: function (params) {
                        var value = (params.value + '').split('.');
                        value = value[0].replace(/(\d{1,3})(?=(?:\d{3})+(?!\d))/g, '$1,');
                        return params.name + ': ' + value;
                        
                    }
                };
                var visualMap ={
                    left: 'left',
                    // min: 0,
                    // max: 38000000,
                    inRange: {
                        color: ['#abd9e9','#74add1', '#4575b4','#313695']
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
                var geo = {
                    map: addressName,
                    roam: false,//不开启缩放和平移
                    zoom:1.2,//视角缩放比例
                    label: {
                        normal: {
                            show: false,
                            fontSize:'10',
                            color: 'rgba(0,0,0,0.7)'
                        },
                        emphasis: {
                            // show: false,
                            fontSize: '14'
                        }
                    },
                    itemStyle: {
                        normal:{
                            borderColor: 'rgba(0, 0, 0, 0.2)'
                        },
                        emphasis:{
                            areaColor: '#F3B329',//鼠标选择区域颜色
                            shadowOffsetX: 0,
                            shadowOffsetY: 0,
                            shadowBlur: 20,
                            borderWidth: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }
                };
                that.$ajax.get(url,{
                    params:params
                }).then(function(response){
                    console.log("地图",response)
                    if(response.data.status == 260){
                        var array = [];
                        if(response.data.list.length > 1){
                            for(var i = 0 ; i < response.data.list.length ; i++){
                                array.push(response.data.list[i].value);
                            }
                            array.sort(function (a, b) {
                                return a-b;
                            });
                            visualMap.min = array[0];
                            visualMap.max = array[array.length-1];
                        }else{
                            visualMap.min = response.data.list[0].value/2;
                            visualMap.max = response.data.list[0].value;
                        }
                        var obj = {};
                        obj = that.arrayData.find(function(item) {//obj是选中的对象
                            return item.name === addressName;
                        })
                        var mapData = obj;
                        that.$emit("mapData",mapData);
                        var option = {
                            tooltip: tooltip,
                            visualMap: visualMap,
                            toolbox: toolbox,
                            geo: geo,
                            series: [
                                {
                                    name: '',
                                    type: 'map',
                                    geoIndex: 0,
                                    data:response.data.list
                                }
                            ]
                        };
                        if(that.echartsData.indexOf(addressName) == -1){
                            that.echartsData.push(addressName)
                        }
                        console.log('option',option)
                        setTimeout(function () {
                            that.echartMap.setOption(option);
                        },500)
                    }else{
                        that.$message({
                            type: 'error',
                            message: '当前区域暂无数据'
                        });
                    }
                })
                if(url == '/recovery/truckhospital/statisticCityPlasticWeight' || url == '/recovery/truckhospital/statisticCityGlassWeight'){
                    for(var i = 0 ; i < usaJson.features.length; i++){
                        let obj = {
                            name: usaJson.features[i].properties.name,
                            path: 'static/json/citys/'+usaJson.features[i].id+'.json',
                            level: '市级'
                        }
                        that.arrayData.push(obj);
                    }
                }else if(url == '/recovery/truckhospital/statisticPlatPlasticWeight' || url == '/recovery/truckhospital/statisticPlatGlassWeight'){
                    for(var i = 0 ; i < usaJson.features.length; i++){
                        let obj = {
                            name: usaJson.features[i].properties.name,
                            father: addressName,
                            level: '县级'
                        }
                        that.arrayData.push(obj);
                    }
                }
                console.log(that.arrayData)
                that.echartMap.resize();
            });
            window.addEventListener("resize", () => { this.echartMap.resize();});
            // 先解绑事件再添加事件
            this.echartMap.off("click");
            this.echartMap.on("click", function(res) {
                var obj = {};
                obj = that.arrayData.find(function(item) {//obj是选中的对象
                    return item.name === res.name;
                })

                    console.log("123123123123123123123123123",obj,that.echartsData)
                if(obj.level == '省级'){
                    var json = obj.path;
                    var url = that.provincePath;
                    var params = {
                        address: obj.name
                    };
                    that.creatEchart(obj.name,json,url,params)
                }else if(obj.level == '市级'){
                    if(obj.name == '济源市'){
                        console.log(obj)
                        var objs = {
                            name: obj.name,
                            level: obj.level,
                            father: that.echartsData[that.echartsData.length-1]
                        }
                        that.getBaiduNews(obj.name,that.echartsData[that.echartsData.length-1],objs);
                    }else{
                        var json = obj.path;
                        var url = that.cityPath;
                        var params = {
                            address: obj.name,
                            paddress: that.echartsData[that.echartsData.length-1]
                        };
                        that.creatEchart(obj.name,json,url,params)
                    }
                }else if(obj.level == '县级'){
                    console.log("县级",that.echartsData)
                    that.getBaiduNews(obj.name,that.echartsData[1],obj);
                }
                console.log(that.arrayData)
            });
        },
        getBaiduNews(name,father,obj){
            //发送请求
            console.log(name,father,obj)
            var that = this;
            this.$ajax.get(this.platPath,{
                params:{
                    address: name,
                    paddress: obj.father
                }
            }).then(function (response) {
                console.log("百度地图信息",response);
                if(response.data.status == 260){
                    that.$emit("mapData",obj);
                    if(that.echartsData.indexOf(name) == -1){
                        that.echartsData.push(name)
                    }
                    that.baiduIfMap = true;
                    that.makerDetail = response.data.list;
                    that.mapLoad();
                }else{
                    that.$message.error('当前区域无数据')
                }
            })
        },
        mapLoad(){
            var that = this;
            // 百度地图API功能
            var map = new BMap.Map("baiDuMap",{ enableMapClick: false });
            //可缩放
            map.enableScrollWheelZoom(true);
            var address = this.echartsData[0]+this.echartsData[1]+"省"+this.echartsData[2]
            map.centerAndZoom(address, 11);

            // 创建地址解析器实例
            var myGeo = new BMap.Geocoder();
            //遍历创建点和信息框
            for(var i = 0 ; i < that.makerDetail.length ; i++){
                var content = "<h5 style='margin:0 0 5px 0;padding:0.1em 0;font-size:17px;'>"+ that.makerDetail[i].name +"</h5>" +
                "<p style='margin:0;line-height:1.5;font-size:13px;padding:0 5px;'>回收总重量："+ that.makerDetail[i].value +"kg</p>"
                // "<p style='margin-top:10px;width:80px;height:28px;border-radius:4px;color:#fff;font-size:14px;float:right;background:#2a82ec;line-height:28px;text-align: center;' onclick='lookMaker("+that.makerDetail[i].id+")'>查看详情</p>";

                postNews({
                    lng: that.makerDetail[i].longitude,
                    lat:that.makerDetail[i].latitude,
                },content,that.makerDetail[i].id)
                
            }
            map.setMapStyle({style:'midnight'});

            function postNews(point,content,id){
                // 将地址解析结果显示在地图上,并调整地图视野
                // myGeo.getPoint(address, function(point){
                //     console.log(address);
                //     if (point) {
                //         console.log(address,point)
                        creatMaker(point,content,id)
                //     }else{

                //         console.log(11111,address,point)
                //         that.$message.warning('您有地址解析有误')
                //     }
                // }, "全国");
            }
            function creatMaker(point,content,id){
                map.centerAndZoom(point, 11);
                var marker = new BMap.Marker(new BMap.Point(point.lng,point.lat));  // 创建标注，为要查询的地址对应的经纬度
                map.addOverlay(marker);
                addClickHandler(content,marker,id);
            }
            // //监听点的点击
            function addClickHandler(content,marker,id){
                marker.addEventListener("click",function(e){
                    openInfo(content,e,id)//触发详情面板
                });
            }
            function openInfo(content,e,id){
                var mapData = {
                    id: id
                }
                that.$emit("mapData",mapData);
                that.$ajax.get('/recovery/supervisionunit/selectByHospitalId',{
                    params:{
                        hospitalid: id,
                    }
                }).then(function (response) {
                    console.log("医院监管单位",response);
                    if(response.data.status == 260){
                        content = content+"<p style='margin:0;line-height:1.5;font-size:13px;padding:0 5px;'>监管单位："+ response.data.object.name +"</p>";
                        var p = e.target;
                        var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat);
                        var sContent = content;
                        var infoWindow = new BMap.InfoWindow(sContent);  // 创建信息窗口对象
                        map.openInfoWindow(infoWindow,point); //开启信息窗口
                    }else{
                        
                    }
                })
                
            }
        },
        creatBaiduMap(){
            var that = this;
            that.baiduIfMap = true;
            // 百度地图API功能
            var map = new BMap.Map("baiDuMap",{ enableMapClick: false });
            //可缩放
            map.enableScrollWheelZoom(true);
            map.setMapStyle({style:'midnight'});
            // 创建地址解析器实例
            var myGeo = new BMap.Geocoder();
            // 将地址解析结果显示在地图上,并调整地图视野
            var address = sessionStorage.getItem("userAddress");
            map.centerAndZoom(address, 11);
            myGeo.getPoint(address, function(point){
                if (point) {
                    creatMaker(point)
                }else{
                    alert("您选择地址没有解析到经纬度!");
                }
            }, "全国");
            function creatMaker(point){
                map.centerAndZoom(point, 11);
                var marker = new BMap.Marker(new BMap.Point(point.lng,point.lat));  // 创建标注，为要查询的地址对应的经纬度
                map.addOverlay(marker);
            }
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


