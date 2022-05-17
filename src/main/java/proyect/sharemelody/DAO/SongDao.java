package proyect.sharemelody.DAO;

import proyect.sharemelody.interfaces.IDao;
import proyect.sharemelody.models.Gender;
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

public class SongDao extends Dao implements IDao<Song> {

    public static List<Song> songs = new ArrayList();

    private static SongDao instance;
    private PreparedStatement st;

    private static final String insert="INSERT into canciones (url, name, photo, user, duration, gender)  VALUES (?, ?, ?, ?, ?, ?)";
    private static final String getName="SELECT id_song,url,name,photo,user,views,duration,gender FROM canciones WHERE name=?";
    private static final String getId="SELECT id_song,url,name,photo,user,views,duration,gender FROM canciones WHERE id_song=?";
    private static final String getAll="SELECT id_song,url,name,photo,user,duration,gender FROM canciones";
    private static final String delete="DELETE FROM canciones WHERE name=?";
    private static final String update="UPDATE canciones SET url=?, name=?, photo=?, duration=?, gender=? WHERE name=?";
    private static final String updateViews="UPDATE canciones SET views=views+1";

    public SongDao(){
        super();
    }

    /**
     * Singleton de la clase SongDao
     * @return devuelve la instancia de la clase
     */
    public static SongDao getInstance(){
        if( instance == null){
            instance = new SongDao();
        }
        return instance;
    }

    /**
     * Inserta una cancion en la base de datos
     * @param s Cancion a insertar
     * @return devuelve true si lo ha insertado y false si no se ha podido
     */
    @Override
    public boolean insert(Song s) {
        boolean result=false;
        System.out.println(s);
        try{
            st = con.prepareStatement(insert);
            st.setString(1,s.getUrl());
            st.setString(2, s.getName());
            st.setString(3,s.getPhoto());
            st.setString(4,s.getUser().getName());
            st.setFloat(5,s.getDuration());
            st.setObject(6,s.getGender().toString());
            st.executeUpdate();
            songs.add(s);
            result=true;

        } catch (SQLException e) {
            Log.severe("No se ha podido insertar una cancion en la base de datos");
        }

        return result;
    }

    /**
     * Devuelve una cancion segun su nombre con todos sus parametros
     * @param name nombre de la cancion que se quiere buscar
     * @return devuelve la cancion que se desea encontrar si existe
     */
    @Override
    public Song get(String name) {
        Song s = null;
        if(songs !=null && songs.size()>0){
            for(Song aux : songs){
                if(aux.getName().equals(name)){
                    s=aux;
                }
            }
        }

        return s;
    }

    /**
     * Devuelve todas las canciones que se encuentren en la base de datos
     * @return lista de canciones encontradas
     */
    @Override
    public Collection<Song> getAll() {
        songs = new ArrayList();

        try{
            st = con.prepareStatement(getAll);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                Song s = new Song(rs.getInt("id_song"),rs.getString("url"),rs.getString("name"),
                        rs.getString("photo"), (User) rs.getObject("user"),rs.getInt("views"),
                        rs.getFloat("duration"),
                        (Gender) rs.getObject("gender"));

                songs.add(s);
            }
        } catch (SQLException e) {
            Log.severe("No se ha podido cargar todas las canciones");
        }

        return songs;
    }

    /**
     * Actualiza los datos de una cancion en la base de datos
     * @param s cancion que se desea actualizar
     * @return devuelve true si se ha conseguido hacer y false si no
     */
    @Override
    public boolean update(Song s) {
        boolean result=false;

        try{
            st = con.prepareStatement(update);
            st.setString(6, s.getName());
            st.setString(1,s.getUrl());
            st.setString(2,s.getName());
            st.setString(3,s.getPhoto());
            st.setFloat(4,s.getDuration());
            st.setObject(5,s.getGender());
            st.executeUpdate();
            result=true;
        } catch (SQLException e) {
            Log.severe("No se ha podido actualizar la cancion");
        }

        return result;
    }

    /**
     * Elimina una cancion de la base de datos
     * @param s cancion que se desea eliminar
     * @return devuelve true si se ha conseguido hacer y false si no
     */
    @Override
    public boolean delete(Song s) {
        boolean result=false;

        try{
            st = con.prepareStatement(delete);
            st.setString(1, s.getName());
            st.executeUpdate();
            songs.remove(s);
            result=true;
        } catch (SQLException e) {
            Log.severe("No se ha podido eliminar la cancion");
        }

        return result;
    }
}
