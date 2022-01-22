package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.OccupationService;
import service.SalleService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;

import beans.Occupation;
import beans.Salle;

/**
 * Servlet implementation class SalleController
 */
public class SalleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SalleService ss = new SalleService();
	private OccupationService os = new OccupationService();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SalleController() {
        super();
        // TODO Auto-generated constructor stub
    }

    private void loadData(HttpServletResponse response) 
    		throws ServletException, IOException{
    	response.setContentType("application/json");
		Gson json = new Gson();
		List<Salle> salles = ss.findAll();
		response.getWriter().write(json.toJson(salles));
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	if(request.getParameter("op") != null) {
    		if(request.getParameter("op").equals("load")) 
    			loadData(response);
    		else if(request.getParameter("op").equals("delete")) {
				Salle salle = ss.findById(Integer.parseInt(request.getParameter("id")));
				if(ss.delete(salle)) 
					loadData(response);
				else 
					response.sendError(400);	
			}else if(request.getParameter("op").equals("update")) {
    			Salle salle = ss.findById(Integer.parseInt(request.getParameter("id")));
    			response.setContentType("application/json");
                Gson json = new Gson();
                response.getWriter().write(json.toJson(salle)); 
    		}else if(request.getParameter("op").equals("loadDisponibilite")) {
    			List<Salle> salles = os.findSallesDispo(new Date(request.getParameter("date").replace("-", "/")));
    			response.setContentType("application/json");		
    			Gson json = new Gson();
    			response.getWriter().write(json.toJson(salles));
    		} 	
    	}else {
    		if(!(request.getParameter("code").equals("") || request.getParameter("capacite").equals("") || request.getParameter("type").equals(""))) {
    			int id = Integer.parseInt(request.getParameter("id"));
	    		Salle salle = ss.findById(id);
	    		String code = request.getParameter("code");
				int capacite = Integer.parseInt(request.getParameter("capacite"));
				String type = request.getParameter("type");
	    		if(salle == null) {
	    			if(ss.create(new Salle(code, capacite, type)))
	    				loadData(response);
	    			else
	    				response.sendError(402);
	    		}
	    		else {
	    			salle.setCode(code);
	    			salle.setType(type);
	    			salle.setCapacite(capacite);
	    			ss.update(salle);
	    			loadData(response);
	    		}	
    		}else
    			response.sendError(401);
    	}
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

}
