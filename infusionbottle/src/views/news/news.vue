<template>
    <div id="yudingBox">
        <div class="selectBox">
          <el-form ref="form" :model="form" label-width="70px">
            <el-form-item label="通知内容">
                <el-input v-model="form.item" placeholder="请输入通知内容" @change="selectList"></el-input>
            </el-form-item>
            <el-form-item label="公告类型">
                  <el-select placeholder="请选择公告类型" v-model="addForm.type">
                      <el-option v-for="(item,index) in typeList" :key="index" :label="item.text" :value="item.value" @change="selectList"></el-option>
                  </el-select>
                </el-form-item>
                <el-form-item label="重要程度">
                  <el-select placeholder="请选择公告类型" v-model="addForm.importance">
                      <el-option v-for="(item,index) in importanceList" :key="index" :label="item.text" :value="item.value" @change="selectList"></el-option>
                  </el-select>
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
                <el-table-column prop="time" label="创建时间" sortable>
                  <template slot-scope="scope">
                    {{scope.row.time | formatDate}}
                  </template>
                </el-table-column>
                <el-table-column prop="title" label="公告标题"></el-table-column>
                <el-table-column prop="item" label="公告内容" width="320"></el-table-column>
                <el-table-column prop="importance" label="重要程度"></el-table-column>
                <el-table-column prop="type" label="公告类型"></el-table-column>
                <el-table-column prop="user.username" label="发布人"></el-table-column>
                <el-table-column prop="" label="附件">
                   <template slot-scope="scope">
                    <el-link type="primary" :href="scope.row.file" :disabled="scope.row.file == null">下载附件</el-link>
                  </template>
                </el-table-column>
                <el-table-column prop="" label="图片">
                  <template slot-scope="scope">
                    <el-link type="primary" :href="scope.row.img" :disabled="scope.row.img == null">下载图片</el-link>
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
                <el-button type="primary" @click="clickAddOrUp">添加公告</el-button>
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
              <el-form-item label="公告标题" required>
                  <el-input v-model="addForm.title"></el-input>
              </el-form-item>
              <el-form-item label="公告内容" required>
                  <el-input
                    type="textarea"
                    placeholder="请输入内容"
                    v-model="addForm.item"
                    maxlength="2000"
                    show-word-limit
                  >
                  </el-input>
              </el-form-item>
              <el-form-item label="公告类型" required>
                  <el-select placeholder="请选择公告类型" v-model="addForm.type">
                      <el-option v-for="(item,index) in typeList" :key="index" v-show="item.text != '全部'" :label="item.text" :value="item.value"></el-option>
                  </el-select>
                </el-form-item>
                <el-form-item label="重要程度" required>
                  <el-select placeholder="请选择公告类型" v-model="addForm.importance">
                      <el-option v-for="(item,index) in importanceList" :key="index" v-show="item.text != '全部'" :label="item.text" :value="item.value"></el-option>
                  </el-select>
                </el-form-item>
              <el-form-item label="上传附件">
                   <fileupload directory="notice" :flag="flagIf" @fileUrl="getFileUrl"></fileupload>
              </el-form-item>
            </el-form>
            <div class="iptBox">
                <span>上传图片</span>
                <div>
                    <aliUpFile :file="addForm.img" server='/recovery' directory='notice' fileNum="1" @fileDel='fileDels' @upFileData="upFileDatas"></aliUpFile>
                </div>
            </div>
          </div>
          <span slot="footer" class="dialog-footer">
            <el-button @click="centerDialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="addSaveForm()">确 定</el-button>
          </span>
        </el-dialog>
    </div>
</template>
<script>
import fileupload from "../../components/fileupload.vue"
import aliUpFile from "../../components/aliuploadrenyuan"
  export default {
    components:{
        fileupload,
        aliUpFile,
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
            item: null,
            note:null,
            file: null,
            title: null,
            importance: null,
            type: null,
            userid: null,
            img: null,
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
        flagIf: 0,
        typeList:[],
        importanceList:[]
      };
    },
    mounted() {
      this.getData();
      this.getSelectList();
    },
    methods: {
      getSelectList(){
        var that = this;
        var obj = {"value":'',"text":"全部"};
        this.$ajax.get("/recovery/notice/typeList").then(function(response){
              console.log("type列表",response);
              that.typeList = response.data.list;
              that.typeList.unshift(obj);
        })
        this.$ajax.get("/recovery/notice/importanceList").then(function(response){
              console.log("重要程度列表",response);
              that.importanceList = response.data.list;
              that.importanceList.unshift(obj);
        })
      },
      getFileUrl(data){
        console.log(data);
        this.addForm.file = data;
      },
      //接收图片
      upFileDatas(data){
          this.addForm.img = data;
      },
      //删除图片
      fileDels(data){
          this.addForm.img = '';
      },
      //获取区域信息
      getData(){
        var that = this;
        this.$ajax.get("/recovery/notice/selectVByPage",{
            params:{
              page: this.page,
              rows: this.pageSize,
              item: this.form.item,
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
            item: null,
            note:null,
            file: null,
            title: null,
            importance: null,
            type: null,
            userid: sessionStorage.getItem("userId"),
            img: null,
        };
        this.flagIf = 1;
      },
      //点击新增/编辑
      clickAddOrUp(){
        this.centerDialogVisible = true;
        this.addOrUpTit='创建公告';
        this.clearForm();
      },
      //编辑
      handleEdit(index, row) {
        console.log(index, row);
        var that = this;
        this.centerDialogVisible = true;
        this.addOrUpTit = "编辑公告";
        that.addForm = {
            id: row.id,
            item: row.item,
            note: row.note,
            file: row.file,
            time: row.time,
            title: row.title,
            importance: row.importance,
            type: row.type,
            userid: row.userid,
            img: row.img,
        }
      },
      // 新增
      addSaveForm(){
        console.log(this.addForm)
        var that = this;
        if(this.addForm.item == null || this.addForm.title == null || this.addForm.type == null || this.addForm.importance == null){
          this.$message.error("*为必填项！");
          return;
        }
        var params = this.$qs.stringify(this.addForm, { indices: false })
        if(this.addOrUpTit == "创建公告"){
          this.$ajax.post("/recovery/notice/insert ",params).then(function(response){
            if(response.data.status == 260){
              that.centerDialogVisible = false;
              that.$message.success("添加成功")
              that.getData();
              //清空form
              that.clearForm();
            }
          })
        }else{
          this.$ajax.put("/recovery/notice/update",params).then(function(response){
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
        this.$confirm('此操作将永久删除选中的通知, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          if(this.multipleSelection.length == this.tableData.length){
            this.page = Number(this.page)-1;
          }
          var that = this;
          this.$ajax.delete("/recovery/notice/deletes",{data:this.multipleSelection}).then(function(response){
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
        this.$confirm('此操作将删除该通知, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          console.log(index, row);
          if(this.tableData.length == 1){
            this.page = Number(this.page)-1;
          }
          var that = this;
          this.$ajax.delete("/recovery/notice/delete",{params:{
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
.workImgBox{
    width: 100%;
    padding: 0 5%;
    background: #fff;
    margin-top: .1rem;
    padding: .05rem;
    box-sizing: border-box;
  }
  .workImgBox > p{
    height: .3rem;
    line-height: .3rem;
    font-size: .16rem;
    color: #333;
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
    width: 160px;
    padding-right: 10px;
    text-align: right;;
}
</style>
