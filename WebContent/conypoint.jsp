<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- main_layout.jspにコンテンツとタイトルを渡す -->
<c:import url="./layout/main_layout.jsp">
	<c:param name="title" value="ポイント変換"/>
	<c:param name="content">
	<!-- 非ログイン時はログイン画面に遷移 -->
	<c:if test="${ sessionScope.user == null }">
		<%
			request.setAttribute("loginErr", "ログインが必要です");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		%>
	</c:if>
	<!-- 認証画面を通らなかった場合に認証画面へ -->
	<c:if test="${ sessionScope.user.getNo() != auth.getNo() }">
		<%
			request.setAttribute("url", "conypoint.jsp");
			request.getRequestDispatcher("user_confilm.jsp").forward(request, response);
		%>
	</c:if>
		<div class="buy-point">
		<h2 class="form-title">ポイント購入</h2>
		<c:if test="${!empty loginErr }">
			<div class="error-area text-danger bg-danger">${ pointErr }</div>
		</c:if>
		<form action="PointServlet" method="post">
			<div class="form-group">
				<div class="row buy-form">
					<div class="form-inline">
						<div class="col-xs-3">
							<label>購入ポイント</label>
						</div>
						<div class="col-xs-9 buy-amount">
							<div class="form-inline">
								<div class="form-group">
									<input type="text" name="point" class="form-control">
									<label>pt<span class="caution">※1pt = 1円</span></label>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="row buy-form">
					<div class="col-xs-3">
						<label>プリペイト番号</label>
					</div>
					<div class="col-xs-9">
						<div class="form-inline">
							<div class="form-group">
								<input type="text" name="cardNo1" class="form-control" size="4" id="digit1">
								<label>-</label>
							</div>
							<div class="form-group">
								<input type="text" name="cardNo2" class="form-control" size="4" id="digit2">
								<label>-</label>
							</div>
							<div class="form-group">
								<input type="text" name="cardNo3" class="form-control" size="4" id="digit3">
								<label>-</label>
							</div>
							<div class="form-group">
								<input type="text" name="cardNo4" class="form-control" size="4" id="digit4">
							</div>
						</div>
					</div>
				</div>
				<div class="row buy-form">
					<div class="form-group">
				        <div class="col-xs-3">
				        	<label class="control-label">カード選択</label>
				       	</div>
				       	<div class="col-xs-9">
				       		<label class="radio-inline">
				       			<input type="radio" name="card" value=5000 onClick="selectCard(1)">5000円
				       		</label>
				       		<label class="radio-inline">
								<input type="radio" name="card" value=2000 onClick="selectCard(2)">2000円
				            </label>
				            <label class="radio-inline">
				            	<input type="radio" name="card" value=1000 onClick="selectCard(3)">1000円
				            </label>
				        </div>
				    </div>
				</div>
				<!-- ajaxできたら -->
				<!--
				<div class="row buy-form">
					<div class="col-xs-3">
						購入後ポイント
					</div>
				</div>
				-->
			</div>
			<input type="hidden" name="member_no" value="${ auth.getNo() }">
			<button type="submit" class="btn btn-primary btn-w100">お支払い</button>
		</form>
	</div>
	</c:param>
</c:import>