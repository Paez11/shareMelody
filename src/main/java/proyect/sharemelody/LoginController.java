package proyect.sharemelody;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import utils.Connect;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {

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

    private Connection con = Connect.getConnect("conexion.xml");
    private PreparedStatement statement;
    private ResultSet rs;
    App a = new App();

    public void userLogIn(ActionEvent event) throws IOException{
        checkLogin();
    }

    public void userSignIn(ActionEvent event) throws IOException {
        goSignIn();
    }

    private void goSignIn() throws IOException {
        a.changeScene("register.fxml");
    }

    private void checkLogin() throws IOException{

        String n = name.getText();
        String p = password.getText();
        String e = name.getText();

        String sql = "SELECT name,password FROM usuario WHERE (name = ? or email = ?) and password = ?";
        try {

            statement = con.prepareStatement(sql);
            statement.setString(1,n);
            statement.setString(2,e);
            statement.setString(3,p);
            rs = statement.executeQuery();

            if(rs.next() && (name.getText().toString().equals(n) || name.getText().equals(e))  && password.getText().toString().equals(p)){
                a.changeScene("Home.fxml");
            }
            else if(name.getText().isEmpty() || password.getText().isEmpty()){
                wrongLog.setText("Please enter your data");
            }
            else {
                wrongLog.setText("Wrong password or username/email");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }


}