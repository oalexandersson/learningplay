package controllers

import play.api.mvc.Controller
import play.api.mvc.Action
import models.{ User, Link }
import play.api.data._
import play.api.data.Forms._
import anorm._

object Links extends Controller with Secured {

  val addLinkForm = Form(
      tuple(
          "title" -> nonEmptyText,
          "url" -> nonEmptyText
      )
  )
  
  def index = IsAuthenticated { username => _ =>
    User.findByEmail(username).map { user =>
      Ok(
        views.html.index(Link.findAllByUserEmail(user.email), user, addLinkForm))
    }.getOrElse(Forbidden)
  }
  
  def add = IsAuthenticated { username => implicit request =>
    addLinkForm.bindFromRequest.fold(
      errors => BadRequest,
      result => {
        Link.create(Link(NotAssigned, result._1, result._2, username))
        Redirect(routes.Links.index)
      }
    )
  }
  
  def delete(link_id: Long) = IsAuthenticated { _ => implicit request =>
    Link.delete(link_id)
    Redirect(routes.Links.index)
  }
}

