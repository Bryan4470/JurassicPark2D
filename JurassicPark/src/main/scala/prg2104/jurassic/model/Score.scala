package prg2104.jurassic.model

import scalafx.collections.ObservableBuffer
import scalafx.beans.property.StringProperty
import scalafx.beans.property.IntegerProperty
import scalafx.beans.property.DoubleProperty
import scala.io.Source
import java.io.File
import java.io.FileWriter
import java.io.BufferedWriter

//each battle played by the player will be considered as 1 object of ScoreboardItem
class Score(var usernameS: String, var dinosaurS: String, var gameModeS: String, var levelsCompletedS: String){ //these information will contain information for 1 game for each time
    var username  = new StringProperty(usernameS)
    var dinosaur   = new StringProperty(dinosaurS) 
    var gameMode  = new StringProperty(gameModeS)
    var levelsCompleted   = new StringProperty(levelsCompletedS) 
}

object Score{
    val scoreboard = new ObservableBuffer[Score]()

    def readFile(){
        val file = Source.fromFile("src/main/scala/prg2104/jurassic/model/scores.csv")
        val lecturerRecords = file.getLines.toVector
        file.close()

        //Get the header record in the file into elements in vector
        val fileHeader = lecturerRecords(0).split(',').toVector

        //Set index for each elements in the header
        val usernameIndex = fileHeader.indexOf("Username")
        val dinosaurIndex = fileHeader.indexOf("Dinosaur")
        val gameModeIndex = fileHeader.indexOf("Game Mode")
        val levelsCompletedIndex = fileHeader.indexOf("Levels Completed")

        for(x <- lecturerRecords.drop(1)){
            val splitEntry = x.split(',').toVector         
            Score.scoreboard += new Score(splitEntry(usernameIndex),splitEntry(dinosaurIndex),splitEntry(gameModeIndex), splitEntry(levelsCompletedIndex))
        }
    }

    def writeFile(){
        val file = new File("src/main/scala/prg2104/jurassic/model/scores.csv")
        val bw = new BufferedWriter(new FileWriter(file))
        bw.write("Username,Dinosaur,Game Mode,Levels Completed\n")
        for(line <- scoreboard){
            
            bw.write(s"${line.usernameS},${line.dinosaurS},${line.gameModeS},${line.levelsCompletedS}\n")
        }
        bw.close()
    }

}

 