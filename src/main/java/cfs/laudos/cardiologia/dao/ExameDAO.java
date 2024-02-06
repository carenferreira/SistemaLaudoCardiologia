package cfs.laudos.cardiologia.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.Instant;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cfs.laudos.cardiologia.bd.Conexao;
import cfs.laudos.cardiologia.modelo.Exame;
import cfs.laudos.cardiologia.modelo.Medico;
import cfs.laudos.cardiologia.modelo.Paciente;

public class ExameDAO {
	private Connection connection;

	public ExameDAO() {
		this.connection = Conexao.getConnection();
	}

	public void adiciona(Exame exame) {
		String sql = "insert into exame "
				+ "(paciente, medico, exame, hipotese, recomendacao, situacao,"
				+ "data, hora, datarealizacao)"
				+ "values (?,?,?,?,?,?,?,?,?)";

		try(PreparedStatement stm = connection.prepareStatement(sql)){
			stm.setString(1, exame.getPaciente().getCpf());
			stm.setString(2, exame.getMedico().getCrm());
			stm.setString(3, exame.getExame());
			stm.setString(4, exame.getHipotese());
			stm.setString(5, exame.getRecomendacao());
			stm.setString(6, exame.getSituacao());
			Date data = new Date(exame.getData().getTimeInMillis());
			stm.setDate(7, data);
			Time sqlTime = java.sql.Time.valueOf(exame.getHora());
			stm.setTime(8, sqlTime);
			Date dataRealizacao = new Date(exame.getDataRealizacao().getTimeInMillis());
			stm.setDate(9, dataRealizacao);
			
			System.out.println(stm.toString());
			stm.execute();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void altera(Exame exame) {
		String sql = "update exame set paciente=?, medico=?, exame=?, hipotese=?, recomendacao=?, situacao=?,"
				+ " data=?, hora=?, datarealizacao=?"
				+ "where id_exame=?";

		try(PreparedStatement stm = connection.prepareStatement(sql)){
			stm.setString(1, exame.getPaciente().getCpf());
			stm.setString(2, exame.getMedico().getCrm());
			stm.setString(3, exame.getExame());
			stm.setString(4, exame.getHipotese());
			stm.setString(5, exame.getRecomendacao());
			stm.setString(6, exame.getSituacao());
			Date data = new Date(exame.getData().getTimeInMillis());
			stm.setDate(7, data);
			Time sqlTime = java.sql.Time.valueOf(exame.getHora());
			stm.setTime(8, sqlTime);
			Date dataRealizacao = new Date(exame.getDataRealizacao().getTimeInMillis());
			stm.setDate(9, dataRealizacao);
			stm.setLong(10, exame.getId());
			
			System.out.println(stm.toString());
			stm.execute();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void remove(Exame exame) {
		String sql = "delete from exame where id_exame=?";

		try(PreparedStatement stm = connection.prepareStatement(sql)){
			stm.setLong(1, exame.getId());
			stm.execute();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Exame> listaExames(){
		List<Exame> exames = new ArrayList<>();
		String sql = "select * from exame";

		try(PreparedStatement stm = connection.prepareStatement(sql)){
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {
				Exame exame = new Exame();
				Paciente paciente = new Paciente();
				Medico medico = new Medico();
				paciente.setCpf(rs.getString("paciente"));
				medico.setCrm(rs.getString("medico"));
				exame.setId(rs.getLong("id_exame"));
				exame.setPaciente(paciente);
				exame.setMedico(medico);
				exame.setExame(rs.getString("exame"));
				exame.setHipotese(rs.getString("hipotese"));
				exame.setRecomendacao(rs.getString("recomendacao"));
				exame.setSituacao(rs.getString("situacao"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data"));
				exame.setData(data);
				exames.add(exame);
			}
		}catch (SQLException e) {
			// TODO: handle exception
		}
		return exames;
	}

	public Exame recuperaExameId(Exame c) {
		String sql = "select * from exame where id_exame=? ";
		Exame exame = null;
		try (PreparedStatement stmt = connection.prepareStatement(sql)){
			stmt.setLong(1,c.getId());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				exame = new Exame();
	            Paciente paciente = new Paciente();
	            paciente.setCpf(rs.getString("paciente"));
	            paciente = (new PacienteDAO()).recuperaPaciente(paciente);
	            
	            Medico medico = new Medico();
	            medico.setCrm(rs.getString("medico"));
	            medico = (new MedicoDAO()).recuperaMedico(medico);
	            
				exame.setId(rs.getLong("id_exame"));
				exame.setPaciente(paciente);
				exame.setMedico(medico);
				exame.setExame(rs.getString("exame"));
				exame.setHipotese(rs.getString("hipotese"));
				exame.setRecomendacao(rs.getString("recomendacao"));
				exame.setSituacao(rs.getString("situacao"));
				
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data"));
				exame.setData(data);
				
				java.util.Date utilDate = null;
				LocalTime localTime = null;
				if (rs.getTime("hora") != null) {
					utilDate = new java.util.Date(rs.getTime("hora").getTime());
			        Instant instant = utilDate.toInstant();
			        localTime = instant.atZone(java.time.ZoneOffset.UTC).toLocalTime();
				}
				exame.setHora(localTime);
				
				Calendar dataRealizacao = null;
				if(rs.getDate("datarealizacao")!= null) {
					dataRealizacao = Calendar.getInstance();
					dataRealizacao.setTime(rs.getDate("datarealizacao"));
				}
				
				exame.setDataRealizacao(dataRealizacao);
			}
		} catch (SQLException e) {

		}	
		return exame;
	}

	public boolean verificaPacienteExame(Exame c) {
		String sql = "select * from exame where exame=? and paciente=? and situacao='Aguardando Exame'";
		Exame exame = null;
		try (PreparedStatement stmt = connection.prepareStatement(sql)){
			stmt.setString(1,c.getExame());
			stmt.setString(2,c.getPaciente().getCpf());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				exame = new Exame();
	            Paciente paciente = new Paciente();
	            paciente.setCpf(rs.getString("paciente"));
	            paciente = (new PacienteDAO()).recuperaPaciente(paciente);
	            
	            Medico medico = new Medico();
	            medico.setCrm(rs.getString("medico"));
	            medico = (new MedicoDAO()).recuperaMedico(medico);
	            
				exame.setId(rs.getLong("id_exame"));
				exame.setPaciente(paciente);
				exame.setMedico(medico);
				exame.setExame(rs.getString("exame"));
				exame.setHipotese(rs.getString("hipotese"));
				exame.setRecomendacao(rs.getString("recomendacao"));
				exame.setSituacao(rs.getString("situacao"));
				
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data"));
				exame.setData(data);
				
				java.util.Date utilDate = null;
				LocalTime localTime = null;
				if (rs.getTime("hora") != null) {
					utilDate = new java.util.Date(rs.getTime("hora").getTime());
			        Instant instant = utilDate.toInstant();
			        localTime = instant.atZone(java.time.ZoneOffset.UTC).toLocalTime();
				}
				exame.setHora(localTime);
				
				Calendar dataRealizacao = null;
				if(rs.getDate("datarealizacao")!= null) {
					dataRealizacao = Calendar.getInstance();
					dataRealizacao.setTime(rs.getDate("datarealizacao"));
				}
				
				exame.setDataRealizacao(dataRealizacao);
				
			}
		} catch (SQLException e) {

		}	
		
		if(exame != null) {
			System.out.println(exame.toString());
			return true;
		}
		return false;
	}
	
	public void pedidoExame(Exame exame) {
		String sql = "insert into exame "
				+ "(paciente, medico, exame, hipotese, recomendacao, situacao, data, dataRealizacao)"
				+ "values (?,?,?,?,?,?,?,?)";

		try(PreparedStatement stm = connection.prepareStatement(sql)){
			stm.setString(1, exame.getPaciente().getCpf());
			stm.setString(2, exame.getMedico().getCrm());
			stm.setString(3, exame.getExame());
			stm.setString(4, exame.getHipotese());
			stm.setString(5, exame.getRecomendacao());
			stm.setString(6, exame.getSituacao());
			Date data = new Date(exame.getData().getTimeInMillis());
			stm.setDate(7, data);
			Date dataPrevista = new Date(exame.getDataRealizacao().getTimeInMillis());
			stm.setDate(8, dataPrevista);
			stm.execute();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void realizacaoExame(Exame exame) {
		String sql = "update exame set situacao=?, datarealizacao=?, hora=?, medicoexame=?"
				+ "where id_exame=?";

		try(PreparedStatement stm = connection.prepareStatement(sql)){
			stm.setString(1, exame.getSituacao());
			Date data = new Date(exame.getDataRealizacao().getTimeInMillis());
			stm.setDate(2, data);
			Time sqlTime = java.sql.Time.valueOf(exame.getHora());
			stm.setTime(3, sqlTime);
			stm.setString(4, exame.getMedicoResitente().getCrm());
			stm.setLong(5, exame.getId());
			stm.execute();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void geraLaudo(Exame exame) {
		String sql = "update exame set situacao=? where id_exame=?";

		try(PreparedStatement stm = connection.prepareStatement(sql)){
			stm.setString(1, exame.getSituacao());
			stm.setLong(2, exame.getId());
			stm.execute();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Exame> listaExamesPorPaciente(String pacienteCpf) {
	    List<Exame> exames = new ArrayList<>();
	    String sql = "select * from exame where paciente = ?";

	    try (PreparedStatement stm = connection.prepareStatement(sql)) {
	        stm.setString(1, pacienteCpf); 
	        ResultSet rs = stm.executeQuery();

	        while (rs.next()) {
	            Exame exame = new Exame();
	            Paciente paciente = new Paciente();
	            Medico medico = new Medico();
	            paciente.setCpf(rs.getString("paciente"));
	            medico.setCrm(rs.getString("medico"));
	            exame.setId(rs.getLong("id_exame"));
	            exame.setPaciente(paciente);
	            exame.setMedico(medico);
	            exame.setExame(rs.getString("exame"));
	            exame.setHipotese(rs.getString("hipotese"));
	            exame.setRecomendacao(rs.getString("recomendacao"));
	            exame.setSituacao(rs.getString("situacao"));
	            Calendar data = Calendar.getInstance();
	            data.setTime(rs.getDate("data"));
	            exame.setData(data);
	            exames.add(exame);
	        }
	    } catch (SQLException e) {
	    	e.printStackTrace();
	    }
	    return exames;
	}
	
	public void cancelaExame() {
	    String sql = "UPDATE exame SET situacao = 'Exame Cancelado' "
	    				+ "WHERE situacao = 'Aguardando Exame' "
	    				+ "AND DATE(datarealizacao) <= now()";

	    try (PreparedStatement stm = connection.prepareStatement(sql)) {
	        stm.execute();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public List<Exame> listaExamesCancelados(){
		List<Exame> exames = new ArrayList<>();
		String sql = "select * from exame where situacao = 'Exame Cancelado'";

		try(PreparedStatement stm = connection.prepareStatement(sql)){
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {
				Exame exame = new Exame();
				Paciente paciente = new Paciente();
				Medico medico = new Medico();
				paciente.setCpf(rs.getString("paciente"));
				paciente = (new PacienteDAO()).recuperaPaciente(paciente);
				medico.setCrm(rs.getString("medico"));
				exame.setId(rs.getLong("id_exame"));
				exame.setPaciente(paciente);
				exame.setMedico(medico);
				exame.setExame(rs.getString("exame"));
				exame.setHipotese(rs.getString("hipotese"));
				exame.setRecomendacao(rs.getString("recomendacao"));
				exame.setSituacao(rs.getString("situacao"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data"));
				exame.setData(data);
				exames.add(exame);
			}
		}catch (SQLException e) {
			// TODO: handle exception
		}
		return exames;
	}
	
}
