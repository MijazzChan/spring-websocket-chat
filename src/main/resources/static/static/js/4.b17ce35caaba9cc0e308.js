webpackJsonp([4],{Sn9y:function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var s=a("0RrJ"),o={name:"DeleteUser",data:function(){return{myName:this.$store.state.globalUserName,mySex:this.$store.state.sex,myAvatar:this.$store.state.avatar,myAddr:this.$store.state.IpAddr,img_src:[a("iDO4"),a("kkKX"),a("FdLO"),a("Q7Kr"),a("/PRx"),a("mhzR")]}},methods:{del:function(){var t=this;Object(s.b)({url:this.$store.state.globalUrl+"/cleanalluser",method:"get"}).then(function(e){console.log("successfully delete"),t.$router.replace("/home")}).catch(function(e){console.log("fail to delete"),t.$router.replace("/home")})},delStorage:function(){sessionStorage.clear()},logout:function(){var t=this;Object(s.b)({url:this.$store.state.globalUrl+"/logout",params:{username:this.$store.state.globalUserName}}).then(function(e){t.$router.replace("/home")}).catch(function(t){alert("下线失败")})}},destroyed:function(){this.logout()}},r={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{attrs:{id:"me"}},[a("div",[a("img",{attrs:{src:t.img_src[parseInt(t.myAvatar)],id:"avatar"}})]),t._v(" "),a("div",[a("h2",{staticStyle:{"font-style":"italic"}},[t._v("name: "+t._s(t.myName))])]),t._v(" "),a("div",[a("h2",{staticStyle:{"font-style":"italic"}},[t._v("IP Address:"+t._s(t.myAddr))])]),t._v(" "),a("div",{staticStyle:{"font-style":"italic"}},[0===t.mySex?a("h2",[t._v("sex:男")]):a("h2",[t._v("sex:女")])]),t._v(" "),a("div",[a("button",{attrs:{id:"logout"},on:{click:t.logout}},[t._v("log out")])]),t._v(" "),a("div",{attrs:{id:"delete"}},[a("button",{on:{click:t.del}},[t._v("delete")]),t._v(" "),a("button",{on:{click:t.delStorage}},[t._v("clear cache")])])])},staticRenderFns:[]};var l=a("VU/8")(o,r,!1,function(t){a("y0kg")},"data-v-f7451fbc",null);e.default=l.exports},y0kg:function(t,e){}});
//# sourceMappingURL=4.b17ce35caaba9cc0e308.js.map