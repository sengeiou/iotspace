<template>
    <div id="yudingBox">
        <div class="selectBox">
          <el-form ref="form" :model="form" label-width="60px">
            <el-form-item label="颗粒料类别" label-width="90px">
                <el-select v-model="form.type" placeholder="请选择颗粒料类别" @change="selectList">
                    <el-option v-for="(item,index) in tableTypes" :key="index"  :label="item.name" :value="item.id"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="去向">
                <el-select v-model="form.source" placeholder="请选择破碎料去向" @change="selectList">
                    <el-option v-for="(item,index) in sourceList" :key="index"  :label="item.text" :value="item.value"></el-option>
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
            <el-table :data="tableData" style="width: 100%" @selection-change="handleSelectionChange">
                <!-- <el-table-column
                  type="selection"
                  width="55"
                  >
                </el-table-column> -->
                <el-table-column prop="time" label="出库时间" sortable>
                  <template slot-scope="scope">
                    {{scope.row.time | formatDate}}
                  </template>
                </el-table-column>
                <el-table-column prop="gmaterialcategory.name" label="颗粒料类别"></el-table-column>
                <el-table-column prop="source" label="去向"></el-table-column>
                <el-table-column prop="weight" label="重量（千克）"></el-table-column>
                <el-table-column prop="number" label="数量（袋）"></el-table-column>
                <el-table-column prop="note" label="用途"></el-table-column>
                <!-- <el-table-column label="操作" width="200">
                  <template slot-scope="scope">
                    <el-button
                      size="mini"
                      @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                    <el-button
                      size="mini"
                      type="danger"
                      @click="handleDelete(scope.$index, scope.row.id)">删除</el-button>
                  </template>
                </el-table-column> -->
            </el-table>
            <div class="pafinationBox">
              <div>
                  <el-button type="primary" @click="clickAddOrUp">添加自用颗粒料</el-button>
                  <!-- <el-button type="warning" @click="delAll">批量删除</el-button> -->
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
        <!-- 111 -->
        <!-- git -->
        <el-dialog
          :title="addOrUpTit"
          :visible.sync="centerDialogVisible"
          width="50%"
          center>
          <div class="dialogMain">
            <el-form ref="addForm" :model="addForm" label-width="100px">
                <el-form-item label="颗粒料类别">
                    <el-select v-model="addForm.gmaterialcategoryid" placeholder="请选择类别" @change="getWeight">
                        <el-option v-for="(item,index) in tableTypes" :key="index" v-show="item.name != '全部'"  :label="item.name" :value="item.id"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="重量（kg）">
                    <el-input v-model="addForm.weight" :placeholder="weightText"></el-input>
                </el-form-item>
                <el-form-item label="数量（袋）">
                    <el-input v-model="addForm.number" placeholder="请输入颗粒料数量"></el-input>
                </el-form-item>
                <el-form-item label="用途">
                    <el-input v-model="addForm.note" placeholder="请输入颗粒料用途"></el-input>
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
        form:{
          type:'',
          starttime: '',
          endtime: '',
          source: '',
        },
        //弹框title
        addOrUpTit:'',
        //新增与编辑form
        addForm:{
            id: null,
            gmaterialcategoryid: null,
            weight: null,
            number: null,
            treatmentplantid: sessionStorage.getItem('belongId'),
        },
        //表格数据
        tableData: [],
        tableTypes: [],
        typeInfo:[],
        sourceList: [],
        //批量选择
        multipleSelection: [],
        centerDialogVisible: false,
        endPickerOptions: {
              disabledDate(time) {
                  return time.getTime() < Date.now() - 8.64e7;
              }
        },

        weightText: "请输入重量",
        weightNumver: 0,
      };
    },
    mounted() {
      this.getData();
      this.getSelectList();
    },
    watch:{
      addForm:function(a,b){
        console.log(a)
      }
    },
    methods: {
      //获取库存重量
      getWeight(){
        var that = this;
         //颗粒料类别
        this.$ajax.get("/recovery/gmaterialcategory/selectByPrimaryKey",{
          params:{
            id: that.addForm.gmaterialcategoryid,
          }
        }).then(function(response){
              console.log("库存重量",response);
              if(response.data.status == 260){
                that.weightText = "库存剩余"+response.data.object.stock+"kg";
                that.weightNumber = response.data.object.stock;
              }else{
                that.weightText = "请输入重量";
                that.weightNumber = 0;
              }
              
        })
      },
      //获取类别
      getSelectList(){
        var _this = this;
        var obj = {"id":'',"name":"全部"};
        this.$ajax.get("/recovery/gmaterialcategory/selectByTreatmentplantid",{
          params:{
            treatmentplantid: sessionStorage.getItem('belongId'),
          }
        }).then(function(response){
              console.log("type列表",response);
              _this.tableTypes = response.data.list;
              _this.tableTypes.unshift(obj);
        })
        //出库去向
        var objs = {"value":'',"text":"全部"};
        this.$ajax.get("/recovery/gmaterialout/sourceList",{
          params:{
            treatmentplantid: sessionStorage.getItem('belongId'),
          }
        }).then(function(response){
              console.log("去向列表",response);
              _this.sourceList = response.data.list;
              _this.sourceList.unshift(objs);
        })
      },
      //获取颗粒料信息
      getData(){
        var that = this;
        if(this.form.starttime == null){
          this.form.starttime = "";
        }
        if(this.form.endtime == null){
          this.form.endtime = "";
        }
        this.$ajax.get("/recovery/gmaterialout/selectVByPage",{
            params:{
              page: this.page,
              rows: this.pageSize,
              treatmentplantid: sessionStorage.getItem('belongId'),
              gmaterialcategoryid: this.form.type,
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
        // this.$ajax.get("/recovery/gmaterialout/sumWeightGroupByCategory",{
        //     params:{
        //       treatmentplantid: sessionStorage.getItem('belongId'),
        //       categoryid: this.form.type,
        //       begintime: this.form.starttime==""? "" : new Date(this.form.starttime)/1000*1000,
        //       endtime: this.form.endtime==""? "" : new Date(this.form.endtime)/1000*1000,
        //     }
        // }).then(function(response){
        //   console.log("类别统计",response)
        //   if(response.data.status == 260){
        //     that.typeInfo = response.data.list;
        //   }else{
        //     that.typeInfo = [];
        //   }
        // })
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
          type:'',
          starttime: '',
          endtime: '',
          source: '',
        }
        this.getData();
      },

      //清空创建编辑form
      clearForm(){
        this.addForm = {
            id: null,
            gmaterialcategoryid: null,
            weight: null,
            number: null,
            source: null,
            treatmentplantid: sessionStorage.getItem('belongId'),
        } 
      },
      //点击新增/编辑
      clickAddOrUp(){
        this.centerDialogVisible = true;
        this.addOrUpTit='添加自用颗粒料';
        this.clearForm();
      },
      //编辑
      handleEdit(index, row) {
        console.log(index, row);
        var that = this;
        this.centerDialogVisible = true;
        this.addOrUpTit = "编辑颗粒料";
        that.addForm = {
            id: row.id,
            gmaterialcategoryid: row.gmaterialcategoryid,
            weight: row.weight,
            source: row.source,
            number: row.number,
            treatmentplantid: row.treatmentplantid,
            time: row.time
        } 
      },
      // 新增
      addSaveForm(index){
        console.log(this.addForm)
        if(this.addForm.gmaterialcategoryid == null){
          this.$message.error("*为必填项！");
          return;
        }
        if(Number(this.addForm.weight) > Number(this.weightNumber)){
          this.$message.error("重量库存不足！");
          return;
        }
        var that = this;
        var params = this.$qs.stringify(this.addForm, { indices: false })
        if(this.addOrUpTit == "添加自用颗粒料"){
          this.$ajax.post("/recovery/gmaterialout/insert",params).then(function(response){
            if(response.data.status == 260){
              that.centerDialogVisible = false;
              that.$message.success("添加成功")
              that.getData();
              //清空form
              that.clearForm();
            }
          })
        }else{
          this.$ajax.put("/recovery/gmaterialout/update",params).then(function(response){
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
        this.$confirm('此操作将永久删除选中的颗粒料, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          if(this.multipleSelection.length == this.tableData.length){
            this.page = Number(this.page)-1;
          }
          var that = this;
          this.$ajax.delete("/recovery/gmaterialout/deletes",{data:this.multipleSelection}).then(function(response){
            if(response.data.status == 260){
              that.$message.success("删除成功");
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
        this.$confirm('此操作将删除该颗粒料, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          console.log(index, row);
          if(this.tableData.length == 1){
            this.page = Number(this.page)-1;
          }
          var that = this;
          this.$ajax.delete("/recovery/gmaterialout/delete",{params:{
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
.typeInfoBox{
  width: 100%;
  height: 30px;
  display: flex;
  flex-wrap: wrap;
  align-items: center;
}
.typeInfoBox > p {
  padding: 0 10px;
}
</style>
