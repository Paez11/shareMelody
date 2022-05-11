package proyect.sharemelody.utils;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class Valid {

    private static boolean passwordMatched(PasswordField p1, PasswordField p2){
        boolean result=false;

        if(p1.getText().equals(p2.getText()) && (!p1.getText().isEmpty()) && !p2.getText().isEmpty()) {
            result = true;
        }
        return result;
    }

    public static boolean passwordMatched(PasswordField p1, PasswordField p2,Label lb, String error){
        boolean result=true;
        String pat="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{4,}$";

        if(!p1.getText().matches(pat)){
            result=false;
            lb.setText(error="Invalid password");
        }

        if(!passwordMatched(p1, p2)) {
            result = false;
            lb.setText(error);
        }
        return result;
    }

    private static boolean Email(TextField e){
        boolean result=false;

        String pat ="^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$";
        if(e.getText().matches(pat) && !e.getText().isEmpty()){
            result=true;
        }
        return result;
    }

    public static boolean Email(TextField e, Label lb, String error){
        boolean result=true;

        if(!Email(e)){
            result=false;
            lb.setText(error);
        }

        return result;
    }

    public static String sha256(String s) {
        String msg = "";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(s.getBytes(StandardCharsets.UTF_8));
            msg = toHexString(hash);
            System.out.println(toHexString(hash));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }

    private static String toHexString(byte[] array){
        StringBuilder sb = new StringBuilder(array.length*2);
        for (byte b: array){
            int value = 0xFF & b;
            String toAppend = Integer.toHexString(value);
            sb.append(toAppend);
        }
        sb.setLength(sb.length()-1);

        return sb.toString();
    }


    public static boolean isFloat(TextField input, float f){
        boolean result=false;

        try{
            f = Float.parseFloat(input.getText());
            result=true;
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        return result;
    }
}
