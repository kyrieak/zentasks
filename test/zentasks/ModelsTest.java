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
		new Uzer("momo@taro.com", "Taro", "kibidango-ooo-").save();
		Uzer taro = Uzer.find.where().eq("email", "momo@taro.com").findUnique();
		assertNotNull(taro);
		assertEquals("Taro", taro.name);
	}
	
	@Test
    public void tryAuthenticateUser() {
        new Uzer("momo@taro.com", "Taro", "kibidango-ooo-").save();
        
        assertNotNull(Uzer.auth("momo@taro.com", "kibidango-ooo-"));
        assertNull(Uzer.auth("momo@taro.com", "badpassword"));
        assertNull(Uzer.auth("momo@tataro.com", "kibidango-ooo-"));
    }

}
