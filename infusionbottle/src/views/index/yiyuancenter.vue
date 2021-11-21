<template>
    <div id="yudingBox">
        <div class="selectBox">
          <el-form ref="form" label-width="60px">
            
          </el-form>
        </div>
        <div class="tableBox">
            <div class="newBox">
              <div>
                <span>监管单位：</span>
                <span>{{supervisionunitName}}</span>
              </div>
              <div>
                <span>登记号：</span>
                <span>{{tableData.code}}</span>
              </div>
              <div>
                <span>医疗机构名称：</span>
                <span>{{tableData.name}}</span>
              </div>
              <div>
                <span>医疗机构地址：</span>
                <span>{{tableData.address}}</span>
              </div>
              <div>
                <span>注册类型：</span>
                <span>{{tableData.type}}</span>
              </div>
              <div>
                <span>等级：</span>
                <span>{{tableData.level}}</span>
              </div>
              <div>
                <span>负责人：</span>
                <span>{{tableData.leadername}}</span>
              </div>
              <div>
                <span>负责人电话：</span>
                <span>{{tableData.leaderphone}}</span>
              </div>
              <div>
                <span>床位数：</span>
                <span>{{tableData.beds}}</span>
              </div>
              <div>
                <span>入驻系统时间：</span>
                <span>{{tableData.time | formatDate}}</span>
              </div>
              <div>
                <span>输液瓶（袋）回收企业：</span>
                <span v-if="tableData.transportcompany.treatmentplant==null">{{tableData.transportcompany.name}}</span>
                <span v-else>{{tableData.transportcompany.treatmentplant.name}}</span>
              </div>
              <div>
                <span>执业许可证：</span>
                <img :src='tableData.img' @click="lookImg(tableData.img)" style="width: 90px;height:70px"/>
              </div>
            </div>
            <div class="pafinationBox">
              <div>
                <el-button type="primary" @click="handleEdit">编辑</el-button>
                <el-button type="warning" @click="changeQiYe">更换合作企业</el-button>
              </div>
            </div>
        </div>
        <el-dialog
          :title="addOrUpTit"
          :visible.sync="centerDialogVisible"
          width="600px"
          center>
          <div class="dialogMain">
            <el-form ref="addForm" :model="addForm" label-width="110px">
                <el-form-item label="登记号" placeholder="请填写登记号" required>
                    <el-input v-model="addForm.code"></el-input>
                </el-form-item>
                <el-form-item label="医疗机构名称" placeholder="请填写医疗机构名称" required>
                    <el-input v-model="addForm.name"></el-input>
                </el-form-item>
                <el-form-item label="注册类型" required>
                  <el-select v-model="addForm.type" placeholder="请选择类型" @change="getLevelList(addForm.type,2)">
                        <el-option v-for="(item,index) in typeList" :key="index" v-show="item.text != '全部'" :label="item.text" :value="item.value"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="等级" v-show="addForm.type != null" required>
                  <el-select v-model="addForm.level" placeholder="请选择等级">
                        <el-option v-for="(item,index) in levelList" :key="index" v-show="item.text != '全部'" :label="item.text" :value="item.value"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="负责人" placeholder="请填写负责人" required>
                    <el-input v-model="addForm.leadername"></el-input>
                </el-form-item>
                <el-form-item label="负责人电话" placeholder="请填写电话">
                    <el-input v-model="addForm.leaderphone"></el-input>
                </el-form-item>
                <el-form-item label="地址" required>
                    <el-input v-model="addForm.address" placeholder="请填写地址"></el-input>
                </el-form-item>
                <el-form-item label="床位数" required>
                  <el-input-number v-model="addForm.beds"></el-input-number>
                </el-form-item>
            </el-form>
            <div class="iptBox">
                <span>执业许可证</span>
                <div>
                    <aliUpFile :file="addForm.img" server='/recovery' directory='ehospital' fileNum="1" @fileDel='fileDels' @upFileData="upFileDatas"></aliUpFile>
                </div>
            </div>
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
        <!-- 更换企业 -->
        <el-dialog
          title="更换合作企业"
          :visible.sync="changeQiYeIf"
          width="600px"
          center>
          <div class="dialogMain">
             <el-form label-width="100px">
              <el-form-item label="回收物流">
                  <el-autocomplete
                  class="inline-input"
                  style="width: 193px"
                  v-model="transportcompany"
                  :fetch-suggestions="querySearch"
                  placeholder="请输入回收物流名称"
                  @select="handleSelects"
                  ></el-autocomplete>
              </el-form-item>
             </el-form>
          </div>
          <span slot="footer" class="dialog-footer">
            <el-button @click="changeQiYeIf = false">取 消</el-button>
            <el-button type="primary" @click="addSaveForm()">确 定</el-button>
          </span>
        </el-dialog>
    </div>
</template>
<script>
import aliUpFile from "../../components/aliuploadrenyuan"
export default {
  components:{
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
        
                // return year+"-"+month+"-"+day+" "+hour+":"+minutes;
                return year+"-"+month+"-"+day;
            },
    },
    data() {
      return {
        changeQiYeIf: false,
        centerDialogVisible:false,
        lookImgIf:false,
        imgLook:'',
        supervisionunitName: sessionStorage.getItem('supervisionunitName'),
        //弹框title
        addOrUpTit:'',
        //新增与编辑form
        addForm:{
            address: null,
            beds: null,
            belonglevel: null,
            code: null,
            id: null,
            img: null,
            leadername: null,
            leaderphone: null,
            level: null,
            name: null,
            note: null,
            supervisionunitid: null,
            transportcompanyid: null,
            transportcompany: null,
            type: null,
        },
        //表格数据
        tableData: {
          address: null,
          beds: null,
          belonglevel: null,
          code: null,
          id: null,
          img: null,
          leadername: null,
          leaderphone: null,
          level: null,
          name: null,
          note: null,
          supervisionunitid: null,
          transportcompanyid: null,
          type: null,
        },
        typeList:[],
        supervisionunitList:[],
        levelList:[],
        transportcompanyAllList:[],
        transportcompany: null,
      };
    },
    mounted() {
      this.getData();
      this.getSelectList();
    },
    methods: {
      //查看大图
      lookImg(src){
        this.imgLook = src;
        this.lookImgIf = true;
      },
      //接收图片
      upFileDatas(data){
          this.addForm.img = data;
      },
      //删除图片
      fileDels(data){
          this.addForm.img = '';
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
        // var objs = {"id":'',"name":"全部"};
        this.$ajax.get("/recovery/supervisionunit/selectAll").then(function(response){
              console.log("type列表",response);
              _this.supervisionunitList = response.data.list;
              // _this.supervisionunitList.unshift(objs);
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
        this.$ajax.get("/recovery/hospital/selectVByPrimaryKey",{
            params:{
              id: sessionStorage.getItem('belongId')
            }
        }).then(function(response){
          console.log(response)
          if(response.data.status == 260){
            that.tableData = response.data.object;
          }else{
            that.tableData = [];
          }
        })
      },
      //清空创建编辑form
      clearForm(){
        this.addForm = {
            address: null,
            beds: null,
            belonglevel: null,
            code: null,
            id: null,
            img: null,
            leadername: null,
            leaderphone: null,
            level: null,
            name: null,
            note: null,
            supervisionunitid: null,
            transportcompanyid: null,
            transportcompany: null,
        } 
      },
      //编辑
      handleEdit(index, row) {
        console.log(index, row);
        var that = this;
        this.centerDialogVisible = true;
        this.addOrUpTit = "编辑";
        that.addForm = that.tableData;
      },
      // 编辑确认
      addSaveForm(){
        console.log(this.addForm)
        var that = this;
        var params = this.$qs.stringify(this.addForm, { indices: false })
          this.$ajax.put("/recovery/hospital/update",params).then(function(response){
            if(response.data.status == 260){
              that.centerDialogVisible = false;
              that.$message.success("编辑成功")
              that.getData();
              //清空form
              that.clearForm();
            }
          })
      },
      //更换合作企业
      changeQiYe(){
        console.log(this.addForm)
        var that = this;
        this.changeQiYeIf = true;
        this.$ajax.get("/recovery/transportcompany/selectAll").then(function(response){
          console.log("回收物流",response)
          for(let i=0;i<response.data.list.length;i++){
              that.transportcompanyAllList.push({'value':response.data.list[i].name,'id':response.data.list[i].id})
          }
        })
      },
      handleSelects(item) {
          console.log(item);
          this.addForm = this.tableData;
          this.addForm.transportcompanyid = item.id;
          this.transportcompany = item.value;
      },
      querySearch(queryString, cb) {
          var restaurants = this.transportcompanyAllList;
          var results = queryString ? restaurants.filter(this.createFilter(queryString)) : restaurants;
          // 调用 callback 返回建议列表的数据
          cb(results);
      },
      createFilter(queryString) {
          return (restaurant) => {
          return (restaurant.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0);
          };
      },
      // 编辑确认
      changeSaveForm(){
        console.log(this.addForm)
        var that = this;
        var params = this.$qs.stringify(this.addForm, { indices: false })
          this.$ajax.put("/recovery/hospital/update",params).then(function(response){
            if(response.data.status == 260){
              that.changeQiYeIf = false;
              that.$message.success("编辑成功")
              that.getData();
              //清空form
              that.clearForm();
            }
          })
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
  margin: auto;
}
.tableBox{
  width: 95%;
  margin: auto;
}
.newBox{
  width: 98%;
  box-sizing: border-box;
  padding: 0 1%;
  background: #fff;
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  justify-content: flex-start;
}
.newBox > div{
  width: 33%;
  padding: 15px 0;
  display: flex;
  flex-wrap: wrap;
  align-items: center;
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
