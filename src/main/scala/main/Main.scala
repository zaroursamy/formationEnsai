import config.Settings
import mapping.PrepareData
import model.{Iris, Movie}
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}
import utils.Stat._


/**
  * Created by zarour on 13/03/2017.
  */
object Main {


  def main(args: Array[String]): Unit = {

    val sc = new SparkContext(new SparkConf().setMaster(Settings.master).setAppName(Settings.appName))
    val sQLContext = new SQLContext(sc)


    val iris: RDD[Iris] = PrepareData.readIris(sc)
    val movies: RDD[Movie] = PrepareData.readMovie(sQLContext).cache

    def exoIris(okLines: RDD[Iris]) = {
      println("nombre de replicas")
      println(okLines.count - okLines.distinct().count)

      println("nb despece")
      println(okLines.map(_.species).distinct().count)



      println("nb d'occurence de chaque espece")
      print(okLines.map(_.species).countByValue())

      println("nb virginica")
      println(okLines.filter(_.species == "virginica").count)

      println("petalLength moyenne")
      println(okLines.map(_.petalLength).mean())
      println("moyenne des petalle length avec reduce")
      println(okLines.map(_.petalLength).reduce(_+_)/okLines.count())


      println("nb de lignes au dessus de la moyenne petalLength")
      var moyenne = okLines.map(_.petalLength).mean()
      println(okLines.map(_.petalLength).filter(_>moyenne).count())

      println("nb de lignes au dessus de la moyenne sepalLength")
      moyenne = okLines.map(_.sepalLength).mean()
      println(okLines.map(_.sepalLength).filter(_>moyenne).count())
    }

    def exoMovies(movies: RDD[Movie]) = {

      movies.map(_.budget).foreach(println)
      println("Moyenne budget " + movies.map(_.budget).filter(_.isDefined).map(_.get).mean)
      println("Moyenne budget noir et blanc " + movies.filter(_.color.contains("Black and White")).map(_.budget).filter(_.isDefined).map(_.get).mean)
      println("Moyenne budget couleur "+movies.filter(_.color.contains("Color")).map(_.budget).filter(_.isDefined).foreach(println))


      println(s"\nNombre de lignes: ${movies.count()}")
      println(s"\nNombre de lignes distinctes: ${movies.distinct().count()}")
      println("Moyenne budget France " + movies.filter(_.country.contains("France")).map(_.budget).filter(_.isDefined).map(_.get).mean)
      println("Moyenne budget Mexico " + movies.filter(_.country.contains("Mexico")).map(_.budget).filter(_.isDefined).map(_.get).mean)
      println("Moyenne budget China " + movies.filter(_.country.contains("China")).map(_.budget).filter(_.isDefined).map(_.get).mean)
      println("Moyenne budget USA " + movies.filter(_.country.contains("USA")).map(_.budget).filter(_.isDefined).map(_.get).mean)

      println("\nFilms de George Lucas")
      movies.filter(_.directorName == "George Lucas").map(_.movieTitle).foreach(println)
    }

    exoMovies(movies)

  }


}
