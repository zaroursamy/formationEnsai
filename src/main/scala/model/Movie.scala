package model

case class Movie(color: Option[String],
                 directorName: String,
                 num_critic_for_reviews: Option[Int],
                 duration: Option[Int],
                 director_facebook_likes: Option[Int],
                 actor_3_facebook_likes: Option[Int],
                 actor_2_name: Option[String],
                 actor_1_facebook_likes: Option[Int],
                 gross: Option[Int],
                 genres: Option[String],
                 actor_1_name: Option[String],
                 movie_title: Option[String],
                 num_voted_users: Option[Int],
                 cast_total_facebook_likes: Option[Int],
                 actor_3_name: Option[String],
                 facenumber_in_poster: Option[Int],
                 plot_keywords: Option[String],
                 movie_imdb_link: Option[String],
                 num_user_for_reviews: Option[Int],
                 language: Option[String],
                 country: Option[String],
                 content_rating: Option[String],
                 budget: Option[Int],
                 title_year: Option[Int],
                 actor_2_facebook_likes: Option[Int],
                 imdb_score: Double,
                 aspect_ratio: Option[Double],
                 movie_facebook_likes: Option[Int]
                )

object Movie {

  def fromString(r: String) = {
    val l: Array[String] = r.split(",")
    Movie(
      if (l(0).isEmpty) None else Some(l(0)),
      l(1),
      if (l(2).isEmpty) None else Some(l(2).toInt),
      if (l(3).isEmpty) None else Some(l(3).toInt),
      if (l(4).isEmpty) None else Some(l(4).toInt),
      if (l(5).isEmpty) None else Some(l(5).toInt),
      if (l(6).isEmpty) None else Some(l(6)),
      if (l(7).isEmpty) None else Some(l(7).toInt),
      if (l(8).isEmpty) None else Some(l(8).toInt),
      if (l(9).isEmpty) None else Some(l(9)),
      if (l(10).isEmpty) None else Some(l(10)),
      if (l(11).isEmpty) None else Some(l(11)),
      if (l(12).isEmpty) None else Some(l(12).toInt),
      if (l(13).isEmpty) None else Some(l(13).toInt),
      if (l(14).isEmpty) None else Some(l(14)),
      if (l(15).isEmpty) None else Some(l(15).toInt),
      if (l(16).isEmpty) None else Some(l(16)),
      if (l(17).isEmpty) None else Some(l(17)),
      if (l(18).isEmpty) None else Some(l(18).toInt),
      if (l(19).isEmpty) None else Some(l(19)),
      if(l(20).isEmpty) None else Some(l(20)),
      if(l(21).isEmpty) None else Some(l(21)),
      if(l(22).isEmpty) None else Some(l(22).toInt),
      if(l(23).isEmpty) None else Some(l(23).toInt),
      if(l(24).isEmpty) None else Some(l(24).toInt),
      l(25).toDouble,
      if(l(26).isEmpty) None else Some(l(26).toDouble),
      if(l(27).isEmpty) None else Some(l(27).replaceAll("]", "").toInt)
    )
  }
}
