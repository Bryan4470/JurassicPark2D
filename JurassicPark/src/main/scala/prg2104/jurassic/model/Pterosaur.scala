package prg2104.jurassic.model

//Dinosaur Type
//Pterosaurs are flying dinosaurs
trait Pterosaur extends CommonTypeProperties{ //strength over amphibians, weaknesses over herbivore
    val typeName = "Pterosaur"
    val typeWeakness = "Herbivore"
    val typeStrength = "Amphibian"
    val criticalAttack = List(10,10,18,25) //addition to att po
}