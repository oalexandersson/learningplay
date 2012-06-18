package controllers;

import play.*;
import play.mvc.*;
import play.mvc.Http.*;

import models.*;
import views.html.admin.*;

public class AdminSecured extends Security.Authenticator {
    
    @Override
    public String getUsername(Context ctx) {
    	if (ctx.session().get("admin") != null)
    	{
    		return ctx.session().get("email");
    	}
    	return null;
    }
    
    @Override
    public Result onUnauthorized(Context ctx) {
        return forbidden(accessdenied.render("This area requires administrator privileges."));
    }
    
    // Access rights
    
//    public static boolean isMemberOf(Long project) {
//        return Project.isMember(
//            project,
//            Context.current().request().username()
//        );
//    }
//    
//    public static boolean isOwnerOf(Long task) {
//        return Task.isOwner(
//            task,
//            Context.current().request().username()
//        );
//    }
    
}