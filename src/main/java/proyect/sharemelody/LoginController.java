package proyect.sharemelody;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import proyect.sharemelody.utils.Connect;

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

    private Connection con;
    private PreparedStatement statement;
    private ResultSet rs;
    App a = new App();

    public LoginController(){
        this.con = Connect.getConnect();
    }


    public void userLogIn(ActionEvent event) throws IOException{
        checkLogin();
    }

    public void userSignIn(ActionEvent event) throws IOException {
        checkSignIn();
    }

    private void checkSignIn() throws IOException {
        a.changeScene("register.fxml");
    }

    private void checkLogin() throws IOException{

        String n = name.getText();
        String p = password.getText();

        String sql = "SELECT name,password FROM usuario WHERE name = ? and password = ?";
        try {

            statement = con.prepareStatement(sql);
            statement.setString(1,n);
            statement.setString(2,p);
            rs = statement.executeQuery();

            if(rs.next() && name.getText().toString().equals(n) && password.getText().toString().equals(p)){
                a.changeScene("Home.fxml");
            }
            else if(name.getText().isEmpty() || password.getText().isEmpty()){
                wrongLog.setText("Please enter your data");
            }
            else {
                wrongLog.setText("Wrong password or username");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}