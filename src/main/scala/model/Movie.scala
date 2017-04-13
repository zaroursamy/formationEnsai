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
                 contentRating: Option[String],
                 budget: Option[Int],
                 titleYear: Option[Int],
                 actor2facebookLikes: Option[Int],
                 imdbScore: Option[Double],
                 aspectRatio: Option[Double],
                 movieFacebookLikes: Option[Int]
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
        if (r.getAs[String]("num_voted_users").isEmpty) None else Some(r.getAs[String]("num_voted_users").replaceAll(",",".").toInt),
        if (r.getAs[String]("cast_total_facebook_likes").isEmpty) None else Some(r.getAs[Int]("cast_total_facebook_likes")),
        if (r.getAs[String]("actor_3_name").isEmpty) None else Some(r.getAs[String]("actor_3_name").trim),
        if (r.getAs[String]("facenumber_in_poster").isEmpty) None else Some(r.getAs[Int]("facenumber_in_poster")),
        if (r.getAs[String]("plot_keywords").isEmpty) None else Some(r.getAs[String]("plot_keywords").trim),
        if (r.getAs[String]("movie_imdb_link").isEmpty) None else Some(r.getAs[String]("movie_imdb_link").trim),
        if (r.getAs[String]("num_user_for_reviews").isEmpty) None else Some(r.getAs[Int]("num_user_for_reviews")),
        if (r.getAs[String]("language").isEmpty) None else Some(r.getAs[String]("language").trim),
        if (r.getAs[String]("country").isEmpty) None else Some(r.getAs[String]("country")),
        if (r.getAs[String]("content_rating").isEmpty) None else Some(r.getAs[String]("content_rating").trim),
        if (r.getAs[String]("budget").isEmpty) None else Some(r.getAs[String]("budget").replaceAll(",", ".").toInt),
        if (r.getAs[String]("title_year").isEmpty) None else Some(r.getAs[Int]("title_year")),
        if (r.getAs[String]("actor_2_facebook_likes").isEmpty) None else Some(r.getAs[Int]("actor_2_facebook_likes")),
        if (r.getAs[String]("imdb_score").isEmpty) None else Some(r.getAs[String]("imdb_score").replaceAll(",", ".").toDouble),
        if (r.getAs[String]("aspect_ratio").isEmpty) None else Some(r.getAs[Double]("aspect_ratio")),
        if (r.getAs[String]("movie_facebook_likes").isEmpty) None else Some(r.getAs[Int]("movie_facebook_likes"))
      )
    } match {
      case Success(m) => Some(m)
      case Failure(e) => None
    }
  }

}

case class Iris(id: String, sepalLength: Double, sepalWidth: Double, petalLength: Double, petalWidth: Double, species: String)

















object Iris {


















  def splitLine(line: String): Array[String] = line.split(",")

  def irisObject(colonnes: Array[String]): Option[Iris] = {

    if (colonnes(0).replaceAll("\"", "").isEmpty) None
    else {
      Some(
        Iris(
          id = colonnes(0).replaceAll("\"", ""),
          sepalLength = colonnes(1).toDouble,
          sepalWidth = colonnes(2).toDouble,
          petalLength = colonnes(3).toDouble,
          petalWidth = colonnes(4).toDouble,
          species = colonnes(5).replaceAll("\"", "")
        )
      )
    }

  }
}

case class Species(species: String, typeSpecies: String)




