<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="/HEW_Sample/dist/css/lightbox.min.css">
<link rel="stylesheet" href="./css/sp.css">
<c:import url="./layout/main_layout.jsp">
	<c:param name="title" value="" />
	<c:param name="content">
	<h1>あなたの点数は${ grayCt }です</h1>
		<div class="img">
			<a><img src="/HEW_Zidolympic/UploadImages/${ filename }" alt="splatorch" /></a>
		</div>
					<div class="update-form">
						<form action="SplatorchUpdata" method="post" name="splatorch">
							<div class="form-group">
								<input type="text" name="filename" placeholder="タイトル" class="form-control">
							</div>
							<div class="form-group">
								<textarea cols="20"rows="4" name="comment" placeholder="コメント" class="form-control"></textarea>
							</div>
							<div class="use-point">
								消費ポイント： <span style="color:orange">150pt</span>
							</div>
							<div class="form-group">
								<div class="text-center">
									<button class="btn btn-primary text-right photo_btn">投稿</button>
								</div>
							</div>
						</form>
					</div>
	</c:param>
</c:import>