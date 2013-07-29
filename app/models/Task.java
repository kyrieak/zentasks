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
	public boolean done = false;
	public Date dueDate;
	public String folder;
	@ManyToOne
	private Integer uzerId;
	@ManyToOne
	private Long projectId;

	public static Model.Finder<Long, Task> find = new Model.Finder<Long, Task>(Long.class, Task.class);

	public static List<Task> findTaskFor(Uzer uzer, boolean completion) {
		return find.fetch("project").where()
				.eq("done", completion)
				.eq("project.members.email", uzer.email).
				findList();
	}
	
	public Task(String title, Long projectId, Integer uzerId) {
		this.title = title;
		this.projectId = projectId;
		this.uzerId = uzerId;
	}
	
	public static Task create(String title, Long projectId, Integer uzerId, Date dueDate) {
		Task task = new Task(title, projectId, uzerId);
		task.dueDate = dueDate;
		task.save();
		return task;
	}
	
	public String assignedTo() {
		Uzer uzer = Uzer.find.where().eq("uid", this.uzerId).findUnique();
		return uzer.name;
	}
	
	public String projectName() {
		Project project = Project.find.where().eq("id", this.projectId).findUnique();
		return project.name;
	}
	
}
