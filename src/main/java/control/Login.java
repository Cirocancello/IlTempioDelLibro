package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.UtenteModel;
import model.bean.UtenteBean;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			if (email == null || password == null) {
				response.sendError(400);
				return;
			}
			try {
				UtenteBean utente = new UtenteModel().doRetrieveByEmailAndPassword(email, password);
				if (utente == null) {
					response.sendError(403);
					return;
				}
				request.getSession(true).setAttribute("user", utente);
				request.getSession().setAttribute("redirect", null);
				response.sendRedirect(getServletContext().getContextPath() + "");
				return;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

}
