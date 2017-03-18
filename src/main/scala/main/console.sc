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

unBool

/*
* Exercice 1
* Stocker dans une val la chaine "0/1/2/3/4" en l'initialisant à "0"
*/
val exo1 = {
  var i = "0"
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
    if(pair)list.sortWith(_ > _) ::: list.sortWith(_ < _)
    else list.sortWith(_ > _) ::: list.sortWith(_ < _).tail
  }
}

val list1 = List(4,3,2,3,4)
val list2 = List(1,3,5)

mirrorList(list1)
mirrorList(list2)
mirrorList(list2, true)
/*
* Exemple 4: les classes
*/

class Movie1(val title: String, id: Long = 0) {

  // constructeur
  def this(id: Long) = this("shrek "+id, id)
}

case class Movie2(title: String, id: Long = 0)

var movie1 = new Movie1("shrek")
movie1.title

movie1 = new Movie1(1)
movie1.title
val movie2 = Movie2("shrek")
movie2.title
movie2.id
movie2.copy(id = 9).id

/* Les traits: définir un comportement  */
trait Animal {
  // pas de parametre de constructeur
  def communicate: String

  val nbPattes: Option[Int] = None
}

trait Mamifere extends Animal {
  def allaiter: String = "ttt tttt" // on peut implémenter la méthode dans le trait
}

class Cat extends Mamifere {
  override def communicate: String = "miaou"

  override val nbPattes: Option[Int] = Some(4)
}

class Dog extends Mamifere {
  override def communicate: String = "wouf"

  override val nbPattes: Option[Int] = Some(4)
}

class Baleine extends Mamifere {
  override def communicate = "mmmmmmmmm"
  override val nbPattes: Option[Int] = None
}

val cat = new Cat
cat.nbPattes
cat.communicate
cat.allaiter

val dog = new Dog
dog.nbPattes
dog.communicate

/* Les classes abstraites */
abstract class Person{
  val name: String
  val age: Int
  def accoucher: Boolean
}

case class Femme(name: String, age: Int) extends Person{
  override def accoucher: Boolean = true

  def faireLesBoutiques = "louboutin"
}
case class Homme(name: String, age: Int) extends Person{
  override def accoucher: Boolean = false

  def jouerJeuxVideos = "lol"
}

val samia: Person = Femme("samia", 22)
val samia2: Femme = Femme("samia", 22)
samia2.age
samia2.faireLesBoutiques

val samiaVieille = samia2.copy(age = 89)
samiaVieille.age

import utils.Stat._

val seqStatInt: List[Int] = List(10, 20)
seqStatInt.mean




