<!-- Modal for edit truck -->
<div class="modal fade" id="editTruckModal" tabindex="-1" role="dialog" aria-labelledby="editTruckModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="editTruckModalLabel">
                    Fill out the form to edit the truck
                </h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form action="/trucksEdit" method="post">

                    <div class="form-group" style="display: none;">
                        <input type="text" name="idTruck" id="inputIdTruck">
                    </div>

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