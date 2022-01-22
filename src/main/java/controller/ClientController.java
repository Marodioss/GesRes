package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import securite.Hash;
import service.ClientService;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

import beans.Client;

/**
 * Servlet implementation class ClientController
 */
public class ClientController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 ClientService cs = new ClientService();
	 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    private void loadData(HttpServletResponse response) 
    		throws ServletException, IOException{
    	response.setContentType("application/json");
		Gson json = new Gson();
		List<Client> clients = cs.findAll();
		response.getWriter().write(json.toJson(clients));
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	if(request.getParameter("op") != null) {
    		if(request.getParameter("op").equals("delete")) {
    			Client client = cs.findById(Integer.parseInt(request.getParameter("id")));
    			if(!cs.delete(client))
    				response.sendError(401);
    		}
    	}else {
			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			String login = request.getParameter("login");
			String email = request.getParameter("email");
			String password = Hash.encrypt(request.getParameter("password"));
			if(!cs.create(new Client(nom, prenom, login, email, password)))
				response.sendError(400);
    	}
    	loadData(response);
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

}
