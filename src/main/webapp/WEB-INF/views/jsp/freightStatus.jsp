<!DOCTYPE html>
<html lang="en">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <jsp:include page="templates/header.jsp"/>
</head>

<body>
<jsp:include page="templates/nav.jsp"/>

<main role="main" class="container">

    <h1 class="mt-5">Freight status</h1>
    <div class="list-group">
        <ul class="list-group">
            <c:forEach items="${freightList}" var="freight">
             <li class="list-group-item"><c:out value="${freight}"/></li>
            </c:forEach>
        </ul>
    </div>
</main>
<jsp:include page="templates/footer.jsp"/>
</body>
</html>
