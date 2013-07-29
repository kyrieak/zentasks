package zentasks;

import models.*;

import org.junit.*;

import static org.junit.Assert.*;
import play.test.WithApplication;
import static play.test.Helpers.*;

import java.util.*;

/* extending Play's WithApplication provides me with the start() method.
 * this method will automatically clean up the fake application
 * after each test has run. */
public class ModelsTest extends WithApplication {

	@Before
	public void setUp() {
		start(fakeApplication(inMemoryDatabase()));
	}
	
//	Uzer Model: #new #save #find
	@Test
	public void createAndRetrieveUser() {
		new Uzer("momo@taro.com", "Taro", "kibidango-ooo-").save();
		Uzer taro = Uzer.find.where().eq("email", "momo@taro.com").findUnique();
		assertNotNull(taro);
		assertEquals("Taro", taro.name);
	}
	
//	Uzer Model: #auth
	@Test
    public void tryAuthenticateUser() {
        new Uzer("momo@taro.com", "Taro", "kibidango-ooo-").save();
        
        assertNotNull(Uzer.auth("momo@taro.com", "kibidango-ooo-"));
        assertNull(Uzer.auth("momo@taro.com", "badpassword"));
        assertNull(Uzer.auth("momo@tataro.com", "kibidango-ooo-"));
    }

//	Project Model: #create
	@Test
	public void createProject() {		
		Uzer taro = Uzer.create("momo@taro.com", "Taro", "kibidango-ooo-");
		Project project = Project.create("Kibidango", "Top Secret", taro);
		
		List<Project> result = Project.find.all();
		
		assertEquals(1, result.size());
		assertEquals(project.name, result.get(0).name);
	}
	
//  Project Model: #findInvolving
	@Test
	public void findProjectsInvolving() {
		Uzer taro = Uzer.create("momo@taro.com", "Taro", "kibidango-ooo-");
		Uzer oni = Uzer.create("oni@onigashima.com", "Oni", "otakara181");
		
		Project.create("Kibidango", "Top Secret", taro);
		Project.create("Treasure", "Top Secret", oni);
		
		List<Project> results = Project.findInvolving("momo@taro.com");
		
		assertEquals(1, results.size());
		assertEquals("Kibidango", results.get(0).name);
	}

//  Task Model: #create
	@Test
	public void createTask() {
		Uzer taro = Uzer.create("momo@taro.com", "Taro", "kibidango-ooo-");
		Project project = Project.create("Kibidango", "Top Secret", taro);
		Task task = Task.create("Recruit Allies", project.id, taro.uid, new Date());
		
		List<Task> result = Task.find.all();
		Task savedTask = result.get(0);
		
		assertEquals(1, result.size());
		assertEquals(task.title, result.get(0).title);
		assertEquals(taro.name, savedTask.assignedTo());
		assertEquals(project.name, savedTask.projectName());
	}
	
	

}
