package prg2104.jurassic.model

//Dinosaur Type
trait Amphibian extends CommonTypeProperties{//strength over carnivore, weaknesses over pterosaurs
    val typeName = "Amphibian"
    val typeWeakness = "Pterosaur"
    val typeStrength = "Carnivore"
    val criticalAttack = List(10,12,15,25)
}