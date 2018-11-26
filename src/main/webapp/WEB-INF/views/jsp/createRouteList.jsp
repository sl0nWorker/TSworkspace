<!DOCTYPE html>
<html lang="en">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<head>
    <jsp:include page="templates/header.jsp"/>
    <!-- Show delete button, if one at least checkbox is pressed -->
    <script type="text/javascript">
        var pressedCheckboxes = 0;

        function showOrHide(checkBoxId, btnDel) {
            checkBoxId = document.getElementById(checkBoxId);
            btnDel = document.getElementById(btnDel);
            if (checkBoxId.checked) {
                if (pressedCheckboxes == 0) {
                    btnDel.style.display = "block";
                }
                pressedCheckboxes++;
            }
            else {
                pressedCheckboxes--;
                if (pressedCheckboxes == 0) {
                    btnDel.style.display = "none";
                }
            }
        }
    </script>
</head>

<body>
<jsp:include page="templates/nav.jsp"/>

<main role="main" class="container">

    <h1 class="mt-5">Creating route list</h1>
    <form action="/createRouteList/deleteRoute" method="post" id="formDeleteRoute">
        <table class="table table-bordered">
            <thead>
            <c:if test="${routeList!= null && routeList.size() != 0}">
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">City</th>
                    <th scope="col">Freight</th>
                    <th scope="col">Loading/Unloading</th>
                </tr>
            </c:if>
            </thead>
            <tbody>
            <c:forEach items="${routeList}" var="route" varStatus="routeListLoop">
                <tr>
                    <td>
                        <input class="custom-checkbox" type="checkbox" id="${routeListLoop.count}"
                               onchange='showOrHide("${routeListLoop.count}", "btnDel");' name="id"
                               value="${routeListLoop.count}"/>
                    </td>

                    <td>
                        <c:out value="${route.city}"/>
                    </td>


                    <td>
                        <c:out value="${route.freight}"/>
                    </td>

                    <td>
                        <c:if test="${route.unloading == true}">
                            Unloading
                        </c:if>
                        <c:if test="${route.unloading == false}">
                            Loading
                        </c:if>

                    </td>

                </tr>
            </c:forEach>
            </tbody>
        </table>
        <button id="btnDel" form="formDeleteRoute" style='display: none;' class="btn btn-warning btn-block"
                type="submit">Delete selected
        </button>
    </form>

    <form:form action="/createRouteList" method="post" modelAttribute="routeForm">
        <table class="table table-bordered">
            <thead>
            <tr>
                <th scope="col">City</th>
                <th scope="col">Freight</th>
                <th scope="col">Loading/Unloading</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>
                    <div class="form-group">
                        <label for="selectCity">City</label>
                        <form:select path="cityId" class="form-control" id="selectCity">
                            <c:forEach items="${citiesList}" var="city">
                                <!-- Send cityId to controller (/createRouteList) -->
                                <form:option value="${city.id}">
                                    <c:out value="${city.cityName}"/>
                                </form:option>
                            </c:forEach>
                        </form:select>
                    </div>
                </td>
                <td>
                    <%-- Freight number --%>
                    <div class="form-group">
                        <label for="inputFreightNumber">Freight number</label>
                        <%-- TODO: add regexp for number --%>
                        <form:input type="text" path="freightNumber" required="required" pattern="[0-9]{1,9}"
                               placeholder="max length 9 (only digits): 9102" class="form-control"
                               id="inputFreightNumber"/>
                    </div>
                    <%-- TODO: use selected list, create list of freight names--%>
                    <div class="form-group">
                        <label for="inputFreightName">Freight name</label>
                        <form:input type="text" path="freightName" required="required" pattern="[A-Za-z]{1,25}"
                               placeholder="max length 25 (only eng letters): Apple" class="form-control"
                               id="inputFreightName"/>
                    </div>

                    <div class="form-group">
                        <label for="inputFreightWeight">Weight</label>
                        <%-- TODO: remove wrong regexp --%>
                        <form:input type="text" path="weight" required="required" pattern="[0-9]|[1-9]{1}[0-9]{1,4}"
                               placeholder="max 5 digits: 25000"
                               class="form-control" id="inputFreightWeight"/>
                    </div>
                    <%-- Freight status always is prepared at creation stage --%>
                </td>


                <td>
                    <div class="form-group">
                        <label for="selectLoading">Loading</label>
                        <form:select path="loading" class="form-control" id="selectLoading">
                            <option>Loading</option>
                            <option>Unloading</option>
                        </form:select>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-dark">
                            Add route
                        </button>
                    </div>
                </td>

            </tr>
            </tbody>
        </table>
    </form:form>

    <form action="/createRouteList/saveRouteList" method="post" id="formAddRouteList">
        <c:if test="${routeList != null && routeList.size() != 0}">
            <div class="form-group">
                <button type="submit" class="btn btn-dark btn-block">
                    Save route list
                </button>
            </div>
        </c:if>
    </form>


</main>
<jsp:include page="templates/footer.jsp"/>


</body>
</html>
