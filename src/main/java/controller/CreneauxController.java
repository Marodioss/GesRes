package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.CreneauService;

import java.io.IOException;
import java.sql.Time;
import java.util.List;

import com.google.gson.Gson;

import beans.Creneaux;
import beans.Salle;

/**
 * Servlet implementation class CreneauxController
 */
public class CreneauxController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CreneauService cs = new CreneauService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreneauxController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    private void loadData(HttpServletResponse response) throws IOException {
		List<Creneaux> creneaux = cs.findAll();
		response.setContentType("application/json");		
		Gson json = new Gson();
		response.getWriter().write(json.toJson(creneaux));
	}

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	if(request.getParameter("op") != null) {
    		if(request.getParameter("op").equals("update")) {
    			Creneaux creneaux = cs.findById(Integer.parseInt(request.getParameter("id")));
    			response.setContentType("application/json");		
    			Gson json = new Gson();
    			response.getWriter().write(json.toJson(creneaux));
    		}else if(request.getParameter("op").equals("delete")) {
    			if(!cs.delete(cs.findById(Integer.parseInt(request.getParameter("id"))))) 
    				response.sendError(400);	
    		}
    	}else {
    		if(!(request.getParameter("heureDebut").equals("") || request.getParameter("heureFin").equals(""))) {
    			int id = Integer.parseInt(request.getParameter("id"));
	    		Creneaux creneaux = cs.findById(id);
	    		Time heureDebut = Time.valueOf(request.getParameter("heureDebut") + ":00") ;
				Time heureFin = Time.valueOf(request.getParameter("heureFin") + ":00") ;
	    		if(creneaux == null) {
	    			if(!cs.create(new Creneaux(heureDebut, heureFin)))
	    				response.sendError(402);   				
	    		}else {
	    			creneaux.setHeureDebut(Time.valueOf(request.getParameter("heureDebut") + ":00"));
	    			creneaux.setHeureFin(Time.valueOf(request.getParameter("heureFin") + ":00"));
	    			cs.update(creneaux);
	    		}
    		}else
    			response.sendError(401);
    	}
    	loadData(response);
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
