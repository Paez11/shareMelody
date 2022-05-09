package proyect.sharemelody;

import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import proyect.sharemelody.models.Song;
import proyect.sharemelody.models.User;

import java.io.IOException;
import java.net.URL;
import java.security.PrivateKey;
import java.util.ResourceBundle;


public class HomeController /*implements Initializable*/ {

    App a = new App();
    private static Stage stg;

    private ObservableList<Song> songs;
    private Song song = null;
    private ObservableList<User> users;
    private User user = null;


    //Menu

    @FXML
    private Button home;
    @FXML
    private Button yours;
    @FXML
    private Button likes;
    @FXML
    private Button profile;

    @FXML
    private TabPane homePane;
    @FXML
    private ScrollPane yoursPane;
    @FXML
    private ScrollPane likesPane;
    @FXML
    private Pane profilePane;

    //insert
    @FXML
    private Button add;

    //Options
    @FXML
    private MenuButton more;
    @FXML
    private MenuItem Logout;

    @FXML
    private VBox pnUsong = null;



    //CSS
    /*
    private static final String select = "selected";
    Node nh = home;
    Node ny = yours;
    Node nl = likes;
    Node np = profile;
    */

    /*
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Node[] nodes = new Node[10];
        for (int i=0; i< nodes.length; i++){
            try{
                nodes[i] = FXMLLoader.load(getClass().getResource("uSong.fxml"));
                pnUsong.getChildren().add(nodes[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    */

    public  void handleMenu(ActionEvent event){
        /*
        nh.getStyleClass().add(select);
        ny.getStyleClass().add(select);
        nl.getStyleClass().add(select);
        np.getStyleClass().add(select);
        */

        if (event.getSource() == home){
            //home.pseudoClassStateChanged(PseudoClass.getPseudoClass(select), true);
            homePane.setStyle("-fx-background-color: blue");
            homePane.toFront();
        }
        if (event.getSource() == yours){
            yoursPane.setStyle("-fx-background-color: red");
            yoursPane.toFront();
        }
        if (event.getSource() == likes){
            likesPane.setStyle("-fx-background-color: yellow");
            likesPane.toFront();
        }
        if (event.getSource() == profile){
            profilePane.setStyle("-fx-background-color: green");
            profilePane.toFront();
        }
    }


    public void addSong(ActionEvent event)throws IOException{
        a.popUpScene("insertSong.fxml", add);
    }

    public void goLogOut() throws IOException {
        logOut();
    }


    private void logOut() throws IOException {
        a.changeScene("login.fxml");
    }

}
