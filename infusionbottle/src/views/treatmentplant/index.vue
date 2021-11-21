<template>
    <div id="yudingBox">
        <div class="selectBox">
          <el-form ref="form" :model="form" label-width="80px">
            <el-form-item label="企业名称">
                <el-input v-model="form.item" @change="selectList"></el-input>
            </el-form-item>
            <el-form-item label="法人">
                <el-input v-model="form.leadername" @change="selectList"></el-input>
            </el-form-item>
            <el-form-item label="批次">
                  <!-- <el-select v-model="form.batch" placeholder="请选择批次" @change="selectList">
                        <el-option v-for="(item,index) in batchList" :key="index"  :label="item.text" :value="item.value"></el-option>
                    </el-select> -->
                <el-input type="number" v-model="form.batch" min="1" @change="selectList"></el-input>
              </el-form-item>
            <el-form-item>
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
                <el-table-column prop="code" label="企业编码"></el-table-column>
                <el-table-column prop="name" label="企业名称"></el-table-column>
                <el-table-column prop="socialcode" label="统一社会信用代码"></el-table-column>
                <el-table-column prop="leadername" label="法人"></el-table-column>
                <el-table-column prop="leaderphone" label="联系电话"></el-table-column>
                <el-table-column prop="batch" label="批次" sortable>
                  <template slot-scope="scope">
                    <span>第{{scope.row.batch}}批</span>
                  </template>
                </el-table-column>
                <el-table-column prop="address" label="地址"></el-table-column>
                <el-table-column prop="" label="营业执照">
                    <template slot-scope="scope">
                        <img :src='scope.row.img' @click="lookImg(scope.row.img)" style="width: 90px;height:70px"/>
                    </template>
                </el-table-column>
                <el-table-column label="入库记录" width="80" v-if="userType == '回收利用企业'">
                  <template slot-scope="scope">
                    <el-button
                      size="mini"
                      @click="lookInDetail(scope.row.id)">查看</el-button>
                  </template>
                </el-table-column>
                <el-table-column label="出库记录" width="80" v-if="userType == '回收利用企业'">
                  <template slot-scope="scope">
                    <el-button
                      size="mini"
                      @click="lookOutDetail(scope.row.id)">查看</el-button>
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
                <el-button type="primary" @click="clickAddOrUp" v-if="userType == '系统'">添加回收利用企业</el-button>
                <el-button type="warning" @click="delAll" v-if="userType == '系统'">批量删除</el-button>
              </div>
              <el-pagination
                background
                layout="prev, pager, next"
                :page-size="pageSize"
                :current-page.sync="page"
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
            <el-form ref="addForm" :model="addForm" label-width="140px">
              <el-form-item label="企业编码" required>
                    <el-input v-model="addForm.code"></el-input>
                </el-form-item>
                <el-form-item label="企业名称" required>
                    <el-input v-model="addForm.name"></el-input>
                </el-form-item>
                <el-form-item label="法人" required>
                    <el-input v-model="addForm.leadername"></el-input>
                </el-form-item>
                <el-form-item label="联系电话" required>
                    <el-input v-model="addForm.leaderphone"></el-input>
                </el-form-item>
                <el-form-item label="统一社会信用代码" required width="150px">
                    <el-input v-model="addForm.socialcode"></el-input>
                </el-form-item>
                <el-form-item label="营业执照" required>
                    <aliUpFile :file="addForm.img" server='/recovery' directory='etreatmentplant' fileNum="1" @fileDel='fileDels' @upFileData="upFileDatas"></aliUpFile>
                </el-form-item>
                <el-form-item label="批次" required>
                  <!-- <el-select v-model="addForm.batch" placeholder="请选择批次">
                        <el-option v-for="(item,index) in batchList" :key="index" v-show="item.text !== '全部'"  :label="item.text" :value="item.value"></el-option>
                    </el-select> -->
                    <el-input-number v-model="addForm.batch" :min="1"></el-input-number>
                </el-form-item>
                <el-form-item label="省市区" required>
                    <span>{{procityplat}}</span>
                </el-form-item>
                <el-form-item label="详细地址" required>
                    <span>{{addForm.address}}</span>
                </el-form-item>
                <el-form-item>
                    <div style="width: 100%;height:300px">
                      <serchMap :pointlng="point.lng" :pointlat="point.lat" @serchData="getSerchData"></serchMap>
                    </div>
                </el-form-item>
                
            </el-form>
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
import serchMap from '../../components/serchmap';
  export default {
    components:{
      serchMap,
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
          leadername:'',
          batch: '',
        },
        //弹框title
        addOrUpTit:'',
        //新增与编辑form
        addForm:{
            id: null,
            item: null,
            leadername: null,
            leaderphone: null,
            img: '',
            address: "未选择",
            province: null,
            city: null,
            plat: null,
            code: null,
            socialcode: null,
            batch: 1,
            longitude: null,
            latitude: null,
        },
        
        point:{
          lng: null,
          lat: null
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
        imgLook:'',
        procityplat: "未选择-未选择-未选择",
        batchList: [],
      };
    },
    mounted() {
      this.getData();
      this.getSelectList();
    },
    methods: {
      //获取筛选列表
      getSelectList(){
        var _this = this;
        var obj = {"value":'',"text":"全部"};
        this.$ajax.get("/recovery/treatmentplant/batchList").then(function(response){
              console.log("批次列表",response);
              _this.batchList = response.data.list;  
              _this.batchList.unshift(obj);
        })
      },
      //接收地址信息
      getSerchData(data){
        console.log(data);
        this.addForm.address = data.address;
        this.procityplat = data.addressComponents.province+"-"+data.addressComponents.city+'-'+data.addressComponents.district;
        this.addForm.province = data.addressComponents.province;
        this.addForm.city = data.addressComponents.city;
        this.addForm.plat = data.addressComponents.district;
        this.addForm.longitude = data.point.lng;
        this.addForm.latitude = data.point.lat;
      },
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
            type: '回收利用企业'
          }
        })
      },
      //查看出库记录
      lookOutDetail(id){
        this.$router.replace({
          path:'/outDetail',
          query:{
            treatmentId: id,
          }
        })
      },
      lookInDetail(id){
        this.$router.replace({
          path:'/inDetail',
          query:{
            treatmentId: id,
          }
        })
      },
      //获取区域信息
      getData(){
        var that = this;
        this.$ajax.get("/recovery/treatmentplant/selectByPage",{
            params:{
              page: this.page,
              rows: this.pageSize,
              name: this.form.item,
              leadername: this.form.leadername,
              batch: this.form.batch,
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
              leadername:'',
              batch: null,
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
            img: '',
            address: "未选择",
            province: null,
            city: null,
            plat: null,
            code: null,
            socialcode: null,
            batch: 1,
            longitude: null,
            latitude: null,
        } 
      },
      //点击新增/编辑
      clickAddOrUp(){
        this.centerDialogVisible = true;
        this.addOrUpTit='创建回收利用企业';
        this.clearForm();

      },
      //编辑
      handleEdit(index, row) {
        console.log(index, row);
        var that = this;
        this.centerDialogVisible = true;
        this.addOrUpTit = "编辑回收利用企业";
        that.addForm = {
            id: row.id,
            name: row.name,
            leadername: row.leadername,
            leaderphone: row.leaderphone,
            address: row.address,
            img: row.img,
            province: row.province,
            city: row.city,
            plat: row.plat,
            socialcode: row.socialcode,
            code: row.code,
            batch: row.batch,
            longitude: row.longitude,
            latitude: row.latitude,
        }
        this.point.lng = row.longitude;
        this.point.lat = row.latitude;
        this.procityplat = row.province+'-'+row.city+'-'+row.plat;
      },
      // 新增
      addSaveForm(){
        console.log(this.addForm)
        var that = this;
        if(this.addForm.name == null || this.addForm.leadername == null || this.addForm.leaderphone == null || this.addForm.address == null||this.addForm.code == null || this.addForm.batch == null || this.addForm.socialcode == null){
          this.$message.error("*为必填项！");
          return;
        }
        if(this.addForm.img == ''){
          this.$message.error("营业执照为必填项！");
          return;
        }
        var params = this.$qs.stringify(this.addForm, { indices: false })
        if(this.addOrUpTit == "创建回收利用企业"){
          this.$ajax.post("/recovery/treatmentplant//insert ",params).then(function(response){
            if(response.data.status == 260){
              that.centerDialogVisible = false;
              that.$message.success("添加成功")
              that.getData();
              //清空form
              that.clearForm();
            }
          })
        }else{
          this.$ajax.put("/recovery/treatmentplant/update",params).then(function(response){
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
        this.$confirm('此操作将永久删除选中的回收利用企业, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          if(this.multipleSelection.length == this.tableData.length){
            this.page = Number(this.page)-1;
          }
          var that = this;
          this.$ajax.delete("/recovery/treatmentplant/deletes",{data:this.multipleSelection}).then(function(response){
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
        this.$confirm('此操作将删除该回收利用企业, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          console.log(index, row);
          if(this.tableData.length == 1){
            this.page = Number(this.page)-1;
          }
          var that = this;
          this.$ajax.delete("/recovery/treatmentplant/delete",{params:{
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
.mapBoxBtn{
  width: 302px;
}
.latLngMapBox{
  height: 600px;
}
.dialogMains{
  width:100%;
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
    width: 100px;
    padding-right: 10px;
    text-align: right;;
}
</style>
