package proyect.sharemelody;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import proyect.sharemelody.models.Song;

import java.io.IOException;

public class YoursController {

    App a = new App();
    private static Stage stg;

    private ObservableList<Song> songs;
    private Song song = null;

    @FXML
    private Button add;

    @FXML
    private Button home;
    @FXML
    private Button yours;
    @FXML
    private Button likes;
    @FXML
    private Button profile;

    @FXML
    private MenuButton more;
    @FXML
    private MenuItem Logout;


    public void addSong(ActionEvent event)throws IOException{
        a.popUpScene("song.fxml", add);
    }





    public void loadHome(ActionEvent event) throws IOException {
        home();
    }

    public void loadYours(ActionEvent event) throws IOException {
        yours();
    }

    public void loadLikes (ActionEvent event) throws  IOException{
        likes();
    }

    public void loadProfile(ActionEvent event) throws IOException {
        profile();
    }

    public void goLogOut() throws IOException {
        logOut();
    }

    private void home() throws IOException{
        a.changeScene("HOME.fxml");
    }

    private void yours() throws IOException{
        a.changeScene("yours.fxml");
    }

    private void likes() throws IOException{
        a.changeScene("likes.fxml");
    }

    private void profile() throws IOException{
        a.changeScene("profile.fxml");
    }

    private void logOut() throws IOException {
        a.changeScene("login.fxml");
    }

}
