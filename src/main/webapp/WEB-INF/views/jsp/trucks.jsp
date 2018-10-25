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
        <c:forEach items="${trucksList}" var="truck">
            <input type="checkbox" name="id" value="${truck.id}"/>
            <c:out value="${truck.regNumber}"></c:out>
            <c:out value="${truck.working}"></c:out>
        </c:forEach>
        <div>
            <button class="btn btn-primary btn-lg btn-block" type="submit">delete</button>
        </div>
    </form>
</main>
<jsp:include page="templates/footer.jsp"/>
</body>
</html>
