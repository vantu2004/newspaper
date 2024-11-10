<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Topbar Start -->
<div class="container-fluid mt-3 mb-3">
	<div class="row align-items-center py-2 px-lg-5">
		<!-- Logo Section (5/12) -->
		<div class="col-lg-5">
			<a href="#" class="navbar-brand d-none d-lg-block">
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
					<a class="dropdown-item" href="#">Thế giới</a> <a
						class="dropdown-item" href="#">Thời sự</a> <a
						class="dropdown-item" href="#">Kinh doanh</a> <a
						class="dropdown-item" href="#">Startup</a> <a
						class="dropdown-item" href="#">Giải trí</a> <a
						class="dropdown-item" href="#">Thể thao</a> <a
						class="dropdown-item" href="#">Pháp luật</a> <a
						class="dropdown-item" href="#">Giáo dục</a> <a
						class="dropdown-item" href="#">Sức khỏe</a> <a
						class="dropdown-item" href="#">Đời sống</a> <a
						class="dropdown-item" href="#">Du lịch</a> <a
						class="dropdown-item" href="#">Khoa học</a> <a
						class="dropdown-item" href="#">Số hóa</a> <a class="dropdown-item"
						href="#">Xe</a> <a class="dropdown-item" href="#">Ý kiến</a> <a
						class="dropdown-item" href="#">Tâm sự</a>
				</div>
			</div>

			<!-- Kiểm tra người dùng đã đăng nhập -->
			<c:if test="${not empty pageContext.request.userPrincipal}">
				<!-- Start list -->
				<div class="btn-group">
					<a data-toggle="dropdown" class="dropdown-toggle-icon"><i
						class="fas fa-bell"></i> <i class="fas fa-angle-down"></i></a>

					<div class="dropdown-menu dropdown-menu-right">
						<a class="dropdown-item" href="#">Action</a> <a
							class="dropdown-item" href="#">Another action</a> <a
							class="dropdown-item" href="#">Something else here</a> <a
							class="dropdown-item" href="#">Separated link</a>
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

						<a class="dropdown-item" href="/client/user/update/${sessionScope.id}">Quản lý tài khoản</a>
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
				<a href="index.html" class="nav-item nav-link active"><i
					class="fas fa-home"></i></a> <a href="category.html"
					class="nav-item nav-link">Thời sự</a> <a href="single.html"
					class="nav-item nav-link">Thế giới</a> <a href="index.html"
					class="nav-item nav-link">Kinh doanh</a> <a href="category.html"
					class="nav-item nav-link">Giải trí</a> <a href="category.html"
					class="nav-item nav-link">Thể thao</a> <a href="single.html"
					class="nav-item nav-link">Giáo dục</a> <a href="single.html"
					class="nav-item nav-link">Theo dõi</a>
			</div>
		</div>
	</nav>
</div>
<!-- Navbar End -->