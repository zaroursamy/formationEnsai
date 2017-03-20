package mapping

import config.Settings
import model.{Iris, Movie}
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SQLContext
import model.Iris._

/**
  * Created by zarour on 15/03/2017.
  */
object PrepareData {

  def readMovie(sQLContext: SQLContext): RDD[Movie] = {
    sQLContext.read.format("com.databricks.spark.csv").option("header","true").load(Settings.pathMovieData).rdd.flatMap(Movie.fromRow)
  }

  def readIris(sc: SparkContext): RDD[Iris] = {
    sc.textFile(Settings.pathIris).flatMap(x => irisObject(splitLine(x)))
  }
}
