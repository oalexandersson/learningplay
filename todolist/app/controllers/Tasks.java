package controllers;

import models.Task;
import models.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.tasks.index;

@Security.Authenticated(Secured.class)
public class Tasks extends Controller {

	public static Result index() {
		return ok(
				index.render(Task.belongingToUser(request().username()), User.find.ref(request().username()), form(Task.class))
			);
	}

	public static Result add() {
		Form<Task> filledForm = form(Task.class).bindFromRequest();
		if(filledForm.hasErrors()) {
			return badRequest(
					index.render(Task.all(), User.find.ref(request().username()), filledForm)
				);
		} else {
			Task.create(filledForm.get(), request().username());
			return redirect(routes.Tasks.index());
		}
	}

	public static Result delete(Long id) {
		Task.delete(id);
		return redirect(routes.Tasks.index());
	}
}
