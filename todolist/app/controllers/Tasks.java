package controllers;

import models.Task;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.tasks.*;

@Security.Authenticated(Secured.class)
public class Tasks extends Controller {

	public static Result index() {
		return ok(
				index.render(Task.all(), form(Task.class))
			);
	}

	public static Result create() {
		Form<Task> filledForm = form(Task.class).bindFromRequest();
		if(filledForm.hasErrors()) {
			return badRequest(
					index.render(Task.all(), filledForm)
				);
		} else {
			Task.create(filledForm.get());
			return redirect(routes.Tasks.index());
		}
	}

	public static Result delete(Long id) {
		Task.delete(id);
		return redirect(routes.Tasks.index());
	}
}
