<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- main_layout.jspにコンテンツとタイトルを渡す -->
<c:import url="./layout/main_layout.jsp">
	<c:param name="title" value="新規登録"/>
	<c:param name="content">	
		<form action="InsertServlet" method="post"><br>
			ﾕｰｻﾞID	：<input type="text" name="id"  required><br>
			${ idErr }<br>
			ﾕｰｻﾞ名	：<input type="text" name="name"  required><br>
			${ nameErr }<br>
			ﾊﾟｽﾜｰﾄﾞ	：<input type="text" name="password" required><br>
			${ passErr }<br>
			ﾒｰﾙｱﾄﾞﾚｽ	：<input type="email" name="mail_adress" required><br>
			生年月日	：<input type="date" name="birthday"><br>
			男：<input type="radio" name="sex" value="0" checked>
			女：<input type="radio" name="sex" value="1"><br>
			<input type="submit" value="登録"><br>
			${ mess }
		</form>
	</c:param>
</c:import>