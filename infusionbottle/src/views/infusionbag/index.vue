<template>
    <div id="yudingBox">
        <div class="selectBox">
          <el-form ref="form" :model="form" label-width="60px">
            <el-form-item label="条码">
              <el-input v-model="form.barcode" @change="selectList"></el-input>
            </el-form-item>
            <el-form-item label="名称">
              <el-input v-model="form.name" @change="selectList"></el-input>
            </el-form-item>
            <el-form-item label="状态">
              <el-select v-model="form.status" placeholder="请选择状态" @change="selectList">
                <el-option v-for="(item,index) in statusList" :key="index" :label="item.text"  :value="item.value"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="时间">
              <el-col :span="11">
                <el-date-picker type="date" placeholder="起始日期" value-format="yyyy/MM/dd" v-model="form.starttime" @change="selectList" style="width: 100%;"></el-date-picker>
              </el-col>
              <el-col :span="11">
                <el-date-picker type="date" placeholder="结束日期" value-format="yyyy/MM/dd" v-model="form.endtime" @change="selectList" style="width: 100%;"></el-date-picker>
              </el-col>
            </el-form-item>
          </el-form>
        </div>
        <!-- 输液袋列表 -->
        <div class="tableBox">
            <el-table :data="tableData" style="width: 100%">

                <el-table-column prop="garbagebag.barcode" label="所属垃圾袋"></el-table-column>
                <el-table-column prop="time" label="时间" width="150" sortable>
                  <template slot-scope="scope">
                    <span v-if="scope.row.time == null"></span>
                    <span v-else>{{scope.row.time | formatDate}}</span>
                  </template>
                </el-table-column>
                <el-table-column prop="barcode" label="条码"></el-table-column>
                <el-table-column prop="name" label="输液袋名称"></el-table-column>
                <el-table-column prop="status" label="状态"></el-table-column>
                <el-table-column prop="note" label="备注"></el-table-column>
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
        
                return year+"-"+month+"-"+day+" "+hour+":"+minutes;
            },
    },
    data() {
      return {
        //用户id和用户类型
        userId: sessionStorage.getItem("userId"),
        userType:sessionStorage.getItem("userType"),
        page: 1,
        pageSize: 5,
        totalNum: 100,
        //筛选
        form: {
          barcode: null,
          garbagebagid: null,
          status:null,
          name:null,
          starttime: null,
          endtime: null
        },
        //弹框title
        addOrUpTit:'',
        
        //表格数据
        tableData: [],
        //垃圾袋列表
        garbagebagList:[],
        //状态列表
        statusList:[],
      }
    },
    mounted() {
      this.getData();
      this.getDepartmentList();
    },
    methods: {
      //获取科室列表
      getDepartmentList(){
        var _this = this;
        var objss = {"value":'',"text":"全部"};
        //状态列表
        this.$ajax.get("/recovery/infusionbag/statusList").then(function(response){
              console.log("status列表",response);
              _this.statusList = response.data.list;
              _this.statusList.unshift(objss);
        })
      },
      //获取垃圾袋信息
      getData(){
        var that = this;
        if(this.form.starttime == null){
          this.form.starttime = "";
        }
        if(this.form.endtime == null){
          this.form.endtime = "";
        }
        this.$ajax.get("/recovery/infusionbag/selectVByPage",{
            params:{
              page: this.page,
              rows: this.pageSize,
              barcode: this.form.barcode,
              garbagebagid: this.$route.query.garbagebagid,
              status: this.form.status,
              name: this.form.name,
              begintime: this.form.starttime==""? "" : new Date(this.form.starttime)/1000*1000,
              endtime: this.form.endtime==""? "" : new Date(this.form.endtime)/1000*1000,
            }
        }).then(function(response){
          console.log(response)
          if(response.data.status == 260){
            that.tableData = response.data.list;
            that.totalNum = response.data.info.total;
          }else{
            that.tableData = [];
            that.totalNum = 0;
            that.$message.error(response.data.message)
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
    }
  }
</script>

<style scoped>
#yudingBox {
    width: 100%;
    margin-top: 20px;
    background: #F5F7F9;
}
.selectBox{
   width: 95%;
    margin:  auto;
    margin-bottom: 20px;
    padding-top: 20px;
    display:flex;
    justify-content: flex-start;
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
.red{
  color: brown
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
.search-box{
  width: 200px;
  margin-top: 15px;
  display: flex;
  justify-content: center;
}
.search-box > span:first-child{
  width:100px;
  height: 40px;
  line-height: 40px;
  color: #666
}
.iptBox{
    width: 90%;
    margin: 0 auto;
    margin-top: .1rem;
    display: flex;
    align-items: center;
    justify-content: flex-start;
}
.iptBox > span{
    font-size: 15px;
    color: #606266;
    display: block;
    width: 68px;
}
</style>
