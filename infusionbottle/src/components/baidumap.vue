<template>
  <div class="hello">
	  <div id="allmap"></div>
  </div>
</template>

<script>
export default {
  data () {
    return {
        //地图中心点
        longitude:'',
        latitude:'',
        
        makerListLength:Number,
        makerDetail:[],
    }
  },
  mounted:function(){
    this.getProject();
  },
  methods:{
    //获取初始化信息
    getProject(){
      //发送请求
      var _this = this;
      this.$ajax.get('/truckhospital/statisticHospitalPlasticWeight',{
        params:{
          name: sessionStorage.getItem('projectId')
        }
      }).then(function (response) {
          console.log("默认信息",response)

          if(response.data.status == 260){
            if(response.data.project.longitude == null){
              _this.longitude = 116.413384;
              _this.latitude = 39.91092;
            }else{
              _this.longitude = response.data.project.longitude;
              _this.latitude = response.data.project.latitude;
            }
            _this.makerDetail = response.data.bimlist;
            _this.mapLoad();
          }else{
            // Toast(response.data.message);
          }
      })
    },
    //获取节点详情
    getroomAndDevice(id){
      //发送请求
      var _this = this;
      this.$ajax.get(this.baseURL1+'/base/room/selectByBimId',{
        params:{
          bimid: id
        }
      }).then(function (response) {
          console.log("节点信息",response)
          if(response.data.status == 260){
                _this.$emit("roomList",response.data.list)
          }else{
            // Toast(response.data.message);
          }
      })
    },
    mapLoad(){
      var _this = this;
      // 百度地图API功能
      var map = new BMap.Map("allmap");
      //可缩放
      map.enableScrollWheelZoom(true);
      map.centerAndZoom(new BMap.Point(_this.longitude,_this.latitude), 15);
//       map.setMapStyleV2({
// 　　　　 //setMapStyleV2方法需要在地图初始化（centerAndZoom）完成后执行；
//   　　　　styleId: '0e4b513d64936695abea2e9fd4ceedbf' 　　　　 //styleId值在平台发布地图时生成
//   　　});
      //遍历创建点和信息框
      for(var i = 0 ; i < _this.makerDetail.length ; i++){
        var pt = new BMap.Point(_this.makerDetail[i].longitude,_this.makerDetail[i].latitude);
      	// var myIcon = new BMap.Icon(require("../assets/normal.png"), new BMap.Size(35,35));
        // var marker = new BMap.Marker(pt,{icon:myIcon});
        var marker = new BMap.Marker(pt);
        var markerNews = _this.makerDetail[i];
        var content = "<h5 style='margin:0 0 5px 0;padding:0.2em 0'>"+ _this.makerDetail[i].item +"</h5>" +
        "<p style='margin:0;line-height:1.5;font-size:13px;padding:0 5px;'>当前状态："+ _this.makerDetail[i].status +"&nbsp;&nbsp;&nbsp;当前等级："+ _this.makerDetail[i].level +"</p>"
        // "<p style='margin-top:10px;width:80px;height:28px;border-radius:4px;color:#fff;font-size:14px;float:right;background:#2a82ec;line-height:28px;text-align: center;' onclick='lookMaker("+_this.makerDetail[i].id+")'>前往查看</p>";
        map.addOverlay(marker);               // 将标注添加到地图中
        //标注添加到点
        addClickHandler(content,markerNews,marker);
      }
      
      map.setMapStyle({style:'midnight'});
      //监听点的点击
      function addClickHandler(content,markerNews,marker){
        marker.addEventListener("click",function(e){
          openInfo(content,e)//触发详情面板
          console.log(markerNews)
          _this.getroomAndDevice(markerNews.id)
        });
      }
      function openInfo(content,e){
        var p = e.target;
        var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat);
        var sContent = content;
        var infoWindow = new BMap.InfoWindow(sContent);  // 创建信息窗口对象
        map.openInfoWindow(infoWindow,point); //开启信息窗口
      }
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.hello{
  width: 100%;
  height: 100%;
  margin: 0 auto;
}
#allmap {
  width: 100%;
  height: 100%;
  overflow: hidden;
  margin:0;
  font-family:"微软雅黑";
  }
#l-map{
  height:100%;
  width:78%;
  float:left;
  border-right:2px solid #bcbcbc;
  }
#r-result{
  height:100%;
  width:20%;
  float:left;
  }

</style>
