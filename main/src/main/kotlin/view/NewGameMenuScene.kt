package view

import service.RootService
import tools.aqua.bgw.components.uicomponents.Button
import tools.aqua.bgw.components.uicomponents.Label
import tools.aqua.bgw.components.uicomponents.TextField
import tools.aqua.bgw.core.BoardGameScene
import tools.aqua.bgw.util.Font
import tools.aqua.bgw.visual.ColorVisual
import java.awt.Color

/**
 * The class creates the view for the start of the game
 * @param ueberschrift
 * @anweisung Indication that the player must enter his name
 * @param ueberschrift1 Heading of the input field
 * @param eingabe1 Input field
 * @param ueberschrift2 Heading of the input field
 * @param eingabe2 Input field
 * @param ueberschrift3 Heading of the input field
 * @param eingabe3 Input field
 * @param ueberschrift4 Heading of the input field
 * @param eingabe4 Input field
 * @param knopf startbutton
 */

class NewGameMenuScene(rootService: RootService): BoardGameScene(1920, 1080, background = ColorVisual(Color(200,162,200))), Refreshable {

    val ueberschrift = Label(posX = 0, posY= 0, width = 1920, height = 100, text = "Swim", font= Font(size=60))

    val anweisung = Label(posX = 0, posY= 100, width = 1920, height = 100,
        text = "Enter your names to take part, a minimum of 2 players is required to start", font= Font(size=40))

    val ueberschrift1 = Label(posX = 0, posY= 200, width = 1920, height = 100, text = "Player1", font= Font(size=30))

    val eingabe1 = TextField(posX = 500, posY= 300, width = 920, height = 100, prompt = "Player1", font= Font(size=30))

    val ueberschrift2 = Label(posX = 0, posY= 400, width = 1920, height = 100, text = "Player2", font= Font(size=30))

    val eingabe2 = TextField(posX = 500, posY= 500, width = 920, height = 100, prompt = "Player2", font= Font(size=30))

    val ueberschrift3 = Label(posX = 0, posY= 600, width = 1920, height = 100, text = "Player3", font= Font(size=30))

    val eingabe3 = TextField(posX = 500, posY= 700, width = 920, height = 100, prompt = "Player3", font= Font(size=30))

    val ueberschrift4 = Label(posX = 0, posY= 800, width = 1920, height = 100, text = "Player4", font= Font(size=30))

    val eingabe4 = TextField(posX = 500, posY= 900, width = 920, height = 100, prompt = "Player4", font= Font(size=30))

    val knopf = Button(posX = 1500, posY= 900, width = 300, height = 100,text = "Start", font= Font(size=30), visual = ColorVisual.GREEN).apply {
        onMousePressed= {
            val namen = mutableListOf<String>()
            if(eingabe1.text != ""){
                namen.add(eingabe1.text)
            }
            if(eingabe2.text != ""){
                namen.add(eingabe2.text)
            }
            if(eingabe3.text != ""){
                namen.add(eingabe3.text)
            }
            if(eingabe4.text != ""){
                namen.add(eingabe4.text)
            }
            if(namen.size >=2 && namen.distinct().size == namen.size){
                rootService.gameService.startGame(namen)
            }

        }
    }

    /**
     * initialises the class
     */

    init {
        addComponents(ueberschrift, anweisung, ueberschrift1, eingabe1, ueberschrift2, eingabe2,
            ueberschrift3, eingabe3,ueberschrift4, eingabe4, knopf)

    }



}