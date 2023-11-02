package prg2104.jurassic

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
import prg2104.jurassic.model.Dinosaur
import prg2104.jurassic.model.{Trex,Spinosaurus,Triceratops,Pachycephalosaurus,Pteranodon,Sarcosuchus}
import prg2104.jurassic.model.Score
import scalafx.scene.image.Image

object Main extends JFXApp {
  Score.readFile()
  // initialize the loader object.
  val loader = new FXMLLoader(getClass.getResource("view/RootLayout.fxml"), NoDependencyResolver)
  // Load root layout from fxml file.
  loader.load();
  
  val roots = loader.getRoot[javafx.scene.layout.BorderPane]

  // initialize stage
  stage = new PrimaryStage {
    title = "Jurassic Park Game"
    icons += (new Image(getClass().getResourceAsStream("view/images/jurassicparklogo.png")))
    scene = new Scene {
      root = roots
    }
  }

  //First Page (Welcome / Login Page)
  def showStartMenu() = {
    val resource = getClass.getResource("view/StartMenu.fxml")
    val loader = new FXMLLoader(resource, NoDependencyResolver)
    loader.load();
    val roots = loader.getRoot[javafx.scene.layout.AnchorPane]
    this.roots.setCenter(roots)
  }
  showStartMenu()

  //Login PopUp Page
  def showLogin() = {
    val resource = getClass.getResourceAsStream("view/Login.fxml")
    val loader = new FXMLLoader(null, NoDependencyResolver)
    loader.load(resource);
    val roots2  = loader.getRoot[javafx.scene.layout.AnchorPane]
    val control = loader.getController[LoginController#Controller]

    val dialog = new Stage() {
      initModality(Modality.APPLICATION_MODAL)
      initOwner(stage)
      scene = new Scene {
        root = roots2
      }
    }
    control.dialogStage = dialog
    dialog.showAndWait()
    
  }

  //Main Menu
  def showMainMenu() = {
    val loader = new FXMLLoader(getClass.getResource("view/MainMenu.fxml"), NoDependencyResolver)
    loader.load();
    val roots = loader.getRoot[javafx.scene.layout.AnchorPane]
    this.roots.setCenter(roots)
  }

  //Dinosaur Selection Page
  def showCharacters() = {
    val resource = getClass.getResource("view/Characters.fxml")
    val loader = new FXMLLoader(resource, NoDependencyResolver)
    loader.load();
    val roots = loader.getRoot[javafx.scene.layout.AnchorPane]
    this.roots.setCenter(roots)
  }

  //Dinosaur Information & Upgrade Page
  def showDinoInfo(dino: Dinosaur) = {
    val resource = getClass.getResource("view/DinoInfo.fxml")
    val loader = new FXMLLoader(resource, NoDependencyResolver)
    loader.load();
    val roots = loader.getRoot[javafx.scene.layout.AnchorPane]
    val control = loader.getController[DinoInfoController#Controller]
    control.assign(dino)
    this.roots.setCenter(roots)
  }

  //Choose Game Mode Page
  def showMap() = {
    val resource = getClass.getResource("view/Map.fxml")
    val loader = new FXMLLoader(resource, NoDependencyResolver)
    loader.load();
    val roots = loader.getRoot[javafx.scene.layout.AnchorPane]
    this.roots.setCenter(roots)
  }
  
  //Battleground Page
  def showBattleground(mode: String) = {
    val resource = getClass.getResource("view/Battleground.fxml")
    val loader = new FXMLLoader(resource, NoDependencyResolver)
    loader.load();
    val roots = loader.getRoot[javafx.scene.layout.AnchorPane]
    val control = loader.getController[BattlegroundController#Controller]
    control.gameMode(mode)
    this.roots.setCenter(roots)
  }
  
  //Game Guide Page
  def showGuide()= {
    val resource = getClass.getResource("view/Guide.fxml")
    val loader = new FXMLLoader(resource, NoDependencyResolver)
    loader.load();
    val roots = loader.getRoot[javafx.scene.layout.AnchorPane]
    this.roots.setCenter(roots)
  }

  //Scoreboard Page
  def showScoreboard() = {
    val resource = getClass.getResource("view/Scoreboard.fxml")
    val loader = new FXMLLoader(resource, NoDependencyResolver)
    loader.load();
    val roots = loader.getRoot[javafx.scene.layout.AnchorPane]
    this.roots.setCenter(roots)
  } 

}