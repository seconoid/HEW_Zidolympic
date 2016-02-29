<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- main_layout.jspにコンテンツとタイトルを渡す -->
<c:import url="./layout/main_layout.jsp">
	<c:param name="title" value="競技一覧"/>
	<c:param name="content">
	<h2>競技一覧</h2>
	<div class="content-box"><a href="./try.jsp">トライジドリング</a></div>
	<div class="content-box"><a href="./synchroj.jsp">シンクロナイズドジドリング</a></div>
	<div class="content-box"><a href="./splatorch.jsp">スプラトゥーチ</a></div>
	</c:param>
</c:import>