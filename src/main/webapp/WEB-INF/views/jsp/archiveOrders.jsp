<!DOCTYPE html>
<html lang="en">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <jsp:include page="templates/header.jsp"/>
</head>

<body>
<jsp:include page="templates/nav.jsp"/>

<main role="main" class="container">

    <h1 class="mt-5">Archive orders</h1>

    <table class="table table-bordered">
        <thead>
        <c:if test = "${archiveOrderList!= null && archiveOrderList.size() != 0}">
            <tr>
                <th class="align-middle" scope="col"><p class="text-center">Order number</p></th>
                <th class="align-middle" scope="col"><p class="text-center">Ready</p></th>
                <th class="align-middle" scope="col"><p class="text-center">Route list</p></th>
                <th class="align-middle" scope="col"><p class="text-center">Truck</p></th>
                <th class="align-middle" scope="col"><p class="text-center">Trucker list</p></th>
            </tr>
        </c:if>
        </thead>
        <tbody>
        <c:forEach items="${archiveOrderList}" var="order">
            <tr>
                <td class="align-middle">
                    <p class="text-center"><c:out value="${order.orderId}"/></p>
                </td>

                <td class="align-middle">
                    <p class="text-center"><c:out value="${order.ready}"/></p>
                </td>

                <td class="align-middle">
                    <p class="text-center"><c:out value="${order.routeList}"/></p>
                </td>

                <td class="align-middle">
                    <p class="text-center"><c:out value="${order.truck}"/></p>
                </td>

                <td class="align-middle">
                    <p class="text-center"><c:out value="${order.truckerList}"/></p>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</main>
<jsp:include page="templates/footer.jsp"/>
</body>
</html>
