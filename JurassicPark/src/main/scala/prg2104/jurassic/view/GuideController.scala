package prg2104.jurassic

import scalafx.event.ActionEvent
import scalafxml.core.macros.sfxml
import scalafx.stage.Stage
import scalafx.Includes._


@sfxml
class GuideController {
    def handleBack(action : ActionEvent) = { 
        Main.showMainMenu()
    }
}