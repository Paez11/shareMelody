package proyect.sharemelody;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import proyect.sharemelody.models.Song;
import proyect.sharemelody.models.User;

import java.io.*;
import java.net.URL;
import java.util.*;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import proyect.sharemelody.utils.Log;
import proyect.sharemelody.utils.Valid;


public class HomeController extends Controller implements Initializable{

    App a = new App();
    private static Stage stg;

    private ObservableList<Song> observableSongs;
    private Song song = null;
    private ObservableList<User> observableUsers;
    List<String> pstFile;


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

    //tables
    @FXML
    private TableView mostRecentTable;
    @FXML
    private TableView mostViewTable;
    @FXML
    private TableView yoursTable;
    @FXML
    private TableView likesTable;

    //insert
    @FXML
    private Button add;

    //edit panel
    @FXML
    private Button edit;
    @FXML
    private TextField name, email, photoText;
    @FXML
    private PasswordField p1, p2;
    @FXML
    private ImageView profileImage, buttonImage;
    @FXML
    private Label wrongEmail, wrongPassword;


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



    @FXML
    private VBox pnUsong = null;

    /**
     * Metodo que se inica al cargar la vista
     * @param url la ruta del fxml que pertenece
     * @param resourceBundle recursos del fxml
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        principalUser=this.users.get(principalUser.getName());
        homePane.toFront();
        System.out.println(principalUser);


        //Esta parte corresponde al reproductor de archivos .mp3
        //No reconoce los archivos dentro del directorio por lo cual al ser null lanza una excepcion

        mediaSongs = new ArrayList<File>();
        directory = new File("music");
        files = directory.listFiles();


        if(files != null){
            for (File f : files){
                mediaSongs.add(f);
                System.out.println(f);
            }
        }else {
            Log.info("No se han encontrado archivos .mp3 en el directorio");
        }

        /*
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


    /**
     * Cambia de panel dentro del stack panel del Home.fxml segun el boton que se pulse para traerlo al frente
     * el cual sera el visible por el usuario
     * @param event ActionEvent necesario para describir lo que pasara con la ventana
     */
    public  void handleMenu(ActionEvent event){


        if (event.getSource() == home){
            homePane.toFront();
        }
        if (event.getSource() == yours){
            yoursPane.toFront();
        }
        if (event.getSource() == likes){
            likesPane.toFront();
        }
        if (event.getSource() == profile){
            profilePane.toFront();
            name.setText(principalUser.getName());
            email.setText(principalUser.getEmail());

        }
    }

    /**
     * Metodo que controla el tableview asignado al panel que consistira en mostrar la tabla con las ultimas canciones
     * subidas a la base de datos
     */
    public void mostRecentSongs(){
        for (Song s: songs.getAll()){
            observableSongs.add(s);
        }

        mostRecentTable.setItems(observableSongs);
        

    }

    /**
     * Editara la informacion que el usuario haya manipulado y la actualizara en la base de datos
     * @param event
     */
    public void editUser(ActionEvent event){

        boolean validE = Valid.Email(email, wrongEmail, "invalid email");
        boolean validP = Valid.passwordMatched(p1,p2,wrongPassword,"the password doesnt match");


        if (p1.getText().isEmpty()){
            p1.setText(principalUser.getPassword());
        }
        if (p2.getText().isEmpty()){
            p2.setText(principalUser.getPassword());
        }

        if(validE){
            users.update(principalUser);
            if (users.update(principalUser)==true){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("User edited");
                alert.setHeaderText("");
                alert.setContentText("data successful edited");

                alert.showAndWait();
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("User edited");
                alert.setHeaderText("");
                alert.setContentText("Unable to update data");

                alert.showAndWait();
            }
        }
        System.out.println(users.update(principalUser));
    }

    /**
     * Copia el archivo que se haya seleccionado en el explorador con formato de imagen y lo copia a la carpeta de
     * imagenes dentro de recursos para tener la imagen en el directorio (del perfil del usuario)
     * @param event
     * @throws IOException excepcion en caso de que el archivo encontrado sea null
     */
    public void photoFileChooser(ActionEvent event) throws IOException {

        photoText.setDisable(true);

        pstFile = new ArrayList<>();
        pstFile.add("*.png");
        pstFile.add("*.jpg");
        pstFile.add("*.jpeg");

        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Photo files",pstFile));
        File f = fc.showOpenDialog(null);
        File destiny = new File("src\\main\\resources\\images\\Users\\"+f.getName());
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
            photoText.setText("src\\main\\resources\\images\\"+principalUser.getPhoto());
            byte[] buf = new byte[1024];
            int len;

            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
        }
        Image image = new Image(destiny.toURI().toString());
        profileImage.setImage(image);
        buttonImage.setImage(image);
        in.close();
        out.close();
    }

    /**
     * Comienza a reproducir el archivo .mp3 seleccionado dentro de la carpeta
     */
    public void playMedia(){
        beginTimer();
        mediaPlayer.play();
        Log.info("Archivo .mp3 comenzado");
    }

    /**
     * Pausa la reproduccion del archivo .mp3 que esta en reproduccion en ese momento
     */
    public void pauseMedia(){
        cancelTimer();
        mediaPlayer.pause();
        Log.info("Archivo .mp3 pausado");
    }

    /**
     * Reproduce el archivo .mp3 previo al que se encuentra en reproduccion dentro del directorio
     */
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
            Log.info("Archivo .mp3 anterior cargado");
        }
    }

    /**
     * Reproduce el archivo .mp3 siguiente al que se encuentra en reproduccion dentro del directorio
     */
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
            Log.info("Archivo .mp3 siguiente cargado");
        }
    }

    /**
     * Comienza a transcurrir el tiempo para el archivo .mp3 en reproduccion hasta llegar al maximo de esta
     */
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

    /**
     * Pausa el contador de tiempo del archivo .mp3
     */
    public void cancelTimer(){
        running=false;
        timer.cancel();
    }


    /**
     * Muesta la ventana para subir una cancion a la base de datos
     * @param event
     * @throws IOException
     */
    public void addSong(ActionEvent event)throws IOException{
        a.popUpScene("insertSong.fxml", add);
    }

    /**
     * Llama al metodo de logOut
     * @throws IOException excepcion en caso de no encontrar el fxml
     */
    public void goLogOut() throws IOException {
        logOut();
    }

    /**
     * Devuelve a la ventana de logIn
     * @throws IOException excepcion en caso de no encontrar el fxml
     */
    private void logOut() throws IOException {
        a.changeScene("login.fxml");
    }

}
