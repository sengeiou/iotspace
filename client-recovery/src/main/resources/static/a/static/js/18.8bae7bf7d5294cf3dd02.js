webpackJsonp([18],{"HY+G":function(e,a){},ZxYz:function(e,a,t){"use strict";Object.defineProperty(a,"__esModule",{value:!0});var l={components:{aliUpFile:t("2R99").a},filters:{formatDate:function(e){var a=new Date(e),t=a.getFullYear(),l=a.getMonth()+1,i=a.getDate(),s=a.getHours(),o=a.getMinutes();return l<10&&(l="0"+l),s<10&&(s="0"+s),o<10&&(o="0"+o),t+"-"+l+"-"+i+" "+s+":"+o}},data:()=>({centerDialogVisible:!1,lookImgIf:!1,imgLook:"",addOrUpTit:"",addForm:{address:null,id:null,img:null,leadername:null,leaderphone:null,name:null,note:null},tableData:{address:null,id:null,img:null,leadername:null,leaderphone:null,name:null,note:null},typeList:[],supervisionunitList:[],levelList:[]}),mounted(){this.getData()},methods:{lookImg(e){this.imgLook=e,this.lookImgIf=!0},upFileDatas(e){this.addForm.img=e},fileDels(e){this.addForm.img=""},getData(){var e=this;this.$ajax.get("/recovery/transportcompany/selectByPrimaryKey",{params:{id:sessionStorage.getItem("belongId")}}).then(function(a){console.log(a),260==a.data.status?e.tableData=a.data.object:e.tableData=[]})},clearForm(){this.addForm={address:null,id:null,img:null,leadername:null,leaderphone:null,name:null,note:null}},handleEdit(e,a){console.log(e,a);this.centerDialogVisible=!0,this.addOrUpTit="编辑",this.addForm=this.tableData},addSaveForm(){console.log(this.addForm);var e=this,a=this.$qs.stringify(this.addForm,{indices:!1});this.$ajax.put("/recovery/transportcompany/update",a).then(function(a){260==a.data.status&&(e.centerDialogVisible=!1,e.$message.success("编辑成功"),e.getData(),e.clearForm())})}}},i={render:function(){var e=this,a=e.$createElement,t=e._self._c||a;return t("div",{attrs:{id:"yudingBox"}},[t("div",{staticClass:"selectBox"},[t("el-form",{ref:"form",attrs:{"label-width":"60px"}})],1),e._v(" "),t("div",{staticClass:"tableBox"},[t("div",{staticClass:"newBox"},[t("div",[t("span",[e._v("回收物流名称：")]),e._v(" "),t("span",[e._v(e._s(e.tableData.name))])]),e._v(" "),t("div",[t("span",[e._v("回收物流地址：")]),e._v(" "),t("span",[e._v(e._s(e.tableData.address))])]),e._v(" "),t("div",[t("span",[e._v("负责人：")]),e._v(" "),t("span",[e._v(e._s(e.tableData.leadername))])]),e._v(" "),t("div",[t("span",[e._v("负责人电话：")]),e._v(" "),t("span",[e._v(e._s(e.tableData.leaderphone))])]),e._v(" "),t("div",[t("span",[e._v("营业许可证：")]),e._v(" "),t("img",{staticStyle:{width:"90px",height:"70px"},attrs:{src:e.tableData.img},on:{click:function(a){return e.lookImg(e.tableData.img)}}})])]),e._v(" "),t("div",{staticClass:"pafinationBox"},[t("div",[t("el-button",{attrs:{type:"primary"},on:{click:e.handleEdit}},[e._v("编辑")])],1)])]),e._v(" "),t("el-dialog",{attrs:{title:e.addOrUpTit,visible:e.centerDialogVisible,width:"600px",center:""},on:{"update:visible":function(a){e.centerDialogVisible=a}}},[t("div",{staticClass:"dialogMain"},[t("el-form",{ref:"addForm",attrs:{model:e.addForm,"label-width":"110px"}},[t("el-form-item",{attrs:{label:"回收物流名称",placeholder:"请填写名称",required:""}},[t("el-input",{model:{value:e.addForm.name,callback:function(a){e.$set(e.addForm,"name",a)},expression:"addForm.name"}})],1),e._v(" "),t("el-form-item",{attrs:{label:"负责人",placeholder:"请填写负责人",required:""}},[t("el-input",{model:{value:e.addForm.leadername,callback:function(a){e.$set(e.addForm,"leadername",a)},expression:"addForm.leadername"}})],1),e._v(" "),t("el-form-item",{attrs:{label:"负责人电话",placeholder:"请填写电话"}},[t("el-input",{model:{value:e.addForm.leaderphone,callback:function(a){e.$set(e.addForm,"leaderphone",a)},expression:"addForm.leaderphone"}})],1),e._v(" "),t("el-form-item",{attrs:{label:"地址",required:""}},[t("el-input",{attrs:{placeholder:"请填写地址"},model:{value:e.addForm.address,callback:function(a){e.$set(e.addForm,"address",a)},expression:"addForm.address"}})],1)],1),e._v(" "),t("div",{staticClass:"iptBox"},[t("span",[e._v("营业执照")]),e._v(" "),t("div",[t("aliUpFile",{attrs:{file:e.addForm.img,server:"/recovery",directory:"etransportcompany",fileNum:"1"},on:{fileDel:e.fileDels,upFileData:e.upFileDatas}})],1)])],1),e._v(" "),t("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[t("el-button",{on:{click:function(a){e.centerDialogVisible=!1}}},[e._v("取 消")]),e._v(" "),t("el-button",{attrs:{type:"primary"},on:{click:function(a){return e.addSaveForm()}}},[e._v("确 定")])],1)]),e._v(" "),t("el-dialog",{attrs:{title:"",visible:e.lookImgIf,width:"600px",center:""},on:{"update:visible":function(a){e.lookImgIf=a}}},[t("div",{staticClass:"dialogMain"},[t("img",{staticStyle:{width:"100%"},attrs:{src:e.imgLook}})])])],1)},staticRenderFns:[]};var s=t("VU/8")(l,i,!1,function(e){t("HY+G")},"data-v-d6546e00",null);a.default=s.exports}});
//# sourceMappingURL=18.8bae7bf7d5294cf3dd02.js.map