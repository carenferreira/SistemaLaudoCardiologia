package cfs.laudos.cardiologia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cfs.laudos.cardiologia.bd.Conexao;
import cfs.laudos.cardiologia.modelo.MedicoResidente;

public class MedicoResidenteDAO {
	private Connection connection;
	
	public MedicoResidenteDAO() {
		this.connection = Conexao.getConnection();
	}
	
	public void adiciona(MedicoResidente medicoResidente) {
		String sql = "insert into medicoresidente "
				+ "(crm, nome, matricula)"
				+ "values (?,?,?)";
		
	try(PreparedStatement stm = connection.prepareStatement(sql)){
			stm.setString(1, medicoResidente.getCrm());
			stm.setString(2, medicoResidente.getNome());
			stm.setString(3, medicoResidente.getMatricula());
			stm.execute();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void altera(MedicoResidente medicoResidente) {
		String sql = "update medicoresidente set crm=?, nome=?, matricula=? "
				+ "where crm=?";
		
	try(PreparedStatement stm = connection.prepareStatement(sql)){
			stm.setString(1, medicoResidente.getNome());
			stm.setString(2, medicoResidente.getMatricula());
			stm.setString(3, medicoResidente.getCrm());
			System.out.println(stm);
			stm.execute();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void remove(MedicoResidente medicoResidente) {
		String sql = "delete from medicoresidente where crm=?";
		
	try(PreparedStatement stm = connection.prepareStatement(sql)){
			stm.setString(1, medicoResidente.getCrm());
			stm.execute();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<MedicoResidente> listaMedicoResidentes(){
		List<MedicoResidente> medicoResidentes = new ArrayList<>();
		String sql = "select * from medicoresidente";
		
		try(PreparedStatement stm = connection.prepareStatement(sql)){
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {
				MedicoResidente medicoResidente = new MedicoResidente();
				medicoResidente.setCrm(rs.getString("crm"));
				medicoResidente.setNome(rs.getString("nome")); 
				medicoResidente.setMatricula(rs.getString("matricula"));
				medicoResidentes.add(medicoResidente);
			}
		}catch (SQLException e) {
			// TODO: handle exception
		}
		return medicoResidentes;
	}
	
	public MedicoResidente recuperaMedicoResidente(MedicoResidente c) {
		String sql = "select * from medicoresidente where crm=?";
		MedicoResidente medicoResidente = null;
		try (PreparedStatement stmt = connection.prepareStatement(sql)){
			stmt.setString(1,c.getCrm());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				medicoResidente = new MedicoResidente();
				medicoResidente.setCrm(rs.getString("crm"));
				medicoResidente.setNome(rs.getString("nome"));
				medicoResidente.setMatricula(rs.getString("matricula"));
			}
		} catch (SQLException e) {

		}	
		return medicoResidente;
	}
}
