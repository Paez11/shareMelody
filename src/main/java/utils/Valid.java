package utils;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Valid {

    public static boolean passwordMatched(PasswordField p1, PasswordField p2){
        boolean result=false;

        if(p1.getText().equals(p2.getText()) && (!p1.getText().isEmpty()) && !p2.getText().isEmpty()) {
            result = true;
        }
        return result;
    }

    public static boolean passwordMatched(PasswordField p1, PasswordField p2,Label lb, String error){
        boolean result=true;

        if(!passwordMatched(p1, p2)) {
            result = false;
            lb.setText(error);
        }
        return result;
    }

    public  static boolean Email(TextField e){
        boolean result=false;

        String pat ="^[\\w-]+(\\.[\\w-]+)@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)(\\.[A-Za-z]{2,})$";
        if(e.getText().matches(pat) && !e.getText().isEmpty()){
            result=true;
        }
        return result;
    }

    public static boolean Email(TextField e, Label lb, String error){
        boolean result=true;

        if(Email(e)){
            result=false;
            lb.setText(error);
        }

        return result;
    }
}
