package proyect.sharemelody;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;


public class HomeController /*implements Initializable*/ {

    App a = new App();
    private static Stage stg;

    @FXML
    private Label profile;
    @FXML
    private MenuButton more;
    @FXML
    private MenuItem Logout;


    public void showMore() throws FileNotFoundException {

        FileInputStream input = new FileInputStream("@../../images/ic_more.png");
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
        more.setGraphic(imageView);
    }

    /*
    @FXML
    private Button button_logout;


    public HomeController() throws IOException {
        stg.getMaxHeight();
        stg.getMaxWidth();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        button_logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });
    }
    */

    public void goLogOut() throws IOException {
        logOut();
    }

    private void logOut() throws IOException {
        a.changeScene("login.fxml");
    }


    public void goProfile(ActionEvent event) throws IOException {
        a.changeScene("profile.fxml");
    }
}
