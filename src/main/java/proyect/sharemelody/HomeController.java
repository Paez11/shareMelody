package proyect.sharemelody;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController /*implements Initializable*/ {

    App a = new App();
    private static Stage stg;

    @FXML
    private Label profile;
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

    public void goProfile(ActionEvent event) throws IOException {
        a.changeScene("profile.fxml");
    }
}
