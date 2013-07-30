import play.*;
import play.libs.*;
import com.avaje.ebean.Ebean;
import models.*;
import java.util.*;

public class CustomGlobal extends GlobalSettings {
    @Override
    public void onStart(Application app) {
        // Check if the database is empty
        if (Uzer.find.findRowCount() == 0) {
            Ebean.save((List) Yaml.load("seed.yml"));
        }
    }
}