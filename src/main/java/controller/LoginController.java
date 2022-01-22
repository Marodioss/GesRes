package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import securite.Hash;
import service.ClientService;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import beans.Client;

/**
 * Servlet implementation class LoginController
 */
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ClientService cs = new ClientService();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    private static void sendMail(String recepient, int code) throws Exception {
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		String myAccount = "monpon841@gmail.com";
		String password = "Marouane2019-";
		Session session = Session.getInstance(properties, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(myAccount, password);
			}
		});
		Message message = prepareMessage(session, myAccount, recepient, code);
		Transport.send(message);
	}

	private static Message prepareMessage(Session session, String myAccount, String recepient, int code) {
		try {
			Message message;
			message = new MimeMessage(session);
			message.setFrom(new InternetAddress(myAccount));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
			message.setSubject("Récupération du mot de passe");
			message.setText("Le code de vérification est : " + code);
			return message;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
    	if(request.getParameter("loginEmail") != null && request.getParameter("password") != null) {
    		String loginEmail = request.getParameter("loginEmail");
    		String hashedPassword = Hash.encrypt(request.getParameter("password"));
    		HttpSession session = request.getSession();
    		if ((loginEmail.equals("Admin") || loginEmail.equals("admin@gmail.com"))
					&& hashedPassword.equals(Hash.encrypt("123456"))) {
				session.setAttribute("idAmin", 1);
				response.sendRedirect("/reservationsApp/index.jsp");
			} else {
				Client client = cs.findByLoginOrEmail(loginEmail, loginEmail);
				if (client != null) {
					if (hashedPassword.equals(client.getPassword())) {
						session.setAttribute("idClient", client.getId());
						response.sendRedirect("/reservationsApp/home.jsp");
					} else {
						String destination = "login.jsp";
						RequestDispatcher dispatcher = request.getRequestDispatcher(destination);
						request.setAttribute("mdpError", "notexist");
						dispatcher.forward(request, response);
					}
				} else {
					String destination = "login.jsp";
					RequestDispatcher dispatcher = request.getRequestDispatcher(destination);
					request.setAttribute("loginEmailError", "notexist");
					dispatcher.forward(request, response);
				}
			}
    	}else if (request.getParameter("email") != null) {
			String email = request.getParameter("email");
			Client client = cs.findByLoginOrEmail(email, email);
			if (client == null) {
				String destination = "récuperer-password.jsp";
				RequestDispatcher dispatcher = request.getRequestDispatcher(destination);
				request.setAttribute("emailError", "notexist");
				dispatcher.forward(request, response);
			} else {
				int code = (int) (99999 * Math.random());
				HttpSession session = request.getSession();
				session.setAttribute("code", code);
				session.setAttribute("idClient", client.getId());
				response.sendRedirect("/reservationsApp/code-verification.jsp");
				try {
					this.sendMail(email, code);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else if (request.getParameter("code") != null) {
			int codeSaisie = Integer.parseInt(request.getParameter("code"));		
			HttpSession session = request.getSession();
			int code = Integer.parseInt(session.getAttribute("code").toString());
			if (code != codeSaisie) {
				String destination = "code-verification.jsp";
				RequestDispatcher dispatcher = request.getRequestDispatcher(destination);
				request.setAttribute("codeError", "incorrect");
				request.setAttribute("code", code);
				dispatcher.forward(request, response);			
			}else {
				String destination = "changer-password.jsp";
				RequestDispatcher dispatcher = request.getRequestDispatcher(destination);
				dispatcher.forward(request, response);
			}
			
		}else if(request.getParameter("password") != null && request.getParameter("passwordConfirm") != null){
			String password = Hash.encrypt(request.getParameter("password"));
			String passwordConfirm = Hash.encrypt(request.getParameter("passwordConfirm"));
			if(password.equals(passwordConfirm)) {
				HttpSession session = request.getSession();
				int id = Integer.parseInt(session.getAttribute("idClient").toString());
				Client client = cs.findById(id);
				client.setPassword(password);
				cs.update(client);
				session.invalidate();
				String destination = "login.jsp";
				RequestDispatcher dispatcher = request.getRequestDispatcher(destination);
				request.setAttribute("mdpChanged", "success");
				dispatcher.forward(request, response);
			}else {
				String destination = "changer-password.jsp";
				RequestDispatcher dispatcher = request.getRequestDispatcher(destination);
				request.setAttribute("mdpConfirmError", "notexist");
				dispatcher.forward(request, response);
			}
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
