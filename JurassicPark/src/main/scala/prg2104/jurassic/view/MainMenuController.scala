package prg2104.jurassic

import scalafx.event.ActionEvent
import scalafxml.core.macros.sfxml


@sfxml
class MainMenuController {
  def handleScore(action : ActionEvent) = {
    Main.showScoreboard()
  }
  def handlePlay(action : ActionEvent) = {
    Main.showCharacters()
  }
  def handleGuide(action : ActionEvent) = {
    Main.showGuide()
  }
  def handleLogOut(action : ActionEvent) = {
    Main.showStartMenu()
  }
}
