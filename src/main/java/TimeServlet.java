import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet("/time")
public class TimeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String timezone = req.getParameter("timezone");
        ZoneId zoneId;
        if (timezone == null || timezone.isEmpty()) {
            zoneId = ZoneId.of("UTC");
        } else {
            zoneId = ZoneId.of(timezone);
        }

        ZonedDateTime zonedTime = ZonedDateTime.now(zoneId);
        String formattedTime = zonedTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z"));

        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write("<html><body><h1>Current time: " + formattedTime + "</h1></body></html>");
    }
}


