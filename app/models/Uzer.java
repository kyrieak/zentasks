package models;

import javax.persistence.*;
import play.db.ebean.*;
import com.avaje.ebean.*;

/* The @Entity annotation marks this class as a managed Ebean entity.
 * The Model superclass automatically provides a set of useful JPA helpers. */

@Entity
public class Uzer extends Model {

    @Id
    public Integer uid;
    public String email;
    public String name;
    public String password;
    
    public Uzer(String email, String name, String password) {
      this.email = email;
      this.name = name;
      this.password = password;
    }
    
    public static Uzer create(String email, String name, String password) {
    	Uzer uzer = new Uzer(email, name, password);
    	uzer.save();
    	return uzer;
    }

    public static Finder<String,Uzer> find = new Finder<String,Uzer>(
        String.class, Uzer.class
    );
    
    public static Uzer auth(String email, String password) {
    	return find.where().eq("email", email).eq("password", password).findUnique();
    }
}
