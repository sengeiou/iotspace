webpackJsonp([19],{BthI:function(e,t,l){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var a=l("2t8s"),i=l("2R99"),o={components:{fileupload:a.a,aliUpFile:i.a},filters:{formatDate:function(e){var t=new Date(e),l=t.getFullYear(),a=t.getMonth()+1,i=t.getDate(),o=t.getHours(),s=t.getMinutes();return a<10&&(a="0"+a),o<10&&(o="0"+o),s<10&&(s="0"+s),l+"-"+a+"-"+i+" "+o+":"+s}},data:()=>({page:1,pageSize:10,totalNum:100,form:{item:""},addOrUpTit:"",addForm:{id:null,item:null,note:null,file:null,title:null,importance:null,type:null,userid:null,img:null},tableData:[],multipleSelection:[],centerDialogVisible:!1,innerVisible:!1,endPickerOptions:{disabledDate:e=>e.getTime()<Date.now()-864e5},flagIf:0,typeList:[],importanceList:[]}),mounted(){this.getData(),this.getSelectList()},methods:{getSelectList(){var e=this,t={value:"",text:"全部"};this.$ajax.get("/recovery/notice/typeList").then(function(l){console.log("type列表",l),e.typeList=l.data.list,e.typeList.unshift(t)}),this.$ajax.get("/recovery/notice/importanceList").then(function(l){console.log("重要程度列表",l),e.importanceList=l.data.list,e.importanceList.unshift(t)})},getFileUrl(e){console.log(e),this.addForm.file=e},upFileDatas(e){this.addForm.img=e},fileDels(e){this.addForm.img=""},getData(){var e=this;this.$ajax.get("/recovery/notice/selectVByPage",{params:{page:this.page,rows:this.pageSize,item:this.form.item}}).then(function(t){console.log(t),260==t.data.status?(e.tableData=t.data.list,e.totalNum=t.data.info.total):(e.tableData=[],e.totalNum=0)})},changePage(e){this.page=e,this.getData()},selectList(){this.page=1,this.getData()},clearForm(){this.addForm={id:null,item:null,note:null,file:null,title:null,importance:null,type:null,userid:sessionStorage.getItem("userId"),img:null},this.flagIf=1},clickAddOrUp(){this.centerDialogVisible=!0,this.addOrUpTit="创建公告",this.clearForm()},handleEdit(e,t){console.log(e,t);this.centerDialogVisible=!0,this.addOrUpTit="编辑公告",this.addForm={id:t.id,item:t.item,note:t.note,file:t.file,time:t.time,title:t.title,importance:t.importance,type:t.type,userid:t.userid,img:t.img}},addSaveForm(){console.log(this.addForm);var e=this;if(null!=this.addForm.item&&null!=this.addForm.title&&null!=this.addForm.type&&null!=this.addForm.importance){var t=this.$qs.stringify(this.addForm,{indices:!1});"创建公告"==this.addOrUpTit?this.$ajax.post("/recovery/notice/insert ",t).then(function(t){260==t.data.status&&(e.centerDialogVisible=!1,e.$message.success("添加成功"),e.getData(),e.clearForm())}):this.$ajax.put("/recovery/notice/update",t).then(function(t){260==t.data.status&&(e.centerDialogVisible=!1,e.$message.success("编辑成功"),e.getData(),e.clearForm())})}else this.$message.error("*为必填项！")},delAll(){if(this.multipleSelection.length<1)return this.$message.success("请先选择删除对象");this.$confirm("此操作将永久删除选中的通知, 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(()=>{this.multipleSelection.length==this.tableData.length&&(this.page=Number(this.page)-1);var e=this;this.$ajax.delete("/recovery/notice/deletes",{data:this.multipleSelection}).then(function(t){260==t.data.status&&(e.$message.success("删除成功"),e.getData())})}).catch(()=>{this.$message({type:"info",message:"已取消删除"})})},handleSelectionChange(e){this.multipleSelection=[];for(var t=0;t<e.length;t++)this.multipleSelection.push(e[t].id);console.log(this.multipleSelection)},handleDelete(e,t){this.$confirm("此操作将删除该通知, 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(()=>{console.log(e,t),1==this.tableData.length&&(this.page=Number(this.page)-1);var l=this;this.$ajax.delete("/recovery/notice/delete",{params:{id:t}}).then(function(e){260==e.data.status&&(l.$message.success("删除成功"),l.getData())})}).catch(()=>{this.$message({type:"info",message:"已取消删除"})})}}},s={render:function(){var e=this,t=e.$createElement,l=e._self._c||t;return l("div",{attrs:{id:"yudingBox"}},[l("div",{staticClass:"selectBox"},[l("el-form",{ref:"form",attrs:{model:e.form,"label-width":"70px"}},[l("el-form-item",{attrs:{label:"通知内容"}},[l("el-input",{attrs:{placeholder:"请输入通知内容"},on:{change:e.selectList},model:{value:e.form.item,callback:function(t){e.$set(e.form,"item",t)},expression:"form.item"}})],1),e._v(" "),l("el-form-item",{attrs:{label:"公告类型"}},[l("el-select",{attrs:{placeholder:"请选择公告类型"},model:{value:e.addForm.type,callback:function(t){e.$set(e.addForm,"type",t)},expression:"addForm.type"}},e._l(e.typeList,function(t,a){return l("el-option",{key:a,attrs:{label:t.text,value:t.value},on:{change:e.selectList}})}),1)],1),e._v(" "),l("el-form-item",{attrs:{label:"重要程度"}},[l("el-select",{attrs:{placeholder:"请选择公告类型"},model:{value:e.addForm.importance,callback:function(t){e.$set(e.addForm,"importance",t)},expression:"addForm.importance"}},e._l(e.importanceList,function(t,a){return l("el-option",{key:a,attrs:{label:t.text,value:t.value},on:{change:e.selectList}})}),1)],1)],1)],1),e._v(" "),l("div",{staticClass:"tableBox"},[l("el-table",{staticStyle:{width:"100%"},attrs:{data:e.tableData},on:{"selection-change":e.handleSelectionChange}},[l("el-table-column",{attrs:{type:"selection",width:"55"}}),e._v(" "),l("el-table-column",{attrs:{prop:"time",label:"创建时间",sortable:""},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n                "+e._s(e._f("formatDate")(t.row.time))+"\n              ")]}}])}),e._v(" "),l("el-table-column",{attrs:{prop:"title",label:"公告标题"}}),e._v(" "),l("el-table-column",{attrs:{prop:"item",label:"公告内容",width:"320"}}),e._v(" "),l("el-table-column",{attrs:{prop:"importance",label:"重要程度"}}),e._v(" "),l("el-table-column",{attrs:{prop:"type",label:"公告类型"}}),e._v(" "),l("el-table-column",{attrs:{prop:"user.username",label:"发布人"}}),e._v(" "),l("el-table-column",{attrs:{prop:"",label:"附件"},scopedSlots:e._u([{key:"default",fn:function(t){return[l("el-link",{attrs:{type:"primary",href:t.row.file,disabled:null==t.row.file}},[e._v("下载附件")])]}}])}),e._v(" "),l("el-table-column",{attrs:{prop:"",label:"图片"},scopedSlots:e._u([{key:"default",fn:function(t){return[l("el-link",{attrs:{type:"primary",href:t.row.img,disabled:null==t.row.img}},[e._v("下载图片")])]}}])}),e._v(" "),l("el-table-column",{attrs:{label:"操作",width:"150"},scopedSlots:e._u([{key:"default",fn:function(t){return[l("el-button",{attrs:{size:"mini"},on:{click:function(l){return e.handleEdit(t.$index,t.row)}}},[e._v("编辑")]),e._v(" "),l("el-button",{attrs:{size:"mini",type:"danger"},on:{click:function(l){return e.handleDelete(t.$index,t.row.id)}}},[e._v("删除")])]}}])})],1),e._v(" "),l("div",{staticClass:"pafinationBox"},[l("div",[l("el-button",{attrs:{type:"primary"},on:{click:e.clickAddOrUp}},[e._v("添加公告")]),e._v(" "),l("el-button",{attrs:{type:"warning"},on:{click:e.delAll}},[e._v("批量删除")])],1),e._v(" "),l("el-pagination",{attrs:{background:"",layout:"prev, pager, next","page-size":e.pageSize,total:e.totalNum},on:{"current-change":e.changePage}})],1)],1),e._v(" "),l("el-dialog",{attrs:{title:e.addOrUpTit,visible:e.centerDialogVisible,width:"600px",center:""},on:{"update:visible":function(t){e.centerDialogVisible=t}}},[l("div",{staticClass:"dialogMain"},[l("el-form",{ref:"addForm",attrs:{model:e.addForm,"label-width":"100px"}},[l("el-form-item",{attrs:{label:"公告标题",required:""}},[l("el-input",{model:{value:e.addForm.title,callback:function(t){e.$set(e.addForm,"title",t)},expression:"addForm.title"}})],1),e._v(" "),l("el-form-item",{attrs:{label:"公告内容",required:""}},[l("el-input",{attrs:{type:"textarea",placeholder:"请输入内容",maxlength:"2000","show-word-limit":""},model:{value:e.addForm.item,callback:function(t){e.$set(e.addForm,"item",t)},expression:"addForm.item"}})],1),e._v(" "),l("el-form-item",{attrs:{label:"公告类型",required:""}},[l("el-select",{attrs:{placeholder:"请选择公告类型"},model:{value:e.addForm.type,callback:function(t){e.$set(e.addForm,"type",t)},expression:"addForm.type"}},e._l(e.typeList,function(e,t){return l("el-option",{directives:[{name:"show",rawName:"v-show",value:"全部"!=e.text,expression:"item.text != '全部'"}],key:t,attrs:{label:e.text,value:e.value}})}),1)],1),e._v(" "),l("el-form-item",{attrs:{label:"重要程度",required:""}},[l("el-select",{attrs:{placeholder:"请选择公告类型"},model:{value:e.addForm.importance,callback:function(t){e.$set(e.addForm,"importance",t)},expression:"addForm.importance"}},e._l(e.importanceList,function(e,t){return l("el-option",{directives:[{name:"show",rawName:"v-show",value:"全部"!=e.text,expression:"item.text != '全部'"}],key:t,attrs:{label:e.text,value:e.value}})}),1)],1),e._v(" "),l("el-form-item",{attrs:{label:"上传附件"}},[l("fileupload",{attrs:{directory:"notice",flag:e.flagIf},on:{fileUrl:e.getFileUrl}})],1)],1),e._v(" "),l("div",{staticClass:"iptBox"},[l("span",[e._v("上传图片")]),e._v(" "),l("div",[l("aliUpFile",{attrs:{file:e.addForm.img,server:"/recovery",directory:"notice",fileNum:"1"},on:{fileDel:e.fileDels,upFileData:e.upFileDatas}})],1)])],1),e._v(" "),l("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[l("el-button",{on:{click:function(t){e.centerDialogVisible=!1}}},[e._v("取 消")]),e._v(" "),l("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.addSaveForm()}}},[e._v("确 定")])],1)])],1)},staticRenderFns:[]};var r=l("VU/8")(o,s,!1,function(e){l("N5/M")},"data-v-bfe34d8c",null);t.default=r.exports},"N5/M":function(e,t){}});
//# sourceMappingURL=19.245216746d08140dffbf.js.map