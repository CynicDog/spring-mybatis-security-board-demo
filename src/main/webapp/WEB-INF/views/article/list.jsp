<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<title>Application</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
</head>
<body>
<%@ include file="../common/navbar.jsp" %>
<div class="container">
	<div class="row my-3 justify-content-center align-items-center">
		<div class="col-12">
			<h1 class="border bg-light p-2 fs-4">Articles</h1>
		</div>
	</div>
	<div class="row my-3 justify-content-center align-items-center">
		<div class="col-12">
			<table class="table">
				<thead>
					<tr>
						<th style="width: 10%;">Number</th>
						<th style="width: 25%;">Title</th>
						<th style="width: 15%;">View Cnt.</th>
						<th style="width: 15%;">Review Cnt.</th>
						<th style="width: 20%;">Author</th>
						<th style="width: 15%;">Published at</th>
					</tr>
				</thead>
				<tbody>
				<c:choose>
					<c:when test="${not empty articles}">
						<c:forEach items="${articles}" var="article">
							<tr>
								<td>${article.id}</td>
								<td><a href="">${article.title}</a></td>
								<td>${article.readCount}</td>
								<td>${article.reviewCount}</td>
								<td>${article.author.email}</td>
								<td><fmt:formatDate value="${article.createDate}" pattern="yyyy-MM-dd"/></td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
					<tr >
						<td colspan="6" class="text-center">No articles are found.</td>
					</tr>
					</c:otherwise>
				</c:choose>
				</tbody>
			</table>
			<sec:authorize access="isAuthenticated()">
				<div class="text-end">
					<a href="add" class="btn btn-outline-primary btn-sm">Post new article</a>
				</div>
			</sec:authorize>
		</div>
	</div>
	<div class="row mb-3" >
		<div class="col-12">
			<nav>
				<ul class="pagination justify-content-center">
					<li class="page-item">
						<a class="page-link"  href="list?page=1" >Prev</a>
					</li>
					
					<li class="page-item" >
						<a class="page-link" href="list?page=1" >1</a>
					</li>
					<li class="page-item" >
						<a class="page-link" href="list?page=2" >2</a>
					</li>
					<li class="page-item" >
						<a class="page-link" href="list?page=3" >3</a>
					</li>
					
					<li class="page-item">
						<a class="page-link" href="list?page=2" >Next</a>
					</li>
				</ul>
			</nav>
		</div>
	</div>
</div>
</body>
</html>