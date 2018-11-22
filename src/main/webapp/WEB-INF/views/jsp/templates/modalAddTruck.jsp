<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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
                <form:form action="/trucksAdd" method="post" modelAttribute="truckForm">
                    <div class="form-group">
                        <label for="inputRegNumber">Registration number</label>
                        <form:input path="regNumber" placeholder="2 letters, 5 digits: Ab12345" pattern="[A-Za-z]{2}[0-9]{5}" required="required" class="form-control" id="inputRegNumber"/>
                    </div>

                    <div class="form-group">
                        <label for="inputWorkShift">Work shift</label>
                        <form:input type="text" path="workShift" pattern="[4-8]{1}" placeholder="1 digit (4-8): 6" required="required"  class="form-control" id="inputWorkShift"/>
                    </div>

                    <div class="form-group">
                        <label for="inputLoadWeight">Load weight</label>
                        <form:input type="text" path="loadWeight" pattern="1[5-9]|2[0-5]" placeholder="2 digits (15-25): 22" required="required"  class="form-control" id="inputLoadWeight"/>
                    </div>

                    <div class="form-group">
                        <label for="selectWorking">Working</label>
                        <form:select path = "working" class="form-control" id="selectWorking">
                            <form:option value ="true">true</form:option>
                            <form:option value ="false">false</form:option>
                        </form:select>
                    </div>

                    <div class="form-group">
                        <label for="selectCity">City</label>
                        <form:select path="cityId" class="form-control" id="selectCity">
                            <c:forEach items="${citiesList}" var="city">
                                <!-- Send cityId to controller (/trucksAdd) for setting exsicting cityName (in Cities) -->
                                <form:option value="${city.id}">
                                    <c:out value="${city.cityName}"/>
                                </form:option>
                            </c:forEach>
                        </form:select>
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary">Save changes</button>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>