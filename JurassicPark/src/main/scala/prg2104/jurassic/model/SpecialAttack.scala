package prg2104.jurassic.model

//this skill deals [ normal damage (same as attack damage) / reduced damage / critical damage ] 
//normal damage = opponent is not our weakness / strength
//reduced damage = our weakness is equal to opponent's type
//critical damage = our strength is equal to opponent's type
class SpecialAttack(a: Int) extends Skill(a){  

    skillName = "Special Attack!"
    comments = List( s"You used Special Attack! Enemy took $a damage!",s"Enemy used Special Attack! You took $a damage!")
    var specialComment = ""

    def trigger(dino: Dinosaur, selfDino: Dinosaur) = { //dino is the victim, selfDino is the attacker
        val r = new scala.util.Random
        val r1 = r.nextInt(selfDino.criticalAttack.length)
        val enemyType = dino.typeName
        var criticalAttackValue = selfDino.criticalAttack(r1)

        if(enemyType.equals(selfDino.typeName)){  //normal damage
            if(dino.battleShield != 0){
                dino.battleShield -= a
                if(dino.battleShield < 0 ){ //shield broken
                    dino.battleShield = 0 
                }
            }
            else{
                dino.battleHealth -= a
            }
            comments = List( s"You used Special Attack! Enemy took $a damage!",s"Enemy used Special Attack! You took $a damage!")
            specialComment = "Normal Effect!"     
        }
        else if(selfDino.typeStrength == enemyType){ //if it is the strength of our dino, increased damage, shield penetration

            if(dino.battleShield != 0){
                var currentShield = dino.battleShield
                var leftOverDamage = (a+criticalAttackValue) - dino.battleShield
                
                dino.battleShield -= (a+criticalAttackValue)
                if(dino.battleShield < 0 ){
                    dino.battleShield = 0 //shield broken
                    dino.battleHealth -= leftOverDamage //penetrate shield
                }
            }
            else{
                dino.battleHealth -= (a+criticalAttackValue)

            }
            comments = List( s"You used Special Attack! Enemy took ${a+criticalAttackValue} damage!",s"Enemy used Special Attack! You took ${a+criticalAttackValue} damage!")
            specialComment = "Critical Hit! Increased Damage!"
            
        }
        else if(selfDino.typeWeakness == enemyType){ //if it is the weakness of our dino
            //reduced damage
            if(dino.battleShield != 0){
                    dino.battleShield -= (a-criticalAttackValue)
                    if(dino.battleShield < 0 ){
                        dino.battleShield = 0 //shield broken
                    }
                }
                else{
                    dino.battleHealth -= (a-criticalAttackValue)
                }
            comments = List( s"You used Special Attack! Enemy took ${a-criticalAttackValue} damage!",s"Enemy used Special Attack! You took ${a-criticalAttackValue} damage!")
            specialComment = "Not so effective! Reduced Damage!"
            
        }
        else{ //normal damage

            if(dino.battleShield != 0){
                dino.battleShield -= a
                if(dino.battleShield < 0 ){
                    dino.battleShield = 0 //shield broken
                        
                }
            }
            else{
                dino.battleHealth -= a
            }
            comments = List( s"You used Special Attack! Enemy took $a damage!",s"Enemy used Special Attack! You took $a damage!")
            specialComment = "Normal Effect!"
            
        }

    } 
    def trigger(dino: Dinosaur): Unit = ???
}