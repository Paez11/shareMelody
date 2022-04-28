package proyect.sharemelody.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
	
	private static Connection con;
	private String file = "conexion.xml";
	private static Connect _newInstance;
	private static DatosConexion dc;


	private Connect() {

		dc = new DatosConexion("conexion.xml");

		try {
			con = DriverManager.getConnection("jdbc:mysql://"+dc.getServer() + "/" + dc.getDatabase(), dc.getUsername(), dc.getPassword());
			
		} catch (SQLException e) {
			e.printStackTrace();
			con = null;
		}
	}

	public static void disconnect() {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {

			}
		}
	}
	
	public static Connection getConnect() {
		if (_newInstance == null) {
			_newInstance = new Connect();
		}
		return con;
	}

}
