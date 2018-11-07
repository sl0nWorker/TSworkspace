<!DOCTYPE html>
<html lang="en">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <jsp:include page="templates/header.jsp"/>
</head>

<body>
<jsp:include page="templates/nav.jsp"/>

<main role="main" class="container">

    <h1 class="mt-5">Trucker list</h1>

    <form action="/truckerUi/trucker" method="post">
    <div class="list-group">
        <c:forEach items="${truckerList}" var="trucker">
            <button name="truckerId" value="${trucker.id}" type="submit" class="btn btn-secondary">
                Personal number: <c:out value="${trucker.personalNumber}"/>
                Last name: <c:out value="${trucker.lastName}"/>
                First name: <c:out value="${trucker.firstName}"/>
            </button>
        </c:forEach>
    </div>
    </form>
</main>
<jsp:include page="templates/footer.jsp"/>
</body>
</html>
