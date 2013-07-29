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

public class ProjectModelTest extends WithApplication {
	
	@Before
	public void setUp() {
		start(fakeApplication(inMemoryDatabase()));
		new Uzer("momo@taro.com", "Taro", "kibidango-ooo-").save();
		new Uzer("oni@onigashima.com", "Oni", "otakara181").save();
	}
	
//	Project#create
	@Test
	public void createProject() {	
		Uzer taro = Uzer.findByEmail("momo@taro.com");
		Project project = Project.create("Kibidango", "Top Secret", taro);
		
		List<Project> allProjects = Project.find.all();
		
		assertEquals(1, allProjects.size());
		assertEquals(project.name, allProjects.get(0).name);
	}
	
//  Project#findInvolving
	@Test
	public void findByUid() {
		Uzer taro = Uzer.findByEmail("momo@taro.com");
		Uzer oni = Uzer.findByEmail("oni@onigashima.com");
		
		new Project("Kibidango", "Top Secret", taro).save();
		new Project("Treasure", "Top Secret", oni).save();
		new Project("Island", "Top Secret", oni).save();
		
		List<Project> taroProjects = Project.findByUid(taro.uid);
		List<Project> oniProjects = Project.findByUid(oni.uid);
		
		assertEquals(1, taroProjects.size());
		assertEquals(2, oniProjects.size());
		assertEquals("Kibidango", taroProjects.get(0).name);
	}
	
}
