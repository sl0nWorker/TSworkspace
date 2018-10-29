<!DOCTYPE html>
<html lang="en">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

    <h1 class="mt-5">Truckers</h1>
    <!-- Modal for add new trucker -->
    <jsp:include page="templates/modalAddTrucker.jsp"/>
    <!-- Modal for edit trucker -->
    <jsp:include page="templates/modalEditTrucker.jsp"/>

    <form action="/truckersDelete" method="post">
        <table class="table table-bordered">
            <thead>
            <tr>
                <th colspan="9" scope="col" bgcolor="#faebd7">
                    <!-- Button trigger modal -->
                    <div class="d-flex justify-content-end  ">
                        <button type="button" class="btn btn-dark" data-toggle="modal" data-target="#addTruckerModal">
                            Add new trucker
                        </button>
                    </div>
                </th>
            </tr>
            <tr>
                <th scope="col">#</th>
                <th scope="col">First name</th>
                <th scope="col">Last name</th>
                <th scope="col">Personal number</th>
                <th scope="col">Work hours</th>
                <th scope="col">Status</th>
                <th scope="col">City</th>
                <th scope="col">Truck</th>
                <th scope="col">Edit</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${truckersList}" var="trucker">
                <tr>
                    <td>
                        <input class="custom-checkbox" type="checkbox" id="${trucker.id}"
                               onchange='showOrHide("${trucker.id}", "btnDel");' name="id" value="${trucker.id}"/>
                    </td>

                    <td>
                        <c:out value="${trucker.firstName}"/>
                    </td>

                    <td>
                        <c:out value="${trucker.lastName}"/>
                    </td>

                    <td>
                        <c:out value="${trucker.personalNumber}"/>
                    </td>

                    <td>
                        <c:out value="${trucker.workHours}"/>
                    </td>


                    <td>
                        <c:out value="${trucker.status}"/>
                    </td>


                    <td>
                        <c:out value="${trucker.city}"/>
                    </td>

                    <td>
                        <c:if test="${empty trucker.truck}">
                            Not assigned
                        </c:if>
                        <c:if test="${not empty trucker.truck}">
                            ${trucker.truck}
                        </c:if>
                    </td>


                    <td>
                        <button type="button" class="btn btn-dark" data-trucker-id="${trucker.id}" data-toggle="modal"
                                data-target="#editTruckerModal">
                            Edit trucker
                        </button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <button id="btnDel" style='display: none;' class="btn btn-warning btn-block" type="submit">Delete selected
        </button>
    </form>
</main>
<jsp:include page="templates/footer.jsp"/>

<script>
    $('#editTruckerModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget) // Button that triggered the modal
        var truckerId = button.data('trucker-id') // Extract info from data-* attributes
        // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
        // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
        var modal = $(this)
        modal.find('.modal-body #inputTruckerId').val(truckerId)
    })
</script>

</body>
</html>
