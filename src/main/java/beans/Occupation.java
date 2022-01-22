package beans;
public class Occupation {
	private int id;
	private Salle salle;
	private Creneaux creneaux;
	
	public Occupation(int id, Salle salle, Creneaux creneaux) {
		this.id = id;
		this.salle = salle;
		this.creneaux = creneaux;
	}

	public Occupation(Salle salle, Creneaux creneaux) {
		this.salle = salle;
		this.creneaux = creneaux;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Salle getSalle() {
		return salle;
	}

	public void setSalle(Salle salle) {
		this.salle = salle;
	}

	public Creneaux getCreneaux() {
		return creneaux;
	}

	public void setCreneaux(Creneaux creneaux) {
		this.creneaux = creneaux;
	}

	@Override
	public String toString() {
		return "Occupation [id=" + id + ", salle=" + salle + ", creneaux=" + creneaux + "]";
	}
	
}
