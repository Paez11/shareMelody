package proyect.sharemelody;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import models.P_User.User;
import models.P_User.UserDao;
import utils.Connect;
import utils.Valid;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterController {

    @FXML
    private Button SignIn;
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

    private Connection con = Connect.getConnect("conexion.xml");
    private PreparedStatement statement;
    private PreparedStatement statement2;
    private ResultSet rs;
    App a = new App();

    public void userSignIn(ActionEvent event) throws IOException, SQLException {
        checkSignIn();
    }

    private void checkSignIn() throws IOException, SQLException {

        UserDao uDao = new UserDao();

        String n = name.getText();
        String e = email.getText();
        String p = password2.getText();

        User u = new User(n,e,p);

        boolean validE = Valid.Email(email, wrongReg2, "invalid email");
        boolean validP = Valid.passwordMatched(password,password2,wrongReg4,"the password doesnt match");

        if(!name.getText().isEmpty()){
            wrongReg1.setText("");
        }

        if(name.getText().isEmpty()){
            wrongReg1.setText("*");
        }else if(!checkName(n)){
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


        if(validE && validP && checkName(n)){
            uDao.insert(u);
            a.changeScene("Home.fxml");
        }
    }

    private  boolean checkName(String n) throws SQLException {
        boolean result=false;
        String sql = "SELECT name from usuario WHERE name = ?";

        statement2 = con.prepareStatement(sql);
        statement2.setString(1, n);
        rs = statement2.executeQuery();

        if(name.getText().equals(n)){
            result=true;
        }

        return result;
    }

}
