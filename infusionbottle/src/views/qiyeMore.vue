<template>
    <div id="yudingBox">
        <div class="headerBox">
            <img src="../assets/header.png"/>
        </div>
        <div class="box">
            <div class="pageTitle">
                医疗机构可回收物中废塑料回收企业信息
            </div>
            <div class="selectBox">
                <el-form ref="form" :model="form" label-width="80px">
                    <el-form-item label="所属省份">
                      <el-select placeholder="请选择所属省份" v-model="form.province"  @change="selectList">
                        <el-option v-for="(item,index) in allProvince" :key="index" :label="item.name" :value="item.name"></el-option>
                      </el-select>
                    </el-form-item>
                    <el-form-item label="批次">
                        <el-input type="number" v-model="form.batch" min="1" @change="selectList"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="warning" size="small" @click="clearSelect" style="font-size:14px">清空筛选</el-button>
                    </el-form-item>
                </el-form>
            </div>
            <div class="tableBox">
                <el-table :data="tableData" style="width: 100%">
                    <el-table-column prop="code" label="企业编码"></el-table-column>
                    <el-table-column prop="name" label="企业名称"></el-table-column>
                    <el-table-column prop="socialcode" label="统一社会信用代码" width="150px"></el-table-column>
                    <el-table-column prop="leadername" label="法人"></el-table-column>
                    <el-table-column prop="leaderphone" label="联系电话"></el-table-column>
                    <el-table-column prop="batch" label="批次" sortable>
                      <template slot-scope="scope">
                        <span>第{{scope.row.batch}}批</span>
                      </template>
                    </el-table-column>
                    <el-table-column prop="address" label="地址"></el-table-column>
                    <el-table-column prop="" label="营业执照">
                        <template slot-scope="scope">
                            <img :src='scope.row.img' @click="lookImg(scope.row.img)" style="width: 90px;height:70px"/>
                        </template>
                    </el-table-column>
                </el-table>
                <div class="pafinationBox">
                <div>
                </div>
                <el-pagination
                    background
                    layout="prev, pager, next"
                    :page-size="pageSize"
                    :total="totalNum"
                    @current-change="changePage"
                    >
                </el-pagination>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
  export default {
      filters: {
            formatDate: function(times) {
                var time = new Date(times);
                var year = time.getFullYear();
                var month = time.getMonth()+1;
                var day = time.getDate();
                var hour = time.getHours();
                var minutes = time.getMinutes();
                if(month <10){
                    month = "0" + month;
                }
                if(hour <10){
                    hour = "0" + hour;
                }
                if(minutes <10){
                    minutes = "0" + minutes;
                }
        
                // return year+"-"+month+"-"+day+" "+hour+":"+minutes;
                return year+"-"+month+"-"+day;
            },
    },
    data() {
      return {
        page: 1,
        pageSize: 10,
        totalNum: 100,
        //表格数据
        tableData: [],
        allProvince:[],
        form:{
            batch: null,
            province: null
        } 
      };
    },
    mounted() {
      this.getData();
      this.getAddressData();
    },
    methods: {
      //获取省份数据
      getAddressData(){
        var that = this;
        this.$ajax.get("../../static/json/allprovince.json").then(function(response){
          console.log(response)
            that.allProvince = response.data;
        })
      },
      //获取区域信息
      getData(){
        var that = this;
        this.$ajax.get("/recovery/treatmentplant/homeSelectByPage",{
            params:{
              page: this.page,
              rows: this.pageSize,
              batch: this.form.batch,
              province: this.form.province
            }
        }).then(function(response){
          console.log(response)
          if(response.data.status == 260){
            that.tableData = response.data.list;
            that.totalNum = response.data.info.total;
          }else{
            that.tableData = [];
            that.totalNum = 0;
          }
        })
      },
      //分页换页
      changePage(page){
        this.page = page;
        this.getData();
      },
      //查询
      selectList(){
        this.page = 1;
        this.getData();
      },
      //清空创建编辑form
      clearSelect(){
        this.form = {
            batch: null,
            province: null
        } 
        this.getData();
      },
    }
  }
</script>

<style scoped>
#yudingBox {
    width: 1200px;
    min-height: 100vh;
    margin: 0 auto;
    background: #F5F7F9;
}
.headerBox{
    width: 1200px;
    margin: 0 auto;
    height: 130px;
    display: flex;
    align-items: center;
    justify-content: center;
}
.headerBox > img{
    width: 540px;
    height: 90px;
}
.box{
    width: 100%;
    height: 100%;
}
.pageTitle{
    width: 95%;
    margin: 0 auto;
    height: 50px;
    font-size: 20px;
}
.tableBox{
  width: 95%;
  margin: auto;
}
.pafinationBox{
  width: 100%;
  height: 100px;
  display: flex;
  align-items: center;
  justify-content: space-between;
}
.selectBox{
  width: 95%;
  margin: auto;
}
.selectBox .el-form{
  width: 100%;
  height: 50px;
  margin-top: 20px;
  display: flex;
  align-items: center;
  justify-content: flex-start;
}
.selectBox .selectBox .el-form-item{
  margin-left: 20px;
  margin-bottom: 0px;
}
.selectBox .el-form-item:first-child{
  margin-left: 0;
}
</style>
