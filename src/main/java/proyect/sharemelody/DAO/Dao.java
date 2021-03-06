package proyect.sharemelody.DAO;

import proyect.sharemelody.utils.Connect;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class Dao {

    protected static Connection con;

    public Dao() {
        this.con = Connect.getConnect("conexion.xml");
    }


    public static Boolean close(){
        Boolean close = false;
        try {
            if(con != null){
                con.close();
                con = null;
                close = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return close;
    }
}
