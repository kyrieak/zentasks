package zentasks;

import models.*;
import org.junit.*;
import static org.junit.Assert.*;
import play.test.WithApplication;
import static play.test.Helpers.*;

/* extending Play's WithApplication provides me with the start() method.
 * this method will automatically clean up the fake application
 * after each test has run. */
public class ModelsTest extends WithApplication {

	@Before
	public void setUp() {
		start(fakeApplication(inMemoryDatabase()));
	}
	
	@Test
	public void createAndRetrieveUser() {
		new User("momo@taro.com", "Taro", "kibidango-ooo-").save();
		User taro = User.find.where().eq("email", "momo@taro.com").findUnique();
		assertNotNull(taro);
	}

}
