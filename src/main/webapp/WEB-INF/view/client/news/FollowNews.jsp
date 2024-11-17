<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html lang="en">

<head>
<meta charset="utf-8">
<title>News</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<!-- Favicon -->
<link href="client/img/favicon.ico" rel="icon">

<!-- Google Web Fonts -->
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700;900&display=swap"
	rel="stylesheet">

<!-- Font Awesome -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.0/css/all.min.css"
	rel="stylesheet">

<!-- Libraries Stylesheet -->
<link href="/client/lib/owlcarousel/assets/owl.carousel.min.css"
	rel="stylesheet">

<!-- Customized Bootstrap Stylesheet -->
<link href="/client/css/style.css" rel="stylesheet">


</head>

<body>

	<!-- header start -->
	<jsp:include page="../layout/Header.jsp" />
	<!-- header end -->

	<div class="container-fluid py-3">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="row mb-3">
						<div class="col-12">
							<div
								class="d-flex align-items-center justify-content-between bg-light py-2 px-4 mb-3">
								<h3 class="m-0">Tin Đã Lưu</h3>
							</div>
						</div>
						<c:if test="${not empty listFollowNews}">
							<c:forEach var="news" items="${listFollowNews}">
								<div class="col-12 mb-4">
									<div
										class="d-flex flex-column flex-lg-row bg-light p-3 rounded">
										<img src="${news.image}" alt="news image"
											class="img-fluid mb-3 mb-lg-0"
											style="max-width: 200px; height: 150px; object-fit: cover;">
										<div
											class="w-100 d-flex flex-column justify-content-between px-3">
											<div
												class="d-flex justify-content-between align-items-center">
												<div>
													<a href="/listNews/${news.category.id}"
														class="text-decoration-none text-red">${news.category.nameCategory}</a>
													<span class="px-1">/</span> <span>${news.pubdate}</span>
												</div>

												<form action="/unfollow-news-followPage/${news.id}" method="post"
													class="d-flex align-items-center">
													<input type="hidden" name="${_csrf.parameterName}"
														value="${_csrf.token}" />
													<button type="submit"
														class="mx-auto btn border border-secondary px-3 text-primary">
														<i class="fas fa-minus-square"></i> Bỏ lưu tin
													</button>
												</form>
											</div>
											<a href="/detail-news/${news.id}"
												class="h5 text-dark text-decoration-none">${news.title}</a>
											<p class="mt-2 text-muted">${news.summary}</p>
										</div>
									</div>
								</div>
							</c:forEach>
						</c:if>


						<c:if test="${empty listFollowNews}">
							<div class="col-12 d-flex justify-content-center">
								<p>Chưa có tin nào được lưu</p>
							</div>
						</c:if>

					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- footer start -->
	<jsp:include page="../layout/Footer.jsp" />
	<!-- footer end -->

	<!-- Back to Top -->
	<a href="#" class="btn btn-dark back-to-top"><i
		class="fa fa-angle-up"></i></a>

	<!-- JavaScript Libraries -->
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
	<script src="/client/lib/easing/easing.min.js"></script>
	<script src="/client/lib/owlcarousel/owl.carousel.min.js"></script>

	<!-- Template Javascript -->
	<script src="/client/js/main.js"></script>
</body>

</html>