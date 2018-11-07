<!DOCTYPE html>
<html lang="en">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <jsp:include page="templates/header.jsp"/>
</head>

<body>
<jsp:include page="templates/nav.jsp"/>

<main role="main" class="container">

    <h1 class="mt-5"><c:out value="${trucker.lastName}"/> <c:out value="${trucker.firstName}"/></h1>

    <form action="/truckerUi/trucker" method="post">
        <div>
            <ul class="list-group">
                <li class="list-group-item list-group-item-dark">Personal number: <c:out value="${trucker.personalNumber}"/></li>
                <%-- co-trucker personalNumber--%>
                <c:if test="${not empty cotrucker}">
                    <li class="list-group-item list-group-item-dark">Co-trucker personal number: <c:out value="${cotrucker.personalNumber}"/></li>
                </c:if>
                <c:if test="${not empty trucker.truck}">
                <li class="list-group-item list-group-item-dark">Truck regNumber: <c:out value="${trucker.truck.regNumber}"/></li>
                <li class="list-group-item list-group-item-dark">Order number: <c:out value="${trucker.truck.order.id}"/></li>
                </c:if>
                <%-- RouteList --%>
                <c:if test="${routeList != null && routeList.size() != 0}">
                    <li class="list-group-item list-group-item-light">Route list:</li>
                    <c:forEach items="${routeList}" var="route">
                        <li class="list-group-item list-group-item-dark"><c:out value="${route}"/></li>
                    </c:forEach>
                </c:if>
            </ul>
        </div>
    </form>
</main>
<jsp:include page="templates/footer.jsp"/>
</body>
</html>
