package conectionjdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection {
	
	private static String url = "jdbc:postgresql://localhost:5432/jdbc";
	private static String password = "password";
	private static String user = "user";
	private static Connection connection = null;
	

	static {
		connect();
	}
	
	
	public SingleConnection() {
		connect();
	}
	private static void connect() {
		try {
			if (connection == null) {
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(url, user, password);
				connection.setAutoCommit(false);
				System.out.print("CONECTOU");
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	
	public static Connection getConnection() {
		return connection;
	}
	
}
