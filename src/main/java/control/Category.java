package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ProdottoModel;
import model.bean.ProdottoBean;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Servlet implementation class Category
 */
@WebServlet("/Category")
public class Category extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Category() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    		String category = request.getParameter("category");
    		int skip = Integer.parseInt(request.getParameter("skip"));
    		int limit = Integer.parseInt(request.getParameter("limit"));
    		if (!category.equals("all")) {
    			try {
    				request.setAttribute(
    					"prodotti",
    					new ProdottoModel().doRetrieveByCategory(category, skip, limit)
    				);
    				request.setAttribute(
    					"numProdotti",
    					new ProdottoModel().getNumProdottiByCategory(category)
    				);
    				getServletContext()
    					.getRequestDispatcher("/pages/categoria.jsp")
    					.forward(request, response);
    			} catch (SQLException e) {
    				e.printStackTrace();
    			}
    		} else {
    			try {
    				ArrayList<ProdottoBean> prodotti = (ArrayList<ProdottoBean>) new ProdottoModel()
    					.doRetrieveAll(null, skip, limit);
    				request.setAttribute("prodotti", prodotti);
    				request.setAttribute("numProdotti", new ProdottoModel().getNumProdotti());
    				getServletContext()
    					.getRequestDispatcher("/pages/categoria.jsp")
    					.forward(request, response);
    			} catch (SQLException e) {
    				e.printStackTrace();
    			}
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
