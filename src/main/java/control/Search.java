package control;

import com.google.gson.Gson;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ProdottoModel;
import model.bean.ProdottoBean;

@WebServlet("/search")
public class Search extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		String query = request.getParameter("query");
		try {
			ArrayList<ProdottoBean> result = (ArrayList<ProdottoBean>) new ProdottoModel()
				.searchByName(query);
			response.getWriter().print(new Gson().toJson(result, ArrayList.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
