package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

@WebFilter(urlPatterns = { "/*" })
public class FilterForLogger implements Filter {

    Logger logger = Logger.getLogger("logger");

    public void init(FilterConfig fConfig) throws ServletException {
    }
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        final PrintWriter writer = new PrintWriter(response.getWriter());
        chain.doFilter(request, new HttpServletResponseWrapper((HttpServletResponse) response) {
            @Override public PrintWriter getWriter() {
                return writer;
            }
        });
//        logger.log(writer.getCopy());
    }
    public void destroy() {
//        code = null;
    }
}
