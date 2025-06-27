package filter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Servlet implementation class LoggingFilter
 */
@WebFilter("/*")
public class LoggingFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
		throws IOException, ServletException {
		final String ip = request.getRemoteAddr();
		final String protocol = request.getProtocol();
		String method = ((HttpServletRequest) request).getMethod();
		String path = ((HttpServletRequest) request).getRequestURL().toString();
		Logger
			.getLogger("teraware")
			.log(Level.INFO, protocol + " " + method + " " + path + " FROM " + ip + "");
		chain.doFilter(request, response);
	}
}
