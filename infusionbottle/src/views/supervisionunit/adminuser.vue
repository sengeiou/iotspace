<template>
    <div id="yudingBox">
        <div class="selectBox">
          <el-form ref="form" :model="form" label-width="55px">
            <el-form-item label="姓名">
              <el-input v-model="form.realname" placeholder="请输入姓名" @change="selectList"></el-input>
            </el-form-item>
            <el-form-item label="手机号">
              <el-input v-model="form.phone" placeholder="请输入手机号" @change="selectList"></el-input>
            </el-form-item>
            <!-- <el-form-item label="类型">
              <el-select v-model="form.type" placeholder="请选择用户类型" @change="selectList">
                <el-option v-for="(item,index) in tableTypes" :key="index" :label="item.text"  :value="item.value"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="角色">
              <el-select v-model="form.role" placeholder="请选择用户角色" @change="selectList">
                <el-option v-for="(item,index) in roleList" :key="index" :label="item.text"  :value="item.value"></el-option>
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
          </el-form>
        </div>
        <!-- 用户列表 -->
        <div class="tableBox">
            <p style="height:30px;font-size: 14px">所属单位：{{gsName}}</p>
            <el-table :data="tableData" style="width: 100%" @selection-change="handleSelectionChange">
                <el-table-column
                  type="selection"
                  width="55"
                  >
                </el-table-column>
                <el-table-column prop="username" label="用户名"></el-table-column>
                <el-table-column prop="phone" label="手机号"></el-table-column>
                <el-table-column prop="realname" label="姓名"></el-table-column>
                <el-table-column prop="role" label="角色"></el-table-column>
                <el-table-column prop="type" label="类型"></el-table-column>
                <el-table-column prop="note" label="备注"></el-table-column>
                <el-table-column prop="time" label="注册时间" sortable>
                  <template slot-scope="scope">
                    {{scope.row.time | formatDate}}
                  </template>
                </el-table-column>
                <el-table-column label="操作" width="150">
                  <template slot-scope="scope">
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
                <el-button type="primary" @click="clickAddOrUp">新增用户</el-button>
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
          width="50%"
          center>
          <div class="dialogMain">
            <el-form ref="form" :model="addForm" label-width="100px">
              <el-form-item label="用户名" required>
                <el-input v-model="addForm.username" placeholder="请输入用户名"></el-input>
              </el-form-item>
              <el-form-item label="姓名" required>
                <el-input v-model="addForm.realname" placeholder="请输入姓名"></el-input>
              </el-form-item>
              <el-form-item label="手机号" required>
                <el-input v-model="addForm.phone" placeholder="请输入手机号"></el-input>
              </el-form-item>
              <el-form-item label="备注">
                <el-input type="textarea" v-model="addForm.note"></el-input>
              </el-form-item>
            </el-form>
          </div>
          <span slot="footer" class="dialog-footer">
            <el-button @click="centerDialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="addSaveForm()">确 定</el-button>
          </span>
        </el-dialog>
        <!-- 查看用户所属单位 -->
        <el-dialog
          title="所属单位"
          :visible.sync="belongIf"
          width="50%"
          center>
          <div class="dialogMain">
            <el-table :data="belongList" style="width: 100%">
                <el-table-column prop="name" label="单位名称"></el-table-column>
                <el-table-column prop="leadername" label="单位领导"></el-table-column>
                <el-table-column prop="leaderphone" label="领导电话"></el-table-column>
                <el-table-column prop="address" label="地址"></el-table-column>
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
        gsName:'',
        belongIf: false,
        writeCardIfIs:false,
        //用户id和用户类型
        userId: sessionStorage.getItem("userId"),
        userType:sessionStorage.getItem("userType"),
        page: 1,
        pageSize: 5,
        totalNum: 100,
        //筛选
        form: {
          phone: '',
          name: '',
          type:'',
          starttime: '',
          endtime: '',
          role: '',
          realname:''
        },
        //弹框title
        addOrUpTit:'',
        //新增与编辑form
        addForm:{
          id:null,
          username: null,
          phone: null,
          note: null,
          type: null,
          userid: sessionStorage.getItem('userId'),
          belongid: null,
          role: null,
          realname: null
        },
        //表格数据
        tableData: [],
        //单位列表
        belongList: [],
        //新增单位列表
        addBelongList: [],
        //类型筛选列表
        tableTypes:[],
        roleList:[],
        //批量选择
        multipleSelection: [],
        centerDialogVisible: false,
        endPickerOptions: {
              disabledDate(time) {
                  return time.getTime() < Date.now() - 8.64e7;
              }
          },
      };
    },
    mounted() {
      this.gsName = this.$route.query.gsName;
      this.getData();
      // this.getSelectList();
    },
    methods: {
      //获取用户类型
      // getSelectList(){
      //   var _this = this;
      //   var obj = {"value":'',"text":"全部"};
      //   this.$ajax.get("/recovery/user/typeList",{
      //     params:{
      //       type: sessionStorage.getItem('userType')
      //     }
      //   }).then(function(response){
      //         console.log("type列表",response);
      //         _this.tableTypes = response.data.list;
      //         _this.tableTypes.unshift(obj);
      //   })
      //   this.$ajax.get("/recovery/user/roleList").then(function(response){
      //         console.log("role列表",response);
      //         _this.roleList = response.data.list;
      //         _this.roleList.unshift(obj);
      //   })
      // },
      //获取用户信息
      getData(){
        var that = this;
        if(this.form.starttime == null){
          this.form.starttime = "";
        }
        if(this.form.endtime == null){
          this.form.endtime = "";
        }
        // console.log(this.form.sysstarttime);
        this.$ajax.get("/recovery/user/selectByPage",{
            params:{
              page: this.page,
              rows: this.pageSize,
              phone: this.form.phone,
              realname: this.form.realname,
              type: this.$route.query.type,
              role: '管理员',
              belongid: this.$route.query.belongid,
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
      //查看用户单位
      lookUser(id){
        var that = this;
        this.belongList = [];
        this.$ajax.get("/recovery/user/userDetail",{
            params:{
              userid: id
            }
        }).then(function(response){
          console.log(response)
          if(response.data.status == 260){
            that.belongList.push(response.data.unit);
            that.belongIf = true;
          }else{

          }
        })
        
      },
      //切换单位列表
      typeChange(val){
        var that = this;
        that.addForm.belongid = null;
        this.belongList = [];
        this.$ajax.get("/recovery/user/unitList",{
            params:{
              type: val
            }
        }).then(function(response){
          console.log(response)
          if(response.data.status == 260){
            that.addBelongList = response.data.list;
          }else{

          }
        })
      },
      clearAddForm(){
        this.addForm = {
          id:null,
          username: null,
          phone: null,
          note: null,
          type: this.$route.query.type,
          userid: sessionStorage.getItem('userId'),
          belongid: this.$route.query.belongid,
          role: '管理员',
          realname: null
        };
      },
      //点击新增/编辑
      clickAddOrUp(){
        this.centerDialogVisible = true;
        this.addOrUpTit='新增用户';
        //清空form
        this.clearAddForm();
      },
      //编辑
      handleEdit(index, row) {
        console.log(index, row);
        this.centerDialogVisible = true;
        this.addOrUpTit = "编辑用户";
        this.addForm = {
          id: row.id,
          username: row.username,
          password: row.password,
          phone: row.phone,
          note: row.note,
          type: row.type,
          userid: sessionStorage.getItem('userId'),
          belongid: row.belongid,
          time: row.time,
          role: row.role,
          realname: row.realname
        };
      },
      // 新增
      addSaveForm(index){
        console.log(this.addForm)
        
        if(this.addForm.realname == null ||this.addForm.role == null || this.addForm.username == null || this.addForm.phone == null || this.addForm.type == null || this.addForm.belongid == null){
          return this.$message.error("*为必填项！");
        }
        var that = this;
        var params = this.$qs.stringify(this.addForm, { indices: false })
        if(this.addOrUpTit == "新增用户"){
          this.$ajax.post("/recovery/user/insert",params).then(function(response){
            if(response.data.status == 260){
              that.centerDialogVisible = false;
              that.$message.success("添加成功")
              that.getData();
              //清空form
              that.clearAddForm();
            }
          })
        }else{
          this.$ajax.put("/recovery/user/update",params).then(function(response){
            if(response.data.status == 260){
              that.centerDialogVisible = false;
              that.$message.success("编辑成功")
              that.getData();
              //清空form
              that.clearAddForm();
            }
          })
        }
      },
      //批量删除
      delAll(){
        this.$confirm('此操作将永久删除所选用户, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          if(this.multipleSelection.length < 1){
            return this.$message.success("请先选择删除对象");
          }
          if(this.multipleSelection.length == this.tableData.length){
            this.page = Number(this.page)-1;
          }
          var that = this;
          // var params = this.$qs.stringify({
          //   ids: this.multipleSelection
          // }, { indices: false })
          this.$ajax.delete("/recovery/user/deletes",{data:this.multipleSelection}).then(function(response){
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
        console.log(index, row);
        this.$confirm('此操作将永久删除该用户, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          if(this.tableData.length == 1){
            this.page = Number(this.page)-1;
          }
          var that = this;
          this.$ajax.delete("/recovery/user/delete",{params:{
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
