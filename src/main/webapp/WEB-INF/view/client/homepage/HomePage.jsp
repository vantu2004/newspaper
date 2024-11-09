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
	<!-- Main News Slider Start -->
	<div class="container-fluid py-3">
		<div class="container">
			<div class="row">
				<div class="col-lg-8">
					<div
						class="owl-carousel owl-carousel-2 carousel-item-1 position-relative mb-3 mb-lg-0">
						<div class="position-relative overflow-hidden"
							style="height: 435px;">
							<img class="img-fluid h-100"
								src="https://i1-suckhoe.vnecdn.net/2024/11/09/image001-1731115275-5933-1731115422.jpg?w=1200&h=0&q=100&dpr=1&fit=crop&s=lydsQY1D42aLfeMcdwA1xA"
								style="object-fit: cover;">
							<div class="overlay">
								<div class="mb-1">
									<a class="text-white" href="">Technology</a> <span
										class="px-2 text-white">/</span> <a class="text-white" href="">January
										01, 2045</a>
								</div>
								<a class="h2 m-0 text-white font-weight-bold" href="">Sanctus
									amet sed amet ipsum lorem. Dolores et erat et elitr sea sed</a>
							</div>
						</div>
						<div class="position-relative overflow-hidden"
							style="height: 435px;">
							<img class="img-fluid h-100" src="img/news-700x435-2.jpg"
								style="object-fit: cover;">
							<div class="overlay">
								<div class="mb-1">
									<a class="text-white" href="">Technology</a> <span
										class="px-2 text-white">/</span> <a class="text-white" href="">January
										01, 2045</a>
								</div>
								<a class="h2 m-0 text-white font-weight-bold" href="">Sanctus
									amet sed amet ipsum lorem. Dolores et erat et elitr sea sed</a>
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-4">
					<div
						class="d-flex align-items-center justify-content-between bg-light py-2 px-4 mb-3">
						<h3 class="m-0">Categories</h3>
						<a class="text-secondary font-weight-medium text-decoration-none"
							href="">View All</a>
					</div>
					<div class="position-relative overflow-hidden mb-3"
						style="height: 80px;">
						<img class="img-fluid w-100 h-100" src="img/cat-500x80-1.jpg"
							style="object-fit: cover;"> <a href=""
							class="overlay align-items-center justify-content-center h4 m-0 text-white text-decoration-none">
							Business </a>
					</div>
					<div class="position-relative overflow-hidden mb-3"
						style="height: 80px;">
						<img class="img-fluid w-100 h-100" src="img/cat-500x80-2.jpg"
							style="object-fit: cover;"> <a href=""
							class="overlay align-items-center justify-content-center h4 m-0 text-white text-decoration-none">
							Technology </a>
					</div>
					<div class="position-relative overflow-hidden mb-3"
						style="height: 80px;">
						<img class="img-fluid w-100 h-100" src="img/cat-500x80-3.jpg"
							style="object-fit: cover;"> <a href=""
							class="overlay align-items-center justify-content-center h4 m-0 text-white text-decoration-none">
							Entertainment </a>
					</div>
					<div class="position-relative overflow-hidden"
						style="height: 80px;">
						<img class="img-fluid w-100 h-100" src="img/cat-500x80-4.jpg"
							style="object-fit: cover;"> <a href=""
							class="overlay align-items-center justify-content-center h4 m-0 text-white text-decoration-none">
							Sports </a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Main News Slider End -->


	<!-- Featured News Slider Start -->
	<div class="container-fluid py-3">
		<div class="container">
			<div
				class="d-flex align-items-center justify-content-between bg-light py-2 px-4 mb-3">
				<h3 class="m-0">Featured</h3>
				<a class="text-secondary font-weight-medium text-decoration-none"
					href="">View All</a>
			</div>
			<div
				class="owl-carousel owl-carousel-2 carousel-item-4 position-relative">
				<div class="position-relative overflow-hidden"
					style="height: 300px;">
					<img class="img-fluid w-100 h-100"
						src="https://i1-thethao.vnecdn.net/2024/11/07/mcJPG-1730993996-6650-1730994302.jpg?w=1200&h=0&q=100&dpr=1&fit=crop&s=ZbrAuTAKac-_Vw18wCp_xg"
						style="object-fit: cover;">
					<div class="overlay">
						<div class="mb-1" style="font-size: 13px;">
							<a class="text-white" href="">Technology</a> <span
								class="px-1 text-white">/</span> <a class="text-white" href="">January
								01, 2045</a>
						</div>
						<a class="h4 m-0 text-white" href="">Sanctus amet sed ipsum
							lorem</a>
					</div>
				</div>
				<div class="position-relative overflow-hidden"
					style="height: 300px;">
					<img class="img-fluid w-100 h-100"
						src="https://i1-vnexpress.vnecdn.net/2024/09/17/439top-1726542258-6200-1726542293.png?w=1200&h=0&q=100&dpr=1&fit=crop&s=5sb18uAs0ahHHaFDzP8wnQ"
						style="object-fit: cover;">
					<div class="overlay">
						<div class="mb-1" style="font-size: 13px;">
							<a class="text-white" href="">Technology</a> <span
								class="px-1 text-white">/</span> <a class="text-white" href="">January
								01, 2045</a>
						</div>
						<a class="h4 m-0 text-white" href="">Sanctus amet sed ipsum
							lorem</a>
					</div>
				</div>
				<div class="position-relative overflow-hidden"
					style="height: 300px;">
					<img class="img-fluid w-100 h-100"
						src="https://i1-vnexpress.vnecdn.net/2024/10/01/462gifezgifcomcrop-1727766251-9338-1727766320.gif?w=1200&h=0&q=100&dpr=1&fit=crop&s=EI2KGi9WUZZH8rbD5RQvuQ&t=image"
						style="object-fit: cover;">
					<div class="overlay">
						<div class="mb-1" style="font-size: 13px;">
							<a class="text-white" href="">Technology</a> <span
								class="px-1 text-white">/</span> <a class="text-white" href="">January
								01, 2045</a>
						</div>
						<a class="h4 m-0 text-white" href="">Sanctus amet sed ipsum
							lorem</a>
					</div>
				</div>
				<div class="position-relative overflow-hidden"
					style="height: 300px;">
					<img class="img-fluid w-100 h-100"
						src="https://i1-vnexpress.vnecdn.net/2024/10/31/cg2a61671657953602169289148926-7953-4142-1730364439.jpg?w=1200&h=0&q=100&dpr=1&fit=crop&s=tkgKC04uOwH_I3L2I89h6w"
						style="object-fit: cover;">
					<div class="overlay">
						<div class="mb-1" style="font-size: 13px;">
							<a class="text-white" href="">Technology</a> <span
								class="px-1 text-white">/</span> <a class="text-white" href="">January
								01, 2045</a>
						</div>
						<a class="h4 m-0 text-white" href="">Sanctus amet sed ipsum
							lorem</a>
					</div>
				</div>
				<div class="position-relative overflow-hidden"
					style="height: 300px;">
					<img class="img-fluid w-100 h-100"
						src="https://i1-vnexpress.vnecdn.net/2024/11/04/DALLE20240607143358Amodernillu-7297-2094-1730686057.png?w=1200&h=0&q=100&dpr=1&fit=crop&s=i5BKxF6-vgPZQNRp1wUH4A"
						style="object-fit: cover;">
					<div class="overlay">
						<div class="mb-1" style="font-size: 13px;">
							<a class="text-white" href="">Technology</a> <span
								class="px-1 text-white">/</span> <a class="text-white" href="">January
								01, 2045</a>
						</div>
						<a class="h4 m-0 text-white" href="">Sanctus amet sed ipsum
							lorem</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Featured News Slider End -->


	<!-- Category News Slider Start -->
	<div class="container-fluid">
		<div class="container">
			<div class="row">
				<div class="col-lg-6 py-3">
					<div class="bg-light py-2 px-4 mb-3">
						<h3 class="m-0">Business</h3>
					</div>
					<div
						class="owl-carousel owl-carousel-3 carousel-item-2 position-relative">
						<div class="position-relative">
							<img class="img-fluid w-100" src="img/news-500x280-1.jpg"
								style="object-fit: cover;">
							<div class="overlay position-relative bg-light">
								<div class="mb-2" style="font-size: 13px;">
									<a href="">Technology</a> <span class="px-1">/</span> <span>January
										01, 2045</span>
								</div>
								<a class="h4 m-0" href="">Sanctus amet sed ipsum lorem</a>
							</div>
						</div>
						<div class="position-relative">
							<img class="img-fluid w-100" src="img/news-500x280-2.jpg"
								style="object-fit: cover;">
							<div class="overlay position-relative bg-light">
								<div class="mb-2" style="font-size: 13px;">
									<a href="">Technology</a> <span class="px-1">/</span> <span>January
										01, 2045</span>
								</div>
								<a class="h4 m-0" href="">Sanctus amet sed ipsum lorem</a>
							</div>
						</div>
						<div class="position-relative">
							<img class="img-fluid w-100" src="img/news-500x280-3.jpg"
								style="object-fit: cover;">
							<div class="overlay position-relative bg-light">
								<div class="mb-2" style="font-size: 13px;">
									<a href="">Technology</a> <span class="px-1">/</span> <span>January
										01, 2045</span>
								</div>
								<a class="h4 m-0" href="">Sanctus amet sed ipsum lorem</a>
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-6 py-3">
					<div class="bg-light py-2 px-4 mb-3">
						<h3 class="m-0">Technology</h3>
					</div>
					<div
						class="owl-carousel owl-carousel-3 carousel-item-2 position-relative">
						<div class="position-relative">
							<img class="img-fluid w-100" src="img/news-500x280-4.jpg"
								style="object-fit: cover;">
							<div class="overlay position-relative bg-light">
								<div class="mb-2" style="font-size: 13px;">
									<a href="">Technology</a> <span class="px-1">/</span> <span>January
										01, 2045</span>
								</div>
								<a class="h4 m-0" href="">Sanctus amet sed ipsum lorem</a>
							</div>
						</div>
						<div class="position-relative">
							<img class="img-fluid w-100" src="img/news-500x280-5.jpg"
								style="object-fit: cover;">
							<div class="overlay position-relative bg-light">
								<div class="mb-2" style="font-size: 13px;">
									<a href="">Technology</a> <span class="px-1">/</span> <span>January
										01, 2045</span>
								</div>
								<a class="h4 m-0" href="">Sanctus amet sed ipsum lorem</a>
							</div>
						</div>
						<div class="position-relative">
							<img class="img-fluid w-100" src="img/news-500x280-6.jpg"
								style="object-fit: cover;">
							<div class="overlay position-relative bg-light">
								<div class="mb-2" style="font-size: 13px;">
									<a href="">Technology</a> <span class="px-1">/</span> <span>January
										01, 2045</span>
								</div>
								<a class="h4 m-0" href="">Sanctus amet sed ipsum lorem</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Category News Slider End -->


	<!-- Category News Slider Start -->
	<div class="container-fluid">
		<div class="container">
			<div class="row">
				<div class="col-lg-6 py-3">
					<div class="bg-light py-2 px-4 mb-3">
						<h3 class="m-0">Entertainment</h3>
					</div>
					<div
						class="owl-carousel owl-carousel-3 carousel-item-2 position-relative">
						<div class="position-relative">
							<img class="img-fluid w-100" src="img/news-500x280-6.jpg"
								style="object-fit: cover;">
							<div class="overlay position-relative bg-light">
								<div class="mb-2" style="font-size: 13px;">
									<a href="">Technology</a> <span class="px-1">/</span> <span>January
										01, 2045</span>
								</div>
								<a class="h4 m-0" href="">Sanctus amet sed ipsum lorem</a>
							</div>
						</div>
						<div class="position-relative">
							<img class="img-fluid w-100" src="img/news-500x280-5.jpg"
								style="object-fit: cover;">
							<div class="overlay position-relative bg-light">
								<div class="mb-2" style="font-size: 13px;">
									<a href="">Technology</a> <span class="px-1">/</span> <span>January
										01, 2045</span>
								</div>
								<a class="h4 m-0" href="">Sanctus amet sed ipsum lorem</a>
							</div>
						</div>
						<div class="position-relative">
							<img class="img-fluid w-100" src="img/news-500x280-4.jpg"
								style="object-fit: cover;">
							<div class="overlay position-relative bg-light">
								<div class="mb-2" style="font-size: 13px;">
									<a href="">Technology</a> <span class="px-1">/</span> <span>January
										01, 2045</span>
								</div>
								<a class="h4 m-0" href="">Sanctus amet sed ipsum lorem</a>
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-6 py-3">
					<div class="bg-light py-2 px-4 mb-3">
						<h3 class="m-0">Sports</h3>
					</div>
					<div
						class="owl-carousel owl-carousel-3 carousel-item-2 position-relative">
						<div class="position-relative">
							<img class="img-fluid w-100" src="img/news-500x280-3.jpg"
								style="object-fit: cover;">
							<div class="overlay position-relative bg-light">
								<div class="mb-2" style="font-size: 13px;">
									<a href="">Technology</a> <span class="px-1">/</span> <span>January
										01, 2045</span>
								</div>
								<a class="h4 m-0" href="">Sanctus amet sed ipsum lorem</a>
							</div>
						</div>
						<div class="position-relative">
							<img class="img-fluid w-100" src="img/news-500x280-2.jpg"
								style="object-fit: cover;">
							<div class="overlay position-relative bg-light">
								<div class="mb-2" style="font-size: 13px;">
									<a href="">Technology</a> <span class="px-1">/</span> <span>January
										01, 2045</span>
								</div>
								<a class="h4 m-0" href="">Sanctus amet sed ipsum lorem</a>
							</div>
						</div>
						<div class="position-relative">
							<img class="img-fluid w-100"
								src="https://i1-suckhoe.vnecdn.net/2024/11/09/image001-1731115275-5933-1731115422.jpg?w=1200&h=0&q=100&dpr=1&fit=crop&s=lydsQY1D42aLfeMcdwA1xA"
								style="object-fit: cover;">
							<div
								class="overlay position-absolute top-0 bottom-0 start-0 end-0 bg-light p-3">
								<div class="mb-1" style="font-size: 13px;">
									<a href="">Technology</a> <span class="px-1">/</span> <span>January
										01, 2045</span>
								</div>
								<a class="h4 m-0" href="">Sanctus amet sed ipsum lorem</a>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Category News Slider End -->


	<!-- News With Sidebar Start -->
	<div class="container-fluid py-3">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="row mb-3">
						<div class="col-12">
							<div
								class="d-flex align-items-center justify-content-between bg-light py-2 px-4 mb-3">
								<h3 class="m-0">Popular</h3>
								<a
									class="text-secondary font-weight-medium text-decoration-none"
									href="">View All</a>
							</div>
						</div>
						<div class="col-lg-6">
							<div class="position-relative mb-3">
								<img class="img-fluid w-100" src="img/news-500x280-2.jpg"
									style="object-fit: cover;">
								<div class="overlay position-relative bg-light">
									<div class="mb-2" style="font-size: 14px;">
										<a href="">Technology</a> <span class="px-1">/</span> <span>January
											01, 2045</span>
									</div>
									<a class="h4" href="">Est stet amet ipsum stet clita rebum
										duo</a>
									<p class="m-0">Rebum dolore duo et vero ipsum clita, est ea
										sed duo diam ipsum, clita at justo, lorem amet vero eos sed
										sit...</p>
								</div>
							</div>

						</div>
						<div class="col-lg-6">
							<div class="position-relative mb-3">
								<img class="img-fluid w-100" src="img/news-500x280-3.jpg"
									style="object-fit: cover;">
								<div class="overlay position-relative bg-light">
									<div class="mb-2" style="font-size: 14px;">
										<a href="">Technology</a> <span class="px-1">/</span> <span>January
											01, 2045</span>
									</div>
									<a class="h4" href="">Est stet amet ipsum stet clita rebum
										duo</a>
									<p class="m-0">Rebum dolore duo et vero ipsum clita, est ea
										sed duo diam ipsum, clita at justo, lorem amet vero eos sed
										sit...</p>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- News With Sidebar End -->

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