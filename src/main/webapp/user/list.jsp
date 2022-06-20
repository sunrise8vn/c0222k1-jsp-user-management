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

        <div>
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Full name</th>
                        <th>Phone</th>
                        <th>Address</th>
                        <th>City</th>
                        <th colspan="2">Action</th>
                    </tr>
                </thead>
                <tbody id="contentCustomer">
<%--                <c:forEach items="${requestScope['userList']}" var="item">--%>
<%--                    <tr>--%>
<%--                        <td>${item.getId()}</td>--%>
<%--                        <td>${item.getFullName()}</td>--%>
<%--                        <td>${item.getPhone()}</td>--%>
<%--                        <td>${item.getAddress()}</td>--%>
<%--                        <td>${item.getCityName()}</td>--%>
<%--                        <td>--%>
<%--                            <a href="/users?action=edit&id=${item.getId()}">--%>
<%--                                <button type="button" class="btn btn-outline-primary">Edit</button>--%>
<%--                            </a>--%>
<%--                        </td>--%>
<%--                    </tr>--%>
<%--                </c:forEach>--%>
                </tbody>
            </table>
        </div>
    </div>

    <script>

        function getAllCustomers() {
            fetch('http://localhost:8089/api/users?action=find-all')
                .then(response => response.json())
                .then(data => {
                    let str = '';

                    data.forEach((item, index) => {
                        str += `
                            <tr id="tr_\${item.id}">
                                <td>\${item.id}</td>
                                <td>\${item.fullName}</td>
                                <td>\${item.phone}</td>
                                <td>\${item.address}</td>
                                <td>\${item.cityName}</td>
                                <td>
                                    <a href="/users?action=edit&id=\${item.id}">
                                        <button type="button" class="btn btn-outline-primary">Edit</button>
                                    </a>
                                </td>
                                <td>
                                    <a href="javascript:void(0)" onclick="confirmDelete(\${item.id})">
                                        <button type="button" class="btn btn-outline-danger">Delete</button>
                                    </a>
                                </td>
                            </tr>
                        `;
                    });

                    let contentCustomer = document.getElementById("contentCustomer");
                    contentCustomer.innerHTML = str;

                });
        }

        getAllCustomers();

        function confirmDelete(customerId) {
            let text = "Are you sure delete this user ID = " + customerId;
            if (confirm(text) === true) {
                handlerDelete(customerId);
            }
        }

        function handlerDelete(customerId) {
            fetch('http://localhost:8089/api/users?action=delete&id=' + customerId)
                .then(response => response.json())
                .then(data => {
                    if (data === "success") {
                        let rowId = document.getElementById("tr_" + customerId);
                        rowId.remove();
                    }
                    else {
                        alert("Delete fail")
                    }
                });
        }

    </script>

</body>
</html>
