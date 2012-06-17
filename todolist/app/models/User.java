package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class User extends Model {
	@Id
	Long id;
	
	@Required
	String login;
	
	@Required
	String password;
	
	public static Finder<Long,User> find = new Finder(Long.class, User.class);
	
	public static List<User> all()
	{
		return find.all();
	}
	
	public static void create(Task task)
	{
		task.save();
	}
	
	public static void delete(Long id)
	{
		find.ref(id).delete();
	}
}
