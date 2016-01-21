<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- main_layout.jspにコンテンツとタイトルを渡す -->
<c:import url="./layout/main_layout.jsp">
	<c:param name="title" value=""/>
	<c:param name="content">
		<h2>新規登録</h2>
		<form action="#">
			<p>名前<input type="text"></p>
			<p>ID<input type="text"></p>
			<p>メールアドレス<input type="text" /></p>
			<p>性別 
				男<input type="radio" name="sex" value="M" /> 
				女<input type="radio" name="sex" value="W" /> 
				その他<input type="radio" name="sex" value="M" />
			</p>
			<p>パスワード<input type="password" /></p>
			<input type="submit" value="新規登録">
		</form>
	</c:param>
</c:import>