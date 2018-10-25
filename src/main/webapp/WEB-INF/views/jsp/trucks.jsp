<!DOCTYPE html>
<html lang="en">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="templates/header.jsp"/>
<body>
<header>
    <jsp:include page="templates/nav.jsp"/>
</header>


<main role="main" class="container">
    <h1 class="mt-5">Trucks</h1>
    <form action="/trucks" method="post">

            <div class="div-list-group">
            <ul class="list-group">
                <c:forEach items="${trucksList}" var="truck">
                    <li class="list-group-item list-group-item-secondary">
                        <input class="custom-checkbox" type="checkbox" name="id" value="${truck.id}"/>
                        <c:out value="${truck.regNumber}"/>
                        <c:out value="${truck.working}"/>
                    </li>
                </c:forEach>
                 <button class="btn btn-primary btn-lg btn-block" type="submit">delete</button>
            </ul>
            </div>
    </form>
</main>
<jsp:include page="templates/footer.jsp"/>
</body>
</html>
