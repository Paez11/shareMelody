package proyect.sharemelody;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import proyect.sharemelody.models.P_Song.Song;
import proyect.sharemelody.models.P_User.User;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;


public class HomeController /*implements Initializable*/ {

    App a = new App();
    private static Stage stg;

    private ObservableList<Song> songs;
    private Song song = null;
    private ObservableList<User> users;
    private User user = null;

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


    public void initialize(URL url, ResourceBundle rb){

    }


    public void loadHome(ActionEvent event) throws IOException {
        a.changeScene("Home.fxml");
    }

    public void goYours() throws IOException {
        loadyours();
    }
    private void loadyours () throws IOException {
        a.changeScene("");
    }

    public void loadLikes (ActionEvent event) throws  IOException{
        a.changeScene("");
    }

    public void loadProfile(ActionEvent event) throws IOException {
        a.changeScene("");
    }

    public void goLogOut() throws IOException {
        logOut();
    }

    private void logOut() throws IOException {
        a.changeScene("login.fxml");
    }



}
