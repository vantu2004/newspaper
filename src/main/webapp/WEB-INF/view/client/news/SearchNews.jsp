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

	<!-- mainContent start -->
	<div class="container-fluid py-3">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="row mb-3">
						<div class="col-12">

							<div
								class="d-flex align-items-center justify-content-between bg-light py-2 px-4 mb-3">
								<h3 class="m-0">
									<c:choose>
										<c:when test="${not empty news[0].category.nameCategory}">
							                ${news[0].category.nameCategory}
							            </c:when>
										<c:otherwise>
							                Kết quả tìm kiếm
							            </c:otherwise>
									</c:choose>
								</h3>
							</div>

						</div>
						<c:forEach var="news" items="${news}">
							<div class="col-lg-3 mb-3">
								<div class="position-relative" style="overflow: hidden;">
									<div style="width: 100%; height: 250px;">
										<img class="img-fluid w-100 h-100" src="${news.image}"
											style="object-fit: cover;">
									</div>
									<div class="p-3 bg-light">
										<div class="mb-2" style="font-size: 14px;">
											<a href="/listNews/${news.category.id}">${news.category.nameCategory}</a>
											<span class="px-1">/</span> <span>${news.pubdate}</span>
										</div>
										<a class="h4" href="/detail-news/${news.id}">${news.title}</a>
										<p class="m-0">${news.summary}</p>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- mainContent end -->

	<c:if test="${empty news}">
		<div class="pagination d-flex justify-content-center mt-5 mb-5">
			Không tìm thấy tin liên quan
		</div>
	</c:if>

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