package proyect.sharemelody;

import proyect.sharemelody.DAO.SongDao;
import proyect.sharemelody.DAO.UserDao;
import proyect.sharemelody.models.User;

public class Controller {
    protected static User principalUser;
    protected static UserDao users;
    protected static SongDao songs;

    public Controller(){
        principalUser= new User();
        users = UserDao.getInstance();
        songs = SongDao.getInstance();
    }
}
