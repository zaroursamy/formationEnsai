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

val samiaP: Person = Femme("samia", 22)
val samiaF: Femme = Femme("samia", 22)
val samy: Homme = Homme("samy", 24)
val john = Homme("John", 20)

/* Les fonctions de premier ordre */
def isJohn(p: Person) = p.name == "John"
def isJohn2 = new Function[Person, Boolean] {
  def apply(p: Person): Boolean = p.name == "John"
}
def isJohn3: (Person) => Boolean = (p: Person) => p.name == "John"


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




