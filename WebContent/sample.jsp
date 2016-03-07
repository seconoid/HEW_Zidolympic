<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- main_layout.jspにコンテンツとタイトルを渡す -->
<c:import url="./layout/main_layout.jsp">
	<c:param name="title" value="さんぷる"/>
	<c:param name="content">
		<p>さんぷるだよ</p>
		<a href="index.jsp">いんでっくす</a>
	</c:param>
</c:import>