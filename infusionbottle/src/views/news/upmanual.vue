<template>
    <div id="yudingBox">
        <div class="selectBox">
          
        </div>
        <div class="tableBox">
            <el-table :data="tableData" style="width: 100%">
                <el-table-column prop="time" label="创建时间" sortable>
                  <template slot-scope="scope">
                    {{scope.row.time | formatDate}}
                  </template>
                </el-table-column>
                <el-table-column prop="item" label="描述"></el-table-column>
                <el-table-column prop="type" label="类型"></el-table-column>
                <el-table-column prop="note" label="备注"></el-table-column>
                <el-table-column label="附件" width="100">
                  <template slot-scope="scope">
                    <el-link type="primary" :href="scope.row.file" v-if="scope.row.file != null">下载附件</el-link>
                  </template>
                </el-table-column>
            </el-table>
            
        </div>
        
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
        //表格数据
        tableData: [],
      };
    },
    mounted() {
      this.getData();
    },
    methods: {
      //获取手册列表
      getData(){
        var that = this;
        this.$ajax.get("/recovery/operate/selectLimit",{
            params:{
              type: sessionStorage.getItem('userType')
            }
        }).then(function(response){
          console.log(response)
          if(response.data.status == 260){
            that.tableData = response.data.list;
          }else{
            that.tableData = [];
          }
        })
      },
      //下载附件
      upLoadFile(){

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
.workImgBox{
    width: 100%;
    padding: 0 5%;
    background: #fff;
    margin-top: .1rem;
    padding: .05rem;
    box-sizing: border-box;
  }
  .workImgBox > p{
    height: .3rem;
    line-height: .3rem;
    font-size: .16rem;
    color: #333;
  }
</style>
