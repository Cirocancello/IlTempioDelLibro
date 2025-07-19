package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.bean.ProdottoBean;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.ProdottoModel;

/**
 * Servlet implementation class Products
 */
@WebServlet("/Products")
public class Products extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Products() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    		// Ottieni tutti i prodotti e forwarda alla servlet
    		ProdottoModel prodottoModel = new ProdottoModel();
    		ArrayList<ProdottoBean> prodotti = null;
    		try {
    			prodotti = (ArrayList<ProdottoBean>) prodottoModel.doRetrieveAll(null);
    		} catch (SQLException e) {
    			response.getWriter().println("Errore"); //FIXME Redirect pagina di errore
    			e.printStackTrace();
    			return;
    		}
    		request.setAttribute("prodotti", prodotti);
    		getServletContext().getRequestDispatcher("/pages/prodotti.jsp").forward(request, response);
    	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
