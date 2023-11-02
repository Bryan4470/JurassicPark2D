package prg2104.jurassic

import scalafx.event.ActionEvent
import scalafxml.core.macros.sfxml


@sfxml
class StartMenuController {
  def handleStart(action : ActionEvent) = {
    Main.showLogin()
  }

}