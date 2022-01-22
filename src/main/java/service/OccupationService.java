package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import beans.Occupation;
import beans.Salle;
import connexion.Connexion;
import dao.IDao;

public class OccupationService implements IDao<Occupation> {
	private SalleService ss = new SalleService();
	private CreneauService cs = new CreneauService();

	@Override
	public boolean create(Occupation o) {
		String sql = "insert into occupations values (null, ?, ?)";
		String sqlCount = "select count(*) from occupations where idCreneaux = ? and idSalle = ?";
		try {
			PreparedStatement psCount = Connexion.getInstane().getConnection().prepareStatement(sqlCount);
			psCount.setInt(1, o.getCreneaux().getId());
			psCount.setInt(2, o.getSalle().getId());
			ResultSet rs = psCount.executeQuery();
			if (rs.next()) {
				if (rs.getInt(1) > 0)
					return false;
			}
			PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
			ps.setInt(1, o.getCreneaux().getId());
			ps.setInt(2, o.getSalle().getId());
			if (ps.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println("create : erreur sql : " + e.getMessage());

		}
		return false;
	}

	@Override
	public boolean delete(Occupation o) {
		String sql = "delete from occupations where id  = ?";
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
	public boolean update(Occupation o) {
		String sql = "update occupations set idCreneaux = ? , idSalle = ? where id  = ?";
		try {
			PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
			ps.setInt(1, o.getCreneaux().getId());
			ps.setInt(2, o.getSalle().getId());
			ps.setInt(3, o.getId());
			if (ps.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println("update : erreur sql : " + e.getMessage());

		}
		return false;
	}

	@Override
	public Occupation findById(int id) {
		String sql = "select * from occupations where id  = ?";
		try {
			PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new Occupation(rs.getInt("id"), ss.findById(rs.getInt("idSalle")),
						cs.findById(rs.getInt("idCreneaux")));
			}

		} catch (SQLException e) {
			System.out.println("findById " + e.getMessage());
		}
		return null;
	}

	@Override
	public List<Occupation> findAll() {
		List<Occupation> occupations = new ArrayList<Occupation>();

		String sql = "select * from occupations";
		try {
			PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				occupations.add(new Occupation(rs.getInt("id"), ss.findById(rs.getInt("idSalle")),
						cs.findById(rs.getInt("idCreneaux"))));
			}

		} catch (SQLException e) {
			System.out.println("findAll " + e.getMessage());
		}
		return occupations;
	}

	public List<Occupation> findDispos(Date dateC) {
		List<Occupation> occupations = new ArrayList<Occupation>();
		java.sql.Date date = new java.sql.Date(dateC.getTime());
		String sql = "select * "
				+ "from occupations "
				+ "where id not in (select idOccupation from reservations where date = ?)";
		try {
			PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
			ps.setDate(1, date);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				occupations.add(new Occupation(rs.getInt("id"), ss.findById(rs.getInt("idSalle")),
						cs.findById(rs.getInt("idCreneaux"))));
			}

		} catch (SQLException e) {
			System.out.println("findDispos " + e.getMessage());
		}
		return occupations;
	}
	
	 public List<Salle> findSallesDispo(Date dateC) {
	        List<Salle> salles = new ArrayList<Salle>();
	        java.sql.Date date = new java.sql.Date(dateC.getTime());
	        
	        String sql = "select DISTINCT(salles.id), code, type, capacite "
	        		+ "from salles "
	        		+ "LEFT JOIN occupations "
	        		+ "ON salles.id = occupations.idSalle "
	        		+ "where occupations.id not in (select idOccupation from reservations where date = ?) "
	        		+ "or occupations.id is null;";
	        try {
	            PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
	            ps.setDate(1, date);
	            ResultSet rs = ps.executeQuery();
	            while (rs.next()) {
	            	salles.add(new Salle(rs.getInt("id"), rs.getString("code"), rs.getInt("capacite"), rs.getString("type")));
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return salles;
	    }
}
