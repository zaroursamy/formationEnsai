package statistic

import com.quantifind.charts.Highcharts._

/**
  * Created by zarour on 23/03/2017.
  */
object Plot {

  def plotKmeans(s: Seq[Double]) = {
    line(1 to s.size, s)
  }
}
