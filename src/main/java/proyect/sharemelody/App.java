package proyect.sharemelody;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import proyect.sharemelody.utils.Connect;
import proyect.sharemelody.utils.Log;

import java.io.IOException;
import java.sql.Connection;

public class App extends Application {

    private static Stage stg;
    private Connection con = Connect.getConnect("conexion.xml");
    private Scene scene1, scene2;

    /**
     * Carga la primera escena del fxml al iniciar la aplicacion
     * @param stage La escena que se quiere cargar primero
     * @throws IOException en caso de no encontrar la escena
     */
    @Override
    public void start(Stage stage) throws IOException {
        stg=stage;
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Home.fxml"));

        scene1 = new Scene(fxmlLoader.load());
        stage.setTitle("Share-Melody");
        stage.setScene(scene1);
        stage.show();

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                Platform.exit();
                System.exit(0);
            }
        });
        Log.info("Vistas cargadas");
    }


    /**
     * Cambia de escena segun la que recibe y reescala la aplicacion a las dimensiones de esta
     * @param fxml Escena fxml que va a recibir para cambiar
     * @throws IOException excepcion en caso de no encontrar la escena
     */
    public void changeScene(String fxml){
        Parent pane = null;
        try {
            pane = FXMLLoader.load(getClass().getResource(fxml));
        } catch (IOException e) {
            Log.severe("No se ha podido cargar el archivo fxml");
        }
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

    /**
     * Muestra una ventana nueva sin cerrar la que el usuario tenga en ese momento
     * @param fxml Escena fxml que va a recibir para mostrar
     * @param b boton por el que accionandolo saldra la ventana
     * @throws IOException excepcion en caso de no encontrar el fxml
     */
    public void popUpScene(String fxml, Button b){
        stg = new Stage();
        Parent pane = null;
        try {
            pane = FXMLLoader.load(getClass().getResource(fxml));
            stg.setScene(new Scene(pane));
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.initOwner(b.getScene().getWindow());
            Log.info("Escena cargada");
            stg.showAndWait();
        } catch (IOException e) {
            Log.severe("No se ha podido cargar el archivo fxml");
        }
    }

    /**
     * Cierra una ventana
     * @param event ActionEvent necesario para describir lo que pasara con la ventana
     */
    public void cancel(ActionEvent event){
        Node n = (Node) event.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }

    /**
     * Conector para el main principal
     * @param args
     */
    public static void main(String[] args) {
        launch();
        Connect.disconnect();
    }
}