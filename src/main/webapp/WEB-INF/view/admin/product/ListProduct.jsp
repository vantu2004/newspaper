<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="Văn Tú - Dự án laptopshop" />
<meta name="author" content="Văn Tú" />
<title>List products</title>
<link
	href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css"
	rel="stylesheet" />
<link href="/css/styles.css" rel="stylesheet" />

</head>

<body class="sb-nav-fixed">

	<jsp:include page="../layout/Header.jsp" />

	<div id="layoutSidenav">

		<jsp:include page="../layout/Sidebar.jsp" />

		<div id="layoutSidenav_content">
			<main>
				<div class="container-fluid px-4">
					<h1 class="mt-4">Manage products</h1>
					<ol class="breadcrumb mb-4">
						<li class="breadcrumb-item"><a href="/admin">Dashboard</a></li>
						<li class="breadcrumb-item active">Products</li>
					</ol>

					<div class="d-flex justify-content-between mb-4">
						<h3 style="margin: 0">Table product</h3>
						<a href="/admin/product/create"
							class="btn btn-primary d-flex justify-content-center align-items-center">Create
							a product</a>
					</div>
					<hr />
					<table class="table table-striped table-hover">
						<thead>
							<tr>
								<th scope="col">ID</th>
								<th scope="col">Name</th>
								<th scope="col">Price</th>
								<th scope="col">Factory</th>
								<th scope="col">Action</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="product" items="${products}">
								<tr>
									<th scope="row">${product.id}</th>
									<td>${product.name}</td>
									<td>${product.price}</td>
									<td>${product.factory}</td>
									<td><a href="/admin/product/${product.id}"
										class="btn btn-success me-2">View</a> <a
										href="/admin/product/update/${product.id}"
										class="btn btn-warning me-2">Update</a> <a
										href="/admin/product/delete/${product.id}"
										class="btn btn-danger me-2">Delete</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</main>

			<jsp:include page="../layout/Footer.jsp" />

		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
		crossorigin="anonymous"></script>
	<script src="js/scripts.js"></script>

</body>

</html>