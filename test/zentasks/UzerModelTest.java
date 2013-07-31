package zentasks;

import models.*;

import org.junit.*;

import static org.junit.Assert.*;
import play.test.WithApplication;
import static play.test.Helpers.*;

/* extending Play's WithApplication provides me with the start() method.
 * this method will automatically clean up the fake application
 * after each test has run. */
public class UzerModelTest extends WithApplication {

	@Before
	public void setUp() {
		start(fakeApplication(inMemoryDatabase()));
	}

// Uzer#findByEmail
	@Test
	public void findByEmail() {
		new Uzer("momo@taro.com", "Taro", "kibidango-ooo-").save();
		Uzer taro = Uzer.findByEmail("momo@taro.com");
		
		assertNotNull(taro);
		assertEquals("Taro", taro.name);
	}
	
// Uzer#create	
	@Test
	public void createUzer() {
	  Uzer taro = Uzer.create("momo@taro.com", "Taro", "kibidango-ooo-");
		assertNotNull(Uzer.find.where().eq("email", taro.email).findUnique());
		assertEquals(1, Uzer.find.all().size());
	}
	
	
// Uzer#auth
	@Test
  public void uzerAuth() {
	  Uzer taro = Uzer.create("momo@taro.com", "Taro", "kibidango-ooo-");

	  assertNotNull(Uzer.auth(taro.email, "kibidango-ooo-"));
	  assertNull(Uzer.auth(taro.email, "badpassword"));
	  assertNull(Uzer.auth("momo@momo.com", "kibidango-ooo-"));
	}
}
