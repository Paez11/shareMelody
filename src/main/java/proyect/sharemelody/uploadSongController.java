package proyect.sharemelody;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import proyect.sharemelody.DAO.SongDao;
import proyect.sharemelody.models.Gender;
import proyect.sharemelody.models.Song;
import proyect.sharemelody.models.User;
import proyect.sharemelody.utils.FileUtil;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class uploadSongController extends Controller implements Initializable {

    ObservableList<Gender> genders = FXCollections.observableArrayList(Gender.values());
    List<String> ustFile;
    List<String> pstFile;

    SongDao sDao = new SongDao();

    @FXML
    private TextField name, duration, urlText, photoText;

    @FXML
    private ChoiceBox genderBox;

    @FXML
    private Button upload, cancel;

    @FXML
    private Label wrong1, wrong2, wrong3, wrong4, wrong5;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ustFile = new ArrayList<>();
        ustFile.add("*.mp3");

        pstFile = new ArrayList<>();
        pstFile.add("*.png");
        pstFile.add("*.jpg");
        pstFile.add("*.jpeg");

        genderBox.setValue(Gender.Pop);
        genderBox.getItems().setAll(genders);

        urlText.setDisable(true);
        photoText.setDisable(true);

    }

    public void urlFileChooser(ActionEvent event){
        try {
            FileUtil.urlFileChooser(ustFile,urlText,name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void photoFileChooser(ActionEvent event){
        try {
            FileUtil.photoFileChooser(pstFile, photoText);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void upload(ActionEvent event){
        String n = name.getText();
        Float d = Float.parseFloat(duration.getText());
        String url = urlText.getText();
        String photo = photoText.getText();
        Gender g = (Gender) genderBox.getValue();

        if (name.getText().isEmpty()){
            wrong1.setText("*");
        }else{
            wrong1.setText("");
        }
        if (duration.getText().isEmpty()){
            wrong3.setText("*");
        }else {
            wrong3.setText("");
        }
        if (urlText.getText().isEmpty()){
            wrong4.setText("*");
        }else {
            wrong4.setText("");
        }

        if (!name.getText().isEmpty() || !duration.getText().isEmpty() || !urlText.getText().isEmpty()){
            User u = principalUser;
            Song s = new Song(url,n,photo,u,d,g);


            sDao.insert(s);
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();
        }


    }

    public void cancel(ActionEvent event) throws Exception {
        Node n = (Node) event.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }




}
