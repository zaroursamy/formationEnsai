package mapping

import config.Settings
import model.{Iris, Movie, Species}
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SQLContext
import model.Iris._
import org.apache.spark.broadcast.Broadcast

/**
  * Created by zarour on 15/03/2017.
  */
object PrepareData {

  def readMovie(sQLContext: SQLContext): RDD[Movie] = {
    sQLContext.read.format("com.databricks.spark.csv")
      .option("header", "true")
      .load(Settings.pathMovieData).rdd.flatMap(Movie.fromRow)
  }

  def readIris(sc: SparkContext): RDD[Iris] = {
    sc.textFile(Settings.pathIris)
      .flatMap(x => irisObject(splitLine(x)))
  }


  def readSpecies(sc: SparkContext): Map[String, String] = {



    val rdd: RDD[Species] = sc.textFile(Settings.pathSpecies)
      .map { line =>

      val (typeSpecies, nameSpecies) = (line.split(",")(0), line.split(",")(1))
      Species(typeSpecies, nameSpecies)
    }
    rdd.collect().map(spec => (spec.typeSpecies, spec.species)).toMap
  }

  def joinIrisSpecies(iris: RDD[Iris], species: Broadcast[Map[String, String]]) = {
    iris
      .flatMap { myIris =>
      species.value.get(myIris.species).map(speciesName => (speciesName, myIris))
    }
      .map{case (name, myIris) => myIris.copy(species=name)}

//    iris.flatMap{ myIris =>
//      species.value.get(myIris.species).map(name => myIris.copy(species=name))
//    }
  }
}
