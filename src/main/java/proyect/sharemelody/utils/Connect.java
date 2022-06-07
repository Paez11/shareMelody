package proyect.sharemelody.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
	
	private static Connection con;
	private static Connect _newInstance;
	private static DataConexion dc;

	private Connect(String url) {
		dc = new DataConexion("conexion.xml");
	}

	/**
	 * Devuelve la instancia del connect si encuentra y existe la base de datos
	 * @param url archivo donde se encuentra los datos de la base de datos
	 * @return devuelve la conexion de la base de datos o null si no esta
	 */
	public static Connection getConnect(String url) {
		if (_newInstance == null) {
			_newInstance = new Connect(url);
		}
		if(con == null){
			try {
				con = DriverManager.getConnection(dc.getServer() + "/" + dc.getDatabase(), dc.getUsername(), dc.getPassword());
				Log.info("Base de datos cargada");
			} catch (SQLException e) {
				Log.severe("No se ha podido conectar a la base de datos");
				con = null;
			}
		}
		return con;
	}

	/**
	 * Desconecta la conexion de la base de datos
	 */
	public static void disconnect() {
		if (con != null) {
			try {
				con.close();
				con = null;
				_newInstance=null;
				Log.info("Base de datos desconectada");
			} catch (SQLException e) {
				Log.severe("No se ha podido desconectar de la base de datos");
			}
		}
	}
	


}
