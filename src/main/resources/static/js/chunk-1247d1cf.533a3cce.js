(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-1247d1cf"],{"2b74":function(t,a,e){"use strict";e.d(a,"f",function(){return r}),e.d(a,"d",function(){return i}),e.d(a,"a",function(){return s}),e.d(a,"e",function(){return o}),e.d(a,"b",function(){return u}),e.d(a,"i",function(){return c}),e.d(a,"h",function(){return m}),e.d(a,"j",function(){return d}),e.d(a,"k",function(){return l}),e.d(a,"c",function(){return f}),e.d(a,"g",function(){return h});var n=e("66df"),r=function(t){return n["a"].request({url:"api/farm/page",method:"get",params:t})},i=function(){return n["a"].request({url:"api/farm/get_farmer",method:"get"})},s=function(t){return n["a"].request({url:"api/farm/add",method:"post",data:t})},o=function(t){return n["a"].request({url:"api/farm/get_farm_info",method:"get",params:t})},u=function(t){return n["a"].request({url:"api/farm/"+t,method:"DELETE"})},c=function(t){return n["a"].request({url:"api/task/page",method:"get",params:t})},m=function(t){return n["a"].request({url:"api/task/"+t,method:"get"})},d=function(t){return n["a"].request({url:"api/task/add",method:"post",data:t})},l=function(t){return n["a"].request({url:"api/task/update",method:"put",data:t})},f=function(t){return n["a"].request({url:"api/task/"+t,method:"DELETE"})},h=function(t){return n["a"].request({url:"api/farm/work/list",method:"get"})}},"3b00":function(t,a,e){"use strict";e.r(a);var n=function(){var t=this,a=t.$createElement,e=t._self._c||a;return e("div",{class:t.pageName},[e("div",{class:t.pageName+"-form"},[e("Input",{staticClass:"form-search",attrs:{search:"","enter-button":"",placeholder:"请输入农场名称"},on:{"on-search":t.onSearch}})],1),e("div",{class:t.pageName+"-data"},[e("Table",{attrs:{loading:t.loading,border:"",columns:t.dataColumns,data:t.dataList},scopedSlots:t._u([{key:"create_time",fn:function(a){var n=a.row;return[e("div",[e("span",[t._v(t._s(n.createTime))])])]}},{key:"handle",fn:function(a){var n=a.row;return[e("div",[e("Poptip",{attrs:{trigger:"hover",title:"",content:"删除农场"}},[e("Button",{attrs:{size:"small",icon:"md-trash",type:"error"},on:{click:function(a){return t.handleDelete(n)}}})],1)],1)]}}])}),e("Page",{staticClass:"table-pagination",attrs:{total:t.pagination.total,size:"small","show-elevator":"","show-sizer":"","show-total":""},on:{"on-change":t.onChange,"on-page-size-change":t.onPageSizeChange}})],1),e("Modal",{class:t.pageName+"-modal",attrs:{title:"农场二维码"},model:{value:t.modalConnectFarm,callback:function(a){t.modalConnectFarm=a},expression:"modalConnectFarm"}},[e("div",{staticStyle:{"text-align":"center"}},[e("img",{attrs:{src:t.farmQRCode,alt:""}})]),e("div",{attrs:{slot:"footer"},slot:"footer"})])],1)},r=[],i=e("2b74"),s=e("90de"),o="BasicFarm",u={name:o,filters:{getRelativeTime:s["b"]},data:function(){return{pageName:o,loading:!1,dataColumns:[{title:"农场名称",key:"name",minWidth:150},{title:"农场主",key:"creatorName",minWidth:100},{title:"加入日期",slot:"create_time",minWidth:150},{title:"操作",slot:"handle",minWidth:100}],dataList:[],params:{pageNo:1,pageSize:10,farmName:""},pagination:{total:0,current:1,pageSize:10},farmQRCode:"",modalConnectFarm:!1}},created:function(){this.axiosGetFarmListPaginate(this.params)},methods:{toFarmEdit:function(){this.$router.push({name:"basicFarmEdit"})},onSearch:function(t){this.params.farmName=t,this.axiosGetFarmListPaginate(this.params)},handleEditFarm:function(t){this.$router.push({name:"basicFarmEdit",query:{id:t.id}})},handleDelete:function(t){var a=this;this.$Modal.confirm({title:"提示",content:"<p>你确认要删除这条记录吗？</p>",onOk:function(){a.axiosDeleteFarm(t.id)}})},onChange:function(t){this.params.pageNo=t,this.axiosGetFarmListPaginate(this.params)},onPageSizeChange:function(t){this.params.pageSize=t,this.params.pageNo=1,this.axiosGetFarmListPaginate(this.params)},axiosGetFarmListPaginate:function(t){var a=this;this.loading=!0,Object(i["f"])(t).then(function(t){if(200===t.status){var e=t.data;a.pagination.total=e.total,a.dataList=e.list}else a.$Message.warning(t.message);a.loading=!1})},axiosDeleteFarm:function(t){var a=this;Object(i["b"])(t).then(function(t){200===t.status?(a.$Message.success("删除农场成功"),a.axiosGetFarmListPaginate(a.params)):a.$Message.success("删除农场失败")})}}},c=u,m=(e("a718"),e("2877")),d=Object(m["a"])(c,n,r,!1,null,null,null);a["default"]=d.exports},"55b6":function(t,a,e){},a718:function(t,a,e){"use strict";var n=e("55b6"),r=e.n(n);r.a}}]);