/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lo54.courses_management.core.servlets;

import com.lo54.courses_management.core.entity.Client;
import com.lo54.courses_management.core.entity.CourseSession;
import com.lo54.courses_management.core.service.ClientService;
import com.lo54.courses_management.core.service.CourseSessionService;
import com.lo54.courses_management.core.service.LocationService;
import com.lo54.courses_management.core.servlets.util.Param;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@WebServlet(
        name = "RegisterClientServlet",
        urlPatterns = {"/registerClient"}
)
public class RegisterClientServlet extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType(Param.CONTENT_TYPE);

        String lastName = request.getParameter(Param.CLIENT_LASTNAME);
        String firstName = request.getParameter(Param.CLIENT_FIRSTNAME);
        String phone = request.getParameter(Param.CLIENT_PHONE);
        String email = request.getParameter(Param.CLIENT_MAIL);

        Client client = new Client();
        client.setLastname(lastName);
        client.setFirstname(firstName);
        client.setPhone(phone);
        client.setEmail(email);

        ClientService clientService = new ClientService();
        clientService.storeEntity(client);

        CourseSessionService courseSessionService = new CourseSessionService();

        int idCourseSession = Integer.parseInt(request.getParameter(Param.ATTRIBUTE_COURSE_SESSION_ID));

        CourseSession courseSession = null;
        try {
            courseSession = (CourseSession)courseSessionService.getEntity(idCourseSession);
            courseSession.getClients().add(client);
            courseSessionService.updateEntity(idCourseSession, courseSession);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        try {
            request.setAttribute(Param.ATTRIBUTE_FILTER_LOCATION, new LocationService().getEntities());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            request.setAttribute(Param.ATTRIBUTE_LIST_COURSES_SESSION, courseSessionService.getEntities());
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher(Param.PATH_LIST_COURSES).forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType(Param.CONTENT_TYPE);

        int idCourseSession = Integer.parseInt(request.getParameter(Param.ATTRIBUTE_COURSE_SESSION_ID));

        CourseSessionService courseSessionService = new CourseSessionService();
        CourseSession courseSession = null;
        try {
            courseSession = (CourseSession) courseSessionService.getEntity(idCourseSession);
            Set clientsOfCourse = courseSession.getClients();
            request.setAttribute(Param.ATTRIBUTE_COURSE_CLIENTS, clientsOfCourse);
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher(Param.PATH_LIST_CLIENTS).forward(request, response);
    }

}
