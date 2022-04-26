package proyect.sharemelody;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import models.P_Song.Song;
import models.P_User.User;

public class SongController {

    @FXML
    private Label songName;

    @FXML
    private Label user;

    public void setData(Song song){
        songName.setText(song.getName());
        user.setText(song.getUser().getName());
    }
}
