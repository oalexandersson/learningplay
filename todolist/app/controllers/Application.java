package controllers;

import models.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.login;

public class Application extends Controller {

    public static class Login {
        
        public String email;
        public String password;
        public boolean isAdmin;
        
        public String validate() {
        	User user = User.authenticate(email, password);
            if(user == null) {
                return "Invalid user or password";
            }
            
            isAdmin = user.isAdmin;
            return null;
        }
        
    }

    /**
     * Login page.
     */
    public static Result login() {
        return ok(
            login.render(form(Login.class))
        );
    }
    
    /**
     * Handle login form submission.
     */
    public static Result authenticate() {
        Form<Login> loginForm = form(Login.class).bindFromRequest();
        if(loginForm.hasErrors()) {
            return badRequest(login.render(loginForm));
        } else {
            session("email", loginForm.get().email);
            if(loginForm.get().isAdmin) {
            	session("admin", "true");
            }
            return redirect(
                routes.Tasks.index()
            );
        }
    }
    
    /**
     * Logout and clean the session.
     */
    public static Result logout() {
        session().clear();
        flash("success", "You've been logged out");
        return redirect(
            routes.Application.login()
        );
    }

	public static Result index() {
		return redirect(routes.Tasks.index());
	}
}