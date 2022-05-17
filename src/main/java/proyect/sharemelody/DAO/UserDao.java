package proyect.sharemelody.DAO;

import proyect.sharemelody.interfaces.IDao;
import proyect.sharemelody.models.Song;
import proyect.sharemelody.models.User;
import proyect.sharemelody.utils.Connect;
import proyect.sharemelody.utils.Log;

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
    private static final String update="UPDATE usuario SET name=?, email=?, password=?, photo=? WHERE id_user=?";
    private static final String delete="DELETE FROM usuario WHERE name=?";

    public UserDao(){
        super();
    }

    /**
     * Singleton de la clase UserDao
     * @return devuelve la instancia de la clase
     */
    public static UserDao getInstance(){
        if( instance == null){
            instance = new UserDao();
        }
        return instance;
    }


    /**
     * Inserta un usuario en la base de datos
     * @param u Usuario a insertar
     * @return devuelve true si lo ha insertado y false si no se ha podido
     */
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
            Log.severe("No se ha podido insertar el usuario a la base de datos");
        }

        return result;
    }

    /**
     * Devuelve un usuario segun su nombre con todos sus parametros
     * @param s nombre del usuario que se quiere buscar
     * @return devuelve el usuario que se desea encontrar si existe
     */
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
            Log.severe("No se ha podido traer el usuario por su nombre de la base de datos");
        }

        return u;
    }

    /**
     * Devuelve un usuario segun su email con todos sus parametros
     * @param e email del usuario que se quiere buscar
     * @return devuelve el usuario que se desea encontrar si existe
     */
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
            Log.severe("No se ha podido traer el usuario por su email de la base de datos");
        }


        return u;
    }

    /**
     * Devuelve todos los usuarios que se encuentren en la base de datos
     * @return lista de usuarios encontrados
     */
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
            Log.severe("No se ha podido cargar todos los usuarios");
        }

        return userList;
    }

    /**
     * Actualiza los datos de un usuario en la base de datos
     * @param u usuario que se desea actualizar
     * @return devuelve true si se ha conseguido hacer y false si no
     */
    @Override
    public boolean update(User u) {
        boolean result=false;

        try{
            st = con.prepareStatement(update);
            st.setInt(5, u.getId_u());
            st.setString(1,u.getName());
            st.setString(2,u.getEmail());
            st.setString(3,u.getPassword());
            st.setString(4,u.getPhoto());
            st.executeUpdate();
            result=true;
        } catch (SQLException e) {
            Log.severe("No se ha podido actualizar el usuario");
        }

        return result;
    }

    /**
     * Elimina un usuario de la base de datos
     * @param u usuario que se desea eliminar
     * @return devuelve true si se ha conseguido hacer y false si no
     */
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
            Log.severe("No se ha podido eliminar el usuario");
        }

        return result;
    }

    public static  void insertSong(Song s, User u){
        if(s != null && u != null){
            u.getSongs().add(s);
        }
    }
}
