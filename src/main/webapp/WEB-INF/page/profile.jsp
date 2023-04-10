<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<!DOCTYPE html>
		<html>

		<head>

			<meta charset="UTF-8">
			<title>${webName }</title>

			<jsp:include page="/WEB-INF/component/common_dependencies.jsp" />
			<style>
				.xmark {
					top: 10%;
					right: 7%;
				}

				.xmark-hide {
					display: none;
				}
			</style>

		</head>


		<body>

			<jsp:include page="/WEB-INF/component/header.jsp" />

			<main>
				<div class="container py-5">

					<div class="row">
						<div class="col-lg-4">
							<div class="card mb-4">
								<div class="card-body text-center">
									<img src="${member.memberDetail.photo }" class="rounded-circle img-fluid w-50">
									<h5 class="my-3">${member.memberDetail.name }</h5>
									<p class="text-muted mb-4">${member.memberDetail.age },
										${member.memberDetail.address }</p>
								</div>
							</div>
							<div class="card mb-4 mb-lg-0">
								<div class="card mb-4">
									<div class="card-body">
										<div class="row">
											<div class="col-sm-3">
												<p class="mb-0">Email</p>
											</div>
											<div class="col-sm-9">
												<p class="text-muted mb-0">${member.email }</p>
											</div>
										</div>
										<hr>
										<div class="row">
											<div class="col-sm-3">
												<p class="mb-0">權限</p>
											</div>
											<div class="col-sm-9">
												<p class="text-muted mb-0">${member.level }</p>
											</div>
										</div>
										<hr>
										<div class="row">
											<div class="col-sm-3">
												<p class="mb-0">年齡</p>
											</div>
											<div class="col-sm-9">
												<p class="text-muted mb-0">${member.memberDetail.age}</p>
											</div>
										</div>
										<hr>
										<div class="row">
											<div class="col-sm-3">
												<p class="mb-0">地址</p>
											</div>
											<div class="col-sm-9">
												<p class="text-muted mb-0">${member.memberDetail.address }</p>
											</div>
										</div>
										<hr>
										<div class="row">
											<div class="col-sm-3">
												<p class="mb-0">寵物數</p>
											</div>
											<div class="col-sm-9">
												<p class="text-muted mb-0">${member.pets.size() }</p>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-lg-8">

							<div class="row">
								<c:forEach items="${member.pets}" var="p">
									<div class="col-md-6 mb-4 ">
										<div class="card mb-4 mb-md-0">
											<div class="card-body text-center position-relative">
												<i
													class="fa-solid fa-xmark fa-beat fa-2xl position-absolute xmark xmark-hide"></i>
												<img src="${root}/getPetPhoto?pID=${p.pID}" class="w-100 petPhoto"
													pID="${p.pID}">
												<p class="card-text fs-3">${p.name }</p>

											</div>
										</div>
									</div>
								</c:forEach>
							</div>
						</div>
					</div>
				</div>
			</main>

			<jsp:include page="/WEB-INF/component/footer.jsp" />

		</body>

		<script>
			$(".petPhoto").mouseenter(function () {
				$(this).prev().removeClass("xmark-hide");
			})

			$(".petPhoto").mouseleave(function (e) {
				if (e.relatedTarget.classList.contains("xmark")) { return; }
				$(this).prev().addClass("xmark-hide");
			})

			$(".xmark").click(function () {
				if (window.confirm("確定要刪除嗎?") == false) { return };

				fetch("${root}/deletePet?pID=" + $(this).next().attr("pID"), { method: "POST" }).then(rs => rs.text()).then(status => {
					if (status == "success") {
						alert("刪除成功");
						$(this).parent().parent().parent().remove();
					}
					if (status == "fail") {
						alert("刪除失敗");
						this.classList.add("xmark-hide");
					}
				})
			})
		</script>

		</html>