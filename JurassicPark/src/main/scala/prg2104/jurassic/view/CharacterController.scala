package prg2104.jurassic

import scalafx.event.ActionEvent
import scalafxml.core.macros.sfxml
import prg2104.jurassic.model.Dinosaur
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafxml.core.NoDependencyResolver
import scalafx.scene.layout.AnchorPane
import scalafxml.core.FXMLLoader
import scalafx.Includes._
import scalafx.scene.Parent
import scalafx.collections.{ObservableBuffer}
import scalafx.stage.{Stage,Modality}
import prg2104.jurassic.model.{Attack,Block,Heal,Player,SpecialAttack}
import prg2104.jurassic.model.{Trex,Spinosaurus,Triceratops,Pachycephalosaurus,Pteranodon,Sarcosuchus}
import scalafx.scene.image.Image
import scalafx.scene.image.ImageView

@sfxml
class CharacterController(
    private val dino1Img: ImageView,
    private val dino2Img: ImageView, 
    private val dino3Img: ImageView,
    private val dino4Img: ImageView, 
    private val dino5Img: ImageView,
    private val dino6Img: ImageView

    ) {
    //get all the dinosaurs
    var dino1 = Player.dinosaurInventory(0)
    var dino2 = Player.dinosaurInventory(1)
    var dino3 = Player.dinosaurInventory(2)
    var dino4 = Player.dinosaurInventory(3)
    var dino5 = Player.dinosaurInventory(4)
    var dino6 = Player.dinosaurInventory(5)

    //display dinosaur images
    val dino1Pic:Image = new Image(getClass().getResourceAsStream(dino1.imagePath()));
    dino1Img.image = dino1Pic
    val dino2Pic:Image = new Image(getClass().getResourceAsStream(dino2.imagePath()));
    dino2Img.image = dino2Pic
    val dino3Pic:Image = new Image(getClass().getResourceAsStream(dino3.imagePath()));
    dino3Img.image = dino3Pic
    val dino4Pic:Image = new Image(getClass().getResourceAsStream(dino4.imagePath()));
    dino4Img.image = dino4Pic
    val dino5Pic:Image = new Image(getClass().getResourceAsStream(dino5.imagePath()));
    dino5Img.image = dino5Pic
    val dino6Pic:Image = new Image(getClass().getResourceAsStream(dino6.imagePath()));
    dino6Img.image = dino6Pic

  def handleDino1(action : ActionEvent) = {
    Player.dinosaur = dino1
    Main.showMap()
  }
  def handleDino2(action : ActionEvent) = {
    Player.dinosaur = dino2
    Main.showMap()
  }
  def handleDino3(action : ActionEvent) = {
    Player.dinosaur = dino3
    Main.showMap()
  }
  def handleDino4(action : ActionEvent) = {
    Player.dinosaur = dino4
    Main.showMap()
  }
  def handleDino5(action : ActionEvent) = {
    Player.dinosaur = dino5
    Main.showMap()
  }
  def handleDino6(action : ActionEvent) = {
    Player.dinosaur = dino6
    Main.showMap()
  }

  def handleInfo1(action: ActionEvent) = {
    Main.showDinoInfo(dino1)
  }
  def handleInfo2(action: ActionEvent) = {
    Main.showDinoInfo(dino2)
  }
  def handleInfo3(action: ActionEvent) = {
    Main.showDinoInfo(dino3)
  }
  def handleInfo4(action: ActionEvent) = {
    Main.showDinoInfo(dino4)
  }
  def handleInfo5(action: ActionEvent) = {
    Main.showDinoInfo(dino5)
  }
  def handleInfo6(action: ActionEvent) = {
    Main.showDinoInfo(dino6)
  }

  def handleBack(action : ActionEvent) = {
    Main.showMainMenu()
  }

}