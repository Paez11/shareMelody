package proyect.sharemelody.models.P_Song;

import proyect.sharemelody.interfaces.IDao;
import javafx.collections.FXCollections;
import proyect.sharemelody.models.P_User.User;
import proyect.sharemelody.utils.Connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SongDao implements IDao<Song> {

    public static List<Song> songs = new ArrayList();

    private Connection con;
    private PreparedStatement st;

    private static final String insert="INSERT into canciones (url, name, photo, user, duration, gender)  VALUES (?, ?, ?, ?, ?, ?)";
    private static final String getName="SELECT id_song,url,name,photo,user,views,duration,gender FROM canciones WHERE name=?";
    private static final String getId="SELECT id_song,url,name,photo,user,views,duration,gender FROM canciones WHERE id_song=?";
    private static final String getAll="SELECT id_song,name,artist,duration,gender FROM canciones";
    private static final String delete="DELETE FROM canciones WHERE name=?";
    private static final String update="UPDATE canciones SET url=?, name=?, photo=?, duration=?, gender=? WHERE name=?";
    private static final String updateViews="UPDATE canciones SET views=views+1";

    public SongDao(){
        this.con = Connect.getConnect("conexion.xml");
    }

    @Override
    public boolean insert(Song s) {
        boolean result=false;

        try{
            st = con.prepareStatement(insert);
            st.setString(1,s.getUrl());
            st.setString(2, s.getName());
            st.setString(3,s.getPhoto());
            st.setString(4,s.getUser().getName());
            st.setFloat(5,s.getDuration());
            st.setObject(6,s.getGender());
            st.executeUpdate();
            songs.add(s);
            result=true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

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

    @Override
    public Collection<Song> getAll() {
        songs = new ArrayList();

        try{
            st = con.prepareStatement(getAll);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                Song s = new Song(rs.getInt("id_song"),rs.getString("url"),rs.getString("name"),
                        rs.getString("photo"),(User)rs.getObject("user"),rs.getInt("views"),
                        rs.getFloat("duration"),
                        (Gender) rs.getObject("gender"));

                songs.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return songs;
    }


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
            e.printStackTrace();
        }

        return result;
    }

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
            e.printStackTrace();
        }

        return result;
    }
}
