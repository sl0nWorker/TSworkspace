<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Modal for add new truck -->
<div class="modal fade" id="addTruckerModal" tabindex="-1" role="dialog" aria-labelledby="addTruckerModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="addTruckerModalLabel">
                    Fill out the form to create a trucker
                </h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form action="/truckersAdd" method="post">
                    <div class="form-group">
                        <label for="inputFirstName">First name</label>
                        <input type="text" name="firstName" pattern="[A-Za-z]{1,25}"
                               placeholder="max length 25 (only eng letters): Alexander" required class="form-control"
                               id="inputFirstName">
                    </div>

                    <div class="form-group">
                        <label for="inputLastName">Last name</label>
                        <input type="text" name="lastName" pattern="[A-Za-z]{1,35}"
                               placeholder="max length 35 (only eng letters): Ivanov" required class="form-control"
                               id="inputLastName">
                    </div>

                    <div class="form-group">
                        <label for="inputPersonalNubmer">Personal number</label>
                        <%-- TODO: fix pattern to {15}, (1-15) for testing --%>
                        <input type="text" name="personalNumber" pattern="[0-9]{1,15}"
                               placeholder="15 digits: 112233445567890" required class="form-control"
                               id="inputPersonalNubmer">
                    </div>

                    <div class="form-group">
                        <label for="inputWorkHours">Work hours</label>
                        <input type="text" name="workHours" pattern="0|[1-9]|[1-9][0-9]|1[0-6][0-9]|17[0-6]"
                               placeholder=" interval [0 - 176]: 55" required class="form-control" id="inputWorkHours">
                    </div>

                    <div class="form-group">
                        <label for="selectCity">City</label>
                        <select name="city" class="form-control" id="selectCity">
                            <c:forEach items="${citiesList}" var="city">
                                <!-- Send cityId to controller (/truckersAdd) for setting exsicting cityName (in Cities) -->
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