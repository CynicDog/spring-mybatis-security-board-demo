<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
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
    <div class="row my-3">
        <div class="card shadow px-4 my-2">
            <div class="row my-3 justify-content-center align-items-center">
                <div class="col-12">
                    <div class="p-2 fs-4">Articles
                        <select class="form-select float-end mx-1" name="rows" style="width: 150px"
                                onchange="changeRows()">
                            <option value="10" ${param.rows eq 10 ? 'selected' : ''}>10</option>
                            <option value="30" ${param.rows eq 30 ? 'selected' : ''}>30</option>
                            <option value="50" ${param.rows eq 50 ? 'selected' : ''}>50</option>
                        </select>
                    </div>
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
                            <c:when test="${not empty response.articles}">
                                <c:forEach items="${response.articles}" var="article">
                                    <tr>
                                        <td>${article.id}</td>
                                        <c:choose>
                                            <c:when test="${article.title.length() gt 25}">
                                                <td><a href="detail?id=${article.id}"
                                                       class="link-secondary link-underline-opacity-0">${article.title.substring(0, 25)}...</a>
                                                </td>
                                            </c:when>
                                            <c:otherwise>
                                                <td><a href="detail?id=${article.id}"
													   class="link-secondary link-underline-opacity-0">${article.title}</a></td>
                                            </c:otherwise>
                                        </c:choose>
                                        <td>${article.readCount}</td>
                                        <td>${article.reviewCount}</td>
                                        <td>${article.author.email}</td>
                                        <td><fmt:formatDate value="${article.createDate}" pattern="yyyy-MM-dd"/></td>
                                    </tr>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <tr>
                                    <td colspan="6" class="text-center">No articles are found.</td>
                                </tr>
                            </c:otherwise>
                        </c:choose>
                        </tbody>
                    </table>
                    <sec:authorize access="isAuthenticated()">
                        <div class="text-end">
                            <a href="add" class="btn btn-outline-secondary btn-sm">Post new article</a>
                        </div>
                    </sec:authorize>
                </div>
            </div>
        </div>
    </div>
    <div class="row mb-3">
        <div class="col-12">
            <nav>
                <ul class="pagination justify-content-center">
                    <li class="page-item ${response.pagination.first ? 'disabled': ''}">
                        <a href="" class="page-link" onclick="changePage(event, 1)">First</a>
                    </li>
                    <li class="page-item ${response.pagination.first ? 'disabled': ''}">
                        <a class="page-link" href="" onclick="changePage(event, ${response.pagination.prePage})"
                           aria-label="Prev">
                            <span aria-hidden="true">&laquo;</span>
                            <span class="sr-only"></span>
                        </a>
                    </li>
                    <c:forEach var="num" begin="${response.pagination.beginPage}"
                               end="${response.pagination.endPage}">
                        <li class="page-item ${response.pagination.page eq num ? "active" : ""}">
                            <a href="" class="page-link" onclick="changePage(event, ${num})">${num}</a>
                        </li>
                    </c:forEach>
                    <li class="page-item ${response.pagination.last ? 'disabled': ''}">
                        <a class="page-link" href="" onclick="changePage(event, ${response.pagination.nextPage})"
                           aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                            <span class="sr-only"></span>
                        </a>
                    </li>
                    <li class="page-item ${response.pagination.last ? 'disabled': ''}">
                        <a href="" class="page-link"
                           onclick="changePage(event, ${response.pagination.totalPages})">Last</a>
                    </li>
                </ul>
            </nav>
            <form id="articles-pagination-control">
                <input type="hidden" name="rows" value="${param.rows}">
                <input type="hidden" name="page" value="${param.page}">
            </form>
        </div>
    </div>

</div>
</body>
<script>
    function changeRows() {
        let rows = document.querySelector("select[name=rows]").value;

        document.querySelector("input[name=rows]").value = rows;
        document.querySelector("input[name=page]").value = 1;

        document.getElementById("articles-pagination-control").submit();
    }

    function changePage(event, page) {

        event.preventDefault();

        document.querySelector("input[name=page]").value = page;
        document.getElementById("articles-pagination-control").submit();
    }

</script>
</html>