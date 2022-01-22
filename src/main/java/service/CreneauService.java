package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import beans.Creneaux;
import beans.Salle;
import connexion.Connexion;
import dao.IDao;

public class CreneauService implements IDao<Creneaux>{
	@Override
    public boolean create(Creneaux o) {
        String sql = "insert into creneaux values (null, ?, ?)";
        String sqlCount = "select count(*) from creneaux where heureDebut  = ? and heureFin = ?";
        try {
        	PreparedStatement psCount = Connexion.getInstane().getConnection().prepareStatement(sqlCount);
        	psCount.setTime(1, o.getHeureDebut());
        	psCount.setTime(2, o.getHeureFin());
        	ResultSet rs = psCount.executeQuery();
        	if(rs.next()) {
        		if(rs.getInt(1) == 0) {
        			PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
                    ps.setTime(1, o.getHeureDebut());
                    ps.setTime(2, o.getHeureFin());
                    if (ps.executeUpdate() == 1) {
                        return true;
                    }
        		}
            }
        } catch (SQLException e) {
           e.printStackTrace();

        }
        return false;
    }

    @Override
    public boolean delete(Creneaux o) {
        String sql = "delete from creneaux where id  = ?";
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
    public boolean update(Creneaux o) {

        String sql = "update creneaux set heureDebut  = ? ,heureFin = ? where id  = ?";
        try {
            PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
            ps.setTime(1, o.getHeureDebut());
            ps.setTime(2, o.getHeureFin());
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
    public Creneaux findById(int id) {
    	Creneaux c = null;
        String sql = "select * from creneaux where id  = ?";
        try {
            PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Creneaux(rs.getInt("id"), rs.getTime("heureDebut"), rs.getTime("heureFin"));
            }

        } catch (SQLException e) {
            System.out.println("findById " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Creneaux> findAll() {
        List<Creneaux> creneaux = new ArrayList<Creneaux>();

        String sql = "select * from creneaux";
        try {
            PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);;
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	creneaux.add(new Creneaux(rs.getInt("id"), rs.getTime("heureDebut"), rs.getTime("heureFin")));
            }

        } catch (SQLException e) {
            System.out.println("findAll " + e.getMessage());
        }
        return creneaux;
    }
    
    public Creneaux findByHeures(Time heureDebut, Time heureFin) {
        String sql = "select * from creneaux where heureDebut  = ? and heureFin = ?";
        try {
            PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
            ps.setTime(1, heureDebut);
            ps.setTime(2, heureFin);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Creneaux(rs.getInt("id"), rs.getTime("heureDebut"), rs.getTime("heureFin"));
            }

        } catch (SQLException e) {
            System.out.println("findByHeures " + e.getMessage());
        }
        return null;
    }
}
