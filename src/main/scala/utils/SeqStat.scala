package utils

/**
  * Created by zarour on 18/03/2017.
  */

object Stat {

  trait SeqStat[T <: AnyVal]{
    def mean: Double
  }

  implicit class SeqStatDouble(s: Seq[Double]) extends SeqStat[Double]{
    def mean: Double = s.sum / s.size
  }

   implicit class SeqStatInt(s: Seq[Int]) extends SeqStat[Int]{
    def mean: Double = s.sum / s.size
  }

}



