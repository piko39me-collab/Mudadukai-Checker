package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.MudadukaiManager;


@WebServlet("/MudadukaiServlet")
public class MudadukaiServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	HttpSession session = request.getSession();
	
	MudadukaiManager manager = (MudadukaiManager)session.getAttribute("mudadukaiManager");
	
	if(manager == null) {
		manager = new MudadukaiManager();
		session.setAttribute("mudadukaiManager", manager);
	}
	
	RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/Mudadukai.jsp");
	dispatcher.forward(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		String name = request.getParameter("name");
		String priceStr = request.getParameter("price");
		
		
		MudadukaiManager manager = (MudadukaiManager) session.getAttribute("mudadukaiManager");
		if(manager == null) {
			manager = new MudadukaiManager();
			session.setAttribute("mudadukaiManager", manager);
		}
		
		try {
			int price = Integer.parseInt(priceStr);
			manager.addExpense(name,price);
		}catch (NumberFormatException e){
		}
		response.sendRedirect(request.getContextPath() + "/MudadukaiServlet");
	}

}
