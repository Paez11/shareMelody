package proyect.sharemelody.DAO;

import proyect.sharemelody.interfaces.IDao;
import proyect.sharemelody.models.Song;
import proyect.sharemelody.models.User;
import proyect.sharemelody.utils.Connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDao extends Dao implements IDao<User> {

    public static List<User> users = new ArrayList();

    private static UserDao instance;

    private PreparedStatement st;

    private static final String insert="INSERT into usuario (name, email, password)  VALUES (?, ?, ?)";
    private static final String getByName="SELECT id_user,name,email,password,photo FROM usuario WHERE name=?";
    private static final String getAll= "SELECT id,name,email,password FROM usuario";
    private static final String getByEmail="SELECT id_user,name,email,password FROM usuario WHERE email=?";
    private static final String update="UPDATE usuario SET name=?,email=?,password=?, photo=? WHERE name=?";
    private static final String delete="DELETE FROM usuario WHERE name=?";

    public UserDao(){
        super();
    }

    public static UserDao getInstance(){
        if( instance == null){
            instance = new UserDao();
        }
        return instance;
    }


    @Override
    public boolean insert(User u) {
        boolean result=false;


        try{
            st = con.prepareStatement(insert);
            st.setString(1,u.getName());
            st.setString(2,u.getEmail());
            st.setString(3,u.getPassword());
            st.executeUpdate();
            users.add(u);
            result=true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public User get(String s) {
        User u = new User();

        try{
            st = con.prepareStatement(getByName);
            st.setString(1,s);
            ResultSet rs = st.executeQuery();
            if (rs.next()){
                u.setId_u(rs.getInt("id_user"));
                u.setName(rs.getString("name"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setPhoto(rs.getString("photo"));

                SongDao sDao = new SongDao();
                //u.setSongs((List<Song>) sDao.getAll());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return u;
    }

    public User getByEmail(String e){
        User u=new User();

        try{
            st = con.prepareStatement(getByEmail);
            st.setString(1,e);
            ResultSet rs = st.executeQuery();
            rs.next();
            u.setId_u(rs.getInt("id_user"));
            u.setName(rs.getString("nombre"));
            u.setEmail(rs.getString("email"));
            u.setPassword(rs.getString("password"));
            u.setPhoto(rs.getString("photo"));

            SongDao sDao = new SongDao();
            u.setSongs((List<Song>) sDao.getAll());

        } catch (SQLException ex) {
            ex.printStackTrace();
        }


        return u;
    }

    @Override
    public Collection<User> getAll() {
        List<User> userList= new ArrayList();
        try{
            st = con.prepareStatement(getAll);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                User u = new User();
                u.setId_u(rs.getInt("id_user"));
                u.setEmail(rs.getString("email"));
                u.setName(rs.getString("name"));
                u.setPassword(rs.getString("password"));
                u.setPhoto(rs.getString("photo"));
                userList.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }

    @Override
    public boolean update(User u) {
        boolean result=false;

        try{
            st = con.prepareStatement(update);
            st.setString(1,u.getName());
            st.setString(2,u.getEmail());
            st.setString(3,u.getPassword());
            st.setString(4,u.getPhoto());
            st.setString(5, u.getName());
            st.executeUpdate();
            result=true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean delete(User u) {
        boolean result=false;

        try{
            st = con.prepareStatement(delete);
            st.setString(1, u.getName());
            st.executeUpdate();
            users.remove(u);
            result=true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static  void insertSong(Song s, User u){
        if(s != null && u != null){
            u.getSongs().add(s);
        }
    }
}
