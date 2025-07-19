package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.bean.OrdineBean;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import dao.OrdineModel;

/**
 * Servlet implementation class ListaOrdiniDataAdmin
 */
@WebServlet("/admin/listaordinidata")
public class ListaOrdiniDataAdmin extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		try {
			int skip = Integer.parseInt(request.getParameter("skip"));
			int limit = Integer.parseInt(request.getParameter("limit"));
			if (limit == 0) limit = 10;
			Date dateStart = new SimpleDateFormat("yyyy-MM-dd")
				.parse(request.getParameter("dateStart"));
			Date dateEnd = new SimpleDateFormat("yyyy-MM-dd")
				.parse(request.getParameter("dateEnd"));
			ArrayList<OrdineBean> ordini = new OrdineModel()
				.doRetriveByDate(dateStart, dateEnd, skip, limit);
			request.setAttribute("ordini", ordini);
			request.setAttribute("skip", skip);
			request.setAttribute("limit", limit);
			getServletContext()
				.getRequestDispatcher("/pages/admin/listaperdata.jsp")
				.forward(request, response);
			return;
		} catch (ParseException e) {
			response.getWriter().println("Errore nel convertire la data");
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}