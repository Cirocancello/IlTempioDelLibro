package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.bean.ProdottoBean;

import java.io.IOException;
import java.sql.SQLException;

import dao.ProdottoModel;

/**
 * Servlet implementation class Product
 */
@WebServlet("/Product")
public class Product extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Product() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			String productId = request.getParameter("id");
			if (productId != null) {
				try {
					ProdottoBean prodotto = new ProdottoModel()
						.doRetrieveByKey(Integer.parseInt(productId));
					request.setAttribute("prod", prodotto);
					getServletContext()
						.getRequestDispatcher("/pages/prodotto.jsp")
						.forward(request, response);
				} catch (NumberFormatException e) {
					
					response.sendError(400);
				} catch (SQLException e) {
					e.printStackTrace();
					response.sendError(500);
				}
			} else {
				response.sendRedirect(getServletContext().getContextPath() + "/products");
			}
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
