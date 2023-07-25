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
    <div class="row my-3 justify-content-center align-items-center">

        <div class="col-8">
            <div class="card">
                <div class="fs-4 p-2 m-2">Publish an article.</div>
            </div>
        </div>
    </div>
    <div class="row my-3 justify-content-center align-items-center">
        <div class="col-8">
            <form class="card shadow p-3" method="post" action="add">
                <div class="form-group my-3">
                    <label class="form-label">Title</label>
                    <input type="text" class="form-control" id="article-title" name="title"/>
                </div>
                <div class="form-group my-3">
                    <label class="form-label">Content</label>
                    <textarea rows="5" class="form-control" id="article-content" name="content"></textarea>
                </div>
                <div class="text-end">
                    <a href="list?page=1" class="btn btn-secondary btn-sm">cancel</a>
                    <button type="submit" class="btn btn-primary btn-sm">submit</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>