package prg2104.jurassic.model

//This skill generates a Shield to block off attack 
//Even if a shield is low, and opponent attack damage is higher than the shield, shield will go to 0, your health will not reduce. (cannot penetrate)

//A Shield can only be penetrated when using the "Special Attack" skill on opponent's dinosaur that is your strength based on Dino Type
//if opponent attack damage is higher than the shield, the remaining damage will be dealt to the victim's health
//e.g. Carnivores strength is Herbivore
class Block(a: Int) extends Skill(a){ 

    skillName = "Block!"
    comments = List( s"You used Block! $value hp shield generated!",s"Enemy used Block! $value hp shield generated!")
    
    def trigger(dino: Dinosaur) = {
        dino.battleShield = a
    }
}