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

  def accoucher: Boolean

  def call(receiver: Person): Unit = println(this.name + " appelle " + receiver.name)
}

case class Femme(name: String, age: Int) extends Person {
  override def accoucher: Boolean = true

  def faireLesBoutiques = name + " achète des louboutins"
}

case class Homme(name: String, age: Int) extends Person {
  override def accoucher: Boolean = false

  def jouerJeuxVideos = name + " joue à lol"
}

case class HommeFemme(name: String, age: Int)


val samiaP: Person = Femme("samia", 22)
val samiaF: Femme = Femme("samia", 22)
samiaF.age
samiaF.faireLesBoutiques
val samy: Homme = Homme("samy", 24)
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

def pm2(liste: List[Int], a: Int) = liste match {
  case Nil => liste
  case List(0, _) => 0
  case List(a, _*) => a
  case t :: ts => t
}

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
  case BinOp("-", Number(0), e) => e
  case BinOp("*", Number(1), e) => e
  case _ => expr
}

def describe(e: Expr): String = e match {
  case Number(_) => "a number"
  case Var(_)    => "a variable"
  case _ => "not a number, not a variable"
}


/* Les fonctions de premier ordre */
def isJohn(p: Person) = p.name == "John"
def isJohn2 = new Function[Person, Boolean] {
  def apply(p: Person): Boolean = p.name == "John"
}
def isJohn3: (Person) => Boolean = (p: Person) => p.name == "John"

val john = Homme("John", 20)
isJohn(john)
isJohn2(john)
isJohn3(john)

println(List(john, samiaF, samy, samiaP).filter(isJohn2))

/* Ecrire une fonction qui prend une personne et un string, renvoie son prénom ajouté du string
 * au début si c un homme et a la fin si c une femme
 * Si c'est John alors elle renvoie son name
 * */
def composerNom(p: Person, s: String = "X") = p match { // attention a l'ordre

  case p: Person if isJohn(p) => p.name
  case Homme(n, _) => s+n
  case Femme(n, _) => n+s

}

/* Currying */
def composerNom2(s: String = "X")(p: Person) = p match { // attention a l'ordre

  case p: Person if isJohn(p) => p.name
  case Homme(n, _) => s+n
  case Femme(n, _) => n+s

}

composerNom(john)
composerNom(samy)
composerNom(samiaF)
composerNom(samiaP)

def composerNom2Curry(): (Person) => String = composerNom2("X")_

def isFemmeNotJohn(f: (Person) => String, p:Person): Boolean = {
  if(f(p).endsWith("X")) true else false
}

isFemmeNotJohn(composerNom2Curry(), samiaF)
isFemmeNotJohn(composerNom2Curry(), john)

/* Les collections */
val listePersonnes: List[Person] = List(john, samiaF, samiaP, samy)

def voirPersonnes(l: List[Person]): List[String] = {

  def transformPersonne(p: Person): String = p.name+ " a " + p.age + " ans."

  l.map(transformPersonne)
}

voirPersonnes(listePersonnes)


