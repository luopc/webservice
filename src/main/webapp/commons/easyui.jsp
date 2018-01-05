<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp" %>

<!-- easyui皮肤 -->
<link href="${ctx}/pages/pc/timedTask/static/plugins/easyui/jquery-easyui-theme/<c:out value="${cookie.themeName.value}" default="default"/>/easyui.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/pages/pc/timedTask/static/plugins/easyui/jquery-easyui-theme/icon.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/pages/pc/timedTask/static/plugins/easyui/icons/icon-all.css" rel="stylesheet" type="text/css" />
<!-- ztree样式 -->
<link href="${ctx}/pages/pc/timedTask/static/plugins/ztree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css" />

<script src="${ctx}/pages/pc/timedTask/static/plugins/easyui/jquery/jquery-1.11.1.min.js"></script>

<script src="${ctx}/pages/pc/timedTask/static/plugins/easyui/jquery-easyui-1.3.6/jquery.easyui.min.js" type="text/javascript"></script>
<script src="${ctx}/pages/pc/timedTask/static/plugins/easyui/jquery-easyui-1.3.6/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>

<!-- jquery扩展 -->
<script src="${ctx}/pages/pc/timedTask/static/plugins/easyui/release/jquery.jdirk.min.js"></script>

<!-- easyui扩展 -->
<link href="${ctx}/pages/pc/timedTask/static/plugins/easyui/jeasyui-extensions/jeasyui.extensions.css" rel="stylesheet" type="text/css" />

<script src="${ctx}/pages/pc/timedTask/static/plugins/easyui/jeasyui-extensions/jeasyui.extensions.js" type="text/javascript"></script>
<script src="${ctx}/pages/pc/timedTask/static/plugins/easyui/jeasyui-extensions/jeasyui.extensions.progressbar.js"></script>
<script src="${ctx}/pages/pc/timedTask/static/plugins/easyui/jeasyui-extensions/jeasyui.extensions.slider.js"></script>
<script src="${ctx}/pages/pc/timedTask/static/plugins/easyui/jeasyui-extensions/jeasyui.extensions.linkbutton.js" type="text/javascript"></script>
<script src="${ctx}/pages/pc/timedTask/static/plugins/easyui/jeasyui-extensions/jeasyui.extensions.validatebox.js" type="text/javascript"></script>
<script src="${ctx}/pages/pc/timedTask/static/plugins/easyui/jeasyui-extensions/jeasyui.extensions.combo.js" type="text/javascript"></script>
<script src="${ctx}/pages/pc/timedTask/static/plugins/easyui/jeasyui-extensions/jeasyui.extensions.combobox.js" type="text/javascript"></script>
<script src="${ctx}/pages/pc/timedTask/static/plugins/easyui/jeasyui-extensions/jeasyui.extensions.menu.js" type="text/javascript"></script>
<script src="${ctx}/pages/pc/timedTask/static/plugins/easyui/jeasyui-extensions/jeasyui.extensions.searchbox.js" type="text/javascript"></script>
<script src="${ctx}/pages/pc/timedTask/static/plugins/easyui/jeasyui-extensions/jeasyui.extensions.panel.js" type="text/javascript"></script>
<script src="${ctx}/pages/pc/timedTask/static/plugins/easyui/jeasyui-extensions/jeasyui.extensions.window.js" type="text/javascript"></script>
<script src="${ctx}/pages/pc/timedTask/static/plugins/easyui/jeasyui-extensions/jeasyui.extensions.dialog.js" type="text/javascript"></script>
<script src="${ctx}/pages/pc/timedTask/static/plugins/easyui/jeasyui-extensions/jeasyui.extensions.layout.js" type="text/javascript"></script>
<script src="${ctx}/pages/pc/timedTask/static/plugins/easyui/jeasyui-extensions/jeasyui.extensions.tree.js" type="text/javascript"></script>
<script src="${ctx}/pages/pc/timedTask/static/plugins/easyui/jeasyui-extensions/jeasyui.extensions.datagrid.js" type="text/javascript"></script>
<script src="${ctx}/pages/pc/timedTask/static/plugins/easyui/jeasyui-extensions/jeasyui.extensions.treegrid.js" type="text/javascript"></script>
<script src="${ctx}/pages/pc/timedTask/static/plugins/easyui/jeasyui-extensions/jeasyui.extensions.combogrid.js" type="text/javascript"></script>
<script src="${ctx}/pages/pc/timedTask/static/plugins/easyui/jeasyui-extensions/jeasyui.extensions.combotree.js" type="text/javascript"></script>
<script src="${ctx}/pages/pc/timedTask/static/plugins/easyui/jeasyui-extensions/jeasyui.extensions.tabs.js" type="text/javascript"></script>
<script src="${ctx}/pages/pc/timedTask/static/plugins/easyui/jeasyui-extensions/jeasyui.extensions.theme.js" type="text/javascript"></script>

<script src="${ctx}/pages/pc/timedTask/static/plugins/easyui/release/jeasyui.extensions.all.min.js"></script>

<script src="${ctx}/pages/pc/timedTask/static/plugins/easyui/icons/jeasyui.icons.all.js" type="text/javascript"></script>
<script src="${ctx}/pages/pc/timedTask/static/plugins/easyui/release/jeasyui.icons.all.min.js"></script>
    
<script src="${ctx}/pages/pc/timedTask/static/plugins/easyui/jeasyui-extensions/jeasyui.extensions.icons.js" type="text/javascript"></script>
<script src="${ctx}/pages/pc/timedTask/static/plugins/easyui/jeasyui-extensions/jeasyui.extensions.gridselector.js" type="text/javascript"></script>

<script src="${ctx}/pages/pc/timedTask/static/plugins/easyui/jeasyui-extensions/jquery.toolbar.js" type="text/javascript"></script>
<script src="${ctx}/pages/pc/timedTask/static/plugins/easyui/jeasyui-extensions/jquery.comboicons.js" type="text/javascript"></script>
<script src="${ctx}/pages/pc/timedTask/static/plugins/easyui/jeasyui-extensions/jquery.comboselector.js" type="text/javascript"></script>
<script src="${ctx}/pages/pc/timedTask/static/plugins/easyui/jeasyui-extensions/jquery.portal.js" type="text/javascript"></script>
<script src="${ctx}/pages/pc/timedTask/static/plugins/easyui/jeasyui-extensions/jquery.my97.js" type="text/javascript"></script>    
<script src="${ctx}/pages/pc/timedTask/static/plugins/easyui/jeasyui-extensions/jeasyui.extensions.ty.js"></script>

<!-- ztree扩展 -->
<script src="${ctx}/pages/pc/timedTask/static/plugins/ztree/js/jquery.ztree.all-3.5.min.js"></script>
<script src="${ctx}/pages/pc/timedTask/static/plugins/ztree/js/jquery.ztree.exhide-3.5.min.js"></script>

<link rel="stylesheet" href="${ctx}/pages/pc/timedTask/static/plugins/easyui/common/other.css"></link>

<script>
//全局的AJAX访问，处理AJAX清求时SESSION超时
$.ajaxSetup({
    contentType:"application/x-www-form-urlencoded;charset=utf-8",
    complete:function(XMLHttpRequest,textStatus){
          //通过XMLHttpRequest取得响应头，sessionstatus           
          var sessionstatus=XMLHttpRequest.getResponseHeader("sessionstatus"); 
          if(sessionstatus=="timeout"){
               //跳转的登录页面
               window.location.replace('${ctx}/pages/pc/login.jsp');
       		}	
    }
});
</script>