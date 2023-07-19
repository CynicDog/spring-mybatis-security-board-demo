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

<%--TODO: Social Network Service: followings and followers --%>

<div class="container">
    <div class="row my-4 justify-content-center align-items-center">
        <div class="col-9">
            <div class="card shadow">
                <div class="card-header">My Page</div>
                <div class="card-body">
                    <div class="row m-2">
                        <label for="email" class="col-sm-2 col-form-label"><span style="white-space: nowrap">Email</span></label>
                        <div class="col-sm-10">
                            <p class="form-control" id="email"> ${user.username} </p>
                        </div>
                    </div>
                    <div class="row m-2">
                        <label for="create-date" class="col-sm-2 col-form-label"><span style="white-space: nowrap">Joined Date</span></label>
                        <div class="col-sm-10">
                            <p class="form-control" id="create-date">
                                <fmt:formatDate value="${user.user.createDate}" pattern="yyyy-MM-dd"/>
                            </p>
                        </div>
                    </div>
                    <div class="row m-2">
                        <label for="roles" class="col-sm-2 col-form-label"><span style="white-space: nowrap">Roles</span></label>
                        <div class="col-sm-10">
                            <c:forEach items="${user.user.roles}" var="role">
                            <span class="form-control" id="roles">${role} </span>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <c:if test="${not empty articles}" >
        <div class="row my-4 justify-content-center align-items-center">
            <div class="col-9">
                <div class="card shadow overflow-auto" style="max-height: 500px;">
                    <div class="card-header">My Articles</div>
                    <div class="card-body">
                        <ol class="list-group list-group-numbered">

                            <c:forEach items="${articles}" var="article">
                                <li class="list-group-item d-flex justify-content-between align-items-start">
                                    <div class="ms-2 me-auto">
                                        <div class="fw-bold">${article.title}</div>
                                        <c:choose>
                                            <c:when test="${article.content.length() gt 50}">
                                                ${article.content.substring(0, 50)} ...
                                            </c:when>
                                            <c:otherwise>
                                                ${article.content}
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                    <a href="" class="btn btn-outline-danger btn-sm my-3" >delete</a>
                                </li>
                            </c:forEach>
                        </ol>
                    </div>
                </div>
            </div>
        </div>
    </c:if>
</div>
</body>
</html>
