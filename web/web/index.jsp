<%@ page import="com.lo54.courses_management.core.entity.CourseSession" %>
<%@ page import="com.lo54.courses_management.core.service.CourseSessionService" %>
<%@ page import="com.lo54.courses_management.core.entity.Course" %>
<%@ page import="com.lo54.courses_management.core.entity.Location" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.sun.xml.internal.ws.server.sei.SEIInvokerTube" %>
<%@ page import="com.lo54.courses_management.core.service.LocationService" %>
<%@ page import="java.util.List" %>
<%@ page import="main.java.com.lo54.courses_management.core.servlets.util.Param" %><%--
  Created by IntelliJ IDEA.
  User: Thomas
  Date: 07/01/2017
  Time: 09:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    LocationService locationService = new LocationService();
    List<Location> listLocations = locationService.getEntities();
    request.setAttribute(Param.ATTRIBUTE_FILTER_LOCATION, listLocations);

    CourseSessionService courseSessionService = new CourseSessionService();
    List<CourseSession> listCourseSession = courseSessionService.getEntities();
    request.setAttribute(Param.ATTRIBUTE_LIST_COURSES_SESSION, listCourseSession);

%>

<jsp:forward page="/homeServlet"></jsp:forward>

