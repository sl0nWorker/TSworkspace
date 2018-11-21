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
                if(pressedCheckboxes == 0){
                    btnDel.style.display = "block";
                }
                pressedCheckboxes++;
            }
            else {
                pressedCheckboxes--;
                if (pressedCheckboxes == 0){
                    btnDel.style.display = "none";
                }
            }
        }
    </script>
</head>

<body>
<jsp:include page="templates/nav.jsp"/>

<main role="main" class="container">

    <h1 class="mt-5">Trucks</h1>



    <!-- Modal for add new truck -->
    <jsp:include page="templates/modalAddTruck.jsp"/>
    <!-- Modal for edit truck -->
    <jsp:include page="templates/modalEditTruck.jsp"/>


    <form action="/trucksDelete" method="post">

        <table class="table table-bordered">


            <thead>
            <tr>
                <th colspan="8" scope="col" bgcolor="#faebd7">
                    <!-- Button trigger modal -->
                    <div class="d-flex justify-content-end  ">
                        <button type="button" class="btn btn-dark" data-toggle="modal" data-target="#addTruckModal">
                            Add new truck
                        </button>
                    </div>
                </th>
            </tr>
            <c:if test = "${trucksList!= null && trucksList.size() != 0}">
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Registration number</th>
                    <th scope="col">Working</th>
                    <th scope="col">Work Shift</th>
                    <th scope="col">Load weight</th>
                    <th scope="col">City</th>
                    <th scope="col">On order</th>
                    <th scope="col">Edit</th>
                </tr>
            </c:if>
            </thead>
            <tbody>
            <c:forEach items="${trucksList}" var="truck">
                <tr>
                    <td>
                        <input class="custom-checkbox" type="checkbox" id="${truck.id}"
                               onchange='showOrHide("${truck.id}", "btnDel");' name="id" value="${truck.id}"/>
                    </td>

                    <td>
                        <c:out value="${truck.regNumber}"/>
                    </td>

                    <td>
                        <c:out value="${truck.working}"/>
                    </td>

                    <td>
                        <c:out value="${truck.workShift}"/>
                    </td>

                    <td>
                        <c:out value="${truck.loadWeight}"/>
                    </td>

                    <td>
                        <c:out value="${truck.city}"/>
                    </td>

                    <td>
                        <c:if test = "${truck.order == null}">
                            false
                        </c:if>
                        <c:if test = "${truck.order != null}">
                            true
                        </c:if>
                    </td>

                    <td>
                        <%-- Send data('truck-id') = truck.id to modal through <script> #editTruckModal --%>
                        <button type="button" class="btn btn-dark" data-truck-id="${truck.id}" data-toggle="modal" data-target="#editTruckModal">
                            Edit truck
                        </button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <button id="btnDel" style='display: none;' class="btn btn-warning btn-block" type="submit">Delete selected</button>
    </form>
</main>
<jsp:include page="templates/footer.jsp"/>

<script>
    $('#editTruckModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget) // Button that triggered the modal
        var truckId = button.data('truck-id') // Extract info from data-* attributes
        // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
        // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
        var modal = $(this)
        modal.find('.modal-body #inputIdTruck').val(truckId)
    })
</script>

</body>
</html>
