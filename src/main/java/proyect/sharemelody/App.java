package proyect.sharemelody;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.Connect;

import java.io.IOException;
import java.sql.Connection;

public class App extends Application {

    private static Stage stg;
    private Connection con = Connect.getConnect("conexion.xml");

    @Override
    public void start(Stage stage) throws IOException {
        stg=stage;
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Share-Melody");
        stage.setScene(scene);
        stage.show();
    }

    public void changeScene(String fxml)throws IOException{
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stg.getScene().setRoot(pane);
    }

    public static void main(String[] args) {
        launch();
    }
}