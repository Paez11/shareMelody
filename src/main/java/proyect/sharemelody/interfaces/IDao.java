package proyect.sharemelody.interfaces;

import java.util.Collection;

public interface IDao<T> {

    boolean insert(T ob);
    T get (String s);
    Collection<T> getAll();
    boolean  update (T ob);
    boolean delete (T ob);
}
