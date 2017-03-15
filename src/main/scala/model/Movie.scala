package model

import org.apache.spark.sql.Row

case class Movie(color: String,
                 directorName: String,
                 num_critic_for_reviews: Option[Int]

                )

object Movie {
  def fromRow(r: Row): Movie = Movie(
    r.getAs[String]("color"),
    r.getAs[String]("director_name"),
    Option(r.getAs[Int]("num_critic_for_reviews"))
  )
}
