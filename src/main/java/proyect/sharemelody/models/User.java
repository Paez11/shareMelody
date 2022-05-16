package proyect.sharemelody.models;

import java.util.List;

public class User {

    private int id_u;
    private String name;
    private String email;
    private String password;
    private String photo;
    private List<Song> songs;



    public User(int id_u, String name, String email, String password, String photo) {
        this.id_u = id_u;
        this.name = name;
        this.email = email;
        this.password = password;
        this.photo = photo;
    }

    public User(String name, String email, String password, String photo) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.photo = photo;
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User(int id_u, String name, String email, String password, String photo, List<Song> songs) {
        this.id_u = id_u;
        this.name = name;
        this.email = email;
        this.password = password;
        this.photo = photo;
        this.songs = songs;
    }

    public User(){
        this(0,"","","","",null);
    }

    public int getId_u() {
        return id_u;
    }

    public void setId_u(int id_u) {
        this.id_u = id_u;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    @Override
    public String toString() {
        return "User{" +
                "id_u=" + id_u +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", photo='" + photo + '\'' +
                ", songs=" + songs +
                '}';
    }

    @Override
    public boolean equals(Object o){
        boolean result=false;

        if(o!=null){
            if (this == o){
                result=true;
            }else if(this.getClass()==o.getClass()){
                User tmp = (User) o;
                if(this.id_u==tmp.id_u){
                    result=true;
                }
            }
        }

        return result;
    }
}
