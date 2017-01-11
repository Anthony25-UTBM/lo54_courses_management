package com.lo54.courses_management.core.servlets;


import com.lo54.courses_management.core.entity.CourseSession;
import com.lo54.courses_management.core.service.CourseSessionService;
import com.lo54.courses_management.core.service.LocationService;
import com.lo54.courses_management.core.servlets.util.Param;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(
        name = "FilterCourseLocationServlet",
        urlPatterns = {"/filterCourseLocationServlet"}
)
public class FilterCourseLocationServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
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

        CourseSessionService courseSessionService = new CourseSessionService();
        String location = request.getParameter(Param.FILTER_LOCATION);

        List<CourseSession> listCoursesSession = null;

        listCoursesSession = courseSessionService.getEntitiesByLocation(location);
        if(listCoursesSession.size() < 1){
            try {
                listCoursesSession = courseSessionService.getEntities();
            } catch (Exception e) {
                e.printStackTrace();
                listCoursesSession = new ArrayList<>();
            }
        }

        //resets the list of locations but doesn't keep the last one selected
        try {
            request.setAttribute(Param.ATTRIBUTE_FILTER_LOCATION, new LocationService().getEntities());
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute(Param.ATTRIBUTE_LIST_COURSES_SESSION, listCoursesSession);

        request.getRequestDispatcher(Param.PATH_LIST_COURSES).forward(request, response);
      
    }
}
