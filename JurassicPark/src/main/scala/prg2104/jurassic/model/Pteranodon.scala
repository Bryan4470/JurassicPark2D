package prg2104.jurassic.model

import scala.collection.mutable.ListBuffer

class Pteranodon extends Dinosaur with Pterosaur{

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
        baseDamage += 40
        baseHeal += 15
        baseShield += 20

        battleHealth= baseHealth
        battleShield= 0
    }

    def calculateBattleStats(value:Int) = {//increase battle stats for jump level
        baseHealth += 30*value
        baseDamage += 40*value
        baseHeal += 15*value
        baseShield += 20*value

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

    def jumpLevel(lvl:Int)={ //increase battle stats for jump level     
        level=lvl;
        calculateBattleStats(level-1)
        skills = ListBuffer(new Attack(baseDamage),new Block(baseShield), new Heal(baseHeal),new SpecialAttack(baseDamage))   
    }

    def imagePath() = {
        Pteranodon.imagePath
    }
    def name() = {
        Pteranodon.name
    }
}
object Pteranodon{
    val name = "Pteranodon"
    var imagePath: String = "view/images/dino/pteranodon.png"
}