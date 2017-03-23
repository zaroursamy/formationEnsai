/*
*Exemple 1: val et var
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

/*
* Exercice 1
* Stocker dans une val l'alphabet séparé de - en l'initialisant à "0"
*/
val exo1 = {
  var i = ""
  val sep = "-"
  for (j <- 'a' to 'z') i = i + sep + j
  i
}

/*
Exemple 2: les fonctions
 */

def addS(s: String): String = s + "s"
val pluriel = addS("ensaien")

def fSide() {
  "ok"
}
def fNoSide() = {
  "ok"
}

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
*Exercice 2: Ecrire la fonction factorielle
 */
def factorielle(n: Int): Int = {
  if (n == 0) 1 else n * factorielle(n - 1)
}

val fact4 = factorielle(4)

/*
* Exercice 3: Palindromes
* Si a liste est un palindrome, la retourner
* Sinon, faire de cette liste un palindrome etl a retourner
* */
def mirrorList(list: List[Int], pair: Boolean = false): List[Int] = {
  if (list.reverse == list) list
  else {
    if (pair) list.sortWith(_ > _) ::: list.sortWith(_ < _)
    else list.sortWith(_ > _) ::: list.sortWith(_ < _).tail
  }
}

/* Fonction reverse */
def reversePerso(l: List[Int]): List[Int] = {
  l match {
    case Nil => Nil
    case t :: ts => reversePerso(ts) ::: List(t)
  }
}

val list1 = List(4, 3, 2, 3, 4)
val list2 = List(1, 3, 5)

mirrorList(list1)
mirrorList(list2)
mirrorList(list2, true)
println(reversePerso(list2))
