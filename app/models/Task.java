package models;

import java.util.*;

import javax.persistence.*;

import play.db.ebean.*;
import play.db.ebean.Model.Finder;

@Entity
public class Task extends Model {

	@Id
	public Long id;
	public String title;
	public Date dueDate;
	public String folder;
	public boolean done = false;
	
	@ManyToOne
	private Uzer uzer;
	@ManyToOne
	private Project project;

// Finder
	
	public static Model.Finder<Long, Task> find = new Model.Finder<Long, Task>(
			Long.class, Task.class);
	
// Constructors
	
	public Task(String title, Project project, Uzer uzer) {
		this.title = title;
		this.project = project;
		this.uzer = uzer;
	}
	
// Methods
	
	public Uzer getUzer() {
	  return Uzer.find.where()
	                  .eq("uid", this.uzer.uid)
	                  .findUnique();
	}
	
	public Project getProject() {
	  return Project.find.where()
	                     .eq("id", this.project.id)
	                     .findUnique();
	}

// #create
	public static Task create(String title, Project project, Uzer uzer, Date dueDate) {
		Task task = new Task(title, project, uzer);
		task.dueDate = dueDate;
		task.save();
		return task;
	}
	
// #findTaskFor
	public static List<Task> findAllFor(Integer uid, boolean completion) {
	  return find.where()
                .eq("done", false)
                .eq("uzer.uid", uid)
                .findList();
	}
	
}
