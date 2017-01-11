/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lo54.courses_management.core.servlets;

import com.lo54.courses_management.core.entity.CourseSession;
import com.lo54.courses_management.core.entity.Location;
import com.lo54.courses_management.core.service.CourseSessionService;
import com.lo54.courses_management.core.service.LocationService;
import com.lo54.courses_management.core.servlets.util.Param;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(
        name = "HomeServlet",
        urlPatterns = {"/homeServlet"}
)
public class HomeServlet extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType(Param.CONTENT_TYPE);

        CourseSessionService courseSessionService = new CourseSessionService();
        List<CourseSession> allEntities = courseSessionService.getEntities();

        request.setAttribute(Param.ATTRIBUTE_LIST_COURSES_SESSION, allEntities);

        LocationService locationService = new LocationService();
        List<Location> listLocations = locationService.getEntities();

        request.setAttribute(Param.ATTRIBUTE_FILTER_LOCATION, listLocations);

        request.getRequestDispatcher(Param.PATH_LIST_COURSES).forward(request, response);
    }

}
