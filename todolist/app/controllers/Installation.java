package controllers;

import models.InstallState;
import models.User;
import play.data.Form;
import play.data.validation.Constraints;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.admin.*;

public class Installation extends Controller {
	
	public static class NewAdminUser {
		@Constraints.Required
		@Constraints.Email
		public String email;
		
		@Constraints.Required
		public String password;
	}
	
	public static Result index() {
		if (InstallState.isComplete())
		{
			return redirect(routes.Tasks.index());
		}
		
		return ok(install.render(form(NewAdminUser.class)));
	}
	
	public static Result addAdminUser() {
		Form<NewAdminUser> filledForm = form(NewAdminUser.class).bindFromRequest();
		if(filledForm.hasErrors()) {
			return badRequest(
					install.render(filledForm)
				);
		} else {
			NewAdminUser formData = filledForm.get();
			User newUser = new User();
			newUser.email = formData.email;
			newUser.password = formData.password;
			newUser.isAdmin = true;
			
			User.create(newUser);
			InstallState.create(new InstallState());
			
			return redirect(routes.Tasks.index());
		}
	}
}
