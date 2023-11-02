package prg2104.jurassic

import scalafxml.core.macros.sfxml
import scalafx.event.ActionEvent

@sfxml
class RootLayoutController{
    def handleClose(action : ActionEvent){
        System.exit(0)    
    }
}