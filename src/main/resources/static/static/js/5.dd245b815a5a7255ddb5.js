webpackJsonp([5],{"SBS+":function(e,t){},Sn9y:function(e,t,l){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var o=l("0RrJ"),n={name:"DeleteUser",methods:{del:function(){Object(o.b)({url:this.$store.state.globalUrl+"cleanalluser",method:"get"}).then(function(e){console.log("successfully delete")}).catch(function(e){console.log("fail to delete")})},delStorage:function(){sessionStorage.clear()},logout:function(){var e=this;Object(o.b)({url:this.$store.state.globalUrl+"/logout",params:{username:this.$store.state.globalUserName}}).then(function(t){e.$router.replace("/home")}).catch(function(e){alert("下线失败")})}}},i={render:function(){var e=this,t=e.$createElement,l=e._self._c||t;return l("div",{staticStyle:{display:"flex","align-items":"center","flex-direction":"column",height:"600px","justify-content":"space-between"},attrs:{id:"delete"}},[l("div",[l("button",{staticStyle:{width:"200px",height:"100px","border-radius":"10px"},on:{click:e.logout}},[e._v("log out")])]),e._v(" "),l("div",[l("button",{staticStyle:{width:"200px",height:"100px","border-radius":"10px"},on:{click:e.del}},[e._v("delete")])]),e._v(" "),l("div",[l("button",{staticStyle:{width:"200px",height:"100px","border-radius":"10px"},on:{click:e.delStorage}},[e._v("clear cache")])])])},staticRenderFns:[]};var c=l("VU/8")(n,i,!1,function(e){l("SBS+")},"data-v-9456518e",null);t.default=c.exports}});
//# sourceMappingURL=5.dd245b815a5a7255ddb5.js.map