webpackJsonp([53],{"+gGo":function(t,e){},oQnw:function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var i={filters:{formatDate:function(t){var e=new Date(t),a=e.getFullYear(),i=e.getMonth()+1,l=e.getDate(),s=e.getHours(),n=e.getMinutes();return i<10&&(i="0"+i),s<10&&(s="0"+s),n<10&&(n="0"+n),a+"-"+i+"-"+l+" "+s+":"+n}},data:()=>({page:1,pageSize:10,totalNum:100,hospitalid:null,form:{item:""},addOrUpTit:"",addForm:{id:null,hospitalid:null,number:null,note:null,time:null},tableData:[],multipleSelection:[],centerDialogVisible:!1,innerVisible:!1,endPickerOptions:{disabledDate:t=>t.getTime()<Date.now()-864e5}}),mounted(){"医疗机构"==sessionStorage.getItem("userType")?this.hospitalid=sessionStorage.getItem("belongId"):this.hospitalid=this.$route.query.hospitalId,this.getData()},methods:{getData(){var t=this;this.$ajax.get("/recovery/barcode/selectVByPage",{params:{page:this.page,rows:this.pageSize,hospitalid:this.hospitalid}}).then(function(e){console.log(e),260==e.data.status?(t.tableData=e.data.list,t.totalNum=e.data.info.total):(t.tableData=[],t.totalNum=0)})},changePage(t){this.page=t,this.getData()},selectList(){this.page=1,this.getData()},clearForm(){this.addForm={id:null,hospitalid:this.hospitalid,number:null,note:null,time:null}},clickAddOrUp(){this.centerDialogVisible=!0,this.addOrUpTit="创建贴码",this.clearForm()},handleEdit(t,e){console.log(t,e);this.centerDialogVisible=!0,this.addOrUpTit="编辑贴码",this.addForm={id:e.id,hospitalid:e.hospitalid,number:e.number,note:e.note,time:e.time}},addSaveForm(){console.log(this.addForm);var t=this;if(null!=this.addForm.number){var e=this.$qs.stringify(this.addForm,{indices:!1});"创建贴码"==this.addOrUpTit?this.$ajax.post("/recovery/barcode//insert ",e).then(function(e){260==e.data.status&&(t.centerDialogVisible=!1,t.$message.success("添加成功"),t.getData(),t.clearForm())}):this.$ajax.put("/recovery/barcode/update",e).then(function(e){260==e.data.status&&(t.centerDialogVisible=!1,t.$message.success("编辑成功"),t.getData(),t.clearForm())})}else this.$message.error("*为必填项！")},exportBarCode(t){window.location.href="http://hrt.chinagrp.org.cn/recovery/barcode/barcodeExcel?hospitalid="+this.hospitalid+"&number="+t.number},delAll(){if(this.multipleSelection.length<1)return this.$message.success("请先选择删除对象");this.$confirm("此操作将永久删除选中的贴码, 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(()=>{this.multipleSelection.length==this.tableData.length&&(this.page=Number(this.page)-1);var t=this;this.$ajax.delete("/recovery/barcode/deletes",{data:this.multipleSelection}).then(function(e){260==e.data.status&&(t.$message.success("删除成功"),t.getData())})}).catch(()=>{this.$message({type:"info",message:"已取消删除"})})},handleSelectionChange(t){this.multipleSelection=[];for(var e=0;e<t.length;e++)this.multipleSelection.push(t[e].id);console.log(this.multipleSelection)},handleDelete(t,e){this.$confirm("此操作将删除该贴码, 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(()=>{console.log(t,e),1==this.tableData.length&&(this.page=Number(this.page)-1);var a=this;this.$ajax.delete("/recovery/barcode/delete",{params:{id:e}}).then(function(t){260==t.data.status&&(a.$message.success("删除成功"),a.getData())})}).catch(()=>{this.$message({type:"info",message:"已取消删除"})})}}},l={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{attrs:{id:"yudingBox"}},[a("div",{staticClass:"selectBox"}),t._v(" "),a("div",{staticClass:"tableBox"},[a("el-table",{staticStyle:{width:"100%"},attrs:{data:t.tableData},on:{"selection-change":t.handleSelectionChange}},[a("el-table-column",{attrs:{type:"selection",width:"55"}}),t._v(" "),a("el-table-column",{attrs:{prop:"time",label:"时间",width:"150",sortable:""},scopedSlots:t._u([{key:"default",fn:function(e){return[null==e.row.time?a("span"):a("span",[t._v(t._s(t._f("formatDate")(e.row.time)))])]}}])}),t._v(" "),a("el-table-column",{attrs:{prop:"hospital.name",label:"医疗机构名称"}}),t._v(" "),a("el-table-column",{attrs:{prop:"number",label:"生成数量"}}),t._v(" "),a("el-table-column",{attrs:{label:"操作",width:"220"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("el-button",{attrs:{size:"mini"},on:{click:function(a){return t.exportBarCode(e.row)}}},[t._v("导出")]),t._v(" "),a("el-button",{attrs:{size:"mini"},on:{click:function(a){return t.handleEdit(e.$index,e.row)}}},[t._v("编辑")]),t._v(" "),a("el-button",{attrs:{size:"mini",type:"danger"},on:{click:function(a){return t.handleDelete(e.$index,e.row.id)}}},[t._v("删除")])]}}])})],1),t._v(" "),a("div",{staticClass:"pafinationBox"},[a("div",[a("el-button",{attrs:{type:"primary"},on:{click:t.clickAddOrUp}},[t._v("添加贴码")]),t._v(" "),a("el-button",{attrs:{type:"warning"},on:{click:t.delAll}},[t._v("批量删除")])],1),t._v(" "),a("el-pagination",{attrs:{background:"",layout:"prev, pager, next","page-size":t.pageSize,total:t.totalNum},on:{"current-change":t.changePage}})],1)],1),t._v(" "),a("el-dialog",{attrs:{title:t.addOrUpTit,visible:t.centerDialogVisible,width:"600px",center:""},on:{"update:visible":function(e){t.centerDialogVisible=e}}},[a("div",{staticClass:"dialogMain"},[a("el-form",{ref:"addForm",attrs:{model:t.addForm,"label-width":"100px"}},[a("el-form-item",{attrs:{label:"生成数量",required:""}},[a("el-input",{attrs:{type:"number",min:"1",placeholder:"请填写生成数量"},model:{value:t.addForm.number,callback:function(e){t.$set(t.addForm,"number",e)},expression:"addForm.number"}})],1)],1)],1),t._v(" "),a("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(e){t.centerDialogVisible=!1}}},[t._v("取 消")]),t._v(" "),a("el-button",{attrs:{type:"primary"},on:{click:function(e){return t.addSaveForm()}}},[t._v("确 定")])],1)])],1)},staticRenderFns:[]};var s=a("VU/8")(i,l,!1,function(t){a("+gGo")},"data-v-01fadb48",null);e.default=s.exports}});
//# sourceMappingURL=53.48f36f4b66e5b3322b12.js.map