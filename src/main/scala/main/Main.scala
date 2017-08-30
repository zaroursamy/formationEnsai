import config.Settings
import mapping.PrepareData
import model.{Iris, Movie}
import org.apache.spark.broadcast.Broadcast
import org.apache.spark.mllib.linalg.Vector
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}
import statistic.{Classification, Plot, Regression}
import utils.Stat._

import scala.reflect.internal.ClassfileConstants


/**
  * Created by zarour on 13/03/2017.
  */
object Main {


  def main(args: Array[String]): Unit = {

    val sc = new SparkContext(new SparkConf().setMaster(Settings.master).setAppName(Settings.appName))

    val iris: RDD[Iris] = PrepareData.readIris(sc)

    val sQLContext = new SQLContext(sc)

    val typeSpecies: Broadcast[Map[String, String]] = sc.broadcast(PrepareData.readSpecies(sc))
    val irisNew: RDD[Iris] = PrepareData.joinIrisSpecies(iris, typeSpecies)

    //    val movies: RDD[Movie] = PrepareData.readMovie(sQLContext).cache


    def exoIris(okLines: RDD[Iris]) = {

      iris
        .map(_.sepalLength)
        .filter(_>3)
        .reduce(_+_)

      println("nombre de replicas: nombre de lignes dupliquées")
      println(okLines.count - okLines.distinct().count)
      //ou, sans id
      okLines.count - okLines.map(_.copy(id = "")).distinct().count


      println("nb despece")
      println(okLines.map(_.species).distinct().count)

      println("nb d'occurence de chaque espece")






      print(okLines.map(_.species).countByValue())

      println("nb virginica")
      println(okLines.filter(_.species == "virginica").count)

      println("petalLength moyenne")
      println(okLines.map(_.petalLength).mean())
      println("moyenne des petalLength avec reduce")
      println(okLines.map(_.petalLength).reduce(_ + _) / okLines.count())

      println("nb de lignes au dessus de la moyenne petalLength")
      var moyenne = okLines.map(_.petalLength).mean()
      println(okLines.map(_.petalLength).filter(_ > moyenne).count())

      println("nb de lignes au dessus de la moyenne sepalLength")
      moyenne = okLines.map(_.sepalLength).mean()
      println(okLines.map(_.sepalLength).filter(_ > moyenne).count())

      println("\nKmeans: print de chaque classe")
      val x: (RDD[(Vector, Int)], Double) = Classification.kmeans(okLines, sc)
      x._1.foreach(println)


      val multipleKmeans: Seq[(RDD[(Vector, Int)], Double)] = (1 to 10).map(nbClust => Classification.kmeans(okLines, sc, numClust = nbClust))

      println("\nLinear regression")
      val lr: (RDD[(Double, Double)], Double, Double, Vector) = Regression.linearRegression(okLines, sc)
      println(s"MSE: ${lr._2} \nIntercept: ${lr._3}\nPoids: ");
      lr._4.toArray.foreach(println)


    }

    exoIris(iris)

    def exoMovies(movies: RDD[Movie]) = {

      println("Moyenne budget " + movies.map(_.budget).filter(_.isDefined).map(_.get).mean)
      println("Moyenne budget noir et blanc " + movies.filter(_.color.contains("Black and White")).map(_.budget).filter(_.isDefined).map(_.get).mean)
      println("Moyenne budget couleur " + movies.filter(_.color.contains("Color")).map(_.budget).filter(_.isDefined).map(_.get).mean)

      println("\nCombien d'occurences par couleur de film")
      println(movies.map(_.color).countByValue())


      println(s"\nNombre de lignes: ${movies.count()}")
      println(s"\nNombre de lignes distinctes: ${movies.distinct().count()}")
      println("Moyenne budget France " + movies.filter(_.country.contains("France")).map(_.budget).filter(_.isDefined).map(_.get).mean)
      println("Moyenne budget Mexico " + movies.filter(_.country.contains("Mexico")).map(_.budget).filter(_.isDefined).map(_.get).mean)
      println("Moyenne budget China " + movies.filter(_.country.contains("China")).map(_.budget).filter(_.isDefined).map(_.get).mean)
      println("Moyenne budget USA " + movies.filter(_.country.contains("USA")).map(_.budget).filter(_.isDefined).map(_.get).mean)

      println("\n Nombre de film par pays")
      println(movies.map(_.country.getOrElse("")).countByValue().filter(_._2 > 40))

      println("\nFilms de George Lucas")
      movies.filter(_.directorName == "George Lucas").map(_.movieTitle).foreach(println)

      println("\nParmis les films de George Lucas, lequel a le plus gros imdb_score ?")
      println(movies
        .filter(_.directorName == "George Lucas")
        .map(m => (m.movieTitle.getOrElse(""), m.imdbScore))
        .reduce((x, y) => if (x._2.get > y._2.get) x else y))

      println("\nListe des films d'aventures")
      movies.flatMap { m =>
        val title = m.movieTitle
        val keywords = m.genres.getOrElse("").split("\\|")

        if (keywords.map(_.toLowerCase) contains "adventure") title else None
      }.take(10).foreach(println)

      println("\n Combien de films d'actions et d'aventure ?")
      println(movies.flatMap { m =>
        val title = m.movieTitle
        val keywords = m.genres.getOrElse("")

        if (keywords.toLowerCase.contains("adventure") && keywords.toLowerCase.contains("action")) title else None
      }.count())


      /* Corrélations */
      import org.apache.spark.mllib.stat.Statistics
      val corrRdd: RDD[(Double, Int)] = movies
        .map(m => (m.imdbScore, m.numVotedUsers))
        .flatMap {
          case (Some(a), Some(b)) => Some((a, b))
          case _ => None
        }

      val corr: Double = Statistics.corr(corrRdd.map(_._1), corrRdd.map(_._2.toDouble))
      println(s"\n correlation imdbscore et numvoteduser: ${corr}")

    }

    //    exoMovies(movies)

  }


}
