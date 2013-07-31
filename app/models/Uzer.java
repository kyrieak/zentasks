package models;

import javax.persistence.*;
import play.db.ebean.*;
import com.avaje.ebean.*;
import java.util.*;
/* The @Entity annotation marks this class as a managed Ebean entity.
 * The Model superclass automatically provides a set of useful JPA helpers. */

@Entity
public class Uzer extends Model {

  @Id
  public Integer uid;
  public String email;
  public String name;
  public String password;

  //Finder

  public static Finder<Integer,Uzer> find = new Finder<Integer,Uzer>(
      Integer.class, Uzer.class);

  //Constructors

  public Uzer(String email, String name, String password) {
    this.email = email;
    this.name = name;
    this.password = password;
  }

  //Methods

  //#create
  public static Uzer create(String email, String name, String password) {
    Uzer uzer = new Uzer(email, name, password);
    uzer.save();
    return uzer;
  }

  //#auth
  public static Uzer auth(String email, String password) {
    return find.where().eq("email", email).eq("password", password).findUnique();
  }

  //#getTasks
  public List<Task> getTasks() {
    return Task.find.where().eq("uzer_id", this.uid).findList();
  }
  
  //#findByEmail
  public static Uzer findByEmail(String email) {
    return find.where().eq("email", email).findUnique();
  }
}
