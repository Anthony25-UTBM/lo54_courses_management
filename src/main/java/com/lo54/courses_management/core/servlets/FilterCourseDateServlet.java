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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@WebServlet(
        name = "FilterCourseDateServlet",
        urlPatterns = {"/filterCourseDateServlet"}
)
public class FilterCourseDateServlet extends HttpServlet {

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

        String sDate = request.getParameter(Param.DATE);
        String[] tabDate = sDate.split("-");

        List<CourseSession> listCoursesSession = null;
        CourseSessionService courseSessionService = new CourseSessionService();

        if(tabDate.length == 3) {
            int year = Integer.parseInt(tabDate[2]);
            int month = Integer.parseInt(tabDate[1]);
            int day = Integer.parseInt(tabDate[0]);

            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, day);

            Timestamp startDate = new Timestamp(calendar.getTimeInMillis());
            calendar.set(Calendar.DATE, day + 1);
            Timestamp endDate = new Timestamp(calendar.getTimeInMillis());

            listCoursesSession = courseSessionService.getEntitiesByTimeStamp(startDate, endDate);

            request.setAttribute(Param.ATTRIBUTE_LIST_COURSES_SESSION, listCoursesSession);
        }

        if(listCoursesSession.size() < 1){
            try {
                listCoursesSession = courseSessionService.getEntities();
            } catch (Exception e) {
                listCoursesSession = new ArrayList<>();
                e.printStackTrace();
            }
        }
        try {
            request.setAttribute(Param.ATTRIBUTE_FILTER_LOCATION, new LocationService().getEntities());
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute(Param.ATTRIBUTE_LIST_COURSES_SESSION, listCoursesSession);

        request.getRequestDispatcher(Param.PATH_LIST_COURSES).forward(request, response);
      
    }
}
