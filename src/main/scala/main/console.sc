/*
Exemple 1: val et var
 */


// Avec un entier
val immutableInt = 2
var mutableInt = 2
println(immutableInt, mutableInt)
mutableInt = 3
println(immutableInt, mutableInt)

// Avec un tableau
val immutableArray = new Array[String](3)
immutableArray(0) = "premier"
immutableArray(1) = "second"
immutableArray(2) = "troisieme"
immutableArray foreach println

var mutableArray = new Array[Int](3)
mutableArray(0) = 1
mutableArray(1) = 2
mutableArray foreach println

mutableArray = new Array[Int](3)
mutableArray foreach println

// Une val avec des scope
val unBool: Boolean = {
  val x = 3
  val y = x + 1
  if (y >= 3) true else false
}

unBool

/*
Exercice 1
Stocker dans une val la chaine "www.example.com/1/2/3/4" en l'initialisant à "www.example.com"
 */
val exo1 = {
  var i = "www.example.com"
  val sep = "/"
  for (j <- 1 to 4) i = i + sep + j
  i
}

/*
Exemple 2: les fonctions
 */
def addS(s: String): String = s + "s"
val pluriel = addS("ensaien")

def lancerUnMissile = println("BOOOOM")

/* Les effets sont observables dans la fonction */
def isPrecisePure(x: Double, y: Double, error: Double): Boolean = if (x <= (1 - error) * y || x >= (1 + y) * error) true else false

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

/*
Exercice 2: Ecrire la fonction factorielle
 */
def factorielle(n: Int): Int = {
  if (n == 0) 1 else n * factorielle(n - 1)
}

val fact4 = factorielle(4)

/*
Exemple 3: les classes
*/

class Movie1(val title: String, id: Long = 0) {

  // constructeur
  def this(id: Long) = this("shrek", id)
}

case class Movie2(title: String, id: Long = 0)

var movie1 = new Movie1("shrek")
movie1.title

movie1 = new Movie1(1)
movie1.title
val movie2 = Movie2("shrek")
movie2.title
movie2.id




def mirrorList(x: List[Int]): List[Int] = {

  if(x.reverse == x){
    x
  }
  else {
    x.sortWith(_>_) ::: x.sortWith(_<_)
  }
}





