package statistic

import model.Iris
import org.apache.spark.SparkContext
import org.apache.spark.mllib.clustering.{KMeans, KMeansModel}
import org.apache.spark.mllib.linalg.{Vector, Vectors}
import org.apache.spark.rdd.RDD

/**
  * Created by zarour on 23/03/2017.
  */
object Classification {

  def kmeans(iris: RDD[Iris], sc: SparkContext): (RDD[(Vector, Int)], Double) = {

    val parsedData: RDD[Vector] = iris.map(s => Vectors.dense(Array(s.petalWidth, s.sepalWidth, s.petalLength, s.sepalLength))).cache()

    // Cluster the data into two classes using KMeans
    val numClusters = 3
    val numIterations = 20
    val clusters: KMeansModel = KMeans.train(parsedData, numClusters, numIterations)

    val res: RDD[(Vector, Int)] = parsedData.map(x => (x, clusters.predict(x)))

    // Evaluate clustering by computing Within Set Sum of Squared Errors
    val WSSSE: Double = clusters.computeCost(parsedData)

    (res, WSSSE)

  }
}
