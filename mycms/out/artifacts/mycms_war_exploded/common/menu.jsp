<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div class="menu">
	<div class="w960 m960">
		<ul>
			<li class="first"><a href="${pageContext.request.contextPath}/index.jsp" >首页</a></li>
			
				<c:forEach var="arcType" items="${arcTypeList }">
					<li><a href="${pageContext.request.contextPath}/arcType/${arcType.id }.html">${arcType.typeName }</a></li>
<%--					拼接出了完整的用于访问的url地址，就是显示在浏览器地址栏上的那一串--%>
				</c:forEach>
		</ul>
	</div>
</div>
    