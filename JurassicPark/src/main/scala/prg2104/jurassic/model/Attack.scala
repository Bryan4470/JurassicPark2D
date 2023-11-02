package prg2104.jurassic.model

//Deals damage to the opponent
class Attack(a: Int) extends Skill(a){
    skillName = "Attack!"
    comments = List( s"You used Attack! Enemy took $value damage!",s"Enemy used Attack! You took $value damage!") 

    
    def trigger(dino: Dinosaur) = {

        if(dino.battleShield != 0){
            dino.battleShield -= a
            if(dino.battleShield < 0 ){
                dino.battleShield = 0 //shield broken
            }
        }
        else{      
            dino.battleHealth -= a
        }


    }     
}