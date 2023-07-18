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
<%@ include file="common/navbar.jsp" %>
<div class="container">
    <div class="row">
        <div class="col-md-8">
            <h5 class="my-4">Most Viewed Articles</h5>
            <c:forEach items="${mostViewed}" var="article">
                <div class="card shadow my-3">
                    <div class="card-body" onclick="toArticle(${article.id})">
                        <i class="link-secondary fw-bold fs-4 font-italic">${article.title}</i>
                        <div class="row m-2">
                            <c:choose>
                                <c:when test="${article.content.length() gt 2000}">
                                    ${article.content.substring(0, 2000)} ...
                                </c:when>
                                <c:otherwise>
                                    ${article.content}
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
        <div class="col-md-4">
            <h5 class="my-4">Articles with comments</h5>
            <c:forEach items="${mostCommented}" var="article">
                <div class="border rounded p-2 my-3" onclick="toArticle(${article.id})">
                    <li class="list-group-item d-flex justify-content-between align-items-start">
                        <div class="ms-2 me-auto">
                            <h5 class="link-secondary fw-bold my-1">${article.title}</h5>
                            <div>
                                <c:choose>
                                    <c:when test="${article.content.length() gt 200}">
                                        ${article.content.substring(0, 200)} ...
                                    </c:when>
                                    <c:otherwise>
                                        ${article.content}
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                        <i class="bi bi-trash py-1 m-3"></i>
                    </li>
                </div>
            </c:forEach>
        </div>
    </div>
    <h4 class="my-4">Recent Articles</h4>
    <div class="row">
        <c:forEach items="${mostRecent}" var="article">
        <div class="col-md-3 col-sm-6 mb-4">
            <div class="card" onclick="toArticle(${article.id})">
                <div class="card-body">
                    <i class="link-secondary fw-bold fs-4 font-italic">${article.title}</i>
                    <div class="row m-2">
                        <c:choose>
                            <c:when test="${article.content.length() gt 200}">
                                ${article.content.substring(0, 200)} ...
                            </c:when>
                            <c:otherwise>
                                ${article.content}
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </div>
        </c:forEach>
    </div>
</div>
</body>
<script>
    function toArticle(articleId) {
        window.location.href="/article/detail?id=" + articleId;
    }
</script>
</html>