<template>
    <div id="yudingBox">
        <div class="selectBox">
          <el-form ref="form" :model="form" label-width="130px" v-if="userType == '系统'">
            <el-form-item label="再利用企业名称">
                <el-input v-model="form.item" @change="selectList"></el-input>
            </el-form-item>
            <el-form-item label="负责人" label-width="70px">
                <el-input v-model="form.leadername" @change="selectList"></el-input>
            </el-form-item>
            <el-form-item label-width="30px">
                <el-button type="warning" size="small" @click="clearSelect" style="font-size:14px">清空筛选</el-button>
            </el-form-item>
          </el-form>
        </div>
        <div class="tableBox">
            <el-table :data="tableData" style="width: 100%" @selection-change="handleSelectionChange">
                <el-table-column
                  type="selection"
                  width="55"
                  >
                </el-table-column>
                <el-table-column prop="name" label="再利用企业名称"></el-table-column>
                <el-table-column prop="leadername" label="负责人"></el-table-column>
                <el-table-column prop="leaderphone" label="负责人电话"></el-table-column>
                <el-table-column prop="address" label="地址"></el-table-column>
                <el-table-column prop="" label="营业执照">
                    <template slot-scope="scope">
                        <img :src='scope.row.img' @click="lookImg(scope.row.img)" style="width: 90px;height:70px"/>
                    </template>
                </el-table-column>
                <el-table-column prop="" label="交接记录" width="90" v-if="userType == '再利用企业'">
                    <template slot-scope="scope">
                        <el-button
                          size="mini"
                          @click="lookJoinList(scope.row.id)">查看</el-button>
                    </template>
                </el-table-column>
                <el-table-column label="操作" width="260" v-if="userType == '系统'">
                  <template slot-scope="scope">
                    <el-button
                      size="mini"
                      @click="setAdminUser(scope.row)">设置管理员</el-button>
                    <el-button
                      size="mini"
                      @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                    <el-button
                      size="mini"
                      type="danger"
                      @click="handleDelete(scope.$index, scope.row.id)">删除</el-button>
                  </template>
                </el-table-column>
            </el-table>
            <div class="pafinationBox">
              <div>
                <el-button type="primary" @click="clickAddOrUp" v-if="userType == '系统'">添加再利用企业</el-button>
                <el-button type="warning" @click="delAll" v-if="userType == '系统'">批量删除</el-button>
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
            <el-form ref="addForm" :model="addForm" label-width="130px">
                <el-form-item label="再利用企业名称" required>
                    <el-input v-model="addForm.name"></el-input>
                </el-form-item>
                <el-form-item label="负责人" required>
                    <el-input v-model="addForm.leadername"></el-input>
                </el-form-item>
                <el-form-item label="负责人电话" required>
                    <el-input v-model="addForm.leaderphone"></el-input>
                </el-form-item>
                <el-form-item label="地址" required>
                    <el-input v-model="addForm.address"></el-input>
                </el-form-item>
            </el-form>
            <div class="iptBox">
                <span>营业执照</span>
                <div>
                    <aliUpFile :file="addForm.img" server='/recovery' directory='eproductionplant' fileNum="1" @fileDel='fileDels' @upFileData="upFileDatas"></aliUpFile>
                </div>
            </div>
          </div>
          <span slot="footer" class="dialog-footer">
            <el-button @click="centerDialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="addSaveForm()">确 定</el-button>
          </span>
        </el-dialog>
        <!-- 查看交接记录 -->
        <el-dialog
          title="交接记录"
          :visible.sync="shengchanIf"
          width="90%"
          center>
          <div class="dialogMains">
            <div class="selectBox">
              <el-form ref="joinForm" :model="joinForm" label-width="60px">
                <el-form-item label="提交人">
                  <el-select v-model="joinForm.submitid" placeholder="请选择提交人" @change="selectJoinList">
                    <el-option v-for="(item,index) in submitList" :key="index" :label="item.username"  :value="item.id"></el-option>
                  </el-select>
                </el-form-item>
                <el-form-item label="状态">
                  <el-select v-model="joinForm.status" placeholder="请选择状态" @change="selectJoinList">
                    <el-option v-for="(item,index) in statusList" :key="index" :label="item.text"  :value="item.value"></el-option>
                  </el-select>
                </el-form-item>
                <el-form-item label="时间">
                  <el-col :span="11">
                    <el-date-picker type="date" placeholder="起始日期" value-format="yyyy/MM/dd" v-model="joinForm.starttime" @change="selectJoinList" style="width: 100%;"></el-date-picker>
                  </el-col>
                  <el-col :span="11">
                    <el-date-picker type="date" placeholder="结束日期" value-format="yyyy/MM/dd" v-model="joinForm.endtime" @change="selectJoinList" style="width: 100%;"></el-date-picker>
                  </el-col>
                </el-form-item>
                <el-form-item>
                    <el-button type="warning" size="small" @click="clearJoinSelect" style="font-size:14px">清空筛选</el-button>
                </el-form-item>
              </el-form>
            </div>
          </div>
          <!-- 用户列表 -->
          <div class="tableBox">
              <el-table :data="joinTableData" style="width: 100%">
                  <el-table-column prop="" label="时间" width="135" sortable>
                    <template slot-scope="scope">
                      <span v-if="scope.row.time == null"></span>
                      <span v-else>{{scope.row.time | formatDate}}</span>
                    </template>
                  </el-table-column>
                  <el-table-column prop="productionplant.name" label="再利用企业"></el-table-column>
                  <el-table-column prop="truck.vehicle.number" label="车牌"></el-table-column>
                  <el-table-column prop="plasticweight" label="塑料重量(kg)"></el-table-column>
                  <el-table-column prop="glassweight" label="玻璃重量(kg)"></el-table-column>
                  <el-table-column prop="status" label="状态"></el-table-column>
                  <el-table-column prop="submitUser.username" label="提交人"></el-table-column>
                  <el-table-column prop="confirmUser.username" label="确认人"></el-table-column>
                  <el-table-column prop="note" label="备注"></el-table-column>
              </el-table>
              <div class="pafinationBox">
                <el-pagination
                  background
                  layout="prev, pager, next"
                  :page-size="jpageSize"
                  :current-page.sync="jpage"
                  :total="jtotalNum"
                  @current-change="changePage"
                  >
                </el-pagination>
              </div>
          </div>
        </el-dialog>
        <!-- 查看大图 -->
        <el-dialog
          title=""
          :visible.sync="lookImgIf"
          width="600px"
          center>
          <div class="dialogMain">
             <img :src="imgLook" style="width: 100%;"/>
          </div>
        </el-dialog>
    </div>
</template>
<script>
import aliUpFile from "../../components/aliuploadrenyuan"
export default {
  components:{
        aliUpFile
    },
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
        userType: sessionStorage.getItem('userType'),
        page: 1,
        pageSize: 10,
        totalNum: 100,
        //筛选
        form: {
          item:'',
          leadername:''
        },
        //交接记录
        shengchanIf: false,
        joinid:null,
        jpage: 1,
        jpageSize: 5,
        jtotalNum: 100,
        //筛选
        joinForm: {
          treatmentplantid: null,
          status:null,
          submitid:null,
          starttime: null,
          endtime: null,
          confirmid: null
        },
        //表格数据
        joinTableData: [],
        //筛选列表
        treatmentplantList:[],
        submitList:[],
        statusList:[],
        confirmList:[],
        // truckList:[],
        //弹框title
        addOrUpTit:'',
        //新增与编辑form
        addForm:{
            id: null,
            item: null,
            leadername: null,
            leaderphone: null,
            address: null,
            img: '',
        },
        //表格数据
        tableData: [],
        //批量选择
        multipleSelection: [],
        centerDialogVisible: false,
        innerVisible:false,
        endPickerOptions: {
              disabledDate(time) {
                  return time.getTime() < Date.now() - 8.64e7;
              }
        },
        //查看大图
        lookImgIf: false,
        imgLook:''
      };
    },
    mounted() {
      this.getData();
      this.getJoinSelectList();
    },
    methods: {

      //接收图片
      upFileDatas(data){
          this.addForm.img = data;
      },
      //删除图片
      fileDels(data){
          this.addForm.img = '';
      },
      //查看大图
      lookImg(src){
        this.imgLook = src;
        this.lookImgIf = true;

      },
      //设置管理员
      setAdminUser(row){
        this.$router.replace({
          path:'/adminUser',
          query:{
            belongid: row.id,
            gsName: row.name,
            type: '再利用企业'
          }
        })
      },
    //查看交接记录
      lookJoinList(id){
        this.joinid = id;
        this.shengchanIf = true;
      },
      //获取区域信息
      getData(){
        var that = this;
        this.$ajax.get("/recovery/productionplant/selectByPage",{
            params:{
              page: this.page,
              rows: this.pageSize,
              name: this.form.item,
              leadername: this.form.leadername,
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
      //清空筛选
      clearSelect(){
            this.form = {
              item: '',
              leadername:''
            }
            this.getData();
      },
      //清空创建编辑form
      clearForm(){
        this.addForm = {
            id: null,
            item: null,
            leadername: null,
            leaderphone: null,
            address: null,
            img: '',
        } 
      },
      //点击新增/编辑
      clickAddOrUp(){
        this.centerDialogVisible = true;
        this.addOrUpTit='创建再利用企业';
        this.clearForm();

      },
      //编辑
      handleEdit(index, row) {
        console.log(index, row);
        var that = this;
        this.centerDialogVisible = true;
        this.addOrUpTit = "编辑再利用企业";
        that.addForm = {
            id: row.id,
            name: row.name,
            leadername: row.leadername,
            leaderphone: row.leaderphone,
            address: row.address,
            img: row.img,
        }
      },
      // 新增
      addSaveForm(){
        console.log(this.addForm)
        var that = this;
        if(this.addForm.name == null || this.addForm.leadername == null || this.addForm.leaderphone == null || this.addForm.address == null){
          this.$message.error("*为必填项！");
          return;
        }
        if(this.addForm.img == ''){
          this.$message.error("营业执照为必填项！");
          return;
        }
        var params = this.$qs.stringify(this.addForm, { indices: false })
        if(this.addOrUpTit == "创建再利用企业"){
          this.$ajax.post("/recovery/productionplant//insert ",params).then(function(response){
            if(response.data.status == 260){
              that.centerDialogVisible = false;
              that.$message.success("添加成功")
              that.getData();
              //清空form
              that.clearForm();
            }
          })
        }else{
          this.$ajax.put("/recovery/productionplant/update",params).then(function(response){
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
        this.$confirm('此操作将永久删除选中的再利用企业, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          if(this.multipleSelection.length == this.tableData.length){
            this.page = Number(this.page)-1;
          }
          var that = this;
          this.$ajax.delete("/recovery/productionplant/deletes",{data:this.multipleSelection}).then(function(response){
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
        this.$confirm('此操作将删除该再利用企业, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          console.log(index, row);
          if(this.tableData.length == 1){
            this.page = Number(this.page)-1;
          }
          var that = this;
          this.$ajax.delete("/recovery/productionplant/delete",{params:{
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
      },
      //交接
      //获取筛选列表
      getJoinSelectList(){
        var _this = this;
        var objs = {"id":'',"name":"全部"};var objss = {"value":'',"text":"全部"};
        var obj = {"id":'',"username":"全部"};
        // //处产厂列表
        // this.$ajax.get("/recovery/treatmentplant/selectAll").then(function(response){
        //       console.log("再利用企业列表",response);
        //       _this.treatmentplantList = response.data.list;
        //       _this.treatmentplantList.unshift(objs);
        // })
        // //再利用企业列表
        // this.$ajax.get("/recovery/productionplant/selectAll").then(function(response){
        //       console.log("回收利用企业列表",response);
        //       _this.productionplantList = response.data.list;
        //       _this.productionplantList.unshift(objs);
        // })
        //提交人列表
        this.$ajax.get("/recovery/user/selectByRoleAndBelongId",{
          params:{
            belongid: sessionStorage.getItem('belongId'),
            role: '审核员',
            type: sessionStorage.getItem('userType'),
            
          }
        }).then(function(response){
              console.log("申报人列表",response);
              _this.submitList = response.data.list;
              _this.submitList.unshift(obj);
        })
        //状态列表
        this.$ajax.get("/recovery/truckproductionplant/statusList").then(function(response){
              console.log("status列表",response);
              _this.statusList = response.data.list;
              _this.statusList.unshift(objss);
        })
      },
      clearJoinSelect(){
          this.joinForm = {
            treatmentplantid: null,
            status:null,
            submitid:null,
            starttime: null,
            endtime: null,
            confirmid: null
          }
          this.lookJoinList(this.joinid);
      },
      //获取垃圾袋信息
      //查看交接记录
      lookJoinList(id){
        this.joinid = id;
        this.shengchanIf = true;
        var that = this;
        if(this.joinForm.starttime == null){
          this.joinForm.starttime = "";
        }
        if(this.joinForm.endtime == null){
          this.joinForm.endtime = "";
        }
        console.log(this.joinForm)
        this.$ajax.get("/recovery/truckproductionplant/selectVByPage",{
            params:{
                page: this.page,
                rows: this.pageSize,
                productionplantid: id,
                treatmentplantid: this.joinForm.treatmentplantid,
                submituserid: this.joinForm.submitid,
                confirmuserid: this.joinForm.confirmid,
                status: this.joinForm.status,
                name: this.joinForm.name,
                begintime: this.joinForm.starttime==""? "" : new Date(this.joinForm.starttime)/1000*1000,
                endtime: this.joinForm.endtime==""? "" : new Date(this.joinForm.endtime)/1000*1000,
                }
            }).then(function(response){
          console.log(response)
          if(response.data.status == 260){
            that.joinTableData = response.data.list;
            that.jtotalNum = response.data.info.total;
          }else{
            that.joinTableData = [];
            that.jtotalNum = 0;
          }
        })
      },
      //分页换页
      changeJoinPage(page){
        this.jpage = page;
        this.lookJoinList(this.joinid);
      },
      //查询
      selectJoinList(){
        this.jpage = 1;
        this.lookJoinList(this.joinid);
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
  width: 100%;
  min-height: 30px;
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
.iptBox{
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
    width: 120px;
    padding-right: 10px;
    text-align: right;
}
</style>
