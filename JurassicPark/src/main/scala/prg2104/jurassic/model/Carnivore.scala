package prg2104.jurassic.model

//Dinosaur Type
trait Carnivore extends CommonTypeProperties{//strength over herbivore, weakness over amphibians
    val typeName = "Carnivore"
    val typeWeakness = "Amphibian"
    val typeStrength = "Herbivore"
    val criticalAttack = List(10,12,15,20) 
}