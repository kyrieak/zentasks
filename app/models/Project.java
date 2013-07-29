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
	
	@ManyToMany(cascade = CascadeType.REMOVE)
	public List<Uzer> members = new ArrayList<Uzer>();
	
// Finder
	
	public static Model.Finder<Long, Project> find = new Model.Finder<Long, Project>(
			Long.class,  Project.class);
	
// Constructors
	
	public Project(String name, String folder, Uzer owner) {
		this.name = name;
		this.folder = folder;
		this.members.add(owner);
	}

// Methods
	
// #create
	public static Project create(String name, String folder, Uzer uzer) {
		Project project = new Project(name, folder, uzer);
		project.save();
		project.saveManyToManyAssociations("members");
		return project;
	}
	
// #findByUid	
	public static List<Project> findByUid(Integer uid) {
		return find.where().eq("members.uid", uid).findList();
	}
	
	
	
}