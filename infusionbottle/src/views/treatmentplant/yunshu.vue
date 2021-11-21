<template>
    <div id="yudingBox">
        <div class="selectBox">
          <el-form ref="form" label-width="60px">
            
          </el-form>
        </div>
        <div class="tableBox">
            <el-table :data="tableData" style="width: 100%">
                <el-table-column prop="" label="营业执照">
                    <template slot-scope="scope">
                        <img :src='scope.row.img' @click="lookImg(scope.row.img)" style="width: 90px;height:70px"/>
                    </template>
                </el-table-column>
                <el-table-column prop="name" label="名称"></el-table-column>
                <el-table-column prop="address" label="地址"></el-table-column>
                <el-table-column prop="leadername" label="负责人"></el-table-column>
                <el-table-column prop="leaderphone" label="负责人电话"></el-table-column>
                <el-table-column prop="note" label="备注"></el-table-column>
                <el-table-column label="操作" width="100">
                  <template slot-scope="scope">
                    <el-button
                      size="mini"
                      @click="lookCar(scope.row)">查看车辆</el-button>
                  </template>
                </el-table-column>
            </el-table>
        </div>
    </div>
</template>
<script>
  export default {
    data() {
      return {
        //表格数据
        tableData: [],
        statusList:[],
      };
    },
    mounted() {
        this.getData();
    },
    methods: {
      //获取区域信息
      getData(){
        var that = this;
        this.$ajax.get("/recovery/transportcompany/selectByTreatmentplantId",{
            params:{
              treatmentplantid : sessionStorage.getItem('belongId'),
            }
        }).then(function(response){
          console.log('123',response)
          if(response.data.status == 260){
            that.tableData = response.data.list;
          }else{
            that.tableData = [];
          }
        })
      },
      lookCar(row){
          this.$router.replace({
              path:'/yunShuCarList',
              query:{
                  name: row.name,
                  id: row.id
              }
          })
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
