package models;

import javax.persistence.*;
import play.db.ebean.*;
import com.avaje.ebean.*;

/* The @Entity annotation marks this class as a managed Ebean entity.
 * The Model superclass automatically provides a set of useful JPA helpers. */

@Entity
public class User extends Model {


  private static final long serialVersionUID = -4436969634220710538L;
    @Id
    @GeneratedValue
    public Integer uid;
    
    public String email;
    public String name;
    public String password;
    
    public User(String email, String name, String password) {
      this.email = email;
      this.name = name;
      this.password = password;
    }

    public static Finder<String,User> find = new Finder<String,User>(
        String.class, User.class
    ); 
}
