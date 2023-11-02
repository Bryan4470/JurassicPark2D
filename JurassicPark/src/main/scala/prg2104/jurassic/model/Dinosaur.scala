
package prg2104.jurassic.model

import scala.collection.mutable.ListBuffer

abstract class Dinosaur extends CommonTypeProperties{
    //base stats details
    var baseHealth: Int
    var baseDamage: Int
    var baseHeal: Int
    var baseShield: Int

    //battle stats
    var battleHealth: Int 
    var battleShield: Int

    //current level and maximum level possible
    var level: Int = 1
    var maxLevel: Int = 10

    var skills: ListBuffer[Skill] 

    def calculateBattleStats(): Unit //increase battle stats for level up

    def calculateBattleStats(value: Int): Unit //increase battle stats for jump level

    def levelUp(): Unit //level up 1 level each time

    def jumpLevel(levels: Int): Unit //level up based on the argument passed in    

    def imagePath():String //get image path from singleton object

    def name(): String //get dinosaur name from singleton object

}
object Dinosaur{
    var name: String = "Dinosaur"
    var imagePath: String = "view/images/dino/"
}







