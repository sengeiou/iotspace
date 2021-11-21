<template>
  <div id="loginBox">
        <div class="headerBox">
            <img src="../assets/header.png"/>
            <div class="souBox">
                <img src="../assets/souysou.png" alt="">
            </div>
        </div>
        <div class="bannerBox" style="margin-top: 10px">
            <div class="newsBox">
                <el-carousel trigger="click" height="372px">
                    <el-carousel-item v-for="(item,index) in newsData" :key="index">
                       <img :src="item.img" alt="" class="bannerImg"  @click="lookGongGaoDetail(item,1)">
                    </el-carousel-item>
                </el-carousel>
            </div>
            <div class="mainCenterBox">
                <div class="loginTitle">
                    <span :class="{ 'active': isActive == 0 }" @click="loginTabOne">医疗机构/企业/监管单位登录</span>
                    <!-- <el-divider direction="vertical"></el-divider>
                    <span :class="{ 'active': isActive == 1 }" @click="loginTabTwo">商务部门登录</span> -->
                </div>
                <div class="iptBox">
                    <span>账号</span>
                    <input type="text" placeholder="请填写您的账号" v-model="username"/>
                </div>
                <div class="iptBox">
                    <span>密码</span>
                    <input type="password" placeholder="请填写您的密码" v-model="password"/>
                </div>
                <div class="iptBox">
                    <span>验证码</span>
                    <input type="text" style="width: 55%" placeholder="请输入图片验证码" v-model="imgcode"/>
                    <p @click="rand()">
                        <imgCode :identifyCode="identifyCode"></imgCode>
                    </p>
                </div>
                <div class="loginBtn">
                    <button @click="login">登录</button>
                </div>
            </div>
            
        </div>
        <div class="bannerBox">
            <!-- <div class="qiyeBox">
                <div class="qiyeTit">
                    <p>各地输液瓶（袋）回收利用企业公示名单</p>
                    <router-link tag="a"  class="linkA"
                        target="_blank"
                        to="/gongshiMore">
                        <span style="font-size:14px;color:#5ab533">查看更多</span>
                    </router-link>  
                </div>
                <div class="qiYeBox">
                    <el-table :data="qiYeData" style="width: 100%;background: transparent;color:#1b5402">
                        <el-table-column prop="name" label="企业名称"></el-table-column>
                        <el-table-column prop="department" label="认证部门" width="150"></el-table-column>
                        <el-table-column prop="time" label="公示时间" sortable width='100'>
                        <template slot-scope="scope">
                            {{scope.row.time | formatDate}}
                        </template>
                        </el-table-column>
                    </el-table>
                </div>
            </div> -->
            <div class="qiyeBox" style="width: 100%">
                <div class="qiyeTit">
                    <p>医疗机构可回收物中废塑料回收企业信息</p>
                    <router-link tag="a"  class="linkA"
                        target="_blank"
                        to="/qiyeMore">
                        <span style="font-size:14px;color:#5ab533">查看更多</span>
                    </router-link> 
                </div>
                <div class="qiYeBox">
                    <el-table :data="huishouData" style="width: 100%;background: transparent;color:#1b5402">
                        <el-table-column prop="name" label="企业名称"></el-table-column>
                        <el-table-column prop="leadername" label="法人"></el-table-column>
                        <el-table-column prop="province" label="省份"></el-table-column>
                        <el-table-column prop="batch" label="批次" width="100px" sortable>
                            <template slot-scope="scope">
                                <span>第{{scope.row.batch}}批</span>
                            </template>
                        </el-table-column>
                    </el-table>
                </div>
            </div>
            
        </div>
        <div class="bannerBox">
            <div class="gongGaoBox">
                <div class="gonggaoTit">
                    <p>通知公告</p>
                    <router-link tag="a"  class="linkA"
                        target="_blank"
                        :to="{ path: '/publicity', query: {index: 2} }">
                        <span style="font-size:14px;color:#5ab533">查看更多</span>
                    </router-link> 
                </div>
                <div class="gongGaoListBox">
                    <div class="gongGaoList"  v-for="(item,index) in gongGaoData" :key="index" @click="lookGongGaoDetail(item,1)">
                        <p class="textsl">{{item.title}}</p>
                        <p>{{item.time | formatDate}}</p>
                    </div>
                </div>
            </div>
            <div class="gongGaoBox">
                <div class="gonggaoTit">
                    <p>政策文件</p>
                    <router-link tag="a"  class="linkA"
                        target="_blank"
                        :to="{ path: '/publicity', query: {index: 4} }">
                        <span style="font-size:14px;color:#5ab533">查看更多</span>
                    </router-link> 
                </div>
                <div class="gongGaoListBox">
                    <div class="gongGaoList"  v-for="(item,index) in zhengFuData" :key="index" @click="lookGongGaoDetail(item,2)">
                        <p class="textsl">{{item.title}}</p>
                        <p>{{item.time | formatDate}}</p>
                    </div>
                </div>
            </div>
        </div>
        <div class="codeBottomBox">
            <div class="codeBox">
                <img src="../assets/1、再生溯源公众号.jpg" alt="">
                <p>再生溯源公众号</p>
            </div>
            
            <div class="codeBox">
                <img src="../assets/3、塑料分会公众号.jpg" alt="">
                <p>塑料分会公众号</p>
            </div>
            <div class="codeBox">
                <img src="../assets/4、回收分会公众号.jpg" alt="">
                <p>回收分会公众号</p>
            </div>
            <div class="codeBox">
                <img src="../assets/2、小程序.jpg" alt="">
                <p>小程序</p>
            </div>
        </div>
        <div class="bottomBox">
            <P>版权所有：中国物资再生协会回收分会、中国物资再生协会塑料分会</P>
            <p>技术支持：再生溯源（北京）科技有限公司</p>
            <p>咨询电话：13370108016</p>
        </div>
        <el-dialog
          :title="tanTitle"
          :visible.sync="gongGaoIfIs"
          width="500px"
          center>
          <div class="dialogMain">
            <div class="detailBox">
              <div>
                <span>标题：</span>
                <span>{{gonggaoDetail.title}}</span>
              </div>
              <div>
                <span>内容：</span>
                <span>{{gonggaoDetail.item}}</span>
              </div>
              <div>
                <span>重要程度：</span>
                <span>{{gonggaoDetail.importance}}</span>
              </div>
              <div>
                <span>类型：</span>
                <span>{{gonggaoDetail.type}}</span>
              </div>
              <div v-if="gonggaoDetail.file != null">
                <span>附件：</span>
                <el-link :href="gonggaoDetail.file" type="primary">下载附件</el-link>
              </div>
              <div v-if="gonggaoDetail.img != null">
                <span>图片：</span>
                <img :src='gonggaoDetail.img' style="width: 260px;"/>
              </div>
            </div>
          </div>
        </el-dialog>
  </div>
</template>

<script>
import ImgCode from "../components/img"
export default {
  components:{
      "imgCode" :ImgCode
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
          if(day <10){
              day = "0" + day;
          }
          return year+"-"+month+"-"+day;
      },
    },
  data () {
    return {
        gongGaoIfIs: false,
        tanTitle: "",
        username:'',
        password:'',
        imgcode:'',
        newsData:[],
        gongGaoData: [],
        zhengFuData: [],
        huishouData: [],
        qiYeData: [],
        identifyCode: "",
        isActive: 0,
        gonggaoDetail: {
            file: null,
            id: null,
            img: null,
            importance: null,
            item: null,
            note: null,
            time: null,
            title: null,
            type: null,
            userid: null,
        }
    }
  },
  mounted(){
      this.getNewsData();
      this.rand();
  },
  methods: {
    rand() {
        this.identifyCode = String(Math.floor(Math.random()*(9999-1000))+1000);
    },
    getNewsData(){
        var that = this;
        this.$ajax.get("/recovery/notice/selectLimit3").then(function(response){
            console.log("通知banner图片",response);
            that.newsData = response.data.list;
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
        this.$ajax.get("/recovery/nterprise/homeSelectByPage",{
            params:{
                page: 1,
                rows: 3,
            }
        }).then(function(response){
            console.log("企业公示",response);
            that.qiYeData = response.data.list;
        })
        this.$ajax.get("/recovery/treatmentplant/homeSelectByPage",{
            params:{
                page: 1,
                rows: 5,
            }
        }).then(function(response){
            console.log("企业公示",response);
            that.huishouData = response.data.list;
        })
    },
    //tab切换
    loginTabOne(){
        this.isActive = 0;
    },
    loginTabTwo(){
        this.isActive = 1;
    },
    login() {
        var that = this;
        if(this.username == "" || this.password == "" || this.imgcode == ""){
            return that.$message.error("请填写用户名/密码/验证码！");
        }
        if(this.identifyCode != this.imgcode){
            return that.$message.error("验证码输入错误！");
        }
        if(this.isActive == 1){
            this.$ajax.get("/recovery/tradeuser/login",{
                params:{
                    username: this.username,
                    password: this.$md5(this.password)
                }
            }).then(function(response){
                console.log('登陆成功',response);
                sessionStorage.setItem('token',response.data.token);
                sessionStorage.setItem('userId',response.data.user.id);
                sessionStorage.setItem('userType',response.data.user.type);
                sessionStorage.setItem('userRole',response.data.user.role);
                sessionStorage.setItem('userName',response.data.user.username);
                that.$router.replace("/nterPrise");
            })
         return;
        }
        this.$ajax.get("/recovery/user/login",{
            params:{
                username: this.username,
                password: this.$md5(this.password)
            }
        }).then(function(response){
            console.log('登陆成功',response)
            if(response.data.status == 260){
                sessionStorage.setItem('token',response.data.token)
                sessionStorage.setItem('userId',response.data.user.id)
                sessionStorage.setItem('userType',response.data.user.type)
                sessionStorage.setItem('userRole',response.data.user.role)
                sessionStorage.setItem('userName',response.data.user.username)
                if(response.data.user.type == '系统'){
                    sessionStorage.setItem('belongId',0)
                }else{
                    
                    sessionStorage.setItem('belongId',response.data.user.belongid);
                    if(response.data.supervisionunit != null){
                        sessionStorage.setItem('belongName',response.data.supervisionunit.name);
                        if(response.data.user.type != '回收利用企业'&&response.data.user.type != '再利用企业'&&response.data.user.type != '回收物流'){
                            sessionStorage.setItem('userAddress',response.data.supervisionunit.address);
                            sessionStorage.setItem('addressLevel',response.data.supervisionunit.level);
                        }
                        if(response.data.user.type != '国家管理单位'&&response.data.user.type != '回收利用企业'&&response.data.user.type != '再利用企业'&&response.data.user.type != '回收物流'){
                            sessionStorage.setItem('userFatherAddress',response.data.supervisionunit.supervisionunit.address);
                            sessionStorage.setItem('supervisionunitName',response.data.supervisionunit.supervisionunit.name);
                        }
                    }
                    
                }
                if(response.data.user.role == '管理员'){
                    if(response.data.user.type == '系统' || response.data.user.type == '国家管理单位' || response.data.user.type == '省管理单位' || response.data.user.type == '市管理单位'){
                        that.$router.replace("/SuperVisionUnit")
                    }else if(response.data.user.type == '区管理单位'){
                        that.$router.replace('/hospitalList')
                    }else if(response.data.user.type == '医疗机构'){
                        that.$router.replace('/Department')
                    }else if(response.data.user.type == '回收利用企业'){
                        that.$router.replace('/outDetail')
                    }else if(response.data.user.type == '再利用企业'){
                        that.$router.replace('/productionJoinList')
                    }else if(response.data.user.type == '回收物流'){
                        that.$router.replace('/Truck')
                    }
                }else{
                    that.$message.error("对不起，您非管理员用户，不可登录！")
                }
            }
        })
    },
    //查看公告详情
    lookGongGaoDetail(data,index){
        if(index == 1){
            this.tanTitle = '公告详情'
        }else if(index == 2){
            this.tanTitle = "政策文件"
        }
        var that = this;
        this.$ajax.get("/recovery/notice/selectById",{
            params:{
                id: data.id
            }
        }).then(function(response){
            console.log("通知公告详情",response);
            if(response.data.status == 260){
                that.gonggaoDetail = response.data.object;
                that.gongGaoIfIs = true;
            }else{
              that.$message.error("暂无详情")
            }
        })
    }
  },
}
</script>

<style scoped>
#loginBox {
  width: 1200px;
  margin: 0 auto;
  /* height: 100vh; */
}
.headerBox{
    width: 1200px;
    margin: 0 auto;
    height: 130px;
    display: flex;
    align-items: center;
    justify-content: space-between;
    background: rgb(240,247,241, 80%);
}
.headerBox > img{
    width: 540px;
    height: 90px;
}
.bannerImg{
    width: 100%;
    height: 100%;
}
.codeBottomBox{
    width: 100%;
    display: flex;
    align-items: center;
    justify-content: space-between;
    padding: 10px;
    box-sizing: border-box;
    background: rgb(240,247,241, 80%);
}
.souBox{
    padding: 0 30px;
    height: 120px;
}
.souBox > img{
    height: 100%;
}
.codeBox{
    width: 150px;
} 
.codeBox > img{
    width: 100%;
}
.codeBox > p{
    text-align: center;
    font-size: 12px;
    color: #1b5402;
}

.bannerBox{
    width: 1200px;
    /* height: 500px; */
    margin: 0 auto;
    display: flex;
    align-items: center;
    justify-content: space-between;
    margin-bottom: 10px;
}
.newsBox{
    width: 59.5%;
    height: 100%;
    background: rgba(255,255,255,0.6);
    box-sizing: border-box;
    padding: 1px;
}
.mainCenterBox{
    width: 38%;
    padding: 8px 10px 18px;
    overflow: hidden;
    background: rgb(240 247 241 / 80%);
    text-align: center;
    /* margin-left: 2%; */

}
.loginTitle{
    width: 100%;
    height: 80px;
    line-height: 80px;
    color: #666;
    font-size: 18px;
}
.loginTitle > span{
    cursor: pointer;
}
.loginTit{
    width: 100%;
    height: 30px;
    line-height: 30px;
    font-size: 28px;
    margin-bottom: 40px;
}
.iptBox{
    width: 85%;
    height: 50px;
    margin: 0 auto;
    display: flex;
    align-items: center;
    justify-content: flex-start;
    color: #1b5402;
    border: 1px solid rgba(255,255,255,0.1);
    background: rgba(0,0,0,0.1);
    border-radius: 5px;
    margin-bottom: 20px;
}
.iptBox > span{
    font-size: 14px;
    display: block;
    width: 60px;
}
.iptBox > input{
    width: 80%;
    height: 50px;
    border: none;
    background: transparent;
    font-size: 14px;
    outline: none;
    color: #1b5402;
}
.loginBtn{
    width: 85%;
    height: 50px;
    margin: auto;
}
.loginBtn > button{
    width: 100%;
    height: 40px;
    border-radius: 5px;
    background: #5ab533;
    color: #fff;
    border: none;
    font-size: 14px;
    cursor: pointer;
    outline: none;
}
.bottomBox{
    width: 1000px;
    height: 90px;
    font-size: 14px;
    margin: 0 auto;
    display: flex;
    align-items: center;
    justify-content: space-between;
    color: #1b5402;
}
.active{
    color: #1b5402
}
.qiyeBox{
    width: 59.5%;
    height: 301px;
    background: rgb(240 247 241 / 80%);
}
.qiyeTit{
    width: 90%;
    height: 60px;
    margin: 0 auto;
    line-height: 60px;
    color: #1b5402;
    font-size: 18px;
    display: flex;
    align-items: center;
    justify-content: space-between;
    border-bottom: 1px solid #ccc;
}
.qiyeTit > p:last-child{
    font-size: 14px;
    color: #5ab533;
    cursor: pointer;
}
.qiYeBox{
    width: 90%;
    margin: 0 auto;
}
.gongGaoBox{
    width: 48%;
    height: 283px;
    padding: 0 10px 18px;
    background: rgb(240 247 241 / 80%);
}
.gonggaoTit{
    width: 80%;
    height: 60px;
    margin: 0 auto;
    line-height: 60px;
    color: #1b5402;
    font-size: 18px;
    display: flex;
    align-items: center;
    justify-content: space-between;
    border-bottom: 1px solid #ccc;
}
.gonggaoTit > p:last-child{
    font-size: 14px;
    color: #5ab533;
    cursor: pointer;
}
.gongGaoListBox{
    width: 80%;
    margin: 0 auto;
}
.gongGaoList{
    width: 100%;
    height: 40px;
    display: flex;
    align-items: center;
    justify-content: space-between;
    color: #1b5402;
    border-bottom: 1px solid #EBEEF5;
}
.detailBox{
    width: 80%;
    margin: auto;
    box-sizing: border-box;
    padding: 0 1%;
    background: #fff;
    display: flex;
    flex-wrap: wrap;
    align-items: center;
    justify-content: flex-start;
}
.detailBox > div{
    width: 100%;
    padding: 15px 0;
    display: flex;
    flex-wrap: wrap;
    align-items: center;
}
.linkA{
    text-decoration: none;
}
.textsl{
    width: 290px;
    display: -webkit-box;/*作为弹性伸缩盒子模型显示*/
    -webkit-line-clamp: 1; /*显示的行数；如果要设置2行加...则设置为2*/
    overflow: hidden; /*超出的文本隐藏*/
    text-overflow: ellipsis; /* 溢出用省略号*/
    -webkit-box-orient: vertical;/*伸缩盒子的子元素排列：从上到下*/
}
</style>
