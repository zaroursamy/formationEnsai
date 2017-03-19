package model

import org.apache.spark.sql.Row

import scala.util.{Failure, Success, Try}

case class Movie(color: Option[String],
                 directorName: String,
                 numCriticForReviews: Option[Int],
                 duration: Option[Int],
                 directorFacebookLikes: Option[Int],
                 actor3facebookLikes: Option[Int],
                 actor2name: Option[String],
                 actor1facebookLikes: Option[Int],
                 gross: Option[Int],
                 genres: Option[String],
                 actor1name: Option[String],
                 movieTitle: Option[String],
                 numVotedUsers: Option[Int],
                 castTotalFacebookLikes: Option[Int],
                 actor3name: Option[String],
                 facenumberInPoster: Option[Int],
                 plotKeywords: Option[String],
                 movieImdbLink: Option[String],
                 numUserForReviews: Option[Int],
                 language: Option[String],
                 country: Option[String],
                 contentRating: Option[String]
                 //                 budget: Opt/*/**/*/ion[Int],
                 //                 titleYear: Option[Int],
                 //                 actor2facebookLikes: Option[Int],
                 //                 imdbScore: Double,
                 //                 aspectRatio: Option[Double],
                 //                 movieFacebookLikes: Option[Int]
                )

object Movie {

  def fromRow(r: Row) = {
    Try {
      Movie(
        if (r.getAs[String]("color").isEmpty) None else Some(r.getAs[String]("color").trim),
        r.getAs[String]("director_name"),
        if (r.getAs[String]("num_critic_for_reviews").isEmpty) None else Some(r.getAs[Int]("num_critic_for_reviews")),
        if (r.getAs[String]("duration").isEmpty) None else Some(r.getAs[String]("duration").replaceAll(",", ".").toInt),
        if (r.getAs[String]("director_facebook_likes").isEmpty) None else Some(r.getAs[Int]("director_facebook_likes")),
        if (r.getAs[String]("actor_3_facebook_likes").isEmpty) None else Some(r.getAs[Int]("actor_3_facebook_likes")),
        if (r.getAs[String]("actor_2_name").isEmpty) None else Some(r.getAs[String]("actor_2_name").trim),
        if (r.getAs[String]("actor_1_facebook_likes").isEmpty) None else Some(r.getAs[Int]("actor_1_facebook_likes")),
        if (r.getAs[String]("gross").isEmpty) None else Some(r.getAs[Int]("gross")),
        if (r.getAs[String]("genres").isEmpty) None else Some(r.getAs[String]("genres".trim)),
        if (r.getAs[String]("actor_1_name").isEmpty) None else Some(r.getAs[String]("actor_1_name").trim),
        if (r.getAs[String]("movie_title").isEmpty) None else Some(r.getAs[String]("movie_title").trim),
        if (r.getAs[String]("num_voted_users").isEmpty) None else Some(r.getAs[Int]("num_voted_users")),
        if (r.getAs[String]("cast_total_facebook_likes").isEmpty) None else Some(r.getAs[Int]("cast_total_facebook_likes")),
        if (r.getAs[String]("actor_3_name").isEmpty) None else Some(r.getAs[String]("actor_3_name").trim),
        if (r.getAs[String]("facenumber_in_poster").isEmpty) None else Some(r.getAs[Int]("facenumber_in_poster")),
        if (r.getAs[String]("plot_keywords").isEmpty) None else Some(r.getAs[String]("plot_keywords").trim),
        if (r.getAs[String]("movie_imdb_link").isEmpty) None else Some(r.getAs[String]("movie_imdb_link").trim),
        if (r.getAs[String]("num_user_for_reviews").isEmpty) None else Some(r.getAs[Int]("num_user_for_reviews")),
        if (r.getAs[String]("language").isEmpty) None else Some(r.getAs[String]("language").trim),
        if (r.getAs[String]("country").isEmpty) None else Some(r.getAs[String]("country")),
        if (r.getAs[String]("content_rating").isEmpty) None else Some(r.getAs[String]("countent_rating").trim)

      )
    } match {
      case Success(m) => Some(m)
      case Failure(e) => None
    }


  }

  //  def fromString(r: String) = {
  //    val l: Array[String] = r.split(",")
  //    Try{
  //      Movie(
  //        if (l(0).isEmpty) None else Some(l(0).replaceAll("\\[","").replaceAll("\\]", "").trim),
  //        l(1).replaceAll("\\[","").replaceAll("\\]", "").trim,
  //        if (l(2).isEmpty) None else Some(l(2).toInt),
  //        if (l(3).isEmpty) None else Some(l(3).toInt),
  //        if (l(4).isEmpty) None else Some(l(4).toInt),
  //        if (l(5).isEmpty) None else Some(l(5).toInt),
  //        if (l(6).isEmpty) None else Some(l(6).replaceAll("\\[","").replaceAll("\\]", "").trim),
  //        if (l(7).isEmpty) None else Some(l(7).toInt),
  //        if (l(8).isEmpty) None else Some(l(8).toInt),
  //        if (l(9).isEmpty) None else Some(l(9).replaceAll("\\[","").replaceAll("\\]", "").trim),
  //        if (l(10).isEmpty) None else Some(l(10).replaceAll("\\[","").replaceAll("\\]", "").trim),
  //        if (l(11).isEmpty) None else Some(l(11).replaceAll("\\[","").replaceAll("\\]", "").trim),
  //        if (l(12).isEmpty) None else Some(l(12).toInt),
  //        if (l(13).isEmpty) None else Some(l(13).toInt),
  //        if (l(14).isEmpty) None else Some(l(14).replaceAll("\\[","").replaceAll("\\]", "").trim),
  //        if (l(15).isEmpty) None else Some(l(15).toInt),
  //        if (l(16).isEmpty) None else Some(l(16).replaceAll("\\[","").replaceAll("\\]", "").trim),
  //        if (l(17).isEmpty) None else Some(l(17).replaceAll("\\[","").replaceAll("\\]", "").trim),
  //        if (l(18).isEmpty) None else Some(l(18).toInt),
  //        if (l(19).isEmpty) None else Some(l(19).replaceAll("\\[","").replaceAll("\\]", "").trim),
  //        if(l(20).isEmpty) None else Some(l(20).replaceAll("\\[","").replaceAll("\\]", "").trim),
  //        if(l(21).isEmpty) None else Some(l(21).replaceAll("\\[","").replaceAll("\\]", "").trim),
  //        if(l(22).isEmpty) None else Some(l(22).toInt),
  //        if(l(23).isEmpty) None else Some(l(23).toInt),
  //        if(l(24).isEmpty) None else Some(l(24).toInt),
  //        l(25).toDouble,
  //        if(l(26).isEmpty) None else Some(l(26).toDouble),
  //        if(l(27).isEmpty) None else Some(l(27).replaceAll("\\[","").replaceAll("\\]", "").trim.toInt)
  //        )
  //    } match {
  //      case Success(e) => Some(e)
  //      case _ => None
  //    }
  //  }
}
