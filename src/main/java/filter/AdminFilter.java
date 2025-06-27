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
import model.bean.UtenteBean;

import java.io.IOException;

/**
 * Servlet implementation class AdminFilter
 */
@WebFilter(urlPatterns = { "/pages/admin/*", "/admin/*" })
public class AdminFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
		throws IOException, ServletException {
		HttpServletRequest filteredRequest = (HttpServletRequest) request;
		HttpServletResponse filteredResponse = (HttpServletResponse) response;
		UtenteBean user = (UtenteBean) filteredRequest.getSession().getAttribute("user");
		if (user == null) {
			//filteredRequest.getSession().setAttribute("redirect", "/pages/");
			filteredResponse.sendRedirect(filteredRequest.getContextPath() + "/pages/login.jsp");
			return;
		} else if (user.getRole() != 1) {
			filteredResponse.setStatus(403);
			filteredResponse.getWriter().println("Non hai i permessi");
			return;
		}
		chain.doFilter(request, response);
	}
}
