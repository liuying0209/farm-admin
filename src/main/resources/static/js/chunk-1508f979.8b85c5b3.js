(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-1508f979"],{3975:function(t,e,a){"use strict";a.r(e);var n=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{class:t.pageName},[a("div",{class:t.pageName+"-form"},[a("Button",{staticClass:"form-add",attrs:{icon:"md-add",type:"primary"},on:{click:t.toUserEdit}},[t._v("新增")]),a("Input",{staticClass:"form-search",attrs:{search:"","enter-button":"",placeholder:"请输入姓名"},on:{"on-search":t.onSearch}})],1),a("div",{class:t.pageName+"-data"},[a("Table",{attrs:{loading:t.loading,border:"",columns:t.dataColumns,data:t.dataList},scopedSlots:t._u([{key:"create_time",fn:function(e){var n=e.row;return[a("div",[a("span",[t._v(t._s(n.create_time))])])]}},{key:"handle",fn:function(e){var n=e.row;return[a("div",[a("Poptip",{attrs:{trigger:"hover",title:"",content:"编辑用户"}},[a("Button",{attrs:{size:"small",icon:"md-create",type:"success"},on:{click:function(e){return t.handleEditUser(n)}}})],1),a("Poptip",{attrs:{trigger:"hover",title:"",content:"重置密码"}},[a("Button",{attrs:{size:"small",icon:"md-flower",type:"success"},on:{click:function(e){return t.handleResetPWD(n)}}})],1),a("Poptip",{attrs:{trigger:"hover",title:"",content:"删除用户"}},[a("Button",{attrs:{size:"small",icon:"md-trash",type:"error"},on:{click:function(e){return t.handleDeleteUser(n)}}})],1)],1)]}}])}),a("Page",{staticClass:"table-pagination",attrs:{total:t.pagination.total,size:"small","show-elevator":"","show-sizer":"","show-total":""},on:{"on-change":t.onChange,"on-page-size-change":t.onPageSizeChange}})],1)])},i=[],s=a("8593"),r=a("90de"),o="SystemUser",u={name:o,filters:{getRelativeTime:r["b"]},data:function(){return{pageName:o,dataColumns:[{title:"姓名",key:"username",minWidth:100},{title:"电话",key:"mobile",minWidth:120},{title:"邮箱",key:"email",minWidth:100},{title:"角色",key:"levelName",minWidth:100},{title:"创建时间",slot:"create_time",minWidth:150},{title:"操作",slot:"handle",minWidth:160}],dataList:[],params:{pageNo:1,pageSize:10,username:""},pagination:{total:0,current:1,pageSize:10},loading:!1,modalConnectFarm:!1,connectFarm:{model:"",farmList:[],user_id:"",loading:!1}}},created:function(){this.getDataList(this.params)},methods:{toUserEdit:function(){this.$router.push({name:"systemUserEdit"})},onSearch:function(t){this.params.username=t,this.getDataList(this.params)},onChange:function(t){this.params.pageNo=t,this.getDataList(this.params)},onPageSizeChange:function(t){this.params.pageSize=t,this.params.pageNo=1,this.getDataList(this.params)},handleEditUser:function(t){this.$router.push({name:"systemUserEdit",query:{id:t.admin_id}})},handleResetPWD:function(t){var e=this;this.$Modal.confirm({title:"提示",content:"<p>将密码重置为'123456'，是否继续？</p>",onOk:function(){e.axiosResetPwd({adminId:t.admin_id})}})},handleDeleteUser:function(t){var e=this;this.$Modal.confirm({title:"提示",content:"<p>确认删除？</p>",onOk:function(){e.axiosDeleteUser(t.admin_id)}})},getDataList:function(t){var e=this;this.loading=!0,Object(s["g"])(t).then(function(t){if(console.log(t),200===t.status){var a=t.data;e.pagination.total=a.total,e.dataList=a.list}else e.$Message.warning(t.message);e.loading=!1})},axiosResetPwd:function(t){var e=this;Object(s["h"])(t).then(function(t){200===t.status?e.$Message.success("重置密码成功"):e.$Message.success("重置密码失败")})},axiosDeleteUser:function(t){var e=this;Object(s["d"])(t).then(function(t){200===t.status?(e.$Message.success("删除用户成功"),e.getDataList(e.params)):e.$Message.success("删除用户失败")})}}},c=u,d=(a("b19f"),a("2877")),l=Object(d["a"])(c,n,i,!1,null,"03bb7edb",null);e["default"]=l.exports},8593:function(t,e,a){"use strict";a.d(e,"g",function(){return i}),a.d(e,"a",function(){return s}),a.d(e,"i",function(){return r}),a.d(e,"b",function(){return o}),a.d(e,"h",function(){return u}),a.d(e,"d",function(){return c}),a.d(e,"f",function(){return d}),a.d(e,"e",function(){return l}),a.d(e,"c",function(){return m});var n=a("66df"),i=function(t){return n["a"].request({url:"api/admin/page",method:"get",params:t})},s=function(t){return n["a"].request({url:"api/admin/add",method:"post",data:t})},r=function(t){return n["a"].request({url:"api/admin/update",method:"PUT",data:t})},o=function(t){return n["a"].request({url:"api/admin/"+t,method:"get"})},u=function(t){return n["a"].request({url:"api/admin/reset",method:"post",data:t})},c=function(t){return n["a"].request({url:"api/admin/"+t,method:"DELETE"})},d=function(t){return n["a"].request({url:"api/role/edit",method:"get",params:t})},l=function(t){return n["a"].request({url:"api/role/edit_save",method:"post",data:t})},m=function(t){return n["a"].request({url:"api/admin/updatePassword",method:"post",data:t})}},b19f:function(t,e,a){"use strict";var n=a("b268"),i=a.n(n);i.a},b268:function(t,e,a){}}]);