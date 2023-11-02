package prg2104.jurassic.model

//inherited by Carnivore, Herbivore, Pterosaur,Amphibian traits and Dinosaur Abstract Class
trait CommonTypeProperties{
    val typeName: String
    val typeWeakness: String
    val typeStrength: String
    val criticalAttack: List[Int]
}