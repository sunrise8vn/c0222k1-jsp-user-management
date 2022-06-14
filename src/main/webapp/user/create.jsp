<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Create user</title>
    <link rel="stylesheet" href="/assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/assets/css/style.css">
    <script src="/assets/bootstrap/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-lg-6">
            <h1>Create user</h1>
        </div>
        <div class="col-lg-6">
            <a href="/users">
                <button type="button" class="btn btn-outline-success">List of users</button>
            </a>
        </div>
    </div>

    <div>
        <form method="post">
            <div class="mb-3">
                <label for="fullName" class="form-label">Full name</label>
                <input type="text" class="form-control" id="fullName" name="fullName">
            </div>
            <div class="mb-3">
                <label for="phone" class="form-label">Phone</label>
                <input type="tel" class="form-control" id="phone" name="phone">
            </div>
            <div class="mb-3">
                <label for="address" class="form-label">Address</label>
                <input type="text" class="form-control" id="address" name="address">
            </div>
            <button type="submit" class="btn btn-outline-primary">Create</button>
        </form>
    </div>

    <div class="footer">
        <c:if test="${requestScope['success'] == true}">
            <ul class="success">
                <li>Thêm mới thành công</li>
            </ul>
        </c:if>
        <c:if test="${requestScope['error'] == true}">
            <ul class="error">
                <li>Thêm mới thất bại</li>
            </ul>
        </c:if>
    </div>
</div>

</body>
</html>
