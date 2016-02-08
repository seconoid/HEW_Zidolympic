<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<<<<<<< HEAD
<<<<<<< HEAD
<!DOCTYPE>
<html>
	<head>
		<meta charset="UTF-8">
	</head>
<body>
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
</body>
</html>
=======
=======
>>>>>>> ef61938d8d570398ccb6abe0ce51f66f577f19d6

<!-- main_layout.jspにコンテンツとタイトルを渡す -->
<c:import url="./layout/main_layout.jsp">
	<c:param name="title" value="新規登録"/>
<<<<<<< HEAD
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
>>>>>>> 4076b2e2cfc84c5396abfb3378ca88e0a7690bf9
=======
	<c:param name="content">
	<!-- 新規登録フォーム -->
	<div class="join">
		<h2 class="form-title">「ジドリンピック」に参加しよう！</h2>
		<form action="InsertServlet" method="post">
			<div class="form-group">
				<div class="row">
					<div class="col-xs-3">
						<label>ユーザID</label>
					</div>
					<div class="col-xs-9">
						<input type="text" name="id"  class="form-control" required>
						${ idErr }
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="row">
					<div class="col-xs-3">
						<label>ユーザ名</label>
					</div>
					<div class="col-xs-9">
						<input type="text" name="name"  class="form-control" required>
						${ nameErr }
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="row">
					<div class="col-xs-3">
						<label>パスワード</label>
					</div>
					<div class="col-xs-9">
						<input type="password" name="password" class="form-control" required>
						${ passErr }
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="row">
					<div class="col-xs-3">
						<label>メールアドレス</label>
					</div>
					<div class="col-xs-9">
						<input type="email" name="mail_adress" class="form-control" required>
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="row">
					<div class="col-xs-3">
						<label>生年月日</label>
					</div>
					<div class="col-xs-9">
						<input type="date" name="birthday" class="form-control">
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-3">
					<b>性別</b>
				</div>
				<div class="col-xs-9">
					<div class="radio-inline">
						<label>
							<input type="radio" name="sex" value="0" checked><b>男</b>
						</label>
					</div>
					<div class="radio-inline">
						<label>
							<input type="radio" name="sex" value="1"><b>女</b>
						</label>
					</div>
				</div>
			</div>
			<button type="submit" class="btn btn-primary btn-w100">アカウントを作成</button>
			${ mess }
		</form>
	</div>
	</c:param>
>>>>>>> ef61938d8d570398ccb6abe0ce51f66f577f19d6
