webpackJsonp([11],{"+l+h":function(e,s,t){"use strict";var a=function(){var e=this,s=e.$createElement,t=e._self._c||s;return t("div",{staticClass:"container-fluid no-padding"},[t("div",{class:["nav-tabs-custom","no-padding",{"col-lg-offset-2":!e.bShowMore,"col-lg-8":!e.bShowMore,"col-lg-offset-1":e.bShowMore,"col-lg-10":e.bShowMore}]},[t("ul",{staticClass:"nav nav-tabs"},[t("li",{staticClass:"active"},[t("a",{attrs:{href:"#base-config","data-toggle":"tab"}},[e._v(e._s(e.logoText)+" 信令服务配置")])]),e._v(" "),t("li",{on:{click:function(s){s.preventDefault(),e.getSMSList(s)}}},[t("a",{attrs:{href:"#sms-config","data-toggle":"tab"}},[e._v(e._s(e.logoText)+" 流媒体服务配置")])])]),e._v(" "),t("div",{staticClass:"tab-content"},[t("div",{staticClass:"tab-pane active",attrs:{id:"base-config"}},[t("form",{staticClass:"form-horizontal",attrs:{role:"form",autocomplete:"off"},on:{submit:function(s){s.preventDefault(),e.onSubmit(s)}}},[t("div",{class:[{"col-md-6":e.bShowMore,"col-md-12":!e.bShowMore}]},[t("div",{class:["form-group",{"has-error":e.errors.has("Serial")}]},[t("label",{staticClass:"col-sm-4 control-label",attrs:{for:"sip-serial"}},[e._v("SIP ID")]),e._v(" "),t("div",{staticClass:"col-sm-7"},[t("input",{directives:[{name:"validate",rawName:"v-validate",value:"required",expression:"'required'"},{name:"model",rawName:"v-model.trim",value:e.Serial,expression:"Serial",modifiers:{trim:!0}}],staticClass:"form-control",attrs:{id:"sip-serial",type:"text",name:"Serial","data-vv-as":"SIP ID"},domProps:{value:e.Serial},on:{input:function(s){s.target.composing||(e.Serial=s.target.value.trim())},blur:function(s){e.$forceUpdate()}}}),e._v(" "),t("span",{staticClass:"help-block"},[e._v(e._s(e.errors.first("Serial")))])])]),e._v(" "),t("div",{class:["form-group",{"has-error":e.errors.has("Realm")}]},[t("label",{staticClass:"col-sm-4 control-label",attrs:{for:"sip-realm"}},[e._v("SIP 域")]),e._v(" "),t("div",{staticClass:"col-sm-7"},[t("input",{directives:[{name:"validate",rawName:"v-validate",value:"required",expression:"'required'"},{name:"model",rawName:"v-model.trim",value:e.Realm,expression:"Realm",modifiers:{trim:!0}}],staticClass:"form-control",attrs:{id:"sip-realm",type:"text",name:"Realm","data-vv-as":"SIP 域"},domProps:{value:e.Realm},on:{input:function(s){s.target.composing||(e.Realm=s.target.value.trim())},blur:function(s){e.$forceUpdate()}}}),e._v(" "),t("span",{staticClass:"help-block"},[e._v(e._s(e.errors.first("Realm")))])])]),e._v(" "),t("div",{class:["form-group",{"has-error":e.errors.has("Host")}]},[t("label",{staticClass:"col-sm-4 control-label",attrs:{for:"sip-host"}},[e._v("SIP Host")]),e._v(" "),t("div",{staticClass:"col-sm-7"},[t("input",{directives:[{name:"validate",rawName:"v-validate",value:"required",expression:"'required'"},{name:"model",rawName:"v-model.trim",value:e.Host,expression:"Host",modifiers:{trim:!0}}],staticClass:"form-control",attrs:{type:"text",id:"sip-host",name:"Host","data-vv-as":"SIP Host"},domProps:{value:e.Host},on:{input:function(s){s.target.composing||(e.Host=s.target.value.trim())},blur:function(s){e.$forceUpdate()}}}),e._v(" "),t("span",{staticClass:"help-block"},[e._v(e._s(e.errors.first("Host")))])])]),e._v(" "),t("div",{class:["form-group",{"has-error":e.errors.has("Port")}]},[t("label",{staticClass:"col-sm-4 control-label",attrs:{for:"sip-port"}},[e._v("SIP 端口")]),e._v(" "),t("div",{staticClass:"col-sm-7"},[t("input",{directives:[{name:"validate",rawName:"v-validate",value:"required|numeric",expression:"'required|numeric'"},{name:"model",rawName:"v-model.trim",value:e.Port,expression:"Port",modifiers:{trim:!0}}],staticClass:"form-control",attrs:{type:"text",id:"sip-port",name:"Port","data-vv-as":"SIP 端口"},domProps:{value:e.Port},on:{input:function(s){s.target.composing||(e.Port=s.target.value.trim())},blur:function(s){e.$forceUpdate()}}}),e._v(" "),t("span",{staticClass:"help-block"},[e._v(e._s(e.errors.first("Port")))])])]),e._v(" "),t("div",{class:["form-group",{"has-error":e.errors.has("DevicePassword")}]},[t("label",{staticClass:"col-sm-4 control-label",attrs:{for:"sip-dev-pwd"}},[e._v("设备统一接入密码")]),e._v(" "),t("div",{staticClass:"col-sm-7"},[t("input",{directives:[{name:"model",rawName:"v-model.trim",value:e.DevicePassword,expression:"DevicePassword",modifiers:{trim:!0}}],staticClass:"form-control",attrs:{type:"text",id:"sip-dev-pwd",name:"DevicePassword","data-vv-as":"设备统一接入密码"},domProps:{value:e.DevicePassword},on:{input:function(s){s.target.composing||(e.DevicePassword=s.target.value.trim())},blur:function(s){e.$forceUpdate()}}}),e._v(" "),t("span",{staticClass:"help-block"},[e._v(e._s(e.errors.first("DevicePassword")))])])]),e._v(" "),t("div",{class:["form-group",{"has-error":e.errors.has("PreferStreamFmt")}],attrs:{title:"配置直播流优先使用的播放格式"}},[t("label",{staticClass:"col-sm-4 control-label",attrs:{for:"prefer-stream-fmt"}},[e._v("首选直播格式")]),e._v(" "),t("div",{staticClass:"col-sm-7"},[t("select",{directives:[{name:"model",rawName:"v-model.trim",value:e.PreferStreamFmt,expression:"PreferStreamFmt",modifiers:{trim:!0}}],staticClass:"form-control",attrs:{id:"prefer-stream-fmt",name:"PreferStreamFmt"},on:{change:function(s){var t=Array.prototype.filter.call(s.target.options,function(e){return e.selected}).map(function(e){return"_value"in e?e._value:e.value});e.PreferStreamFmt=s.target.multiple?t:t[0]}}},[t("option",{attrs:{value:""}},[e._v("自动选择")]),e._v(" "),t("option",{attrs:{value:"FLV"}},[e._v("FLV")]),e._v(" "),t("option",{attrs:{value:"WS_FLV"}},[e._v("WS_FLV")]),e._v(" "),t("option",{attrs:{value:"WEBRTC"}},[e._v("WEBRTC")]),e._v(" "),t("option",{attrs:{value:"HLS"}},[e._v("HLS")]),e._v(" "),t("option",{attrs:{value:"RTMP"}},[e._v("RTMP")])]),e._v(" "),t("span",{staticClass:"help-block"},[e._v(e._s(e.errors.first("PreferStreamFmt")))])])]),e._v(" "),t("div",{class:{"form-group":!0,"has-error":e.errors.has("HTTPSPort")},attrs:{title:"配置为0, 表示不开启HTTPS"}},[t("label",{staticClass:"col-sm-4 control-label",attrs:{for:"base-https-port"}},[e._v("HTTPS 端口(可选)")]),e._v(" "),t("div",{staticClass:"col-sm-7"},[t("input",{directives:[{name:"validate",rawName:"v-validate",value:"numeric",expression:"'numeric'"},{name:"model",rawName:"v-model.trim",value:e.HTTPSPort,expression:"HTTPSPort",modifiers:{trim:!0}}],staticClass:"form-control",attrs:{id:"base-https-port",type:"text",name:"HTTPSPort","data-vv-as":"HTTPS 端口",placeholder:"默认不开启HTTPS"},domProps:{value:e.HTTPSPort},on:{input:function(s){s.target.composing||(e.HTTPSPort=s.target.value.trim())},blur:function(s){e.$forceUpdate()}}}),e._v(" "),t("span",{staticClass:"help-block"},[e._v(e._s(e.errors.first("HTTPSPort")))])])]),e._v(" "),e.HTTPSPort&&"0"!=e.HTTPSPort?t("div",{class:{"form-group":!0,"has-error":e.errors.has("HTTPSCertFile")},attrs:{title:"配置 Cert 证书路径，绝对路径"}},[t("label",{staticClass:"col-sm-4 control-label",attrs:{for:"base-https-cert-file"}},[e._v("HTTPS Cert 证书路径")]),e._v(" "),t("div",{staticClass:"col-sm-7"},[t("input",{directives:[{name:"model",rawName:"v-model.trim",value:e.HTTPSCertFile,expression:"HTTPSCertFile",modifiers:{trim:!0}}],staticClass:"form-control",attrs:{id:"base-https-cert-file",type:"text",spellcheck:"false",autocomplete:"off",name:"HTTPSCertFile",placeholder:"配置cert证书绝对路径"},domProps:{value:e.HTTPSCertFile},on:{input:function(s){s.target.composing||(e.HTTPSCertFile=s.target.value.trim())},blur:function(s){e.$forceUpdate()}}}),e._v(" "),t("span",{staticClass:"help-block"},[e._v(e._s(e.errors.first("HTTPSCertFile")))])])]):e._e(),e._v(" "),e.HTTPSPort&&"0"!=e.HTTPSPort?t("div",{class:{"form-group":!0,"has-error":e.errors.has("HTTPSKeyFile")},attrs:{title:"配置 Cert 证书路径，绝对路径"}},[t("label",{staticClass:"col-sm-4 control-label",attrs:{for:"base-https-key-file"}},[e._v("HTTPS Key 证书路径")]),e._v(" "),t("div",{staticClass:"col-sm-7"},[t("input",{directives:[{name:"model",rawName:"v-model.trim",value:e.HTTPSKeyFile,expression:"HTTPSKeyFile",modifiers:{trim:!0}}],staticClass:"form-control",attrs:{id:"base-https-key-file",type:"text",spellcheck:"false",autocomplete:"off",name:"HTTPSKeyFile",placeholder:"配置key证书绝对路径"},domProps:{value:e.HTTPSKeyFile},on:{input:function(s){s.target.composing||(e.HTTPSKeyFile=s.target.value.trim())},blur:function(s){e.$forceUpdate()}}}),e._v(" "),t("span",{staticClass:"help-block"},[e._v(e._s(e.errors.first("HTTPSKeyFile")))])])]):e._e(),e._v(" "),t("div",{staticClass:"form-group"},[t("label",{staticClass:"col-sm-4 control-label"},[e._v("接入控制(可选)")]),e._v(" "),t("div",{staticClass:"col-sm-7 checkbox"},[t("router-link",{attrs:{to:"/black/1"}},[t("i",{staticClass:"fa fa-calendar-times-o cfg-black-list",attrs:{title:"设备接入黑名单维护","aria-hidden":"true"}},[e._v("  黑名单  ")])]),e._v(" "),t("router-link",{attrs:{to:"/white/1"}},[t("i",{staticClass:"fa fa-calendar-check-o cfg-white-list",attrs:{title:"设备接入白名单维护","aria-hidden":"true"}},[e._v("  白名单")])]),e._v(" "),t("span",{staticClass:"help-block"})],1)]),e._v(" "),t("div",{staticClass:"form-group"},[t("label",{staticClass:"col-sm-4 control-label"},[e._v("其他配置")]),e._v(" "),t("div",{staticClass:"col-sm-7 checkbox"},[t("el-checkbox",{staticStyle:{"margin-left":"-19px","margin-top":"-5px"},attrs:{title:"开启后接口调用会校验登录",size:"small",name:"APIAuth"},model:{value:e.APIAuth,callback:function(s){e.APIAuth="string"==typeof s?s.trim():s},expression:"APIAuth"}},[e._v("\r\n                                    HTTP 接口鉴权  \r\n                                ")]),e._v(" "),t("el-checkbox",{staticStyle:{"margin-left":"-19px","margin-top":"-5px"},attrs:{title:"开启后不需要事先通过接口拉流, 直播地址直接可用",size:"small",name:"AllowStreamStartByURL"},model:{value:e.AllowStreamStartByURL,callback:function(s){e.AllowStreamStartByURL="string"==typeof s?s.trim():s},expression:"AllowStreamStartByURL"}},[e._v("\r\n                                    允许直播地址拉流  \r\n                                ")]),e._v(" "),t("el-checkbox",{staticStyle:{"margin-left":"-19px","margin-top":"-5px"},attrs:{size:"small",name:"SIPLog"},model:{value:e.SIPLog,callback:function(s){e.SIPLog="string"==typeof s?s.trim():s},expression:"SIPLog"}},[e._v("信令日志  ")]),e._v(" "),t("span",{staticClass:"help-block"})],1)]),e._v(" "),e.bShowMore?t("br"):e._e(),e._v(" "),t("div",{staticClass:"form-group",staticStyle:{"font-weight":"bold","padding-top":"15px","padding-bottom":"10px"}},[t("div",{staticClass:"col-sm-offset-4 col-sm-7"},[t("a",{staticClass:"form-control-static text-primary",attrs:{href:"#"},on:{click:function(s){s.preventDefault(),e.bShowMore=!e.bShowMore}}},[e._v("\r\n                                    "+e._s(e.bShowMore?"<<  关闭更多配置":"显示更多配置  >>")+"\r\n                                ")])])])]),e._v(" "),e.bShowMore?t("div",{staticClass:"col-md-6"},[t("div",{class:["form-group",{"has-error":e.errors.has("TimeServer")}]},[t("label",{staticClass:"col-sm-4 control-label",attrs:{for:"sip-time-server"}},[e._v("校时源(可选)")]),e._v(" "),t("div",{staticClass:"col-sm-7"},[t("input",{directives:[{name:"validate",rawName:"v-validate"},{name:"model",rawName:"v-model.trim",value:e.TimeServer,expression:"TimeServer",modifiers:{trim:!0}}],staticClass:"form-control",attrs:{type:"text",id:"sip-time-server",name:"TimeServer","data-vv-as":"校时源",placeholder:"上级国标编号/NTP"},domProps:{value:e.TimeServer},on:{input:function(s){s.target.composing||(e.TimeServer=s.target.value.trim())},blur:function(s){e.$forceUpdate()}}}),e._v(" "),t("span",{staticClass:"help-block"},[e._v(e._s(e.errors.first("TimeServer")))])])]),e._v(" "),t("div",{staticClass:"form-group"},[t("label",{staticClass:"col-sm-4 control-label",attrs:{for:"black-ip-list"}},[e._v("黑名单 IP(可选)")]),e._v(" "),t("div",{staticClass:"col-sm-7"},[t("input",{directives:[{name:"model",rawName:"v-model.trim",value:e.BlackIPList,expression:"BlackIPList",modifiers:{trim:!0}}],staticClass:"form-control",attrs:{type:"text",id:"black-ip-list",name:"BlackIPList","data-vv-as":"黑名单 IP",placeholder:"选填"},domProps:{value:e.BlackIPList},on:{input:function(s){s.target.composing||(e.BlackIPList=s.target.value.trim())},blur:function(s){e.$forceUpdate()}}}),e._v(" "),t("span",{staticClass:"help-block"})])]),e._v(" "),t("div",{staticClass:"form-group"},[t("label",{staticClass:"col-sm-4 control-label",attrs:{for:"black-ua-list"}},[e._v("黑名单 UA(可选)")]),e._v(" "),t("div",{staticClass:"col-sm-7"},[t("input",{directives:[{name:"model",rawName:"v-model.trim",value:e.BlackUAList,expression:"BlackUAList",modifiers:{trim:!0}}],staticClass:"form-control",attrs:{type:"text",id:"black-ua-list",name:"BlackUAList","data-vv-as":"黑名单 UA",placeholder:"选填"},domProps:{value:e.BlackUAList},on:{input:function(s){s.target.composing||(e.BlackUAList=s.target.value.trim())},blur:function(s){e.$forceUpdate()}}}),e._v(" "),t("span",{staticClass:"help-block"})])]),e._v(" "),t("div",{staticClass:"form-group"},[t("label",{staticClass:"col-sm-4 control-label"},[e._v("全局过滤通道类型")]),e._v(" "),t("div",{staticClass:"col-sm-7"},[t("el-select",{staticStyle:{width:"100%"},attrs:{size:"medium",multiple:"",filterable:"","allow-create":"","default-first-option":"",placeholder:"选填, 需要丢弃的通道类型"},model:{value:e.dropChannelTypes,callback:function(s){e.dropChannelTypes="string"==typeof s?s.trim():s},expression:"dropChannelTypes"}},e._l(e.innerChannelTypes,function(e,s){return t("el-option",{key:s,attrs:{label:s+" - "+e,value:s}})}))],1)]),e._v(" "),t("div",{staticClass:"form-group"},[t("label",{staticClass:"col-sm-4 control-label"},[e._v("全局订阅项目")]),e._v(" "),t("div",{staticClass:"col-sm-7 checkbox"},[t("el-checkbox",{staticStyle:{"margin-left":"-19px","margin-top":"-5px"},attrs:{size:"small",name:"global_catalog_subscribe"},model:{value:e.global_catalog_subscribe,callback:function(s){e.global_catalog_subscribe="string"==typeof s?s.trim():s},expression:"global_catalog_subscribe"}},[e._v("\r\n                                    目录订阅  \r\n                                ")]),e._v(" "),t("el-checkbox",{staticStyle:{"margin-left":"-19px","margin-top":"-5px"},attrs:{size:"small",name:"global_alarm_subscribe"},model:{value:e.global_alarm_subscribe,callback:function(s){e.global_alarm_subscribe="string"==typeof s?s.trim():s},expression:"global_alarm_subscribe"}},[e._v("\r\n                                    报警订阅  \r\n                                ")]),e._v(" "),t("el-checkbox",{staticStyle:{"margin-left":"-19px","margin-top":"-5px"},attrs:{size:"small",name:"global_position_subscribe"},model:{value:e.global_position_subscribe,callback:function(s){e.global_position_subscribe="string"==typeof s?s.trim():s},expression:"global_position_subscribe"}},[e._v("\r\n                                    位置订阅\r\n                                ")]),e._v(" "),t("span",{staticClass:"help-block"})],1)]),e._v(" "),t("div",{staticClass:"form-group"},[t("label",{staticClass:"col-sm-4 control-label"},[e._v("全局通道开启音频")]),e._v(" "),t("div",{staticClass:"col-sm-7 checkbox"},[t("el-switch",{model:{value:e.GlobalChannelAudio,callback:function(s){e.GlobalChannelAudio="string"==typeof s?s.trim():s},expression:"GlobalChannelAudio"}}),e._v(" "),t("span",{staticClass:"help-block"})],1)]),e._v(" "),t("div",{staticClass:"form-group"},[t("label",{staticClass:"col-sm-4 control-label"},[e._v("全局通道开启分享")]),e._v(" "),t("div",{staticClass:"col-sm-7 checkbox"},[t("el-switch",{model:{value:e.GlobalChannelShared,callback:function(s){e.GlobalChannelShared="string"==typeof s?s.trim():s},expression:"GlobalChannelShared"}}),e._v(" "),t("span",{staticClass:"help-block"})],1)])]):e._e(),e._v(" "),t("div",{staticClass:"col-md-12"},[t("div",{staticClass:"form-group"},[t("div",{class:[{"col-sm-offset-5":e.bShowMore,"col-sm-offset-4":!e.bShowMore,"col-sm-7":!0}]},[t("button",{staticClass:"btn btn-primary",attrs:{type:"submit",disabled:e.isBasicNoChange||e.errors.any()||e.bCommitting}},[e._v("保存")])])])]),e._v(" "),t("div",{staticClass:"clearfix"})])]),e._v(" "),t("div",{staticClass:"tab-pane",attrs:{id:"sms-config"}},[t("form",{staticClass:"form-horizontal",attrs:{role:"form",autocomplete:"off"},on:{submit:function(s){s.preventDefault(),e.onSubmitSMS(s)}}},[e.smss.length<=0?t("div",{staticClass:"form-group"},[t("div",{staticClass:"col-sm-12"},[t("div",{staticClass:"alert text-center no-margin"},[e._v("SMS "+e._s(e.smstip))])])]):e._e(),e._v(" "),t("div",{class:[{"col-md-6":e.bShowMore,"col-md-12":!e.bShowMore}]},[e.smss.length>0?t("div",{staticClass:"form-group"},[t("label",{staticClass:"col-sm-4 control-label"},[e._v("SMS 服务")]),e._v(" "),t("div",{staticClass:"col-sm-7"},[t("select",{directives:[{name:"model",rawName:"v-model.trim",value:e.smsserial,expression:"smsserial",modifiers:{trim:!0}}],staticClass:"form-control",on:{change:[function(s){var t=Array.prototype.filter.call(s.target.options,function(e){return e.selected}).map(function(e){return"_value"in e?e._value:e.value});e.smsserial=s.target.multiple?t:t[0]},e.smschange]}},e._l(e.smss,function(s,a){return t("option",{key:a,domProps:{value:s.Serial}},[e._v(e._s(s.Serial))])}))])]):e._e(),e._v(" "),void 0!=e.smsbaseconfig.Host&&e.smss.length>0?t("div",[t("div",{class:["form-group",{"has-error":e.errors.has("smsHost")}],attrs:{title:"内部通信收流. 启用外网IP收流后, 此处配置信令服务可访问的局域网IP如：127.0.0.1"}},[t("label",{staticClass:"col-sm-4 control-label"},[e._v("本地|内网 IP")]),e._v(" "),t("div",{staticClass:"col-sm-7"},[t("input",{directives:[{name:"validate",rawName:"v-validate",value:"required",expression:"'required'"},{name:"model",rawName:"v-model.trim",value:e.smsbaseconfig.Host,expression:"smsbaseconfig.Host",modifiers:{trim:!0}}],staticClass:"form-control",attrs:{type:"text",placeholder:"内部通信收流. 启用外网IP收流后, 此处配置信令服务可访问的局域网IP如：127.0.0.1",name:"smsHost","data-vv-as":"本地|内网 IP"},domProps:{value:e.smsbaseconfig.Host},on:{input:function(s){s.target.composing||e.$set(e.smsbaseconfig,"Host",s.target.value.trim())},blur:function(s){e.$forceUpdate()}}}),e._v(" "),t("span",{staticClass:"help-block"},[e._v(e._s(e.errors.first("smsHost")))])])]),e._v(" "),t("div",{class:["form-group",{"has-error":e.errors.has("WanIP")}]},[t("label",{staticClass:"col-sm-4 control-label"},[e._v("外网 IP(可选)")]),e._v(" "),t("div",{staticClass:"col-sm-7"},[t("input",{directives:[{name:"model",rawName:"v-model.trim",value:e.smsbaseconfig.WanIP,expression:"smsbaseconfig.WanIP",modifiers:{trim:!0}}],staticClass:"form-control",attrs:{type:"text",name:"WanIP","data-vv-as":"外网 IP",placeholder:"选填"},domProps:{value:e.smsbaseconfig.WanIP},on:{input:function(s){s.target.composing||e.$set(e.smsbaseconfig,"WanIP",s.target.value.trim())},blur:function(s){e.$forceUpdate()}}}),e._v(" "),t("span",{staticClass:"help-block"},[e._v(e._s(e.errors.first("WanIP")))])])]),e._v(" "),t("div",{class:["form-group",{"has-error":e.errors.has("RecordDir")}]},[t("label",{staticClass:"col-sm-4 control-label"},[e._v("云录像目录")]),e._v(" "),t("div",{staticClass:"col-sm-7"},[t("input",{directives:[{name:"model",rawName:"v-model.trim",value:e.smsbaseconfig.RecordDir,expression:"smsbaseconfig.RecordDir",modifiers:{trim:!0}}],staticClass:"form-control",attrs:{type:"text",name:"RecordDir","data-vv-as":"云录像目录"},domProps:{value:e.smsbaseconfig.RecordDir},on:{input:function(s){s.target.composing||e.$set(e.smsbaseconfig,"RecordDir",s.target.value.trim())},blur:function(s){e.$forceUpdate()}}}),e._v(" "),t("span",{staticClass:"help-block"},[e._v(e._s(e.errors.first("RecordDir")))])])]),e._v(" "),t("div",{staticClass:"form-group"},[t("label",{staticClass:"col-sm-4 control-label"},[e._v("RTSP 端口(可选)")]),e._v(" "),t("div",{staticClass:"col-sm-7"},[t("input",{directives:[{name:"validate",rawName:"v-validate",value:"numeric",expression:"'numeric'"},{name:"model",rawName:"v-model.trim",value:e.smsbaseconfig.RTSPPort,expression:"smsbaseconfig.RTSPPort",modifiers:{trim:!0}}],staticClass:"form-control",attrs:{type:"text",name:"RTSPPort","data-vv-as":"RTSP 端口",placeholder:"选填"},domProps:{value:e.smsbaseconfig.RTSPPort},on:{input:function(s){s.target.composing||e.$set(e.smsbaseconfig,"RTSPPort",s.target.value.trim())},blur:function(s){e.$forceUpdate()}}}),e._v(" "),t("span",{staticClass:"help-block"},[e._v(e._s(e.errors.first("RTSPPort")))])])]),e._v(" "),t("div",{class:["form-group",{"has-error":e.errors.has("RTMPPort")}]},[t("label",{staticClass:"col-sm-4 control-label"},[e._v("RTMP 端口")]),e._v(" "),t("div",{staticClass:"col-sm-7"},[t("input",{directives:[{name:"validate",rawName:"v-validate",value:"required|numeric",expression:"'required|numeric'"},{name:"model",rawName:"v-model.trim",value:e.smsbaseconfig.RTMPPort,expression:"smsbaseconfig.RTMPPort",modifiers:{trim:!0}}],staticClass:"form-control",attrs:{type:"text",name:"RTMPPort","data-vv-as":"RTMP 端口"},domProps:{value:e.smsbaseconfig.RTMPPort},on:{input:function(s){s.target.composing||e.$set(e.smsbaseconfig,"RTMPPort",s.target.value.trim())},blur:function(s){e.$forceUpdate()}}}),e._v(" "),t("span",{staticClass:"help-block"},[e._v(e._s(e.errors.first("RTMPPort")))])])]),e._v(" "),t("div",{staticClass:"form-group"},[t("label",{staticClass:"col-sm-4 control-label"},[e._v("其他配置")]),e._v(" "),t("div",{staticClass:"col-sm-7 checkbox"},[t("el-checkbox",{staticStyle:{"margin-left":"-19px","margin-top":"-5px"},attrs:{title:"加快启播速度,相应也会增大一点延时",size:"small",name:"GOPCache"},model:{value:e.smsbaseconfig.GOPCache,callback:function(s){e.$set(e.smsbaseconfig,"GOPCache","string"==typeof s?s.trim():s)},expression:"smsbaseconfig.GOPCache"}},[e._v("直播秒开")]),e._v(" "),e.bShowMore?t("br"):e._e(),e.bShowMore?t("br"):e._e(),e.bShowMore?e._e():t("span",[e._v("    ")]),e._v(" "),t("el-checkbox",{staticStyle:{"margin-left":"-19px","margin-top":"-5px"},attrs:{size:"small",name:"HLS"},model:{value:e.smsbaseconfig.HLS,callback:function(s){e.$set(e.smsbaseconfig,"HLS","string"==typeof s?s.trim():s)},expression:"smsbaseconfig.HLS"}},[e._v("HLS")]),e._v(" "),e.bShowMore?t("br"):e._e(),e.bShowMore?t("br"):e._e(),e.bShowMore?e._e():t("span",[e._v("    ")]),e._v(" "),t("el-checkbox",{staticStyle:{"margin-left":"-19px","margin-top":"-5px"},attrs:{size:"small",name:"SIPLog"},model:{value:e.smsbaseconfig.SIPLog,callback:function(s){e.$set(e.smsbaseconfig,"SIPLog","string"==typeof s?s.trim():s)},expression:"smsbaseconfig.SIPLog"}},[e._v("信令日志")]),e._v(" "),e.bShowMore?t("br"):e._e(),e.bShowMore?t("br"):e._e(),e.bShowMore?e._e():t("span",[e._v("    ")]),e._v(" "),t("el-checkbox",{staticStyle:{"margin-left":"-19px","margin-top":"-5px"},attrs:{size:"small",name:"UseWanIPRecvStream"},model:{value:e.smsbaseconfig.UseWanIPRecvStream,callback:function(s){e.$set(e.smsbaseconfig,"UseWanIPRecvStream","string"==typeof s?s.trim():s)},expression:"smsbaseconfig.UseWanIPRecvStream"}},[e._v("外网 IP 收流")])],1)]),e._v(" "),e.bShowMore?t("br"):e._e(),e._v(" "),t("div",{staticClass:"form-group",staticStyle:{"font-weight":"bold","padding-top":"15px","padding-bottom":"10px"}},[t("div",{staticClass:"col-sm-offset-4 col-sm-7"},[t("a",{staticClass:"form-control-static text-primary",attrs:{href:"#"},on:{click:function(s){s.preventDefault(),e.bShowMore=!e.bShowMore}}},[e._v("\r\n                                        "+e._s(e.bShowMore?"<<  关闭更多配置":"显示更多配置  >>")+"\r\n                                    ")])])])]):e._e()]),e._v(" "),e.bShowMore&&void 0!=e.smsbaseconfig.Host&&e.smss.length>0?t("div",{staticClass:"col-md-6"},[t("div",{class:["form-group",{"has-error":e.errors.has("smsSerial")}],attrs:{title:"内部通信使用"}},[t("label",{staticClass:"col-sm-4 control-label"},[e._v("SMS ID")]),e._v(" "),t("div",{staticClass:"col-sm-7"},[t("input",{directives:[{name:"validate",rawName:"v-validate",value:"required",expression:"'required'"},{name:"model",rawName:"v-model.trim",value:e.smsbaseconfig.Serial,expression:"smsbaseconfig.Serial",modifiers:{trim:!0}}],staticClass:"form-control",attrs:{type:"text",name:"smsSerial","data-vv-as":"SIP ID"},domProps:{value:e.smsbaseconfig.Serial},on:{input:function(s){s.target.composing||e.$set(e.smsbaseconfig,"Serial",s.target.value.trim())},blur:function(s){e.$forceUpdate()}}}),e._v(" "),t("span",{staticClass:"help-block"},[e._v(e._s(e.errors.first("smsSerial")))])])]),e._v(" "),t("div",{class:["form-group",{"has-error":e.errors.has("smsRealm")}],attrs:{title:"内部通信使用"}},[t("label",{staticClass:"col-sm-4 control-label"},[e._v("SMS 域")]),e._v(" "),t("div",{staticClass:"col-sm-7"},[t("input",{directives:[{name:"validate",rawName:"v-validate",value:"required",expression:"'required'"},{name:"model",rawName:"v-model.trim",value:e.smsbaseconfig.Realm,expression:"smsbaseconfig.Realm",modifiers:{trim:!0}}],staticClass:"form-control",attrs:{type:"text",name:"smsRealm","data-vv-as":"SIP 域"},domProps:{value:e.smsbaseconfig.Realm},on:{input:function(s){s.target.composing||e.$set(e.smsbaseconfig,"Realm",s.target.value.trim())},blur:function(s){e.$forceUpdate()}}}),e._v(" "),t("span",{staticClass:"help-block"},[e._v(e._s(e.errors.first("smsRealm")))])])]),e._v(" "),t("div",{class:["form-group",{"has-error":e.errors.has("smsPort")}],attrs:{title:"内部通信使用"}},[t("label",{staticClass:"col-sm-4 control-label"},[e._v("SMS 端口")]),e._v(" "),t("div",{staticClass:"col-sm-7"},[t("input",{directives:[{name:"validate",rawName:"v-validate",value:"required|numeric",expression:"'required|numeric'"},{name:"model",rawName:"v-model.trim",value:e.smsbaseconfig.Port,expression:"smsbaseconfig.Port",modifiers:{trim:!0}}],staticClass:"form-control",attrs:{type:"text",name:"smsPort","data-vv-as":"SIP 端口"},domProps:{value:e.smsbaseconfig.Port},on:{input:function(s){s.target.composing||e.$set(e.smsbaseconfig,"Port",s.target.value.trim())},blur:function(s){e.$forceUpdate()}}}),e._v(" "),t("span",{staticClass:"help-block"},[e._v(e._s(e.errors.first("smsPort")))])])]),e._v(" "),t("div",{staticClass:"form-group"},[t("label",{staticClass:"col-sm-4 control-label"},[e._v("分组 ID(可选)")]),e._v(" "),t("div",{staticClass:"col-sm-7"},[t("input",{directives:[{name:"model",rawName:"v-model.trim",value:e.smsbaseconfig.GroupID,expression:"smsbaseconfig.GroupID",modifiers:{trim:!0}}],staticClass:"form-control",attrs:{type:"text",name:"GroupID","data-vv-as":"分组 ID",placeholder:"选填"},domProps:{value:e.smsbaseconfig.GroupID},on:{input:function(s){s.target.composing||e.$set(e.smsbaseconfig,"GroupID",s.target.value.trim())},blur:function(s){e.$forceUpdate()}}}),e._v(" "),t("span",{staticClass:"help-block"},[e._v(e._s(e.errors.first("GroupID")))])])]),e._v(" "),t("div",{staticClass:"form-group"},[t("label",{staticClass:"col-sm-4 control-label"},[e._v("录像保留(天数)")]),e._v(" "),t("div",{staticClass:"col-sm-7"},[t("input",{directives:[{name:"model",rawName:"v-model.trim",value:e.smsbaseconfig.CleanOverDays,expression:"smsbaseconfig.CleanOverDays",modifiers:{trim:!0}}],staticClass:"form-control",attrs:{type:"number",title:"默认不配置会永久保存，直到触发下面的清理阀值",name:"CleanOverDays","data-vv-as":"录像保留(天数)",placeholder:"选填"},domProps:{value:e.smsbaseconfig.CleanOverDays},on:{input:function(s){s.target.composing||e.$set(e.smsbaseconfig,"CleanOverDays",s.target.value.trim())},blur:function(s){e.$forceUpdate()}}}),e._v(" "),t("span",{staticClass:"help-block"},[e._v(e._s(e.errors.first("CleanOverDays")))])])]),e._v(" "),t("div",{staticClass:"form-group"},[t("label",{staticClass:"col-sm-4 control-label"},[e._v("存储清理阀值(%)")]),e._v(" "),t("div",{staticClass:"col-sm-7"},[t("input",{directives:[{name:"model",rawName:"v-model.trim",value:e.smsbaseconfig.CleanFreespacePercent,expression:"smsbaseconfig.CleanFreespacePercent",modifiers:{trim:!0}}],staticClass:"form-control",attrs:{type:"number",name:"CleanFreespacePercent","data-vv-as":"存储清理阀值(%)",placeholder:"选填"},domProps:{value:e.smsbaseconfig.CleanFreespacePercent},on:{input:function(s){s.target.composing||e.$set(e.smsbaseconfig,"CleanFreespacePercent",s.target.value.trim())},blur:function(s){e.$forceUpdate()}}}),e._v(" "),t("span",{staticClass:"help-block"},[e._v(e._s(e.errors.first("CleanFreespacePercent")))])])]),e._v(" "),t("div",{staticClass:"form-group"},[t("label",{staticClass:"col-sm-4 control-label"},[e._v("存储清理阀值(MB)")]),e._v(" "),t("div",{staticClass:"col-sm-7"},[t("input",{directives:[{name:"model",rawName:"v-model.trim",value:e.smsbaseconfig.CleanFreespaceSize,expression:"smsbaseconfig.CleanFreespaceSize",modifiers:{trim:!0}}],staticClass:"form-control",attrs:{type:"number",name:"CleanFreespaceSize","data-vv-as":"存储清理阀值(MB)",placeholder:"选填"},domProps:{value:e.smsbaseconfig.CleanFreespaceSize},on:{input:function(s){s.target.composing||e.$set(e.smsbaseconfig,"CleanFreespaceSize",s.target.value.trim())},blur:function(s){e.$forceUpdate()}}}),e._v(" "),t("span",{staticClass:"help-block"},[e._v(e._s(e.errors.first("CleanFreespaceSize")))])])]),e._v(" "),t("div",{staticClass:"form-group"},[t("label",{staticClass:"col-sm-4 control-label"},[e._v("收流端口区间(TCP)")]),e._v(" "),t("div",{staticClass:"col-sm-7"},[t("input",{directives:[{name:"model",rawName:"v-model.trim",value:e.smsbaseconfig.TCPPortRange,expression:"smsbaseconfig.TCPPortRange",modifiers:{trim:!0}}],staticClass:"form-control",attrs:{type:"text",name:"TCPPortRange","data-vv-as":"收流TCP端口区间",placeholder:"选填"},domProps:{value:e.smsbaseconfig.TCPPortRange},on:{input:function(s){s.target.composing||e.$set(e.smsbaseconfig,"TCPPortRange",s.target.value.trim())},blur:function(s){e.$forceUpdate()}}}),e._v(" "),t("span",{staticClass:"help-block"},[e._v(e._s(e.errors.first("TCPPortRange")))])])]),e._v(" "),t("div",{staticClass:"form-group"},[t("label",{staticClass:"col-sm-4 control-label"},[e._v("收流端口区间(UDP)")]),e._v(" "),t("div",{staticClass:"col-sm-7"},[t("input",{directives:[{name:"model",rawName:"v-model.trim",value:e.smsbaseconfig.UDPPortRange,expression:"smsbaseconfig.UDPPortRange",modifiers:{trim:!0}}],staticClass:"form-control",attrs:{type:"text",name:"UDPPortRange","data-vv-as":"收流UDP端口区间",placeholder:"选填"},domProps:{value:e.smsbaseconfig.UDPPortRange},on:{input:function(s){s.target.composing||e.$set(e.smsbaseconfig,"UDPPortRange",s.target.value.trim())},blur:function(s){e.$forceUpdate()}}}),e._v(" "),t("span",{staticClass:"help-block"},[e._v(e._s(e.errors.first("UDPPortRange")))])])]),e._v(" "),t("div",{staticClass:"form-group"},[t("label",{staticClass:"col-sm-4 control-label"},[e._v("WebRTC端口区间(UDP)")]),e._v(" "),t("div",{staticClass:"col-sm-7"},[t("input",{directives:[{name:"model",rawName:"v-model.trim",value:e.smsbaseconfig.RTCPortRange,expression:"smsbaseconfig.RTCPortRange",modifiers:{trim:!0}}],staticClass:"form-control",attrs:{type:"text",name:"RTCPortRange","data-vv-as":"WebRTC播放端口区间(UDP)",placeholder:"选填"},domProps:{value:e.smsbaseconfig.RTCPortRange},on:{input:function(s){s.target.composing||e.$set(e.smsbaseconfig,"RTCPortRange",s.target.value.trim())},blur:function(s){e.$forceUpdate()}}}),e._v(" "),t("span",{staticClass:"help-block"},[e._v(e._s(e.errors.first("RTCPortRange")))])])])]):e._e(),e._v(" "),e.smss.length>0?t("div",{staticClass:"col-md-12"},[t("div",{staticClass:"form-group"},[t("div",{class:[{"col-sm-offset-5":e.bShowMore,"col-sm-offset-4":!e.bShowMore,"col-sm-7":!0}]},[t("button",{staticClass:"btn btn-primary",attrs:{type:"submit",disabled:e.isSMSNoChange||e.errors.any()||e.bCommitting}},[e._v("保存")])])])]):e._e(),e._v(" "),t("div",{staticClass:"clearfix"})])])])])])},r=[],o={render:a,staticRenderFns:r};s.a=o},"/ld9":function(e,s,t){"use strict";function a(e){return function(){var s=e.apply(this,arguments);return new Promise(function(e,t){function a(r,o){try{var i=s[r](o),l=i.value}catch(e){return void t(e)}if(!i.done)return Promise.resolve(l).then(function(e){a("next",e)},function(e){a("throw",e)});e(l)}return a("next")})}}Object.defineProperty(s,"__esModule",{value:!0});var r=Object.assign||function(e){for(var s=1;s<arguments.length;s++){var t=arguments[s];for(var a in t)Object.prototype.hasOwnProperty.call(t,a)&&(e[a]=t[a])}return e},o=t("0iPh"),i=function(e){return e&&e.__esModule?e:{default:e}}(o),l=t("NYxO");s.default={data:function(){return{bCommitting:!1,Serial:"",Realm:"",Host:"",Port:0,DevicePassword:"",TimeServer:"",PreferStreamFmt:"",AckTimeout:0,KeepaliveTimeout:0,APIAuth:!1,SIPLog:!1,AllowStreamStartByURL:!1,BlackIPList:"",BlackUAList:"",remoteBasicData:"",remoteSMSData:"",smsserial:"",smss:[],sms:{},smsbaseconfig:{},smstip:"流媒体服务尚未启动",HTTPSPort:"",HTTPSCertFile:"",HTTPSKeyFile:"",timer:0,bShowMore:!1,GlobalChannelShared:!1,GlobalChannelAudio:!1,GlobalDeviceCatalogSubscribeInterval:0,GlobalDeviceAlarmSubscribeInterval:0,GlobalDevicePositionSubscribeInterval:0,globalDeviceCatalogSubscribeInterval:0,globalDeviceAlarmSubscribeInterval:0,globalDevicePositionSubscribeInterval:0,global_catalog_subscribe:!1,global_alarm_subscribe:!1,global_position_subscribe:!1,DropChannelType:"",dropChannelTypes:[],innerChannelTypes:{134:"报警输入",135:"报警输出",136:"语音输入",137:"语音输出",200:"中心信令",215:"业务分组",216:"虚拟组织"}}},mounted:function(){this.getBaseConfig()},beforeDestroy:function(){this.timer&&(clearTimeout(this.timer),this.timer=0)},methods:{onSubmit:function(){var e=this;return a(regeneratorRuntime.mark(function s(){var t,a;return regeneratorRuntime.wrap(function(s){for(;;)switch(s.prev=s.next){case 0:return s.next=2,e.$validator.validateAll();case 2:if(t=s.sent){s.next=8;break}return a=e.errors.items[0],e.$message({type:"error",message:a.msg}),(0,i.default)("[name="+a.field+"]").focus(),s.abrupt("return");case 8:e.bCommitting=!0,i.default.get("/api/v1/setbaseconfig",e.getBasicCommitObject()).then(function(s){e.$message({type:"success",message:"配置成功！"})}).always(function(){e.getBaseConfig(),e.bCommitting=!1});case 10:case"end":return s.stop()}},s,e)}))()},onSubmitSMS:function(){var e=this;return a(regeneratorRuntime.mark(function s(){var t,a;return regeneratorRuntime.wrap(function(s){for(;;)switch(s.prev=s.next){case 0:return s.next=2,e.$validator.validateAll();case 2:if(t=s.sent){s.next=8;break}return a=e.errors.items[0],e.$message({type:"error",message:a.msg}),(0,i.default)("[name="+a.field+"]").focus(),s.abrupt("return");case 8:if(!(""!=e.smsbaseconfig.CleanOverDays&&e.smsbaseconfig.CleanOverDays<0)){s.next=11;break}return e.$message({type:"error",message:"录像保留(天数)不能小于0"}),s.abrupt("return");case 11:if(!(e.smsbaseconfig.CleanFreespacePercent<0)){s.next=14;break}return e.$message({type:"error",message:"存储清理阀值(百分比)不能小于0"}),s.abrupt("return");case 14:if(!(e.smsbaseconfig.CleanFreespaceSize<0)){s.next=17;break}return e.$message({type:"error",message:"存储清理阀值(MB))不能小于0"}),s.abrupt("return");case 17:""==e.smsbaseconfig.CleanOverDays&&(e.smsbaseconfig.CleanOverDays=0),e.bCommitting=!0,i.default.get("/api/v1/sms/setbaseconfig",e.smsbaseconfig).then(function(s){e.$message({type:"success",message:"配置保存中,请稍后..."})}).always(function(){e.smstip="配置保存中,请稍后...",e.smsserial="",e.getSMSList(),e.bCommitting=!1});case 20:case"end":return s.stop()}},s,e)}))()},getBasicCommitObject:function(){return this.DropChannelType=this.dropChannelTypes.join(","),this.global_catalog_subscribe&&this.GlobalDeviceCatalogSubscribeInterval<=0?this.globalDeviceCatalogSubscribeInterval>0?this.GlobalDeviceCatalogSubscribeInterval=this.globalDeviceCatalogSubscribeInterval:this.GlobalDeviceCatalogSubscribeInterval=3600:!this.global_catalog_subscribe&&this.GlobalDeviceCatalogSubscribeInterval>0&&(this.GlobalDeviceCatalogSubscribeInterval=0),this.global_alarm_subscribe&&this.GlobalDeviceAlarmSubscribeInterval<=0?this.globalDeviceAlarmSubscribeInterval>0?this.GlobalDeviceAlarmSubscribeInterval=this.globalDeviceAlarmSubscribeInterval:this.GlobalDeviceAlarmSubscribeInterval=3600:!this.global_alarm_subscribe&&this.GlobalDeviceAlarmSubscribeInterval>0&&(this.GlobalDeviceAlarmSubscribeInterval=0),this.global_position_subscribe&&this.GlobalDevicePositionSubscribeInterval<=0?this.globalDevicePositionSubscribeInterval>0?this.GlobalDevicePositionSubscribeInterval=this.globalDevicePositionSubscribeInterval:this.GlobalDevicePositionSubscribeInterval=3600:!this.global_position_subscribe&&this.GlobalDevicePositionSubscribeInterval>0&&(this.GlobalDevicePositionSubscribeInterval=0),{Serial:this.Serial,Realm:this.Realm,Host:this.Host,Port:this.Port,DevicePassword:this.DevicePassword,TimeServer:this.TimeServer,PreferStreamFmt:this.PreferStreamFmt,AckTimeout:this.AckTimeout,KeepaliveTimeout:this.KeepaliveTimeout,APIAuth:this.APIAuth,SIPLog:this.SIPLog,AllowStreamStartByURL:this.AllowStreamStartByURL,BlackIPList:this.BlackIPList,BlackUAList:this.BlackUAList,HTTPSPort:this.HTTPSPort,HTTPSCertFile:this.HTTPSCertFile,HTTPSKeyFile:this.HTTPSKeyFile,GlobalChannelShared:this.GlobalChannelShared,GlobalChannelAudio:this.GlobalChannelAudio,GlobalDeviceCatalogSubscribeInterval:this.GlobalDeviceCatalogSubscribeInterval,GlobalDeviceAlarmSubscribeInterval:this.GlobalDeviceAlarmSubscribeInterval,GlobalDevicePositionSubscribeInterval:this.GlobalDevicePositionSubscribeInterval,DropChannelType:this.DropChannelType}},getBaseConfig:function(){var e=this;i.default.get("/api/v1/getbaseconfig").then(function(s){e.Serial=s.Serial,e.Realm=s.Realm,e.Host=s.Host,e.Port=s.Port,e.DevicePassword=s.DevicePassword,e.TimeServer=s.TimeServer,e.PreferStreamFmt=s.PreferStreamFmt,e.AckTimeout=s.AckTimeout,e.KeepaliveTimeout=s.KeepaliveTimeout,e.APIAuth=s.APIAuth,e.SIPLog=s.SIPLog,e.AllowStreamStartByURL=s.AllowStreamStartByURL,e.BlackIPList=s.BlackIPList,e.BlackUAList=s.BlackUAList,e.HTTPSPort=s.HTTPSPort||"",e.HTTPSCertFile=s.HTTPSCertFile,e.HTTPSKeyFile=s.HTTPSKeyFile,e.GlobalChannelShared=s.GlobalChannelShared||!1,e.GlobalChannelAudio=s.GlobalChannelAudio||!1,e.GlobalDeviceCatalogSubscribeInterval=s.GlobalDeviceCatalogSubscribeInterval||0,e.GlobalDeviceAlarmSubscribeInterval=s.GlobalDeviceAlarmSubscribeInterval||0,e.GlobalDevicePositionSubscribeInterval=s.GlobalDevicePositionSubscribeInterval||0,e.globalDeviceCatalogSubscribeInterval=e.GlobalDeviceCatalogSubscribeInterval,e.globalDeviceAlarmSubscribeInterval=e.GlobalDeviceAlarmSubscribeInterval,e.globalDevicePositionSubscribeInterval=e.GlobalDevicePositionSubscribeInterval,e.global_catalog_subscribe=e.GlobalDeviceCatalogSubscribeInterval>0,e.global_alarm_subscribe=e.GlobalDeviceAlarmSubscribeInterval>0,e.global_position_subscribe=e.GlobalDevicePositionSubscribeInterval>0,e.DropChannelType=s.DropChannelType||"",e.DropChannelType?e.dropChannelTypes=e.DropChannelType.split(","):e.dropChannelTypes=[],e.remoteBasicData=JSON.stringify(e.getBasicCommitObject())})},getSMSList:function(){var e=this;""==this.smsserial&&i.default.get("/api/v1/sms/list").then(function(s){e.smss=s,s.length>0&&(e.sms=s[0],e.smsserial=s[0].Serial),e.getSMSInfo(),""==e.smsserial?e.timer=setTimeout(function(){e.getSMSList()},1e3):e.smstip="流媒体服务尚未启动"})},getSMSInfo:function(){var e=this;""!=this.smsserial&&i.default.get("/api/v1/sms/getbaseconfig",{serial:this.smsserial}).then(function(s){e.smsbaseconfig=s,e.smsbaseconfig.RTSPPort=e.smsbaseconfig.RTSPPort||"",e.smsbaseconfig.RTMPPort=e.smsbaseconfig.RTMPPort||"",e.smsbaseconfig.PreSerial=e.smsserial,0==e.smsbaseconfig.CleanOverDays&&(e.smsbaseconfig.CleanOverDays=""),e.remoteSMSData=JSON.stringify(e.smsbaseconfig)})},smschange:function(){this.getSMSInfo()}},computed:r({},(0,l.mapState)(["logoText","logoMiniText","menus","serverInfo"]),{isBasicNoChange:function(){var e=JSON.stringify(this.getBasicCommitObject());return 0==this.remoteBasicData.localeCompare(e)},isSMSNoChange:function(){this.smsbaseconfig.PreSerial=this.smsserial;var e=JSON.stringify(this.smsbaseconfig);return 0==this.remoteSMSData.localeCompare(e)}})}},"5IrG":function(e,s,t){var a=t("Ax7f");"string"==typeof a&&(a=[[e.i,a,""]]),a.locals&&(e.exports=a.locals);t("rjj0")("a982f972",a,!0)},Ax7f:function(e,s,t){s=e.exports=t("FZ+f")(void 0),s.push([e.i,".fa.cfg-black-list[data-v-77fca647],.fa.cfg-white-list[data-v-77fca647]{font-size:14px;line-height:24px;display:inline-block;color:#567}",""])},"o9Q+":function(e,s,t){"use strict";function a(e){t("5IrG")}Object.defineProperty(s,"__esModule",{value:!0});var r=t("/ld9"),o=t.n(r),i=t("+l+h"),l=t("VU/8"),n=a,c=l(o.a,i.a,n,"data-v-77fca647",null);s.default=c.exports}});