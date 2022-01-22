package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Client;
import beans.Creneaux;
import connexion.Connexion;
import dao.IDao;
import securite.Hash;

public class ClientService implements IDao<Client> {

	@Override
	public boolean create(Client o) {
		String sql = "insert into clients values (null, ?, ?, ?, ?, ?)";
		String sqlCount = "select count(*) from clients where (nom = ? and prenom = ?) or (login = ?) or (email = ?)";
		try {
			PreparedStatement psCount = Connexion.getInstane().getConnection().prepareStatement(sqlCount);
			psCount.setString(1, o.getNom());
			psCount.setString(2, o.getPrenom());
			psCount.setString(3, o.getLogin());
			psCount.setString(4, o.getEmail());
			ResultSet rs = psCount.executeQuery();
			if (rs.next()) {
				if(rs.getInt(1) > 0)
					return false;
			}
			PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
			ps.setString(1, o.getNom());
			ps.setString(2, o.getPrenom());
			ps.setString(3, o.getLogin());
			ps.setString(4, o.getEmail());
			ps.setString(5, o.getPassword());
			if (ps.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println("create : erreur sql : " + e.getMessage());

		}
		return false;
	}

	@Override
	public boolean delete(Client o) {
		String sql = "delete from clients where id  = ?";
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
	public boolean update(Client o) {
		String sql = "update clients set nom  = ? ,prenom = ?, login = ?, email = ?, password = ? where id  = ?";
		try {
			PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
			ps.setString(1, o.getNom());
			ps.setString(2, o.getPrenom());
			ps.setString(3, o.getLogin());
			ps.setString(4, o.getEmail());
			ps.setString(5, o.getPassword());
			ps.setInt(6, o.getId());
			if (ps.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println("update : erreur sql : " + e.getMessage());

		}
		return false;
	}

	@Override
	public Client findById(int id) {
		String sql = "select * from clients where id  = ?";
		try {
			PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new Client(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("login"),
						rs.getString("email"), rs.getString("password"));
			}

		} catch (SQLException e) {
			System.out.println("findById " + e.getMessage());
		}
		return null;
	}

	@Override
	public List<Client> findAll() {
		List<Client> clients = new ArrayList<Client>();

		String sql = "select * from clients";
		try {
			PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
			;
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				clients.add(new Client(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"),
						rs.getString("login"), rs.getString("email"), rs.getString("password")));
			}

		} catch (SQLException e) {
			System.out.println("findAll " + e.getMessage());
		}
		return clients;
	}

	public Client findByLoginOrEmail(String login, String email) {
		String sql = "select * from clients where login  = ? or email = ?";
		try {
			PreparedStatement ps = Connexion.getInstane().getConnection().prepareStatement(sql);
			ps.setString(1, login);
			ps.setString(2, email);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new Client(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("login"),
						rs.getString("email"), rs.getString("password"));
			}

		} catch (SQLException e) {
			System.out.println("findById " + e.getMessage());
		}
		return null;
	}

}
