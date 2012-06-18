package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class Task extends Model {

	@Id
	public Long id;
	
	@Required
	public String label;
	
	@ManyToOne
	public User user;
	
	public static Finder<Long,Task> find = new Finder(Long.class, Task.class);
	
	public static List<Task> all() {
		return find.all();
	}
	
	public static List<Task> belongingToUser(String userEmail)
	{
		return find.join("user")
			.where()
				.eq("user.email", userEmail)
			.findList();
	}
	
	public static void create(Task task, String username) {
		task.user = User.find.ref(username);
		task.save();
	}
	
	public static void delete(Long id) {
		find.ref(id).delete();
	}
}
