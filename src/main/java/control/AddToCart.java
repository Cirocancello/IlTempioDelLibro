package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

import dao.Cart;
import dao.ProdottoModel;
import model.bean.ProdottoBean;
import model.helper.CartEntry;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet implementation class AddToCart
 */
@WebServlet("/AddToCart")
public class AddToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			if (request.getParameter("pId") == null || request.getParameter("q") == null) {
				sendErrorResponse(response);
				return;
			}
			try {
				int productId = Integer.parseInt(request.getParameter("pId"));
				int productQuantity = Integer.parseInt(request.getParameter("q"));
				ProdottoBean prod = new ProdottoModel().doRetrieveByKey(productId);
				Cart cart = (Cart) request.getSession(true).getAttribute("cart");
				if (cart == null) {
					cart = new Cart();
				}
				CartEntry c = new CartEntry(
					prod,
					productQuantity <= prod.getQuantita() ? productQuantity : prod.getQuantita()
				);
				cart.set(c);
				request.getSession().setAttribute("cart", cart);
				response.getWriter().print(new Gson().toJson(cart, Cart.class));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		private void sendErrorResponse(HttpServletResponse response) throws IOException {
			response.sendError(400);
		}
}
