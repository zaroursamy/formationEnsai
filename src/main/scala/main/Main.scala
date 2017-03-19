import config.Settings
import mapping.PrepareData
import model.Movie
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{Dataset, SQLContext}
import org.apache.spark.{SparkConf, SparkContext}
import utils.Stat._


/**
  * Created by zarour on 13/03/2017.
  */
object Main {


  def main(args: Array[String]): Unit = {

    val sc = new SparkContext(new SparkConf().setMaster(Settings.master).setAppName(Settings.appName))
    val sQLContext = new SQLContext(sc)

    val movies: RDD[Movie] = PrepareData.readMovie(sQLContext)

//    movies.foreach(println)

    println("Moyenne duration noir et blanc")
    println(movies
      .filter(_.color.contains("Black and White"))
      .map(_.duration)
      .filter(_.isDefined)
      .map(_.get)
      .mean())
    println

    println("Moyenne duration par groupe de couleur")
    movies.filter(_.duration.isDefined).groupBy(_.color).map(m => (m._1, m._2.map(_.duration.get).toList.mean)).foreach(println)

//    println("Nombre de lignes")
//    println(movies.count); println
//
//    println("Nombre de films en couleur et en noir et blanc")
//    println(movies.map(_.color.getOrElse("")).countByValue()); println

//    println("Budget moyen")
//    println(movies.map(_.budget).filter(_.nonEmpty).map(_.get).mean)
//
//    println("Budget moyen par couleur")
//    //movies.filter(_.color == Some("Black and White")).map(_.budget).foreach(println)
//    movies.filter(_.budget.isDefined).groupBy(m => m.color.getOrElse("")).map(mc => (mc._1, mc._2.map(_.budget.get).toList.mean)).foreach(println); println
//
//    println("Moyenne budget chez noir et blanc")
//    println(movies.filter(_.color.contains("Black and White")).map(_.budget).filter(_.isDefined).map(_.get).mean()); println




  }


}
