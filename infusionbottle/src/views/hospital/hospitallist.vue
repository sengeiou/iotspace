<template>
    <div id="yudingBox">
        <div class="selectBox">
          <el-form ref="form" :model="form" label-width="100px">
            <el-form-item label="医疗机构名称">
                <el-input v-model="form.item" @change="selectList"></el-input>
            </el-form-item>
            <el-form-item label="所属级别" v-if="userType != '回收物流' && addressLevel!=='区'">
              <el-select v-model="form.belonglevel" placeholder="请选择所属级别"  @change="selectList">
                    <el-option v-for="(item,index) in belongLevelList" :key="index"  :label="item.text" :value="item.value"></el-option>
                </el-select>
            </el-form-item>
            <!-- <el-form-item label="省市区" >
              <selectAddress @provinceData="getProvinceData" @cityData="getCityData" @platData="getPlatData"></selectAddress>
            </el-form-item> -->
          </el-form>
        </div>
        <div class="selectBox">
          <el-form ref="form" :model="form" label-width="100px">
            <el-form-item label="机构注册类型">
              <el-select v-model="form.type" placeholder="请选择类型" @change="getLevelList(form.type,1)" style="width: 178px">
                    <el-option v-for="(item,index) in typeList" :key="index"  :label="item.text" :value="item.value"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="级别" v-show="form.type != ''">
              <el-select v-model="form.level" placeholder="请选择级别" @change="selectList">
                    <el-option v-for="(item,index) in levelList" :key="index"  :label="item.text" :value="item.value"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-button type="warning" size="small" @click="clearSelect" style="font-size:14px">清空筛选</el-button>
                <!-- <el-button type="primary"  size="small" @click="add" style="font-size:14px">批量导入</el-button>
                <el-button type="success" size="small" @click="exportBarCode" style="font-size:14px">下载模版</el-button>
                <input type="file" @change="fileChanged" ref="upFile" v-show="false"> -->
            </el-form-item>
          </el-form>
        </div>
        <div class="tableBox">
            <el-table :data="tableData" height="88%" style="width: 100%" @selection-change="handleSelectionChange">
                <el-table-column
                  type="selection"
                  width="55"
                  >
                </el-table-column>
                <el-table-column prop="name" label="名称"></el-table-column>
                <el-table-column prop="supervisionunit.name" label="监管单位"></el-table-column>
                <el-table-column prop="belonglevel" label="归属级别"></el-table-column>
                <el-table-column prop="code" label="登记号"></el-table-column>
                <el-table-column prop="type" label="注册类型"></el-table-column>
                <el-table-column prop="level" label="等级"></el-table-column>
                <el-table-column prop="leadername" label="法人"></el-table-column>
                <el-table-column prop="leaderphone" label="电话"></el-table-column>
                <el-table-column prop="address" label="地址"></el-table-column>
                <el-table-column prop="beds" label="床位数"></el-table-column>
                <el-table-column prop="" label="执业许可证">
                    <template slot-scope="scope">
                        <img :src='scope.row.img' @click="lookImg(scope.row.img)" style="width: 90px;height:70px"/>
                    </template>
                </el-table-column>
                <el-table-column label="操作" width="60px" v-if="userType == '回收物流'">
                  <template slot-scope="scope">
                    <div style="text-align: center">
                        <el-dropdown>
                          <span class="el-dropdown-link">
                            <i class="el-icon-more" style="font-size: 22px"></i>
                          </span>
                          <el-dropdown-menu slot="dropdown">
                            <el-dropdown-item>
                                <p @click="lookJoinList(scope.row)">交接记录</p>
                            </el-dropdown-item>
                            <el-dropdown-item>
                              <p @click="lookDepartment(scope.row.id)">查看科室</p>
                            </el-dropdown-item>
                            <el-dropdown-item>
                              <p @click="setAdminUser(scope.row)">设置管理员</p>
                            </el-dropdown-item>
                            <el-dropdown-item>
                              <p @click="lookBarCode(scope.row.id)">打印条码</p>
                            </el-dropdown-item>
                            <el-dropdown-item>
                              <p @click="handleEdit(scope.$index, scope.row)">编辑</p>
                            </el-dropdown-item>
                            <el-dropdown-item>
                              <p @click="handleDelete(scope.$index, scope.row.id)">删除</p>
                            </el-dropdown-item>
                          </el-dropdown-menu>
                        </el-dropdown>
                    </div>
                  </template>
                </el-table-column>
                <el-table-column label="操作" width="60px" fixed="right" v-if="userType == '回收利用企业'">
                  <template slot-scope="scope">
                      <div style="text-align: center">
                        <el-dropdown>
                          <span class="el-dropdown-link">
                            <i class="el-icon-more" style="font-size: 22px"></i>
                          </span>
                          <el-dropdown-menu slot="dropdown">
                            <el-dropdown-item>
                                <p @click="lookJoinList(scope.row)">交接记录</p>
                            </el-dropdown-item>
                            <el-dropdown-item>
                              <p @click="setAdminUser(scope.row)">设置管理员</p>
                            </el-dropdown-item>
                            
                            <el-dropdown-item>
                              <p @click="handleDelete(scope.$index, scope.row.id)">删除</p>
                            </el-dropdown-item>
                          </el-dropdown-menu>
                        </el-dropdown>
                    </div>
                  </template>
                </el-table-column>
                <el-table-column label="操作" width="60px" fixed="right" v-if="userType == '省管理单位' || userType == '市管理单位' || userType == '区管理单位' || userType == '全国管理单位'">
                  <template slot-scope="scope">
                      <div style="text-align: center">
                        <el-dropdown>
                          <span class="el-dropdown-link">
                            <i class="el-icon-more" style="font-size: 22px"></i>
                          </span>
                          <el-dropdown-menu slot="dropdown">
                            <el-dropdown-item>
                                <p @click="lookyiyuanJoinList(scope.row)">交接记录</p>
                            </el-dropdown-item>
                            <el-dropdown-item>
                              <p @click="setAdminUser(scope.row)">设置管理员</p>
                            </el-dropdown-item>
                            
                            <el-dropdown-item>
                              <p @click="handleDelete(scope.$index, scope.row.id)">删除</p>
                            </el-dropdown-item>
                          </el-dropdown-menu>
                        </el-dropdown>
                    </div>
                  </template>
                </el-table-column>
            </el-table>
            <div class="pafinationBox">
              <div>
                <el-button type="primary" @click="clickAddOrUp" v-if="userType == '回收物流'">添加医疗机构</el-button>
                <el-button type="warning" @click="delAll" v-if="userType == '回收物流'">批量删除</el-button>
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
          width="800px"
          center>
          <div class="dialogMain">
              <el-form ref="addForm" :model="addForm" style="width: 85%" label-width="110px">
                  <el-form-item label="登记号" placeholder="请填写登记号">
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
                  <el-form-item label="监管单位" v-if="userType == '回收物流'" required>
                    <el-autocomplete
                      class="inline-input"
                      v-model="addForm.supervisionunit"
                      :fetch-suggestions="querySearchAsync"
                      placeholder="请输入监管单位"
                      @select="handleSelect"
                    ></el-autocomplete>
                  </el-form-item>
                  <el-form-item label="法人" placeholder="请填写法人" required>
                      <el-input v-model="addForm.leadername"></el-input>
                  </el-form-item>
                  <el-form-item label="法人电话" placeholder="请填写电话" required>
                      <el-input v-model="addForm.leaderphone"></el-input>
                  </el-form-item>
                  <el-form-item label="床位数" required>
                    <el-input-number v-model="addForm.beds"></el-input-number>
                  </el-form-item>
                  <el-form-item label="执业许可证">
                    <div>
                        <aliUpFile :file="addForm.img" server='/recovery' directory='ehospital' fileNum="1" @fileDel='fileDels' @upFileData="upFileDatas"></aliUpFile>
                    </div>
                  </el-form-item>
                  <el-form-item label="省市区" required>
                      <span>{{procityplat}}</span>
                  </el-form-item>
                  <el-form-item label="详细地址" required>
                      <span>{{addForm.address}}</span>
                  </el-form-item>
                  <el-form-item>
                      <div style="width: 100%;height:300px">
                        <serchMap ref="serchMap" :pointlng="point.lng" :pointlat="point.lat" @serchData="getSerchData"></serchMap>
                      </div>
                  </el-form-item>
              </el-form>
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
import {regionDataPlus,CodeToText} from 'element-china-area-data'
import aliUpFile from "../../components/aliuploadrenyuan"
import selectAddress from "../../components/selectprovince"
import serchMap from '../../components/serchmap';
  export default {
    components:{
      serchMap,
        aliUpFile,
        selectAddress
    },
    data() {
      return {
        serchFlag: false,
        userType: sessionStorage.getItem('userType'),
        addressLevel: sessionStorage.getItem('addressLevel'),
        page: 1,
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
          province: '',
          city: '',
          plat: '',
        },
        //类型筛选列表
        typeList:[],
        belongLevelList:[],
        levelList:[],
        supervisionunitList: [],
        //弹框title
        addOrUpTit:'',
        //新增与编辑form
        addForm:{
            id: null,
            item: null,
            leadername: null,
            leaderphone: null,
            address: "未选择",
            type: null,
            code: null,
            level: null,
            note: null,
            supervisionunitid: null,
            img: '',
            belonglevel: null,
            province: null,
            city: null,
            plat: null,
            longitude: null,
            latitude: null,
        },
        point:{
          lng: null,
          lat: null
        },
        //表格数据
        tableData: [],
        //批量选择
        multipleSelection: [],
        centerDialogVisible: false,
        innerVisible:false,
        endPickerOptions: {
              disabledDate(time) {
                  return time.getTime() < Date.now() - 8.64e7;
              }
        },
        //查看大图
        lookImgIf: false,
        imgLook:'',
        timeout:'',
        //省市区筛选列表
        selectData:{
          proname: null,
          cityname: null,
          platname: null,
        },
        // clearSelectFlag: 0,

        procityplat: "未选择-未选择-未选择",
        options: regionDataPlus,
        selectedOptions: []
      };
    },
    mounted() {
      this.getData();
      this.getSelectList();
      console.log(1111111111111)
    },
    methods: {
      getProvinceData(data){
        this.form.province = data.label;
        this.selectList();
      },
      getCityData(data){
        this.form.city = data.label;
        this.selectList();
      },
      getPlatData(data){
        this.form.plat = data.label;
        this.selectList();
      },
      handleChange (value) {
          this.form.province = CodeToText[value[0]];
          if(value[1] != ''){
            this.form.city = CodeToText[value[1]];
          }
          if(value[2] != ''){
            this.form.plat = CodeToText[value[2]];
          }
          console.log(CodeToText[value[0]]);
          this.selectList();
      },
      //接收地址信息
      getSerchData(data){
        console.log(data);
        this.addForm.address = data.address;
        this.procityplat = data.addressComponents.province+"-"+data.addressComponents.city+'-'+data.addressComponents.district;
        this.addForm.province = data.addressComponents.province;
        this.addForm.city = data.addressComponents.city;
        this.addForm.plat = data.addressComponents.district;
        this.addForm.longitude = data.point.lng;
        this.addForm.latitude = data.point.lat;
      },
      //导出
      exportBarCode(row){
        window.location.href = "http://hrt.chinagrp.org.cn/recovery/fileDownload/excel"; 
      },
      querySearchAsync(queryString, cb) {
        var restaurants = this.supervisionunitList;
        var results = queryString ? restaurants.filter(this.createStateFilter(queryString)) : restaurants;

        clearTimeout(this.timeout);
        this.timeout = setTimeout(() => {
          cb(results);
        }, 1000 * Math.random());
      },
      createStateFilter(queryString) {
        return (supervisionunitList) => {
          return (supervisionunitList.value.toLowerCase().indexOf(queryString.toLowerCase()) !== -1);
        };
      },
      handleSelect(item) {
        console.log(item);
        this.addForm.supervisionunitid = item.id;
      },
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
      lookJoinList(row){
        this.$router.replace({
              path:'/treatmentPlantHospitalJoin',
              query:{
                  name: row.name,
                  id: row.id,
                  flag: 0
              }
          })
      },
      lookyiyuanJoinList(row){
         this.$router.replace({
              path:'/joinList',
              query:{
                  name: row.name,
                  id: row.id,
                  flag: 0
              }
          })
      },
      //设置管理员
      setAdminUser(row){
        this.$router.replace({
          path:'/adminUser',
          query:{
            belongid: row.id,
            gsName: row.name,
            type: '医疗机构'
          }
        })
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
      //获取筛选列表
      getSelectList(){
        var _this = this;
        var obj = {"value":'',"text":"全部"};
        this.$ajax.get("/recovery/hospital/typeList").then(function(response){
              console.log("type列表",response);
              _this.typeList = response.data.list;
              _this.typeList.unshift(obj);
        })
        this.$ajax.get("/recovery/supervisionunit/selectAll").then(function(response){
          console.log("监管单位列表",response)
              _this.supervisionunitList = [];
              for(let i=0;i<response.data.list.length;i++){
                _this.supervisionunitList.push({'value':response.data.list[i].name,'id':response.data.list[i].id})
              }
        })
        if(this.userType != '回收物流'){
          this.$ajax.get("/recovery/hospital/belonglevelList",{
            params:{
                level:  sessionStorage.getItem('addressLevel'),
            }
          }).then(function(response){
              console.log("所属级别列表",response);
                _this.belongLevelList = response.data.list;
                _this.belongLevelList.unshift(obj);
          })
        }
      },
      //获取级别列表
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
        var params,url;
        console.log(212)
        if(this.userType != '回收物流'){
          url = "/recovery/hospital/supervisionunitHospital"
          // if(this.addressLevel == '区'){
          //   this.selectData.cityname = sessionStorage.getItem('userFatherAddress');
          // }
          if(this.userType == '省管理单位'){
            params = {
              page: this.page,
              rows: this.pageSize,
              address: sessionStorage.getItem('userAddress'),
              name: this.form.item,
              level:  this.addressLevel,
              type: this.form.type,
              hospitallevel: this.form.level,
              belonglevel: this.form.belonglevel,
              provinceaddress: sessionStorage.getItem("userFatherAddress"),
            }
          }else{
            params = {
              page: this.page,
              rows: this.pageSize,
              address: sessionStorage.getItem('userAddress'),
              name: this.form.item,
              level:  this.addressLevel,
              type: this.form.type,
              hospitallevel: this.form.level,
              belonglevel: this.form.belonglevel,
              // provinceaddress: this.form.province,
              provinceaddress: sessionStorage.getItem("userFatherAddress"),
              cityaddress: sessionStorage.getItem("userFatherAddress"),
              // plataddress: this.form.plat,
              // province: this.form.province,
              // city: this.form.city,
              // plat: this.form.plat
            }
          }
          
        }else if(this.userType == '回收物流'){
            url = "/recovery/hospital/selectVByPage"
            params = {
              page: this.page,
              rows: this.pageSize,
              name: this.form.item,
              leadername: this.form.leadername,
              type: this.form.type,
              level: this.form.level,
              transportcompanyid: sessionStorage.getItem('belongId'),
              code: this.form.code,
              phone: this.form.phone,
            }
        }
        console.log(url)
        this.$ajax.get(url,{
            params: params
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
      // getAddressData(data){
      //   console.log("接收",data);
      //   this.selectData = data;
      //   this.getData();
      // },
      //查询
      selectList(){
        this.page = 1;
        this.getData();
      },
      getLevel(val){
        var obj = {};
        obj = this.supervisionunitList.find(function(item) {//obj是选中的对象
            return item.id === val;
        })
        console.log(obj)
        this.addForm.belonglevel = obj.level;
      },
      //清空筛选
      clearSelect(){
            this.form = {
              item:'',
              leadername:'',
              type: '',
              level:'',
              code:'',
              phone:''
            }
            this.selectedOptions = [];
            this.selectData = {
              proname: null,
              cityname: null,
              platname: null,
            },
            // this.clearSelectFlag = 1;
            this.getData();
      },
      //清空创建编辑form
      clearForm(){
        this.addForm = {
            id: null,
            item: null,
            leadername: null,
            leaderphone: null,
            address: "未选择",
            type: null,
            code: null,
            level: null,
            note: null,
            img: '',
            supervisionunitid: null,
            supervisionunit: null,
            beds: null,
            level: null,
            transportcompanyid:null,
            longitude: null,
            latitude: null,
            province: null,
            city: null,
            plat: null,
        } 
        this.$refs.serchMap.clearAll();
        this.procityplat="未选择-未选择-未选择";
        
      },
      //点击新增/编辑
      clickAddOrUp(){
        this.centerDialogVisible = true;
        this.addOrUpTit='创建医疗机构';
        this.clearForm();
      },
      //编辑
      handleEdit(index, row) {
        console.log(index, row);
        var that = this;
        this.centerDialogVisible = true;
        this.addOrUpTit = "编辑医疗机构";
        that.addForm = {
            id: row.id,
            name: row.name,
            leadername: row.leadername,
            leaderphone: row.leaderphone,
            address: row.address,
            type: row.type,
            code: row.code,
            level: row.level,
            note: row.note,
            supervisionunitid: row.supervisionunitid,
            img: row.img,
            transportcompanyid: row.transportcompanyid,
            beds: row.beds,
            belonglevel: row.belonglevel,
            supervisionunit: row.supervisionunit.name,
            province: row.province,
            city: row.city,
            plat: row.plat,
            longitude: row.longitude,
            latitude: row.latitude,
        }
        this.point.lng = row.longitude;
        this.point.lat = row.latitude;
        this.procityplat = row.province+'-'+row.city+'-'+row.plat;
      },
      // 新增
      addSaveForm(){
        console.log(this.addForm)
        var that = this;
        if(this.addForm.address == "未选择"){
          this.addForm.address = null;
        }
        if(this.addForm.name == null || this.addForm.leadername == null || this.addForm.leaderphone == null || this.addForm.address == null){
          this.$message.error("*为必填项！");
          return;
        }
        if(this.userType != '回收物流'){
            this.addForm.supervisionunitid = sessionStorage.getItem('belongId');
        }else if(this.userType == '回收物流'){
            this.addForm.transportcompanyid = sessionStorage.getItem('belongId');
            console.log(this.addForm.transportcompanyid);
        }
        var params = this.$qs.stringify(this.addForm, { indices: false })
        if(this.addOrUpTit == "创建医疗机构"){
          this.$ajax.post("/recovery/hospital//insert ",params).then(function(response){
            if(response.data.status == 260){
              that.centerDialogVisible = false;
              that.$message.success("添加成功")
              that.getData();
              //清空form
              that.clearForm();
            }
          })
        }else{
          this.$ajax.put("/recovery/hospital/update",params).then(function(response){
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
      //导入医院
      //触发upfile
      add() {
          this.$refs.upFile.click();
      },
      fileChanged() {
          const list = this.$refs.upFile.files;
          
          console.log(list)
          if (list.length == 0) {
              alert("请选择文件");
              return;
          }
          var that = this;
          for(var i=0;i<list.length;i++){
              var formData = new FormData();
              var f=list[i];
              console.log(f)
              if(f.size > 20971520){
                that.$message.warning("上传文件不得大于20M！")
                return;
              }
              formData.append("excelFile",f);
              formData.append("transportcompanyid",sessionStorage.getItem('belongId'));
              this.$ajax.post("/recovery/addHospitalExcel/add", formData, {
                headers: {'Content-Type': 'multipart/form-data'}
              }).then(function(res){
                  that.getData();
                  that.$refs.upFile.value = '';
                  if(res.data.status == 260){
                    that.$message.success('导入医院成功');
                  }else{
                    that.$message.error('导入医院失败,请重试！');
                  }
                  
              })
          }
      },
      //批量删除
      delAll(){
        if(this.multipleSelection.length < 1){
            return this.$message.success("请先选择删除对象");
        }
        this.$confirm('此操作将永久删除选中的医疗机构, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          if(this.multipleSelection.length == this.tableData.length){
            this.page = Number(this.page)-1;
          }
          var that = this;
          this.$ajax.delete("/recovery/hospital/deletes",{data:this.multipleSelection}).then(function(response){
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
        this.$confirm('此操作将删除该医疗机构, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          console.log(index, row);
          if(this.tableData.length == 1){
            this.page = Number(this.page)-1;
          }
          var that = this;
          this.$ajax.delete("/recovery/hospital/delete",{params:{
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
    height: 90vh;
    background: #F5F7F9;
}
.selectBox{
  width: 95%;
  margin: auto;
}
.tableBox{
  width: 95%;
  height: 88%;
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
