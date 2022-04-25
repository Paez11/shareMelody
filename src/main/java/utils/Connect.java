package utils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
	
	private static Connection con;
	private String file = "conexion.xml";
	private static Connect _newInstance;
	
	private Connect() {
		//Cargamos los datos de la conexion del XML
		DatosConexion dc = load();
		//Establecemos la conexion
		try {
			con = DriverManager.getConnection(dc.getServer() + "/" + dc.getDatabase(), dc.getUsername(), dc.getPassword());
			
		} catch (SQLException e) {
			e.printStackTrace();
			con = null;
		}
	}
	
	public static Connection getConnect() {
		if (_newInstance == null) {
			_newInstance = new Connect();
		}
		return con;
	}
	
	public DatosConexion load() {
		JAXBContext contexto;
		DatosConexion datosCon = new DatosConexion();
		try {
			contexto = JAXBContext.newInstance(DatosConexion.class);
			Unmarshaller m = contexto.createUnmarshaller();
			DatosConexion newR = (DatosConexion) m.unmarshal(new File(file));
			datosCon = newR;
			
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return datosCon;
	}
}
