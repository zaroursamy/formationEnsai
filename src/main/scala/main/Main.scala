import config.Settings
import mapping.PrepareData
import model.Movie
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{Row, SQLContext}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by zarour on 13/03/2017.
  */
object Main {


  def main(args: Array[String]): Unit = {

    val sc = new SparkContext(new SparkConf().setMaster(Settings.master).setAppName(Settings.appName))
    val sQLContext = new SQLContext(sc)

    val movies: RDD[Movie] = PrepareData.readMovie(sQLContext)

    movies.take(2).foreach(println)



  }


}
