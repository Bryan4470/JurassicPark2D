package prg2104.jurassic

import scalafx.event.ActionEvent
import scalafxml.core.macros.sfxml
import scalafx.scene.image.ImageView;
import scalafx.scene.image.Image
import scalafx.scene.text.Text
import scalafx.scene.control.ProgressBar
import scalafx.Includes._
import prg2104.jurassic.model.Dinosaur
import prg2104.jurassic.model.{Trex,Spinosaurus,Triceratops,Pachycephalosaurus,Pteranodon,Sarcosuchus}
import prg2104.jurassic.model.{Attack,Block,Heal,Player,Enemy,SpecialAttack}
import prg2104.jurassic.model.Score
import scalafx.scene.control.Alert
import scalafx.scene.control.ButtonType
import scala.util.Random
import scala.annotation.switch
import java.math.BigDecimal
import scalafx.scene.layout.AnchorPane
import scalafx.application.HostServices
import scalafx.scene.control.Label
import scalafx.scene.transform.Translate
import scalafx.scene.transform.Rotate


@sfxml
class BattlegroundController(
    private val playerImage: ImageView,
    private val enemyImage: ImageView,
    private val playerHealthBar: ProgressBar,
    private val enemyHealthBar: ProgressBar,
    private val playerShieldBar: ProgressBar,
    private val enemyShieldBar: ProgressBar,
    private val backgroundImage: ImageView,
    private val playerSkillLabel: Label,
    private val enemySkillLabel: Label,
    private val levelLabel: Label,
    private val playerSkillLabel2: Label,
    private val enemySkillLabel2: Label,
    private val enemySkillLabel3: Label,
    private val playerSkillLabel3: Label


    ) {
    //initialize
    playerHealthBar.setStyle("-fx-accent: blue;")
    enemyHealthBar.setStyle("-fx-accent: red;")
    playerShieldBar.setStyle("-fx-accent: green;")
    enemyShieldBar.setStyle("-fx-accent: green;")

    var gameLevel = 0 // level 1 starts from 0
    var currentGameMode = ""
    var totalLevel = 0

    //initialize healthbar/shield bar of player(You) and enemy(computer)
    var progress:BigDecimal = new BigDecimal("%.2f".format(1.0))
    var progressE: BigDecimal = new BigDecimal("%.2f".format(1.0))
    var shieldProgress: BigDecimal = new BigDecimal("%.2f".format(0.0))
    var shieldProgressE: BigDecimal = new BigDecimal("%.2f".format(0.0))
    // var dinoType: String = null
    var dinoInstance: Dinosaur = null

    var playerSpecialAttack:SpecialAttack = null
    var enemySpecialAttack:SpecialAttack = null

    var enemy: Dinosaur = null
    
    //set player's dino (your dino)
    dinoInstance = Player.dinosaur
    val playerPic = new Image(getClass().getResourceAsStream(dinoInstance.imagePath()))
    playerImage.image = playerPic

    var enemyPic:Image = null //store enemy dino image
    var myImage: Image = null //store background image
    
    levelLabel.text = s"Level ${gameLevel+1}"
    
    def gameMode(mode: String) = { //Determines the game mode selected

        if(mode == "Easy"){ //easy mode enemy 
            enemy = Enemy.easyEnemy(gameLevel)
            enemyPic = new Image(getClass().getResourceAsStream(enemy.imagePath()));
            enemyImage.image = enemyPic

            myImage = new Image(getClass().getResourceAsStream("view/images/battlebackground1.png"));
            backgroundImage.image = myImage

            totalLevel = Enemy.easyEnemy.length
            currentGameMode = mode
    
        }
        else if(mode == "Hard"){ //hard mode enemy
            enemy = Enemy.hardEnemy(gameLevel)
            enemyPic = new Image(getClass().getResourceAsStream(enemy.imagePath()));
            enemyImage.image = enemyPic
            
            myImage = new Image(getClass().getResourceAsStream("view/images/battlebackground2.png"));
            backgroundImage.image = myImage

            totalLevel = Enemy.hardEnemy.length
            currentGameMode = mode

        }
    }

    //update player/enemy healthbar and shield bar
    def updateHealthBar() = {
        var currentHealth:Double = (dinoInstance.battleHealth).toDouble/(dinoInstance.baseHealth) //current player health
        var currentEnemyHealth:Double = (enemy.battleHealth).toDouble/(enemy.baseHealth) //current enemy health
        var currentShield:Double = (dinoInstance.battleShield).toDouble/(dinoInstance.baseShield) //current player shield
        var currentEnemyShield:Double = (enemy.battleShield).toDouble/(enemy.baseShield) //current enemy shield

        progress = new BigDecimal("%.2f".format(currentHealth))
        progressE = new BigDecimal("%.2f".format(currentEnemyHealth))
        playerHealthBar.setProgress(progress.doubleValue())
        enemyHealthBar.setProgress(progressE.doubleValue())

        shieldProgress = new BigDecimal("%.2f".format(currentShield))
        shieldProgressE = new BigDecimal("%.2f".format(currentEnemyShield))
        playerShieldBar.setProgress(shieldProgress.doubleValue())
        enemyShieldBar.setProgress(shieldProgressE.doubleValue())

    }
    //player attack
    def handleAttack(action : ActionEvent) = {
        val r = new scala.util.Random
        val r1 = r.nextInt(4)//randomize enemy skill select (attack,block,heal,special attack)
            
        if(r1 == 0){ //enemy use attack
            dinoInstance.skills(0).trigger(enemy)
            enemy.skills(r1).trigger(dinoInstance)           
        }
        else if(r1 == 1){ //enemy use block
            enemy.skills(r1).trigger(enemy)
            dinoInstance.skills(0).trigger(enemy)
        }
        else if(r1 == 2){ //enemy use heal
            dinoInstance.skills(0).trigger(enemy)
            enemy.skills(r1).trigger(enemy)         
        }
        else if(r1 == 3){ //enemy use special attack  
            dinoInstance.skills(0).trigger(enemy)       
            enemySpecialAttack = enemy.skills(r1).asInstanceOf[SpecialAttack]
            enemySpecialAttack.trigger(dinoInstance,enemy)           
        }

        playerSkillLabel.text = playerComment(0,1)
        playerSkillLabel3.text = playerComment(0,2)
        playerSkillLabel3.text = playerComment(0,3)

        enemySkillLabel.text = enemyComment(r1,1)
        enemySkillLabel2.text = enemyComment(r1,2)
        enemySkillLabel3.text = enemyComment(r1,3)

        updateHealthBar()
        checkWinLose()        
    }
    //player block
    def handleBlock(action : ActionEvent) = {
        val r = new scala.util.Random
        val r1 = r.nextInt(4) //randomize enemy skill select (attack,block,heal,special attack)

        dinoInstance.skills(1).trigger(dinoInstance) 

        if(r1 == 0){ //enemy use attack      
            enemy.skills(r1).trigger(dinoInstance)
        }
        else if(r1 == 1){//enemy use heal
            enemy.skills(r1).trigger(enemy) // block off dinoInstance.skills(0).a
        }
        else if(r1 == 2){//enemy use block
            enemy.skills(r1).trigger(enemy)
        }
        else if(r1 == 3){//enemy use special attack        
            enemySpecialAttack = enemy.skills(r1).asInstanceOf[SpecialAttack]
            enemySpecialAttack.trigger(dinoInstance,enemy)        
        }
        playerSkillLabel.text = playerComment(1,1)
        playerSkillLabel2.text = playerComment(1,2)
        playerSkillLabel3.text = playerComment(1,3)

        enemySkillLabel.text = enemyComment(r1,1)
        enemySkillLabel2.text = enemyComment(r1,2)
        enemySkillLabel3.text = enemyComment(r1,3)

        updateHealthBar()
        checkWinLose()    
    }
    //player heal
    def handleHeal(action : ActionEvent) = {

        val r = new scala.util.Random
        val r1 = r.nextInt(4)//randomize enemy skill select (attack,block,heal,special attack)

        dinoInstance.skills(2).trigger(dinoInstance)

        if(r1 == 0){//enemy use special attack
            enemy.skills(r1).trigger(dinoInstance)
        }
        else if(r1 == 1){//enemy use block
            enemy.skills(r1).trigger(enemy)       
        }
        else if(r1 == 2){//enemy use heal
            enemy.skills(r1).trigger(enemy)
        }
        else if(r1 == 3){//enemy use special attack     
            enemySpecialAttack = enemy.skills(r1).asInstanceOf[SpecialAttack]
            enemySpecialAttack.trigger(dinoInstance,enemy) 
        }

        playerSkillLabel.text = playerComment(2,1)
        playerSkillLabel2.text = playerComment(2,2)
        playerSkillLabel3.text = playerComment(2,3)

        enemySkillLabel.text = enemyComment(r1,1)
        enemySkillLabel2.text = enemyComment(r1,2)
        enemySkillLabel3.text = enemyComment(r1,3)
        
        updateHealthBar()
        checkWinLose()      
    }
    //player special attack
    def handleSpecialAttack(action : ActionEvent){
     
        playerSpecialAttack = dinoInstance.skills(3).asInstanceOf[SpecialAttack]
        val r = new scala.util.Random
        val r1 = r.nextInt(4)//randomize enemy skill select (attack,block,heal,special attack)

        if(r1 == 0){//enemy use attack
            playerSpecialAttack.trigger(enemy,dinoInstance)
            enemy.skills(r1).trigger(dinoInstance)    
        }
        else if(r1 == 1){//enemy use block
            enemy.skills(r1).trigger(enemy) 
            playerSpecialAttack.trigger(enemy,dinoInstance)
        }
        else if(r1 == 2){//enemy use heal
            enemy.skills(r1).trigger(enemy)
            playerSpecialAttack.trigger(enemy,dinoInstance)      
        }
        else if(r1 == 3){//enemy use special attack
            playerSpecialAttack.trigger(enemy,dinoInstance)        
            enemySpecialAttack = enemy.skills(r1).asInstanceOf[SpecialAttack]
            enemySpecialAttack.trigger(dinoInstance,enemy)      
        }

        playerSkillLabel.text = playerComment(3,1)     
        playerSkillLabel2.text = playerComment(3,2)
        playerSkillLabel3.text = playerComment(3,3)

        enemySkillLabel.text = enemyComment(r1,1)
        enemySkillLabel2.text = enemyComment(r1,2) 
        enemySkillLabel3.text = enemyComment(r1,3)

        updateHealthBar()
        checkWinLose()
    }

    def updateEnemy(){ //update enemy to next level enemy

        if(currentGameMode == "Easy"){
            enemy = Enemy.easyEnemy(gameLevel)
            enemyPic = new Image(getClass().getResourceAsStream(enemy.imagePath()));
            enemyImage.image = enemyPic
        }
        else if(currentGameMode == "Hard"){
            enemy = Enemy.hardEnemy(gameLevel)
            enemyPic = new Image(getClass().getResourceAsStream(enemy.imagePath()));
            enemyImage.image = enemyPic
        }
       
    }
    
    def switchLevel(){ //switching level
        gameLevel += 1
        progressE = new BigDecimal("%.2f".format(1.0)) 
        enemyHealthBar.setProgress(progressE.doubleValue()) //reset enemy healthbar to max for next dino

        levelLabel.text = s"Level ${gameLevel+1}"
        updateEnemy() 

    }

    def checkWinLose()={ //check whether player/enemy won/lost
        if(dinoInstance.battleHealth <= 0){ //You Lost
            Player.levelsCompleted = gameLevel

            Score.scoreboard += new Score(Player.username,Player.dinosaur.name,Player.gameMode, Player.levelsCompleted.toString)
            Player.reset()
            Enemy.reset()
            Score.writeFile()
            loseMessage()
        }
        else if(enemy.battleHealth <= 0 ){

            if(gameLevel == (totalLevel - 1)){ //last enemy in the easy enemy list - You Won
                Player.levelsCompleted = totalLevel 

                Score.scoreboard += new Score(Player.username,Player.dinosaur.name,Player.gameMode, Player.levelsCompleted.toString)
                Player.reset()
                Enemy.reset()
                Score.writeFile()
                winMessage()
                
            }
            else{
                switchLevel()
            }
        }
        
    }
    //display win message dialog
    def winMessage() = {
        val ButtonTypeOne = new ButtonType("Yes")
        val ButtonTypeTwo = new ButtonType("No")

        val alert = new Alert(Alert.AlertType.Confirmation) {

            title = "You Won!"
            headerText = "you won the battle!"
            contentText = "Would you like to play again?"
            buttonTypes = Seq(
                ButtonTypeOne, ButtonTypeTwo)
        }
        val result = alert.showAndWait()

        result match {
            case Some(ButtonTypeOne)   => {
                Main.showCharacters()
                }
            case Some(ButtonTypeTwo)   => {
                Main.showStartMenu()
                }   
        }
    }
    //display lose message dialog
    def loseMessage() = {
        val ButtonTypeOne = new ButtonType("Yes")
        val ButtonTypeTwo = new ButtonType("No")

        val alert = new Alert(Alert.AlertType.Confirmation) {

            title = "You Lost!"
            headerText = "you lost the battle!"
            contentText = "Would you like to play again?"

            buttonTypes = Seq(
                ButtonTypeOne, ButtonTypeTwo)
        }
        val result = alert.showAndWait()

        result match {
            case Some(ButtonTypeOne)   => {
                Main.showCharacters()
                }
            case Some(ButtonTypeTwo)   => {
                Main.showStartMenu()
                }   
        }
    }

    //handle player comment when using skills
    //first argument takes the index of skill used, label takes the label number (total 3 labels for comments in scene builder)
    def playerComment(skillIndex: Int, label: Int): String={ //skill 0-3, label 1-3
        var returnComment = ""
        if(label == 1){
            returnComment = dinoInstance.skills(skillIndex).comments(0)       
        }
        else if(label == 2){
            if(skillIndex == 3){
                returnComment = playerSpecialAttack.specialComment
            }
            else{
                returnComment = ""
            }
          
        }
        else if(label == 3){
            returnComment = dinoInstance.skills(skillIndex).skillName
            
        }
        returnComment //return the comment
    }

    //handle enemy comment when using skills
    //first argument takes the index of skill used, label takes the label number (total 3 labels for comments in scene builder)
    def enemyComment(skillIndex: Int, label: Int): String={
        var returnComment = ""        
        if(label == 1){
            returnComment = enemy.skills(skillIndex).comments(1)
        }
        else if(label == 2){
            
            if(skillIndex == 3){
                returnComment = enemySpecialAttack.specialComment
            }
            else{
                returnComment = ""
            }
        }
        else if(label == 3){
            returnComment = enemy.skills(skillIndex).skillName
        }
        returnComment //return the comment
    }

    //quit current game 
    def handleBack(action : ActionEvent){
        val ButtonTypeOne = new ButtonType("Yes")
        val ButtonTypeTwo = new ButtonType("No")

        val alert = new Alert(Alert.AlertType.Confirmation) {
            title = "Exit Game"
            headerText = "Exit"
            contentText = "Do you want to exit the game?"
            buttonTypes = Seq(
                ButtonTypeOne, ButtonTypeTwo)
        }
        val result = alert.showAndWait()

        result match {
            case Some(ButtonTypeOne)   => {
                Player.reset()
                Enemy.reset()
                Main.showCharacters()
                }
            case Some(ButtonTypeTwo)   => {}   
        }
    }
}