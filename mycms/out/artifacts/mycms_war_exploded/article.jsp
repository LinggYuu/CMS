<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${article.title }</title>
<META NAME="Author" CONTENT="yazan">
<meta name="keywords"
	content="${article.keyWords }" />
<meta name="description"
	content="${article.summary }" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/mycms.css">
<script src="${pageContext.request.contextPath}/static/js/jQuery.js"></script>
</head>
<body>
	<jsp:include page="/common/head.jsp"></jsp:include>
	<jsp:include page="/common/menu.jsp"></jsp:include>

	<div class="content">
		<div class="w960">
			<div class="pLeft">
				<div class="data_list">
					<div class="dataHeader navi">${navCode }</div>
<%--					导航栏--%>
					<div class="article_title"><h2><strong>${article.title }</strong></h2></div>
					<div class="share">
						
					</div>	
					<div class="article_info">
						发布时间：【<fmt:formatDate value="${article.publishDate }" pattern="yyyy-MM-dd HH:mm" />】&nbsp;&nbsp;
						&nbsp;&nbsp;内容类别：【${article.arcType.typeName}】&nbsp;&nbsp;阅读次数：${article.click}
					</div>
					<div class="article_summary">
						${article.summary }
					</div>
					
					<div class="article_content">
						${article.content }
					</div>
					<div class="article_keyWord">
						<font><strong>关键字：</strong></font>
						<c:choose>
							<c:when test="${keyWords==null }">
								&nbsp;&nbsp;无
							</c:when>
							<c:otherwise>
								<c:forEach var="keyWord" items="${keyWords }">
								  &nbsp;&nbsp;<a href="https://www.baidu.com/s?wd=${keyWord }" target="_blank">${keyWord }</a>&nbsp;&nbsp;
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</div>
					<div class="article_lastAndNextPage">
						${pageCode }
					</div>				
					
				</div>
				
				<div>
<%--					畅言云评--%>
						<!--PC版-->
						<div id="SOHUCS" sid="${article.id}"></div>
						<script charset="utf-8" type="text/javascript" src="https://cy-cdn.kuaizhan.com/upload/changyan.js" ></script>
						<script type="text/javascript">
						window.changyan.api.config({
						appid: 'cyvqCoNSv',
						conf: 'prod_8b16468ec97cff14d29025717298c05c'
						});
						</script>
				</div>
				
				
				
			</div>
			
			
			<div class="pRight">
				<div class="data_list">
					<div class="dataHeader">站长推荐</div>
					<div class="datas">
						<ul>
							<c:forEach var="article" items="${recommendArticleList }">
								<li><a target="_blank"
									href="${pageContext.request.contextPath}/article/${article.id}.html"
									title="${article.title }"><font
										color="${article.titleColor }">${fn:substring(article.title,0,16) }</font></a></li>
							</c:forEach>

						</ul>
					</div>
				</div>
				
				<div class="data_list" style="margin-top: 10px;">
					<div class="dataHeader">最近更新</div>
					<div class="datas">
						<ul>
							<c:forEach var="article" items="${newestArticleList }">
								<li><a target="_blank"
									href="${pageContext.request.contextPath}/article/${article.id}.html"
									title="${article.title }"><font
										color="${article.titleColor }">${fn:substring(article.title,0,16) }</font></a></li>
							</c:forEach>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="/common/foot.jsp"></jsp:include>
</body>
</html>