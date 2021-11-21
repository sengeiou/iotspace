<template>
  <div class="linkage">
    <div>
      <el-select v-model="selectProvince" filterable @change="selectProvinceFun($event)" placeholder="请选择省份" style="width: 100px">
        <el-option value="" label="全部"></el-option>
        <el-option :value="item" :label="item.label" v-for="(item, index) in city" :key="index"></el-option>
      </el-select>
    </div>
    <div>
      <el-select v-model="selectCity" filterable v-if="selectProvince.label" @change="selectCityFun($event)" placeholder="请选择城市" style="width: 100px">
        <el-option value="" label="全部"></el-option>
        <el-option :value="item" :label="item.label" v-for="(item, index) in cityList" :key="index"></el-option>
      </el-select>
    </div>
    <div>
      <el-select v-model="selectArea" filterable v-if="selectCity.label" @change="selectAreaFun($event)" placeholder="请选择区县" style="width: 100px">
        <el-option value="" label="全部"></el-option>
        <el-option :value="item" :label="item.label" v-for="(item, index) in areaList" :key="index"></el-option>
      </el-select>
    </div>
  </div>
</template>
<script>
import city from '../../static/json/allcity'
export default {
  name: 'linkage',
  props:["provincestr","citystr","platstr"],
  data () {
    return {
      // 整个省市县数据
      city: city,
      // 被选中的市数据
      cityList: [],
      // 被选中的县数据
      areaList: [],
      selectProvince: {},
      selectCity: {},
      selectArea: {}
    }
  },
  mounted () {
  },
  methods: {
    // 省份 市 县联动
    selectProvinceFun (event) {
      console.log(this.selectProvince)
      this.$emit("provinceData",event);
      if (event) {
        this.cityList = event.children
      } else {
        this.cityList = []
      }
      this.areaList = []
      this.$emit('getLawyerListInfo', [event.label, 'province'])
    },
    selectCityFun (event) {
      this.$emit("cityData",event);
      console.log(event)
      if (event) {
        this.areaList = event.children
      } else {
        this.areaList = []
      }
      this.$emit('getLawyerListInfo', [event.label, 'city'])
    },
    selectAreaFun (event) {
      this.$emit("platData",event);
      console.log(event)
      this.$emit('getLawyerListInfo', [event.label, 'area'])
    }
  }
}
</script>
<style scoped>
  .linkage {
    display: flex;
    
  }
  .linkage div {
      margin-right: 5px;
    }
</style>
