<template>
    <div id="yudingBox">
        <div class="selectBox">
          <el-form ref="form" :model="form" label-width="60px">
            <el-form-item label="车辆">
                <el-select v-model="form.vehicleid" placeholder="请选择车辆" @change="selectList">
                    <el-option v-for="(item,index) in carList" :key="index"  :label="item.number" :value="item.id"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="运输人">
              <el-select v-model="form.userid" placeholder="请选择运输人" @change="selectList">
                    <el-option v-for="(item,index) in userList" :key="index"  :label="item.username" :value="item.id"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="状态">
              <el-select v-model="form.status" placeholder="请选择状态" @change="selectList">
                    <el-option v-for="(item,index) in statusList" :key="index"  :label="item.text" :value="item.value"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="类型">
              <el-select v-model="form.type" placeholder="请选择状态" @change="selectList">
                    <el-option v-for="(item,index) in typeList" :key="index"  :label="item.text" :value="item.value"></el-option>
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
            <el-form-item>
                <el-button type="warning" size="small" @click="clearSelect" style="font-size:14px">清空筛选</el-button>
            </el-form-item>
          </el-form>
        </div>
        <div class="tableBox">
            <el-table :data="tableData" height="90%" style="width: 100%" @selection-change="handleSelectionChange">
                <el-table-column
                  type="selection"
                  width="55"
                  >
                </el-table-column>
                <el-table-column prop="time" label="时间" width="150" sortable>
                  <template slot-scope="scope">
                    <span v-if="scope.row.time == null"></span>
                    <span v-else>{{scope.row.time | formatDate}}</span>
                  </template>
                </el-table-column>
                <el-table-column prop="transportcompany.name" label="所属公司"></el-table-column>
                <el-table-column prop="vehicle.number" label="车牌号"></el-table-column>
                <el-table-column prop="user.username" label="运输人"></el-table-column>
                <el-table-column prop="type" label="类型"></el-table-column>
                <el-table-column prop="status" label="状态"></el-table-column>
                <el-table-column prop="note" label="备注"></el-table-column>
                <el-table-column label="操作" width="250">
                  <template slot-scope="scope">
                    <p>
                      <el-button
                        size="mini"
                        v-if="scope.row.type == '医疗机构-回收利用企业'"
                        @click="lookGarbagebag(scope.row,1)">医疗机构记录</el-button>
                      <el-button
                        size="mini"
                        v-if="scope.row.type == '医疗机构-回收物流'"
                        @click="lookGarbagebag(scope.row,3)">医疗机构记录</el-button>
                      <el-button
                        size="mini"
                        v-if="scope.row.type == '回收物流-回收利用企业'"
                        @click="lookGarbagebag(scope.row,5)">回 收 物 流</el-button>
                      <el-button
                        size="mini"
                        v-if="scope.row.type == '回收利用企业-再利用企业'"
                        @click="lookGarbagebag(scope.row,7)">回收利用企业交接</el-button>
                      <el-button
                        size="mini"
                        @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                    </p>
                    <p style="margin-top: 5px">
                      <el-button
                        size="mini"
                        v-if="scope.row.type == '医疗机构-回收利用企业'"
                        @click="lookGarbagebag(scope.row,2)">回收利用企业记录</el-button>
                      <el-button
                        size="mini"
                        v-if="scope.row.type == '医疗机构-回收物流'"
                        @click="lookGarbagebag(scope.row,4)">回 收 物 流</el-button>
                      <el-button
                        size="mini"
                        v-if="scope.row.type == '回收物流-回收利用企业'"
                        @click="lookGarbagebag(scope.row,6)">回收利用企业交接</el-button>
                      <el-button
                        size="mini"
                        v-if="scope.row.type == '回收利用企业-再利用企业'"
                        @click="lookGarbagebag(scope.row,8)">再利用企业交接</el-button>
                      <!-- <el-button
                        size="mini"
                        type="danger"
                        @click="handleDelete(scope.$index, scope.row.id)">删除</el-button> -->
                    </p>
                  </template>
                </el-table-column>
            </el-table>
            <div class="pafinationBox">
              <div>
                <el-button type="primary" @click="clickAddOrUp">添加车次</el-button>
                <el-button type="warning" @click="delAll">批量删除</el-button>
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
        <el-dialog
          :title="addOrUpTit"
          :visible.sync="centerDialogVisible"
          width="600px"
          center>
          <div class="dialogMain">
            <el-form ref="addForm" :model="addForm" label-width="100px">
                <el-form-item label="车辆" required>
                  <el-select v-model="addForm.vehicleid" placeholder="请选择车辆">
                      <el-option v-for="(item,index) in carList" :key="index" v-show="item.number !== '全部'" :label="item.number" :value="item.id"></el-option>
                  </el-select>
                </el-form-item>
                <el-form-item label="运输人" required>
                  <el-select v-model="addForm.userid" placeholder="请选择运输人">
                        <el-option v-for="(item,index) in userList" :key="index" v-show="item.username !== '全部'"  :label="item.username" :value="item.id"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="类型" required>
                  <el-select v-model="addForm.type" placeholder="请选择类型">
                        <el-option v-for="(item,index) in typeList" :key="index" v-show="item.text !== '全部'"  :label="item.text" :value="item.value"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="状态" required v-if="addOrUpTit == '编辑车次'">
                  <el-select v-model="addForm.status" placeholder="请选择状态">
                        <el-option v-for="(item,index) in statusList" :key="index" v-show="item.text !== '全部'"  :label="item.text" :value="item.value"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="备注">
                    <el-input v-model="addForm.note"></el-input>
                </el-form-item>
            </el-form>
          </div>
          <span slot="footer" class="dialog-footer">
            <el-button @click="centerDialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="addSaveForm()">确 定</el-button>
          </span>
        </el-dialog>
        <!-- 查看医疗机构交接记录 -->
        <el-dialog
          title="医疗机构交接记录"
          :visible.sync="hospitalIf"
          width="90%"
          center>
          <div class="dialogMain">
            <el-table :data="hospitalJoinList" style="width: 100%">
                <el-table-column prop="" label="时间" width="150" sortable>
                  <template slot-scope="scope">
                    <span v-if="scope.row.time == null"></span>
                    <span v-else>{{scope.row.time | formatDate}}</span>
                  </template>
                </el-table-column>
                <el-table-column prop="truck.vehicle.number" label="运输车牌"></el-table-column>
                <el-table-column prop="hospital.name" label="医疗机构"></el-table-column>
                <el-table-column prop="plasticnumber" label="塑料数量(袋)"></el-table-column>
                <el-table-column prop="plasticweight" label="塑料重量(kg)"></el-table-column>
                <el-table-column prop="glassnumber" label="玻璃数量(袋)"></el-table-column>
                <el-table-column prop="glassweight" label="玻璃重量(kg)"></el-table-column>
                <el-table-column prop="status" label="状态"></el-table-column>
                <el-table-column prop="submitUser.username" label="提交人"></el-table-column>
                <el-table-column prop="confirmUser.username" label="确认人"></el-table-column>
                <el-table-column prop="note" label="备注"></el-table-column>
            </el-table>
          </div>
        </el-dialog>
        <!-- 查看回收利用企业交接记录 -->
        <el-dialog
          title="回收利用企业交接记录"
          :visible.sync="chulichangIf"
          width="90%"
          center>
          <div class="dialogMain">
            <el-table :data="chulichangList" style="width: 100%">
                <el-table-column prop="" label="时间" width="135" sortable>
                  <template slot-scope="scope">
                    <span v-if="scope.row.time == null"></span>
                    <span v-else>{{scope.row.time | formatDate}}</span>
                  </template>
                </el-table-column>
                <el-table-column prop="truck.vehicle.number" label="运输车牌"></el-table-column>
                <el-table-column prop="treatmentplant.name" label="回收利用企业"></el-table-column>
                <el-table-column prop="plasticnumber" label="塑料数量(袋)"></el-table-column>
                <el-table-column prop="plasticweight" label="塑料重量(kg)"></el-table-column>
                <el-table-column prop="glassnumber" label="玻璃数量(袋)"></el-table-column>
                <el-table-column prop="glassweight" label="玻璃重量(kg)"></el-table-column>
                <el-table-column prop="status" label="状态"></el-table-column>
                <el-table-column prop="submitUser.username" label="提交人"></el-table-column>
                <el-table-column prop="confirmUser.username" label="确认人"></el-table-column>
                <el-table-column prop="note" label="备注"></el-table-column>
            </el-table>
          </div>
        </el-dialog>
        <!-- 查看再利用企业交接记录 -->
        <el-dialog
          title="再利用企业交接记录"
          :visible.sync="shengchangIf"
          width="90%"
          center>
          <div class="dialogMain">
            <el-table :data="shengchangList" style="width: 100%">
                <el-table-column prop="" label="时间" width="135" sortable>
                  <template slot-scope="scope">
                    <span v-if="scope.row.time == null"></span>
                    <span v-else>{{scope.row.time | formatDate}}</span>
                  </template>
                </el-table-column>
                <el-table-column prop="truck.vehicle.number" label="运输车牌"></el-table-column>
                <el-table-column prop="productionplant.name" label="再利用企业"></el-table-column>
                <el-table-column prop="plasticweight" label="塑料重量(kg)"></el-table-column>
                <el-table-column prop="glassweight" label="玻璃重量(kg)"></el-table-column>
                <el-table-column prop="status" label="状态"></el-table-column>
                <el-table-column prop="submitUser.username" label="提交人"></el-table-column>
                <el-table-column prop="confirmUser.username" label="确认人"></el-table-column>
                <el-table-column prop="note" label="备注"></el-table-column>
            </el-table>
          </div>
        </el-dialog>
        <!-- 查看回收物流交接记录 -->
        <el-dialog
          title="交接记录"
          :visible.sync="yunshuIf"
          width="90%"
          center>
          <div class="dialogMain">
            <el-table :data="yunshuList" style="width: 100%">
                <el-table-column prop="" label="时间" width="135" sortable>
                  <template slot-scope="scope">
                    <span v-if="scope.row.time == null"></span>
                    <span v-else>{{scope.row.time | formatDate}}</span>
                  </template>
                </el-table-column>
                <el-table-column prop="truck.vehicle.number" label="运输车牌"></el-table-column>
                <el-table-column prop="plasticnumber" label="塑料数量(袋)"></el-table-column>
                <el-table-column prop="plasticweight" label="塑料重量(kg)"></el-table-column>
                <el-table-column prop="glassnumber" label="玻璃数量(袋)"></el-table-column>
                <el-table-column prop="glassweight" label="玻璃重量(kg)"></el-table-column>
                <el-table-column prop="status" label="状态"></el-table-column>
                <el-table-column prop="submitUser.username" label="提交人"></el-table-column>
                <el-table-column prop="confirmUser.username" label="确认人"></el-table-column>
                <el-table-column prop="note" label="备注"></el-table-column>
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
        page: 1,
        pageSize: 10,
        totalNum: 100,
        //筛选
        form: {
          vehicleid:'',
          userid:'',
          starttime: null,
          endtime: null,
          status: null,
          type: null,
        },
        //弹框title
        addOrUpTit:'',
        //新增与编辑form
        addForm:{
            id: null,
            number: null,
            userid: null,
            note: null,
            status: null,
            type: null,
            vehicleid: null
        },
        //表格数据
        tableData: [],
        userList:[],
        statusList:[],
        typeList:[],
        carList:[],
        //医疗机构交接记录
        hospitalIf: false,
        hospitalJoinList: [],
        //回收利用企业交接记录
        chulichangIf: false,
        chulichangList: [],
        //再利用企业交接记录
        shengchangIf: false,
        shengchangList: [],
        //回收物流交接记录
        yunshuIf: false,
        yunshuList: [],
        //批量选择
        multipleSelection: [],
        centerDialogVisible: false,
        innerVisible:false,
        endPickerOptions: {
              disabledDate(time) {
                  return time.getTime() < Date.now() - 8.64e7;
              }
        },
      };
    },
    mounted() {
      this.getData();
      this.getSelectList();
    },
    methods: {
      //获取筛选列表
      getSelectList(){
        var _this = this;
        var objs = {"id":'',"username":"全部"};
        this.$ajax.get("/recovery/user/transportcompanyUser",{
          params:{
            role: '运输员',
            transportcompany: sessionStorage.getItem('belongId')
          }
        }).then(function(response){
              console.log("司机列表",response);
              _this.userList = response.data.list;
              _this.userList.unshift(objs);
        })
        var obj = {"value":'',"text":"全部"};
        this.$ajax.get("/recovery/truck/statusList").then(function(response){
              console.log("type列表",response);
              _this.statusList = response.data.list;
              _this.statusList.unshift(obj);
        })
        this.$ajax.get("/recovery/truck/typeList").then(function(response){
              console.log("type列表",response);
              _this.typeList = response.data.list;
              _this.typeList.unshift(obj);
        })
        var carobj = {"id":'',"number":"全部"};
        this.$ajax.get("/recovery/vehicle/selectByTransportcompanyId",{
          params:{
            transportcompanyid: sessionStorage.getItem('belongId')
          }
        }).then(function(response){
              console.log("type列表",response);
              _this.carList = response.data.list;
              _this.carList.unshift(carobj);
        })
        
      },
      //获取区域信息
      getData(){
        var that = this;
        if(this.form.starttime == null){
          this.form.starttime = "";
        }
        if(this.form.endtime == null){
          this.form.endtime = "";
        }
        this.$ajax.get("/recovery/truck/selectVByPage",{
            params:{
              page: this.page,
              rows: this.pageSize,
              transportcompanyid: sessionStorage.getItem('belongId'),
              vehicleid: this.form.vehicleid,
              userid: this.form.userid,
              status: this.form.status,
              type: this.form.type,
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
      },
      //分页换页
      changePage(page){
        this.page = page;
        this.getData();
      },
      //查看医疗机构交接记录
      lookGarbagebag(row,index){
        var that = this;
        this.$ajax.get('/recovery/truck/truckRecord',{
            params:{
              truckid: row.id,
              type: row.type
            }
        }).then(function(response){
          console.log(response)
          if(index == 1){
              that.hospitalIf = true;
            }else if(index == 2 || index == 6 || index == 7){
              that.chulichangIf = true;
            }else if(index == 3){
              that.hospitalIf = true;
            }else if(index == 4 || index == 5){
              that.yunshuIf = true;
            }else if(index == 8){
              that.shengchangIf = true;
            }
          if(response.data.status == 260){
            if(index == 1){
              that.hospitalJoinList = response.data.truckhospital;
            }else if(index == 2 || index == 6){
              that.chulichangList = response.data.trucktreatmentplant;
            }else if(index == 3){
              that.hospitalJoinList = response.data.truckhospital;
            }else if(index == 4 || index == 5){
              that.yunshuList = response.data.trucktransportcompany;
            }else if(index == 7){
              that.chulichangList = response.data.trucktreatmentplantreceive;
            }else if(index == 8){
              that.shengchangList = response.data.truckproductionplant;
            }
          }else{

          }
        })
        
      },
      //查询
      selectList(){
        this.page = 1;
        this.getData();
      },
      //清空筛选
      clearSelect(){
            this.form = {
              vehicleid: '',
              userid:'',
              starttime: null,
              endtime: null,
              status: null,
              type: null,
            }
            this.getData();
      },

      //清空创建编辑form
      clearForm(){
        this.addForm = {
            id: null,
            number: null,
            userid: null,
            note: null,
            status: '未完成',
            type: null,
            vehicleid:null,
            transportcompanyid: sessionStorage.getItem('belongId'),
        } 
      },
      //点击新增/编辑
      clickAddOrUp(){
        this.centerDialogVisible = true;
        this.addOrUpTit='创建车次';
        this.clearForm();

      },
      //编辑
      handleEdit(index, row) {
        console.log(index, row);
        var that = this;
        this.centerDialogVisible = true;
        this.addOrUpTit = "编辑车次";
        that.addForm = {
            id: row.id,
            number: row.vehicle.number,
            userid: row.userid,
            note: row.note,
            time: row.time,
            status: row.status,
            type: row.type,
            transportcompanyid: row.transportcompanyid,
            vehicleid: row.vehicleid
        }
      },
      // 新增
      addSaveForm(){
        console.log(this.addForm)
        var that = this;
        if(this.addForm.userid == null || this.addForm.vehicleid == null || this.addForm.type == null || this.addForm.status == null){
          this.$message.error("*为必填项！");
          return;
        }
        var params = this.$qs.stringify(this.addForm, { indices: false })
        if(this.addOrUpTit == "创建车次"){
          this.$ajax.post("/recovery/truck//insert",params).then(function(response){
            if(response.data.status == 260){
              that.centerDialogVisible = false;
              that.$message.success("添加成功")
              that.getData();
              //清空form
              that.clearForm();
            }
          })
        }else{
          this.$ajax.put("/recovery/truck/update",params).then(function(response){
            if(response.data.status == 260){
              that.centerDialogVisible = false;
              that.$message.success("编辑成功")
              that.getData();
              //清空form
              that.clearForm();
            }
          })
        }
      },
      //批量删除
      delAll(){
        if(this.multipleSelection.length < 1){
            return this.$message.success("请先选择删除对象");
        }
        this.$confirm('此操作将永久删除选中的车次, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          if(this.multipleSelection.length == this.tableData.length){
            this.page = Number(this.page)-1;
          }
          var that = this;
          this.$ajax.delete("/recovery/truck/deletes",{data:this.multipleSelection}).then(function(response){
            if(response.data.status == 260){
              that.$message.success("删除成功")

              that.getData();
            }
          })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });          
        });
      },
      handleSelectionChange(val) {
        this.multipleSelection = [];
        for(var i = 0 ; i < val.length ; i++){
          this.multipleSelection.push(val[i].id);
        }
        console.log(this.multipleSelection)
      },
    
      //删除
      handleDelete(index, row) {
        this.$confirm('此操作将删除该车次, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          console.log(index, row);
          if(this.tableData.length == 1){
            this.page = Number(this.page)-1;
          }
          var that = this;
          this.$ajax.delete("/recovery/truck/delete",{params:{
            id: row
          }}).then(function(response){
            if(response.data.status == 260){
              that.$message.success("删除成功")
              that.getData();
            }
          })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });          
        });
      }
    }
  }
</script>

<style scoped>
#yudingBox {
    width: 100%;
    margin-top: 20px;
    height: 87vh;
    background: #F5F7F9;
}
.selectBox{
  width: 95%;
  margin: auto;
}
.tableBox{
  width: 95%;
  height: 95%;
  margin: auto;
}
.pafinationBox{
  width: 100%;
  height: 50px;
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
.workDetailMain{
  width:100%;
  background: #F5F7F9;
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  justify-content: flex-start;
}
.workDetailMain > li{
  width: 30%;
  height: 35px;
  font-size: 14px;
  margin-left: 3%;
  display: flex;
  align-items: center;
  justify-content: flex-start;
}
.workDetailMain > li >span:first-child{
  display: block;
  width: 100px;
}
.workDetailMain > li >span:last-child{
  color: #333;
}
.dialogMain{
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
}
.mapBoxBtn{
  width: 302px;
}
.latLngMapBox{
  height: 600px;
}
</style>
