webpackJsonp([46],{VBf4:function(t,e){},ni1c:function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var i={data:()=>({page:1,pageSize:10,totalNum:100,form:{itemName:""},addOrUpTit:"",addForm:{id:null,name:null,note:null,treatmentplantid:sessionStorage.getItem("belongId")},tableData:[],multipleSelection:[],centerDialogVisible:!1,endPickerOptions:{disabledDate:t=>t.getTime()<Date.now()-864e5}}),mounted(){this.getData()},watch:{addForm:function(t,e){console.log(t)}},methods:{getData(){var t=this;this.$ajax.get("/recovery/cmaterialcategory/selectByPage",{params:{page:this.page,rows:this.pageSize,treatmentplantid:sessionStorage.getItem("belongId"),name:this.form.itemName}}).then(function(e){console.log(e),260==e.data.status?(t.tableData=e.data.list,t.totalNum=e.data.info.total):(t.tableData=[],t.totalNum=0)})},changePage(t){this.page=t,this.getData()},selectList(){this.page=1,this.getData()},clearSelect(){this.form={itemName:""},this.getData()},clearForm(){this.addForm={id:null,name:null,note:null,treatmentplantid:sessionStorage.getItem("belongId")}},clickAddOrUp(){this.centerDialogVisible=!0,this.addOrUpTit="创建破碎料类别",this.clearForm()},handleEdit(t,e){console.log(t,e);this.centerDialogVisible=!0,this.addOrUpTit="编辑破碎料类别",this.addForm={id:e.id,name:e.name,note:e.note,stock:e.stock,treatmentplantid:e.treatmentplantid}},addSaveForm(t){if(console.log(this.addForm),null!=this.addForm.name){var e=this,a=this.$qs.stringify(this.addForm,{indices:!1});"创建破碎料类别"==this.addOrUpTit?this.$ajax.post("/recovery/cmaterialcategory/insert",a).then(function(t){260==t.data.status&&(e.centerDialogVisible=!1,e.$message.success("添加成功"),e.getData(),e.clearForm())}):this.$ajax.put("/recovery/cmaterialcategory/update",a).then(function(t){260==t.data.status&&(e.centerDialogVisible=!1,e.$message.success("编辑成功"),e.getData(),e.clearForm())})}else this.$message.error("*为必填项！")},delAll(){if(this.multipleSelection.length<1)return this.$message.success("请先选择删除对象");this.$confirm("此操作将永久删除选中的破碎料类别, 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(()=>{this.multipleSelection.length==this.tableData.length&&(this.page=Number(this.page)-1);var t=this;this.$ajax.delete("/recovery/cmaterialcategory/deletes",{data:this.multipleSelection}).then(function(e){260==e.data.status&&(t.$message.success("删除成功"),t.getData())})}).catch(()=>{this.$message({type:"info",message:"已取消删除"})})},handleSelectionChange(t){this.multipleSelection=[];for(var e=0;e<t.length;e++)this.multipleSelection.push(t[e].id);console.log(this.multipleSelection)},handleDelete(t,e){this.$confirm("此操作将删除该破碎料类别, 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(()=>{console.log(t,e),1==this.tableData.length&&(this.page=Number(this.page)-1);var a=this;this.$ajax.delete("/recovery/cmaterialcategory/delete",{params:{id:e}}).then(function(t){260==t.data.status&&(a.$message.success("删除成功"),a.getData())})}).catch(()=>{this.$message({type:"info",message:"已取消删除"})})}}},l={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{attrs:{id:"yudingBox"}},[a("div",{staticClass:"selectBox"},[a("el-form",{ref:"form",attrs:{model:t.form,"label-width":"90px"}},[a("el-form-item",{attrs:{label:"破碎料类别"}},[a("el-input",{on:{change:t.selectList},model:{value:t.form.itemName,callback:function(e){t.$set(t.form,"itemName",e)},expression:"form.itemName"}})],1),t._v(" "),a("el-form-item",{attrs:{"label-width":"10px"}},[a("el-button",{staticStyle:{"font-size":"14px"},attrs:{type:"warning",size:"small"},on:{click:t.clearSelect}},[t._v("清空筛选")])],1)],1)],1),t._v(" "),a("div",{staticClass:"tableBox"},[a("el-table",{staticStyle:{width:"100%"},attrs:{data:t.tableData},on:{"selection-change":t.handleSelectionChange}},[a("el-table-column",{attrs:{type:"selection",width:"55"}}),t._v(" "),a("el-table-column",{attrs:{prop:"name",label:"破碎料类别"}}),t._v(" "),a("el-table-column",{attrs:{prop:"note",label:"备注"}}),t._v(" "),a("el-table-column",{attrs:{label:"操作",width:"200"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("el-button",{attrs:{size:"mini"},on:{click:function(a){return t.handleEdit(e.$index,e.row)}}},[t._v("编辑")]),t._v(" "),a("el-button",{attrs:{size:"mini",type:"danger"},on:{click:function(a){return t.handleDelete(e.$index,e.row.id)}}},[t._v("删除")])]}}])})],1),t._v(" "),a("div",{staticClass:"pafinationBox"},[a("div",[a("el-button",{attrs:{type:"primary"},on:{click:t.clickAddOrUp}},[t._v("创建破碎料类别")]),t._v(" "),a("el-button",{attrs:{type:"warning"},on:{click:t.delAll}},[t._v("批量删除")])],1),t._v(" "),a("el-pagination",{attrs:{background:"",layout:"prev, pager, next","page-size":t.pageSize,total:t.totalNum},on:{"current-change":t.changePage}})],1)],1),t._v(" "),a("el-dialog",{attrs:{title:t.addOrUpTit,visible:t.centerDialogVisible,width:"50%",center:""},on:{"update:visible":function(e){t.centerDialogVisible=e}}},[a("div",{staticClass:"dialogMain"},[a("el-form",{ref:"addForm",attrs:{model:t.addForm,"label-width":"100px"}},[a("el-form-item",{attrs:{label:"破碎料类别",required:""}},[a("el-input",{model:{value:t.addForm.name,callback:function(e){t.$set(t.addForm,"name",e)},expression:"addForm.name"}})],1),t._v(" "),a("el-form-item",{attrs:{label:"备注"}},[a("el-input",{attrs:{placeholder:"请输入类别备注"},model:{value:t.addForm.note,callback:function(e){t.$set(t.addForm,"note",e)},expression:"addForm.note"}})],1)],1)],1),t._v(" "),a("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(e){t.centerDialogVisible=!1}}},[t._v("取 消")]),t._v(" "),a("el-button",{attrs:{type:"primary"},on:{click:function(e){return t.addSaveForm()}}},[t._v("确 定")])],1)])],1)},staticRenderFns:[]};var n=a("VU/8")(i,l,!1,function(t){a("VBf4")},"data-v-1cade6b3",null);e.default=n.exports}});
//# sourceMappingURL=46.88fd641c6a5477dd3486.js.map