/**
  * Created by zarour on 13/03/2017.
  */
class Test extends FunSuite {

  def median(x: List[Int]): Double = {
    val xSorted = x.sorted
    val milieu = x.length/2
    val (low, up) = xSorted.splitAt(milieu)

    if(x.length % 2 == 0) {
      (low.last + up.head) / 2
    } else {
      up.head
    }
  }


}
