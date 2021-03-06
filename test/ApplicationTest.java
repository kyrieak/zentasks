//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.codehaus.jackson.JsonNode;
import org.junit.*;

import play.mvc.*;
import play.test.*;
//import play.data.DynamicForm;
//import play.data.validation.ValidationError;
//import play.data.validation.Constraints.RequiredValidator;
//import play.i18n.Lang;
//import play.libs.F;
//import play.libs.F.*;
import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;
import models.*;

/**
 *
 * Simple (JUnit) tests that can call all parts of a play app.
 * If you are interested in mocking a whole application, see the wiki for more details.
 *
 */

public class ApplicationTest extends WithApplication {
  
  @Before
  public void setUp() {
    start(fakeApplication(inMemoryDatabase()));
  }

  @Test 
  public void simpleCheck() {
    int a = 1 + 1;
    assertThat(a).isEqualTo(2);
  }

  @Test
  public void renderTemplate() {
    Content html = views.html.index_layout.render(Project.find.all(), new Uzer("sam@i.am", "SamIam", "greeneggs&ham"));
    assertThat(contentType(html)).isEqualTo("text/html");
    assertThat(contentAsString(html)).contains("Dashboard");
  }


}