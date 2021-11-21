<template>
    <div id="yudingBox">
        <div class="selectBox">
          <el-form ref="form" :model="form" label-width="60px">
            <el-form-item label="条码">
              <el-input v-model="form.barcode" @change="selectList"></el-input>
            </el-form-item>
            <el-form-item label="科室">
              <el-select v-model="form.departmentid" placeholder="请选择科室" @change="selectList">
                <el-option v-for="(item,index) in departmentList" :key="index" :label="item.name"  :value="item.id"></el-option>
              </el-select>
            </el-form-item>
            <!-- <el-form-item label="车次">
              <el-select v-model="form.truckid" placeholder="请选择车次" @change="selectList">
                <el-option v-for="(item,index) in truckList" :key="index" :label="item.number"  :value="item.id"></el-option>
              </el-select>
            </el-form-item> -->
            <el-form-item label="状态">
              <el-select v-model="form.status" placeholder="请选择状态" @change="selectList">
                <el-option v-for="(item,index) in statusList" :key="index" :label="item.text"  :value="item.value"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="类型">
              <el-select v-model="form.type" placeholder="请选择类型" @change="selectList">
                    <el-option v-for="(item,index) in typeList" :key="index"  :label="item.text" :value="item.value"></el-option>
                </el-select>
            </el-form-item>
          </el-form>
        </div>
        <div class="selectBox">
          <el-form ref="form" :model="form" label-width="60px">
            <el-form-item label="时间">
              <el-col :span="11">
                <el-date-picker type="date" placeholder="起始日期" value-format="yyyy/MM/dd" v-model="form.starttime" @change="selectList" style="width: 100%;"></el-date-picker>
              </el-col>
              <el-col :span="11">
                <el-date-picker type="date" placeholder="结束日期" value-format="yyyy/MM/dd" v-model="form.endtime" @change="selectList" style="width: 100%;"></el-date-picker>
              </el-col>
            </el-form-item>
            <el-form-item>
                <el-button type="warning" size="small" @click="clearSelect" style="font-size:14px">清空筛选</el-button>
            </el-form-item>
          </el-form>
        </div>
        <!-- 用户列表 -->
        <div class="tableBox">
            <el-table :data="tableData" style="width: 100%">
                <el-table-column prop="time" label="时间" width="150" sortable>
                  <template slot-scope="scope">
                    <span v-if="scope.row.time == null"></span>
                    <span v-else>{{scope.row.time | formatDate}}</span>
                  </template>
                </el-table-column>
                <el-table-column prop="barcode" label="条码"></el-table-column>
                <el-table-column prop="department.name" label="所属科室"></el-table-column>
                <el-table-column prop="belongname" label="实时位置"></el-table-column>
                <el-table-column prop="belongtype" label="位置类型"></el-table-column>
                <el-table-column prop="type" label="类型"></el-table-column>
                <el-table-column prop="status" label="状态"></el-table-column>
                <el-table-column prop="" label="记录">
                    <template slot-scope="scope">
                        <el-button
                          size="mini"
                          @click="lookGarbagebag(scope.row.id)">查看</el-button>
                    </template>
                </el-table-column>
                <!-- <el-table-column prop="" label="输液袋">
                    <template slot-scope="scope">
                        <el-button
                          size="mini"
                          @click="lookInfusionbag(scope.row.id)">查看</el-button>
                    </template>
                </el-table-column> -->
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
        <!-- 查看垃圾袋记录 -->
        <el-dialog
          title="垃圾袋记录"
          :visible.sync="belongIf"
          width="60%"
          center>
          <div class="dialogMain">
            <el-table :data="jiluList" style="width: 100%">
                <el-table-column prop="time" label="时间" width="150" sortable>
                  <template slot-scope="scope">
                    <span v-if="scope.row.time == null"></span>
                    <span v-else>{{scope.row.time | formatDate}}</span>
                  </template>
                </el-table-column>
                <el-table-column prop="belongname" label="经存单位"></el-table-column>
                <el-table-column prop="belongtype" label="单位类型" width="100"></el-table-column>
                <el-table-column prop="status" label="状态" width="100"></el-table-column>
            </el-table>
          </div>
        </el-dialog>
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
        belongIf: false,
        //用户id和用户类型
        userId: sessionStorage.getItem("userId"),
        userType:sessionStorage.getItem("userType"),
        page: 1,
        pageSize: 5,
        totalNum: 100,
        //筛选
        form: {
          barcode: null,
          departmentid: null,
          status:null,
          truckid:null,
          starttime: null,
          endtime: null,
          type: null,
        },
        //弹框title
        addOrUpTit:'',
        //表格数据
        tableData: [],
        jiluList:[],
        //类型筛选列表
        typeList:[],
        //科室列表
        departmentList:[],
        //车次列表
        truckList:[],
        //状态列表
        statusList:[],
        
        centerDialogVisible: false,
      };
    },
    mounted() {
      this.getData();
      this.getDepartmentList();
    },
    methods: {
      //获取科室列表
      getDepartmentList(){
        var _this = this;
        var obj = {"id":'',"name":"全部"};var objs = {"id":'',"number":"全部"};var objss = {"value":'',"text":"全部"};
        //科室列表
        this.$ajax.get("/recovery/department/selectByHospitalId",{
          params:{
            hospitalid: sessionStorage.getItem('belongId')
          }
        }).then(function(response){
              console.log("keshi列表",response);
              _this.departmentList = response.data.list;
              _this.departmentList.unshift(obj);
        })
        //车次列表
        this.$ajax.get("/recovery/truck/selectAll").then(function(response){
              console.log("truck列表",response);
              _this.truckList = response.data.list;
              _this.truckList.unshift(objs);
        })
        //状态列表
        this.$ajax.get("/recovery/garbagebag/statusList").then(function(response){
              console.log("status列表",response);
              _this.statusList = response.data.list;
              _this.statusList.unshift(objss);
        })
        //类型列表
        this.$ajax.get("/recovery/garbagebag/typeList").then(function(response){
              console.log("type列表",response);
              _this.typeList = response.data.list;
              _this.typeList.unshift(objss);
        })
      },
      //清空筛选
      clearSelect(){
            this.form = {
              barcode: null,
              departmentid: null,
              status:null,
              truckid:null,
              starttime: null,
              endtime: null,
              type: null,
            }
            this.getData();
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
        // console.log(this.form.sysstarttime);
        this.$ajax.get("/recovery/garbagebag/selectVByHospital",{
            params:{
              page: this.page,
              rows: this.pageSize,
              barcode: this.form.barcode,
              departmentid: this.form.departmentid,
              status: this.form.status,
              truckid: this.form.truckid,
              hospitalid: sessionStorage.getItem('belongId'),
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
      lookInfusionbag(id){
        this.$router.replace({
          path:'/Infusionbag',
          query:{
            garbagebagid: id,
          }
        })
      },
      //查看垃圾袋记录
      lookGarbagebag(id){
        var that = this;
        this.jiluList = [];
        this.$ajax.get("/recovery/garbagebagrecord/selectVByGarbagebagId",{
            params:{
              garbagebagid: id
            }
        }).then(function(response){
          console.log(response)
          that.belongIf = true;
          if(response.data.status == 260){
            that.jiluList=response.data.list;
          }else{

          }
        })
        
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
  /* margin-top: 20px; */
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
