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
<link href="client/lib/owlcarousel/assets/owl.carousel.min.css"
	rel="stylesheet">

<!-- Customized Bootstrap Stylesheet -->
<link href="client/css/style.css" rel="stylesheet">


</head>

<body>

	<!-- header start -->
	<jsp:include page="../layout/Header.jsp" />
	<!-- header end -->

	<!-- mainContent start -->

	<!-- Tin Mới -->
	<div class="container-fluid py-3">
		<div class="container">
			<div class="row">
				<div class="col-lg-8">
					<div
						class="owl-carousel owl-carousel-2 carousel-item-1 position-relative mb-3 mb-lg-0">
						<c:forEach var="news" items="${listNews.tinMoi}" begin="0" end="9">
							<div class="position-relative overflow-hidden"
								style="height: 435px;">
								<img class="img-fluid h-100" src="${news.image}"
									style="object-fit: cover;">
								<div class="overlay">
									<div class="mb-1">
										<a class="text-white" href="/listNews/${news.category.id}">${news.category.nameCategory}</a>
										<span class="px-2 text-white">/</span> <a class="text-white"
											href="">${news.pubdate}</a>
									</div>
									<a class="h2 m-0 text-white font-weight-bold" href="/detail-news/${news.id}">${news.title}</a>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>

				<div class="col-lg-4">
					<div
						class="d-flex align-items-center justify-content-between bg-light py-2 px-3 mb-3">
						<h3 class="m-0">Tin mới</h3>
						<a class="text-secondary font-weight-medium text-decoration-none"
							href="">Xem tất cả</a>
					</div>
					<c:forEach var="news" items="${listNews.tinMoi}" begin="10"
						end="12">
						<div
							class="d-flex align-items-start justify-content-between py-0 px-0 mb-3">

							<div class="d-flex flex-column" style="flex: 3;">
								<a class="m-0" style="color: black;" href="/detail-news/${news.id}">${news.title}</a> <a
									href="/listNews/${news.category.id}">${news.category.nameCategory}</a> <span class="px-1"></span>
								<p>${news.pubdate}</p>
							</div>
							<div class="position-relative overflow-hidden"
								style="flex: 1; margin-left: 10px;">
								<img class="img-fluid w-100 h-100" src="${news.image}"
									style="object-fit: cover; height: 300px;">
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
	<!-- Tin Mới -->

	<!-- Thời Sự -->
	<div class="container-fluid py-3">
		<div class="container">
			<div
				class="d-flex align-items-center justify-content-between bg-light py-2 px-4 mb-3">
				<h3 class="m-0">Thời Sự</h3>
				<a class="text-secondary font-weight-medium text-decoration-none"
					href="/listNews/${listNews.thoiSu[0].category.id}">Xem tất cả</a>
			</div>
			<div
				class="owl-carousel owl-carousel-2 carousel-item-4 position-relative">
				<c:forEach var="news" items="${listNews.thoiSu}" begin="0" end="9">
					<div class="position-relative overflow-hidden"
						style="height: 300px;">
						<img class="img-fluid w-100 h-100" src="${news.image}"
							style="object-fit: cover;">
						<div class="overlay">
							<a class="h4 m-0 text-white" href="/detail-news/${news.id}">${news.title}</a>
							<div class="mb-1" style="font-size: 13px;">
								<a class="text-white" href="/listNews/${news.category.id}">${news.category.nameCategory}</a>
								<span class="px-1 text-white">/</span> <a class="text-white"
									href="">${news.pubdate}</a>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
	<!-- Thời Sự -->

	<!-- Thế Giới - Kinh Doanh-->
	<div class="container-fluid">
		<div class="container">
			<div class="row">
				<!-- Thế Giới -->
				<div class="col-lg-6 py-3">
					<div class="bg-light py-2 px-4 mb-3">
						<h3 class="m-0">Thế Giới</h3>
					</div>
					<div
						class="owl-carousel owl-carousel-3 carousel-item-2 position-relative">
						<c:forEach var="news" items="${listNews.theGioi}" begin="0"
							end="9">
							<div class="position-relative">
								<!-- Phần ảnh với kích thước cố định -->
								<div style="width: 100%; height: 240px;">
									<img class="img-fluid w-100 h-100" src="${news.image}"
										style="object-fit: cover;">
								</div>

								<!-- Phần danh mục và ngày tháng -->
								<div class="p-3 bg-light">
									<div class="mb-2" style="font-size: 13px;">
										<a href="/listNews/${news.category.id}">${news.category.nameCategory}</a> <span
											class="px-1">/</span> <span>${news.pubdate}</span>
									</div>

									<!-- Phần tiêu đề -->
									<a class="h4 m-0" href="/detail-news/${news.id}">${news.title}</a>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
				<!-- Thế Giới -->

				<!-- Kinh Doanh -->
				<div class="col-lg-6 py-3">
					<div class="bg-light py-2 px-4 mb-3">
						<h3 class="m-0">Kinh Doanh</h3>
					</div>
					<div
						class="owl-carousel owl-carousel-3 carousel-item-2 position-relative">
						<c:forEach var="news" items="${listNews.kinhDoanh}" begin="0"
							end="9">
							<div class="position-relative">
								<!-- Phần ảnh với kích thước cố định -->
								<div style="width: 100%; height: 240px;">
									<img class="img-fluid w-100 h-100" src="${news.image}"
										style="object-fit: cover;">
								</div>

								<!-- Phần danh mục và ngày tháng -->
								<div class="p-3 bg-light">
									<div class="mb-2" style="font-size: 13px;">
										<a href="/listNews/${news.category.id}">${news.category.nameCategory}</a> <span
											class="px-1">/</span> <span>${news.pubdate}</span>
									</div>

									<!-- Phần tiêu đề -->
									<a class="h4 m-0" href="/detail-news/${news.id}">${news.title}</a>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
				<!-- Kinh Doanh -->
			</div>
		</div>
	</div>
	<!-- Thế Giới - Kinh Doanh -->

	<!-- Giải Trí - Thể Thao -->
	<div class="container-fluid">
		<div class="container">
			<div class="row">
				<!-- Giải Trí -->
				<div class="col-lg-6 py-3">
					<div class="bg-light py-2 px-4 mb-3">
						<h3 class="m-0">Giải Trí</h3>
					</div>
					<div
						class="owl-carousel owl-carousel-3 carousel-item-2 position-relative">
						<c:forEach var="news" items="${listNews.giaiTri}" begin="0"
							end="9">
							<div class="position-relative">
								<!-- Phần ảnh với kích thước cố định -->
								<div style="width: 100%; height: 240px;">
									<img class="img-fluid w-100 h-100" src="${news.image}"
										style="object-fit: cover;">
								</div>

								<!-- Phần danh mục và ngày tháng -->
								<div class="p-3 bg-light">
									<div class="mb-2" style="font-size: 13px;">
										<a href="/listNews/${news.category.id}">${news.category.nameCategory}</a> <span
											class="px-1">/</span> <span>${news.pubdate}</span>
									</div>

									<!-- Phần tiêu đề -->
									<a class="h4 m-0" href="/detail-news/${news.id}">${news.title}</a>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
				<!-- Giải Trí -->

				<!-- Thể Thao -->
				<div class="col-lg-6 py-3">
					<div class="bg-light py-2 px-4 mb-3">
						<h3 class="m-0">Thể thao</h3>
					</div>
					<div
						class="owl-carousel owl-carousel-3 carousel-item-2 position-relative">
						<c:forEach var="news" items="${listNews.theThao}" begin="0"
							end="9">
							<div class="position-relative">
								<!-- Phần ảnh với kích thước cố định -->
								<div style="width: 100%; height: 240px;">
									<img class="img-fluid w-100 h-100" src="${news.image}"
										style="object-fit: cover;">
								</div>

								<!-- Phần danh mục và ngày tháng -->
								<div class="p-3 bg-light">
									<div class="mb-2" style="font-size: 13px;">
										<a href="/listNews/${news.category.id}">${news.category.nameCategory}</a> <span
											class="px-1">/</span> <span>${news.pubdate}</span>
									</div>

									<!-- Phần tiêu đề -->
									<a class="h4 m-0" href="/detail-news/${news.id}">${news.title}</a>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
				<!-- Thể Thao -->
			</div>
		</div>
	</div>
	<!-- Giải Trí - Thể Thao -->


	<!-- Giáo Dục -->
	<div class="container-fluid py-3">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="row mb-3">
						<div class="col-12">
							<div
								class="d-flex align-items-center justify-content-between bg-light py-2 px-4 mb-3">
								<h3 class="m-0">Giáo dục</h3>
								<a
									class="text-secondary font-weight-medium text-decoration-none"
									href="/listNews/${listNews.giaoDuc[0].category.id}">Xem tất cả</a>
							</div>
						</div>
						<c:forEach var="news" items="${listNews.giaoDuc}" begin="0"
							end="1">
							<div class="col-lg-6 mb-3">
								<div class="position-relative" style="overflow: hidden;">
									<!-- Phần ảnh với kích thước cố định -->
									<div style="width: 100%; height: 300px;">
										<img class="img-fluid w-100 h-100" src="${news.image}"
											style="object-fit: cover;">
									</div>

									<!-- Phần danh mục và ngày tháng -->
									<div class="p-3 bg-light">
										<div class="mb-2" style="font-size: 14px;">
											<a href="/listNews/${news.category.id}">${news.category.nameCategory}</a> <span
												class="px-1">/</span> <span>${news.pubdate}</span>
										</div>

										<!-- Phần tiêu đề -->
										<a class="h4" href="/detail-news/${news.id}">${news.title}</a>

										<!-- Phần mô tả -->
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
	<!-- Giáo Dục-->

	<!-- mainContent end -->


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
	<script src="client/lib/easing/easing.min.js"></script>
	<script src="client/lib/owlcarousel/owl.carousel.min.js"></script>

	<!-- Template Javascript -->
	<script src="client/js/main.js"></script>
</body>

</html>