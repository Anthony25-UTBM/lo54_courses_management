/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.lo54.courses_management.core.servlets;

import com.lo54.courses_management.core.entity.Client;
import com.lo54.courses_management.core.entity.CourseSession;
import com.lo54.courses_management.core.service.ClientService;
import com.lo54.courses_management.core.service.CourseSessionService;
import main.java.com.lo54.courses_management.core.servlets.util.Param;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
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

        CourseSession courseSession = new CourseSession();

        //int idCourseSession = Integer.parseInt(request.getParameter(Param.ATTRIBUTE_COURSE_SESSION_ID));
        //courseSession.getClients().add(client);
        //courseSessionService.updateEntity(idCourseSession, courseSession);

        request.getRequestDispatcher(Param.PATH_LIST_COURSES).forward(request, response);
    }

}
