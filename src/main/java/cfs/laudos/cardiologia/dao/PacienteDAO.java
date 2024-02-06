package cfs.laudos.cardiologia.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cfs.laudos.cardiologia.bd.Conexao;
import cfs.laudos.cardiologia.modelo.Paciente;

public class PacienteDAO {
private Connection connection;
	
	public PacienteDAO() {
		this.connection = Conexao.getConnection();
	}
	
	public void adiciona(Paciente paciente) {
		String sql = "insert into paciente "
				+ "(cpf, nome, email, sexo, idade, datanascimento)"
				+ "values (?,?,?,?,?,?)";
		
	try(PreparedStatement stm = connection.prepareStatement(sql)){
			stm.setString(1, paciente.getCpf());
			stm.setString(2, paciente.getNome());
			stm.setString(3, paciente.getEmail());
			stm.setString(4, paciente.getSexo());
			stm.setLong(5, paciente.getIdade());
			Date data = new Date(paciente.getDataNascimento().getTimeInMillis());
			stm.setDate(6, data);
			stm.execute();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void altera(Paciente paciente) {
		String sql = "update paciente set nome=?, email=?, "
				+ "sexo=?, idade=?, datanascimento=? "
				+ "where cpf=?";
		
	try(PreparedStatement stm = connection.prepareStatement(sql)){
		stm.setString(1, paciente.getNome());
		stm.setString(2, paciente.getEmail());
		stm.setString(3, paciente.getSexo());
		stm.setLong(4, paciente.getIdade());
		Date data = new Date(paciente.getDataNascimento().getTimeInMillis());
		stm.setDate(5, data);
		stm.setString(6, paciente.getCpf());
		stm.execute();
			stm.execute();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void remove(Paciente paciente) {
		String sql = "delete from paciente where cpf=?";
		
	try(PreparedStatement stm = connection.prepareStatement(sql)){
			stm.setString(1, paciente.getCpf());
			stm.execute();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Paciente> listaPacientes(){
		List<Paciente> pacientes = new ArrayList<>();
		String sql = "select * from paciente";
		
		try(PreparedStatement stm = connection.prepareStatement(sql)){
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {
				Paciente paciente = new Paciente();
				paciente.setCpf(rs.getString("cpf"));
				paciente.setNome(rs.getString("nome"));
				paciente.setEmail(rs.getString("email"));
				paciente.setSexo(rs.getString("sexo"));
				paciente.setIdade(rs.getLong("idade"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("datanascimento"));
				paciente.setDataNascimento(data);
				pacientes.add(paciente);
			}
		}catch (SQLException e) {
			// TODO: handle exception
		}
		return pacientes;
	}
	
	public Paciente recuperaPaciente(Paciente c) {
		String sql = "select * from paciente where cpf=?";
		Paciente paciente = null;
		try (PreparedStatement stmt = connection.prepareStatement(sql)){
			stmt.setString(1,c.getCpf());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				paciente = new Paciente();
				paciente.setCpf(rs.getString("cpf"));
				paciente.setNome(rs.getString("nome"));
				paciente.setEmail(rs.getString("email"));
				paciente.setSexo(rs.getString("sexo"));
				paciente.setIdade(rs.getLong("idade"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("datanascimento"));
				paciente.setDataNascimento(data);
			}
		} catch (SQLException e) {

		}	
		return paciente;
	}
	
}
