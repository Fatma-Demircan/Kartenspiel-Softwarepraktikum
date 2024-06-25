package view

import service.RootService
import tools.aqua.bgw.components.uicomponents.Button
import tools.aqua.bgw.components.uicomponents.Label
import tools.aqua.bgw.components.uicomponents.TextField
import tools.aqua.bgw.core.BoardGameScene
import tools.aqua.bgw.util.Font
import tools.aqua.bgw.visual.ColorVisual
import java.awt.Color
import kotlin.system.exitProcess

/**
 * creates the end scene of the game
 * @param ueberschrift indicates that the game is over
 * @param platz1 occupied place from player
 * @param platz2 occupied place from player
 * @param platz3 occupied place from player
 * @param platz4 occupied place from player
 * @param exit exitbutton
 * @param newGame button to start a new game
 */

class EndGameScene(rootService: RootService, application: SwimApplication): BoardGameScene(1920, 1080, background = ColorVisual(Color(200,162,200))), Refreshable {
    val rs = rootService
    val app = application

    val ueberschrift = Label(posX = 0, posY= 20, width = 1920, height = 100, text = "Game Over",
        font= Font(size=60))

    val platz1 = Label(posX = 500, posY= 200, width = 920, height = 100, text = "1.Place: Player1  31.pts", font= Font(size=45),
        visual = ColorVisual(Color(215,171,147)))
    val platz2 = Label(posX = 500, posY= 350, width = 920, height = 100, text = "2.Place: Player2", font= Font(size=45),
        visual = ColorVisual(Color(215,171,147)))
    val platz3 = Label(posX = 500, posY= 500, width = 920, height = 100, text = "", font= Font(size=45),
        visual = ColorVisual(Color(215,171,147)))
    val platz4 = Label(posX = 500, posY= 650, width = 920, height = 100, text = "", font= Font(size=45),
        visual = ColorVisual(Color(215,171,147)))

    val exit = Button(posX = 570, posY= 900, width = 300, height = 100,text = "Exit",
        font= Font(size=30), visual = ColorVisual.RED).apply {
            onMousePressed = {
                exitProcess(0)
            }
    }

    val newGame = Button(posX = 1050, posY= 900, width = 300, height = 100,text = "New Game",
        font= Font(size=30), visual = ColorVisual.GREEN).apply {
            onMousePressed = {
                app.startScene.eingabe1.text = ""
                app.startScene.eingabe2.text = ""
                app.startScene.eingabe3.text = ""
                app.startScene.eingabe4.text = ""
                app.gameScene.knock.isDisabled= false
                app.showGameScene(app.startScene)
            }
    }

    /**
     * Method to create a new game
     */
    override fun refreshAfterEndGame() {
        // clear previous rounds
        platz1.text = ""
        platz2.text = ""
        platz3.text = ""
        platz4.text = ""

        val game = rs.swim
        checkNotNull(game)
        game.players.sortByDescending { player -> player.score }

        platz1.text = "1.Place:   ${game.players[0].playerName}    ${game.players[0].score} pts"
        platz2.text = "2.Place:   ${game.players[1].playerName}    ${game.players[1].score} pts"
        if(game.players.size>2) {
            platz3.text = "3.Place:   ${game.players[2].playerName}    ${game.players[2].score} pts"
        }
        if(game.players.size >3) {
            platz4.text = "4.Place:   ${game.players[3].playerName}    ${game.players[3].score} pts"
        }

    }

    /**
     * initialises the class
     */
    init {

        addComponents(ueberschrift, platz1, platz2, platz3, platz4, exit, newGame)
    }

}