package proyect.sharemelody;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import proyect.sharemelody.DAO.UserDao;
import proyect.sharemelody.models.Song;
import proyect.sharemelody.models.User;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class HomeController extends Controller implements Initializable{

    App a = new App();
    private static Stage stg;

    private ObservableList<Song> songs;
    private Song song = null;
    private ObservableList<User> users;
    public static UserDao user = null;


    //Menu

    @FXML
    private Button home;
    @FXML
    private Button yours;
    @FXML
    private Button likes;
    @FXML
    private Button profile;

    //panels
    @FXML
    private TabPane homePane;
    @FXML
    private ScrollPane yoursPane;
    @FXML
    private ScrollPane likesPane;
    @FXML
    private Pane profilePane;

    //insert
    @FXML
    private Button add;

    //edit panel
    @FXML
    private Button edit;
    @FXML
    private TextField name, email;
    @FXML
    private PasswordField p1, p2;


    //Media
    @FXML
    private Button playButton, previousButton, nextButton;
    @FXML
    private Label songProgressN;
    @FXML
    private Slider songProgress, volumen;

    private Media media;
    private MediaPlayer mediaPlayer;

    private File directory;
    private File[] files;

    private ArrayList<File> mediaSongs;

    private int songNumber;

    private Timer timer;
    private TimerTask task;
    private boolean running;


    //Options
    @FXML
    private MenuButton more;
    @FXML
    private MenuItem Logout;



    //CSS
    /*
    private static final String select = "selected";
    Node nh = home;
    Node ny = yours;
    Node nl = likes;
    Node np = profile;
    */

    @FXML
    private VBox pnUsong = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        homePane.toFront();

        //editUser(principalUser);


        /*
        mediaSongs = new ArrayList<File>();
        directory = new File("music");
        files = directory.listFiles();

        if(files != null){
            for (File f : files){
                mediaSongs.add(f);
                System.out.println(f);
            }
        }

        media = new Media(mediaSongs.get(songNumber).toURI().toString());
        mediaPlayer = new MediaPlayer(media);

        songProgressN.setText(mediaSongs.get(songNumber).getName());

        volumen.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                mediaPlayer.setVolume(volumen.getValue()*0.01);
            }
        });
        */
    }


    public  void handleMenu(ActionEvent event){
        /*
        nh.getStyleClass().add(select);
        ny.getStyleClass().add(select);
        nl.getStyleClass().add(select);
        np.getStyleClass().add(select);
        */

        if (event.getSource() == home){
            //home.pseudoClassStateChanged(PseudoClass.getPseudoClass(select), true);
            homePane.setStyle("-fx-background-color: blue");
            homePane.toFront();
        }
        if (event.getSource() == yours){
            yoursPane.setStyle("-fx-background-color: red");
            yoursPane.toFront();

            Node[] nodes = new Node[10];
            for (int i=0; i< nodes.length; i++){
                try{
                    nodes[i] = FXMLLoader.load(getClass().getResource("uSong.fxml"));
                    pnUsong.getChildren().add(nodes[i]);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if (event.getSource() == likes){
            likesPane.setStyle("-fx-background-color: yellow");
            likesPane.toFront();
        }
        if (event.getSource() == profile){
            profilePane.setStyle("-fx-background-color: green");
            profilePane.toFront();
        }
    }

    public void insertSong(){

    }

    public void editUser(User u){

    }


    public void playMedia(){
        beginTimer();
        mediaPlayer.play();
    }

    public void pauseMedia(){
        cancelTimer();
        mediaPlayer.pause();
    }

    public void previousMedia(){
        if(songNumber > 0){
            songNumber--;

            mediaPlayer.stop();

            if(running){
                cancelTimer();
            }

            media = new Media(mediaSongs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            songProgressN.setText(mediaSongs.get(songNumber).getName());

            playMedia();
        }
        else {
            songNumber= mediaSongs.size()-1;

            mediaPlayer.stop();

            if(running){
                cancelTimer();
            }

            media = new Media(mediaSongs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            songProgressN.setText(mediaSongs.get(songNumber).getName());
        }
    }

    public void nextMedia(){
        if(songNumber < mediaSongs.size()-1){
            songNumber++;

            mediaPlayer.stop();

            if(running){
                cancelTimer();
            }

            media = new Media(mediaSongs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            songProgressN.setText(mediaSongs.get(songNumber).getName());

            playMedia();
        }
        else {
            songNumber=0;

            mediaPlayer.stop();

            if(running){
                cancelTimer();
            }

            media = new Media(mediaSongs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            songProgressN.setText(mediaSongs.get(songNumber).getName());
        }
    }

    public void beginTimer(){
        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                running = true;
                double current = mediaPlayer.getCurrentTime().toSeconds();
                double end = media.getDuration().toSeconds();
                songProgress.setBlockIncrement(current/end);

                if(current/end ==1){
                    cancelTimer();
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, 1000);
    }

    public void cancelTimer(){
        running=false;
        timer.cancel();
    }


    public void addSong(ActionEvent event)throws IOException{
        a.popUpScene("insertSong.fxml", add);
    }

    public void goLogOut() throws IOException {
        logOut();
    }


    private void logOut() throws IOException {
        a.changeScene("login.fxml");
    }

}
