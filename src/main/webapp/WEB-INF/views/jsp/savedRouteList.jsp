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

    <form action="/" method="post">

        <table class="table table-bordered">


            <thead>
            <tr>
                <th colspan="7" scope="col" bgcolor="#faebd7">
                    <!-- Button trigger modal -->
                    <div class="d-flex justify-content-end  ">
                        <button type="button" class="btn btn-dark" ">
                            Choose coincident truck
                        </button>
                    </div>
                </th>
            </tr>
            <c:if test = "${savedRouteList!= null && savedRouteList.size() != 0}">
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
            <c:forEach items="${savedRouteList}" var="route">
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
            </tbody>
            </c:forEach>
        </table>
    </form>
</main>
<jsp:include page="templates/footer.jsp"/>


</body>
</html>
