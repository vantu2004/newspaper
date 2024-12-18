<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<title>News</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="Free HTML Templates" name="keywords">
<meta content="Free HTML Templates" name="description">

<!-- Favicon -->
<link href="/client/img/favicon.ico" rel="icon">

<!-- Google Web Fonts -->
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700;900&display=swap"
	rel="stylesheet">

<!-- Font Awesome -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.0/css/all.min.css"
	rel="stylesheet">
<link rel='stylesheet prefetch'
	href='https://netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css'>

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

	<!-- News With Sidebar Start -->
	<div class="container-fluid py-3">
		<div class="container">
			<div class="row">
				<div class="col-lg-8">
					<!-- News Detail Start -->
					<div class="position-relative mb-3">
						<div class="overlay position-relative bg-light">
							<div
								class="mb-3 d-flex justify-content-between align-items-center"
								style="width: 100%">
								<div>
									<a href="/listNews/${news.category.id}">${news.category.nameCategory}</a>
									<span class="px-1">/</span> <span>${news.pubdate}</span>
								</div>
								<c:if test="${not empty pageContext.request.userPrincipal}">
									<c:if test="${!checkExistUserNews}">
										<form action="/follow-news/${news.id}" method="post"
											class="d-flex align-items-center justify-content-end">
											<input type="hidden" name="${_csrf.parameterName}"
												value="${_csrf.token}" />

											<button type="submit"
												class="mx-auto btn border border-secondary px-3 text-primary">
												<i class="fas fa-plus-square"></i> Lưu tin
											</button>
										</form>
									</c:if>

									<c:if test="${checkExistUserNews}">
										<form action="/unfollow-news/${news.id}" method="post"
											class="d-flex align-items-center justify-content-end">
											<input type="hidden" name="${_csrf.parameterName}"
												value="${_csrf.token}" />

											<button type="submit"
												class="mx-auto btn border border-secondary px-3 text-primary">
												<i class="fas fa-minus-square"></i> Bỏ lưu tin
											</button>
										</form>
									</c:if>
								</c:if>
							</div>

							<div>
								<h3 class="mb-3">${news.title}</h3>
								<p>
									<img class="img-fluid w-100" src="${news.image}"
										style="object-fit: cover;">
								</p>
								<c:out value="${news.content}" escapeXml="false" />
								<p>${news.author}</p>
								
								<p>Nguồn: <a href="${news.link}">VnExpress</a></p>
							</div>

							<!-- Đánh giá chủ đề bài viết -->
							<c:if test="${not empty pageContext.request.userPrincipal}">
								<div style="width: 100%; text-align: center; margin-top: 10px">
									<p style="font-size: 14px; font-style: italic; margin: 0;">(Đánh
										giá chất lượng chủ đề bài viết)</p>

									<form:form action="/rating" method="post"
										modelAttribute="userCategory"
										style="display: inline-block; text-align: center;">

										<!-- CSRF token -->
										<input type="hidden" name="${_csrf.parameterName}"
											value="${_csrf.token}" />

										<div style="display: block;">
											<!-- Các radio button đánh giá sao -->
											<div class="rating-stars">
												<form:radiobutton class="star star-5" id="star-5"
													path="interactionScore" value="5" />
												<label class="star star-5" for="star-5"></label>

												<form:radiobutton class="star star-4" id="star-4"
													path="interactionScore" value="4" />
												<label class="star star-4" for="star-4"></label>

												<form:radiobutton class="star star-3" id="star-3"
													path="interactionScore" value="3" />
												<label class="star star-3" for="star-3"></label>

												<form:radiobutton class="star star-2" id="star-2"
													path="interactionScore" value="2" />
												<label class="star star-2" for="star-2"></label>

												<form:radiobutton class="star star-1" id="star-1"
													path="interactionScore" value="1" />
												<label class="star star-1" for="star-1"></label>
											</div>

											<!-- Truyền categoryId -->
											<form:hidden path="category.id" value="${news.category.id}" />

											<!-- Truyền userId -->
											<form:hidden path="user.id" value="${userId}" />

											<!-- Truyền newsId -->
											<input type="hidden" name="newsId" value="${news.id}" />
										</div>

										<!-- Nút submit -->
										<button type="submit"
											class="btn border border-secondary px-3 text-primary">
											Xác nhận</button>
									</form:form>

								</div>
							</c:if>
							<!-- Đánh giá chủ đề bài viết -->


						</div>
					</div>
					<!-- News Detail End -->


					<!-- Comment List Start -->
					<div class="bg-light mb-3" style="padding: 30px;">
						<h3 class="mb-4">Bình luận</h3>
<c:forEach var="comment" items="${listComment}">
    <div class="media mb-4">
        <c:if test="${not empty comment.user.avatar}">
            <img src="/avatar/${comment.user.avatar}" alt="Image"
                class="img-fluid mr-3 mt-1 rounded-circle"
                style="width: 45px; height: 45px;">
        </c:if>
        <c:if test="${empty comment.user.avatar}">
            <img src="/avatar/noUser.png" alt="Image"
                class="img-fluid mr-3 mt-1 rounded-circle"
                style="width: 45px; height: 45px;">
        </c:if>
        <div class="media-body">
            <h6>
                <a href="">${comment.user.fullName}</a>
                <small><i class="comment-datetime">${comment.commentDatetime}</i></small>
            </h6>
            <p>${comment.content}</p>
        </div>
    </div>
</c:forEach>

					</div>
					<!-- Comment List End -->

					<!-- Comment Form Start -->
					<c:if test="${not empty pageContext.request.userPrincipal}">
						<div class="bg-light mb-3" style="padding: 30px;">
							<h3 class="mb-4">Bình luận</h3>
							<form method="post" action="/comment">
								<!-- CSRF token -->
								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />
								<div class="form-group" style="display: none">
									<label for="message">NewsId</label> <input name="newsId"
										value="${news.id}" />
								</div>
								<div class="form-group">
									<label for="message">Chi tiết</label>
									<textarea id="message" cols="30" rows="5" class="form-control"
										name="comment"></textarea>
								</div>
								<div class="form-group mb-0">
									<input type="submit" value="Gửi"
										class="btn btn-primary font-weight-semi-bold py-2 px-3">
								</div>
							</form>
						</div>
					</c:if>
					<!-- Comment Form End -->
				</div>

				<div class="col-lg-4 pt-3 pt-lg-0">
					<!-- Popular News Start -->
					<div class="pb-3">
						<div class="bg-light py-2 px-4 mb-3">
							<h3 class="m-0">Dành cho bạn</h3>
						</div>

						<c:forEach var="news" items="${recommendationNews}" begin="0"
							end="6">
							<div class="d-flex mb-3">
								<img src="${news.image}"
									style="width: 100px; height: 100px; object-fit: cover;">
								<div
									class="w-100 d-flex flex-column justify-content-center bg-light px-3"
									style="height: 100px;">
									<div class="mb-1" style="font-size: 13px;">
										<a href="/listNews/${news.category.id}">${news.category.nameCategory}</a>
										<span class="px-1">/</span> <span>${news.pubdate}</span>
									</div>
									<a class="h6 m-0" href="/detail-news/${news.id}">${news.title}</a>
								</div>
							</div>
						</c:forEach>

					</div>
					<!-- Popular News End -->

					<!-- Danh mục đề xuất -->
					<%-- 					<div class="pb-3">
						<div class="bg-light py-2 px-4 mb-3">
							<h3 class="m-0">Danh mục đề xuất</h3>
						</div>
						<div class="d-flex flex-wrap m-n1">
							<c:forEach var="news" items="${recommendationNews}" begin="0"
							end="1">
								<a href="/listNews/${categoryId + 1}"
									class="btn btn-sm btn-outline-secondary m-1">${category.nameCategory}</a>
							</c:forEach>
						</div>
					</div> --%>
					<!-- Danh mục đề xuất -->

					<!-- Tất cả danh mục -->
					<div class="pb-3">
						<div class="bg-light py-2 px-4 mb-3">
							<h3 class="m-0">Tất cả danh mục</h3>
						</div>
						<div class="d-flex flex-wrap m-n1">
							<a href="/listNews/1"
								class="btn btn-sm btn-outline-secondary m-1">Thế Giới</a> <a
								href="/listNews/2" class="btn btn-sm btn-outline-secondary m-1">Thời
								Sự</a> <a href="/listNews/3"
								class="btn btn-sm btn-outline-secondary m-1">Kinh Doanh</a> <a
								href="/listNews/4" class="btn btn-sm btn-outline-secondary m-1">Startup</a>
							<a href="/listNews/5"
								class="btn btn-sm btn-outline-secondary m-1">Thể Thao</a> <a
								href="/listNews/6" class="btn btn-sm btn-outline-secondary m-1">Giải
								Trí</a> <a href="/listNews/7"
								class="btn btn-sm btn-outline-secondary m-1">Pháp Luật</a> <a
								href="/listNews/8" class="btn btn-sm btn-outline-secondary m-1">Giáo
								Dục</a> <a href="/listNews/9"
								class="btn btn-sm btn-outline-secondary m-1">Sức Khỏe</a> <a
								href="/listNews/10" class="btn btn-sm btn-outline-secondary m-1">Đời
								Sống</a> <a href="/listNews/11"
								class="btn btn-sm btn-outline-secondary m-1">Du Lịch</a> <a
								href="/listNews/12" class="btn btn-sm btn-outline-secondary m-1">Khoa
								Học</a> <a href="/listNews/13"
								class="btn btn-sm btn-outline-secondary m-1">Số Hóa</a> <a
								href="/listNews/14" class="btn btn-sm btn-outline-secondary m-1">Xe</a>
							<a href="/listNews/15"
								class="btn btn-sm btn-outline-secondary m-1">Ý Kiến</a> <a
								href="/listNews/16" class="btn btn-sm btn-outline-secondary m-1">Tâm
								Sự</a>
						</div>
					</div>
					<!-- Tất cả danh mục -->
				</div>
			</div>
		</div>
	</div>
	<!-- News With Sidebar End -->

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