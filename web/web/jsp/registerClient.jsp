<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="../css/bootstrap.min.css"/>
        <script src="../js/bootstrap.min.js"></script>
        <title>Register client</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
    <div class="container">
    <form action="${pageContext.request.contextPath}/registerClient" method="post"  role="form" data-toggle="validator" >
        <h2>Register to a course</h2>
        <br/>
        <br/>
            <div class="form-group col-xs-4">
                <label for="lastName">Last name :</label>
                <input type="text" class="form-control" id="lastName" required="true">
            </div>
            <div class="form-group col-xs-4">
                <label for="firstName">First name :</label>
                <input type="text" class="form-control" id="firstName" required="true">
            </div>
            <div class="form-group col-xs-4">
                <label for="phone">Phone number :</label>
                <input type="text" class="form-control" id="phone" required="true">
            </div>
            <div class="form-group col-xs-4">
                <label for="email">Email :</label>
                <input type="text" class="form-control" id="email" required="true">
            </div>
            <br/>
            <br/>
        <div class="form-group col-xs-1">
            <button type="submit" class="btn btn-info">
                    <span class="glyphicon glyphicon-check"></span> Confirm
            </button>
        </div>
        </form>
    </div>
    </body>
</html>
