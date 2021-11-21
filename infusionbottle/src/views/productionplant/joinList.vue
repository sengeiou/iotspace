<template>
    <div id="yudingBox">
        <div class="selectBox">
        </div>
        <!-- 用户列表 -->
        <div class="tableBox">
          <p style="height:30px;font-size: 14px">出库记录：</p>
            <el-table :data="tableData" style="width: 100%">
                <el-table-column prop="" label="时间" width="135" sortable>
                    <template slot-scope="scope">
                      <span v-if="scope.row.time == null"></span>
                      <span v-else>{{scope.row.time | formatDate}}</span>
                    </template>
                  </el-table-column>
                  <el-table-column prop="productionplant.name" label="再利用企业"></el-table-column>
                <el-table-column prop="truck.transportcompany.name" label="回收物流"></el-table-column>
                  <el-table-column prop="truck.vehicle.number" label="车牌"></el-table-column>
                  <el-table-column prop="plasticweight" label="塑料重量(kg)"></el-table-column>
                  <el-table-column prop="glassweight" label="玻璃重量(kg)"></el-table-column>
                  <el-table-column prop="status" label="状态"></el-table-column>
                  <el-table-column prop="submitUser.username" label="提交人"></el-table-column>
                  <el-table-column prop="confirmUser.username" label="确认人"></el-table-column>
                  <el-table-column prop="note" label="备注"></el-table-column>
            </el-table>
            <div class="pafinationBox">
              <div>
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
        //用户id和用户类型
        userId: sessionStorage.getItem("userId"),
        userType:sessionStorage.getItem("userType"),
        page: 1,
        pageSize: 5,
        totalNum: 100,
        //表格数据
        tableData: [],
      };
    },
    mounted() {
      this.getData();
    },
    methods: {
      
      //获取出库信息
      getData(){
        var that = this;
        this.$ajax.get("/recovery/truckproductionplant/selectVByPage",{
            params:{
              page: this.page,
              rows: this.pageSize,
              productionplantid: sessionStorage.getItem('belongId'),
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
      //清空筛选
      clearSelect(){
        this.form = {
            submitid: null,
            status: null,
            starttime: null,
            endtime: null
        }
        this.getData();
      },
      //查询
      selectList(){
        this.page = 1;
        this.getData();
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
    margin-bottom: 20px;
    padding-top: 20px;
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
.search-box{
  width: 200px;
  margin-top: 15px;
  display: flex;
  justify-content: center;
}
.search-box > span:first-child{
  width:100px;
  height: 40px;
  line-height: 40px;
  color: #666
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
    width: 68px;
}
</style>
