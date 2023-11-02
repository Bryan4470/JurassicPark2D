package prg2104.jurassic.model

import scala.collection.mutable.ListBuffer

class Pachycephalosaurus extends Dinosaur with Herbivore{

    def this(levels:Int,maxLevel:Int){
        this()
        this.maxLevel = maxLevel
        jumpLevel(levels)
    }
    //base stats details
    var baseHealth: Int = 250
    var baseDamage: Int = 35
    var baseHeal: Int = 30
    var baseShield: Int = 40
    //manipulated stats during battle
    var battleHealth: Int = baseHealth
    var battleShield: Int = 0
 
    var skills: ListBuffer[Skill] = ListBuffer(new Attack(baseDamage),new Block(baseShield), new Heal(baseHeal),new SpecialAttack(baseDamage))

    def calculateBattleStats() = { //increase battle stats for level up
        // increase health, damage, heal, shield
        baseHealth += 40
        baseDamage += 15
        baseHeal += 12
        baseShield += 15

        battleHealth= baseHealth
        battleShield= 0
    }

    def calculateBattleStats(value:Int) = {//increase battle stats for jump level
        baseHealth += 40*value
        baseDamage += 15*value
        baseHeal += 12*value
        baseShield += 15*value

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
        Pachycephalosaurus.imagePath
    }
    def name() = {
        Pachycephalosaurus.name
    }
}
object Pachycephalosaurus{
    val name = "Pachycephalosaurus"
    var imagePath: String = "view/images/dino/pachycephalosaurus.png"
}