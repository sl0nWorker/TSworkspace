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
    <c:if test="${not empty trucker}">
        <div class="form-group">
            <div class="row">
                <div class="col-sm-3">
                    <form id="formChangeStatus" action="/truckerUi/trucker/changeStatus" method="post">
                        <div class="form-group" style="display: none;">
                            <input type="text" value="${truckerId}" name="truckerId">
                        </div>
                        <select name="changeStatus" class="form-control">
                            <option value="FREE">
                                FREE
                            </option>
                            <option value="WORK">
                                WORK
                            </option>
                            <option value="WHEEL">
                                WHEEL
                            </option>
                        </select>
                        <button form="formChangeStatus" type="submit" class="btn btn-dark btn-block">
                            Change status
                        </button>
                    </form>
                </div>
                <c:if test="${not empty trucker.truck}">
                    <div class="col-sm-9">
                        <form id="formChangeShift" action="/truckerUi/trucker/changeShift" method="post">
                            <div class="form-group" style="display: none;">
                                <input type="text" value="${truckerId}" name="truckerId">
                            </div>
                            <div>
                                <select name="shiftStatus" class="form-control">
                                    <option value="startShift">
                                        Start shift
                                    </option>
                                    <option value="finishShift">
                                        Finish shift
                                    </option>
                                </select>
                            </div>
                            <button form="formChangeShift" type="submit" class="btn btn-dark btn-block">
                                Set shift status
                            </button>
                        </form>
                    </div>
                </c:if>
            </div>
        </div>

    </c:if>

    <div class="form-group">
        <ul class="list-group">
            <li class="list-group-item list-group-item-dark">Personal number: <c:out
                    value="${trucker.personalNumber}"/>
                | Status: <c:out value="${trucker.status}"/>
                | Work hours: <c:out value="${trucker.workHours}"/></li>
            <%-- co-trucker personalNumber--%>
            <c:if test="${not empty cotrucker}">
                <li class="list-group-item list-group-item-dark">Co-trucker personal number: <c:out
                        value="${cotrucker.personalNumber}"/></li>
            </c:if>
            <c:if test="${not empty trucker.truck}">
                <li class="list-group-item list-group-item-dark">Truck regNumber: <c:out
                        value="${trucker.truck.regNumber}"/></li>
                <li class="list-group-item list-group-item-dark">Order number: <c:out
                        value="${trucker.truck.order.id}"/></li>
            </c:if>
            <%-- RouteList --%>
            <c:if test="${routeList != null && routeList.size() != 0}">
                <li class="list-group-item list-group-item-light">Route list:</li>
                <c:forEach items="${routeList}" var="route">
                    <form action="/truckerUi/trucker/changeFreightStatus" method="post">
                            <c:choose>
                                <c:when test="${ not empty route.complete and route.complete == true}">
                                    <li class="list-group-item list-group-item-success">
                                </c:when>
                                <c:otherwise>
                                     <li class="list-group-item list-group-item-dark">
                                </c:otherwise>
                            </c:choose>
                            <div class="form-group" style="display: none;">
                                <input type="text" value="${route.id}" name="routeId">
                            </div>
                            <div class="form-group" style="display: none;">
                                <input type="text" value="${truckerId}" name="truckerId">
                            </div>
                            <div class="form-group" style="display: none;">
                                <input type="text" value="${route.freight.id}" name="freightId">
                            </div>
                            <div class="form-group" style="display: none;">
                                <input type="text" value="${route.unloading}" name="unloading">
                            </div>
                            <div class="d-flex">
                                <div class="mr-auto p-2">
                                    <c:out value="${route}"/>
                                </div>
                                <c:if test="${ empty route.complete or route.complete != true}">
                                    <div class="p-2">
                                        <button type="submit" class="btn btn-warning">
                                            complite
                                        </button>
                                    </div>
                                </c:if>
                            </div>
                    </form>
                    </li>
                </c:forEach>
                <c:if test="${not empty orderReady}">
                    <form action="/truckerUi/trucker/completeOrder" method="post">
                        <div class="form-group" style="display: none;">
                            <input type="text" value="${trucker.truck.order.id}" name="orderId">
                        </div>
                        <div class="form-group" style="display: none;">
                            <input type="text" value="${truckerId}" name="truckerId">
                        </div>
                        <button type="submit" class="btn btn-dark btn-block">
                            Complete order
                        </button>
                    </form>
                </c:if>
            </c:if>
        </ul>
    </div>
</main>
<jsp:include page="templates/footer.jsp"/>
</body>
</html>
