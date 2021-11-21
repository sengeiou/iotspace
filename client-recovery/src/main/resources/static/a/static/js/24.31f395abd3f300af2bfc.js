webpackJsonp([24],{DglN:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var l={components:{aliUpFile:a("2R99").a},filters:{formatDate:function(e){var t=new Date(e),a=t.getFullYear(),l=t.getMonth()+1,i=t.getDate(),s=t.getHours(),n=t.getMinutes();return l<10&&(l="0"+l),s<10&&(s="0"+s),n<10&&(n="0"+n),a+"-"+l+"-"+i}},data:()=>({centerDialogVisible:!1,lookImgIf:!1,imgLook:"",supervisionunitName:sessionStorage.getItem("supervisionunitName"),addOrUpTit:"",addForm:{address:null,beds:null,belonglevel:null,code:null,id:null,img:null,leadername:null,leaderphone:null,level:null,name:null,note:null,supervisionunitid:null,transportcompanyid:null,type:null},tableData:{address:null,beds:null,belonglevel:null,code:null,id:null,img:null,leadername:null,leaderphone:null,level:null,name:null,note:null,supervisionunitid:null,transportcompanyid:null,type:null},typeList:[],supervisionunitList:[],levelList:[]}),mounted(){this.getData(),this.getSelectList()},methods:{lookImg(e){this.imgLook=e,this.lookImgIf=!0},upFileDatas(e){this.addForm.img=e},fileDels(e){this.addForm.img=""},getSelectList(){var e=this,t={value:"",text:"全部"};this.$ajax.get("/recovery/hospital/typeList").then(function(a){console.log("type列表",a),e.typeList=a.data.list,e.typeList.unshift(t)}),this.$ajax.get("/recovery/supervisionunit/selectAll").then(function(t){console.log("type列表",t),e.supervisionunitList=t.data.list})},getLevelList(e,t){1==t?(this.getData(),this.form.level=""):2==t&&(this.addForm.level=null);var a=this,l={value:"",text:"全部"};this.$ajax.get("/recovery/hospital/levelList",{params:{type:e}}).then(function(e){console.log("ji别列表",e),a.levelList=e.data.list,a.levelList.unshift(l)})},getData(){var e=this;this.$ajax.get("/recovery/hospital/selectVByPrimaryKey",{params:{id:sessionStorage.getItem("belongId")}}).then(function(t){console.log(t),260==t.data.status?e.tableData=t.data.object:e.tableData=[]})},clearForm(){this.addForm={address:null,beds:null,belonglevel:null,code:null,id:null,img:null,leadername:null,leaderphone:null,level:null,name:null,note:null,supervisionunitid:null,transportcompanyid:null}},handleEdit(e,t){console.log(e,t);this.centerDialogVisible=!0,this.addOrUpTit="编辑",this.addForm=this.tableData},addSaveForm(){console.log(this.addForm);var e=this,t=this.$qs.stringify(this.addForm,{indices:!1});this.$ajax.put("/recovery/hospital/update",t).then(function(t){260==t.data.status&&(e.centerDialogVisible=!1,e.$message.success("编辑成功"),e.getData(),e.clearForm())})}}},i={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{attrs:{id:"yudingBox"}},[a("div",{staticClass:"selectBox"},[a("el-form",{ref:"form",attrs:{"label-width":"60px"}})],1),e._v(" "),a("div",{staticClass:"tableBox"},[a("div",{staticClass:"newBox"},[a("div",[a("span",[e._v("监管单位：")]),e._v(" "),a("span",[e._v(e._s(e.supervisionunitName))])]),e._v(" "),a("div",[a("span",[e._v("登记号：")]),e._v(" "),a("span",[e._v(e._s(e.tableData.code))])]),e._v(" "),a("div",[a("span",[e._v("医疗机构名称：")]),e._v(" "),a("span",[e._v(e._s(e.tableData.name))])]),e._v(" "),a("div",[a("span",[e._v("医疗机构地址：")]),e._v(" "),a("span",[e._v(e._s(e.tableData.address))])]),e._v(" "),a("div",[a("span",[e._v("注册类型：")]),e._v(" "),a("span",[e._v(e._s(e.tableData.type))])]),e._v(" "),a("div",[a("span",[e._v("等级：")]),e._v(" "),a("span",[e._v(e._s(e.tableData.level))])]),e._v(" "),a("div",[a("span",[e._v("负责人：")]),e._v(" "),a("span",[e._v(e._s(e.tableData.leadername))])]),e._v(" "),a("div",[a("span",[e._v("负责人电话：")]),e._v(" "),a("span",[e._v(e._s(e.tableData.leaderphone))])]),e._v(" "),a("div",[a("span",[e._v("床位数：")]),e._v(" "),a("span",[e._v(e._s(e.tableData.beds))])]),e._v(" "),a("div",[a("span",[e._v("入驻系统时间：")]),e._v(" "),a("span",[e._v(e._s(e._f("formatDate")(e.tableData.time)))])]),e._v(" "),a("div",[a("span",[e._v("输液瓶（袋）回收企业：")]),e._v(" "),null==e.tableData.transportcompany.treatmentplant?a("span",[e._v(e._s(e.tableData.transportcompany.name))]):a("span",[e._v(e._s(e.tableData.transportcompany.treatmentplant.name))])]),e._v(" "),a("div",[a("span",[e._v("执业许可证：")]),e._v(" "),a("img",{staticStyle:{width:"90px",height:"70px"},attrs:{src:e.tableData.img},on:{click:function(t){return e.lookImg(e.tableData.img)}}})])]),e._v(" "),a("div",{staticClass:"pafinationBox"},[a("div",[a("el-button",{attrs:{type:"primary"},on:{click:e.handleEdit}},[e._v("编辑")])],1)])]),e._v(" "),a("el-dialog",{attrs:{title:e.addOrUpTit,visible:e.centerDialogVisible,width:"600px",center:""},on:{"update:visible":function(t){e.centerDialogVisible=t}}},[a("div",{staticClass:"dialogMain"},[a("el-form",{ref:"addForm",attrs:{model:e.addForm,"label-width":"110px"}},[a("el-form-item",{attrs:{label:"登记号",placeholder:"请填写登记号",required:""}},[a("el-input",{model:{value:e.addForm.code,callback:function(t){e.$set(e.addForm,"code",t)},expression:"addForm.code"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"医疗机构名称",placeholder:"请填写医疗机构名称",required:""}},[a("el-input",{model:{value:e.addForm.name,callback:function(t){e.$set(e.addForm,"name",t)},expression:"addForm.name"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"注册类型",required:""}},[a("el-select",{attrs:{placeholder:"请选择类型"},on:{change:function(t){return e.getLevelList(e.addForm.type,2)}},model:{value:e.addForm.type,callback:function(t){e.$set(e.addForm,"type",t)},expression:"addForm.type"}},e._l(e.typeList,function(e,t){return a("el-option",{directives:[{name:"show",rawName:"v-show",value:"全部"!=e.text,expression:"item.text != '全部'"}],key:t,attrs:{label:e.text,value:e.value}})}),1)],1),e._v(" "),a("el-form-item",{directives:[{name:"show",rawName:"v-show",value:null!=e.addForm.type,expression:"addForm.type != null"}],attrs:{label:"等级",required:""}},[a("el-select",{attrs:{placeholder:"请选择等级"},model:{value:e.addForm.level,callback:function(t){e.$set(e.addForm,"level",t)},expression:"addForm.level"}},e._l(e.levelList,function(e,t){return a("el-option",{directives:[{name:"show",rawName:"v-show",value:"全部"!=e.text,expression:"item.text != '全部'"}],key:t,attrs:{label:e.text,value:e.value}})}),1)],1),e._v(" "),a("el-form-item",{attrs:{label:"负责人",placeholder:"请填写负责人",required:""}},[a("el-input",{model:{value:e.addForm.leadername,callback:function(t){e.$set(e.addForm,"leadername",t)},expression:"addForm.leadername"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"负责人电话",placeholder:"请填写电话"}},[a("el-input",{model:{value:e.addForm.leaderphone,callback:function(t){e.$set(e.addForm,"leaderphone",t)},expression:"addForm.leaderphone"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"地址",required:""}},[a("el-input",{attrs:{placeholder:"请填写地址"},model:{value:e.addForm.address,callback:function(t){e.$set(e.addForm,"address",t)},expression:"addForm.address"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"床位数",required:""}},[a("el-input-number",{model:{value:e.addForm.beds,callback:function(t){e.$set(e.addForm,"beds",t)},expression:"addForm.beds"}})],1)],1),e._v(" "),a("div",{staticClass:"iptBox"},[a("span",[e._v("执业许可证")]),e._v(" "),a("div",[a("aliUpFile",{attrs:{file:e.addForm.img,server:"/recovery",directory:"ehospital",fileNum:"1"},on:{fileDel:e.fileDels,upFileData:e.upFileDatas}})],1)])],1),e._v(" "),a("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(t){e.centerDialogVisible=!1}}},[e._v("取 消")]),e._v(" "),a("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.addSaveForm()}}},[e._v("确 定")])],1)]),e._v(" "),a("el-dialog",{attrs:{title:"",visible:e.lookImgIf,width:"600px",center:""},on:{"update:visible":function(t){e.lookImgIf=t}}},[a("div",{staticClass:"dialogMain"},[a("img",{staticStyle:{width:"100%"},attrs:{src:e.imgLook}})])])],1)},staticRenderFns:[]};var s=a("VU/8")(l,i,!1,function(e){a("hWEh")},"data-v-a7becab4",null);t.default=s.exports},hWEh:function(e,t){}});
//# sourceMappingURL=24.31f395abd3f300af2bfc.js.map