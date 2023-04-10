<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<header>
			<nav class="navbar-dark bg-dark">
				<div class="container-fulid">
					<div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
						<div class="col-2 ms-2 ">
							<a href="#" class="navbar-brand d-flex align-items-center"> <img src="${root }/img/doge.png"
									class="w-15 rounded-circle"> <strong class="ms-2">Doge寵物交流園地</strong>
							</a>
						</div>
						<ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0 ms-2">

							<li><a href="${root}/" class="nav-link px-2 text-white">所有寵物</a></li>
							<c:if test="${LoggedInMember!=null }">
								<li><a href="${root}/profile" class="nav-link px-2 text-warning">個人資料</a></li>
								<li><a href="${root}/add_pet" class="nav-link px-2 text-warning">新增寵物</a></li>
							</c:if>
						</ul>

						<c:if test="${LoggedInMember==null }">
							<div class="text-end m-3">
								<button type="button" class="btn btn-outline-light me-2" data-bs-toggle="modal"
									data-bs-target="#loginModel">登入</button>
								<button type="button" class="btn btn-warning">註冊</button>
							</div>
						</c:if>

						<c:if test="${LoggedInMember!=null }">
							<a href="${root}/Logout"> <button type="button"
									class="btn btn-outline-light me-2">登出</button></a>
							<div class="text-white text-end me-3">歡迎你，${LoggedInMember.memberDetail.name }</div>
							<img src="${LoggedInMember.memberDetail.photo }" class="w-5 me-2 mt-2 mb-2">
 
						</c:if>
					</div>
				</div>
			</nav>
		</header>

		<!-- 隱藏的登入視窗 -->
		<div class="modal fade" id="loginModel" tabindex="-1" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">登入視窗</h5>
						<span id="message" class="text-danger ms-3"></span>
						<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
					</div>
					<div class="modal-body">
						<div class="input-group mb-3">
							<span class="input-group-text">帳號</span> <input type="text" class="form-control"
								placeholder="xxx@xxmail.com" id="email_input">
						</div>
						<div class="input-group mb-3">
							<span class="input-group-text">密碼</span> <input type="password" class="form-control"
								id="password_input">
						</div>

					</div>
					<div class="modal-footer">
						<div class="row"></div>

						<button type="button" class="btn btn-danger " id="notEnabledBTN">未認證</button>
						<button type="button" class="btn btn-success " id="memberLoginBTN">使用者</button>
						<button type="button" class="btn btn-warning me-5" id="adminLoginBTN">管理員</button>
						<button type="button" class="btn btn-secondary ms-5" data-bs-dismiss="modal">取消</button>
						<button type="button" class="btn btn-primary" id="loginBTN">登入</button>
					</div>
				</div>
			</div>
		</div>
		<!-- 隱藏的登入視窗 -->


		<script>
			const ei = document.getElementById("email_input");
			const pi = document.getElementById("password_input");

			//快速登入
			document.getElementById("memberLoginBTN").addEventListener("click", function () {
				ei.value = "Elisa@xxmail.com";
				pi.value = "1234";
			})
			document.getElementById("adminLoginBTN").addEventListener("click", function () {
				ei.value = "Lee@xxmail.com";
				pi.value = "1234";
			})
			document.getElementById("notEnabledBTN").addEventListener("click", function () {
				ei.value = "peggie@xxmail.com";
				pi.value = "1234";
			})
			// 快速登入
			// 登入
			document.getElementById("loginBTN").addEventListener("click", function () {
				fetch("${root}/Login", {
					"method": "POST",
					"body": JSON.stringify({ email: ei.value, password: pi.value }),
					"headers": {
						"content-type": "application/json"
					}
				}).then(rs => rs.text()).then(status => {
					if (status == "success") {
						location.href = "${root}"
					}

					if (status == "fail") {
						ei.value = "";
						pi.value = "";
						document.getElementById("message").innerHTML = "登入失敗(帳號或密碼錯誤)"
					}
					if (status == "account_not_enabled") {
						ei.value = "";
						pi.value = "";
						document.getElementById("message").innerHTML = "登入失敗(帳號未認證)"
					}
				})

			})
		//登入
		</script>