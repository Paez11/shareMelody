package proyect.sharemelody.DAO;

import proyect.sharemelody.utils.Connect;
import proyect.sharemelody.utils.Valid;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

public abstract class Dao {

    protected static Connection con;
    //protected static final Logger Log = Valid.getLogger();

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
