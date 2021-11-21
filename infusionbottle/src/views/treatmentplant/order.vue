<template>
    <div id="orderBox">
        <div class="selectBox">
          <el-form ref="form" :model="form" label-width="70px">
            <el-form-item label="销售类型">
                <el-select v-model="form.type" placeholder="请选择销售类型" @change="selectCategoryList(form.type,1)">
                    <el-option v-for="(item,index) in xiaoshouTypeList" :key="index"  :label="item.text" :value="item.value"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="类别" v-if="form.type != null">
                <el-select v-model="form.categoryid" placeholder="请选择类别" @change="selectList">
                    <el-option v-for="(item,index) in TypeList" :key="index"  :label="item.name" :value="item.id"></el-option>
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
                <el-table-column prop="time" label="时间" sortable>
                  <template slot-scope="scope">
                    {{scope.row.time | formatDate}}
                  </template>
                </el-table-column>
                <!-- <el-table-column prop="no" label="订单编号"></el-table-column> -->
                <el-table-column prop="name" label="销售客户"></el-table-column>
                <el-table-column prop="type" label="销售类型"></el-table-column>
                <el-table-column prop="" label="类别">
                  <template  slot-scope="scope">
                    <el-button type="text" @click="lookCategory(scope.row)">查看</el-button>
                  </template>
                </el-table-column>
                <el-table-column prop="number" label="数量(袋)"></el-table-column>
                <el-table-column prop="weight" label="重量(kg)"></el-table-column>
                <el-table-column prop="address" label="销售地址"></el-table-column>
                <el-table-column prop="car" label="运输车辆"></el-table-column>
                <el-table-column prop="phone" label="联系电话"></el-table-column>
                <el-table-column prop="unitprice" label="单价"></el-table-column>
                <el-table-column prop="totalprice" label="总价"></el-table-column>
                <el-table-column prop="username" label="出库人"></el-table-column>
                <el-table-column prop="" label="发票" width="80">
                  <template slot-scope="scope">
                    <el-link type="primary" :href="scope.row.invoice" :disabled="scope.row.invoice == null">下载发票</el-link>
                  </template>
                </el-table-column>
                <el-table-column prop="" label="购销合同" width="80">
                  <template slot-scope="scope">
                    <el-link type="primary" :href="scope.row.contract" :disabled="scope.row.contract == null">下载附件</el-link>
                  </template>
                </el-table-column>
                <el-table-column prop="note" label="备注"></el-table-column>
                <el-table-column label="操作" fixed="right" width="150">
                  <template slot-scope="scope">
                    <el-button
                      size="mini"
                      @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                    <!-- <el-button
                      size="mini"
                      type="danger"
                      @click="handleDelete(scope.$index, scope.row.id)">删除</el-button> -->
                  </template>
                </el-table-column>
            </el-table>
            <div class="pafinationBox">
              <div>
                  <el-button type="primary" @click="clickAddOrUp">新增订单</el-button>
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
        <el-dialog
          :title="addOrUpTit"
          :visible.sync="centerDialogVisible"
          width="50%"
          top="5vh"
          center>
          <div class="dialogMain">
            <el-form ref="addForm" :model="addForm" label-width="100px">
                <!-- <el-form-item label="订单编号">
                    <el-input v-model="addForm.no" placeholder="请输入订单编号"></el-input>
                </el-form-item> -->
                <el-form-item label="销售客户" required>
                    <el-input v-model="addForm.name" placeholder="请输入销售客户"></el-input>
                </el-form-item>
                <el-form-item label="销售类型" required>
                    <el-select v-model="addForm.type" placeholder="请选择销售类型" @change="selectCategoryList(addForm.type,2)">
                        <el-option v-for="(item,index) in xiaoshouTypeList" :key="index" v-show="item.text != '全部'"  :label="item.text" :value="item.value"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="类别" required>
                    <el-select v-model="addForm.categoryid" placeholder="请选择类别" @change="getWeight">
                        <el-option v-for="(item,index) in TypeAddList" :key="index" v-show="item.name != '全部'"  :label="item.name" :value="item.id"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="重量" required>
                    <el-input v-model="addForm.weight" :placeholder="weightText"></el-input>
                </el-form-item>
                <el-form-item label="袋数" required>
                    <el-input v-model="addForm.number" placeholder="请输入袋数"></el-input>
                </el-form-item>
                <el-form-item label="销售地址" required>
                    <el-input v-model="addForm.address" placeholder="请输入销售地址"></el-input>
                </el-form-item>
                <el-form-item label="运输车辆" required>
                    <el-input v-model="addForm.car" placeholder="请输入运输车辆"></el-input>
                </el-form-item>
                <el-form-item label="联系电话" required>
                    <el-input v-model="addForm.phone" placeholder="请输入联系电话"></el-input>
                </el-form-item>
                <el-form-item label="单价" required>
                    <el-input v-model="addForm.unitprice" placeholder="请输入单价"></el-input>
                </el-form-item>
                <el-form-item label="总价" required>
                    <el-input v-model="addForm.totalprice" placeholder="请输入总价"></el-input>
                </el-form-item>
                <el-form-item label="出库人" required>
                    <el-input v-model="addForm.username" placeholder="请输入出库人"></el-input>
                </el-form-item>
                <el-form-item label="备注">
                    <el-input v-model="addForm.note" placeholder="请输入备注"></el-input>
                </el-form-item>
                <el-form-item label="购销合同">
                    <fileupload directory="order" :file="addForm.contract" @delFile="delFile" @fileUrl="getFileUrl"></fileupload>
                </el-form-item>
                <el-form-item label="发票">
                    <fileupload directory="order" :file="addForm.invoice" @delFile="delFiles" @fileUrl="getFileUrls"></fileupload>
                </el-form-item>
            </el-form>
          </div>
          <span slot="footer" class="dialog-footer">
            <el-button @click="centerDialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="addSaveForm()">确 定</el-button>
          </span>
        </el-dialog>
        <!-- //查看类别 -->
        <el-dialog
        title="类别"
        :visible.sync="categoryVisible"
        width="200px">
        <span>{{categoryText}}</span>
      </el-dialog>
    </div>
</template>
<script>
import fileupload from "../../components/fileupload.vue"
  export default {
    components:{
        fileupload
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
        categoryVisible: false,
        categoryText: '',
        categoryFlag: null,
        form:{
          type:null,
          starttime: '',
          endtime: '',
          categoryid: null,
        },
        //弹框title
        addOrUpTit:'',
        //新增与编辑form
        addForm:{
            id: null,
            treatmentplantid: sessionStorage.getItem('belongId'),
            name: null,
            type: null,
            number: null,
            unitprice: null,
            address: null,
            contract: null,
            no: null,
            categoryid: null,
            weight: null,
            totalprice: null,
            car: null,
            phone: null,
            invoice: null,
            note: null,
            treatmentplantid: null,
            username: null,
        },
        //表格数据
        tableData: [],
        tableTypes: [],
        sourceList: [],
        xiaoshouTypeList: [],
        TypeList:[],
        TypeAddList:[],
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
      //查看类别
      lookCategory(row){
        var that = this;
        this.$ajax.get("/recovery/order/selectByCategory",{
          params:{
            type: row.type,
            categoryid: row.categoryid
          }
        }).then(function(response){
              console.log("类别",response);
              if(response.data.status == 260){
                that.categoryText = response.data.object.name;
                that.categoryVisible = true;
              }
        })
      },
      //销售合同附件
      getFileUrl(data){
        console.log("销售合同附件",data);
        this.addForm.contract = data;
      },
      delFile(data){
        this.addForm.contract = null;
      },
      //发票附件
      getFileUrls(data){
        console.log("发票附件",data);
        this.addForm.invoice = data;
      },
      delFiles(data){
        this.addForm.invoice = null;
      },
      //获取类别
      selectCategoryList(str,index){
        console.log(str,index)
        var _this = this;
        var obj = {"id":'',"name":"全部"};
        var url;
        if(index == 1){
          this.form.categoryid = null;
        }else if(index == 2){
          this.addForm.categoryid = null;
        }
        //记录类别标识
        this.categoryFlag = str;
        if(str == "破碎料"){
          url = "/recovery/cmaterialcategory/selectByTreatmentplantid"
        }else if(str == '颗粒料'){
          url = "/recovery/gmaterialcategory/selectByTreatmentplantid"
        }
        this.$ajax.get(url,{
          params:{
            treatmentplantid: sessionStorage.getItem('belongId'),
          }
        }).then(function(response){
              console.log("类别",response);
              if(index == 1){
                _this.TypeList = response.data.list;
                _this.TypeList.unshift(obj);
              }else if(index == 2){
                _this.TypeAddList = response.data.list;
                _this.TypeAddList.unshift(obj);
              }
        })
      },
      //获取库存重量
      getWeight(){
        var that = this;
        var url;
        if(this.categoryFlag == "破碎料"){
          url = "/recovery/cmaterialcategory/selectByPrimaryKey"
        }else if(this.categoryFlag == '颗粒料'){
          url = "/recovery/gmaterialcategory/selectByPrimaryKey"
        }
         //颗粒料类别
        this.$ajax.get(url,{
          params:{
            id: that.addForm.categoryid,
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
      getSelectList(){
        var _this = this;
        var objs = {"value":'',"text":"全部"};
        this.$ajax.get("/recovery/order/typeList").then(function(response){
              console.log("销售类别",response);
              _this.xiaoshouTypeList = response.data.list;
              _this.xiaoshouTypeList.unshift(objs);
        })
      },
      //获取订单信息
      getData(){
        var that = this;
        if(this.form.starttime == null){
          this.form.starttime = "";
        }
        if(this.form.endtime == null){
          this.form.endtime = "";
        }
        this.$ajax.get("/recovery/order/selectByPage",{
            params:{
              page: this.page,
              rows: this.pageSize,
              treatmentplantid: sessionStorage.getItem('belongId'),
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
            treatmentplantid: sessionStorage.getItem('belongId'),
            name: null,
            type: null,
            number: null,
            unitprice: null,
            address: null,
            contract: null,
            no: null,
            categoryid: null,
            weight: null,
            totalprice: null,
            car: null,
            phone: null,
            invoice: null,
            note: null,
            username: null,
        } 
      },
      //点击新增/编辑
      clickAddOrUp(){
        this.centerDialogVisible = true;
        this.addOrUpTit='新增订单';
        this.clearForm();
      },
      //编辑
      handleEdit(index, row) {
        console.log(index, row);
        var that = this;
        this.centerDialogVisible = true;
        this.addOrUpTit = "编辑订单";
        that.addForm = {
            id: row.id,
            treatmentplantid: row.treatmentplantid,
            name: row.name,
            type: row.type,
            number: row.number,
            unitprice: row.unitprice,
            address: row.address,
            contract: row.contract,
            no: row.no,
            categoryid: row.categoryid,
            weight: row.weight,
            totalprice: row.totalprice,
            car: row.car,
            phone: row.phone,
            invoice: row.invoice,
            note: row.note,
            username: row.username,
        } 
      },
      // 新增
      addSaveForm(index){
        console.log(this.addForm)
        if(this.addForm.treatmentplantid == null || this.addForm.name == null || this.addForm.type == null 
        || this.addForm.number == null || this.addForm.unitprice == null || this.addForm.address == null || 
        this.addForm.categoryid == null || this.addForm.weight == null || this.addForm.totalprice == null ||
        this.addForm.car == null || this.addForm.phone == null || this.addForm.username == null){
          this.$message.error("*为必填项！");
          return;
        }
        if(Number(this.addForm.weight) > Number(this.weightNumber)){
          this.$message.error("销售重量库存不足！");
          return;
        }
        var that = this;
        var params = this.$qs.stringify(this.addForm, { indices: false })
        if(this.addOrUpTit == "新增订单"){
          this.$ajax.post("/recovery/order/insert",params).then(function(response){
            if(response.data.status == 260){
              that.centerDialogVisible = false;
              that.$message.success("添加成功")
              that.getData();
              //清空form
              that.clearForm();
            }
          })
        }else{
          this.$ajax.put("/recovery/order/update",params).then(function(response){
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
        this.$confirm('此操作将永久删除选中的订单, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          if(this.multipleSelection.length == this.tableData.length){
            this.page = Number(this.page)-1;
          }
          var that = this;
          this.$ajax.delete("/recovery/order/deletes",{data:this.multipleSelection}).then(function(response){
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
        this.$confirm('此操作将删除该订单, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          console.log(index, row);
          if(this.tableData.length == 1){
            this.page = Number(this.page)-1;
          }
          var that = this;
          this.$ajax.delete("/recovery/order/delete",{params:{
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
#orderBox {
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
