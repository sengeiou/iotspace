webpackJsonp([52],{D1F6:function(t,e){},gEJm:function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var l={filters:{formatDate:function(t){var e=new Date(t),a=e.getFullYear(),l=e.getMonth()+1,s=e.getDate(),r=e.getHours(),o=e.getMinutes();return l<10&&(l="0"+l),r<10&&(r="0"+r),o<10&&(o="0"+o),a+"-"+l+"-"+s+" "+r+":"+o}},data:()=>({belongIf:!1,userId:sessionStorage.getItem("userId"),userType:sessionStorage.getItem("userType"),page:1,pageSize:5,totalNum:100,form:{barcode:null,departmentid:null,status:null,truckid:null,starttime:null,endtime:null,type:null},addOrUpTit:"",tableData:[],jiluList:[],typeList:[],departmentList:[],truckList:[],statusList:[],centerDialogVisible:!1}),mounted(){this.getData(),this.getDepartmentList()},methods:{getDepartmentList(){var t=this,e={id:"",name:"全部"},a={id:"",number:"全部"},l={value:"",text:"全部"};this.$ajax.get("/recovery/department/selectByHospitalId",{params:{hospitalid:sessionStorage.getItem("belongId")}}).then(function(a){console.log("keshi列表",a),t.departmentList=a.data.list,t.departmentList.unshift(e)}),this.$ajax.get("/recovery/truck/selectAll").then(function(e){console.log("truck列表",e),t.truckList=e.data.list,t.truckList.unshift(a)}),this.$ajax.get("/recovery/garbagebag/statusList").then(function(e){console.log("status列表",e),t.statusList=e.data.list,t.statusList.unshift(l)}),this.$ajax.get("/recovery/garbagebag/typeList").then(function(e){console.log("type列表",e),t.typeList=e.data.list,t.typeList.unshift(l)})},clearSelect(){this.form={barcode:null,departmentid:null,status:null,truckid:null,starttime:null,endtime:null,type:null},this.getData()},getData(){var t=this;null==this.form.starttime&&(this.form.starttime=""),null==this.form.endtime&&(this.form.endtime=""),this.$ajax.get("/recovery/garbagebag/selectVByHospital",{params:{page:this.page,rows:this.pageSize,barcode:this.form.barcode,departmentid:this.form.departmentid,status:this.form.status,truckid:this.form.truckid,hospitalid:sessionStorage.getItem("belongId"),begintime:""==this.form.starttime?"":new Date(this.form.starttime)/1e3*1e3,endtime:""==this.form.endtime?"":new Date(this.form.endtime)/1e3*1e3}}).then(function(e){console.log(e),260==e.data.status?(t.tableData=e.data.list,t.totalNum=e.data.info.total):(t.tableData=[],t.totalNum=0,t.$message.error(e.data.message))})},changePage(t){this.page=t,this.getData()},lookInfusionbag(t){this.$router.replace({path:"/Infusionbag",query:{garbagebagid:t}})},lookGarbagebag(t){var e=this;this.jiluList=[],this.$ajax.get("/recovery/garbagebagrecord/selectVByGarbagebagId",{params:{garbagebagid:t}}).then(function(t){console.log(t),e.belongIf=!0,260==t.data.status&&(e.jiluList=t.data.list)})},selectList(){this.page=1,this.getData()}}},s={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{attrs:{id:"yudingBox"}},[a("div",{staticClass:"selectBox"},[a("el-form",{ref:"form",attrs:{model:t.form,"label-width":"60px"}},[a("el-form-item",{attrs:{label:"条码"}},[a("el-input",{on:{change:t.selectList},model:{value:t.form.barcode,callback:function(e){t.$set(t.form,"barcode",e)},expression:"form.barcode"}})],1),t._v(" "),a("el-form-item",{attrs:{label:"科室"}},[a("el-select",{attrs:{placeholder:"请选择科室"},on:{change:t.selectList},model:{value:t.form.departmentid,callback:function(e){t.$set(t.form,"departmentid",e)},expression:"form.departmentid"}},t._l(t.departmentList,function(t,e){return a("el-option",{key:e,attrs:{label:t.name,value:t.id}})}),1)],1),t._v(" "),a("el-form-item",{attrs:{label:"状态"}},[a("el-select",{attrs:{placeholder:"请选择状态"},on:{change:t.selectList},model:{value:t.form.status,callback:function(e){t.$set(t.form,"status",e)},expression:"form.status"}},t._l(t.statusList,function(t,e){return a("el-option",{key:e,attrs:{label:t.text,value:t.value}})}),1)],1),t._v(" "),a("el-form-item",{attrs:{label:"类型"}},[a("el-select",{attrs:{placeholder:"请选择类型"},on:{change:t.selectList},model:{value:t.form.type,callback:function(e){t.$set(t.form,"type",e)},expression:"form.type"}},t._l(t.typeList,function(t,e){return a("el-option",{key:e,attrs:{label:t.text,value:t.value}})}),1)],1)],1)],1),t._v(" "),a("div",{staticClass:"selectBox"},[a("el-form",{ref:"form",attrs:{model:t.form,"label-width":"60px"}},[a("el-form-item",{attrs:{label:"时间"}},[a("el-col",{attrs:{span:11}},[a("el-date-picker",{staticStyle:{width:"100%"},attrs:{type:"date",placeholder:"起始日期","value-format":"yyyy/MM/dd"},on:{change:t.selectList},model:{value:t.form.starttime,callback:function(e){t.$set(t.form,"starttime",e)},expression:"form.starttime"}})],1),t._v(" "),a("el-col",{attrs:{span:11}},[a("el-date-picker",{staticStyle:{width:"100%"},attrs:{type:"date",placeholder:"结束日期","value-format":"yyyy/MM/dd"},on:{change:t.selectList},model:{value:t.form.endtime,callback:function(e){t.$set(t.form,"endtime",e)},expression:"form.endtime"}})],1)],1),t._v(" "),a("el-form-item",[a("el-button",{staticStyle:{"font-size":"14px"},attrs:{type:"warning",size:"small"},on:{click:t.clearSelect}},[t._v("清空筛选")])],1)],1)],1),t._v(" "),a("div",{staticClass:"tableBox"},[a("el-table",{staticStyle:{width:"100%"},attrs:{data:t.tableData}},[a("el-table-column",{attrs:{prop:"time",label:"时间",width:"150",sortable:""},scopedSlots:t._u([{key:"default",fn:function(e){return[null==e.row.time?a("span"):a("span",[t._v(t._s(t._f("formatDate")(e.row.time)))])]}}])}),t._v(" "),a("el-table-column",{attrs:{prop:"barcode",label:"条码"}}),t._v(" "),a("el-table-column",{attrs:{prop:"department.name",label:"所属科室"}}),t._v(" "),a("el-table-column",{attrs:{prop:"belongname",label:"实时位置"}}),t._v(" "),a("el-table-column",{attrs:{prop:"belongtype",label:"位置类型"}}),t._v(" "),a("el-table-column",{attrs:{prop:"type",label:"类型"}}),t._v(" "),a("el-table-column",{attrs:{prop:"status",label:"状态"}}),t._v(" "),a("el-table-column",{attrs:{prop:"",label:"记录"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("el-button",{attrs:{size:"mini"},on:{click:function(a){return t.lookGarbagebag(e.row.id)}}},[t._v("查看")])]}}])})],1),t._v(" "),a("div",{staticClass:"pafinationBox"},[a("div"),t._v(" "),a("el-pagination",{attrs:{background:"",layout:"prev, pager, next","page-size":t.pageSize,total:t.totalNum},on:{"current-change":t.changePage}})],1)],1),t._v(" "),a("el-dialog",{attrs:{title:"垃圾袋记录",visible:t.belongIf,width:"60%",center:""},on:{"update:visible":function(e){t.belongIf=e}}},[a("div",{staticClass:"dialogMain"},[a("el-table",{staticStyle:{width:"100%"},attrs:{data:t.jiluList}},[a("el-table-column",{attrs:{prop:"time",label:"时间",width:"150",sortable:""},scopedSlots:t._u([{key:"default",fn:function(e){return[null==e.row.time?a("span"):a("span",[t._v(t._s(t._f("formatDate")(e.row.time)))])]}}])}),t._v(" "),a("el-table-column",{attrs:{prop:"belongname",label:"经存单位"}}),t._v(" "),a("el-table-column",{attrs:{prop:"belongtype",label:"单位类型",width:"100"}}),t._v(" "),a("el-table-column",{attrs:{prop:"status",label:"状态",width:"100"}})],1)],1)])],1)},staticRenderFns:[]};var r=a("VU/8")(l,s,!1,function(t){a("D1F6")},"data-v-04703eaa",null);e.default=r.exports}});
//# sourceMappingURL=52.f3619b507b6bfbafe641.js.map