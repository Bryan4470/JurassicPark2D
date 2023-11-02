package prg2104.jurassic

import scalafx.event.ActionEvent
import scalafxml.core.macros.sfxml
import scalafx.scene.control.TableView
import prg2104.jurassic.model.Score
import scalafx.scene.control.TableColumn
import scalafx.scene.control.Label
import scalafx.Includes._


@sfxml
class ScoreboardController(
    private val scoreboardTable: TableView[Score],
    private val playerColumn: TableColumn[Score, String],
    private val modeColumn: TableColumn[Score, String],
    private val dinosaurColumn: TableColumn[Score, String],
    private val levelColumn: TableColumn[Score, String],  
    private val playerLabel: Label,
    private val modeLabel: Label,
    private val dinosaurLabel: Label,
    private val levelLabel: Label

    ) {
    
    scoreboardTable.items = Score.scoreboard
    playerColumn.cellValueFactory = {_.value.username}
    dinosaurColumn.cellValueFactory  = {_.value.dinosaur} 
    modeColumn.cellValueFactory  = {_.value.gameMode} 
    levelColumn.cellValueFactory  = {_.value.levelsCompleted} 

    showScoreboardDetails(None)
    
    scoreboardTable.selectionModel().selectedItem.onChange(
      (_, _, newValue) => showScoreboardDetails(Some(newValue))
    )
    

    private def showScoreboardDetails (item : Option[Score]) = {
    item match {
      case Some(item) =>
      // Fill the labels with info from the person object.
      playerLabel.text <== item.username
      modeLabel.text <== item.dinosaur
      dinosaurLabel.text <== item.gameMode
      levelLabel.text <== item.levelsCompleted

      case None =>
        // Person is null, remove all the text.
      playerLabel.text = ""
      modeLabel.text  = ""
      dinosaurLabel.text    = ""
      levelLabel.text= ""

    }    
  }

  def handleBack(action : ActionEvent) = {
      Main.showMainMenu()
  }

}