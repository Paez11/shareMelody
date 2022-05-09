package proyect.sharemelody.DAO;

import proyect.sharemelody.models.Rlist;
import proyect.sharemelody.models.User;
import proyect.sharemelody.utils.Connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RlistDao {

    public static List<Rlist> rep = new ArrayList<>();

    private static Connection con;
    private static PreparedStatement st;

    private static final String insert="INSERT into tienen (id_user, id_song, name_song, name_user)  VALUES (?, ?, ?, ?)";
    private static final String getAll="SELECT id_user, id_song, name_song, name_user FROM tienen";
    private static final String getByUser="SELECT id_user, id_song, name_song, name_user FROM tienen WHERE id_user=?";
    private static final String deleteAll="DELETE FROM tienen";
    private static final String deleteOne="DELETE FROM tienen WHERE id_user=?";
    private static final String update="UPDATE tienen SET id_user=?, id_song=?, name_song=?, name_user=? WHERE id_user=?";


    public RlistDao(){
        this.con = Connect.getConnect("conexion.xml");
    }


    public static List<Rlist> getAll(){

        ResultSet rs;
        try {
            st = con.prepareStatement(getAll);
            rs = st.executeQuery();
            while (rs.next()){
                Rlist l = new Rlist();
                //User u = UserDao.users.get();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rep;
    }

}
