package test;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/test")
public class MapController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String fran = "미스터피자";
		String param = req.getParameter("where");
		String where = fran + " " + param;
		
		req.setAttribute("where", where);
		req.getRequestDispatcher("/test2.jsp").forward(req, resp);
		
	}
	
}
