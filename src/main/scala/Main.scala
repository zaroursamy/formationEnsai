/**
  * Created by zarour on 13/03/2017.
  */
object Main {

  /**
    * Exercice 1
    *
    * différence entre val et var
    */
  def exo1 = {
    val immutableInt = 2
    var mutableInt = 2

    println(immutableInt, mutableInt)
    mutableInt = 3
    println(immutableInt, mutableInt)
    mutableInt = mutableInt * 2
    println(immutableInt, mutableInt)


    val immutableInt2: Int = 2
    println(immutableInt == immutableInt2)
    println(immutableInt equals immutableInt2)


    println("*******")

    /* Avec des Array */
    val immutableArray = new Array[String](3)
    println(immutableArray)
    immutableArray(0) = "hey"
    immutableArray.foreach(println)
    println
    immutableArray(0) = "coucou"
    immutableArray.foreach(println)
    println
    immutableArray(1) = " "
    immutableArray(2) = "non"
    immutableArray.foreach(println)
    println(immutableArray)
    println
    var mutableArray = new Array[Int](3)
    mutableArray(0) = 0
    println(mutableArray)
    mutableArray = Array(1, 2) // Scala comprend que c'est un Array[Int]. Méthode apply
    println(mutableArray)
    mutableArray = Array.apply(1,2)
    println(mutableArray)
    mutableArray(1) = 10
    mutableArray.foreach(println)
    println(mutableArray)

    /* Les autres types */
    val bool: Boolean = true
    val double: Double = 1



  }

  /**
    * Exercice 1 bis
    *
    * collections
    */
  def exo1Bis = {

    /* les listes */
    val uneListe = List(1,2,3)
    val uneAutreListe: List[String] = List("a", "b")

    val uneListeVide = Nil
    val uneListePleine = 1 :: Nil
    val uneAutreListePleine = 1 :: 2 :: Nil

    /* les map */

    /* les set */

  }

  /**
    * Exercice 2
    *
    * Les fonctions
    */
  def exo2 = {

    /* fonction pure / fonction impure */
    def lancerUnMissile = println("BOOOOM")

    /* Les effets sont observables dans la fonction */
    def isPrecisePure (x: Double, y: Double, error: Double): Boolean = if(x <= (1-error) * y || x >= (1+y)*error) true else false

    /* Impure car on observe des effets en dehors de la fonction : ici on modifie l'etat de la console */
    def isPreciseImpure(x: Double, y: Double, error: Double): Boolean = {
      lancerUnMissile
      isPrecisePure(x, y, error)
    }

    val x = 10
    val y = 11
    val (error1, error2) = (0.1, 0.5)

    println(isPrecisePure(x, y, error1))
    println(isPreciseImpure(x, y, error2))

    println("*********")

    /* Exercice  */
    val maListe1 = List(1,2)
    val maListe2 = List(1,99 ,100)

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

    def mirrorList(x: List[Int]): List[Int] = {

      if(x.reverse == x){
        x
      }
      else {
        x.sortWith(_>_) ::: x.sortWith(_<_)
      }
    }

    maListe1.foreach(x => print(x+" ")); println("mirror: " + mirrorList(maListe1))
    println("mediane: "+ median(maListe1))
    maListe2.foreach(x => print(x+" ")); println("mirror: " + mirrorList(maListe2))
    println("mediane: "+ median(maListe2))
  }

  def main(args: Array[String]): Unit = {
    //exo1
    exo2
  }


}
