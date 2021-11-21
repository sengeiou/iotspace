<template>
  <div class="hello">
	  <div id="allmap"></div>
    <div class="addressbox">
      {{address}}（{{longitude}},{{latitude}}）
      
      <el-button type="primary" size="mini" @click="chooesAddress()" v-show="ifIsTrue">确 定</el-button>
    </div>
    <div>
    </div>
  </div>
</template>

<script>
export default {
  props:['lng','lat','addressName'],
  data () {
    return {
        address:'',
        longitude:'',
        latitude:'',
        ifIsTrue:false
    }
  },
  watch:{
    addressName(){
      this.mapLoad(this.lng,this.lat);
    }
  },
  mounted:function(){
    
    this.mapLoad(this.lng,this.lat);
  },
  methods:{
    mapLoad(lng,lat){
        // 百度地图API功能
        console.log(lng,lat)
        if(!lng || !lat){
          if(lng == 0 || lat == 0){
            lng = 116.395636;
            lat = 39.929983;
          }
        }
        var that = this;
        var map = new BMap.Map("allmap");
        var geolocation = new BMap.Geolocation();

        if(this.addressName){
          var point = new BMap.Point(lng,lat);
          map.addOverlay(new BMap.Marker(point));
          map.centerAndZoom(point,10);
          console.log(point);
          map.panTo(point);
          that.address = this.addressName;
          that.longitude = lng;
          that.latitude = lat;
          that.ifIsTrue = true;
        }else{
          that.ifIsTrue = false;
          geolocation.getCurrentPosition(function(r){
            if(this.getStatus() == BMAP_STATUS_SUCCESS){
                that.address = r.address.city;
                that.longitude = r.point.lng;
                that.latitude = r.point.lat;
                var point = new BMap.Point(r.point.lng,r.point.lat);
                map.addOverlay(new BMap.Marker(point));
                map.centerAndZoom(point,10);
                map.panTo(r.point);
            }
            else {
                alert('failed'+this.getStatus());
            }        
          },{enableHighAccuracy: true})
        }
        map.enableScrollWheelZoom(true);
        var geoc = new BMap.Geocoder();
        map.addEventListener("click", function(e){   
            //通过点击百度地图，可以获取到对应的point, 由point的lng、lat属性就可以获取对应的经度纬度    
            that.ifIsTrue = true; 
            var pt = e.point;
            geoc.getLocation(pt, function(rs){
                map.clearOverlays();
                var newPoint = new BMap.Point(rs.point.lng,rs.point.lat);
                map.addOverlay(new BMap.Marker(newPoint));
                map.panTo(rs.point);
                that.longitude = rs.point.lng;
                that.latitude = rs.point.lat;
                that.address = rs.address;
                console.log(rs)
                var params = rs.point;
                that.$emit('lngLatData',params)
            });        
        });
    },
    chooesAddress(){
      this.longitude = '';
      this.latitude = '';
      this.address = '';
      this.ifIsTrue = false;
      this.mapLoad(this.lng,this.lat)
      this.$emit('ifIsClose',false)
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
  position: relative;
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
.addressbox{
  position: absolute;
  top: 8px;
  left: 5%;
  width: 90%;
  height: 40px;
  font-size: 16px;
  background: #fff;
  line-height: 40px;
  text-align: center;
  font-weight: bold;
  border-radius: 4px;
}
</style>
