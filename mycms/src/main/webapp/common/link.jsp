<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--上一句用于引入jstl--%>
<div class="link">
	<div class="w960">
		<div class="data_list link_list">
			<div class="dataHeader">链接</div>
			<div class="datas">
				<ul>
	              <c:forEach var="link" items="${linkList }">
<%--					  取linklist，每次取出的赋给link对象--%>
	              	<li><a target="_blank" href="${link.url }">${link.name }</a></li>
<%--					  取出link的url和name--%>
	              </c:forEach>
				</ul>
			</div>
		</div>
	</div>
</div>    