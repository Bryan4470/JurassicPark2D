package prg2104.jurassic.model

abstract class Skill(var value: Int){
    var skillName: String = "Skill name"
    var comments: List[String] = List("item1","item2")
    def trigger(dino: Dinosaur): Unit 
}