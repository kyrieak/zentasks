package controllers;

import play.mvc.*;
import play.data.Form;
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
      return ok(login_layout.render(Form.form(Login.class)));
   }
   
   public static Result auth() {
      Form<Login> loginForm = Form.form(Login.class).bindFromRequest();
      if (loginForm.hasErrors()) {
         return badRequest(login_layout.render(loginForm));
      } else {
         session().clear();
         session("email", loginForm.get().email);
         return redirect(
            routes.Application.index()
         );
     }
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
      
      public String validate() {
         if (Uzer.auth(email, password) == null) {
           return "Invalid email or password";
         }
         return null;
     }
   }

}