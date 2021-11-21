<template>
    <div id="yudingBox">
        <div class="selectBox">
          <el-form ref="form" :model="form" label-width="90px">
            <el-form-item label="破碎料类别">
              <el-input v-model="form.itemName" @change="selectList"></el-input>
            </el-form-item>
            <el-form-item label-width="10px">
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
                <el-table-column prop="name" label="破碎料类别"></el-table-column>
                <!-- <el-table-column prop="stock" label="库存"></el-table-column> -->
                <el-table-column prop="note" label="备注"></el-table-column>
                <el-table-column label="操作" width="200">
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
                  <el-button type="primary" @click="clickAddOrUp">创建破碎料类别</el-button>
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
            <el-form ref="addForm" :model="addForm" label-width="100px">
                <el-form-item label="破碎料类别" required>
                    <el-input v-model="addForm.name"></el-input>
                </el-form-item>
                <el-form-item label="备注">
                    <el-input v-model="addForm.note" placeholder="请输入类别备注"></el-input>
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
  export default {
    data() {
      return {
        page: 1,
        pageSize: 10,
        totalNum: 100,
        form:{
          itemName:''
        },
        //弹框title
        addOrUpTit:'',
        //新增与编辑form
        addForm:{
            id: null,
            name: null,
            note: null,
            treatmentplantid: sessionStorage.getItem('belongId'),
        },
        //表格数据
        tableData: [],
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
      this.getData();
    },
    watch:{
      addForm:function(a,b){
        console.log(a)
      }
    },
    methods: {
      //获取信息
      getData(){
        var that = this;
        this.$ajax.get("/recovery/cmaterialcategory/selectByPage",{
            params:{
              page: this.page,
              rows: this.pageSize,
              treatmentplantid: sessionStorage.getItem('belongId'),
              name: this.form.itemName
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
              itemName:''
            }
            this.getData();
      },

      //清空创建编辑form
      clearForm(){
        this.addForm = {
            id: null,
            name: null,
            note: null,
            treatmentplantid: sessionStorage.getItem('belongId'),
        } 
      },
      //点击新增/编辑
      clickAddOrUp(){
        this.centerDialogVisible = true;
        this.addOrUpTit='创建破碎料类别';
        this.clearForm();
      },
      //编辑
      handleEdit(index, row) {
        console.log(index, row);
        var that = this;
        this.centerDialogVisible = true;
        this.addOrUpTit = "编辑破碎料类别";
        that.addForm = {
            id: row.id,
            name: row.name,
            note: row.note,
            stock: row.stock,
            treatmentplantid: row.treatmentplantid,
        } 
      },
      // 新增
      addSaveForm(index){
        console.log(this.addForm)
        if(this.addForm.name == null){
          this.$message.error("*为必填项！");
          return;
        }
        var that = this;
        var params = this.$qs.stringify(this.addForm, { indices: false })
        if(this.addOrUpTit == "创建破碎料类别"){
          this.$ajax.post("/recovery/cmaterialcategory/insert",params).then(function(response){
            if(response.data.status == 260){
              that.centerDialogVisible = false;
              that.$message.success("添加成功")
              that.getData();
              //清空form
              that.clearForm();
            }
          })
        }else{
          this.$ajax.put("/recovery/cmaterialcategory/update",params).then(function(response){
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
        this.$confirm('此操作将永久删除选中的破碎料类别, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          if(this.multipleSelection.length == this.tableData.length){
            this.page = Number(this.page)-1;
          }
          var that = this;
          this.$ajax.delete("/recovery/cmaterialcategory/deletes",{data:this.multipleSelection}).then(function(response){
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
        this.$confirm('此操作将删除该破碎料类别, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          console.log(index, row);
          if(this.tableData.length == 1){
            this.page = Number(this.page)-1;
          }
          var that = this;
          this.$ajax.delete("/recovery/cmaterialcategory/delete",{params:{
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
    margin:  auto;
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
  justify-content: center;
}
</style>
