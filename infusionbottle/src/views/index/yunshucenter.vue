<template>
    <div id="yudingBox">
        <div class="selectBox">
          <el-form ref="form" label-width="60px">
            
          </el-form>
        </div>
        <div class="tableBox">
            <div class="newBox">
              <div>
                <span>回收物流名称：</span>
                <span>{{tableData.name}}</span>
              </div>
              <div>
                <span>回收物流地址：</span>
                <span>{{tableData.address}}</span>
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
                <span>营业许可证：</span>
                <img :src='tableData.img' @click="lookImg(tableData.img)" style="width: 90px;height:70px"/>
              </div>
            </div>
            <div class="pafinationBox">
              <div>
                <el-button type="primary" @click="handleEdit">编辑</el-button>
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
                <el-form-item label="回收物流名称" placeholder="请填写名称" required>
                    <el-input v-model="addForm.name"></el-input>
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
            </el-form>
            <div class="iptBox">
                <span>营业执照</span>
                <div>
                    <aliUpFile :file="addForm.img" server='/recovery' directory='etransportcompany' fileNum="1" @fileDel='fileDels' @upFileData="upFileDatas"></aliUpFile>
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
        
                return year+"-"+month+"-"+day+" "+hour+":"+minutes;
            },
    },
    data() {
      return {
        centerDialogVisible:false,
        lookImgIf:false,
        imgLook:'',
        //弹框title
        addOrUpTit:'',
        //新增与编辑form
        addForm:{
            address: null,
            id: null,
            img: null,
            leadername: null,
            leaderphone: null,
            name: null,
            note: null,
        },
        //表格数据
        tableData: {
         address: null,
            id: null,
            img: null,
            leadername: null,
            leaderphone: null,
            name: null,
            note: null,
        },
        typeList:[],
        supervisionunitList:[],
        levelList:[],
      };
    },
    mounted() {
      this.getData();
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
      //获取区域信息
      getData(){
        var that = this;
        this.$ajax.get("/recovery/transportcompany/selectByPrimaryKey",{
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
            id: null,
            img: null,
            leadername: null,
            leaderphone: null,
            name: null,
            note: null,
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
          this.$ajax.put("/recovery/transportcompany/update",params).then(function(response){
            if(response.data.status == 260){
              that.centerDialogVisible = false;
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
    width: 100px;
    padding-right: 10px;
    text-align: right;;
}
</style>
