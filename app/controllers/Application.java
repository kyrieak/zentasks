package controllers;

import play.mvc.*;
import views.html.*;
import models.*;

public class Application extends Controller {

   public static Result index() {

      return ok(
            index_layout.render(
                  Project.find.all()
               ));
   }
   
   public static Result login() {
      return ok(login.render());
   }

   public static Result project(Long id) {

      return ok(
            project_layout.render(
                  Project.find.all(),
                  Project.find.where().eq("id", id).findUnique()
               ));
   }
   
   public static class Login {
      public String email;
      public String password;
   }

}