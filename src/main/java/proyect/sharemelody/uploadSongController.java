package proyect.sharemelody;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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
    private TextField name;

    @FXML
    private ChoiceBox genderBox;

    @FXML
    private TextField duration;


    @FXML
    private TextField urlText;


    @FXML
    private TextField photoText;

    @FXML
    private Button upload;
    @FXML
    private Button cancel;



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
    }

    public void urlFileChooser(ActionEvent event) throws IOException {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("MP3 files",ustFile));
        File f = fc.showOpenDialog(null);
        File destiny = new File("src\\main\\resources\\music\\"+name.getText()+".mp3");
        InputStream in = new FileInputStream(f);
        OutputStream out = new FileOutputStream(destiny);

        if(f != null){
            urlText.setText(f.getAbsolutePath());
            byte[] buf = new byte[1024];
            int len;

            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
        }
    }

    public void photoFileChooser(ActionEvent event) throws IOException {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Photo files",pstFile));
        File f = fc.showOpenDialog(null);
        File destiny = new File("src\\main\\resources\\music\\SongPhoto"+f.getName());
        InputStream in = new FileInputStream(f);
        OutputStream out = new FileOutputStream(destiny);

        if(f != null){
            photoText.setText(f.getAbsolutePath());
            byte[] buf = new byte[1024];
            int len;

            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
        }
    }

    public void upload(){
        String n = name.getText();
        Float d = Float.parseFloat(duration.getText());
        String url = urlText.getText();
        String photo = photoText.getText();
        Gender g = (Gender) genderBox.getValue();
        System.out.println(g);

        User u = principalUser;
        Song s = new Song(url,n,photo,u,d,g);
        System.out.println(s);

        //upload.setOnAction(event -> Valid.isFloat(duration,d));
        sDao.insert(s);

    }

    public void cancel(ActionEvent event) throws Exception {
        Node n = (Node) event.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }




}
