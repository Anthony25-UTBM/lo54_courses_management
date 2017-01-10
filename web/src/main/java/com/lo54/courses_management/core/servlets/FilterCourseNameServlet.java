package main.java.com.lo54.courses_management.core.servlets;



import com.lo54.courses_management.core.service.CourseSessionService;
import com.lo54.courses_management.core.service.LocationService;
import main.java.com.lo54.courses_management.core.servlets.util.Param;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
        name = "FilterCourseNameServlet",
        urlPatterns = {"/filterCourseNameServlet"}
)
public class FilterCourseNameServlet extends HttpServlet {

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

        String keyword = request.getParameter("keyword");
        List result;

        CourseSessionService courseSessionService = new CourseSessionService();

            result = courseSessionService.getEntitiesByTitle(keyword);
            if(result == null){
                result = courseSessionService.getEntities();
             }
        request.setAttribute(Param.ATTRIBUTE_LIST_COURSES_SESSION, result);
        request.setAttribute(Param.ATTRIBUTE_FILTER_LOCATION, new LocationService().getEntities());
        request.getRequestDispatcher(Param.PATH_LIST_COURSES).forward(request, response);
      
    }
}
