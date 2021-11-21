<template>
    <div>
        <el-select v-model="addressData.proid" placeholder="请选择省" v-if="userType == '国家管理员'" style="width: 100px" @change="getCityList(addressData.proid)">
            <el-option v-for="(item,index) in proLists" :key="index"  :label="item.address" :value="item.id"></el-option>
        </el-select>
        <el-select v-model="addressData.cityid" placeholder="请选择市" v-if="userType != '区管理单位' && userType != '市管理单位'" style="width: 100px" @change="getPlatList(addressData.cityid)">
            <el-option v-for="(item,index) in cityLists" :key="index"  :label="item.address" :value="item.id"></el-option>
        </el-select>
        <el-select v-model="addressData.platid" placeholder="请选择区" v-if="userType != '区管理单位'" style="width: 100px" @change="sendaddData(addressData.platid)">
            <el-option v-for="(item,index) in platLists" :key="index"  :label="item.address" :value="item.id"></el-option>
        </el-select>
    </div>
</template>

<script>
export default {
    props:["clearSelectFlag"],
    data() {
        return {
          userType: sessionStorage.getItem("userType"),
          proLists: [],
          cityLists: [],
          platLists: [],
          addressData:{
            proid: null,
            cityid: null,
            platid: null,
          },
          selectData:{
            proname: null,
            cityname: null,
            platname: null,
          },
          flag: 0
        }
    },
    watch:{
            clearSelectFlag(a,b){
                console.log(a,b)
                if(a == 1){
                    this.addressData = {
                        proid: null,
                        cityid: null,
                        platid: null,
                    };
                    this.selectData = {
                        proname: null,
                        cityname: null,
                        platname: null,
                    };
                }
            }
        },
    mounted (){
        console.log(sessionStorage.getItem('userType'))
        if(sessionStorage.getItem('userType') == '国家管理单位'){
            this.getProList(sessionStorage.getItem('belongId'));
        }else if(sessionStorage.getItem('userType') == '省管理单位'){
            this.getCityList(sessionStorage.getItem('belongId'));
        }else if(sessionStorage.getItem('userType') == '市管理单位'){
            this.getPlatList(sessionStorage.getItem('belongId'));
        }
    },
    methods: {
        //获取省
        getProList(id){
            var that = this;
            this.$ajax.get("/recovery/supervisionunit/selectByPid",{
                params:{
                    pid: id
                }
            }).then(function(response){
                console.log(123123,response)
                if(response.data.status == 260){
                    that.proLists = response.data.list;
                    console.log(that.proLists)
                }else{
                    that.proLists = [];
                }
            })
        },
        getCityList(id){
            var that = this;
            this.addressData.cityid = null;
            this.addressData.platid = null;

            this.selectData.cityname = null;
            this.selectData.platname = null;
            if(sessionStorage.getItem('userType') == '国家管理单位'){
                var obj = {};
                obj = this.proLists.find(function(item) {
                    return item.id === id;
                })
                this.selectData.proname = obj.address;
                this.$emit('addressData',this.selectData)
            }
            this.$ajax.get("/recovery/supervisionunit/selectByPid",{
                params:{
                    pid: id
                }
            }).then(function(response){
                console.log(123123,response)
                if(response.data.status == 260){
                    that.cityLists = response.data.list;
                }else{
                    that.cityLists = [];
                }
            })
        },
        getPlatList(id){
            var that = this;
            this.addressData.platid = null;

            this.selectData.platname = null;
            if(sessionStorage.getItem('userType') == '国家管理单位' || sessionStorage.getItem('userType') == '省管理单位'){
                var obj = {};
                obj = this.cityLists.find(function(item) {
                    return item.id === id;
                })
                this.selectData.cityname = obj.address;
                this.$emit('addressData',this.selectData);
            }
            this.$ajax.get("/recovery/supervisionunit/selectByPid",{
                params:{
                    pid: id
                }
            }).then(function(response){
                console.log(123123,response)
                if(response.data.status == 260){
                    that.platLists = response.data.list;
                }else{
                    that.platLists = [];
                }
            })
        },
        sendaddData(id){
            var obj = {};
            obj = this.platLists.find(function(item) {
                return item.id === id;
            })
            this.selectData.platname = obj.address;
            this.$emit('addressData',this.selectData)
        }
    },
}


</script>

<style scoped>

</style>