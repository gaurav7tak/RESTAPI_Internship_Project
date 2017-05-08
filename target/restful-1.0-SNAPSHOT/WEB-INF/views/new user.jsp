<%--
  Created by IntelliJ IDEA.
  User: GRV
  Date: 6/7/2016
  Time: 7:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="signup" method="POST" >
    <div class="form-group">
        <label for="LoginId">LoginId:</label>
        <input type="text" name="LoginId" placeholder="LoginId" class="form-control" id="LoginId" required>
    </div>
    <div class="form-group">
        <label for="FirstName">FirstName:</label>
        <input type="text" name="FirstName" placeholder="Firstname" class="form-control" id="FirstName" required>
    </div>
    <div class="form-group">
        <label for="LastName">LastName:</label>
        <input type="text" name="LastName" placeholder="LastName" class="form-control" id="LastName"required>
    </div>

    <div class="form-group">
        <label for="email">Email address:</label>
        <input type="email" name="Email" placeholder="Email address:" class="form-control" id="email" required>
    </div>







    <div class="form-group">
        <label for="inputPassword">Password</label>

        <input type="password" data-minlength="6" name="Password" class="form-control" id="inputPassword" placeholder="Password" required>
        <div class="help-block">Minimum of 6 characters</div>


        <input type="password" class="form-control" id="inputPasswordConfirm" data-match="Password" data-match-error="oops, these don't match" placeholder="Confirm" required>
        <div class="help-block with-errors"></div>
    </div>
    <div class="form-group">
        <label for="Bloodgroup" >BloodGroup:</label>
        <select name="BloodGroup" id="Bloodgroup">
            <option value="A+">A+</option>
            <option value="A-">A-</option>
            <option value="B+">B+</option>
            <option value="B-">B-</option>
            <option value="AB+">AB+</option>
            <option value="AB-">AB-</option>
            <option value="O+">O+</option>
            <option value="O-">O-</option>
        </select>

    </div>
    </div>

    <div class="col-sm-4 col-sm-offset-3 col-md-4 col-md-offset-1 main">
        <div class="form-group">
            <label for="MobileNo">Mobile no:</label>
            <input type="text" name="MobileNo" placeholder="MobileNo" class="form-control" id="MobileNo" required>
        </div>
        <div class="form-group">
            <label for="Donatedto">Donated To</label>
            <input type="text" name="Donatedto" placeholder="Donatedto:" class="form-control" id="Donatedto">
        </div>
        <div class="form-group">
            <label for="requestedfrom">Requested From</label>
            <input type="text" name="Requestedfrom" placeholder="requestedfrom" class="form-control" id="requestedfrom">
        </div>
        <div class="form-group">
            <label for="Role">Role</label>
            <input type="TextField" name="Role" placeholder="RoleId" class="form-control" id="Role" required>
        </div>
        <div class="form-group">
            <label for="Address">Address</label>
            <input type="TextArea" name="Address" placeholder="Address" class="form-control" id="Address">
        </div>
        <button type="submit" class="btn btn-primary  active">Submit</button>
</form>
</body>
</html>
