package proyect.sharemelody;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import proyect.sharemelody.models.Song;

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
