<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="Văn Tú - Dự án laptopshop" />
<meta name="author" content="Văn Tú" />
<title>Update</title>
<link
	href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css"
	rel="stylesheet" />
<link href="/css/styles.css" rel="stylesheet" />

<!-- dùng jquery để preview upload file -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script>

    $(document).ready(() => {
        const avatarFile = $("#avatarFile");
        avatarFile.change(function (e) {
            const imgURL = URL.createObjectURL(e.target.files[0]);
            $("#avatarPreview").attr("src", imgURL);
            $("#avatarPreview").css({ "display": "block" }); // Hiện ảnh preview
            
            // Ẩn ảnh người dùng nếu preview hiển thị
            $("#userAvatar").css({ "display": "none" });
        });
    });
    
</script>
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

					<div class="row">
						<div class="col-md-6 col-12 mx-auto">
							<h3>Update a product</h3>
							<hr />
							<form:form method="POST" action="/admin/product/update"
								modelAttribute="newProduct" class="row"
								enctype="multipart/form-data">
								<div class="mb-3 col-12 col-md-6">
									<div class="mb-3" style="display: none">
										<label class="form-label">Id:</label>
										<form:input type="text" class="form-control" path="id" />
									</div>
									<c:set var="errorName">
										<form:errors path="name" cssClass="invalid-feedback" />
									</c:set>
									<label class="form-label">Name:</label>
									<form:input type="text"
										class="form-control ${not empty errorName ? 'is-invalid' : ''}"
										path="name" />
									${errorName}
								</div>
								<div class="mb-3 col-12 col-md-6">
									<c:set var="errorPrice">
										<form:errors path="price" cssClass="invalid-feedback" />
									</c:set>
									<label class="form-label">Price:</label>
									<form:input type="number"
										class="form-control ${not empty errorPrice ? 'is-invalid' : ''}"
										path="price" min="0" step="0.01" />
									${errorPrice}
								</div>
								<div class="mb-3 col-12">
									<c:set var="errorShortDes">
										<form:errors path="shortDesc" cssClass="invalid-feedback" />
									</c:set>
									<label class="form-label">Short description:</label>
									<form:textarea
										class="form-control ${not empty errorShortDes ? 'is-invalid' : ''}"
										path="shortDesc" />
									${errorShortDes}
								</div>
								<div class="mb-3 col-12">
									<c:set var="errorDetailDes">
										<form:errors path="detailDesc" cssClass="invalid-feedback" />
									</c:set>
									<label class="form-label">Detail description:</label>
									<form:textarea
										class="form-control ${not empty errorDetailDes ? 'is-invalid' : ''}"
										path="detailDesc" />
									${errorDetailDes}
								</div>
								<div class="mb-3 col-12 col-md-6">
									<label class="form-label">Factory:</label>
									<form:select class="form-select" path="factory">
										<form:option value="APPLE">Apple (Macbook)</form:option>
										<form:option value="ASUS">Asus</form:option>
										<form:option value="LENOVO">Lenovo</form:option>
										<form:option value="DELL">Dell</form:option>
										<form:option value="LG">LG</form:option>
										<form:option value="ACER">Acer</form:option>

									</form:select>
								</div>
								<div class="mb-3 col-12 col-md-6">
									<label class="form-label">Target:</label>
									<form:select class="form-select" path="target">
										<form:option value="GAMING">Gaming</form:option>
										<form:option value="SINHVIEN-VANPHONG">Sinh viên - Văn phòng</form:option>
										<form:option value="THIET-KE-DO-HOA">Thiết kế đồ họa</form:option>
										<form:option value="MONG-NHE">Mỏng nhẹ</form:option>
										<form:option value="DOANH-NHAN">Doanh nhân</form:option>
									</form:select>
								</div>
								<div class="mb-3 col-12 col-md-6">
									<c:set var="errorQuantity">
										<form:errors path="quantity" cssClass="invalid-feedback" />
									</c:set>
									<label class="form-label">Quantity:</label>
									<form:input type="number"
										class="form-control ${not empty errorQuantity ? 'is-invalid' : ''}"
										path="quantity" min="0" step="1" />
									${errorQuantity}
								</div>
								<div class="mb-3 col-12 col-md-6">
									<label for="avatarFile" class="form-label">Product
										image:</label> <input class="form-control" type="file" id="avatarFile"
										accept=".png, .jpg, .jpeg" name="imageFile" />
								</div>
								<div class="col-12 mb-3">
									<img style="max-height: 250px; display: none;"
										alt="Product preview" id="avatarPreview" />
								</div>
								<c:if test="${not empty productImage}">
									<div class="mb-3 col-md-12">
										<img src="/productImage/${productImage}" alt="Product image"
											class="img-thumbnail" style="object-fit: cover; width: 100%;"
											id="userAvatar">
									</div>
								</c:if>
								<div class="d-flex justify-content-end mb-5">
									<button type="submit" class="btn btn-primary">Update</button>
								</div>
							</form:form>

						</div>

					</div>
				</div>
			</main>

			<jsp:include page="../layout/Footer.jsp" />

		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
		crossorigin="anonymous"></script>
	<script src="/js/scripts.js"></script>

</body>

</html>