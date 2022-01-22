package beans;

public class Client {
	private int id;
	private String nom;
	private String prenom;
	private String login;
	private String email;
	private String password;
	
	public Client(int id, String nom, String prenom, String login, String email, String password) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.login = login;
		this.email = email;
		this.password = password;
	}

	
	public Client(String nom, String prenom, String login, String email, String password) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.login = login;
		this.email = email;
		this.password = password;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	public String toString() {
		return "Client [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", login=" + login + ", email=" + email
				+ ", password=" + password + "]";
	}

	
}
