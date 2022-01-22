package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.CreneauService;
import service.OccupationService;
import service.SalleService;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;

import beans.Creneaux;
import beans.Occupation;
import beans.Salle;

/**
 * Servlet implementation class PlanningController
 */
public class PlanningController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CreneauService cs = new CreneauService();
	private SalleService ss = new SalleService();
	private OccupationService os = new OccupationService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlanningController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    private void loadData(HttpServletResponse response) throws IOException {
		List<Occupation> occupations = os.findAll();
		response.setContentType("application/json");		
		Gson json = new Gson();
		response.getWriter().write(json.toJson(occupations));
	}
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	if(request.getParameter("op") != null) {
    		if(request.getParameter("op").equals("load"))
    			loadData(response);
    		else if(request.getParameter("op").equals("delete")) {
    			if(os.delete(os.findById(Integer.parseInt(request.getParameter("id")))))
    				loadData(response);
    			else
    				response.sendError(400);
    			}
		}else {
			if(!(request.getParameter("idCreneaux").equals("") || request.getParameter("idSalle").equals(""))) {
				Occupation occupation = os.findById(Integer.parseInt(request.getParameter("id")));
	    		Creneaux creneaux = cs.findById(Integer.parseInt(request.getParameter("idCreneaux")));
	    		Salle salle = ss.findById(Integer.parseInt(request.getParameter("idSalle")));
	    		if(occupation == null) {
	    			if(os.create(new Occupation(salle, creneaux)))
	    				loadData(response);
	    			else
	    				response.sendError(402);
	    		}else {
	    			occupation.setSalle(salle);
	    			occupation.setCreneaux(creneaux);
	    			os.update(occupation);
	    			loadData(response);
	    		}
			}else
				response.sendError(401);
    	}
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
