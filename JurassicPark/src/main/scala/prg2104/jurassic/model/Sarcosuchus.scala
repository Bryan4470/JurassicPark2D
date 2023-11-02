package prg2104.jurassic.model

import scala.collection.mutable.ListBuffer

class Sarcosuchus extends Dinosaur with Amphibian{

    def this(levels:Int,maxLevel:Int){
        this()
        this.maxLevel = maxLevel
        jumpLevel(levels)
    }
    //base stats details
    var baseHealth: Int = 225
    var baseDamage: Int = 45
    var baseHeal: Int = 25
    var baseShield: Int = 40
    //manipulated stats during battle
    var battleHealth: Int = baseHealth
    var battleShield: Int = 0

    var skills: ListBuffer[Skill] = ListBuffer(new Attack(baseDamage),new Block(baseShield), new Heal(baseHeal),new SpecialAttack(baseDamage))

    def calculateBattleStats() = { //increase battle stats for level up
        // increase health, damage, heal, shield
        baseHealth += 30
        baseDamage += 30
        baseHeal += 10
        baseShield += 15

        battleHealth= baseHealth
        battleShield= 0
    }

    def calculateBattleStats(value:Int) = { //increase battle stats for jump level
        baseHealth += 30*value
        baseDamage += 30*value
        baseHeal += 10*value
        baseShield += 15*value

        battleHealth= baseHealth
        battleShield= 0
    }

    def levelUp() = {//increase battle stats for level up
        if(level < maxLevel){
            level+=1;
            calculateBattleStats()
            skills = ListBuffer(new Attack(baseDamage),new Block(baseShield), new Heal(baseHeal),new SpecialAttack(baseDamage))
        }
    }

    def jumpLevel(lvl:Int)={//increase battle stats for jump level    
        level=lvl;
        calculateBattleStats(level-1)
        skills = ListBuffer(new Attack(baseDamage),new Block(baseShield), new Heal(baseHeal),new SpecialAttack(baseDamage))    
    }

    def imagePath() = {
        Sarcosuchus.imagePath
    }
    def name() = {
        Sarcosuchus.name
    }
}
object Sarcosuchus{
    val name = "Sarcosuchus"
    var imagePath: String = "view/images/dino/sarco.png"
}