package cfs.laudos.cardiologia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cfs.laudos.cardiologia.bd.Conexao;
import cfs.laudos.cardiologia.modelo.Medico;

public class MedicoDAO {
	private Connection connection;
	
	public MedicoDAO() {
		this.connection = Conexao.getConnection();
	}
	
	public void adiciona(Medico medico) {
		String sql = "insert into medico "
				+ "(crm, nome)"
				+ "values (?,?)";
		
	try(PreparedStatement stm = connection.prepareStatement(sql)){
		
			stm.setString(1, medico.getCrm());
			stm.setString(2, medico.getNome());
			System.out.println(stm.toString());
			stm.execute();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void altera(Medico medico) {
		String sql = "update medico set nome=? "
				+ "where crm=?";
		
	try(PreparedStatement stm = connection.prepareStatement(sql)){
			stm.setString(1, medico.getNome());
			stm.setString(2, medico.getCrm());
			System.out.println(stm.toString());
			stm.execute();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void remove(Medico medico) {
		String sql = "delete from medico where crm=?";
		
	try(PreparedStatement stm = connection.prepareStatement(sql)){
			stm.setString(1, medico.getCrm());
			stm.execute();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Medico> listaMedicos(){
		List<Medico> medicos = new ArrayList<>();
		String sql = "select * from medico";
		
		try(PreparedStatement stm = connection.prepareStatement(sql)){
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {
				Medico medico = new Medico();
				medico.setCrm(rs.getString("crm"));
				medico.setNome(rs.getString("nome"));
				medicos.add(medico);
			}
		}catch (SQLException e) {
			// TODO: handle exception
		}
		return medicos;
	}
	
	public Medico recuperaMedico(Medico c) {
		String sql = "select * from medico where crm=?";
		Medico medico = null;
		try (PreparedStatement stmt = connection.prepareStatement(sql)){
			stmt.setString(1,c.getCrm());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				medico = new Medico();
				medico.setCrm(rs.getString("crm"));
				medico.setNome(rs.getString("nome"));
			}
		} catch (SQLException e) {

		}	
		return medico;
	}
}
