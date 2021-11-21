<template>
    <div id="indexBox">
        <div class="indexLeftBox">
          <el-scrollbar style="height: 100%">
            <el-menu 
            :default-active="this.$route.path" router 
            class="el-menu-vertical-demo" unique-opened 
            @open="handleOpen" @close="handleClose" 
            :collapse="isCollapse" background-color="#304156" 
            text-color="#fff">
                <el-menu-item index="/yiyuanCenter"  v-if="userRole == '医疗机构管理员'">
                  <i class="el-icon-user-solid"></i>
                  <span slot="title">个人中心</span>
                </el-menu-item>
                <el-menu-item index="/chuliCenter"  v-if="userRole == '回收利用企业管理员'">
                  <i class="el-icon-user-solid"></i>
                  <span slot="title">个人中心</span>
                </el-menu-item>
                <el-menu-item index="/nterPrise"  v-if="userType == 'null'">
                  <i class="el-icon-school"></i>
                  <span slot="title">公示名单</span>
                </el-menu-item>
                <el-menu-item index="/yunshuCenter"  v-if="userRole == '回收物流管理员'">
                  <i class="el-icon-user-solid"></i>
                  <span slot="title">个人中心</span>
                </el-menu-item>
                <el-menu-item index="/bigPage" v-if="userRole == '医疗机构管理员' || userRole == '国家管理单位管理员' || userRole == '省管理单位管理员' || userRole == '市管理单位管理员' || userRole == '区管理单位管理员'">
                  <i class="el-icon-s-platform"></i>
                  <span slot="title">数据统计</span>
                </el-menu-item>
                <el-menu-item index="/treatmentplantBigPage"  v-if="userRole == '回收利用企业管理员'">
                  <i class="el-icon-s-platform"></i>
                  <span slot="title">数据统计</span>
                </el-menu-item>
                <el-menu-item index="/transportcompanyBigPage"  v-if="userRole == '回收物流管理员'">
                  <i class="el-icon-s-platform"></i>
                  <span slot="title">数据统计</span>
                </el-menu-item>
                <el-menu-item index="/userlist" v-if="userRole != '系统管理员'&&userType != 'null'">
                  <i class="el-icon-user"></i>
                  <span slot="title">用户管理</span>
                </el-menu-item>
                <el-menu-item index="/shangwuUser" v-if="userRole == '系统管理员'">
                  <i class="el-icon-user"></i>
                  <span slot="title">商务部门用户管理</span>
                </el-menu-item>
                <el-menu-item index="/newsList" v-if="userRole == '系统管理员'">
                  <i class="el-icon-news"></i>
                  <span slot="title">通知公告</span>
                </el-menu-item>
                <el-menu-item index="/Manual" v-if="userRole == '系统管理员'">
                  <i class="el-icon-tickets"></i>
                  <span slot="title">操作手册</span>
                </el-menu-item>
                <el-menu-item index="/SuperVisionUnit" v-if="userRole == '系统管理员' || userRole == '国家管理单位管理员' || userRole == '省管理单位管理员' || userRole == '市管理单位管理员'">
                  <i class="el-icon-s-check"></i>
                  <span slot="title">监管单位</span>
                </el-menu-item>
                <el-menu-item index="/hospitalList" v-if="userRole == '国家管理单位管理员' || userRole == '省管理单位管理员' || userRole == '市管理单位管理员' || userRole == '区管理单位管理员' || userRole == '回收物流管理员'">
                  <i class="el-icon-circle-plus-outline"></i>
                  <span slot="title">医疗机构管理</span>
                </el-menu-item>
                <el-menu-item index="/Department" v-if="userRole == '医疗机构管理员'">
                  <i class="el-icon-circle-plus-outline"></i>
                  <span slot="title">科室管理</span>
                </el-menu-item>
                <el-menu-item index="/Garbagebag" v-if="userRole == '医疗机构管理员'">
                  <i class="el-icon-toilet-paper"></i>
                  <span slot="title">垃圾袋管理</span>
                </el-menu-item>
                <el-menu-item index="/barCode" v-if="userRole == '医疗机构管理员'">
                  <i class="el-icon-printer"></i>
                  <span slot="title">生成垃圾袋贴码</span>
                </el-menu-item>
                <el-menu-item index="/treatmentPlant"  v-if="userRole == '系统管理员'">
                  <i class="el-icon-school"></i>
                  <span slot="title">回收利用企业管理</span>
                </el-menu-item>
                <el-submenu index="3" v-if="userRole == '回收利用企业管理员'">
                    <template slot="title">
                      <i class="el-icon-s-help"></i>
                      <span slot="title">原料管理</span>
                    </template>
                    <el-menu-item index="/inDetail">入库记录</el-menu-item>
                    <el-menu-item index="/outDetail">出库记录</el-menu-item>
                    <el-menu-item index="/treatmentplantRecords">交接汇总</el-menu-item>
                </el-submenu>
                <el-menu-item index="/treatmentPlantHospital" v-if="userRole == '回收利用企业管理员'">
                  <i class="el-icon-circle-plus-outline"></i>
                  <span slot="title">医疗机构</span>
                </el-menu-item>
                <el-menu-item index="/productionPlant" v-if="userRole == '系统管理员'">
                  <i class="el-icon-office-building"></i>
                  <span slot="title">再利用企业管理</span>
                </el-menu-item>
                <el-menu-item index="/productionJoinList" v-if="userRole == '再利用企业管理员'">
                  <i class="el-icon-office-building"></i>
                  <span slot="title">交接记录</span>
                </el-menu-item>
                <el-menu-item index="/transportcompany"  v-if="userRole == '系统管理员' || userRole == '回收利用企业管理员'">
                  <i class="el-icon-truck"></i>
                  <span slot="title">回收物流</span>
                </el-menu-item>
                <!-- <el-menu-item index="/yunShu"  v-if="userRole == '回收利用企业管理员'">
                  <i class="el-icon-s-shop"></i>
                  <span slot="title">暂存点</span>
                </el-menu-item> -->
                <el-submenu index="1" v-if="userRole == '回收利用企业管理员'">
                    <template slot="title">
                      <i class="el-icon-refresh"></i>
                      <span slot="title">破碎料管理</span>
                    </template>
                    <el-menu-item index="/producType">破碎料类别</el-menu-item>
                    <el-menu-item index="/producIn">破碎料入库</el-menu-item>
                    <el-menu-item index="/producOut">破碎料出库</el-menu-item>
                    <el-menu-item index="/dataShow">入库统计</el-menu-item>
                    <el-menu-item index="/dataShowOut">出库统计</el-menu-item>
                    <el-menu-item index="/kuCunShow">库存统计</el-menu-item>
                </el-submenu>
                <el-submenu index="2" v-if="userRole == '回收利用企业管理员'">
                    <template slot="title">
                      <i class="el-icon-orange"></i>
                      <span slot="title">颗粒料管理</span>
                    </template>
                    <el-menu-item index="/grainproducType">颗粒料类别</el-menu-item>
                    <el-menu-item index="/grainIn">颗粒料入库</el-menu-item>
                    <el-menu-item index="/grainOut">颗粒料出库</el-menu-item>
                    <el-menu-item index="/graindataShow">入库统计</el-menu-item>
                    <el-menu-item index="/graindataShowOut">出库统计</el-menu-item>
                    <el-menu-item index="/kuCunShowKeli">库存统计</el-menu-item>
                </el-submenu>
                <el-menu-item index="/order"  v-if="userRole == '回收利用企业管理员'">
                  <i class="el-icon-truck"></i>
                  <span slot="title">订单管理</span>
                </el-menu-item>
                <el-menu-item index="/carList"  v-if="userRole == '回收物流管理员'">
                  <i class="el-icon-truck"></i>
                  <span slot="title">车辆管理</span>
                </el-menu-item>
                <el-menu-item index="/Truck"  v-if="userRole == '回收物流管理员'">
                  <i class="el-icon-truck"></i>
                  <span slot="title">车次管理</span>
                </el-menu-item>
                <el-menu-item index="/rukuList"  v-if="userRole == '回收物流管理员'">
                  <i class="el-icon-receiving"></i>
                  <span slot="title">入库统计</span>
                </el-menu-item>
                <el-menu-item index="/joinList"  v-if="userRole == '医疗机构管理员'">
                  <i class="el-icon-truck"></i>
                  <span slot="title">交接记录</span>
                </el-menu-item>
                <el-menu-item index="/upManual" v-if="userRole != '系统管理员' && userType != 'null'">
                  <i class="el-icon-tickets"></i>
                  <span slot="title">操作手册</span>
                </el-menu-item>
                <!-- <el-submenu index="3">
                    <template slot="title">
                      <i class="el-icon-truck"></i>
                      <span slot="title">回收物流</span>
                    </template>
                    <el-menu-item index="/transportcompany">公司列表</el-menu-item>
                    <el-menu-item index="/Truck">车次管理</el-menu-item>
                </el-submenu> -->
            </el-menu>
          </el-scrollbar>
        </div>
        <div class="indexRightBox">
            <div class="topHomeBar">
                <span>{{belongName}} 输液瓶（袋）回收溯源系统</span>
                <div>
                  <span>{{username}}</span>
                  <P>
                    <i class='el-icon-key' @click="changePwd"></i>
                    <i class='el-icon-switch-button' @click="logOut"></i>
                  </P>
                </div>
            </div>
           <keep-alive>
            <router-view v-if="$route.meta.keepAlive"></router-view>
          </keep-alive>
          <router-view v-if="!$route.meta.keepAlive"></router-view>
            
        </div>
    </div>
</template>

<script>
  export default {
    data() {
      return {
        username: sessionStorage.getItem('userName'),
        belongName: sessionStorage.getItem('belongName'),
        isCollapse: false,
        userType: sessionStorage.getItem('userType'),
        userRole: sessionStorage.getItem('userType')+sessionStorage.getItem('userRole')
      };
    },
    mounted(){
      console.log("123123123",this.userType === "null")
    },
    methods: {
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      },
      //修改密码
      changePwd(){
          var that = this;
          this.$prompt('请输入新密码', '修改密码', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          inputValidator: function(v){
              if(v == null || v == ''){
                  return false
              }
          },
          inputErrorMessage: '请输入新密码'
        }).then(({ value }) => {
            let params = that.$qs.stringify({
              userid: sessionStorage.getItem('userId'),
              password: this.$md5(value),
            }, { indices: false })
            that.$ajax.put("/recovery/user/resetPwd",params).then(function(response){
                if(response.data.status == 260){
                    sessionStorage.clear();
                    that.$router.replace("/");
                    that.$message.success("修改成功,请重新登陆")
                }
            })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '取消输入'
          });       
        });
      },
      // 退出登录
      logOut(){
        sessionStorage.clear();
        this.$router.replace("/");
        this.$message.success("已退出")
      }
    }
  }
</script>

<style scoped>
#indexBox {
    width: 100vw;
    min-height: 100%;
}
.el-menu-vertical-demo:not(.el-menu--collapse) {
    width: 200px;
    /* height: 100vh; */
    min-height: 100vh;
}
.indexLeftBox{
    width: 200px;
    height: 100vh;
    position: fixed;
    top: 0;
    left: 0;
}
.indexRightBox{
    width: 100%;
    height: 100vh;
    padding-left: 200px;
    box-sizing: border-box;
    background: #F5F7F9;
    /* position: relative; */
}
.topHomeBar{
    width: 100%;
    height: 50px;
    padding: 10px 35px;
    box-sizing: border-box;
    line-height: 30px;
    background: #fff;
    box-shadow: 0 1px 4px rgba(0,21,41,0.08);
    font-size: 18px;
    display: flex;
    align-items: center;
    justify-content: space-between;
    /* position: fixed;
    top: 0;
    left: 0; */
}
.topHomeBar > div{
    display: flex;
    align-items: center;
}
.topHomeBar > div > span{
  font-size: 16px;
}
.topHomeBar > div > p{
  width: 60px;
  margin-left: 20px;
  display: flex;
  align-items: center;
  justify-content: space-between;
}
.topHomeBar > div > p > i{
  font-size: 20px;
  cursor: pointer;
}
.topHomeBar > div > p > i:hover{
  color: #409EFF;
}
</style>
