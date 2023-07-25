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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">

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
                                            <sec:authentication property="principal.username"
                                                                var="authenticatedUserEmail"/>
                                            <c:if test="${comment.user.email ne authenticatedUserEmail}">
                                                <i class="bi bi-envelope-fill ms-1"
                                                   id="mail-icon"
                                                   data-bs-container="body"
                                                   data-bs-toggle="popover"
                                                   data-bs-placement="bottom"
                                                   data-bs-html="true"
                                                   data-bs-content="<p class='link-secondary m-1' id='mail-confirmation'>Wanna write to?</p>"
                                                   data-commenter='${comment.user.id}'></i>
<%--                                               TODO --%>
                                                <i class="bi bi-person-plus-fill ms-1"
                                                   id="follow-icon"
                                                   data-bs-container="body"
                                                   data-bs-toggle="popover"
                                                   data-bs-placement="top"
                                                   data-bs-html="true"
                                                   data-bs-content="
                                                       <p class='link-secondary m-1 ${comment.user.id}' id='follow-confirmation'>Wanna follow?</p>
                                                   "
                                                   ></i>
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
                    <div class="toast-container position-fixed bottom-0 end-0 p-4">
                        <div id="liveToast" class="toast" role="alert" aria-live="assertive" aria-atomic="true">
                            <div class="toast-header">
                                <div class="me-auto">To: <strong>${comment.user.email}</strong></div>
                                <button type="button" class="btn-close" data-bs-dismiss="toast"
                                        aria-label="Close"></button>
                            </div>
                            <div class="toast-body">
                                <div class="row">
                                    <div class="col">
                                        <label class="fw-lighter" for="floatingInput">title</label>
                                        <input class="form-control-plaintext" name="mail-title" id="floatingInput">
                                        <label class="fw-lighter" for="floatingTextarea">content</label>
                                        <textarea class="form-control-plaintext my-1" name="mail-content"
                                                  id="floatingTextarea" style="height: 350px"></textarea>
                                    </div>
                                </div>
                                <div class="text-start">
                                    <i class="fs-6 bi bi-send m-1" style="color: #838383"
                                       onclick="sendEmail('${comment.user.email}')"></i>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="toast-container position-fixed bottom-0 end-0 p-4">
                        <div id="successfulToast" class="toast align-items-center text-bg-primary border-0" role="alert"
                             aria-live="assertive" aria-atomic="true">
                            <div class="d-flex">
                                <div class="toast-body">
                                    Done successfully :)
                                </div>
                                <button type="button" class="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
                            </div>
                        </div>
                    </div>
                    <div class="toast-container position-fixed bottom-0 end-0 p-4">
                        <div id="failedToast" class="toast align-items-center text-bg-secondary border-0" role="alert"
                             aria-live="assertive" aria-atomic="true">
                            <div class="d-flex">
                                <div class="toast-body">
                                    Something happened :(
                                </div>
                                <button type="button" class="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
                            </div>
                        </div>
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
<script>
    function loginFirst() {
        event.preventDefault();
        window.location.href = "/user/login?error=denied";
    }

    function sendRequest(commenterId) {

        let xhr = new XMLHttpRequest();
        xhr.open("GET", "/follow/request?recipient-id=" + commenterId, true);
        xhr.responseType = "text";

        xhr.onload = function () {
            if (xhr.status === 200) {
                successfulToast("Request successfully sent.")
            } else {
                failedToast(xhr.responseText);
            }
        };

        xhr.send();
    }

    // TODO:
    function sendEmail(commenterEmail) {

        console.log(commenterEmail)

        let toastLiveExample = document.getElementById('liveToast')
        const toastLiveBootstrap = bootstrap.Toast.getOrCreateInstance(toastLiveExample)
        toastLiveBootstrap.hide();

        successfulToast("Sent successfully :)");
    }

    function successfulToast(message) {
        let successfulToast = document.getElementById('successfulToast')

        const toastBody = document.querySelector('.toast-container #successfulToast .toast-body');
        toastBody.textContent = message;

        const toastSentBootstrap = bootstrap.Toast.getOrCreateInstance(successfulToast)
        toastSentBootstrap.show()
    }

    function failedToast(message) {
        let failedToast = document.getElementById('failedToast')

        const toastBody = document.querySelector('.toast-container #failedToast .toast-body');
        toastBody.textContent = message;

        const toastSentBootstrap = bootstrap.Toast.getOrCreateInstance(failedToast)
        toastSentBootstrap.show()
    }

    let popoverTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="popover"]'))
    let popoverList = popoverTriggerList.map(function (popoverTriggerEl) {
        return new bootstrap.Popover(popoverTriggerEl)
    })

    document.body.addEventListener('click', function (event) {
        if (event.target.matches('#follow-confirmation')) {

            let targetElementClassList = event.target.getAttribute('class').split(' ');
            let commenterId = targetElementClassList[targetElementClassList.length - 1];

            console.log(commenterId);

            sendRequest(commenterId);
        }
    });

    document.body.addEventListener('click', function (event) {
        if (event.target.matches('#mail-confirmation')) {

            let toastLiveExample = document.getElementById('liveToast')
            const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLiveExample)
            toastBootstrap.show()
        }
    })

</script>
</body>
</html>