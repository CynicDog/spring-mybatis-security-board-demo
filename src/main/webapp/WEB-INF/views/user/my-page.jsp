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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.6.0/font/bootstrap-icons.css" rel="stylesheet">
</head>
<body>
<%@ include file="../common/navbar.jsp" %>
<div class="container">
    <div class="row my-4">
        <c:if test="${not empty articles}">
            <div class="col-md-5 my-2">
                <div class="card shadow overflow-auto" style="max-height: 900px;">
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
                                    <i style="color: #cb444a" class="bi bi-trash m-2"></i>
                                </li>
                            </c:forEach>
                        </ol>
                    </div>
                </div>
            </div>
        </c:if>
        <div class="col-md-7 my-2">
            <div class="card shadow mb-3">
                <div class="card-header">My Page</div>
                <div class="card-body">
                    <div class="row m-2">
                        <div class="col-sm-3">
                            <label for="email" class="col-sm-2 col-form-label"><span
                                    style="white-space: nowrap">Email</span></label>
                        </div>
                        <div class="col-sm-9">
                            <p class="form-control" id="email"> ${user.username} </p>
                        </div>
                    </div>
                    <div class="row m-2">
                        <div class="col-sm-3">
                            <label for="create-date" class="col-sm-2 col-form-label"><span style="white-space: nowrap">Joined Date</span></label>
                        </div>
                        <div class="col-sm-9">
                            <p class="form-control" id="create-date">
                                <fmt:formatDate value="${user.user.createDate}" pattern="yyyy-MM-dd"/>
                            </p>
                        </div>
                    </div>
                    <div class="row m-2">
                        <div class="col-sm-3">
                            <label for="roles" class="col-sm-2 col-form-label"><span
                                    style="white-space: nowrap">Roles</span></label>
                        </div>
                        <div class="col-sm-9">
                            <c:forEach items="${user.user.roles}" var="role">
                                <span class="form-control" id="roles">${role} </span>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
            <div class="card shadow">
                <div class="card-header">Socials</div>
                <div class="card-body">

                    <c:if test="${not empty followings}"></c:if>
                    <c:if test="${not empty follwers}"></c:if>

                    <div class="row m-3">
                        <div class="col">
                            <div class="border rounded">

                            </div>
                        </div>
                        <div class="col">
                            <div class="border rounded">

                            </div>
                        </div>
                    </div>

                    <c:if test="${not empty recipients}">
                        <div class="p-2 m-3">
                            <p class="my-1">Sent requests</p>
                            <hr/>
                            <c:forEach items="${recipients}" var="recipient">
                                <div class="border rounded p-2 my-2 d-flex align-items-center">
                                    <span class="fw-bold mx-1">${recipient.email}</span>
                                    <div class="ms-auto">
                                        <span class="badge bg-primary">Pending</span>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </c:if>
                    <c:if test="${not empty senders}">
                        <div class="p-2 m-3">
                            <p class="my-1">Arrived requests</p>
                            <hr/>
                            <c:forEach items="${senders}" var="sender">
                                <div class="border rounded p-2 my-2 d-flex align-items-center">
                                    <span class="fw-bold mx-1">${sender.email}</span>
                                    <div class="ms-auto">
                                        <a href="" class="btn btn-outline-secondary btn-sm"
                                           onclick="accept(${sender.id})">accept</a>
                                        <a href="" class="btn btn-outline-danger btn-sm"
                                           onclick="decline()">decline</a>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script>

    function accept(senderId) {

        let xhr = new XMLHttpRequest();
        xhr.open("POST", "/follow/accept?sender-id=" + senderId, true);
        xhr.responseType = "text";

        xhr.onload = function () {
            if (xhr.status === 200) {
                alert("success message")
                window.location.reload();
            } else {
                alert(xhr.responseText);
            }
        };

        xhr.send();

    }

    function decline(senderId) {

        let xhr = new XMLHttpRequest();
        xhr.open("POST", "/follow/decline?id=" + senderId, true);
        xhr.responseType = "text";

        xhr.onload = function () {
            if (xhr.status === 200) {
                alert("success message")
            } else {
                alert(xhr.responseText);
            }
        };

        xhr.send();
    }

</script>
</html>
