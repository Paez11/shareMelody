package proyect.sharemelody.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Rlist {
    private User creator;
    private List<Song> songs;

    public Rlist(){
        this(null, new ArrayList<Song>());
    }

    public Rlist(User creator, List<Song> songs) {
        this.creator = creator;
        this.songs = songs;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    @Override
    public String toString() {
        return "Rlist{" +
                "creator=" + creator +
                ", songs=" + songs +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rlist rlist = (Rlist) o;
        return Objects.equals(creator, rlist.creator) && Objects.equals(songs, rlist.songs);
    }

}
