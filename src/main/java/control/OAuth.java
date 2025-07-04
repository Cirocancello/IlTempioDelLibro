package control;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import auth.GoogleOAuthUser;
import util.GoogleOAuth;
import util.SecretsReader;

@WebServlet("/oauth")
public class OAuth extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		super.init();
		SecretsReader.initInstance(getServletContext());
	}

	/**
	 * TODO Probabilmente qui dobbiamo prendere oltre code come parametro anche se si vuole fare il login o la registrazione dell'utente
	 * */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		if (request.getParameter("code") == null) {
			response.sendError(400);
			return;
		}
		GoogleOAuthUser googleUser = GoogleOAuth.getGoogleUser(request.getParameter("code"));
		response.getWriter().append("User: ").append(googleUser.toString());
	}
}
