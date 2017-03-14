package model

/**
  * Created by zarour on 14/03/2017.
  */
case class Movie(
                  id: Int,
                  title: String,
                  color: String,
                  duration: Option[Int],
                  gross: Option[Int],
                  genres: Seq[String],
                  content_rating: String,
                  facenumber_in_poster: Option[Int],
                  language: String,
                  country: String,
                  budget: Option[Float],
                  title_year: String,
                  aspect_ratio: String,
                  director_name: String,
                  actor_1_name: String,
                  actor_2_name: String,
                  actor_3_name: String,
                  director_facebook_likes: Option[Int],
                  actor_1_facebook_likes: Option[Int],
                  actor_2_facebook_likes: Option[Int],
                  actor_3_facebook_likes: Option[Int],
                  cast_total_facebook_likes: Option[Int],
                  movie_facebook_likes: String,
                  num_voted_users: Option[Int],
                  num_critic_for_reviews: Option[Float],
                  num_user_for_reviews: Option[Int],
                  imdb_score: Option[Float],
                  plot_keywords: Seq[String],
                  movie_imdb_link: String
                )
