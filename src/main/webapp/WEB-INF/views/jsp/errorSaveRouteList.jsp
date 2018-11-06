<!DOCTYPE html>
<html lang="en">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <jsp:include page="templates/header.jsp"/>
</head>

<body>
<jsp:include page="templates/nav.jsp"/>

<main role="main" class="container">

    <h1 class="mt-5">ERROR PAGE</h1>

    <div class="form-group">
        <c:out value="${error}"/>
    </div>

</main>
<jsp:include page="templates/footer.jsp"/>
</body>
</html>
