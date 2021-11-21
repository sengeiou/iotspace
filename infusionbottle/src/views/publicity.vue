<template>
    <div id="yudingBox">
        <div class="headerBox">
            <img src="../assets/header.png"/>
        </div>
        <div class="box" v-if="index == 1">
            <div class="pageTitle">
                各地输液瓶（袋）回收利用企业公示名单
            </div>
            
            <div class="tableBox">
                <el-table :data="tableData" style="width: 100%">
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
        <div class="box" v-if="index == 2">
            <div class="pageTitle">
                通知公告
            </div>
            <div class="tableBox">
                <el-table :data="gongGaoData" style="width: 100%">
                    <el-table-column prop="time" label="创建时间" sortable>
                    <template slot-scope="scope">
                        {{scope.row.time | formatDate}}
                    </template>
                    </el-table-column>
                    <el-table-column prop="title" label="公告标题"></el-table-column>
                    <el-table-column prop="item" label="公告内容"></el-table-column>
                    <el-table-column prop="importance" label="重要程度"></el-table-column>
                    <el-table-column prop="type" label="公告类型"></el-table-column>
                    <el-table-column prop="file" label="附件">
                        <template slot-scope="scope">
                            <el-link type="primary" :href="scope.row.file" v-if="scope.row.file!=null">下载附件</el-link>
                            <span>暂无附件</span>
                        </template>
                    </el-table-column>
                    <el-table-column prop="img" label="图片">
                        <template slot-scope="scope">
                            <img :src="scope.row.img" alt="暂无图片" style="width: 100px;">
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
                    @current-change="changePage"
                    >
                </el-pagination>
                </div>
            </div>
        </div>
        <div class="box" v-if="index == 4">
            <div class="pageTitle">
                政策文件
            </div>
            <div class="tableBox">
                <el-table :data="zhengFuData" style="width: 100%">
                    <el-table-column prop="time" label="创建时间" sortable>
                    <template slot-scope="scope">
                        {{scope.row.time | formatDate}}
                    </template>
                    </el-table-column>
                    <el-table-column prop="title" label="公告标题"></el-table-column>
                    <el-table-column prop="item" label="公告内容"></el-table-column>
                    <el-table-column prop="importance" label="重要程度"></el-table-column>
                    <el-table-column prop="type" label="公告类型"></el-table-column>
                    <el-table-column prop="file" label="附件">
                        <template slot-scope="scope">
                            <el-link type="primary" :href="scope.row.file" v-if="scope.row.file!=null">下载附件</el-link>
                            <span v-else>暂无附件</span>
                        </template>
                    </el-table-column>
                    <el-table-column prop="img" label="图片">
                        <template slot-scope="scope">
                            <img :src="scope.row.img" alt="暂无图片" style="width: 100px;">
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
                    @current-change="changePage"
                    >
                </el-pagination>
                </div>
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
        
                // return year+"-"+month+"-"+day+" "+hour+":"+minutes;
                return year+"-"+month+"-"+day;
            },
    },
    data() {
      return {
        index: this.$route.query.index,
        page: 1,
        pageSize: 10,
        totalNum: 100,
        //表格数据
        tableData: [],
        gongGaoData: [],
        zhengFuData: [],
      };
    },
    mounted() {
      this.getData();
    },
    methods: {
      //获取区域信息
      getData(){
        var that = this;
        this.$ajax.get("/recovery/nterprise/homeSelectByPage",{
            params:{
              page: this.page,
              rows: this.pageSize,
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
        this.$ajax.get("/recovery/notice/homePageNotice",{
            params:{
                page: 1,
                rows: 5,
                type: "一般公告"
            }
        }).then(function(response){
            console.log("通知公告",response);
            that.gongGaoData = response.data.list;
        })
        this.$ajax.get("/recovery/notice/homePageNotice",{
            params:{
                page: 1,
                rows: 5,
                type: "政策文件"
            }
        }).then(function(response){
            console.log("政策文件",response);
            that.zhengFuData = response.data.list;
        })
      },
      //分页换页
      changePage(page){
        this.page = page;
        this.getData();
      },
    }
  }
</script>

<style scoped>
#yudingBox {
    width: 1200px;
    min-height: 100vh;
    margin: 0 auto;
    background: #F5F7F9;
}
.headerBox{
    width: 1200px;
    margin: 0 auto;
    height: 130px;
    display: flex;
    align-items: center;
    justify-content: center;
}
.headerBox > img{
    width: 540px;
    height: 90px;
}
.box{
    width: 100%;
    height: 100%;
}
.pageTitle{
    width: 95%;
    margin: 0 auto;
    height: 50px;
    font-size: 20px;
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
</style>
