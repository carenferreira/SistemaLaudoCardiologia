package cfs.laudos.cardiologia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cfs.laudos.cardiologia.bd.Conexao;
import cfs.laudos.cardiologia.modelo.Usuario;

public class UsuarioDAO {
private Connection connection;
	
	public UsuarioDAO() {
		this.connection = Conexao.getConnection();
	}
	
	public void adiciona(Usuario usuario) {
		String sql = "insert into usuario "
				+ "(login, senha, tipo)" 
				+ "values (?, ?, ?)";
		
		try (PreparedStatement stmt = connection.prepareStatement(sql)){
			stmt.setString(1, usuario.getLogin());
			stmt.setString(2, usuario.getSenha());
			stmt.setInt(3, usuario.getTipo());
			stmt.execute();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}		
	}
	
	public void altera(Usuario usuario) {
		String sql = "update usuario set senha=? where login=?";
		
		try (PreparedStatement stmt = connection.prepareStatement(sql)){
			stmt.setString(1, usuario.getSenha());
			stmt.setString(2, usuario.getLogin());
			stmt.execute();

		} catch (SQLException e) {
			
			e.printStackTrace();
		}		
	}
	
	public void remove (String login) {
		String sql = "delete from usuario where login=?";
		
		try (PreparedStatement stmt = connection.prepareStatement(sql)){
			stmt.setString(1, login);
			stmt.execute();
			
		} catch (SQLException e) {
		}		
	}
	
	public Usuario recuperaUsuario (String login) {
		String sql = "select * from usuario where login=?";
		Usuario usuario = null;
		try (PreparedStatement stmt = connection.prepareStatement(sql)){
			stmt.setString(1, login);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				usuario = new Usuario();
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setTipo(rs.getInt("tipo"));
			}
		} catch (SQLException e) {
		
		}
		return usuario;
	}
	
	public Usuario validaCredencial(String login, String senha) {
		String sql = "select * from usuario where login=? and senha=?";
		Usuario usuario = null;
		try (PreparedStatement stmt = connection.prepareStatement(sql)){
			stmt.setString(1, login);
			stmt.setString(2, senha);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				usuario = new Usuario();
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setTipo(rs.getInt("tipo"));
			}
		} catch (SQLException e) {
		}
		
		return usuario;
	}
	
	public List<Usuario> listaUsuarios(){
		List<Usuario> usuarios = new ArrayList<>();
		String sql = "select * from usuario";
		
		try (PreparedStatement stmt = connection.prepareStatement(sql)){
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setTipo(rs.getInt("tipo"));
				usuarios.add(usuario);
			}
		} catch (SQLException e) {
		
		}	
		
		return usuarios;
	}
	
	public int verificaTipo(String login) {
		String sql = "select * from usuario where login=?";
		Usuario usuario = null;
		try (PreparedStatement stmt = connection.prepareStatement(sql)){
			stmt.setString(1, login);
			//stmt.setString(2, senha);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				usuario = new Usuario();
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setTipo(rs.getInt("tipo"));
			}
		} catch (SQLException e) {
		}
		
		return usuario.getTipo();
	}
}
