<template>
    <div id="yudingBox">
        <div class="selectBox">
          <el-form ref="form" :model="form" label-width="60px">
            <!-- <el-form-item label="再利用企业">
              <el-select v-model="form.productionplantid" placeholder="请选择再利用企业" @change="selectList">
                <el-option v-for="(item,index) in productionplantList" :key="index" :label="item.name"  :value="item.id"></el-option>
              </el-select>
            </el-form-item> -->
            <!-- <el-form-item label="提交人">
                  <el-select v-model="form.submitid" placeholder="请选择提交人" @change="selectList">
                    <el-option v-for="(item,index) in submitList" :key="index" :label="item.username"  :value="item.id"></el-option>
                  </el-select>
                </el-form-item>
                <el-form-item label="状态">
                  <el-select v-model="form.status" placeholder="请选择状态" @change="selectList">
                    <el-option v-for="(item,index) in statusList" :key="index" :label="item.text"  :value="item.value"></el-option>
                  </el-select>
                </el-form-item> -->
            <el-form-item label="时间">
              <el-col :span="11">
                <el-date-picker type="date" placeholder="起始日期" value-format="yyyy/MM/dd" v-model="form.starttime" @change="selectList" style="width: 100%;"></el-date-picker>
              </el-col>
              <el-col :span="11">
                <el-date-picker type="date" placeholder="结束日期" value-format="yyyy/MM/dd" v-model="form.endtime" @change="selectList" style="width: 100%;"></el-date-picker>
              </el-col>
            </el-form-item>
            <el-form-item label-width="10px">
                <el-button type="warning" size="small" @click="clearSelect" style="font-size:14px">清空筛选</el-button>
                <el-button type="success" size="small" @click="exportJoinList" style="font-size:14px">导出交接记录</el-button>
            </el-form-item>
          </el-form>
        </div>
        <!-- 用户列表 -->
        <div class="tableBox">
          <p style="height:30px;font-size: 14px">{{$route.query.name}}交接记录：</p>
            <el-table :data="tableData" style="width: 100%" v-if="$route.query.flag == 1">
                <el-table-column prop="time" label="时间" width="150" sortable>
                  <template slot-scope="scope">
                    <span v-if="scope.row.time == null"></span>
                    <span v-else>{{scope.row.time | formatDate}}</span>
                  </template>
                </el-table-column>
                <el-table-column prop="hospitalname" label="医疗机构"></el-table-column>
                <el-table-column prop="treatmentplantname" label="企业名称"></el-table-column>
                <el-table-column prop="transportcompanyname" label="回收物流"></el-table-column>
                <el-table-column prop="number" label="车次"></el-table-column>
                <el-table-column prop="plasticnumber" label="塑料数量(袋)"></el-table-column>
                <el-table-column prop="plasticweight" label="塑料重量(kg)"></el-table-column>
                <el-table-column prop="glassnumber" label="玻璃数量(袋)"></el-table-column>
                <el-table-column prop="glassweight" label="玻璃重量(kg)"></el-table-column>
                <el-table-column prop="status" label="状态"></el-table-column>
                <el-table-column prop="submituser" label="提交人"></el-table-column>
                <el-table-column prop="confirmuser" label="交接人"></el-table-column>
                <el-table-column prop="note" label="备注"></el-table-column>
            </el-table>
            <el-table :data="tableData" style="width: 100%" v-if="$route.query.flag == 0">
                <el-table-column prop="time" label="时间" width="150" sortable>
                  <template slot-scope="scope">
                    <span v-if="scope.row.time == null"></span>
                    <span v-else>{{scope.row.time | formatDate}}</span>
                  </template>
                </el-table-column>
                <el-table-column prop="hospital.name" label="医疗机构"></el-table-column>
                <el-table-column prop="hospital.transportcompany.treatmentplant.name" label="企业名称"></el-table-column>
                <el-table-column prop="hospital.transportcompany.name" label="回收物流"></el-table-column>
                <el-table-column prop="truck.vehicle.number" label="车次"></el-table-column>
                <el-table-column prop="plasticnumber" label="塑料数量(袋)"></el-table-column>
                <el-table-column prop="plasticweight" label="塑料重量(kg)"></el-table-column>
                <el-table-column prop="glassnumber" label="玻璃数量(袋)"></el-table-column>
                <el-table-column prop="glassweight" label="玻璃重量(kg)"></el-table-column>
                <el-table-column prop="status" label="状态"></el-table-column>
                <el-table-column prop="submitUser.realname" label="提交人"></el-table-column>
                <el-table-column prop="confirmUser.realname" label="交接人"></el-table-column>
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
            submitid: null,
            status: null,
            starttime: null,
            endtime: null
        },
        //表格数据
        tableData: [],
        submitList:[],
        statusList:[],
      };
    },
    mounted() {
      this.getData();
      // this.getDepartmentList();
    },
    methods: {
      //获取科室列表
      // getDepartmentList(){
      //   var _this = this;
      //   var obj = {"id":'',"username":"全部"};var objss = {"value":'',"text":"全部"};
      //   //提交人列表
      //   this.$ajax.get("/recovery/user/selectByRoleAndBelongId",{
      //     params:{
      //       belongid: sessionStorage.getItem('belongId'),
      //       role: '审核员'
      //     }
      //   }).then(function(response){
      //         console.log("确认人列表",response);
      //         _this.submitList = response.data.list;
      //         _this.submitList.unshift(obj);
      //   })
      //   //状态列表
      //   this.$ajax.get("/recovery/truckhospital/statusList").then(function(response){
      //         console.log("status列表",response);
      //         _this.statusList = response.data.list;
      //         _this.statusList.unshift(objss);
      //   })
      // },
      //导出
      exportJoinList(row){
        var starttime = "";
        var endtime = "";
        if(this.form.starttime == "" && this.form.endtime == ""){
          this.$message.error("请选择起止时间！");
          return;
        }else{
          starttime = new Date(this.form.starttime)/1000*1000;
          endtime = new Date(this.form.endtime)/1000*1000;
        }
        
        if(this.$route.query.flag == 1){
          var aaa="http://hrt.chinagrp.org.cn/recovery/truckhospital/exportList?hospitalid="+this.$route.query.id
          +"&treatmentplantid="+sessionStorage.getItem('belongId')
          +"&transportcompanyid="+this.$route.query.transportcompanyid
          +"&begintime="+starttime+"&endtime="+endtime;
          console.log(aaa)
          window.location.href = aaa;
        }else if(this.$route.query.flag == 0){
          var bbb="http://hrt.chinagrp.org.cn/recovery/truckhospital/oneExportList?hospitalid="+this.$route.query.id
          +"&begintime="+starttime+"&endtime="+endtime;
          console.log(bbb)
          window.location.href = bbb;
        }
      },
      //获取出库信息
      getData(){
        var that = this;
        if(this.form.starttime == null){
          this.form.starttime = "";
        }
        if(this.form.endtime == null){
          this.form.endtime = "";
        }
        if(this.$route.query.flag == 1){
          this.$ajax.get("/recovery/truckhospital/truckhospitalList",{
              params:{
                page: this.page,
                rows: this.pageSize,
                hospitalid: this.$route.query.id,
                transportcompanyid: this.$route.query.transportcompanyid,
                treatmentplantid: sessionStorage.getItem('belongId'),
                // confirmuserid: this.form.submitid,
                status: "已审核",
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
            }
          })
        }else if(this.$route.query.flag == 0){
          this.$ajax.get("/recovery/truckhospital/selectVByTransportcompanyIdAndPage",{
              params:{
                page: this.page,
                rows: this.pageSize,
                transportcompanyid: sessionStorage.getItem('belongId'),
                hospitalid: this.$route.query.id,
                status: "已审核",
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
            }
          })
        }
      },
      //分页换页
      changePage(page){
        this.page = page;
        this.getData();
      },
      //清空筛选
      clearSelect(){
            this.form = {
                submitid: null,
                status: null,
                starttime: null,
                endtime: null
            }
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
