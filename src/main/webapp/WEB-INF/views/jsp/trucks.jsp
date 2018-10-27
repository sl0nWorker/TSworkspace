<!DOCTYPE html>
<html lang="en">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <jsp:include page="templates/header.jsp"/>
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



    <!-- Modal -->
    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">
                        Fill out the form to create a truck
                    </h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form action="/trucksAdd" method="post">
                        <div class="form-group">
                            <label for="inputRegNumber">Registration number</label>
                            <input type="text" name="regNumber" pattern="[A-Za-z]{2}[0-9]{5}" placeholder="2 letters, 5 digits: Ab12345" required class="form-control" id="inputRegNumber">
                        </div>

                        <div class="form-group">
                            <label for="inputWorkShift">Work shift</label>
                            <input type="text" name="workShift" pattern="[1-2]{1}[0-9]{1}" placeholder="2 digits (24 hours format): 15" required  class="form-control" id="inputWorkShift">
                        </div>

                        <div class="form-group">
                            <label for="inputLoadWeight">Load weight</label>
                            <input type="text" name="loadWeight" pattern="1[5-9]|2[0-5]" placeholder="2 digits (15-25): 22" required  class="form-control" id="inputLoadWeight">
                        </div>

                        <div class="form-group">
                            <label for="inputWorking">Working</label>
                            <input type="text" name="working" pattern="[0-1]" placeholder="1 digit (0 or 1): 1" required  class="form-control" id="inputWorking">
                        </div>

                        <div class="form-group">
                            <label for="inputCity">City</label>
                            <input type="text" name="city" maxlength="30" pattern="[A-Za-z]{1,30}" placeholder="City name (max len - 30 chars): Moscow" required  class="form-control" id="inputCity">
                        </div>

                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            <button type="submit" class="btn btn-primary">Save changes</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <form action="/trucksDelete" method="post">

        <table class="table table-bordered">


            <thead>
            <tr>
                <th colspan="7" scope="col" bgcolor="#faebd7">
                    <!-- Button trigger modal -->
                    <div class="d-flex justify-content-end  ">
                        <button type="button" class="btn btn-dark" data-toggle="modal" data-target="#exampleModal">
                            Add new truck
                        </button>
                    </div>
                </th>
            </tr>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Registration number</th>
                <th scope="col">Working</th>
                <th scope="col">Work Shift</th>
                <th scope="col">Load weight</th>
                <th scope="col">City</th>
            </tr>
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
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <button id="btnDel" style='display: none;' class="btn btn-warning btn-block" type="submit">Delete selected</button>
    </form>
</main>
<jsp:include page="templates/footer.jsp"/>
</body>
</html>
