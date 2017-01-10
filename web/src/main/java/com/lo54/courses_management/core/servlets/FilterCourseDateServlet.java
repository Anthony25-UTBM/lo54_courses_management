package main.java.com.lo54.courses_management.core.servlets;


import com.lo54.courses_management.core.entity.CourseSession;
import com.lo54.courses_management.core.service.CourseSessionService;
import main.java.com.lo54.courses_management.core.servlets.util.Param;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month - 1);
            calendar.set(Calendar.DAY_OF_MONTH, day);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);

            Timestamp startDate = new Timestamp(calendar.getTimeInMillis());
            calendar.set(Calendar.DAY_OF_MONTH, day + 1);
            Timestamp endDate = new Timestamp(calendar.getTimeInMillis());

            listCoursesSession = courseSessionService.getEntitiesByTimeStamp(startDate, endDate);

            request.setAttribute(Param.ATTRIBUTE_LIST_COURSES_SESSION, listCoursesSession);
        }

        if(listCoursesSession.size() < 1){
            listCoursesSession = courseSessionService.getEntities();
        }

        request.setAttribute(Param.ATTRIBUTE_LIST_COURSES_SESSION, listCoursesSession);
        request.getRequestDispatcher(Param.PATH_LIST_COURSES).forward(request, response);
      
    }
}
