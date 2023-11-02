package prg2104.jurassic


import scalafx.event.ActionEvent
import scalafxml.core.macros.sfxml
import scalafx.scene.control.TextField
import scalafx.stage.Stage
import scalafx.scene.text.Text
import prg2104.jurassic.model.Player

@sfxml
class LoginController(
    private val nameField: TextField,
    private val errorMessage: Text
    ) {

    var dialogStage: Stage  = null
    
    def handleName(action : ActionEvent) = { 
        if(nameField.text() != ""){
            Player.username = nameField.text()
            dialogStage.close()
            Main.showMainMenu()
        }
    }

}