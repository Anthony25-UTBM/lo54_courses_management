package main.java.com.lo54.courses_management.core.servlets;

import com.lo54.courses_management.core.entity.Course;
import com.lo54.courses_management.core.entity.CourseSession;
import com.lo54.courses_management.core.entity.Location;
import com.lo54.courses_management.core.service.CourseService;
import com.lo54.courses_management.core.service.CourseSessionService;
import com.lo54.courses_management.core.service.LocationService;
import main.java.com.lo54.courses_management.core.servlets.util.Param;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@WebServlet(
        name = "CourseSessionListServlet",
        urlPatterns = {"/courseList"}
)
public class CourseSessionListServlet extends HttpServlet{

    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType(Param.CONTENT_TYPE);

        String title = request.getParameter(Param.COURSE_TITLE);
        String city = request.getParameter(Param.COURSE_SESSION_LOCATION);
        String startDate = request.getParameter(Param.COURSE_SESSION_START_DATE);
        String endDate = request.getParameter(Param.COURSE_SESSION_END_DATE);

        CourseSession courseSession = new CourseSession();

        Course course = new Course();
        course.setTitle(title);
        CourseService courseService = new CourseService();
        courseService.storeEntity(course);

        courseSession.setCourse(course);

        Location location = new Location();
        location.setCity(city);
        LocationService locationService = new LocationService();
        locationService.storeEntity(location);

        List<Location> listLoc = new LocationService().getEntities();
        request.setAttribute(Param.ATTRIBUTE_FILTER_LOCATION, listLoc);

        courseSession.setLocation(location);

        if(startDate.split("-").length == 3 && endDate.split("-").length == 3) {
            DateFormat format = new SimpleDateFormat("dd-MM-yyyy", Locale.FRENCH);
            try {
                Date startDateForm = format.parse(startDate);
                Date endDateForm = format.parse(endDate);

                courseSession.setStartDate(startDateForm);
                courseSession.setEndDate(endDateForm);

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        CourseSessionService courseSessionService = new CourseSessionService();
        courseSessionService.storeEntity(courseSession);

        List listCourse = (List<CourseSession>)courseSessionService.getEntities();

        request.setAttribute(Param.ATTRIBUTE_LIST_COURSES_SESSION, listCourse);
        request.getRequestDispatcher(Param.PATH_LIST_COURSES).forward(request, response);

    }
}
