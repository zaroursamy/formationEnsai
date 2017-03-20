package utils

/**
  * Created by zarour on 18/03/2017.
  */

object Stat {

  trait SeqStat[T <: AnyVal]{
    def mean: Option[Double]
  }

  implicit class SeqStatDouble(s: Seq[Double]) extends SeqStat[Double]{
    def mean: Option[Double] = if(s.nonEmpty) Some(s.sum / s.size) else None
  }

   implicit class SeqStatInt(s: Seq[Int]) extends SeqStat[Int]{
    def mean: Option[Double] = if(s.nonEmpty) Some(s.sum / s.size) else None
  }

}



