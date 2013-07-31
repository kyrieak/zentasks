package models;

import java.util.*;

import javax.persistence.*;

import play.db.ebean.*;


@Entity
public class Project extends Model {

	@Id
	public Long id;
	public String name;
	public String folder;
	
	@ManyToMany
	public List<Uzer> uzers = new ArrayList<Uzer>();
	
// Finder
	
	public static Model.Finder<Long, Project> find = new Model.Finder<Long, Project>(
			Long.class,  Project.class);
	
// Constructors
	
	public Project(String name, String folder, Uzer owner) {
		this.name = name;
		this.folder = folder;
		this.uzers.add(owner);
	}

// Methods
	
	public List<Task> getTasks() {
	  return Task.find.where().eq("project_id", this.id).findList();
	}
	
// #create
	public static Project create(String name, String folder, Uzer uzer) {
		Project project = new Project(name, folder, uzer);
		project.save();
		project.saveManyToManyAssociations("uzers");
		return project;
	}
	
// #findByUid	
	public static List<Project> findByUid(Integer uid) {
		return find.where().eq("uzers.uid", uid).findList();
	}	
	
}