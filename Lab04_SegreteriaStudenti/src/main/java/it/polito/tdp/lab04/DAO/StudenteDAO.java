package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {

	public Studente getStudente(int matricola) {
		
		String sql = "SELECT * "
				+ "FROM studente "
				+ "WHERE matricola = ?";
		
		Studente s = null;
		
		try {
			
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);
			ResultSet rs = st.executeQuery();
			
			if(rs.next()) {
				
				s = new Studente (matricola, rs.getString("cognome"), rs.getString("nome"), rs.getString("cds"));

			}
			
			conn.close();
			return s;
			
		}catch(SQLException e) {
			throw new RuntimeException("Database error in getStudente", e);
		}
	}

	public List<Corso> getCorsiStudente(Studente s) {
		
		String sql = "SELECT c.codins, c.crediti, c.nome, c.pd "
				+ "FROM iscrizione i, corso c "
				+ "WHERE i.matricola = ? AND i.codins = c.codins";
		
		List<Corso> result = new ArrayList<>();
		
		Connection conn;
		
		try {
			
			conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, s.getMatricola());
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				Corso c = new Corso(rs.getString("codins"), rs.getInt("crediti"), rs.getString("nome"), rs.getInt("pd"));
				result.add(c);
				
			}
			
			conn.close();
			return result;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Database error in getCorsiStudente", e);
		}
	}

	public boolean isStudenteIscrittoCorso(Studente s, Corso c) {
	
		
		String sql = "SELECT * " +
				"FROM iscrizione " +
				"WHERE matricola = ? AND codins = ?";
			
		try {
			
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, s.getMatricola());
			st.setString(2, c.getCodIns());
			ResultSet rs = st.executeQuery();
			
			if(rs.first()) {
				
				return true;
			}
			
			conn.close();
			return false;
			
		}catch(SQLException e) {
			throw new RuntimeException("Database error in isStudenteIscrittoCorso", e);
		}
	}

}
