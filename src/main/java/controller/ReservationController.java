package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.ClientService;
import service.OccupationService;
import service.ReservationService;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;

import beans.Client;
import beans.Occupation;
import beans.Reservation;
import beans.Salle;

/**
 * Servlet implementation class ReservationController
 */
public class ReservationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReservationService rs = new ReservationService();
	private OccupationService os = new OccupationService();
	private ClientService cs = new ClientService();
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReservationController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	private void loadData(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		int id = Integer.parseInt(session.getAttribute("idClient").toString());
		List<Reservation>  reservations = rs.findByIdClient(id);
		response.setContentType("application/json");		
		Gson json = new Gson();
		response.getWriter().write(json.toJson(reservations));
	}
	
	private void loadDataV(HttpServletResponse response) throws IOException {
		List<Reservation> reservations = rs.findReservationsValide();
		response.setContentType("application/json");		
		Gson json = new Gson();
		response.getWriter().write(json.toJson(reservations));
	}
	
	private void loadDataEV(HttpServletResponse response) throws IOException {
		List<Reservation> reservations = rs.findReservationsEnAttend();
		response.setContentType("application/json");		
		Gson json = new Gson();
		response.getWriter().write(json.toJson(reservations));
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
	 	if(request.getParameter("op") != null) {
    		if(request.getParameter("op").equals("load"))
    			loadData(request, response);
    		else if(request.getParameter("op").equals("loadDisponibilite")) {
    			List<Occupation> occupations = os.findDispos(new Date(request.getParameter("date").replace("-", "/")));
    			response.setContentType("application/json");		
    			Gson json = new Gson();
    			response.getWriter().write(json.toJson(occupations));
    		}else if(request.getParameter("op").equals("delete")) {
				Reservation reservation = rs.findById(Integer.parseInt(request.getParameter("id")));
				if(reservation.getEtat().equals("valide"))
					response.sendError(400);
				else {
					rs.delete(reservation);
					loadData(request, response);
				}		
			}else if(request.getParameter("op").equals("loadResValid")) 
				loadDataV(response);
    		else if(request.getParameter("op").equals("loadResEnAtt")) 
    			loadDataEV(response);
    		else if(request.getParameter("op").equals("invalide")) {
				Reservation reservation = rs.findById(Integer.parseInt(request.getParameter("id")));
				rs.delete(reservation);
				response.sendRedirect("reservations-valide.jsp");
			}else if(request.getParameter("op").equals("valide")) {
				rs.valider(Integer.parseInt(request.getParameter("id")));
				response.sendRedirect("reservations-valide.jsp");
			}
    	}else {
    		HttpSession session = request.getSession();
    		Client client = cs.findById(Integer.parseInt(session.getAttribute("idClient").toString()));
    		Occupation occupation = os.findById(Integer.parseInt(request.getParameter("id")));
    		Date date = new Date(request.getParameter("date").replace("-", "/"));
    		rs.create(new Reservation(occupation, client, date, "en attend de validation"));
    		loadData(request, response);
    	}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

}
