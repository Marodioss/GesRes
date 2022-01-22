package beans;

import java.util.Date;

public class Reservation {
	private int id;
	private Occupation occupation;
	private Client client;
	private Date date;
	private String etat;
	
	public Reservation(int id, Occupation occupation, Client client, Date date, String etat) {
		super();
		this.id = id;
		this.occupation = occupation;
		this.client = client;
		this.date = date;
		this.etat = etat;
	}
	
	public Reservation(Occupation occupation, Client client, Date date, String etat) {
		super();
		this.occupation = occupation;
		this.client = client;
		this.date = date;
		this.etat = etat;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Occupation getOccupation() {
		return occupation;
	}

	public void setOccupation(Occupation occupation) {
		this.occupation = occupation;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	
}
