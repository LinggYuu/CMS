<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MYCMS</title>
<META NAME="Author" CONTENT="yazan">
<meta name="keywords"
	content="一个内容管理系统，专注于发布很棒的帖子" />
<meta name="description"
	content="这是一个CMS，真的很不错:)" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/mycms.css">
<script src="${pageContext.request.contextPath}/static/js/jQuery.js"></script>
</head>
<body>
	<jsp:include page="/common/head.jsp"></jsp:include>
	<jsp:include page="/common/menu.jsp"></jsp:include>

	<div class="content">
		<div class="w960">

			<div class="left">
				<DIV style="width: 330px; height: 228px;" class="tuhuo">
					<A href="" target="_blank"><IMG
						style="width: 330px; height: 208px;" id="fou_img" src=""></A>

					<c:forEach var="article" items="${slideArticleList }">
						<A
							href="${pageContext.request.contextPath}/article/${article.id}.html">
							<IMG style="display: none;" class="tu_img"
							src="${pageContext.request.contextPath}/static/userImages/${article.slideImage}"
							width="330" height="208" />
						</A>
					</c:forEach>

					<c:forEach var="article" items="${slideArticleList }">
						<P style="height: 20px;" class="tc">
							<A
								href="${pageContext.request.contextPath}/article/${article.id}.html"
								target="_blank" title="${article.title }">${fn:substring(article.title,0,18) }</A>
						</P>
					</c:forEach>

					<UL>
						<LI >1</LI>
						<LI>2</LI>
						<LI>3</LI>
						<LI>4</LI>
						<LI>5</LI>
					</UL>
				</DIV>


			</div>

			<div class="middle">

				<div class="data_list">
					<div class="dataHeader">最近更新</div>
					<div class="datas">
						<ul>
							<c:forEach var="article" items="${newestArticleList }">
								<li><a target="_blank"
									href="${pageContext.request.contextPath}/article/${article.id}.html"
									title="${article.title }">[<fmt:formatDate
											value="${article.publishDate }" pattern="MM-dd" />]&nbsp;&nbsp;<font
										color="${article.titleColor }">${fn:substring(article.title,0,20) }</font></a></li>
							</c:forEach>
						</ul>
					</div>
				</div>

			</div>

			<div class="right">
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


			</div>
        </div>

		<c:forEach var="subIndexArticleList" items="${allIndexArticleList }" varStatus="allStatus">
<%--		items一个个集合，每个集合都要被放上去--%>
<%--		var代表当前条目的变量名称   items代表要被循环的信息   varStatus代表循环状态的变量名称--%>
		<c:if test="${allStatus.index%3==0 }">
<%--		当%3==0时显示行比如说0，3，6，代表新的行的开始，所以要显示新的一整条（每条只能显示3个元素集合）--%>
		<div class="w960 article_row">
			</c:if>

<%--			从上面--%>


			<c:forEach var="indexArticle" items="${subIndexArticleList}" varStatus="oneStatus">
			<c:if test="${oneStatus.first }">
<%--				假如是第一个的时候才加第一列的判断--%>
					<%--下面用于显示不同位置的栏目，第一列的与别的格式略有差别--%>
			<c:choose>
				<c:when test="${allStatus.index%3==0 }">
<%--				第一列--%>
			<div class="data_list article_list">
				</c:when>
					<c:otherwise>
						<div class="data_list article_list" style="margin-left: 12px">
<%--							如果不是第一列的栏目要与左边的远12px--%>
					</c:otherwise>
			</c:choose>


					<div class="dataHeader">
							${arcTypeList.get(allStatus.index).typeName}
								<span class="more">
								<a href="${pageContext.request.contextPath}/arcType/${arcTypeList.get(allStatus.index).id}.html">MORE...</a>
								</span>
<%--					${arcTypeList.get(allStatus.index).typeName}为栏目名称--%>
					</div>
					<div class="datas">
<%--						栏目具体内容--%>
						<ul>
							</c:if>
							<li>[<fmt:formatDate value="${indexArticle.publishDate }" pattern="MM-dd" />]&nbsp;&nbsp;
								<a target="_blank" href="${pageContext.request.contextPath}/article/${indexArticle.id }.html"
								   title="${indexArticle.title }"><font color="${indexArticle.titleColor }">${fn:substring(indexArticle.title,0,18) }</font></a></li>
							<c:if test="${oneStatus.last }">
						</ul>
					</div>
				</div>
				</c:if>

				</c:forEach>


<%--					到下面是大内容排版，中间那段是每个栏目取数据的--%>


				<c:if test="${allStatus.index%3==2 || allStatus.last}">
<%--				对%3==2或者是最后一个，就把它封到大集合中(即整体内容显示部分)，完毕--%>
				</div>
				</c:if>
				</c:forEach>

		
	</div>




	<jsp:include page="/common/link.jsp"></jsp:include>


	<jsp:include page="/common/foot.jsp"></jsp:include>
</body>
<script type="text/javascript">
	var auto;
	var index = 0;
	$('.tuhuo ul li').hover(function() {
		clearTimeout(auto);
		index = $(this).index();
		move(index);
	}, function() {
		auto = setTimeout('autogo(' + index + ')', 3000);
	});

	function autogo() {
		if (index < 3) {
			move(index);
			index++;
		} else {
			index = 0;
			move(index);
			index++;
		}
	}
	function move(l) {
		var src = $('.tu_img').eq(index).attr('src');
		$("#fou_img").css({
			"opacity" : "0"
		});
		$('#fou_img').attr('src', src);
		$('#fou_img').stop(true).animate({
			opacity : '1'
		}, 1000);
		$('.tuhuo ul li').removeClass('fouce');
		$('.tuhuo ul li').eq(index).addClass('fouce');
		$('.tuhuo p').hide();
		$('.tuhuo p').eq(index).show();
		var ao = $('.tuhuo p').eq(index).children('a').attr('href');
		$('#fou_img').parent('a').attr("href", ao);
	}
	autogo();
	setInterval('autogo()', 3000);
</script>

</html>