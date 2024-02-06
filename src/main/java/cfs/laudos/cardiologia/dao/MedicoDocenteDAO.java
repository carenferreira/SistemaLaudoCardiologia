package cfs.laudos.cardiologia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cfs.laudos.cardiologia.bd.Conexao;
import cfs.laudos.cardiologia.modelo.MedicoDocente;

public class MedicoDocenteDAO {
	private Connection connection;
	
	public MedicoDocenteDAO() {
		this.connection = Conexao.getConnection();
	}
	
	public void adiciona(MedicoDocente medicoDocente) {
		String sql = "insert into medicodocente "
				+ "(crm, nome, titulacao)"
				+ " values (?,?,?)";
		
	try(PreparedStatement stm = connection.prepareStatement(sql)){
			stm.setString(1, medicoDocente.getCrm());
			stm.setString(2, medicoDocente.getNome());
			stm.setString(3, medicoDocente.getTitulacao());
			System.out.println(stm.toString());
			stm.execute();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void altera(MedicoDocente medicoDocente) {
		String sql = "update medicodocente set nome=?, titulacao=? "
				+ "where crm=?";
		
	try(PreparedStatement stm = connection.prepareStatement(sql)){
			stm.setString(1, medicoDocente.getNome());
			stm.setString(2, medicoDocente.getTitulacao());
			stm.setString(3, medicoDocente.getCrm());
			stm.execute();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void remove(MedicoDocente medicoDocente) {
		String sql = "delete from medicodocente where crm=?";
		
	try(PreparedStatement stm = connection.prepareStatement(sql)){
			stm.setString(1, medicoDocente.getCrm());
			stm.execute();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<MedicoDocente> listaMedicoDocentes(){
		List<MedicoDocente> medicoDocentes = new ArrayList<>();
		String sql = "select * from medicodocente";
		
		try(PreparedStatement stm = connection.prepareStatement(sql)){
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {
				MedicoDocente medicoDocente = new MedicoDocente();
				medicoDocente.setCrm(rs.getString("crm"));
				medicoDocente.setNome(rs.getString("nome")); 
				medicoDocente.setTitulacao(rs.getString("titulacao"));
				medicoDocentes.add(medicoDocente);
			}
		}catch (SQLException e) {
			// TODO: handle exception
		}
		return medicoDocentes;
	}
	
	public MedicoDocente recuperaMedicoDocente(MedicoDocente c) {
		String sql = "select * from medicodocente where crm=?";
		MedicoDocente medicoDocente = null;
		try (PreparedStatement stmt = connection.prepareStatement(sql)){
			stmt.setString(1,c.getCrm());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				medicoDocente = new MedicoDocente();
				medicoDocente.setCrm(rs.getString("crm"));
				medicoDocente.setNome(rs.getString("nome"));
				medicoDocente.setTitulacao(rs.getString("titulacao"));
			}
		} catch (SQLException e) {

		}	
		return medicoDocente;
	}
}
