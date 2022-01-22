package beans;

import java.sql.Time;

public class Creneaux {
	private int id;
	private Time heureDebut;
	private Time heureFin;
	
	public Creneaux(int id, Time heureDebut, Time heureFin) {
		super();
		this.id = id;
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;
	}
	
	public Creneaux(Time heureDebut, Time heureFin) {
		super();
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Time getHeureDebut() {
		return heureDebut;
	}

	public void setHeureDebut(Time heureDebut) {
		this.heureDebut = heureDebut;
	}

	public Time getHeureFin() {
		return heureFin;
	}

	public void setHeureFin(Time heureFin) {
		this.heureFin = heureFin;
	}

	@Override
	public String toString() {
		return "Creneaux [id=" + id + ", heureDebut=" + heureDebut + ", heureFin=" + heureFin + "]";
	}


	
}
