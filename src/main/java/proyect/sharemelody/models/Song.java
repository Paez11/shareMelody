package proyect.sharemelody.models;

import java.util.List;

public class Song {

    private int id;
    private String url;
    private String name;
    private String photo;
    private User propietario;
    private List<User> users;
    private int views;
    private float duration;
    private Gender gender;

    public Song(int id, String url, String name, String photo, User user, int views, float duration, Gender gender) {
        this.id = id;
        this.url = url;
        this.name = name;
        this.photo = photo;
        this.propietario = user;
        this.views = views;
        this.duration = duration;
        this.gender = gender;
    }

    public Song(String url, String name, String photo, User user, float duration, Gender gender) {
        this.url = url;
        this.name = name;
        this.photo = photo;
        this.propietario = user;
        this.duration = duration;
        this.gender = gender;
    }

    public Song(String url, String name, String photo, User user, int views, float duration, Gender gender) {
        this.url = url;
        this.name = name;
        this.photo = photo;
        this.propietario = user;
        this.views = views;
        this.duration = duration;
        this.gender = gender;
    }

    public Song() {
        this(0,"","","",null,0,0f,null);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public User getPropietario() {
        return propietario;
    }

    public void setPropietario(User propietario) {
        this.propietario = propietario;
    }

    public int getViews() {
        return this.views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id_s='" + url + '\'' +
                ", name='" + name + '\'' +
                ", duration=" + duration +
                ", gender=" + gender +
                ", user=" + propietario +
                '}';
    }

    @Override
    public boolean equals(Object o){
        boolean result=false;

        if(o!=null){
            if (this == o){
                result=true;
            }else if(this.getClass()==o.getClass()){
                Song tmp = (Song) o;
                if(this.url !=null && this.url.equals(tmp.url)){
                    result=true;
                }
            }
        }

        return result;
    }
}
