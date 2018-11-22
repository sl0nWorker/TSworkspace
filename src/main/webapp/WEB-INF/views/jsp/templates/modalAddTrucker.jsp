<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
                <form:form action="/truckersAdd" method="post" modelAttribute="truckerForm">
                    <div class="form-group">
                        <label for="inputFirstName">First name</label>
                        <form:input type="text" path="firstName" pattern="[A-Za-z]{1,25}"
                               placeholder="max length 25 (only eng letters): Alexander" required="required" class="form-control"
                               id="inputFirstName"/>
                    </div>

                    <div class="form-group">
                        <label for="inputLastName">Last name</label>
                        <form:input type="text" path="lastName" pattern="[A-Za-z]{1,35}"
                               placeholder="max length 35 (only eng letters): Ivanov" required="required" class="form-control"
                               id="inputLastName"/>
                    </div>

                    <div class="form-group">
                        <label for="inputPersonalNubmer">Personal number</label>
                        <form:input type="text" path="personalNumber" pattern="[0-9]{1,9}"
                               placeholder="1-9 digits: 12345" required="required" class="form-control"
                               id="inputPersonalNubmer"/>
                    </div>

                    <div class="form-group">
                        <label for="selectCity">City</label>
                        <form:select path="cityId" class="form-control" id="selectCity">
                            <c:forEach items="${citiesList}" var="city">
                                <!-- Send cityId to controller (/truckersAdd) for setting exsicting cityName (in Cities) -->
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