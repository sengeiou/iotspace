webpackJsonp([45],{eqkP:function(e,t){},uaYd:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var l={filters:{formatDate:function(e){var t=new Date(e),a=t.getFullYear(),l=t.getMonth()+1,i=t.getDate(),r=t.getHours(),o=t.getMinutes();return l<10&&(l="0"+l),r<10&&(r="0"+r),o<10&&(o="0"+o),a+"-"+l+"-"+i+" "+r+":"+o}},data:()=>({page:1,pageSize:10,totalNum:100,form:{type:"",starttime:"",endtime:"",source:""},addOrUpTit:"",addForm:{id:null,cmaterialcategoryid:null,cweight:null,cnumber:null,treatmentplantid:sessionStorage.getItem("belongId"),gmaterialcategoryid:null,weight:null,number:null},cweightText:"请输入破碎料重量",cWeightNumber:0,tableData:[],tableTypes:[],sourceList:[],multipleSelection:[],centerDialogVisible:!1,endPickerOptions:{disabledDate:e=>e.getTime()<Date.now()-864e5}}),mounted(){this.getData(),this.getSelectList()},methods:{getSelectList(){var e=this,t={id:"",name:"全部"};this.$ajax.get("/recovery/cmaterialcategory/selectByTreatmentplantid",{params:{treatmentplantid:sessionStorage.getItem("belongId")}}).then(function(a){console.log("type列表",a),e.tableTypes=a.data.list,e.tableTypes.unshift(t)}),this.$ajax.get("/recovery/gmaterialcategory/selectByTreatmentplantid",{params:{treatmentplantid:sessionStorage.getItem("belongId")}}).then(function(a){console.log("来源列表",a),e.sourceList=a.data.list,e.sourceList.unshift(t)})},getData(){var e=this;null==this.form.starttime&&(this.form.starttime=""),null==this.form.endtime&&(this.form.endtime=""),this.$ajax.get("/recovery/gmaterialin/selectVByPage",{params:{page:this.page,rows:this.pageSize,treatmentplantid:sessionStorage.getItem("belongId"),cmaterialcategoryid:this.form.type,gmaterialcategoryid:this.form.source,begintime:""==this.form.starttime?"":new Date(this.form.starttime)/1e3*1e3,endtime:""==this.form.endtime?"":new Date(this.form.endtime)/1e3*1e3}}).then(function(t){console.log(t),260==t.data.status?(e.tableData=t.data.list,e.totalNum=t.data.info.total):(e.tableData=[],e.totalNum=0)})},changePage(e){this.page=e,this.getData()},selectList(){this.page=1,this.getData()},clearSelect(){this.form={type:"",starttime:"",endtime:"",source:""},this.getData()},clearForm(){this.addForm={id:null,cmaterialcategoryid:null,weight:null,number:null,treatmentplantid:sessionStorage.getItem("belongId"),gmaterialcategoryid:null,cweight:null,cnumber:null,cnote:null,note:null}},getWeight(){var e=this;this.$ajax.get("/recovery/cmaterialcategory/selectByPrimaryKey",{params:{id:e.addForm.cmaterialcategoryid}}).then(function(t){console.log("破碎料库存",t),260==t.data.status?(e.cweightText="库存剩余"+t.data.object.stock+"kg",e.cWeightNumber=t.data.object.stock):(e.cweightText="请填写破碎料重量",e.cWeightNumber=0)})},clickAddOrUp(){this.centerDialogVisible=!0,this.addOrUpTit="添加颗粒料",this.cweightText="请填写破碎料重量",this.clearForm()},handleEdit(e,t){console.log(e,t);this.centerDialogVisible=!0,this.addOrUpTit="编辑颗粒料",this.addForm={id:t.id,cmaterialcategoryid:t.cmaterialcategoryid,weight:t.weight,number:t.number,treatmentplantid:t.treatmentplantid,time:t.time,gmaterialcategoryid:t.gmaterialcategoryid,cweight:t.cweight,cnumber:t.cnumber,cnote:t.cnote,note:t.note}},addSaveForm(e){if(console.log("addform",this.addForm),null!=this.addForm.cmaterialcategoryid&&null!=this.addForm.weight&&null!=this.addForm.number&&null!=this.addForm.gmaterialcategoryid&&null!=this.addForm.cweight&&null!=this.addForm.cnumber)if(Number(this.addForm.cweight)>Number(this.cWeightNumber))this.$message.error("加工破碎料重量库存不足！");else if(Number(this.addForm.cweight)<Number(this.addForm.weight))this.$message.error("生成颗粒料重量不可大于加工破碎料重量！");else{var t=this,a=this.$qs.stringify(this.addForm,{indices:!1});"添加颗粒料"==this.addOrUpTit?this.$ajax.post("/recovery/gmaterialin/insert",a).then(function(e){260==e.data.status&&(t.centerDialogVisible=!1,t.$message.success("添加成功"),t.getData(),t.clearForm())}):this.$ajax.put("/recovery/gmaterialin/update",a).then(function(e){260==e.data.status&&(t.centerDialogVisible=!1,t.$message.success("编辑成功"),t.getData(),t.clearForm())})}else this.$message.error("*为必填项！")},delAll(){if(this.multipleSelection.length<1)return this.$message.success("请先选择删除对象");this.$confirm("此操作将永久删除选中的颗粒料, 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(()=>{this.multipleSelection.length==this.tableData.length&&(this.page=Number(this.page)-1);var e=this;this.$ajax.delete("/recovery/gmaterialin/deletes",{data:this.multipleSelection}).then(function(t){260==t.data.status&&(e.$message.success("删除成功"),e.getData())})}).catch(()=>{this.$message({type:"info",message:"已取消删除"})})},handleSelectionChange(e){this.multipleSelection=[];for(var t=0;t<e.length;t++)this.multipleSelection.push(e[t].id);console.log(this.multipleSelection)},handleDelete(e,t){this.$confirm("此操作将删除该颗粒料, 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(()=>{console.log(e,t),1==this.tableData.length&&(this.page=Number(this.page)-1);var a=this;this.$ajax.delete("/recovery/gmaterialin/delete",{params:{id:t}}).then(function(e){260==e.data.status&&(a.$message.success("删除成功"),a.getData())})}).catch(()=>{this.$message({type:"info",message:"已取消删除"})})}}},i={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{attrs:{id:"yudingBox"}},[a("div",{staticClass:"selectBox"},[a("el-form",{ref:"form",attrs:{model:e.form,"label-width":"90px"}},[a("el-form-item",{attrs:{label:"破碎料类别"}},[a("el-select",{attrs:{placeholder:"请选择类别"},on:{change:e.selectList},model:{value:e.form.type,callback:function(t){e.$set(e.form,"type",t)},expression:"form.type"}},e._l(e.tableTypes,function(e,t){return a("el-option",{key:t,attrs:{label:e.name,value:e.id}})}),1)],1),e._v(" "),a("el-form-item",{attrs:{label:"颗粒料类别"}},[a("el-select",{attrs:{placeholder:"请选择来源"},on:{change:e.selectList},model:{value:e.form.source,callback:function(t){e.$set(e.form,"source",t)},expression:"form.source"}},e._l(e.sourceList,function(e,t){return a("el-option",{key:t,attrs:{label:e.name,value:e.id}})}),1)],1),e._v(" "),a("el-form-item",{attrs:{label:"时间"}},[a("el-col",{attrs:{span:11}},[a("el-date-picker",{staticStyle:{width:"100%"},attrs:{type:"date",placeholder:"起始日期","value-format":"yyyy/MM/dd"},on:{change:e.selectList},model:{value:e.form.starttime,callback:function(t){e.$set(e.form,"starttime",t)},expression:"form.starttime"}})],1),e._v(" "),a("el-col",{attrs:{span:11}},[a("el-date-picker",{staticStyle:{width:"100%"},attrs:{type:"date",placeholder:"结束日期","value-format":"yyyy/MM/dd"},on:{change:e.selectList},model:{value:e.form.endtime,callback:function(t){e.$set(e.form,"endtime",t)},expression:"form.endtime"}})],1)],1),e._v(" "),a("el-form-item",[a("el-button",{staticStyle:{"font-size":"14px"},attrs:{type:"warning",size:"small"},on:{click:e.clearSelect}},[e._v("清空筛选")])],1)],1)],1),e._v(" "),a("div",{staticClass:"tableBox"},[a("el-table",{staticStyle:{width:"100%"},attrs:{data:e.tableData},on:{"selection-change":e.handleSelectionChange}},[a("el-table-column",{attrs:{prop:"time",label:"入库时间",sortable:""},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n                "+e._s(e._f("formatDate")(t.row.time))+"\n              ")]}}])}),e._v(" "),a("el-table-column",{attrs:{prop:"cmaterialcategory.name",label:"破碎料类别"}}),e._v(" "),a("el-table-column",{attrs:{prop:"gmaterialcategory.name",label:"颗粒料类别"}}),e._v(" "),a("el-table-column",{attrs:{prop:"source",label:"来源"}}),e._v(" "),a("el-table-column",{attrs:{prop:"weight",label:"重量（千克）"}}),e._v(" "),a("el-table-column",{attrs:{prop:"number",label:"数量（袋）"}}),e._v(" "),a("el-table-column",{attrs:{prop:"note",label:"备注"}})],1),e._v(" "),a("div",{staticClass:"pafinationBox"},[a("div",[a("el-button",{attrs:{type:"primary"},on:{click:e.clickAddOrUp}},[e._v("添加颗粒料")])],1),e._v(" "),a("el-pagination",{attrs:{background:"",layout:"prev, pager, next","page-size":e.pageSize,total:e.totalNum},on:{"current-change":e.changePage}})],1)],1),e._v(" "),a("el-dialog",{attrs:{title:e.addOrUpTit,visible:e.centerDialogVisible,width:"50%",center:""},on:{"update:visible":function(t){e.centerDialogVisible=t}}},[a("div",{staticClass:"dialogMain"},[a("el-form",{ref:"addForm",attrs:{model:e.addForm,"label-width":"150px"}},["编辑颗粒料"!=e.addOrUpTit?a("el-form-item",{attrs:{label:"加工破碎料类别",required:""}},[a("el-select",{attrs:{placeholder:"请选择破碎料类别"},on:{change:e.getWeight},model:{value:e.addForm.cmaterialcategoryid,callback:function(t){e.$set(e.addForm,"cmaterialcategoryid",t)},expression:"addForm.cmaterialcategoryid"}},e._l(e.tableTypes,function(e,t){return a("el-option",{directives:[{name:"show",rawName:"v-show",value:"全部"!=e.name,expression:"item.name != '全部'"}],key:t,attrs:{label:e.name,value:e.id}})}),1)],1):e._e(),e._v(" "),"编辑颗粒料"!=e.addOrUpTit?a("el-form-item",{attrs:{label:"加工破碎料重量(kg)",required:""}},[a("el-input",{attrs:{placeholder:e.cweightText},model:{value:e.addForm.cweight,callback:function(t){e.$set(e.addForm,"cweight",t)},expression:"addForm.cweight"}})],1):e._e(),e._v(" "),"编辑颗粒料"!=e.addOrUpTit?a("el-form-item",{attrs:{label:"加工破碎料数量(袋)",required:""}},[a("el-input",{attrs:{placeholder:"请输入破碎料数量"},model:{value:e.addForm.cnumber,callback:function(t){e.$set(e.addForm,"cnumber",t)},expression:"addForm.cnumber"}})],1):e._e(),e._v(" "),"编辑颗粒料"!=e.addOrUpTit?a("el-form-item",{attrs:{label:"加工破碎料备注"}},[a("el-input",{attrs:{placeholder:"请输入破碎料备注"},model:{value:e.addForm.cnote,callback:function(t){e.$set(e.addForm,"cnote",t)},expression:"addForm.cnote"}})],1):e._e(),e._v(" "),a("el-form-item",{attrs:{label:"生成颗粒料类别",required:""}},[a("el-select",{attrs:{placeholder:"请选择颗粒料类别"},model:{value:e.addForm.gmaterialcategoryid,callback:function(t){e.$set(e.addForm,"gmaterialcategoryid",t)},expression:"addForm.gmaterialcategoryid"}},e._l(e.sourceList,function(e,t){return a("el-option",{directives:[{name:"show",rawName:"v-show",value:"全部"!=e.name,expression:"item.name != '全部'"}],key:t,attrs:{label:e.name,value:e.id}})}),1)],1),e._v(" "),a("el-form-item",{attrs:{label:"生成颗粒料重量(kg)",required:""}},[a("el-input",{attrs:{placeholder:"请输入颗粒料重量"},model:{value:e.addForm.weight,callback:function(t){e.$set(e.addForm,"weight",t)},expression:"addForm.weight"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"生成颗粒料数量(袋)",required:""}},[a("el-input",{attrs:{placeholder:"请输入颗粒料数量"},model:{value:e.addForm.number,callback:function(t){e.$set(e.addForm,"number",t)},expression:"addForm.number"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"生成颗粒料备注"}},[a("el-input",{attrs:{placeholder:"请输入颗粒料备注"},model:{value:e.addForm.note,callback:function(t){e.$set(e.addForm,"note",t)},expression:"addForm.note"}})],1)],1)],1),e._v(" "),a("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(t){e.centerDialogVisible=!1}}},[e._v("取 消")]),e._v(" "),a("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.addSaveForm()}}},[e._v("确 定")])],1)])],1)},staticRenderFns:[]};var r=a("VU/8")(l,i,!1,function(e){a("eqkP")},"data-v-1ea968fa",null);t.default=r.exports}});
//# sourceMappingURL=45.c546fec8b39523581b90.js.map