package prg2104.jurassic.model

import scala.collection.mutable.ListBuffer

class Spinosaurus extends Dinosaur with Carnivore{

    def this(levels:Int,maxLevel:Int){
        this()
        this.maxLevel = maxLevel
        jumpLevel(levels)
    }
    //base stats details
    var baseHealth: Int = 200
    var baseDamage: Int = 45 
    var baseHeal: Int = 30
    var baseShield: Int = 25
    //manipulated stats during battle
    var battleHealth: Int = baseHealth
    var battleShield: Int = 0
 
    var skills: ListBuffer[Skill] = ListBuffer(new Attack(baseDamage),new Block(baseShield), new Heal(baseHeal),new SpecialAttack(baseDamage))

    def calculateBattleStats() = { //increase battle stats for level up
        baseHealth += 20
        baseDamage += 30
        baseHeal += 12
        baseShield += 10

        battleHealth= baseHealth
        battleShield= 0
    }

    def calculateBattleStats(value:Int) = {//increase battle stats for jump level
        baseHealth += 20*value
        baseDamage += 30*value
        baseHeal += 12*value
        baseShield += 10*value

        battleHealth= baseHealth
        battleShield= 0
    }

    def levelUp() = {//level up 1 level each time
        if(level < maxLevel){
            level+=1;
            calculateBattleStats()
            skills = ListBuffer(new Attack(baseDamage),new Block(baseShield), new Heal(baseHeal),new SpecialAttack(baseDamage))
        }

    }

    def jumpLevel(lvl:Int)={//level up based on the argument passed in    
        
        level=lvl;
        calculateBattleStats(level-1)
        skills = ListBuffer(new Attack(baseDamage),new Block(baseShield), new Heal(baseHeal),new SpecialAttack(baseDamage))
        
    }

    def imagePath() = {
        Spinosaurus.imagePath
    }
    def name() = {
        Spinosaurus.name    
    }
}
object Spinosaurus{
    val name = "Spinosaurus"
    var imagePath: String = "view/images/dino/spinosaurus.png"
}