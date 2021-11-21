<template>
    <div class="work-info">
        
    </div>
</template>

<script>
//引入省/市/区数据json文件
import { zmGetProvincesArr,zmGetCitiesArr,zmGetAreaArr } from '../common/cityChooes'

export default {
    props:["addressNews","fromFlag"],
    data() {
        return {
        region: '',//三级地址
        province: '',//省
        city: '',//市
        area: '',//县
        provinceCode: '',//省级代码
        cityCode: '', //市级代码
        areaCode: '',//县级代码

        regionInit: false,//禁止地区选择器自动初始化，picker组件会默认进行初始化，导致一进入页面就会默认选中一个初始3级地址
        provinces:[],
        citys:[],
        areas:[],
        taskTypeSwitch: false,
        taskTypeText:'',
        myAddressSlots: [//省
            {
            flex: 1,
            values: [], //省份数组
            className: 'slot1',
            textAlign: 'center'
            },
            //分隔符
            {
            divider: true,
            content: '-',
            className: 'slot2'
            },
            //市
            {
            flex: 1,
            values: [],
            className: 'slot3',
            textAlign: 'center'
            },
            {
            divider: true,
            content: '-',
            className: 'slot4'
            },
            //县
            {
            flex: 1,
            values: [],
            className: 'slot5',
            textAlign: 'center'
            }
        ],
        shengAddress:'',
        cityAddress:'',
        errorAddress:[
            {
            name: '暂无'
            }
        ],
        worksText:'全部'
        }
    },
    watch:{
        addressNews(a,b){
            this.taskTypeText = a;
        }
    },
    beforeMount(){
        this.getAddress();
    },
    mounted (){
        
    },
    methods: {
        getAddress(){
            var _this = this;
            this.$ajax.get(this.baseURL1+"/base/api/province/selectLocaleByProjectId",{
                params:{
                    projectid: sessionStorage.getItem("projectId")
                }
            }).then(function(response){
                console.log("省市区",response)
                if(response.data.status == 260){
                    _this.provinces = response.data.province;
                    _this.citys = response.data.city;
                    _this.areas = response.data.plat;
                    if(_this.fromFlag == 1){
                        _this.provinces.unshift({
                            item:'附近',
                            id: 99999,
                            projectid:14
                        });
                        _this.citys.unshift({
                            item:'智能范围',
                            id: 999991,
                            provinceid: 99999
                        });
                        _this.areas.unshift({
                            item:'500米',
                            id: 9999911,
                            provinceid: 99999,
                            cityid: 999991
                        },{
                            item:'1公里',
                            id: 9999912,
                            provinceid: 99999,
                            cityid: 999991
                        },{
                            item:'2公里',
                            id: 9999913,
                            provinceid: 99999,
                            cityid: 999991
                        },{
                            item:'5公里',
                            id: 9999914,
                            provinceid: 99999,
                            cityid: 999991
                        },{
                            item:'10公里',
                            id: 9999915,
                            provinceid: 99999,
                            cityid: 999991
                        });
                    }
                    if(_this.provinces){
                         _this.$refs.pickertwo.setSlotValues(0,zmGetProvincesArr(_this.provinces));
                    }else{
                        _this.$refs.pickertwo.setSlotValues(2,this.errorAddress);
                    }
                   
                }else{
                    // Toast({
                    //     message: response.data.message,
                    //     position: 'top'
                    // });
                }
            })
        },
        addressChange(picker, values) {
            console.log(values);
            var _this = this;
            if (!values[0]) {

            } else {
            if(values[0].name != _this.shengAddress){
                if(!this.citys.length){
                    picker.setSlotValues(1, this.errorAddress);
                    picker.setSlotValues(2, this.errorAddress);
                }else{
                    console.log(this.citys)
                    picker.setSlotValues(1,zmGetCitiesArr(values[0].id,this.citys));
                }
                this.shengAddress = values[0].name;
            }
            if(values[1]){
                if (values[1].name != _this.cityAddress) {
                        if(values[1].name != "暂无"){
                            if(!this.areas){
                                picker.setSlotValues(2,this.errorAddress);
                            }else{
                                picker.setSlotValues(2,zmGetAreaArr(values[1].id,this.areas));
                            }
                            this.cityAddress = values[1].name;
                        }
                    }
                }
            }
        },
        handleConfirmTackTypeFalse() {
            this.taskTypeSwitch = false;
        },
        handleConfirmTackType() {
            if(this.fromFlag == 1 && this.$refs.pickertwo.getValues()[0].name == "附近"){
                this.$emit("userAddress",this.$refs.pickertwo.getValues()[2]);
                this.worksText = this.$refs.pickertwo.getValues()[2].name;
            }else{
                let address = {
                    province: this.$refs.pickertwo.getValues()[0].name,
                    city: this.$refs.pickertwo.getValues()[1].name,
                    plat: this.$refs.pickertwo.getValues()[2]
                }
                if(address.plat.name == "暂无"){
                    Toast({
                        message: "当前不可选择",
                        position: 'top'
                    });
                    return;
                }else{
                    this.worksText = this.$refs.pickertwo.getValues()[2].name;
                    this.taskTypeText = this.$refs.pickertwo.getValues()[0].name+this.$refs.pickertwo.getValues()[1].name+this.$refs.pickertwo.getValues()[2].name
                    this.$emit('userAddress',address);
                }
            }
            this.taskTypeSwitch = false;
        }
    },
}


</script>

<style scoped>

</style>