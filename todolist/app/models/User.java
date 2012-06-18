package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.Constraint;

import play.data.validation.Constraints;
import play.data.validation.Constraints.Email;
import play.db.ebean.Model;

@Entity
public class User extends Model {
	@Id
	@Constraints.Required
	@Constraints.Email
	public String email;
	
	@Constraints.Required
	public String password;
	
	public Boolean isAdmin;
	
	public static Finder<String,User> find = new Finder(String.class, User.class);
	
	public static List<User> all()
	{
		return find.all();
	}
	
	public static void create(User user)
	{
		user.save();
	}
	
	public static void deleteByEmail(String email)
	{
		find.ref(email).delete();
	}
	
	public static User authenticate(String email, String password)
	{
		return find.where()
	            .eq("email", email)
	            .eq("password", password)
	            .findUnique();
	}
	
	public User() {
		isAdmin = new Boolean(false);
	}
}
