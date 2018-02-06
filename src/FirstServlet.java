import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class FirstServlet extends HttpServlet {

    Logger logger = Logger.getLogger("logger");


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<body>");
        out.println("<h1>Hi there</h1>");
        out.println("</body>");
        out.println("</html>");

        writeToLog(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        writeToLog(req, resp);
    }



    private void writeToLog(HttpServletRequest req, HttpServletResponse resp) {
        getRequestHeadersInfo(req).entrySet().stream().forEach(e -> logger.warning("request header from doGet: " + e.toString()));
        resp.getHeaderNames().stream().forEach(e -> logger.warning("request header from doGet: " + e.toString()));

        getRequestBodyInfo(req).entrySet().stream().forEach(e -> logger.warning("request body from doGet: " + e.toString()));
        resp.
    }
    private Map<String, String> getRequestHeadersInfo(HttpServletRequest request) {

        Map<String, String> map = new HashMap<>();

        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }

        return map;
    }

    private Map<String, String> getRequestBodyInfo(HttpServletRequest request) {

        Map<String, String> map = new HashMap<>();

        Enumeration names = request.getParameterNames();
        while (names.hasMoreElements()) {
            String key = (String) names.nextElement();
            String value = request.getParameter(key);
            map.put(key, value);
        }

        return map;
    }

}
