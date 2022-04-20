package proyect.sharemelody;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController {

    public LoginController(){

    }

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

    public void userLogIn(ActionEvent event) throws IOException{
        checkLogin();
    }

    private void checkLogin() throws IOException{
        App a = new App();

        if(name.getText().toString().equals("") && password.getText().toString().equals("")){
            a.changeScene("Home.fxml");
        }else if(name.getText().isEmpty() || password.getText().isEmpty()){
            wrongLog.setText("Please enter your data");
        }else {
            wrongLog.setText("Wrong password or username");
        }

    }

}