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

  //Task#create
  @Test
  public void createTask() {
    Uzer taro = Uzer.findByEmail("momo@taro.com");
    Project project = Project.findByUid(taro.uid).get(0);

    Task task = Task.create("Recruit Dog", project, taro, new Date());

    List<Task> result = Task.find.all();
    Task firstTask = result.get(0);

    assertEquals(1, result.size());
    assertEquals(task.title, firstTask.title);
  }

  //Task#getUzer
  @Test
  public void getUzer() {
    Uzer taro = Uzer.findByEmail("momo@taro.com");
    Project project = Project.findByUid(taro.uid).get(0);

    Task task = Task.create("Recruit Dog", project, taro, new Date());

    List<Task> result = Task.find.all();
    Task firstTask = result.get(0);

    System.out.println(task.getUzer().name);
    assertEquals(task.getUzer().name, firstTask.getUzer().name);
    assertEquals(taro.email, firstTask.getUzer().email);
  }

  //Task#getProject
  @Test
  public void getProject() {
    Uzer taro = Uzer.findByEmail("momo@taro.com");
    Project project = Project.findByUid(taro.uid).get(0);

    new Task("Recruit Dog", project, taro).save();

    List<Task> result = Task.find.all();
    Task firstTask = result.get(0);

    assertEquals(project.id, firstTask.getProject().id);
    assertEquals(project.name, firstTask.getProject().name);
  }

  //Task#findAllFor
  @Test
  public void findAllFor() {
    Uzer taro = Uzer.findByEmail("momo@taro.com");
    Uzer inu = Uzer.create("inu@kibidango.com", "Inu", "1-wt429d317");
    Uzer saru = Uzer.create("saru@kibidango.com", "Saru", "1-wt429d317");
    Project project = Project.findByUid(taro.uid).get(0);

    new Task("Recruit Dog", project, taro).save();
    new Task("Recruit Monkey", project, taro).save();
    new Task("Recruit Pheasant", project, taro).save();
    new Task("Obtain food", project, inu).save();

    List<Task> tarosTasks = Task.findAllFor(taro.uid, false);
    List<Task> inusTasks = Task.findAllFor(inu.uid, false);
    List<Task> sarusTasks = Task.findAllFor(saru.uid, false);

    assertEquals(3, tarosTasks.size());
    assertEquals(1, inusTasks.size());
    assertEquals(0, sarusTasks.size());
    assertEquals(taro.name, tarosTasks.get(0).getUzer().name);
    assertEquals(inu.name, inusTasks.get(0).getUzer().name);		
  }

}