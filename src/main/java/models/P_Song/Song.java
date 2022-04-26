package models.P_Song;

import models.P_User.User;

public class Song {

    private String id_s;
    private String name;
    private float duration;
    private Gender gender;
    private User user;

    public Song(String id_s, String name, float duration, Gender gender, User user) {
        this.id_s = id_s;
        this.name = name;
        this.duration = duration;
        this.gender = gender;
        this.user = user;
    }

    public Song(){
        this("","",0,null,null);
    }

    public String getId_s() {
        return id_s;
    }

    public void setId_s(String id_s) {
        this.id_s = id_s;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id_s='" + id_s + '\'' +
                ", name='" + name + '\'' +
                ", duration=" + duration +
                ", gender=" + gender +
                ", user=" + user +
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
                if(this.id_s!=null && this.id_s.equals(tmp.id_s)){
                    result=true;
                }
            }
        }

        return result;
    }
}
