package cfs.laudos.cardiologia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cfs.laudos.cardiologia.bd.Conexao;
import cfs.laudos.cardiologia.modelo.Laudo;
import cfs.laudos.cardiologia.modelo.MedicoResidente;

public class LaudoDAO {
	private Connection connection;

	public LaudoDAO() {
		this.connection = Conexao.getConnection();
	}
	
	public void adiciona(Laudo laudo) { 
		String sql = "insert into laudo (id, medico, laudo, diagnostico, situacao)"
				+ " values (?,?,?,?,?)";
		try(PreparedStatement stm = connection.prepareStatement(sql)){
			stm.setLong(1, laudo.getIdExame());
			stm.setString(2, laudo.getMedico().getCrm());
			stm.setString(3, laudo.getLaudo());
			stm.setString(4, laudo.getDiagnostico());
			stm.setString(5, laudo.getSituacao());
			stm.execute();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Laudo recuperaLaudo(Laudo l) {
		String sql = "select * from laudo where id=?";
		Laudo laudo = null;
		try (PreparedStatement stmt = connection.prepareStatement(sql)){
			stmt.setLong(1,l.getIdExame());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				laudo = new Laudo();
				laudo.setIdExame(rs.getLong("id"));
				MedicoResidente m = new MedicoResidente();
				m.setCrm(rs.getString("medico"));
				m = (new MedicoResidenteDAO()).recuperaMedicoResidente(m);
				laudo.setMedico(m);
				laudo.setLaudo(rs.getString("laudo"));
				laudo.setDiagnostico(rs.getString("diagnostico"));
				laudo.setSituacao(rs.getString("situacao"));
			}
		} catch (SQLException e) {

		}	
		return laudo;
	}
	
	public void altera(Laudo laudo) {
		String sql = "update laudo set medico=?, laudo=?, "
				+ "situacao=?, diagnostico=? "
				+ "where id=?";
		
	try(PreparedStatement stm = connection.prepareStatement(sql)){
		stm.setString(1, laudo.getMedico().getCrm());
		stm.setString(2, laudo.getLaudo());
		stm.setString(3, laudo.getSituacao());
		stm.setString(4, laudo.getDiagnostico());
		stm.setLong(5, laudo.getIdExame());
		stm.execute();
		stm.execute();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Laudo> listaLaudosDefinitivos(String paciente){
		List<Laudo> laudos = new ArrayList<>();
		String sql = "select laudo.* from laudo join exame on laudo.id = exame.id_exame "
				+ "where exame.paciente=?";
		try(PreparedStatement stm = connection.prepareStatement(sql)){
			stm.setString(1,paciente);
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {
				if(rs.getString("situacao").equals(Laudo.LAUDO_DEFINITIVO)) {
					Laudo laudo = new Laudo();
					laudo.setIdExame(rs.getLong("id"));
					MedicoResidente m = new MedicoResidente();
					m.setCrm(rs.getString("medico"));
					m = (new MedicoResidenteDAO()).recuperaMedicoResidente(m);
					laudo.setMedico(m);
					laudo.setLaudo(rs.getString("laudo"));
					laudo.setDiagnostico(rs.getString("diagnostico"));
					laudo.setSituacao(rs.getString("situacao"));
					laudos.add(laudo);
				}
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return laudos;
	}
}
