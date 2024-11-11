<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Topbar Start -->
<div class="container-fluid mt-3 mb-3">
	<div class="row align-items-center py-2 px-lg-5">
		<!-- Logo Section (5/12) -->
		<div class="col-lg-5">
			<a href="/" class="navbar-brand d-none d-lg-block">
				<h1 class="m-0 display-5 text-uppercase">
					<span class="text-primary">News</span>Room
				</h1>
			</a>
		</div>

		<!-- Search Section (5/12) -->
		<div class="col-lg-5 d-flex justify-content-end">
			<div class="input-group" style="width: 100%; max-width: 300px;">
				<input type="text" class="form-control" placeholder="Keyword">
				<div class="input-group-append">
					<button class="input-group-text text-secondary">
						<i class="fa fa-search"></i>
					</button>
				</div>
			</div>
		</div>

		<div
			class="col-lg-2 d-flex align-items-center justify-content-between">
			<!-- Start list -->
			<div class="btn-group">
				<a data-toggle="dropdown" class="dropdown-toggle-icon"><i
					class="fas fa-list-ul"></i> <i class="fas fa-angle-down"></i></a>

				<div class="dropdown-menu dropdown-menu-right">
					<a class="dropdown-item" href="/listNews/1">Thế giới</a> <a
						class="dropdown-item" href="/listNews/2">Thời sự</a> <a
						class="dropdown-item" href="/listNews/3">Kinh doanh</a> <a
						class="dropdown-item" href="/listNews/4">Startup</a> <a
						class="dropdown-item" href="/listNews/5">Giải trí</a> <a
						class="dropdown-item" href="/listNews/6">Thể thao</a> <a
						class="dropdown-item" href="/listNews/7">Pháp luật</a> <a
						class="dropdown-item" href="/listNews/8">Giáo dục</a> <a
						class="dropdown-item" href="/listNews/9">Sức khỏe</a> <a
						class="dropdown-item" href="/listNews/10">Đời sống</a> <a
						class="dropdown-item" href="/listNews/11">Du lịch</a> <a
						class="dropdown-item" href="/listNews/12">Khoa học</a> <a
						class="dropdown-item" href="/listNews/13">Số hóa</a> 
						<a class="dropdown-item" href="/listNews/14">Xe</a> 
						<a class="dropdown-item" href="/listNews/15">Ý kiến</a> <a
						class="dropdown-item" href="/listNews/16">Tâm sự</a>
				</div>
			</div>

			<!-- Kiểm tra người dùng đã đăng nhập -->
			<c:if test="${not empty pageContext.request.userPrincipal}">
				<!-- Start list -->
				<div class="btn-group">
					<a data-toggle="dropdown" class="dropdown-toggle-icon"><i
						class="fas fa-bell"></i> <i class="fas fa-angle-down"></i></a>

					<div class="dropdown-menu dropdown-menu-right"
						style="width: 450px; padding: 20px; max-height: 400px; overflow-y: auto;">
						<c:forEach var="news" items="${listNews.tinMoi}" begin="0" end="9">
							<div
								class="d-flex align-items-start justify-content-between py-0 px-0 mb-3">
								<div class="d-flex flex-column" style="flex: 3;">
									<a class="m-0" style="color: black;">${news.title}</a> <a
										href="">${listNews.getCategoryName().get(Long.valueOf(news.category.getId()))}</a>
									<span class="px-1"></span>
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
				<!-- End list -->

				<!-- Start account -->
				<div class="btn-group">
					<a data-toggle="dropdown" class="dropdown-toggle-icon"><i
						class="fas fa-user"></i> <i class="fas fa-angle-down"></i></a>

					<div class="dropdown-menu dropdown-menu-right"
						style="width: 250px; padding: 20px;">

						<div class="text-center">
							<c:choose>
								<c:when test="${not empty sessionScope.avatar}">
									<img
										style="width: 150px; height: 150px; border-radius: 50%; overflow: hidden;"
										src="avatar/${sessionScope.avatar}" />
								</c:when>
								<c:otherwise>
									<img
										style="width: 150px; height: 150px; border-radius: 50%; overflow: hidden;"
										src="avatar/noUser.png" />
								</c:otherwise>
							</c:choose>
							<div class="text-center my-3">
								<c:out value="${sessionScope.fullName}" />
							</div>
						</div>

						<a class="dropdown-item"
							href="/client/user/update/${sessionScope.id}">Quản lý tài
							khoản</a>
						<div>
							<form method="post" action="/logout">
								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />
								<button class="dropdown-item">Đăng xuất</button>
							</form>
						</div>
					</div>
				</div>

				<!-- End account -->
			</c:if>

			<!-- Nếu chưa đăng nhập -->
			<c:if test="${empty pageContext.request.userPrincipal}">
				<div>
					<a href="/login" class="position-relative me-4 my-auto">Đăng
						nhập</a>
				</div>
			</c:if>
		</div>
	</div>
</div>
<!-- Topbar End -->

<!-- Navbar Start -->
<div class="container-fluid p-0 mb-3">
	<nav
		class="navbar navbar-expand-lg bg-light navbar-light py-2 py-lg-0 px-lg-5">
		<div
			class="collapse navbar-collapse justify-content-between px-0 px-lg-3"
			id="navbarCollapse">
			<div class="navbar-nav mr-auto py-0">
				<a href="/" class="nav-item nav-link active"><i
					class="fas fa-home"></i></a> 
					<a href="/listNews/2" class="nav-item nav-link">Thời sự</a> 
					<a href="/listNews/1" class="nav-item nav-link">Thế giới</a> 
					<a href="/listNews/3" class="nav-item nav-link">Kinh doanh</a> 
					<a href="/listNews/6" class="nav-item nav-link">Giải trí</a> 
					<a href="/listNews/5" class="nav-item nav-link">Thể thao</a> 
					<a href="/listNews/8" class="nav-item nav-link">Giáo dục</a> 
					<a href="/" class="nav-item nav-link">Theo dõi</a>
			</div>
		</div>
	</nav>
</div>
<!-- Navbar End -->