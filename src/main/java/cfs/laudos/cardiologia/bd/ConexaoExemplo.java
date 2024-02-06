package cfs.laudos.cardiologia.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoExemplo {
	public static Connection getConnection() {
		try {
			Class.forName("org.postgresql.Driver");
			return DriverManager.getConnection
					("jdbc:postgresql://localhost/nome-do-banco", 
							"banco", "senha123");
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
}
