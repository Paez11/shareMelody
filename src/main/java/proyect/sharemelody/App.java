package proyect.sharemelody;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import proyect.sharemelody.utils.Connect;

import java.io.IOException;
import java.sql.Connection;

public class App extends Application {

    private static Stage stg;
    private Connection con = Connect.getConnect("conexion.xml");
    private Scene scene1, scene2;
    @Override
    public void start(Stage stage) throws IOException {
        stg=stage;
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("login.fxml"));

        scene1 = new Scene(fxmlLoader.load());
        stage.setTitle("Share-Melody");
        stage.setScene(scene1);
        stage.show();
    }

    public void changeScene(String fxml)throws IOException{
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stg.setScene(new Scene(pane));
        stg.resizableProperty();
    }

    /*
    public void changeScene(String fxml)throws IOException{
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stg.getScene().setRoot(pane);
        stg.resizableProperty();

    }
    */
    public void popUpScene(String fxml, Button b)throws IOException{
        stg = new Stage();
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stg.setScene(new Scene(pane));
        stg.initModality(Modality.APPLICATION_MODAL);
        stg.initOwner(b.getScene().getWindow());
        stg.showAndWait();
    }


    public static void main(String[] args) {
        launch();
    }
}