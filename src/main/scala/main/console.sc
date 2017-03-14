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
  val y = x+1
  if(y >= 3) true else false
}

unBool

/*
Exercice 1
Stocker dans une val la chaine "www.example.com/1/2/3/4" en l'initialisant à "www.example.com"
 */
val exo1 = {
  var i = "www.example.com"
  val sep = "/"
  for(j <- 1 to 9) i = i + sep + j
  i
}