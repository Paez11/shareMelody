package models.P_Song;

import interfaces.IDao;
import javafx.collections.FXCollections;
import models.P_User.User;
import utils.Connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SongDao implements IDao<Song> {

    public static List<Song> songs = FXCollections.observableArrayList();

    private Connection con;
    private PreparedStatement st;

    private static final String insert="INSERT into canciones (id_song, name, artist, duration, gender)  VALUES (?, ?, ?, ?, ?)";
    private static final String selectName="SELECT id_song,name,artist,duration,gender FROM canciones WHERE name=?";
    private static final String getAll="SELECT id_song,name,artist,duration,gender FROM canciones";
    private static final String delete="DELETE FROM canciones WHERE name=?";
    private static final String update="UPDATE canciones SET name=?, duration=?, gender=? WHERE name=?";

    public SongDao(){
        this.con = Connect.getConnect("conexion.xml");
    }

    @Override
    public boolean insert(Song s) {
        boolean result=false;

        try{
            st = con.prepareStatement(insert);
            st.setString(1,s.getId_s());
            st.setString(2, s.getName());
            st.setString(3,s.getUser().getName());
            st.setFloat(4,s.getDuration());
            st.setObject(5,s.getGender());
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
                Song s = new Song(rs.getString("id_song"),rs.getString("name"),
                        (User)rs.getObject("artist"),rs.getFloat("duration"),
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
            st.setString(4, s.getName());
            st.executeUpdate();
            st.setString(1,s.getName());
            st.setFloat(2,s.getDuration());
            st.setObject(3,s.getGender());
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
