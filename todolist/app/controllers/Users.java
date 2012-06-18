package controllers;

import models.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.admin.*;

@Security.Authenticated(AdminSecured.class)
public class Users extends Controller {

	public static Result index() {
		return ok(
				users.render(User.all(), form(User.class))
			);
	}

	public static Result add() {
		Form<User> filledForm = form(User.class).bindFromRequest();
		if(filledForm.hasErrors()) {
			return badRequest(
					users.render(User.all(), filledForm)
				);
		} else {
			User.create(filledForm.get());
			return redirect(routes.Users.index());
		}
	}

	public static Result delete(String email) {
		User.deleteByEmail(email);
		return redirect(routes.Users.index());
	}
}
