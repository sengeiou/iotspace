<template>
    <div id="yudingBox">
        <div class="selectBox">
          <el-form ref="form" :model="form" label-width="80px">
            <el-form-item label="企业名称">
                <el-input v-model="form.item" placeholder="请输入企业名称" @change="selectList"></el-input>
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
                <el-table-column prop="name" label="企业名称"></el-table-column>
                <el-table-column prop="code" label="统一社会信用代码"></el-table-column>
                <el-table-column prop="username" label="法定代表人"></el-table-column>
                <el-table-column prop="type" label="企业类型"></el-table-column>
                <el-table-column prop="dealtype" label="处置种类"></el-table-column>
                <el-table-column prop="phone" label="联系电话"></el-table-column>
                <el-table-column prop="address" label="地址"></el-table-column>
                <el-table-column prop="department" label="认证部门"></el-table-column>
                <el-table-column prop="departmentphone" label="部门电话"></el-table-column>
                <el-table-column prop="time" label="公示时间" sortable width='140'>
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
                <el-button type="primary" @click="clickAddOrUp">创建企业</el-button>
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
          width="800px"
          center>
          <div class="dialogMain">
            <el-form ref="addForm" style="width: 80%;margin: 0 auto" :model="addForm" label-width="140px">
                <el-form-item label="企业名称" required>
                    <el-input v-model="addForm.name" placeholder="请填写企业名称"></el-input>
                </el-form-item>
                <el-form-item label="统一社会信用代码" required>
                    <el-input v-model="addForm.code" placeholder="请填写统一社会信用代码"></el-input>
                </el-form-item>
                <el-form-item label="法定代表人" required>
                    <el-input v-model="addForm.username" placeholder="请填写法定代表人"></el-input>
                </el-form-item>
                <el-form-item label="企业类型" required>
                    <el-input v-model="addForm.type" placeholder="请填写企业类型"></el-input>
                </el-form-item>
                <el-form-item label="处置种类" required>
                    <el-input v-model="addForm.dealtype" placeholder="请填写处置种类"></el-input>
                </el-form-item>
                <el-form-item label="电话" required>
                    <el-input v-model="addForm.phone" placeholder="请填写电话"></el-input>
                </el-form-item>
                <el-form-item label="认证部门" required>
                    <el-input v-model="addForm.department" placeholder="请填写认证部门"></el-input>
                </el-form-item>
                <el-form-item label="部门电话" required>
                    <el-input v-model="addForm.departmentphone" placeholder="请填写部门电话"></el-input>
                </el-form-item>
                <el-form-item label="公示时间" required>
                    <el-date-picker
                        v-model="addForm.time"
                        type="date"
                        placeholder="选择日期"
                        format="yyyy 年 MM 月 dd 日"
                        value-format="timestamp">
                        </el-date-picker>
                </el-form-item>
                <el-form-item label="省市区" required>
                    <span>{{procityplat}}</span>
                </el-form-item>
                <el-form-item label="详细地址" required>
                    <span>{{addForm.address}}</span>
                </el-form-item>
                <div style="width: 100%;height:300px">
                  <serchMap @serchData="getSerchData"></serchMap>
                </div>
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
  import serchMap from '../../components/serchmap';
  export default {
    components:{
      serchMap: serchMap,
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
        
                // return year+"-"+month+"-"+day+" "+hour+":"+minutes;
                return year+"-"+month+"-"+day;
            },
    },
    data() {
      return {
        page: 1,
        pageSize: 10,
        totalNum: 100,
        //筛选
        form: {
          item: null,
        },
        //弹框title
        addOrUpTit:'',
        //新增与编辑form
        addForm:{
            name: null,
            username: null,
            phone:null,
            type: null,
            code: null,
            address: "未选择",
            department: null,
            departmentphone: null,
            dealtype: null,
            time: null,
            province: null,
            city: null,
            plat: null,
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
        allProvince:[],
        procityplat: "未选择-未选择-未选择",
      };
    },
    mounted() {
      this.getData();
      this.getAddressData();
    },
    methods: {
      //接受地址信息
      getSerchData(data){
        console.log(data);
        this.addForm.address = data.address;
        this.procityplat = data.addressComponents.province+"-"+data.addressComponents.city+'-'+data.addressComponents.district;
        this.addForm.province = data.addressComponents.province;
        this.addForm.city = data.addressComponents.city;
        this.addForm.plat = data.addressComponents.district;
      },
      //获取省份数据
      getAddressData(){
        var that = this;
        this.$ajax.get("../../../static/json/allprovince.json").then(function(response){
          console.log(response)
            that.allProvince = response.data;
        })
      },
      //获取区域信息
      getData(){
        var that = this;
        this.$ajax.get("/recovery/nterprise/selectByPage",{
            params:{
              page: this.page,
              rows: this.pageSize,
              name: this.form.item,
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
            username: null,
            phone:null,
            type: null,
            code: null,
            address: "未选择",
            department: null,
            departmentphone: null,
            dealtype: null,
            time: null,
            province: null,
            city: null,
            plat: null,
        } 
      },
      //点击新增/编辑
      clickAddOrUp(){
        this.centerDialogVisible = true;
        this.addOrUpTit='创建企业';
        this.clearForm();

      },
      //编辑
      handleEdit(index, row) {
        console.log(index, row);
        var that = this;
        this.centerDialogVisible = true;
        this.addOrUpTit = "编辑企业";
        that.addForm = {
            id: row.id,
            username: row.username,
            phone: row.phone,
            time: row.time,
            name: row.name,
            type: row.type,
            code: row.code,
            address: row.address,
            department: row.department,
            departmentphone: row.departmentphone,
            dealtype: row.dealtype,
            province: row.province,
            city: row.city,
            plat: row.plat,
        }
        this.procityplat = row.province+'-'+row.city+'-'+row.plat;
      },
      // 新增
      addSaveForm(){
        console.log(this.addForm)
        var that = this;
        if(this.addForm.username == null || this.addForm.phone == null){
          this.$message.error("*为必填项！");
          return;
        }
        var params = this.$qs.stringify(this.addForm, { indices: false })
        if(this.addOrUpTit == "创建企业"){
          this.$ajax.post("/recovery/nterprise/insert",params).then(function(response){
            if(response.data.status == 260){
              that.centerDialogVisible = false;
              that.$message.success("添加成功")
              that.getData();
              //清空form
              that.clearForm();
            }
          })
        }else{
          this.$ajax.put("/recovery/nterprise/update",params).then(function(response){
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
        this.$confirm('此操作将永久删除选中的省, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          if(this.multipleSelection.length == this.tableData.length){
            this.page = Number(this.page)-1;
          }
          var that = this;
          this.$ajax.delete("/recovery/nterprise/deletes",{data:this.multipleSelection}).then(function(response){
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
        this.$confirm('此操作将删除该省, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          console.log(index, row);
          if(this.tableData.length == 1){
            this.page = Number(this.page)-1;
          }
          var that = this;
          this.$ajax.delete("/recovery/nterprise/delete",{params:{
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
