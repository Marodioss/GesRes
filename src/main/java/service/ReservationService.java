package service;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Occupation;
import beans.Reservation;
import connexion.Connexion;
import dao.IDao;

public class ReservationService implements IDao<Reservation>{
	private OccupationService os = new OccupationService();
	private ClientService cs = new ClientService();
	
	@Override
	public boolean create(Reservation o) {
		String sql = "insert into reservations values (null, ?, ?, ?, ?)";
        try {
        	PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
		    ps.setInt(1, o.getOccupation().getId());
		    ps.setInt(2, o.getClient().getId());
		    ps.setDate(3, new Date(o.getDate().getTime()));
		    ps.setString(4, o.getEtat());
		    if (ps.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("create : erreur sql : " + e.getMessage());
        }
        return false;
	}

	@Override
	public boolean delete(Reservation o) {
		 String sql = "delete from reservations where id  = ?";
	        try {
	            PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
	            ps.setInt(1, o.getId());
	            if (ps.executeUpdate() == 1) {
	                return true;
	            }
	        } catch (SQLException e) {
	            System.out.println("delete : erreur sql : " + e.getMessage());

	        }
	        return false;
	}

	@Override
	public boolean update(Reservation o) {
		return false;
	}

	@Override
	public Reservation findById(int id) {
		String sql = "select * from reservations where id  = ?";
		try {
			PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new Reservation(rs.getInt("id"), os.findById(rs.getInt("idOccupation")),
						cs.findById(rs.getInt("idClient")), rs.getDate("date"), rs.getString("etat"));
			}

		} catch (SQLException e) {
			System.out.println("findById " + e.getMessage());
		}
		return null;
	}

	@Override
	public List<Reservation> findAll() {
		List<Reservation> reservations = new ArrayList<Reservation>();

		String sql = "select * from reservations";
		try {
			PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				reservations.add(new Reservation(rs.getInt("id"), os.findById(rs.getInt("idOccupation")),
						cs.findById(rs.getInt("idlient")), rs.getDate("date"), rs.getString("etat")));
			}

		} catch (SQLException e) {
			System.out.println("findAll " + e.getMessage());
		}
		return reservations;
	}

	public List<Reservation> findByIdClient(int id) {
		List<Reservation> reservations = new ArrayList<Reservation>();

		String sql = "select * from reservations where idClient  = ?";
		try {
			PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				reservations.add(new Reservation(rs.getInt("id"), os.findById(rs.getInt("idOccupation")),
						cs.findById(rs.getInt("idClient")), rs.getDate("date"), rs.getString("etat")));
			}

		} catch (SQLException e) {
			System.out.println("findByIdClient " + e.getMessage());
		}
		return reservations;
	}
	
	public List<Reservation> findReservationsValide() {
		List<Reservation> reservations = new ArrayList<Reservation>();

		String sql = "select * from reservations where etat = 'valide'";
		try {
			PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				reservations.add(new Reservation(rs.getInt("id"), os.findById(rs.getInt("idOccupation")),
						cs.findById(rs.getInt("idClient")), rs.getDate("date"), rs.getString("etat")));
			}

		} catch (SQLException e) {
			System.out.println("findAll " + e.getMessage());
		}
		return reservations;
	}

	public List<Reservation> findReservationsEnAttend() {
		List<Reservation> reservations = new ArrayList<Reservation>();

		String sql = "select * from reservations where etat = 'en attend de validation'";
		try {
			PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				reservations.add(new Reservation(rs.getInt("id"), os.findById(rs.getInt("idOccupation")),
						cs.findById(rs.getInt("idClient")), rs.getDate("date"), rs.getString("etat")));
			}

		} catch (SQLException e) {
			System.out.println("findAll " + e.getMessage());
		}
		return reservations;
	}
	
	public boolean valider(int id) {
		String sql = "update reservations set etat = ? where id  = ?";
        try {
            PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
            ps.setString(1, "valide");
            ps.setInt(2, id);
            if (ps.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("valider : erreur sql : " + e.getMessage());

        }
        return false;
	}
	
}
