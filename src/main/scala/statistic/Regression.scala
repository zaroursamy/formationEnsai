package statistic

import model.Iris
import org.apache.spark.SparkContext
import org.apache.spark.mllib.linalg.{Vector, Vectors}
import org.apache.spark.mllib.regression.{LabeledPoint, LinearRegressionModel, LinearRegressionWithSGD}
import org.apache.spark.rdd.RDD

/**
  * Created by zarour on 23/03/2017.
  */
object Regression {

  def linearRegression(okLines: RDD[Iris], sc: SparkContext):
  (RDD[(Double, Double)], Double, Double, Vector) = {

    val iris: RDD[LabeledPoint] = okLines.map{ i =>
      LabeledPoint(
        i.sepalLength,
        Vectors.dense(Array(i.petalLength, i.petalWidth, i.sepalWidth))
      )
    }.cache()

//    val trainTest: Array[RDD[LabeledPoint]] = iris.randomSplit(Array(0.6, 0.4))

    // modelisation
    val regression: LinearRegressionWithSGD = new LinearRegressionWithSGD().setIntercept(true)
    regression.optimizer.setStepSize(0.005).setNumIterations(5000)
    val model: LinearRegressionModel = regression.run(iris)

    // Evaluate model on training examples and compute training error
    val valuesAndPreds: RDD[(Double, Double)] = iris.map { point =>
      val prediction = model.predict(point.features)
      (point.label, prediction)
    }

    val MSE: Double = valuesAndPreds.map{case(v: Double, p: Double) => math.pow(v - p, 2)}.mean()


    (valuesAndPreds, MSE, model.intercept, model.weights)

  }

  def logisticRegression(okLines: RDD[Iris], sc: SparkContext) = {

  }
}
