package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.bean.OrdineBean;
import model.bean.ProdottoOrdineBean;
import model.bean.UtenteBean;
import model.helper.CartEntry;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.UUID;

import dao.Cart;
import dao.OrdineModel;
import dao.ProdottoModel;
import dao.ProdottoOrdineModel;

/**
 * Servlet implementation class Checkout
 */
@WebServlet("/checkout")
public class Checkout extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		Cart cart = (Cart) request.getSession().getAttribute("cart");

		if (request.getSession().getAttribute("user") != null) {
			if (cart != null) {
				OrdineBean ordine = createOrder(request);
				ArrayList<ProdottoOrdineBean> prodottiOrdine = createProducts(
					cart,
					ordine.getIdOrdine()
				);
				try {
					new OrdineModel().doSave(ordine);
					ProdottoOrdineModel prodModel = new ProdottoOrdineModel();
					for (ProdottoOrdineBean p : prodottiOrdine) {
						prodModel.doSave(p);
						new ProdottoModel().decreaseQuantity(p.getProdotto(), p.getQuantita());
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				cart.clear();
			}
			response.sendRedirect(
				getServletContext().getContextPath() + "/pages/post-checkout.jsp"
			);
		} else {
			response.sendRedirect(getServletContext().getContextPath() + "/pages/login.jsp");
		}
	}

	private OrdineBean createOrder(HttpServletRequest request) {
		OrdineBean ordine = new OrdineBean();
		ordine.setData(new Date());
		ordine.setStato("preso in carico");
		ordine.setIdOrdine(UUID.randomUUID());
		ordine.setIdUtente(((UtenteBean) request.getSession().getAttribute("user")).getIdUtente());
		return ordine;
	}

	private ArrayList<ProdottoOrdineBean> createProducts(Cart carrello, UUID idOrdine) {
		ArrayList<ProdottoOrdineBean> result = new ArrayList<>();
		Iterator<CartEntry> iterator = carrello.getProdotti();
		while (iterator.hasNext()) {
			CartEntry entry = iterator.next();
			ProdottoOrdineBean bean = new ProdottoOrdineBean();
			bean.setProdotto(entry.getProdotto());
			bean.setPrezzoEffettivo(entry.getProdotto().getPrezzo());
			bean.setQuantita(entry.getQuantita());
			bean.setIdOrdine(idOrdine);
			result.add(bean);
		}
		return result;
	}
}
