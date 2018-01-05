<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%
	String webApp = request.getContextPath();
	String wwww = request.getScheme();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<body>
	<form action="${ctx }/login" method="post">
		<input type="text" name="username" />
		<input type="text"cname="remark" />
		<input type="submit" value="提交" />
	</form>
</html>
