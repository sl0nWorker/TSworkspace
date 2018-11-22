<!DOCTYPE html>
<html lang="en">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <jsp:include page="templates/header.jsp"/>
</head>

<body>
<jsp:include page="templates/nav.jsp"/>

<main role="main" class="container">

    <h1 class="mt-5">Saved Route List</h1>


    <table class="table table-bordered">
        <thead>
        <c:if test="${sessionScope.savedRouteList!= null && sessionScope.savedRouteList.size() != 0}">
            <tr>
            <tr>
                <th scope="col">City</th>
                <th scope="col">Freight</th>
                <th scope="col">Loading/Unloading</th>
            </tr>
            </tr>
        </c:if>
        </thead>
        <tbody>
        <c:forEach items="${sessionScope.savedRouteList}" var="route">
            <tr>
                <td>
                    <c:out value="${route.city}"/>
                </td>


                <td>
                    <c:out value="${route.freight}"/>
                </td>

                <td>
                    <c:out value="${route.unloading}"/>
                </td>

            </tr>
        </c:forEach>
        <c:if test="${not empty sessionScope.assignedTruckId}">
            <tr>
                <td colspan="3">
                    <div class="d-flex justify-content-center">
                        <c:out value="Assigned truck - ${sessionScope.assignedTruck} "/>
                    </div>
                </td>
            </tr>
        </c:if>

        <c:if test="${not empty sessionScope.savedTruckerList}">
            <c:forEach items="${sessionScope.savedTruckerList}" var="savedTrucker">
            <tr>
                <td colspan="3">
                    <div class="d-flex justify-content-center">
                        <c:out value="Assigned trucker - ${savedTrucker} "/>
                    </div>
                </td>
            </tr>
            </c:forEach>
        </c:if>

        </tbody>
    </table>

    <%--alert, if list of truck is empty --%>
    <c:if test="${empty sessionScope.freeTruckList}">
        <div class="alert alert-danger" role="alert">
            No matching trucks
        </div>
    </c:if>

    <c:if test="${empty sessionScope.assignedTruckId && not empty sessionScope.freeTruckList}">
        <form action="/createRouteList/saveRouteList/assignTruck" method="post">
                <%--TODO: add parametr for check selected truck, and show Truckers if true, or selectTruckList if false --%>
            <div class="form-group">
                <label for="selectTruck">Truck</label>
                <select required name="truckId" class="form-control" id="selectTruck">
                    <c:forEach items="${sessionScope.freeTruckList}" var="truck">
                        <option value="${truck.id}">
                            <c:out value="${truck}"/>
                        </option>
                    </c:forEach>
                </select>
            </div>

            <div class="d-flex justify-content-start">
                <button type="submit" class="btn btn-dark">
                    Assign the truck
                </button>
            </div>
        </form>
    </c:if>

    <%--alert, if list of trucker is empty --%>
    <c:if test="${not empty sessionScope.assignedTruck && empty sessionScope.checkedTruckerList}">
        <div class="alert alert-danger" role="alert">
            No matching truckers
        </div>
    </c:if>

    <c:if test="${(not empty sessionScope.checkedTruckerList) and (sessionScope.savedTruckerList.size() < 2)}">
        <form action="/createRouteList/saveRouteList/assignTrucker" method="post">
            <div class="form-group">
                <label for="selectTrucker">Trucker</label>
                <select required name="truckerId" class="form-control" id="selectTrucker">
                    <c:forEach items="${sessionScope.checkedTruckerList}" var="checkedTrucker">
                        <option value="${checkedTrucker.id}">
                            <c:out value="${checkedTrucker}"/>
                        </option>
                    </c:forEach>
                </select>
            </div>

            <div class="d-flex justify-content-start">
                <button type="submit" class="btn btn-dark">
                    Assign the trucker
                </button>
            </div>
        </form>
    </c:if>

    <c:if test="${sessionScope.savedTruckerList.size() >= 2}">
     <form action="/createRouteList/saveRouteList/assignOrder" method="post">
         <div class="d-flex justify-content-start">
             <button type="submit" class="btn btn-dark">
                 Assign the Order
             </button>
         </div>
     </form>
    </c:if>

</main>
<jsp:include page="templates/footer.jsp"/>


</body>
</html>
