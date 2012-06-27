package models

import play.api.data._
import play.api.data.Forms._
import anorm._
import anorm.SqlParser._

import play.api.db._
import play.api.Play.current   // for implicit DB connection

case class Link(id: Pk[Long], title: String, url: String, user_email: String)

object Link {

  val simple = {
    get[Pk[Long]]("link.id") ~
    get[String]("link.title") ~
    get[String]("link.url") ~
    get[String]("link.user_email") map {
      case id~title~url~user_email => Link(id, title, url, user_email)
    }
  }

  def findAllByUserEmail(email: String): List[Link] = {
    DB.withConnection { implicit connection =>
      SQL("SELECT * FROM link where user_email={email}").on(
          'email -> email
      ).as(Link.simple *)
    }
  }
  
  def create(link: Link): Link = {
     DB.withTransaction { implicit connection =>
       
       // Get the project id
       val id: Long = link.id.getOrElse {
         SQL("select next value for link_seq").as(scalar[Long].single)
       }
       
       // Insert the project
       SQL(
         """
           insert into link values (
             {id}, {title}, {url}, {user_email}
           )
         """
       ).on(
         'id -> id,
         'title -> link.title,
         'url -> link.url,
         'user_email -> link.user_email
       ).executeUpdate()
       
       link.copy(id = Id(id))
       
     }
  }
  
  def delete(link_id: Long) = {
    DB.withConnection { implicit connection =>
      SQL( "DELETE FROM link WHERE id={id}").on(
          'id -> link_id
      ).executeUpdate()
    }
  }
}