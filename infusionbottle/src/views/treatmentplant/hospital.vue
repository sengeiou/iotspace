<template>
    <div id="yudingBox">
        <div class="selectBox">
          <el-form ref="form" :model="form" label-width="100px">
            <el-form-item label="医疗机构名称">
                <el-input v-model="form.item" @change="selectList"></el-input>
            </el-form-item>
            <el-form-item label="回收物流">
              <el-select v-model="form.transportcompanyid" placeholder="请选择回收物流" @change="selectList">
                    <el-option v-for="(item,index) in zancunList" :key="index"  :label="item.name" :value="item.id"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-button type="warning" size="small" @click="clearSelect" style="font-size:14px">清空筛选</el-button>
            </el-form-item>
          </el-form>
        </div>
        <div class="tableBox">
            <el-table :data="tableData" height="92%" style="width: 100%">
                <el-table-column prop="name" label="名称"></el-table-column>
                <el-table-column prop="supervisionunit.name" label="监管单位"></el-table-column>
                <el-table-column prop="belonglevel" label="归属级别"></el-table-column>
                <el-table-column prop="code" label="登记号"></el-table-column>
                <el-table-column prop="type" label="注册类型"></el-table-column>
                <el-table-column prop="level" label="等级"></el-table-column>
                <el-table-column prop="leadername" label="负责人"></el-table-column>
                <el-table-column prop="leaderphone" label="电话"></el-table-column>
                <el-table-column prop="address" label="地址"></el-table-column>
                <el-table-column prop="beds" label="床位数"></el-table-column>
                <el-table-column prop="" label="执业许可证">
                    <template slot-scope="scope">
                        <img :src='scope.row.img' @click="lookImg(scope.row.img)" style="width: 90px;height:70px"/>
                    </template>
                </el-table-column>
                <el-table-column prop="" label="操作" width="220" fixed="right">
                    <template slot-scope="scope"> 
                      <p>
                        <el-button
                          size="mini"
                          @click="lookJoinList(scope.row)">交接记录</el-button>
                        <el-button
                          size="mini"
                          @click="lookDepartment(scope.row.id)">查看科室</el-button>
                      </p>
                      <p style="margin-top:5px">
                        <el-button
                          size="mini"
                          @click="lookBarCode(scope.row.id)">打印条码</el-button>
                      </p>
                    </template>
                </el-table-column>
            </el-table>
            <div class="pafinationBox">
              <div>
                
              </div>
              <el-pagination
                background
                layout="prev, pager, next"
                :page-size="pageSize"
                :total="totalNum"
                :current-page.sync="currentPage"
                @current-change="changePage"
                >
              </el-pagination>
            </div>
        </div>
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
export default {
  components:{
        aliUpFile,
    },
    data() {
      return {
        userType: sessionStorage.getItem('userType'),
        page: 1,
        currentPage: 1,
        pageSize: 10,
        totalNum: 100,
        //筛选
        form: {
          item:'',
          leadername:'',
          type: '',
          level:'',
          code:'',
          phone: '',
          transportcompanyid:''
        },
        //类型筛选列表
        typeList:[],
        levelList:[],
        zancunList: [],

        //表格数据
        tableData: [],
        //查看大图
        lookImgIf: false,
        imgLook:''
      };
    },
    mounted() {
      this.getData();
      this.getSelectList();
    },
    methods: {

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
      //查看科室
      lookDepartment(id){
        this.$router.replace({
          path:'/Department',
          query:{
            hospitalId: id,
          }
        })
      },
      
      //打印条码
      lookBarCode(id){
        this.$router.replace({
          path:'/barCode',
          query:{
            hospitalId: id,
          }
        })
      },
      lookJoinList(row){
        this.$router.replace({
              path:'/treatmentPlantHospitalJoin',
              query:{
                  name: row.name,
                  id: row.id,
                  transportcompanyid: this.form.transportcompanyid,
                  flag: 1
              }
          })
      },
      //获取筛选列表
      getSelectList(){
        var _this = this;
        var obj = {"value":'',"text":"全部"};
        this.$ajax.get("/recovery/hospital/typeList").then(function(response){
              console.log("type列表",response);
              _this.typeList = response.data.list;
              _this.typeList.unshift(obj);
        })
        var objs = {"id":'',"name":"全部"};
        this.$ajax.get("/recovery/transportcompany/selectByTreatmentplantId",{
            params:{
                treatmentplantid: sessionStorage.getItem('belongId')
            }
        }).then(function(response){
              console.log("暂存点列表",response);
              _this.zancunList = response.data.list;
              _this.zancunList.unshift(objs);
        })
      },
      //获取jibie列表
      getLevelList(val,index){
        if(index == 1){
          this.getData();
          this.form.level = '';
        }else if(index == 2){
          this.addForm.level = null;
        }
        var _this = this;
        var obj = {"value":'',"text":"全部"};
        this.$ajax.get("/recovery/hospital/levelList",{
          params: {
            type: val
          }
        }).then(function(response){
              console.log("ji别列表",response);
              _this.levelList = response.data.list;
              _this.levelList.unshift(obj);
        })
      },
      //获取区域信息
      getData(){
        var that = this;
        this.$ajax.get("/recovery/hospital/selectByTreatmentplantId",{
            params:{
              page: this.page,
              rows: this.pageSize,
              name: this.form.item,
              type: this.form.type,
              level: this.form.level,
              treatmentplantid: sessionStorage.getItem('belongId'),
              transportcompanyid: this.form.transportcompanyid,
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
              transportcompanyid:'',
              type: '',
              level:'',
            }
            this.getData();
      },
    },
    // beforeRouteEnter(to, from, next) {
    //   next( vm =>{
    //   console.log(123123123123,from)
    //     if (from.path == "/treatmentPlantHospitalJoin" || from.path == "/Department" || from.path == '/barCode') {
    //       next();
    //     }else {
    //       vm.page = 1;
    //       vm.currentPage = 1;
    //       vm.getData();
    //       vm.getSelectList();
    //       next();
    //     }
    //   })
    // },
  }
</script>

<style scoped>
#yudingBox {
    width: 100%;
    margin-top: 20px;
    height: 85vh;
    background: #F5F7F9;
}
.selectBox{
  width: 95%;
  margin: auto;
}
.tableBox{
  width: 95%;
  height: 95%;
  margin: auto;
}
.pafinationBox{
  width: 100%;
  height: 50px;
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
