package proyect.sharemelody.utils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.io.Serializable;

@XmlRootElement(name = "conexion")
@XmlAccessorType(XmlAccessType.FIELD)
public class DatosConexion implements Serializable {
	
	private String server;
	private String database;
	private String username;
	private String password;

	public DatosConexion() {
		this.server = "";
		this.database = "";
		this.username = "";
		this.password = "";
	}

	public DatosConexion(String url) {
		load(url);
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void load(String url){
		DatosConexion aux;
		try {
			JAXBContext context = JAXBContext.newInstance(DatosConexion.class);
			Unmarshaller um = context.createUnmarshaller();
			aux = (DatosConexion) um.unmarshal(new File(url));
			this.database = aux.database;
			this.server = aux.server;
			this.username = aux.username;
			this.password = aux.password;
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "DatosConexion{" +
				"server='" + server + '\'' +
				", database='" + database + '\'' +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				'}';
	}
}
