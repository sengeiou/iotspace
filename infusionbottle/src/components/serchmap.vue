<template>
    <div class="serchMapBox">
        <div id="l-map"></div>
        <div class="serchIpt">
            <el-input  style="width:80%;display:none;" id="suggestId" v-model="serchVal" placeholder="请输入内容"></el-input>
            <el-autocomplete
                class="inline-input"
                v-model="serchVal"
                :fetch-suggestions="querySearch"
                placeholder="请输入内容"
                @select="handleSelect"
                style="width: 80%"
            ></el-autocomplete>
        </div>
    </div>
</template>
<script>
export default {
    props:['pointlng','pointlat'],
    data(){
        return{
            serchMain:null,
            serchVal: null,
            selectData: [],
        }
    },
    watch:{
        pointlng(a,b){
            this.creatMap();
        }
    },
    mounted() {
        this.creatMap();
        console.log(this.pointlng,this.pointlat)
    },
    methods: {
        clearAll(){
            console.log('父组件调用')
            this.selectData = [];
            this.serchVal = null;
            this.pointlng = null;
            this.pointlat = null;
            this.creatMap();
        },
        handleSelect(item) {
            console.log(item);
                // 百度地图API功能
                var that = this;
                // this.serchVal = item.value;
                var myValue = item.province +  item.city +  item.district +  item.street +  item.value;
                var map = new BMap.Map("l-map");
                // map.centerAndZoom(new BMap.Point(116.405338,39.915351),12);                   // 初始化地图,设置城市和地图级别。
                map.enableScrollWheelZoom(true);
                map.clearOverlays();    //清除地图上所有覆盖物
                //根据坐标逆解析地址 
                var geoc = new BMap.Geocoder(); 
                function getCityByCoordinate(rs) { 
                    console.log(rs)
                    that.$emit('serchData',rs);
                } 
                setPlace();
                //监听地图点击并打点
                map.addEventListener("click", function(e){   
                    //通过点击百度地图，可以获取到对应的point, 由point的lng、lat属性就可以获取对应的经度纬度
                    that.serchVal = null;
                    var pt = e.point;
                    map.clearOverlays();
                    map.addOverlay(new BMap.Marker(pt));
                    map.centerAndZoom(pt, 12);
                    geoc.getLocation(pt, function(rs){
                        that.$emit('serchData',rs);
                    }); 
                });
                function setPlace(){
                    map.clearOverlays();    //清除地图上所有覆盖物
                    console.log(myValue)
                    function myFun(){
                        console.log(local.getResults().getPoi(0))
                        var pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
                        map.centerAndZoom(pp, 12);
                        map.addOverlay(new BMap.Marker(pp));    //添加标注
                        // that.$emit('serchData',local.getResults().getPoi(0));
                        console.log(pp)
                        geoc.getLocation(pp, function(rs){
                            that.$emit('serchData',rs);
                        }); 
                    }
                    var local = new BMap.LocalSearch(map, { //智能搜索
                        onSearchComplete: myFun
                    });
                    local.search(myValue);
                }
            
        },
        querySearch(queryString, cb) {
            var results = this.selectData;
            // 调用 callback 返回建议列表的数据
            cb(results);
        },
        creatMap(){
            // 百度地图API功能
            var that = this;
            var map = new BMap.Map("l-map");
            map.clearOverlays();    //清除地图上所有覆盖物
            if(this.pointlng != null && this.pointlat != null){
                map.centerAndZoom(new BMap.Point(this.pointlng,this.pointlat),12);                   // 初始化地图,设置城市和地图级别。
                map.addOverlay(new BMap.Marker(new BMap.Point(this.pointlng,this.pointlat)));
            }else{
                map.centerAndZoom(new BMap.Point(116.405338,39.915351),12);                   // 初始化地图,设置城市和地图级别。
            }
            
            map.enableScrollWheelZoom(true);
            
            //根据坐标逆解析地址 
            var geoc = new BMap.Geocoder(); 


            //监听地图点击并打点
            map.addEventListener("click", function(e){   
                //通过点击百度地图，可以获取到对应的point, 由point的lng、lat属性就可以获取对应的经度纬度
                var pt = e.point;
                map.clearOverlays();
                map.addOverlay(new BMap.Marker(pt));
                map.centerAndZoom(pt, 12);
                geoc.getLocation(pt, getCityByCoordinate); 
            });
            //自动匹配
            var ac = new BMap.Autocomplete(    //建立一个自动完成的对象
                {"input" : "suggestId"
                ,"location" : map,
                "onSearchComplete":function(res){
                    ac.hide();
                    console.log(res);
                    that.selectData = [];
                    for(let i=0;i<res.Ir.length;i++){
                        that.selectData.push({'value':res.Ir[i].business,'id':res.Ir[i].business,province:res.Ir[i].province,city:res.Ir[i].city,district:res.Ir[i].district,street:res.Ir[i].street})
                    }
                },
            });

            // ac.addEventListener("onhighlight", function(e) {  //鼠标放在下拉列表上的事件
            // var str = "";
            //     var _value = e.fromitem.value;
            //     var value = "";
            //     if (e.fromitem.index > -1) {
            //         value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
            //     }    
            //     str = "FromItem<br />index = " + e.fromitem.index + "<br />value = " + value;
                
            //     value = "";
            //     if (e.toitem.index > -1) {
            //         _value = e.toitem.value;
            //         value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
            //     }    
            //     str += "<br />ToItem<br />index = " + e.toitem.index + "<br />value = " + value;
            //     that.serchMain = str;
            // });

            // var myValue;
            // ac.addEventListener("onconfirm", function(e) {    //鼠标点击下拉列表后的事件
            // console.log("地点信息",e)
            // var _value = e.item.value;
            //     myValue = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
            //     that.serchMain ="onconfirm<br />index = " + e.item.index + "<br />myValue = " + myValue;
                
            //     setPlace();
            // });

            function getCityByCoordinate(rs) { 
                console.log(rs)
                that.$emit('serchData',rs);
            } 

            // function setPlace(){
            //     map.clearOverlays();    //清除地图上所有覆盖物
            //     function myFun(){
            //         var pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
            //         map.centerAndZoom(pp, 12);
            //         map.addOverlay(new BMap.Marker(pp));    //添加标注
            //         geoc.getLocation(pp, getCityByCoordinate); 
            //     }
            //     var local = new BMap.LocalSearch(map, { //智能搜索
            //         onSearchComplete: myFun
            //     });
            //     local.search(myValue);
            // }
        }
    }

}
</script>
<style scoped>
.serchMapBox{
    width: 100%;
    height: 100%;
    position: relative;
}
.serchIpt{
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
}

#l-map{height:100%;width:100%;}
</style>