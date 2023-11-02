package prg2104.jurassic.model

import scala.collection.mutable.ListBuffer

//since it is a single player game (Player vs Computer), we use a singleton object 
object Player{ 
var username: String = ""
var dinosaur: Dinosaur = null //selected dinosaur
var gameMode = ""
var levelsCompleted = 0

//possible dinosaur selections
var dinosaurInventory: ListBuffer[Dinosaur] = ListBuffer(new Trex, new Spinosaurus,new Triceratops,new Pachycephalosaurus ,new Pteranodon ,new Sarcosuchus)

def reset() = {
    dinosaurInventory = ListBuffer(new Trex, new Spinosaurus,new Triceratops,new Pachycephalosaurus ,new Pteranodon ,new Sarcosuchus)
}
}