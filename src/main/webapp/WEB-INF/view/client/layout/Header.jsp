<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <!-- Topbar Start -->
    <div class="container-fluid">
        <div class="row align-items-center py-2 px-lg-5">
            <!-- Logo Section (5/12) -->
            <div class="col-lg-5">
                <a href="#" class="navbar-brand d-none d-lg-block">
                    <h1 class="m-0 display-5 text-uppercase"><span class="text-primary">News</span>Room</h1>
                </a>
            </div>

            <!-- Search Section (5/12) -->
            <div class="col-lg-5 d-flex justify-content-end">
                <div class="input-group" style="width: 100%; max-width: 300px;">
                    <input type="text" class="form-control" placeholder="Keyword">
                    <div class="input-group-append">
                        <button class="input-group-text text-secondary"><i class="fa fa-search"></i></button>
                    </div>
                </div>
            </div>

            <!-- Empty Section (2/12) -->
            <div class="col-lg-2 d-flex align-items-center justify-content-end ">
                <!-- Kiểm tra người dùng đã đăng nhập -->
                <c:if test="${not empty pageContext.request.userPrincipal}">
                    <!-- Notification Icon -->
                    <div class="position-relative">
                        <a href="#" class="position-relative">
                            <i class="far fa-bell fa-2x"></i>
                            <span
                                class="position-absolute bg-secondary rounded-circle d-flex align-items-center justify-content-center text-dark px-1"
                                style="top: -5px; left: 15px; height: 20px; min-width: 20px;">3</span>
                        </a>
                    </div>

                    <!-- Account Dropdown -->
                    <div class="dropdown">
                        <a href="#" class="dropdown-toggle" role="button" id="dropdownMenuLink"
                            data-bs-toggle="dropdown" aria-expanded="false">
                            <i class="far fa-user fa-2x"></i>
                        </a>
                        <ul class="dropdown-menu dropdown-menu-end p-4" aria-labelledby="dropdownMenuLink">
                            <li class="d-flex align-items-center flex-column" style="min-width: 300px;">
                                <img style="width: 150px; height: 150px; border-radius: 50%; overflow: hidden;"
                                    src="avatar/${sessionScope.avatar}" />
                                <div class="text-center my-3">
                                    <c:out value="${sessionScope.fullName}" />
                                </div>
                            </li>
                            <li><a class="dropdown-item" href="#">Quản lý tài khoản</a></li>
                            <li><a class="dropdown-item" href="#">Lịch sử mua hàng</a></li>
                            <li>
                                <hr class="dropdown-divider">
                            </li>
                            <li>
                                <form method="post" action="/logout">
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                    <button class="dropdown-item">Đăng xuất</button>
                                </form>
                            </li>
                        </ul>
                    </div>
                </c:if>

                <!-- Nếu chưa đăng nhập -->
                <c:if test="${empty pageContext.request.userPrincipal}">
                    <div>
                        <a href="/login" class="position-relative me-4 my-auto">Đăng nhập</a>
                    </div>
                </c:if>
            </div>

        </div>
    </div>
    <!-- Topbar End -->    <!-- Topbar Start -->
    <div class="container-fluid">
        <div class="row align-items-center py-2 px-lg-5">
            <!-- Logo Section (5/12) -->
            <div class="col-lg-5">
                <a href="#" class="navbar-brand d-none d-lg-block">
                    <h1 class="m-0 display-5 text-uppercase"><span class="text-primary">News</span>Room</h1>
                </a>
            </div>

            <!-- Search Section (5/12) -->
            <div class="col-lg-5 d-flex justify-content-end">
                <div class="input-group" style="width: 100%; max-width: 300px;">
                    <input type="text" class="form-control" placeholder="Keyword">
                    <div class="input-group-append">
                        <button class="input-group-text text-secondary"><i class="fa fa-search"></i></button>
                    </div>
                </div>
            </div>

            <!-- Empty Section (2/12) -->
            <div class="col-lg-2 d-flex align-items-center justify-content-end ">
                <!-- Kiểm tra người dùng đã đăng nhập -->
                <c:if test="${not empty pageContext.request.userPrincipal}">
                    <!-- Notification Icon -->
                    <div class="position-relative">
                        <a href="#" class="position-relative">
                            <i class="far fa-bell fa-2x"></i>
                            <span
                                class="position-absolute bg-secondary rounded-circle d-flex align-items-center justify-content-center text-dark px-1"
                                style="top: -5px; left: 15px; height: 20px; min-width: 20px;">3</span>
                        </a>
                    </div>

                    <!-- Account Dropdown -->
                    <div class="dropdown">
                        <a href="#" class="dropdown-toggle" role="button" id="dropdownMenuLink"
                            data-bs-toggle="dropdown" aria-expanded="false">
                            <i class="far fa-user fa-2x"></i>
                        </a>
                        <ul class="dropdown-menu dropdown-menu-end p-4" aria-labelledby="dropdownMenuLink">
                            <li class="d-flex align-items-center flex-column" style="min-width: 300px;">
                                <img style="width: 150px; height: 150px; border-radius: 50%; overflow: hidden;"
                                    src="avatar/${sessionScope.avatar}" />
                                <div class="text-center my-3">
                                    <c:out value="${sessionScope.fullName}" />
                                </div>
                            </li>
                            <li><a class="dropdown-item" href="#">Quản lý tài khoản</a></li>
                            <li><a class="dropdown-item" href="#">Lịch sử mua hàng</a></li>
                            <li>
                                <hr class="dropdown-divider">
                            </li>
                            <li>
                                <form method="post" action="/logout">
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                    <button class="dropdown-item">Đăng xuất</button>
                                </form>
                            </li>
                        </ul>
                    </div>
                </c:if>

                <!-- Nếu chưa đăng nhập -->
                <c:if test="${empty pageContext.request.userPrincipal}">
                    <div>
                        <a href="/login" class="position-relative me-4 my-auto">Đăng nhập</a>
                    </div>
                </c:if>
            </div>

        </div>
    </div>
    <!-- Topbar End -->