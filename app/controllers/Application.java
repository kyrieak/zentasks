package controllers;

//import play.*;
import play.mvc.*;
import views.html.*;
import models.*;
import java.util.*;

public class Application extends Controller {

  public static Result index() {
    if (Project.find.all().size() == 0) {

      Uzer taro = Uzer.create("momo@taro.com", "Taro", "kibidango-ooo-");
      Uzer inu = Uzer.create("inu@kibidango.com", "Inu", "1-wt429d317");
      Uzer saru = Uzer.create("saru@kibidango.com", "Saru", "1-wt429d317");
      Uzer oni = Uzer.create("oni@onigashima.com", "Oni", "otakara181");

      Project project = Project.create("Kibidango", "Top Secret", taro);
      Project.create("Treasure", "Top Secret", oni);
      Project.create("Island", "Top Secret", oni);

      Task.create("Recruit Dog", project, taro, new Date());
      Task.create("Recruit Monkey", project, taro, new Date());
      Task.create("Recruit Pheasant", project, taro, new Date());
      Task.create("Obtain food", project, inu, new Date());    

    }

    return ok(
        index.render(
            Project.find.all(),
            Task.find.all()));
  }

}