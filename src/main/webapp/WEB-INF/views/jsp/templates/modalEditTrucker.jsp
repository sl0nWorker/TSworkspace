<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- Modal for edit truck -->
<div class="modal fade" id="editTruckerModal" tabindex="-1" role="dialog" aria-labelledby="editTruckerModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="editTruckerModalLabel">
                    Fill out the form to edit the trucker
                </h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form:form action="/truckersEdit" method="post" modelAttribute="truckerEditForm">

                    <div class="form-group">
                        <label for="inputFirstName">First name</label>
                        <form:input type="text" path="firstName" pattern="[A-Za-z]{1,25}"
                               placeholder="max length 25 (only eng letters): Alexander" class="form-control"
                               id="inputFirstName"/>
                    </div>

                    <div class="form-group">
                        <label for="inputLastName">Last name</label>
                        <form:input type="text" path="lastName" pattern="[A-Za-z]{1,35}"
                               placeholder="max length 35 (only eng letters): Ivanov" class="form-control"
                               id="inputLastName"/>
                    </div>

                    <div class="form-group">
                        <label for="inputPersonalNubmer">Personal number</label>
                        <form:input type="text" path="personalNumber" pattern="[0-9]{1,9}"
                               placeholder="1-9 digits: 1234" class="form-control"
                               id="inputPersonalNubmer"/>
                    </div>

                    <div class="form-group">
                        <label for="inputWorkHours">Work hours</label>
                        <form:input type="text" path="workHours" pattern="0|[1-9]|[1-9][0-9]|1[0-6][0-9]|17[0-6]"
                               placeholder=" interval [0 - 176]: 55" class="form-control" id="inputWorkHours"/>
                    </div>

                    <div class="form-group">
                        <label for="selectCity">City</label>
                        <form:select path="cityId" class="form-control" id="selectCity">
                            <form:option value="" selected="selected">Choose city</form:option>
                            <c:forEach items="${citiesList}" var="city">
                                <!-- Send cityId to controller (/truckersAdd) for setting exsicting cityName (in Cities) -->
                                <form:option value="${city.id}">
                                    <c:out value="${city.cityName}"/>
                                </form:option>
                            </c:forEach>
                        </form:select>
                    </div>

                    <div class="form-group" style="display: none;">
                        <form:input type="text" path="truckerId" id="inputTruckerId"/>
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