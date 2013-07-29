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
public class TaskModelTest extends WithApplication {

	@Before
	public void setUp() {
		start(fakeApplication(inMemoryDatabase()));
		
		Uzer taro = Uzer.create("momo@taro.com", "Taro", "kibidango-ooo-");
		new Project("Kibidango", "Top Secret", taro).save();
	}

//  Task#create
	@Test
	public void createTask() {
		Uzer taro = Uzer.findByEmail("momo@taro.com");
		Project project = Project.findByUid(taro.uid).get(0);
		
		Task task = Task.create("Recruit Allies", project.id, taro.uid, new Date());
		
		List<Task> result = Task.find.all();
		Task firstTask = result.get(0);
		
		assertEquals(1, result.size());
		assertEquals(task.title, firstTask.title);
	}

//  Task#getUzer
	@Test
	public void getUzer() {
		Uzer taro = Uzer.findByEmail("momo@taro.com");
		Project project = Project.findByUid(taro.uid).get(0);
		
		new Task("Recruit Allies", project.id, taro.uid).save();
		
		List<Task> result = Task.find.all();
		Task firstTask = result.get(0);
		
		assertEquals(taro.uid, firstTask.getUzer().uid);
		assertEquals(taro.email, firstTask.getUzer().email);
	}
	
//  Task#getUzer
	@Test
	public void getProject() {
		Uzer taro = Uzer.findByEmail("momo@taro.com");
		Project project = Project.findByUid(taro.uid).get(0);
		
		new Task("Recruit Allies", project.id, taro.uid).save();
		
		List<Task> result = Task.find.all();
		Task firstTask = result.get(0);
		
		assertEquals(project.id, firstTask.getProject().id);
		assertEquals(project.name, firstTask.getProject().name);
	}
	
	
	
	
	
	
	
	

}