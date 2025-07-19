package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.bean.OrdineBean;
import model.bean.UtenteBean;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.OrdineModel;
import dao.UtenteModel;

/**
 * Servlet implementation class ListaOrdiniEmailAdmin
 */
@WebServlet("/admin/listaordiniemail")
public class ListaOrdiniEmailAdmin extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		try {
			int skip = Integer.parseInt(request.getParameter("skip"));
			int limit = Integer.parseInt(request.getParameter("limit"));
			if (limit == 0) limit = 10;
			String emailCliente = request.getParameter("email");
			UtenteBean utente = new UtenteModel().doRetrieveByEmail(emailCliente);
			ArrayList<OrdineBean> ordini = new ArrayList<>();
			if (utente != null) {
				ordini = new OrdineModel().doRetriveByUser(utente, "_data DESC", skip, limit);
				request.setAttribute("cliente", utente);
			}
			request.setAttribute("ordini", ordini);
			request.setAttribute("skip", skip);
			request.setAttribute("limit", limit);
			getServletContext()
				.getRequestDispatcher("/pages/admin/listaperutente.jsp")
				.forward(request, response);
			return;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
