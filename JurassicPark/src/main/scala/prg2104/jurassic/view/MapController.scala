package prg2104.jurassic

import scalafx.event.ActionEvent
import scalafxml.core.macros.sfxml
import prg2104.jurassic.model.Player

@sfxml
class MapController {
  def handleEasy(action : ActionEvent) = {
    Player.gameMode = "Easy"
    Main.showBattleground("Easy")
  }
  def handleHard(action : ActionEvent) = {
    Player.gameMode = "Hard"
    Main.showBattleground("Hard")
  }

  def handleBack(action : ActionEvent) = {
    Main.showCharacters()
  }

}