<template>
    <div id="yudingBox">
        <div class="selectBox">
          <el-form ref="form" :model="form" label-width="60px">
            <el-form-item label="区名称">
                <el-input v-model="form.item" placeholder="请输入区名称" @change="selectList"></el-input>
            </el-form-item>
            <el-form-item label="所属区">
              <el-select style="width: 100px" v-model="form.provinceid" placeholder="请选择省" @change="getCitys(form.provinceid,1)">
                  <el-option v-for="(item,index) in provinceList" :key="index"  :label="item.item" :value="item.id"></el-option>
              </el-select>
              <el-select style="width: 100px" v-if="form.provinceid!=''" v-model="form.cityid" placeholder="请选择区" @change="selectList">
                <el-option v-for="(item,index) in cityList" :label="item.item" :value="item.id" :key="index"></el-option>
              </el-select>
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
                <el-table-column prop="item" label="区名称"></el-table-column>
                <el-table-column prop="city.item" label="所属区"></el-table-column>
                <el-table-column prop="longitude" label="中心经度"></el-table-column>
                <el-table-column prop="latitude" label="中心纬度"></el-table-column>
                <el-table-column prop="note" label="备注"></el-table-column>
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
                <el-button type="primary" @click="clickAddOrUp">添加区</el-button>
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
                <el-form-item label="区名称" required>
                    <el-input v-model="addForm.item"></el-input>
                </el-form-item>
                <el-form-item label="所属区">
                    <el-select style="width: 100px" v-model="addForm.province" placeholder="请选择省" @change="getProvinceId(addForm.province)">
                        <el-option v-for="(item,index) in provinceList" :key="index" v-show="item.item != '全部'"  :label="item.item" :value="item.item"></el-option>
                    </el-select>
                    <el-select style="width: 100px" v-if="addForm.provinceid!=''" v-model="addForm.cityid" placeholder="请选择区" @change="selectList">
                        <el-option v-for="(item,index) in cityList" v-show="item.item != '全部'"  :label="item.item" :value="item.id" :key="index"></el-option>
                    </el-select>
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
        //筛选
        form: {
          item:'',
          provinceid:'',
          cityid: '',
        },
        //弹框title
        addOrUpTit:'',
        //新增与编辑form
        addForm:{
            id: null,
            item: null,
            provinceid: null,
            cityid: null,
            longitude: null,
            latitude:null,
            note: null,
        },
        //表格数据
        tableData: [],
        provinceList:[],
        cityList:[],
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
      this.getSelectList();
    },
    methods: {
      //获取筛选列表
      getSelectList(){
        var _this = this;
        var objs = {"id":'',"item":"全部"};
        this.$ajax.get("/recovery/province/selectAll").then(function(response){
              console.log("qu列表",response);
              _this.provinceList = response.data.list;
              _this.provinceList.unshift(objs);
        })
      },
      getProvinceId(val){
            var obj = {};
            obj = this.provinceList.find(function(item) {//obj是选中的对象
                return item.item === val;
            })
            this.addForm.cityid = null;
            this.getCitys(obj.id)
      },
      getCitys(id,index){
          if(index == 1){
              this.form.cityid = '';
          }
        var _this = this;
        var objs = {"id":'',"item":"全部"};
        this.$ajax.get("/recovery/city/selectByProvinceId",{
            params:{
                provinceid: id
            }
        }).then(function(response){
              console.log("qu列表",response);
              _this.cityList = response.data.list;
              _this.cityList.unshift(objs);
        })
      },
      //获取区域信息
      getData(){
        var that = this;
        this.$ajax.get("/recovery/plat/selectVByPage",{
            params:{
              page: this.page,
              rows: this.pageSize,
              item: this.form.item,
              cityid: this.form.cityid,
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
                item:'',
                provinceid: '',
                cityid:'',
            }
            this.getData();
      },
      //清空创建编辑form
      clearForm(){
        this.addForm = {
            id: null,
            item: null,
            province: null,
            cityid: null,
            longitude: null,
            latitude:null,
            note: null,
        } 
      },
      //点击新增/编辑
      clickAddOrUp(){
        this.centerDialogVisible = true;
        this.addOrUpTit='创建区';
        this.clearForm();

      },
      //编辑
      handleEdit(index, row) {
        console.log(index, row);
        var that = this;
        this.centerDialogVisible = true;
        this.addOrUpTit = "编辑区";
        that.addForm = {
            id: row.id,
            item: row.item,
            province: row.city.province.item,
            cityid: row.cityid,
            longitude: row.longitude,
            latitude: row.latitude,
        }
        this.getCitys(row.city.provinceid);
      },
      // 新增
      addSaveForm(){
        console.log(this.addForm)
        var that = this;
        if(this.addForm.cityid == null || this.addForm.item == null){
          this.$message.error("*为必填项！");
          return;
        }
        var obj = {};
        obj = this.cityList.find(function(item) {//obj是选中的对象
            return item.id === that.addForm.cityid;
        })
        // 创建地址解析器实例
        var myGeo = new BMap.Geocoder();
        var address = this.addForm.province+obj.item+this.addForm.item
        console.log(address)
        // 将地址解析结果显示在地图上,并调整地图视野
        myGeo.getPoint(address, function(point){
          if (point) {
            that.savePlat(point.lng,point.lat)
          }else{
            alert("您选择地址没有解析到经纬度!");
          }
        }, "全国");
      },
      savePlat(lng,lat){
        var that = this;
        this.addForm.latitude = lat;
        this.addForm.longitude = lng;
        var params = this.$qs.stringify(this.addForm, { indices: false })
        if(this.addOrUpTit == "创建区"){
          this.$ajax.post("/recovery/plat//insert ",params).then(function(response){
            if(response.data.status == 260){
              that.centerDialogVisible = false;
              that.$message.success("添加成功")
              that.getData();
              //清空form
              that.clearForm();
            }
          })
        }else{
          this.$ajax.put("/recovery/plat/update",params).then(function(response){
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
        this.$confirm('此操作将永久删除选中的区, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          if(this.multipleSelection.length == this.tableData.length){
            this.page = Number(this.page)-1;
          }
          var that = this;
          this.$ajax.delete("/recovery/plat/deletes",{data:this.multipleSelection}).then(function(response){
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
        this.$confirm('此操作将删除该区, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          console.log(index, row);
          if(this.tableData.length == 1){
            this.page = Number(this.page)-1;
          }
          var that = this;
          this.$ajax.delete("/recovery/plat/delete",{params:{
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
