package proyect.sharemelody.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class Connect {
	
	private static Connection con;
	private String file = "conexion.xml";
	private static Connect _newInstance;
	private static DataConexion dc;
	private static final Logger log = Valid.getLogger();

	private Connect(){

	}

	private Connect(String url) {
		dc = new DataConexion("conexion.xml");
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
				con = null;
				_newInstance=null;
			} catch (SQLException e) {

			}
		}
	}
	


}
