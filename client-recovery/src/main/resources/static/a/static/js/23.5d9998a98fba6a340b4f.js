webpackJsonp([23],{n6JE:function(e,t){},"r5+Q":function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var i={filters:{formatDate:function(e){var t=new Date(e),a=t.getFullYear(),i=t.getMonth()+1,l=t.getDate(),r=t.getHours(),o=t.getMinutes();return i<10&&(i="0"+i),r<10&&(r="0"+r),o<10&&(o="0"+o),a+"-"+i+"-"+l+" "+r+":"+o}},data(){return{page:1,pageSize:10,totalNum:100,form:{type:"",starttime:"",endtime:"",categoryid:""},addOrUpTit:"",addForm:{id:null,categoryid:null,weight:null,number:null,treatmentplantid:sessionStorage.getItem("belongId"),trucktreatmentplantreceiveid:this.$route.query.id},tableData:[],tableTypes:[],multipleSelection:[],centerDialogVisible:!1,endPickerOptions:{disabledDate:e=>e.getTime()<Date.now()-864e5}}},mounted(){this.getData(),this.getSelectList()},watch:{addForm:function(e,t){console.log(e)}},methods:{getSelectList(){var e=this,t={id:"",name:"全部"};this.$ajax.get("/recovery/category/selectByTreatmentplantid",{params:{treatmentplantid:sessionStorage.getItem("belongId")}}).then(function(a){console.log("type列表",a),e.tableTypes=a.data.list,e.tableTypes.unshift(t)})},getData(){var e=this;null==this.form.starttime&&(this.form.starttime=""),null==this.form.endtime&&(this.form.endtime=""),this.$ajax.get("/recovery/outboundrecord/selectVByPage",{params:{page:this.page,rows:this.pageSize,treatmentplantid:sessionStorage.getItem("belongId"),trucktreatmentplantreceiveid:this.$route.query.id,categoryid:this.form.type,begintime:""==this.form.starttime?"":new Date(this.form.starttime)/1e3*1e3,endtime:""==this.form.endtime?"":new Date(this.form.endtime)/1e3*1e3}}).then(function(t){console.log(t),260==t.data.status?(e.tableData=t.data.list,e.totalNum=t.data.info.total):(e.tableData=[],e.totalNum=0)})},changePage(e){this.page=e,this.getData()},selectList(){this.page=1,this.getData()},clearSelect(){this.form={type:"",starttime:"",endtime:"",categoryid:""},this.getData()},clearForm(){this.addForm={id:null,categoryid:null,weight:null,number:null,treatmentplantid:sessionStorage.getItem("belongId"),trucktreatmentplantreceiveid:this.$route.query.id}},clickAddOrUp(){this.centerDialogVisible=!0,this.addOrUpTit="添加明细",this.clearForm()},handleEdit(e,t){console.log(e,t);this.centerDialogVisible=!0,this.addOrUpTit="编辑明细",this.addForm={id:t.id,categoryid:t.categoryid,weight:t.weight,number:t.number,treatmentplantid:t.treatmentplantid,trucktreatmentplantreceiveid:t.trucktreatmentplantreceiveid,time:t.time}},addSaveForm(e){if(console.log(this.addForm),null!=this.addForm.categoryid){var t=this,a=this.$qs.stringify(this.addForm,{indices:!1});"添加明细"==this.addOrUpTit?this.$ajax.post("/recovery/outboundrecord/insert",a).then(function(e){260==e.data.status&&(t.centerDialogVisible=!1,t.$message.success("添加成功"),t.getData(),t.clearForm())}):this.$ajax.put("/recovery/outboundrecord/update",a).then(function(e){260==e.data.status&&(t.centerDialogVisible=!1,t.$message.success("编辑成功"),t.getData(),t.clearForm())})}else this.$message.error("*为必填项！")},delAll(){if(this.multipleSelection.length<1)return this.$message.success("请先选择删除对象");this.$confirm("此操作将永久删除选中的明细, 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(()=>{this.multipleSelection.length==this.tableData.length&&(this.page=Number(this.page)-1);var e=this;this.$ajax.delete("/recovery/outboundrecord/deletes",{data:this.multipleSelection}).then(function(t){260==t.data.status&&(e.$message.success("删除成功"),e.getData())})}).catch(()=>{this.$message({type:"info",message:"已取消删除"})})},handleSelectionChange(e){this.multipleSelection=[];for(var t=0;t<e.length;t++)this.multipleSelection.push(e[t].id);console.log(this.multipleSelection)},handleDelete(e,t){this.$confirm("此操作将删除该明细, 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(()=>{console.log(e,t),1==this.tableData.length&&(this.page=Number(this.page)-1);var a=this;this.$ajax.delete("/recovery/outboundrecord/delete",{params:{id:t}}).then(function(e){260==e.data.status&&(a.$message.success("删除成功"),a.getData())})}).catch(()=>{this.$message({type:"info",message:"已取消删除"})})}}},l={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{attrs:{id:"yudingBox"}},[a("div",{staticClass:"selectBox"},[a("el-form",{ref:"form",attrs:{model:e.form,"label-width":"60px"}},[a("el-form-item",{attrs:{label:"类别"}},[a("el-select",{attrs:{placeholder:"请选择类别"},on:{change:e.selectList},model:{value:e.form.type,callback:function(t){e.$set(e.form,"type",t)},expression:"form.type"}},e._l(e.tableTypes,function(e,t){return a("el-option",{key:t,attrs:{label:e.name,value:e.id}})}),1)],1),e._v(" "),a("el-form-item",{attrs:{label:"时间"}},[a("el-col",{attrs:{span:11}},[a("el-date-picker",{staticStyle:{width:"100%"},attrs:{type:"date",placeholder:"起始日期","value-format":"yyyy/MM/dd"},on:{change:e.selectList},model:{value:e.form.starttime,callback:function(t){e.$set(e.form,"starttime",t)},expression:"form.starttime"}})],1),e._v(" "),a("el-col",{attrs:{span:11}},[a("el-date-picker",{staticStyle:{width:"100%"},attrs:{type:"date",placeholder:"结束日期","value-format":"yyyy/MM/dd"},on:{change:e.selectList},model:{value:e.form.endtime,callback:function(t){e.$set(e.form,"endtime",t)},expression:"form.endtime"}})],1)],1),e._v(" "),a("el-form-item",[a("el-button",{staticStyle:{"font-size":"14px"},attrs:{type:"warning",size:"small"},on:{click:e.clearSelect}},[e._v("清空筛选")])],1)],1)],1),e._v(" "),a("div",{staticClass:"tableBox"},[a("p",{staticStyle:{height:"30px","font-size":"14px"}},[e._v("出库明细：")]),e._v(" "),a("el-table",{staticStyle:{width:"100%"},attrs:{data:e.tableData},on:{"selection-change":e.handleSelectionChange}},[a("el-table-column",{attrs:{type:"selection",width:"55"}}),e._v(" "),a("el-table-column",{attrs:{prop:"time",label:"注册时间",sortable:""},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n                "+e._s(e._f("formatDate")(t.row.time))+"\n              ")]}}])}),e._v(" "),a("el-table-column",{attrs:{prop:"category.name",label:"类别"}}),e._v(" "),a("el-table-column",{attrs:{prop:"weight",label:"重量"}}),e._v(" "),a("el-table-column",{attrs:{prop:"number",label:"数量"}}),e._v(" "),a("el-table-column",{attrs:{prop:"note",label:"备注"}}),e._v(" "),a("el-table-column",{attrs:{label:"操作",width:"200"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{attrs:{size:"mini"},on:{click:function(a){return e.handleEdit(t.$index,t.row)}}},[e._v("编辑")]),e._v(" "),a("el-button",{attrs:{size:"mini",type:"danger"},on:{click:function(a){return e.handleDelete(t.$index,t.row.id)}}},[e._v("删除")])]}}])})],1),e._v(" "),a("div",{staticClass:"pafinationBox"},[a("div",[a("el-button",{attrs:{type:"primary"},on:{click:e.clickAddOrUp}},[e._v("添加明细")]),e._v(" "),a("el-button",{attrs:{type:"warning"},on:{click:e.delAll}},[e._v("批量删除")])],1),e._v(" "),a("el-pagination",{attrs:{background:"",layout:"prev, pager, next","page-size":e.pageSize,total:e.totalNum},on:{"current-change":e.changePage}})],1)],1),e._v(" "),a("el-dialog",{attrs:{title:e.addOrUpTit,visible:e.centerDialogVisible,width:"50%",center:""},on:{"update:visible":function(t){e.centerDialogVisible=t}}},[a("div",{staticClass:"dialogMain"},[a("el-form",{ref:"addForm",attrs:{model:e.addForm,"label-width":"100px"}},[a("el-form-item",{attrs:{label:"类别"}},[a("el-select",{attrs:{placeholder:"请选择类别"},model:{value:e.addForm.categoryid,callback:function(t){e.$set(e.addForm,"categoryid",t)},expression:"addForm.categoryid"}},e._l(e.tableTypes,function(e,t){return a("el-option",{directives:[{name:"show",rawName:"v-show",value:"全部"!=e.name,expression:"item.name != '全部'"}],key:t,attrs:{label:e.name,value:e.id}})}),1)],1),e._v(" "),a("el-form-item",{attrs:{label:"重量"}},[a("el-input",{attrs:{placeholder:"请输入重量"},model:{value:e.addForm.weight,callback:function(t){e.$set(e.addForm,"weight",t)},expression:"addForm.weight"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"袋数"}},[a("el-input",{attrs:{placeholder:"请输入袋数"},model:{value:e.addForm.number,callback:function(t){e.$set(e.addForm,"number",t)},expression:"addForm.number"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"备注"}},[a("el-input",{attrs:{placeholder:"请输入备注"},model:{value:e.addForm.note,callback:function(t){e.$set(e.addForm,"note",t)},expression:"addForm.note"}})],1)],1)],1),e._v(" "),a("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(t){e.centerDialogVisible=!1}}},[e._v("取 消")]),e._v(" "),a("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.addSaveForm()}}},[e._v("确 定")])],1)])],1)},staticRenderFns:[]};var r=a("VU/8")(i,l,!1,function(e){a("n6JE")},"data-v-a7d302f6",null);t.default=r.exports}});
//# sourceMappingURL=23.5d9998a98fba6a340b4f.js.map