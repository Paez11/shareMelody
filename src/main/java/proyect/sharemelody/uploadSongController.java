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

    public void urlFileChooser(ActionEvent event) throws IOException {
        urlText.setEditable(false);
        urlText.setMouseTransparent(true);
        urlText.setFocusTraversable(false);
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("MP3 files",ustFile));
        File f = fc.showOpenDialog(null);
        File destiny = new File("src\\main\\resources\\music\\"+name.getText()+".mp3");
        InputStream in = new FileInputStream(f);
        OutputStream out = new FileOutputStream(destiny);

        if(f != null){
            urlText.setText(destiny.getAbsolutePath());
            byte[] buf = new byte[1024];
            int len;

            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
        }

        in.close();
        out.close();
    }

    public void photoFileChooser(ActionEvent event) throws IOException {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Photo files",pstFile));
        File f = fc.showOpenDialog(null);
        File destiny = new File("src\\main\\resources\\images\\Songs\\"+f.getName());
        InputStream in = new FileInputStream(f);
        OutputStream out = new FileOutputStream(destiny);

        if(f != null){
            photoText.setText(destiny.getAbsolutePath());
            byte[] buf = new byte[1024];
            int len;

            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
        }else {
            photoText.setText("src\\main\\resources\\images\\defaultSong.png");
            byte[] buf = new byte[1024];
            int len;

            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
        }
        in.close();
        out.close();
    }

    public void upload(ActionEvent event){
        String n = name.getText();
        Float d = Float.parseFloat(duration.getText());
        String url = urlText.getText();
        String photo = photoText.getText();
        Gender g = (Gender) genderBox.getValue();

        //upload.setOnAction(event -> Valid.isFloat(duration,d));

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
