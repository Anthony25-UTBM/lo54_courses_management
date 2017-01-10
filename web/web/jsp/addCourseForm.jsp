<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="../css/bootstrap.min.css"/>
        <script src="../js/bootstrap.min.js"></script>
        <title>Add a course</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
    <div class="container">
    <form action="${pageContext.request.contextPath}/courseList" method="post"  role="form" data-toggle="validator" >
        <h2>Add a course session</h2>
        <br/>
        <br/>
            <div class="form-group col-xs-4">
                <label for="title">Title:</label>
                <input type="text" class="form-control" name="title" id="title" required="true">
            </div>
            <div class="form-group col-xs-4">
                <label for="location">City:</label>
                <input type="text" class="form-control" name="location" id="location" required="true">
            </div>
            <div class="form-group col-xs-4">
                <label for="startDate">Start date:</label>
                <input type="text" class="form-control" id="startDate" name="startDate" placeholder="dd-mm-yyyy" required="true">
            </div>
            <div class="form-group col-xs-4">
                <label for="endDate">End date:</label>
                <input type="text" class="form-control" id="endDate" name="endDate" placeholder="dd-mm-yyyy" required="true">
            </div>
            <br/>
            <br/>

        <div class="form-group">
            <button type="submit" class="btn btn-info">
                    <span class="glyphicon glyphicon-check"></span> Confirm
            </button>
        </div>
        </form>
    </div>
    </body>
</html>
