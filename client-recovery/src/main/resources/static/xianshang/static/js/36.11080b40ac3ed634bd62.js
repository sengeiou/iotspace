webpackJsonp([36],{AAqx:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var l={filters:{formatDate:function(e){var t=new Date(e),a=t.getFullYear(),l=t.getMonth()+1,i=t.getDate(),s=t.getHours(),r=t.getMinutes();return l<10&&(l="0"+l),s<10&&(s="0"+s),r<10&&(r="0"+r),a+"-"+l+"-"+i+" "+s+":"+r}},data:()=>({page:1,pageSize:10,totalNum:100,form:{type:"",starttime:"",endtime:"",source:""},addOrUpTit:"",addForm:{id:null,cmaterialcategoryid:null,weight:null,number:null,treatmentplantid:sessionStorage.getItem("belongId")},tableData:[],tableTypes:[],sourceList:[],multipleSelection:[],centerDialogVisible:!1,endPickerOptions:{disabledDate:e=>e.getTime()<Date.now()-864e5}}),mounted(){this.getData(),this.getSelectList()},watch:{addForm:function(e,t){console.log(e)}},methods:{getSelectList(){var e=this,t={id:"",name:"全部"};this.$ajax.get("/recovery/cmaterialcategory/selectByTreatmentplantid",{params:{treatmentplantid:sessionStorage.getItem("belongId")}}).then(function(a){console.log("type列表",a),e.tableTypes=a.data.list,e.tableTypes.unshift(t)});var a={value:"",text:"全部"};this.$ajax.get("/recovery/cmaterialin/sourceList",{params:{treatmentplantid:sessionStorage.getItem("belongId")}}).then(function(t){console.log("来源列表",t),e.sourceList=t.data.list,e.sourceList.unshift(a)})},getData(){var e=this;null==this.form.starttime&&(this.form.starttime=""),null==this.form.endtime&&(this.form.endtime=""),this.$ajax.get("/recovery/cmaterialin/selectVByPage",{params:{page:this.page,rows:this.pageSize,treatmentplantid:sessionStorage.getItem("belongId"),source:this.form.source,cmaterialcategoryid:this.form.type,begintime:""==this.form.starttime?"":new Date(this.form.starttime)/1e3*1e3,endtime:""==this.form.endtime?"":new Date(this.form.endtime)/1e3*1e3}}).then(function(t){console.log(t),260==t.data.status?(e.tableData=t.data.list,e.totalNum=t.data.info.total):(e.tableData=[],e.totalNum=0)})},changePage(e){this.page=e,this.getData()},selectList(){this.page=1,this.getData()},clearSelect(){this.form={type:"",starttime:"",endtime:"",source:""},this.getData()},clearForm(){this.addForm={id:null,cmaterialcategoryid:null,weight:null,number:null,source:null,treatmentplantid:sessionStorage.getItem("belongId")}},clickAddOrUp(){this.centerDialogVisible=!0,this.addOrUpTit="添加破碎料",this.clearForm()},handleEdit(e,t){console.log(e,t);this.centerDialogVisible=!0,this.addOrUpTit="编辑破碎料",this.addForm={id:t.id,cmaterialcategoryid:t.cmaterialcategoryid,weight:t.weight,source:t.source,number:t.number,treatmentplantid:t.treatmentplantid,time:t.time}},addSaveForm(e){if(console.log(this.addForm),null!=this.addForm.cmaterialcategoryid){var t=this,a=this.$qs.stringify(this.addForm,{indices:!1});"添加破碎料"==this.addOrUpTit?this.$ajax.post("/recovery/cmaterialin/insert",a).then(function(e){260==e.data.status&&(t.centerDialogVisible=!1,t.$message.success("添加成功"),t.getData(),t.clearForm())}):this.$ajax.put("/recovery/cmaterialin/update",a).then(function(e){260==e.data.status&&(t.centerDialogVisible=!1,t.$message.success("编辑成功"),t.getData(),t.clearForm())})}else this.$message.error("*为必填项！")},delAll(){if(this.multipleSelection.length<1)return this.$message.success("请先选择删除对象");this.$confirm("此操作将永久删除选中的破碎料, 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(()=>{this.multipleSelection.length==this.tableData.length&&(this.page=Number(this.page)-1);var e=this;this.$ajax.delete("/recovery/cmaterialin/deletes",{data:this.multipleSelection}).then(function(t){260==t.data.status&&(e.$message.success("删除成功"),e.getData())})}).catch(()=>{this.$message({type:"info",message:"已取消删除"})})},handleSelectionChange(e){this.multipleSelection=[];for(var t=0;t<e.length;t++)this.multipleSelection.push(e[t].id);console.log(this.multipleSelection)},handleDelete(e,t){this.$confirm("此操作将删除该破碎料, 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(()=>{console.log(e,t),1==this.tableData.length&&(this.page=Number(this.page)-1);var a=this;this.$ajax.delete("/recovery/cmaterialin/delete",{params:{id:t}}).then(function(e){260==e.data.status&&(a.$message.success("删除成功"),a.getData())})}).catch(()=>{this.$message({type:"info",message:"已取消删除"})})}}},i={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{attrs:{id:"yudingBox"}},[a("div",{staticClass:"selectBox"},[a("el-form",{ref:"form",attrs:{model:e.form,"label-width":"60px"}},[a("el-form-item",{attrs:{label:"破碎料类别","label-width":"90px"}},[a("el-select",{attrs:{placeholder:"请选择类别"},on:{change:e.selectList},model:{value:e.form.type,callback:function(t){e.$set(e.form,"type",t)},expression:"form.type"}},e._l(e.tableTypes,function(e,t){return a("el-option",{key:t,attrs:{label:e.name,value:e.id}})}),1)],1),e._v(" "),a("el-form-item",{attrs:{label:"来源"}},[a("el-select",{attrs:{placeholder:"请选择来源"},on:{change:e.selectList},model:{value:e.form.source,callback:function(t){e.$set(e.form,"source",t)},expression:"form.source"}},e._l(e.sourceList,function(e,t){return a("el-option",{key:t,attrs:{label:e.text,value:e.value}})}),1)],1),e._v(" "),a("el-form-item",{attrs:{label:"时间"}},[a("el-col",{attrs:{span:11}},[a("el-date-picker",{staticStyle:{width:"100%"},attrs:{type:"date",placeholder:"起始日期","value-format":"yyyy/MM/dd"},on:{change:e.selectList},model:{value:e.form.starttime,callback:function(t){e.$set(e.form,"starttime",t)},expression:"form.starttime"}})],1),e._v(" "),a("el-col",{attrs:{span:11}},[a("el-date-picker",{staticStyle:{width:"100%"},attrs:{type:"date",placeholder:"结束日期","value-format":"yyyy/MM/dd"},on:{change:e.selectList},model:{value:e.form.endtime,callback:function(t){e.$set(e.form,"endtime",t)},expression:"form.endtime"}})],1)],1),e._v(" "),a("el-form-item",{attrs:{"label-width":"0px"}},[a("el-button",{staticStyle:{"font-size":"14px"},attrs:{type:"warning",size:"small"},on:{click:e.clearSelect}},[e._v("清空筛选")])],1)],1)],1),e._v(" "),a("div",{staticClass:"tableBox"},[a("el-table",{staticStyle:{width:"100%"},attrs:{data:e.tableData},on:{"selection-change":e.handleSelectionChange}},[a("el-table-column",{attrs:{prop:"time",label:"入库时间",sortable:""},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n                "+e._s(e._f("formatDate")(t.row.time))+"\n              ")]}}])}),e._v(" "),a("el-table-column",{attrs:{prop:"cmaterialcategory.name",label:"类别"}}),e._v(" "),a("el-table-column",{attrs:{prop:"source",label:"来源"}}),e._v(" "),a("el-table-column",{attrs:{prop:"weight",label:"重量（千克）"}}),e._v(" "),a("el-table-column",{attrs:{prop:"number",label:"数量（袋）"}}),e._v(" "),a("el-table-column",{attrs:{prop:"note",label:"备注"}})],1),e._v(" "),a("div",{staticClass:"pafinationBox"},[a("div",[a("el-button",{attrs:{type:"primary"},on:{click:e.clickAddOrUp}},[e._v("添加破碎料")])],1),e._v(" "),a("el-pagination",{attrs:{background:"",layout:"prev, pager, next","page-size":e.pageSize,total:e.totalNum},on:{"current-change":e.changePage}})],1)],1),e._v(" "),a("el-dialog",{attrs:{title:e.addOrUpTit,visible:e.centerDialogVisible,width:"50%",center:""},on:{"update:visible":function(t){e.centerDialogVisible=t}}},[a("div",{staticClass:"dialogMain"},[a("el-form",{ref:"addForm",attrs:{model:e.addForm,"label-width":"100px"}},[a("el-form-item",{attrs:{label:"破碎料类别"}},[a("el-select",{attrs:{placeholder:"请选择破碎料类别"},model:{value:e.addForm.cmaterialcategoryid,callback:function(t){e.$set(e.addForm,"cmaterialcategoryid",t)},expression:"addForm.cmaterialcategoryid"}},e._l(e.tableTypes,function(e,t){return a("el-option",{directives:[{name:"show",rawName:"v-show",value:"全部"!=e.name,expression:"item.name != '全部'"}],key:t,attrs:{label:e.name,value:e.id}})}),1)],1),e._v(" "),a("el-form-item",{attrs:{label:"来源"}},[a("el-select",{attrs:{placeholder:"请选择破碎料来源"},model:{value:e.addForm.source,callback:function(t){e.$set(e.addForm,"source",t)},expression:"addForm.source"}},e._l(e.sourceList,function(e,t){return a("el-option",{directives:[{name:"show",rawName:"v-show",value:"全部"!=e.text,expression:"item.text != '全部'"}],key:t,attrs:{label:e.text,value:e.value}})}),1)],1),e._v(" "),a("el-form-item",{attrs:{label:"重量（kg）"}},[a("el-input",{attrs:{placeholder:e.weightText},model:{value:e.addForm.weight,callback:function(t){e.$set(e.addForm,"weight",t)},expression:"addForm.weight"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"数量（袋）"}},[a("el-input",{attrs:{placeholder:"请输入破碎料数量"},model:{value:e.addForm.number,callback:function(t){e.$set(e.addForm,"number",t)},expression:"addForm.number"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"备注"}},[a("el-input",{attrs:{placeholder:"请输入备注"},model:{value:e.addForm.note,callback:function(t){e.$set(e.addForm,"note",t)},expression:"addForm.note"}})],1)],1)],1),e._v(" "),a("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(t){e.centerDialogVisible=!1}}},[e._v("取 消")]),e._v(" "),a("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.addSaveForm()}}},[e._v("确 定")])],1)])],1)},staticRenderFns:[]};var s=a("VU/8")(l,i,!1,function(e){a("z1OP")},"data-v-466d42ae",null);t.default=s.exports},z1OP:function(e,t){}});
//# sourceMappingURL=36.11080b40ac3ed634bd62.js.map