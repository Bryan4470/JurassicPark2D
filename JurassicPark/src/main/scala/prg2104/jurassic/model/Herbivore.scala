package prg2104.jurassic.model

//Dinosaur Type
trait Herbivore extends CommonTypeProperties{ //strength over pterosaurs, weakness over carnivore
    val typeName = "Herbivore"
    val typeWeakness = "Carnivore"
    val typeStrength = "Pterosaur"
    val criticalAttack = List(10,10,18,30) //addition to att po
}