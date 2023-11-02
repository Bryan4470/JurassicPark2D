package prg2104.jurassic.model

//Add health to the dinosaur
class Heal(a: Int) extends Skill(a){

    skillName = "Heal!"
    comments = List(s"You used Heal! You healed $value hp!",s"Enemy used Heal! Enemy healed $value hp!")

    def trigger(dino:Dinosaur) = {

        dino.battleHealth += a

        if(dino.battleHealth > dino.baseHealth){
            dino.battleHealth = dino.baseHealth      
        }
    }
}