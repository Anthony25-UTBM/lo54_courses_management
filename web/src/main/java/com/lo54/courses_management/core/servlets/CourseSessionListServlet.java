package main.java.com.lo54.courses_management.core.servlets;

import com.lo54.courses_management.core.entity.Course;
import com.lo54.courses_management.core.entity.CourseSession;
import com.lo54.courses_management.core.entity.Location;
import com.lo54.courses_management.core.service.CourseService;
import com.lo54.courses_management.core.service.CourseSessionService;
import com.lo54.courses_management.core.service.LocationService;
import main.java.com.lo54.courses_management.core.servlets.util.Param;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

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
        //int code = Integer.parseInt(request.getParameter(Param.COURSE_CODE));
        String city = request.getParameter(Param.COURSE_SESSION_LOCATION);
        String startDate = request.getParameter(Param.COURSE_SESSION_START_DATE);
        String endDate = request.getParameter(Param.COURSE_SESSION_END_DATE);

        CourseSession courseSession = new CourseSession();

        Course course = new Course();
        course.setTitle(title);
        //course.setId(code);
        CourseService courseService = new CourseService();
        courseService.storeEntity(course);

        courseSession.setCourse(course);

        Location location = new Location();
        location.setCity(city);
        LocationService locationService = new LocationService();
        locationService.storeEntity(location);

        courseSession.setLocation(location);
        //courseSession.setStartDate(new Date(Long.parseLong(startDate)));
        //courseSession.setEndDate(new Date(Long.parseLong(endDate)));

        CourseSessionService courseSessionService = new CourseSessionService();
        courseSessionService.storeEntity(courseSession);

        List listCourse = (List<CourseSession>)courseSessionService.getEntities();

        request.setAttribute(Param.ATTRIBUTE_LIST_COURSES_SESSION, listCourse);
        request.getRequestDispatcher(Param.PATH_LIST_COURSES).forward(request, response);

    }
}
