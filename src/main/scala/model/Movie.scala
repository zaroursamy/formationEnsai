package model

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
                 imdbScore: Double,
                 aspectRatio: Option[Double],
                 movieFacebookLikes: Option[Int]
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
