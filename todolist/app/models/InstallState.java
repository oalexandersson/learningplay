package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;

@Entity
public class InstallState extends Model {
	
	@Id
	public Long id;
	
	public boolean installationComplete;
	
	public static Finder<Long,InstallState> find = new Finder(Long.class, InstallState.class);

	public static void create(InstallState state) {
		state.save();
	}
	
	public static boolean isComplete() {
		List<InstallState> states = find.all();
		
		if (states.size() > 0)
		{
			return states.get(0).installationComplete;
		}
		return false;
	}
	
	public InstallState() {
		installationComplete = true;
	}
}
