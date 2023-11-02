package prg2104.jurassic

import scalafx.event.ActionEvent
import scalafxml.core.macros.sfxml
import scalafx.Includes._
import prg2104.jurassic.model.Dinosaur
import prg2104.jurassic.model.{Trex,Spinosaurus,Triceratops,Pachycephalosaurus,Pteranodon,Sarcosuchus,Player}
import scalafx.scene.image.Image
import scalafx.scene.control.Label
import scalafx.scene.image.ImageView
import scalafx.scene.control.Button
import scalafx.scene.text.Text

@sfxml
class DinoInfoController(
    private val nameLabel: Label,
    private val healthLabel: Label,
    private val damageLabel: Label,
    private val healLabel: Label,
    private val shieldLabel: Label,
    private val levelLabel: Label,
    private val dinoTypeText: Text,
    private val dinoImage: ImageView,
    private val upgradeBtn: Button
    ) {

    var dinoInfo: Dinosaur = null

    var dinoPic: Image = null

    if(dinoInfo != null){
        if(dinoInfo.level == dinoInfo.maxLevel){
            upgradeBtn.text = "Upgrade (Maxed)"
        }
    }
    
    def assign(dino: Dinosaur)={
        dinoInfo = dino
        nameLabel.text = dino.name
        dinoStats()
        dinoPic = new Image(getClass().getResourceAsStream(dino.imagePath()));
        dinoImage.image = dinoPic
    }

    def dinoStats() = {
        healthLabel.text = dinoInfo.baseHealth.toString
        damageLabel.text = dinoInfo.baseDamage.toString
        healLabel.text = dinoInfo.baseHeal.toString
        shieldLabel.text = dinoInfo.baseShield.toString
        levelLabel.text = dinoInfo.level.toString
        dinoTypeText.text = dinoInfo.typeName
    }

    def handleUpgrade(action : ActionEvent) = {
        if(dinoInfo.level<=dinoInfo.maxLevel){
            dinoInfo.levelUp()
            dinoStats()

            if(dinoInfo.level == dinoInfo.maxLevel){
                upgradeBtn.text = "Upgrade (Maxed)"
            }
        }
        else{        
            upgradeBtn.disable
        }
    }

    def handleBack(action : ActionEvent) = {
        Main.showCharacters
    }
}
