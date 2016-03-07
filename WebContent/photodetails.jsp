<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="/HEW_Sample/dist/css/lightbox.min.css">

<c:import url="./layout/main_layout.jsp">
	<c:param name="title" value="写真詳細" />
	<c:param name="content">

		<p>クリックで拡大</p>
		<div>
		<c:forEach items="${com2}" var="d">
			<a class="example-image-link" href="/HEW_Zidolympic/UploadImages/${d.img_pass}"
				data-lightbox="example-1"> <img class="example-image"
				src="/HEW_Zidolympic/UploadImages/${d.img_pass}" alt="image-1" /></a>
		</c:forEach>
		</div>
		<p>いいねは㊦クリック</p>
${fov_out}


<form action="/HEW_Zidolympic/FovServlet" method="post" id="form">
			<input type="hidden" id="hidden">
			<input type="hidden" name="img_pass" value="${img_pass }"> 
			<input type="hidden" value="${fov_val}" name="fov_val">
			<input type="hidden" value="${con_id}" name="con_id">
		</form>

		<script>
			document.getElementById('fov_none').addEventListener('click',
					function() {
						document.forms["form"].submit();
						return true;
					});

			window.onload = Change;
		</script>

		<script src="/HEW_Zidolympic/dist/js/lightbox-plus-jquery.min.js"></script>





		<br />
		<br />
		<br />
		<br />





		<h3>画像詳細</h3>
		<p>${ sessionScope.user.getName() }</p>
		<c:forEach items="${com}" var="h">
		<p>${h.comname}</p>
		<p>${h.date}</p>
		</c:forEach>
		<p>コメント（画像タイトル）</p>





	</c:param>
</c:import>