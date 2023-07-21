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
    <div>
        <div class="row my-2 justify-content-center align-items-center">
            <div class="col-8">
                <div class="card shadow my-5">
                    <div class="card-header">Sign up</div>
                    <div class="card-body">
                        <div class="row my-2 p-2">
                            <form method="post" action="register">
                                <div class="form-group my-3">
                                    <input type="email" class="form-control" name="email" placeholder="example@test.com">
                                </div>
                                <div class="form-group my-3">
                                    <input type="password" class="form-control" name="password" placeholder="password">
                                </div>
                                <div class="text-end my-2">
                                    <button class="btn btn-outline-secondary btn-sm">submit</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>