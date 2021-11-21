<template>
    <div id="yudingBox">
        <div class="selectBox">
          <el-form ref="form" :model="form" label-width="70px">
            <el-form-item label="单位名称">
                <el-input v-model="form.item" placeholder="请输入单位名称" @change="selectList"></el-input>
            </el-form-item>
            <el-form-item label="地址">
                <el-input v-model="form.address" placeholder="请输入单位地址" @change="selectList"></el-input>
            </el-form-item>
            <!-- <el-form-item label="级别">
                <el-select v-model="form.level" placeholder="请选择单位级别" @change="selectList">
                    <el-option v-for="(item,index) in levelList" :key="index"  :label="item.text" :value="item.value"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="上级">
              <el-select v-model="form.supervisionunitid" placeholder="请选择所属上级" @change="selectList">
                    <el-option v-for="(item,index) in supervisionunitList" :key="index"  :label="item.name" :value="item.id"></el-option>
                </el-select>
            </el-form-item> -->
            <el-form-item>
                <el-button type="warning" size="small" @click="clearSelect" style="font-size:14px">清空筛选</el-button>
                <el-button type="primary"  size="small" @click="add" style="font-size:14px">批量导入</el-button>
                <input type="file" @change="fileChanged" ref="upFile" v-show="false">
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
                <el-table-column prop="name" label="单位名称"></el-table-column>
                <el-table-column prop="level" label="级别"></el-table-column>
                <el-table-column prop="address" label="所属行政区域" v-if="userType == '系统'||userType == '国家管理单位'||userType == '省管理单位'||userType == '市管理单位'"></el-table-column>
                <el-table-column prop="address" label="地址" v-else></el-table-column>
                <el-table-column label="操作" width="270">
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
                <el-button type="primary" @click="clickAddOrUp">添加监管单位</el-button>
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
            <el-form ref="addForm" :model="addForm" label-width="120px">
                <el-form-item label="单位名称" required>
                    <el-input v-model="addForm.name"></el-input>
                </el-form-item>
                <!-- <el-form-item label="级别">
                    <el-select v-model="addForm.level" placeholder="请选择单位级别" @change="getFatherList(addForm.level)">
                        <el-option v-for="(item,index) in levelList" v-show="item.text != '全部'" :key="index"  :label="item.text" :value="item.value"></el-option>
                    </el-select>
                </el-form-item> -->
                <el-form-item label="所属行政区域" required v-if="userType == '系统'||userType == '国家管理单位'||userType == '省管理单位'||userType == '市管理单位'">
                    <el-input v-model="addForm.address"></el-input>
                </el-form-item>
                <el-form-item label="地址" required v-else>
                    <el-input v-model="addForm.address"></el-input>
                </el-form-item>
                <!-- <el-form-item label="所属上级" required v-show="addForm.level != '全国'">
                    <el-select v-model="addForm.pid" placeholder="请选择所属上级">
                        <el-option v-for="(item,index) in fatherList" :key="index"  :label="item.name" :value="item.id"></el-option>
                    </el-select>
                </el-form-item> -->
            </el-form>
          </div>
          <span slot="footer" class="dialog-footer">
            <el-button @click="centerDialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="addSaveForm()">确 定</el-button>
          </span>
        </el-dialog>
    </div>
</template>
<script>
  export default {
    data() {
      return {
        userType:sessionStorage.getItem('userType'),
        page: 1,
        pageSize: 10,
        totalNum: 100,
        //筛选
        form: {
          item:'',
          address:'',
          level:'',
          supervisionunitid:''
        },
        //弹框title
        addOrUpTit:'',
        //新增与编辑form
        addForm:{
            id: null,
            name: null,
            level: null,
            address: null,
            pid: null,
        },
        //表格数据
        tableData: [],
        levelList:[],
        fatherList: [],
        supervisionunitList:[],
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
    },
    methods: {
      //设置管理员
      setAdminUser(row){
        var type;
        if(sessionStorage.getItem('userType') == '系统'){
            type = '国家管理单位';
        }else if(sessionStorage.getItem('userType') == '国家管理单位'){
            type  = '省管理单位';
        }else if(sessionStorage.getItem('userType') == '省管理单位'){
            type  = '市管理单位';
        }else if(sessionStorage.getItem('userType') == '市管理单位'){
            type  = '区管理单位';
        }
        this.$router.replace({
          path:'/adminUser',
          query:{
            belongid: row.id,
            gsName: row.name,
            type: type
          }
        })
      },
      
      //获取监管单位信息
      getData(){
        var that = this;
        this.$ajax.get("/recovery/supervisionunit/selectVByPage",{
            params:{
              page: this.page,
              rows: this.pageSize,
              address: this.form.address,
              name: this.form.item,
              pid: sessionStorage.getItem('belongId'),
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
      //导入科室
      //触发upfile
      add() {
          this.$refs.upFile.click();
      },
      fileChanged() {
          const list = this.$refs.upFile.files;
          
          console.log(list)
          if (list.length == 0) {
              alert("请选择文件");
              return;
          }
          var that = this;
          for(var i=0;i<list.length;i++){
              var formData = new FormData();
              var f=list[i];

              formData.append("excelFile",f);
              formData.append("pid",sessionStorage.getItem('belongId'));
              formData.append("usertype",sessionStorage.getItem('userType'));
              this.$ajax.post("/recovery/addSupervisionunit/add", formData, {
                headers: {'Content-Type': 'multipart/form-data'}
              }).then(function(res){
                console.log(res.data.status == 260)
                  that.getData();
                  that.$refs.upFile.value = '';
                  if(res.data.status == 260){
                    that.$message.success('导入监管单位成功');
                  }else{
                    that.$message.error('导入监管单位失败,请重试！');
                  }
                  
              })
          }
      },
      //清空筛选
      clearSelect(){
            this.form = {
              item: '',
              address:'',
              level:'',
              supervisionunitid:''
            }
            this.getData();
      },
      //清空创建编辑form
      clearForm(){
        this.addForm = {
            id: null,
            name: null,
            level: '全国',
            address: null,
            pid: sessionStorage.getItem('belongId'),
        } 
        console.log(sessionStorage.getItem('userType'))
        if(sessionStorage.getItem('userType') == '系统'){
            this.addForm.level = '全国';
        }else if(sessionStorage.getItem('userType') == '国家管理单位'){
            this.addForm.level  = '省';
        }else if(sessionStorage.getItem('userType') == '省管理单位'){
            this.addForm.level  = '市';
        }else if(sessionStorage.getItem('userType') == '市管理单位'){
            this.addForm.level  = '区';
        }
      },
      //点击新增/编辑
      clickAddOrUp(){
        this.centerDialogVisible = true;
        this.addOrUpTit='创建监管单位';
        this.clearForm();

      },
      //编辑
      handleEdit(index, row) {
        console.log(index, row);
        var that = this;
        this.centerDialogVisible = true;
        this.addOrUpTit = "编辑监管单位";
        that.addForm = {
            id: row.id,
            name: row.name,
            level: row.level,
            pid: row.pid,
            address: row.address,
        }
      },
      // 新增
      addSaveForm(){
        console.log(this.addForm)
        var that = this;
        if(this.addForm.name == null || this.addForm.level == null || this.addForm.address == null){
          this.$message.error("*为必填项！");
          return;
        }
        var params = this.$qs.stringify(this.addForm, { indices: false })
        if(this.addOrUpTit == "创建监管单位"){
          this.$ajax.post("/recovery/supervisionunit//insert ",params).then(function(response){
            if(response.data.status == 260){
              that.centerDialogVisible = false;
              that.$message.success("添加成功")
              that.getData();
              //清空form
              that.clearForm();
            }
          })
        }else{
          this.$ajax.put("/recovery/supervisionunit/update",params).then(function(response){
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
        this.$confirm('此操作将永久删除选中的监管单位, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          if(this.multipleSelection.length == this.tableData.length){
            this.page = Number(this.page)-1;
          }
          var that = this;
          this.$ajax.delete("/recovery/supervisionunit/deletes",{data:this.multipleSelection}).then(function(response){
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
        this.$confirm('此操作将删除该监管单位, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          console.log(index, row);
          if(this.tableData.length == 1){
            this.page = Number(this.page)-1;
          }
          var that = this;
          this.$ajax.delete("/recovery/supervisionunit/delete",{params:{
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
</style>
