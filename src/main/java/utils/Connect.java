package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
	
	private static Connection con;
	private String file = "conexion.xml";
	private static Connect _newInstance;
	private static DatosConexion dc;

	private Connect(){

	}

	private Connect(String url) {
		dc = new DatosConexion("conexion.xml");
	}

	public static Connection getConnect(String url) {
		if (_newInstance == null) {
			_newInstance = new Connect(url);
		}
		if(con == null){
			try {
				con = DriverManager.getConnection(dc.getServer() + "/" + dc.getDatabase(), dc.getUsername(), dc.getPassword());
			} catch (SQLException e) {
				e.printStackTrace();
				con = null;
			}
		}
		return con;
	}

	public static void disconnect() {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {

			}
		}
	}
	


}
