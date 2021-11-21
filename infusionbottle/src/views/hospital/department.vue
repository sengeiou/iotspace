<template>
    <div id="yudingBox">
        <div class="selectBox">
          <el-form ref="form" :model="form" label-width="90px">
            <!-- <el-form-item label="所属医疗机构">
              <el-select v-model="form.hospitalid" placeholder="请选择医疗机构" @change="selectList">
                <el-option v-for="(item,index) in hospitalList" :key="index" :label="item.name" :value="item.id"></el-option>
              </el-select>
            </el-form-item> -->
            <el-form-item label="科室名称">
                <el-input v-model="form.item" @change="selectList"></el-input>
            </el-form-item>
            <el-form-item label="负责人">
                <el-input v-model="form.leadername" @change="selectList"></el-input>
            </el-form-item>
            <el-form-item label-width="30px">
                <el-button type="warning" size="small" @click="clearSelect" style="font-size:14px">清空筛选</el-button>
                <el-button type="primary"  size="small" @click="add" style="font-size:14px">批量导入</el-button>
                <el-button type="success" size="small" @click="exportBarCode" style="font-size:14px">导出科室二维码</el-button>
                <el-button type="success" size="small" @click="exportMobanFile" style="font-size:14px">下载模版</el-button>
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
                <el-table-column prop="hospital.name" label="所属医疗机构"></el-table-column>
                <el-table-column prop="name" label="科室名称"></el-table-column>
                <el-table-column prop="leadername" label="负责人"></el-table-column>
                <el-table-column prop="leaderphone" label="负责人电话"></el-table-column>
                <el-table-column prop="note" label="备注"></el-table-column>
                <el-table-column label="操作" width="300">
                  <template slot-scope="scope">
                    <el-button
                      size="mini"
                      @click="doCopy(scope.row)">复制科室码</el-button>
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
                <el-button type="primary" @click="clickAddOrUp">添加科室</el-button>
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
              <!-- <el-form-item label="所属医疗机构">
                <el-select v-model="addForm.hospitalid" placeholder="请选择医疗机构">
                  <el-option v-for="(item,index) in hospitalList" :key="index" v-show='item.name !=  "全部"' :label="item.name" :value="item.id"></el-option>
                </el-select>
              </el-form-item> -->
              <el-form-item label="科室名称" required>
                  <el-input v-model="addForm.name"></el-input>
              </el-form-item>
              <el-form-item label="负责人" required>
                  <el-input v-model="addForm.leadername"></el-input>
              </el-form-item>
              <el-form-item label="负责人电话" required>
                  <el-input v-model="addForm.leaderphone"></el-input>
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
    </div>
</template>
<script>
//引入省/市/区数据json文件
import lngLatMap from '../../components/lnglatmap'
  export default {
    components:{
      lngLatMap
    },
    data() {
      return {
        page: 1,
        pageSize: 10,
        totalNum: 100,
        //筛选
        form: {
          item: null,
          leadername: null,
          // hospitalid: null,
        },
        //弹框title
        addOrUpTit:'',
        //新增与编辑form
        addForm:{
            id: null,
            name: null,
            leadername: null,
            leaderphone: null,
            hospitalid: null,
            note: null,
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
        //医疗机构列表
        // hospitalList:[],
      };
    },
    mounted() {
      this.addForm.hospitalid = this.$route.query.hospitalId;
      this.getData();
      // this.getHospitalList();
    },
    watch:{
      addForm:function(a,b){
        console.log(a)
      }
    },
    methods: {
      //下载模版
      exportMobanFile(row){
        window.location.href = "http://hrt.chinagrp.org.cn/recovery/fileDownload/departmentExcel"; 
      },
      //导出
      exportBarCode(row){
        var hospitalId = this.$route.query.hospitalId == undefined?sessionStorage.getItem('belongId'):this.$route.query.hospitalId;
        console.log(hospitalId)
        window.location.href = "http://hrt.chinagrp.org.cn/recovery/department/barcodeExcel?hospitalid="+hospitalId; 
      },
      doCopy: function (val) {
        console.log()
        var hospitalId = this.$route.query.hospitalId == undefined?sessionStorage.getItem('belongId'):this.$route.query.hospitalId;
        var message = hospitalId+"-"+new Date()/1000*1000+"-"+val.id;
        console.log(message)    
    this.$copyText(message).then(function (e) {
         console.log(e)
        }, function (e) {
         console.log(e)
        })
       },
      //获取医疗机构列表
      getHospitalList(){
        var _this = this;
        var obj = {"id":'',"name":"全部"};
        //医疗机构列表
        this.$ajax.get("/recovery/hospital/selectAll").then(function(response){
              console.log("医疗机构列表",response);
              _this.hospitalList = response.data.list;
              _this.hospitalList.unshift(obj);
        })
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
          var hospitalId = this.$route.query.hospitalId == undefined?sessionStorage.getItem('belongId'):this.$route.query.hospitalId;
          for(var i=0;i<list.length;i++){
              var formData = new FormData();
              var f=list[i];

              formData.append("excelFile",f);
              formData.append("hospitalid",hospitalId);
              this.$ajax.post("/recovery/addDepartmentExcel/add", formData, {
                headers: {'Content-Type': 'multipart/form-data'}
              }).then(function(res){
                console.log(res.data.status == 260)
                  that.getData();
                  that.$refs.upFile.value = '';
                  if(res.data.status == 260){
                    that.$message.success('导入科室成功');
                  }else{
                    that.$message.error('导入科室失败,请重试！');
                  }
                  
              })
          }
      },
      //获取科室信息
      getData(){
        var that = this;
        console.log(this.$route.query.hospitalId)
        this.$ajax.get("/recovery/department/selectVByPage",{
            params:{
              page: this.page,
              rows: this.pageSize,
              name: this.form.item,
              hospitalid: this.$route.query.hospitalId == undefined?sessionStorage.getItem('belongId'):this.$route.query.hospitalId,
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
              item: null,
              leadername: null,
              // hospitalid: null,
            }
            this.getData();
      },
      //清空创建编辑form
      clearForm(){
        this.addForm = {
            id: null,
            name: null,
            leadername: null,
            leaderphone: null,
            hospitalid: this.$route.query.hospitalId == undefined?sessionStorage.getItem('belongId'):this.$route.query.hospitalId,
            note: null,
        };
      },
      //点击新增/编辑
      clickAddOrUp(){
        this.centerDialogVisible = true;
        this.addOrUpTit='创建科室';
        this.clearForm();

      },
      //编辑
      handleEdit(index, row) {
        console.log(index, row);
        var that = this;
        this.centerDialogVisible = true;
        this.addOrUpTit = "编辑科室";
        that.addForm = {
            id: row.id,
            name: row.name,
            leadername: row.leadername,
            leaderphone: row.leaderphone,
            hospitalid: row.hospitalid,
            note: row.note,
        }
      },
      // 新增
      addSaveForm(){
        console.log(this.addForm)
        if(this.addForm.name == null || this.addForm.hospitalid == null || this.addForm.leadername == null || this.addForm.leaderphone == null){
          this.$message.error("*为必填项！");
          return;
        }
        var that = this;
        var params = this.$qs.stringify(this.addForm, { indices: false })
        if(this.addOrUpTit == "创建科室"){
          this.$ajax.post("/recovery/department/insert ",params).then(function(response){
            if(response.data.status == 260){
              that.centerDialogVisible = false;
              that.$message.success("添加成功")
              that.getData();
              //清空form
              that.clearForm();
            }
          })
        }else{
          this.$ajax.put("/recovery/department/update",params).then(function(response){
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
        this.$confirm('此操作将永久删除选中的科室, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          if(this.multipleSelection.length == this.tableData.length){
            this.page = Number(this.page)-1;
          }
          var that = this;
          this.$ajax.delete("/recovery/department/deletes",{data:this.multipleSelection}).then(function(response){
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
        this.$confirm('此操作将删除该科室, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          console.log(index, row);
          if(this.tableData.length == 1){
            this.page = Number(this.page)-1;
          }
          var that = this;
          this.$ajax.delete("/recovery/department/delete",{params:{
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
  width: 100%;
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
