<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true" %>
<html>
<head>
    <link rel="stylesheet" href="../css/bootstrap.min.css"/>
    <script src="../js/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="../js/bootstrap.min.js"></script>
    <title>Course sessions</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<div class="container">
    <h2>Course sessions</h2>
    <br/>
    <!--Search Form -->
    <form action="${pageContext.request.contextPath}/filterCourseNameServlet" method="post" id="searchCourseSessionForm" role="form">
        <div>
            <input type="text" name="keyword" id="keyword" class="form-control" placeholder="Type the keyword you want to look for"/>
        </div>
        <button type="submit" class="btn btn-info">
            <span class="glyphicon glyphicon-search"></span> Search title
        </button>
        <br/>
    </form>

    <form action="${pageContext.request.contextPath}/filterCourseLocationServlet" method="post" id="searchCourseByLocationForm" role="form">
        <div>
            <input type="text" name="fLocation" id="location" class="form-control" placeholder="Type the keyword you want to look for"/>
        </div>
        <button type="submit" class="btn btn-info">
            <span class="glyphicon glyphicon-search"></span> Search location
        </button>
        <br/>
    </form>


    <form action="${pageContext.request.contextPath}/filterCourseDateServlet" method="post" id="searchCourseSessionByDateForm" role="form">
        <divr>
            <input type="text" name="date" id="date" class="form-control" placeholder="jj-mm-yyyy"/>
        </divr>
        <button type="submit" class="btn btn-info">
            <span class="glyphicon glyphicon-search"></span> Filter date
        </button>
        <br/>
    </form>


    <!-- List of courses -->
    <form action="${pageContext.request.contextPath}/courseList" method="post" id="courseSessionForm" role="form" >
        <c:choose>
            <c:when test="${not empty listCoursesSession}">
                <table  class="table table-striped">
                    <thead>
                    <tr>
                        <td>#</td>
                        <td>Course title</td>
                        <td>City</td>
                        <td>Start date</td>
                        <td>End date</td>
                        <td></td>
                    </tr>
                    </thead>
                    <c:forEach var="courseSession" items="${listCoursesSession}">
                        <tr>
                            <td>${courseSession.id}</td>
                            <td>${courseSession.course.title}</td>
                            <td>${courseSession.location.city}</td>
                            <td>${courseSession.startDate}</td>
                            <td>${courseSession.endDate}</td>
                            <!--onclick=""-->
                            <td><a href="${pageContext.request.contextPath}/jsp/registerClient.jsp">Register <span class="glyphicon glyphicon-plus"/></a></td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                <br/>
                <div class="alert alert-info">
                    No course session found matching your search criteria
                </div>
            </c:otherwise>
        </c:choose>
    </form>
    <form action ="${pageContext.request.contextPath}/jsp/addCourseForm.jsp">
        <br/>
        <button type="submit" class="btn btn-primary  btn-md">New course session</button>
    </form>
</div>
</body>
</html>