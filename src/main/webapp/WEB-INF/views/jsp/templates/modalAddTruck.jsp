<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Modal for add new truck -->
<div class="modal fade" id="addTruckModal" tabindex="-1" role="dialog" aria-labelledby="addTruckModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="addTruckModalLabel">
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
                        <label for="selectCity">City</label>
                        <select name="city" class="form-control" id="selectCity">
                            <c:forEach items="${citiesList}" var="city">
                                <option value="${city.id}">
                                    <c:out value="${city.cityName}"/>
                                </option>
                            </c:forEach>
                        </select>
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