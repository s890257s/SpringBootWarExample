<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<jsp:include page="/WEB-INF/component/common_dependencies.jsp" />

<script src="${root}/js/index.js"></script>
<meta charset="UTF-8">
<title>Doge寵物交流園地</title>
</head>
<body>

	<jsp:include page="/WEB-INF/component/header.jsp" />

	<main>

		<section class="py-5 text-center container">
			<div class="row py-lg-5">
				<div class="col-lg-6 col-md-8 mx-auto">
					<p class="lead text-muted">(ChatGPT產生)加入我們的寵物社交平台，閱讀飼養心得、瀏覽可愛的寵物相片，與其他寵物愛好者分享您的寵物故事和照片。透過按讚、收藏和分享相簿等功能，建立更多寵物社交關係，探索寵物世界！</p>
				</div>
			</div>
		</section>

		<div class="album py-5 bg-light">
			<div class="container">

				<div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3 text-center"
					id="petHome"></div>
			</div>
		</div>

	</main>

	<jsp:include page="/WEB-INF/component/footer.jsp" /></body>
</html>