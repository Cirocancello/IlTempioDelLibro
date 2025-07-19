package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.bean.UtenteBean;

import java.io.IOException;
import java.sql.SQLException;

import dao.UtenteModel;

/**
 * Servlet implementation class CheckEmail
 */
@WebServlet("/checkemail")
public class CheckEmail extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		String email = request.getParameter("email");
		try {
			UtenteBean utente = new UtenteModel().doRetrieveByEmail(email);
			if (utente == null) {
				response.getWriter().print("0");
				return;
			}
			response.getWriter().print("1");
			return;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
