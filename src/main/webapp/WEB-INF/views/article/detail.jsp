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
    <div class="row my-4 justify-content-center align-items-center">
        <div class="col-10">
            <div class="my-2">
                <i class="fw-bold fs-3 font-italic">${article.title}</i>
                <div class="row m-4">
                    ${article.content}
                </div>
            </div>
        </div>
    </div>

    <div class="row my-4 justify-content-center align-items-center">
        <div class="col-10">
            Comments <span>(${comments.size()})</span>
            <c:if test="${not empty comments}">
                <hr/>
                <c:forEach items="${comments}" var="comment">
                    <div class="border rounded my-1">
                        <li class="list-group-item d-flex justify-content-between align-items-start">
                            <div class="ms-2 me-auto">
                                <div class="my-1">
                                    <span style="color: #888888">
                                        ${comment.user.email}
                                        <sec:authorize access="isAuthenticated()">
                                            <sec:authentication property="principal.username" var="authenticatedUserEmail"/>
                                            <c:if test="${comment.user.email ne authenticatedUserEmail}">
                                                <i class="bi bi-envelope-fill ms-1"></i>
                                                <i class="bi bi-person-plus-fill ms-1"
                                                   onclick="sendRequest(${comment.user.id})"></i>
                                            </c:if>
                                        </sec:authorize>
                                    </span>
                                </div>
                                <div>
                                        ${comment.content}
                                </div>
                            </div>
                            <i class="bi bi-trash py-1 m-3"></i>
                        </li>
                    </div>
                </c:forEach>
            </c:if>
            <hr/>
            <form method="post" action="leave-comment">
                <input name="article-id" value="${article.id}" hidden>
                <textarea rows="5" class="form-control" id="post-content" name="content"></textarea>
                <sec:authorize access="isAuthenticated()">
                    <div class="text-end">
                        <button type="submit" class="btn btn-outline-secondary btn-sm my-1">leave a comment</button>
                    </div>
                </sec:authorize>
                <sec:authorize access="isAnonymous()">
                    <div class="text-end">
                        <button type="submit" class="btn btn-outline-secondary btn-sm my-1" onclick="loginFirst(event)">
                            login first
                        </button>
                    </div>
                </sec:authorize>
                <%--                TODO: up / down vote on comments --%>
            </form>
        </div>
    </div>
</div>
</body>
<script>
    function loginFirst() {
        event.preventDefault();
        window.location.href = "/user/login?error=denied";
    }

    function sendRequest(commenterId) {
        var confirmation = confirm("Are you sure you want to send a request?");

        if (confirmation) {
            let xhr = new XMLHttpRequest();
            xhr.open("GET", "/follow/request?recipient-id=" + commenterId, true);
            xhr.responseType = "text";

            xhr.onload = function () {
                if (xhr.status === 200) {
                    alert("Request successfully sent.")
                } else {
                    alert(xhr.responseText);
                }
            };

            xhr.send();
        }
    }
</script>
</html>