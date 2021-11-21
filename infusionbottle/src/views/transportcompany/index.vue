<template>
    <div id="yudingBox">
        <div class="selectBox">
          <el-form ref="form" :model="form" label-width="70px">
            <el-form-item label="物流名称">
                <el-input v-model="form.item" placeholder="请输入物流名称" @change="selectList"></el-input>
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
                <el-table-column prop="name" label="物流名称"></el-table-column>
                <el-table-column prop="address" label="地址"></el-table-column>
                <el-table-column prop="leadername" label="负责人"></el-table-column>
                <el-table-column prop="leaderphone" label="负责人电话"></el-table-column>
                <!-- <el-table-column prop="" label="营业执照">
                    <template slot-scope="scope">
                        <img :src='scope.row.img' @click="lookImg(scope.row.img)" style="width: 90px;height:70px"/>
                    </template>
                </el-table-column> -->
                <el-table-column label="车次" width="80" v-if="userType == '回收物流'">
                  <template slot-scope="scope">
                    <el-button
                      size="mini"
                      @click="lookTruck(scope.row.id)">查看</el-button>
                  </template>
                </el-table-column>
                <el-table-column prop="note" label="备注"></el-table-column>
                <el-table-column label="操作" width="100">
                  <template slot-scope="scope">
                    <!-- <el-button
                      size="mini"
                      @click="setAdminUser(scope.row)">设置管理员</el-button>
                    <el-button
                      size="mini"
                      @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                    <el-button
                      size="mini"
                      type="danger"
                      @click="handleDelete(scope.$index, scope.row.id)">删除</el-button>
                    <el-button
                      size="mini"
                      @click="lookCar(scope.row)">查看车辆</el-button> -->
                      <div>
                        <el-dropdown>
                          <span class="el-dropdown-link">
                            <i class="el-icon-more" style="font-size: 22px"></i>
                          </span>
                          <el-dropdown-menu slot="dropdown">
                            <el-dropdown-item>
                                <p @click="setAdminUser(scope.row)">设置管理员</p>
                            </el-dropdown-item>
                            <el-dropdown-item>
                              <p @click="lookCar(scope.row)">查看车辆</p>
                            </el-dropdown-item>
                            <el-dropdown-item>
                              <p @click="handleEdit(scope.$index, scope.row)">编辑</p>
                            </el-dropdown-item>
                            <el-dropdown-item>
                              <p @click="handleDelete(scope.$index, scope.row.id)">删除</p>
                            </el-dropdown-item>
                          </el-dropdown-menu>
                        </el-dropdown>
                    </div>
                  </template>
                </el-table-column>
            </el-table>
            <div class="pafinationBox">
              <div>
                <el-button type="primary" @click="clickAddOrUp">添加物流</el-button>
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
                <el-form-item label="物流名称" required>
                    <el-input v-model="addForm.name" placeholder="请填写物流名称"></el-input>
                </el-form-item>
                <el-form-item label="地址" required>
                    <el-input v-model="addForm.address" placeholder="请填写物流地址"></el-input>
                </el-form-item>
                <el-form-item label="负责人" required>
                    <el-input v-model="addForm.leadername" placeholder="请填写负责人"></el-input>
                </el-form-item>
                <el-form-item label="负责人电话" required>
                    <el-input v-model="addForm.leaderphone" placeholder="请填写负责人电话"></el-input>
                </el-form-item>
                <el-form-item label="备注">
                    <el-input v-model="addForm.note"></el-input>
                </el-form-item>
            </el-form>
            <!-- <div class="iptBox">
                <span>营业执照</span>
                <div>
                    <aliUpFile :file="addForm.img" server='/recovery' directory='etransportcompany' fileNum="1" @fileDel='fileDels' @upFileData="upFileDatas"></aliUpFile>
                </div>
            </div> -->
          </div>
          <span slot="footer" class="dialog-footer">
            <el-button @click="centerDialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="addSaveForm()">确 定</el-button>
          </span>
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
        userType:sessionStorage.getItem('userType'),
        page: 1,
        pageSize: 10,
        totalNum: 100,
        //筛选
        form: {
          item:'',
        },
        //弹框title
        addOrUpTit:'',
        //新增与编辑form
        addForm:{
            id: null,
            name: null,
            address: null,
            leadername: null,
            leaderphone: null,
            note:null,
            img: '',
            treatmentplantid: null
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
      //查看车辆
      lookCar(row){
          this.$router.replace({
              path:'/yunShuCarList',
              query:{
                  name: row.name,
                  id: row.id
              }
          })
      },
      //设置管理员
      setAdminUser(row){
        this.$router.replace({
          path:'/adminUser',
          query:{
            belongid: row.id,
            gsName: row.name,
            type: '回收物流'
          }
        })
      },
        //查看车次记录
      lookTruck(id){
        this.$router.replace({
          path:'/Truck',
          query:{
            truckId: id,
          }
        })
      },
      //获取数据
      getData(){
        var that = this;
        this.$ajax.get("/recovery/transportcompany/selectVByPage",{
            params:{
              page: this.page,
              rows: this.pageSize,
              name: this.form.item,
              treatmentplantid: sessionStorage.getItem('belongId')
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
      clearForm(){
        this.addForm = {
            id: null,
            name: null,
            address: null,
            leadername: null,
            leaderphone: null,
            note:null,
            img: '',
            treatmentplantid: null
        } 
      },
      //点击新增/编辑
      clickAddOrUp(){
        this.centerDialogVisible = true;
        this.addOrUpTit='创建物流';
        this.clearForm();
        this.addForm.treatmentplantid = sessionStorage.getItem('belongId');

      },
      //编辑
      handleEdit(index, row) {
        console.log(index, row);
        var that = this;
        this.centerDialogVisible = true;
        this.addOrUpTit = "编辑物流";
        that.addForm = {
            id: row.id,
            name: row.name,
            address: row.address,
            leadername: row.leadername,
            leaderphone: row.leaderphone,
            note: row.note,
            img:  row.img,
            treatmentplantid: row.treatmentplantid
        }
      },
      // 新增
      addSaveForm(){
        console.log(this.addForm)
        var that = this;
        if(this.addForm.name == null || this.addForm.address == null || this.addForm.leadername == null || this.addForm.leaderphone == null){
          this.$message.error("*为必填项！");
          return;
        }
        // if(this.addForm.img == ''){
        //   this.$message.error("营业执照为必填项！");
        //   return;
        // }
        var params = this.$qs.stringify(this.addForm, { indices: false })
        if(this.addOrUpTit == "创建物流"){
          this.$ajax.post("/recovery/transportcompany//insert ",params).then(function(response){
            if(response.data.status == 260){
              that.centerDialogVisible = false;
              that.$message.success("添加成功")
              that.getData();
              //清空form
              that.clearForm();
            }
          })
        }else{
          this.$ajax.put("/recovery/transportcompany/update",params).then(function(response){
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
        this.$confirm('此操作将永久删除选中的物流, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          if(this.multipleSelection.length == this.tableData.length){
            this.page = Number(this.page)-1;
          }
          var that = this;
          this.$ajax.delete("/recovery/transportcompany/deletes",{data:this.multipleSelection}).then(function(response){
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
        this.$confirm('此操作将删除该物流, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          console.log(index, row);
          if(this.tableData.length == 1){
            this.page = Number(this.page)-1;
          }
          var that = this;
          this.$ajax.delete("/recovery/transportcompany/delete",{params:{
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
    },
    // beforeRouteEnter(to, from, next) {
    //   next( vm =>{
    //   console.log(from)
    //     if (from.path == "/adminUser") {
    //       next();
    //     }else {
    //       vm.page = 1;
    //       vm.currentPage = 1;
    //       vm.getData();
    //       next();
    //     }
    //   })
    // },
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
  margin: auto;
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
    width: 100px;
    padding-right: 10px;
    text-align: right;
}
</style>
