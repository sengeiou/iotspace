webpackJsonp([49],{"Gg+M":function(t,e){},RYyZ:function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var s={filters:{formatDate:function(t){var e=new Date(t),a=e.getFullYear(),s=e.getMonth()+1,r=e.getDate(),l=e.getHours(),i=e.getMinutes();return s<10&&(s="0"+s),l<10&&(l="0"+l),i<10&&(i="0"+i),a+"-"+s+"-"+r+" "+l+":"+i}},data:()=>({userId:sessionStorage.getItem("userId"),userType:sessionStorage.getItem("userType"),page:1,pageSize:5,totalNum:100,form:{vehicleid:"",transporterid:"",transportcompanyid:"",trucktype:"",starttime:null,endtime:null},typeList:["医疗机构-回收利用企业","医疗机构-回收物流"],tableData:[],transporterList:[],carList:[],zancunList:[],totalList:{glassnumber:0,glassweight:0,plasticnumber:0,plasticweight:0}}),mounted(){this.getData(),this.getselectList()},methods:{exportList(t){var e="",a="";if(""!=this.form.starttime||""!=this.form.endtime){e=new Date(this.form.starttime)/1e3*1e3,a=new Date(this.form.endtime)/1e3*1e3,console.log(this.form);var s="http://hrt.chinagrp.org.cn/recovery/truckhospital/treatmentplantTruckhospitalExport?treatmentplantid="+sessionStorage.getItem("belongId")+"&begintime="+e+"&endtime="+a+"&trucktype="+this.form.trucktype+"&vehicleid="+this.form.vehicleid+"&transporterid="+this.form.transporterid+"&transportcompanyid="+this.form.transportcompanyid;console.log(s),window.location.href=s}else this.$message.error("请选择起止时间！")},transportChange(){this.selectList(),this.getselectList()},getselectList(){var t=this,e={id:"",username:"全部"},a={id:"",number:"全部"};this.$ajax.get("/recovery/user/selectByTreatmentplant",{params:{treatmentplantid:sessionStorage.getItem("belongId"),transportcompanyid:this.form.transportcompanyid}}).then(function(a){console.log("运输员列表",a),t.transporterList=a.data.list,t.transporterList.unshift(e)}),this.$ajax.get("/recovery/vehicle/selectByTreatmentplant",{params:{treatmentplantid:sessionStorage.getItem("belongId"),transportcompanyid:this.form.transportcompanyid}}).then(function(e){console.log("车辆列表",e),t.carList=e.data.list,t.carList.unshift(a)});var s={id:"",name:"全部"};this.$ajax.get("/recovery/transportcompany/selectByTreatmentplantId",{params:{treatmentplantid:sessionStorage.getItem("belongId")}}).then(function(e){console.log("暂存点列表",e),t.zancunList=e.data.list,t.zancunList.unshift(s)})},getData(){var t=this;null==this.form.starttime&&(this.form.starttime=""),null==this.form.endtime&&(this.form.endtime=""),this.$ajax.get("/recovery/truckhospital/treatmentplantTruckhospitalList",{params:{page:this.page,rows:this.pageSize,treatmentplantid:sessionStorage.getItem("belongId"),vehicleid:this.form.vehicleid,transporterid:this.form.transporterid,transportcompanyid:this.form.transportcompanyid,trucktype:this.form.trucktype,begintime:""==this.form.starttime?"":new Date(this.form.starttime)/1e3*1e3,endtime:""==this.form.endtime?"":new Date(this.form.endtime)/1e3*1e3}}).then(function(e){console.log(e),260==e.data.status?(t.totalList=e.data.info.sum,t.tableData=e.data.list,t.totalNum=e.data.info.total):(t.totalList={glassnumber:0,glassweight:0,plasticnumber:0,plasticweight:0},t.tableData=[],t.totalNum=0)})},changePage(t){this.page=t,this.getData()},clearSelect(){this.form={submitid:null,status:null,starttime:null,endtime:null},this.getData()},selectList(){this.page=1,this.getData()}}},r={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{attrs:{id:"yudingBox"}},[a("div",{staticClass:"selectBox"},[a("el-form",{ref:"form",attrs:{model:t.form,"label-width":"60px"}},[a("el-form-item",{attrs:{label:"回收物流","label-width":"68px"}},[a("el-select",{staticStyle:{width:"200px"},attrs:{placeholder:"请选择回收物流"},on:{change:t.transportChange},model:{value:t.form.transportcompanyid,callback:function(e){t.$set(t.form,"transportcompanyid",e)},expression:"form.transportcompanyid"}},t._l(t.zancunList,function(t,e){return a("el-option",{key:e,attrs:{label:t.name,value:t.id}})}),1)],1),t._v(" "),a("el-form-item",{attrs:{label:"类型"}},[a("el-select",{staticStyle:{width:"200px"},attrs:{placeholder:"请选择车次类型"},on:{change:t.selectList},model:{value:t.form.trucktype,callback:function(e){t.$set(t.form,"trucktype",e)},expression:"form.trucktype"}},[t._v(">\n            "),t._l(t.typeList,function(t,e){return a("el-option",{key:e,attrs:{label:t,value:t}})})],2)],1),t._v(" "),a("el-form-item",{attrs:{label:"运输人","label-width":"70px"}},[a("el-select",{staticStyle:{width:"200px"},attrs:{placeholder:"请选择运输人"},on:{change:t.selectList},model:{value:t.form.transporterid,callback:function(e){t.$set(t.form,"transporterid",e)},expression:"form.transporterid"}},t._l(t.transporterList,function(t,e){return a("el-option",{key:e,attrs:{label:t.username,value:t.id}})}),1)],1),t._v(" "),a("el-form-item",{attrs:{label:"车辆"}},[a("el-select",{attrs:{placeholder:"请选择车辆"},on:{change:t.selectList},model:{value:t.form.vehicleid,callback:function(e){t.$set(t.form,"vehicleid",e)},expression:"form.vehicleid"}},t._l(t.carList,function(t,e){return a("el-option",{key:e,attrs:{label:t.number,value:t.id}})}),1)],1)],1)],1),t._v(" "),a("div",{staticClass:"selectBox"},[a("el-form",{ref:"form",attrs:{model:t.form,"label-width":"70px","label-position":"left"}},[a("el-form-item",{attrs:{label:"时间"}},[a("el-col",{attrs:{span:11}},[a("el-date-picker",{attrs:{type:"date",placeholder:"起始日期","value-format":"yyyy/MM/dd"},on:{change:t.selectList},model:{value:t.form.starttime,callback:function(e){t.$set(t.form,"starttime",e)},expression:"form.starttime"}})],1),t._v(" "),a("el-col",{attrs:{span:11}},[a("el-date-picker",{attrs:{type:"date",placeholder:"结束日期","value-format":"yyyy/MM/dd"},on:{change:t.selectList},model:{value:t.form.endtime,callback:function(e){t.$set(t.form,"endtime",e)},expression:"form.endtime"}})],1)],1),t._v(" "),a("el-form-item",{attrs:{"label-width":"10px"}},[a("el-button",{staticStyle:{"font-size":"14px"},attrs:{type:"warning",size:"small"},on:{click:t.clearSelect}},[t._v("清空筛选")]),t._v(" "),a("el-button",{staticStyle:{"font-size":"14px"},attrs:{type:"success",size:"small"},on:{click:t.exportList}},[t._v("导出记录")])],1)],1)],1),t._v(" "),a("div",{staticClass:"tableBox"},[a("p",{staticStyle:{height:"30px","font-size":"14px"}},[t._v("\n        总计：塑料数量："+t._s(t.totalList.plasticnumber)+"   塑料重量："+t._s(t.totalList.plasticweight)+"   \n        玻璃数量："+t._s(t.totalList.glassnumber)+"   玻璃数量："+t._s(t.totalList.glassweight)+"\n      ")]),t._v(" "),a("el-table",{staticStyle:{width:"100%"},attrs:{data:t.tableData}},[a("el-table-column",{attrs:{prop:"time",label:"时间",width:"150",sortable:""},scopedSlots:t._u([{key:"default",fn:function(e){return[null==e.row.time?a("span"):a("span",[t._v(t._s(t._f("formatDate")(e.row.time)))])]}}])}),t._v(" "),a("el-table-column",{attrs:{prop:"hospitalname",label:"医疗机构"}}),t._v(" "),a("el-table-column",{attrs:{prop:"treatmentplantname",label:"企业名称"}}),t._v(" "),a("el-table-column",{attrs:{prop:"transportcompanyname",label:"回收物流"}}),t._v(" "),a("el-table-column",{attrs:{prop:"number",label:"车次"}}),t._v(" "),a("el-table-column",{attrs:{prop:"submituser",label:"运输员"}}),t._v(" "),a("el-table-column",{attrs:{prop:"plasticnumber",label:"塑料数量"}}),t._v(" "),a("el-table-column",{attrs:{prop:"plasticweight",label:"塑料重量"}}),t._v(" "),a("el-table-column",{attrs:{prop:"glassnumber",label:"玻璃数量"}}),t._v(" "),a("el-table-column",{attrs:{prop:"glassweight",label:"玻璃重量"}})],1),t._v(" "),a("div",{staticClass:"pafinationBox"},[a("div"),t._v(" "),a("el-pagination",{attrs:{background:"",layout:"prev, pager, next","page-size":t.pageSize,total:t.totalNum},on:{"current-change":t.changePage}})],1)],1)])},staticRenderFns:[]};var l=a("VU/8")(s,r,!1,function(t){a("Gg+M")},"data-v-0bef973e",null);e.default=l.exports}});
//# sourceMappingURL=49.e0377dc00f3d0fbd2ff7.js.map