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
	private Integer uzerId;
	@ManyToOne
	private Long projectId;

// Finder
	
	public static Model.Finder<Long, Task> find = new Model.Finder<Long, Task>(
			Long.class, Task.class);
	
// Constructors
	
	public Task(String title, Long projectId, Integer uzerId) {
		this.title = title;
		this.projectId = projectId;
		this.uzerId = uzerId;
	}
	
// Methods

// #create
	public static Task create(String title, Long projectId, Integer uzerId, Date dueDate) {
		Task task = new Task(title, projectId, uzerId);
		task.dueDate = dueDate;
		task.save();
		return task;
	}
	
// #findTaskFor
	public static List<Task> findAllFor(Integer uid, boolean completion) {
		return find.where()
				.eq("done", completion)
				.eq("uzerId", uid).
				findList();
	}
	
// #getUzer	
	public Uzer getUzer() {
		Uzer uzer = Uzer.find.where().eq("uid", this.uzerId).findUnique();
		return uzer;
	}
	
// #getProject	
	public Project getProject() {
		Project project = Project.find.where().eq("id", this.projectId).findUnique();
		return project;
	}	
	
}
