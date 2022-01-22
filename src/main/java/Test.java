
import java.sql.Time;
import java.util.Date;

import beans.Client;
import beans.Creneaux;
import beans.Occupation;
import beans.Salle;
import securite.Hash;
import service.ClientService;
import service.CreneauService;
import service.OccupationService;
import service.SalleService;

public class Test {
	public static void main(String[] args) {
		ClientService cls = new ClientService();
		CreneauService cns = new CreneauService();
		SalleService ss = new SalleService();
		OccupationService os = new OccupationService();
		
		/*cls.create(new Client("Bennani", "Meryem", "BennaniM", "bennani@gmail.com", Hash.encrypt("123456")));
		cls.create(new Client("Messaoudi", "Asmaa", "MessaoudiA", "krachachraf98@gmail.com", Hash.encrypt("123456789")));
		cls.create(new Client("Regragui", "Mohammed", "MohammedR", "MohammedR@gmail.com", Hash.encrypt("123456")));
		
		cns.create(new Creneaux(Time.valueOf("08:30:00"),Time.valueOf("12:30:00")));
		cns.create(new Creneaux(Time.valueOf("09:00:00"),Time.valueOf("11:00:00")));
		cns.create(new Creneaux(Time.valueOf("18:00:00"),Time.valueOf("15:00:00")));
		
		ss.create(new Salle("CC1", 30, "pratique"));
		ss.create(new Salle("CC2", 25, "pratique"));
		ss.create(new Salle("A3", 50, "théorique"));
		
		os.create(new Occupation(cls.findById(1), ss.findById(2), cns.findById(1), new Date("21/05/2021"), "valide"));
		os.create(new Occupation(cls.findById(2), ss.findById(1), cns.findById(2), new Date("20/01/2022"), "en attend de validation"));
		os.create(new Occupation(null, ss.findById(3), cns.findById(3), null, "valide"));*/
	}
}
