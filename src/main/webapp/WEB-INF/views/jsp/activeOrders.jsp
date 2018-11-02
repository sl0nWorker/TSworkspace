<!DOCTYPE html>
<html lang="en">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <jsp:include page="templates/header.jsp"/>
</head>

<body>
<jsp:include page="templates/nav.jsp"/>

<main role="main" class="container">

    <h1 class="mt-5">Active orders</h1>

    <table class="table table-bordered">
        <thead>
        <tr>
            <th colspan="5" scope="col" bgcolor="#faebd7">
                <!-- Button trigger modal -->
                <div class="d-flex justify-content-end  ">
                    <button type="button" class="btn btn-dark" onclick="location.href='/createOrder'">
                        Add new order
                    </button>
                </div>
            </th>
        </tr>
        <tr>
            <th scope="col">Order number</th>
            <th scope="col">Ready</th>
            <th scope="col">Route list</th>
            <th scope="col">Truck</th>
            <th scope="col">Trucker list</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${orderList}" var="order">
            <tr>
                <td>
                    <c:out value="${order.id}"/>
                </td>

                <td>
                    <c:out value="${order.ready}"/>
                </td>

                <td>
                    <c:forEach items="${orderList.routeList}" var="route">
                        <c:out value="${route.city}"/>
                    </c:forEach>
                </td>

                <td>
                    <c:out value="${order.truck}"/>
                </td>

                <td>
                    <c:forEach items="${orderList.truckerList}" var="trucker">
                        <c:out value="${trucker.LastName}"/>
                    </c:forEach>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</main>
<jsp:include page="templates/footer.jsp"/>
</body>
</html>