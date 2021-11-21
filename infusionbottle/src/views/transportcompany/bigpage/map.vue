<template>
    <div class="mapBox">
        <div id="baiDuMap">

        </div>
    </div>
</template>
<script>
import $ from 'jquery'
var echarts = require('echarts');
export default {
    data(){
          return{
              echartsData:[],
          }
    },
    
    mounted(){
        this.getBaiduNews();
        // this.mapLoad();
    },
    methods: {
        getBaiduNews(name,father){
            //发送请求
            var that = this;
            this.$ajax.get('/recovery/transportcompany/hospitalInfo',{
                params:{
                    transportcompanyid: sessionStorage.getItem('belongId')
                }
            }).then(function (response) {
                console.log("百度地图信息",response);
                if(response.data.status == 260){
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
            var address = new BMap.Point(this.makerDetail[0].longitude,this.makerDetail[0].latitude);
            map.centerAndZoom(address, 11);

            // 创建地址解析器实例
            var myGeo = new BMap.Geocoder();
            //遍历创建点和信息框
            for(var i = 0 ; i < that.makerDetail.length ; i++){
                var content = "<h5 style='margin:0 0 5px 0;padding:0.1em 0;font-size:17px;'>"+ that.makerDetail[i].name +"</h5>" +
                "<p style='margin:0;line-height:1.5;font-size:13px;padding:0 5px;'>隶属等级："+ that.makerDetail[i].belonglevel +"</p>"
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
    }
}
</script>
<style scoped>
.mapBox{
    width: 100%;
    height: 100%;
    position: relative;
}
#baiDuMap{
    width: 100%;
    height: 100%;
}
</style>


