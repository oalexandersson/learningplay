package controllers

import play.api.mvc.RequestHeader
import play.api.mvc.Request
import play.api.mvc.Results
import play.api.mvc.Result
import play.api.mvc.Security
import play.api.mvc.Action
import play.api.mvc.AnyContent

trait Secured {
  
  /**
   * Retrieve the connected user email.
   */
  private def username(request: RequestHeader) = request.session.get("email")

  /**
   * Redirect to login if the user in not authorized.
   */
  private def onUnauthorized(request: RequestHeader) = Results.Redirect(routes.Application.login)
  
  // --
  
  /** 
   * Action for authenticated users.
   */
  def IsAuthenticated(f: => String => Request[AnyContent] => Result) = Security.Authenticated(username, onUnauthorized) { user =>
    Action(request => f(user)(request))
  }
//
//  /**
//   * Check if the connected user is a member of this project.
//   */
//  def IsMemberOf(project: Long)(f: => String => Request[AnyContent] => Result) = IsAuthenticated { user => request =>
//    if(Project.isMember(project, user)) {
//      f(user)(request)
//    } else {
//      Results.Forbidden
//    }
//  }
//
//  /**
//   * Check if the connected user is a owner of this task.
//   */
//  def IsOwnerOf(task: Long)(f: => String => Request[AnyContent] => Result) = IsAuthenticated { user => request =>
//    if(Task.isOwner(task, user)) {
//      f(user)(request)
//    } else {
//      Results.Forbidden
//    }
//  }

}
