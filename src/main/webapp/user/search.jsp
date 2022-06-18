<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Users</title>
    <link rel="stylesheet" href="/assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/assets/css/style.css">
    <script src="/assets/bootstrap/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-lg-6">
            <h1>List of users</h1>
        </div>
        <div class="col-lg-6">
            <a href="/users?action=create">
                <button type="button" class="btn btn-outline-success">Create User</button>
            </a>
        </div>
    </div>

    <div class="mb-3 col-lg-3">
        <form>
            <input type="hidden" name="action" value="search">
            <label for="dob" class="form-label">DOB</label>
            <input type="date" class="form-control" id="dob" name="dob">
            <button type="submit" class="btn btn-outline-success">Search</button>
        </form>
    </div>

    <div>
        <table class="table table-hover">
            <thead>
            <tr>
                <th>ID</th>
                <th>Full name</th>
                <th>DOB</th>
                <th>Phone</th>
                <th>Address</th>
                <th>City</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope['dtoList']}" var="item">
                <tr>
                    <td>${item.getId()}</td>
                    <td>${item.getFullName()}</td>
                    <td>${item.getDob()}</td>
                    <td>${item.getPhone()}</td>
                    <td>${item.getAddress()}</td>
                    <td>${item.getCityName()}</td>
                    <td>
                        <a href="/users?action=edit&id=${item.getId()}">
                            <button type="button" class="btn btn-outline-primary">Edit</button>
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <div class="footer">
        <c:if test="${!requestScope['errors'].isEmpty()}">
            <ul class="error">
                <c:forEach items="${requestScope['errors']}" var="item">
                    <li>${item}</li>
                </c:forEach>
            </ul>
        </c:if>
    </div>
</div>

</body>
</html>
