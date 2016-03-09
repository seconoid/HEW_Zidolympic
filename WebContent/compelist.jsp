<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- main_layout.jspにコンテンツとタイトルを渡す -->
<c:import url="./layout/main_layout.jsp">
	<c:param name="title" value="競技一覧"/>
	<c:param name="content">
	<div class="compe-list">
		<div class="content-box list-try"><a href="/HEW_Zidolympic/TryServlet"><img src="./images/try.png" alt="トライジドリング" width="333px"/></a></div>
		<div class="content-box list-sync"><a href="/HEW_Zidolympic/SynchroServlet"><img src="./images/sync.png" alt="シンクロジドリング" width="333px"/></a></div>
		<div class="content-box list-spl"><a href="./splatorch.jsp"><img src="./images/spl.jpg" alt="スプラトゥーン" width="333px" /></a></div>
	</div>
	</c:param>
</c:import>