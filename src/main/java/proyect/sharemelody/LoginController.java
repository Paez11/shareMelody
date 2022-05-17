package proyect.sharemelody;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import proyect.sharemelody.DAO.UserDao;
import proyect.sharemelody.models.User;
import proyect.sharemelody.utils.Connect;
import proyect.sharemelody.utils.Log;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginController extends Controller {

    @FXML
    private Button LogIn;
    @FXML
    private Button SignIn;
    @FXML
    private TextField name;
    @FXML
    private PasswordField password;
    @FXML
    private Label wrongLog;

    App a = new App();

    public LoginController(){
        super();
    }

    /**
     * Llama al metodo que logea al usuario
     * @param event ActionEvent necesario para describir lo que pasara con la ventana
     * @throws IOException excepcion en caso de no encontrar el fxml
     */
    public void userLogIn(ActionEvent event) throws IOException{
        checkLogin();
    }

    /**
     * Llama al metodo que cambia a la ventana de registro
     * @param event ActionEvent necesario para describir lo que pasara con la ventana
     * @throws IOException excepcion en caso de no encontrar el fxml
     */
    public void userSignIn(ActionEvent event) throws IOException {
        goSignIn();
    }

    /**
     * Cambia de escena a la de registro
     * @throws IOException excepcion en caso de no encontrar el fxml
     */
    private void goSignIn() throws IOException {
        a.changeScene("register.fxml");
    }

    /**
     * Logea al usuario en la aplicacion
     * @throws IOException excepcion en caso de no encontrar el fxml
     */
    private void checkLogin() throws IOException{

        String n = name.getText();
        String p = password.getText();
        String e = name.getText();

        //p= Valid.sha256(p);

        User aux = new User(n,e,p);
        users.get(n);
        if((name.getText().equals(n) || name.getText().equals(e))  && password.getText().equals(p)){
            principalUser=aux;
            System.out.println(principalUser);
            Log.info("Credenciales validas");
            a.changeScene("Home.fxml");
        }
        else if(name.getText().isEmpty() || password.getText().isEmpty()){
            wrongLog.setText("Please enter your data");
        }
        else {
            wrongLog.setText("Wrong password or username/email");
        }
    }


}