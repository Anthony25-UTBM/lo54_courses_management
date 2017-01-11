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

        <form id="clientsCourseSessionForm" role="form" >
            <c:choose>
                <c:when test="${not empty courseClients}">
                    <table  class="table table-striped">
                        <thead>
                        <tr>
                            <td>#</td>
                            <td>Lastname</td>
                            <td>Firstname</td>
                            <td>Phone number</td>
                            <td>Email address</td>
                        </tr>
                        </thead>
                        <c:forEach var="client" items="${courseClients}">
                            <tr>
                                <td>${client.id}</td>
                                <td>${client.lastname}</td>
                                <td>${client.firstname}</td>
                                <td>${client.phone}</td>
                                <td>${client.email}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:when>
                <c:otherwise>
                    <br/>
                    <div class="alert alert-info">
                        No client registered to this course
                    </div>
                </c:otherwise>
            </c:choose>
        </form>
        <form action ="${pageContext.request.contextPath}/homeServlet">
            <br/>
            <button type="submit" class="btn btn-primary  btn-md">Back to courses list</button>
        </form>
    </div>
    </body>
</html>
