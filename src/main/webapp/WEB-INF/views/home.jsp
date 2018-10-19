<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<h2>Hello1 World!</h2>
<h1>
    <c:forEach items="${trucksList}" var="truck">
        <c:out value="${truck.name}"></c:out>
        <c:out value="${truck.goodCondition}"></c:out>
    </c:forEach>
</h1>
</body>
</html>
