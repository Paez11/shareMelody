package proyect.sharemelody;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import proyect.sharemelody.models.User;
import proyect.sharemelody.DAO.UserDao;
import proyect.sharemelody.utils.Connect;
import proyect.sharemelody.utils.Valid;
import proyect.sharemelody.utils.datosServicio;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterController {

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

    private Connection con = Connect.getConnect("conexion.xml");
    private PreparedStatement statement;
    private ResultSet rs;
    App a = new App();
    UserDao uDao = new UserDao();

    public void userSignIn(ActionEvent event) throws IOException, SQLException {
        checkSignIn();
    }

    public void goLogIn(ActionEvent event) throws IOException{
        LogIn();
    }

    private void LogIn() throws IOException {
        a.changeScene("login.fxml");
    }

    private void checkSignIn() throws IOException, SQLException {

        String n = name.getText();
        String e = email.getText();
        String p = password2.getText();

        p=Valid.sha256(p);
        System.out.println(p);

        User u = new User(n,e,p,"");

        boolean validE = Valid.Email(email, wrongReg2, "invalid email");
        boolean validP = Valid.passwordMatched(password,password2,wrongReg4,"the password doesnt match");

        if(!name.getText().isEmpty()){
            wrongReg1.setText("");
        }

        if(name.getText().isEmpty()){
            wrongReg1.setText("*");
        }else if(checkName(n)){
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
            uDao.insert(u);
            datosServicio.princpialUser=u;
            a.changeScene("Home.fxml");
        }
    }

    private  boolean checkName(String n) throws SQLException {
        boolean result=true;
        String n1 = "";
        String sql = "SELECT name from usuario WHERE name = ?";
        try{
            statement = con.prepareStatement(sql);
            statement.setString(1, n1);
            rs = statement.executeQuery();
            if(n.equals(n1)){
                result=false;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return result;
    }

}
