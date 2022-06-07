package proyect.sharemelody;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import proyect.sharemelody.models.User;
import proyect.sharemelody.utils.Log;
import proyect.sharemelody.utils.Valid;

import java.io.IOException;
import java.sql.SQLException;

public class RegisterController extends Controller {

    @FXML
    private Button SignIn;
    @FXML
    private Button LogIn;
    @FXML
    private TextField name;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField password2;
    @FXML
    private Label wrongReg1;
    @FXML
    private Label wrongReg2;
    @FXML
    private Label wrongReg3;
    @FXML
    private Label wrongReg4;


    App a = new App();

    public RegisterController(){
        super();
    }

    /**
     *Llama a la funcion que registra el usuario
     * @throws IOException excepcion en caso de que haya un error en la carga del fxml
     * @throws SQLException excepcion en caso de que haya un error con la conexion a la base de datos
     */
    public void userSignIn() throws IOException, SQLException {
        checkSignIn();
    }

    /**
     * Llama a la funcion para cambiar de escena al logIn
     * @throws IOException excepcion en caso de que haya un error en la carga del fxml
     */
    public void goLogIn() throws IOException{
        LogIn();
    }

    /**
     * cambia de escena al logIn
     * @throws IOException excepcion en caso de que haya un error en la carga del fxml
     */
    private void LogIn() throws IOException {
        a.changeScene("login.fxml");
    }

    /**
     * Registra al usuario segun todos los parametros que haya introducido en la base de datos
     * @throws IOException excepcion en caso de que haya un error en la carga del fxml
     * @throws SQLException excepcion en caso de que haya un error con la conexion a la base de datos
     */
    private void checkSignIn() throws IOException, SQLException {

        String n = name.getText();
        String e = email.getText();
        String p = password2.getText();

        p=Valid.sha256(p);

        User u = new User(n,e,p,"");

        boolean validE = Valid.Email(email, wrongReg2, "invalid email");
        boolean validP = Valid.passwordMatched(password,password2,wrongReg4,"the password doesnt match");

        if(!name.getText().isEmpty()){
            wrongReg1.setText("");
        }

        if(name.getText().isEmpty()){
            wrongReg1.setText("*");
        }else if(name.getText().equals(n)){
            wrongReg1.setText("Name already exist");
        }

        if(email.getText().isEmpty()){
            wrongReg2.setText("*");
        }

        if(password.getText().isEmpty()){
            wrongReg3.setText("*");
        }else if(!password.getText().isEmpty()){
            wrongReg3.setText("");
        }
        if(password2.getText().isEmpty()){
            wrongReg4.setText("*");
        }


        if(validE && validP){
            users.insert(u);
            principalUser=u;
            Log.info("Usuario registrado en la base de datos");
            a.changeScene("Home.fxml");
        }
    }

}
