/*
* Exemple 4: les classes (exercice cf utils)
*/

class Movie1(val title: String, id: Long = 0) {

  // constructeur
  def this(id: Long) = this("shrek " + id, id)
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

/* Exercice */

import utils.Stat._

val seqStatInt: List[Int] = List(10, 20)
seqStatInt.mean

/* Les classes abstraites */
abstract class Person {
  val name: String
  val age: Int
  def mettreAuMonde: Boolean
  def call(receiver: Person): Unit = println(this.name + " appelle " + receiver.name)
}

case class Femme(name: String, age: Int) extends Person {
  override def mettreAuMonde: Boolean = true
  def faireLesBoutiques = name + " achète des louboutins"
}

case class Homme(name: String, age: Int) extends Person {
  override def mettreAuMonde: Boolean = false
  def jouerJeuxVideos = name + " joue à lol"
}

case class HommeFemme(name: String, age: Int)


val samiaP: Person = Femme("samia", 22)
val samiaF: Femme = Femme("samia", 22)
val samy: Homme = Homme("samy", 24)
val john = Homme("John", 20)

samiaF.age
samiaF.faireLesBoutiques
samy call samiaF
samy call samiaP

object Pc extends Enumeration {
  val apple = Value("Steve")
  val microsoft = Value("Bill")
}

println(Pc.apple)

/* Pattern matching */
def pm1(x: Any) = x match {
  case s: String => x
  case i: Int => i
  case _ => "autre"
}

/* Définir une fonction qui prend une List[Int] et un entier a et qui renvoie:
la liste si elle est vide
0 si la liste commence par 0
a si la liste commence par a
-1 si la liste commence par un autre entier
 */
def pm2(liste: List[Int], a: Int) = liste match {
  case Nil => liste
  case List(0, _) => 0
  case List(a, _*) => a
  case t :: ts => -1
}

/*
Réécrire la fonction max
 */
def max(l: List[Int]): Int = l match {
  case Nil => 0
  case t :: Nil => t
  case t :: s :: ll if t > s => max(t :: ll)
  case t :: s :: ll if s > t => max(s :: ll)
}


println("max liste 1,10,4 " + max(List(1, 10, 4)))


/*
Exerice 4: Ecrire la simplification des opérations de base sur R
 */
sealed abstract class Expr

case class Var(name: String) extends Expr

case class Number(num: Double) extends Expr

case class UnOp(operator: String, arg: Expr) extends Expr

case class BinOp(operator: String, left: Expr, right: Expr) extends Expr

def simplifyNeutre(expr: Expr) = expr match {
  case UnOp("-", UnOp("-", e)) => e // match ttes les valeurs de type UnOp ayant pr premier param "-" et pr second param  ttes les valeurs de type UnOp(...).."-" et e
  case BinOp("+", Number(0), e) => e //* tous les arguments du constructeur sont des patterns
  case BinOp("-", e, Number(0)) => e
  case BinOp("*", Number(1), e) => e
  case _ => expr
}

val x = Var("x")
println(simplifyNeutre(x) == x)

def describe(e: Expr): String = e match {
  case Number(_) => "a number"
  case Var(_)    => "a variable"
  case _ => "not a number, not a variable"
}

/* Fizzbuzz */
def fizzBuzz(l: Int) = (l%3, l%5) match {
  case (0, 0) => "fizzbuzz"
  case (0, _) => "fizz"
  case (_, 0) => "buzz"
  case _ => ""
}
