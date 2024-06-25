package view

import service.RootService
import tools.aqua.bgw.components.uicomponents.Button
import tools.aqua.bgw.components.uicomponents.Label
import tools.aqua.bgw.core.MenuScene
import tools.aqua.bgw.util.Font
import tools.aqua.bgw.visual.ColorVisual
import java.awt.Color

/**
 * Intermediate screen so that the cards of the previous player are not seen when changing players
 * @param ueberschrift indicates who is the next player
 * @param knopf closes the intermediate view
 */


class InBetweenScene(rootService: RootService, swimApplication: SwimApplication): MenuScene(1920, 1080, background = ColorVisual(Color(200,162,200))), Refreshable  {

    val rs = rootService
    val app = swimApplication

    val ueberschrift = Label(posX = 0, posY= 200, width = 1920, height = 100, text = "irgendwhat",
        font= Font(size=60))

    val knopf = Button(posX = 0, posY= 800, width = 1920, height = 100, text = "close",
        font= Font(size=60)).apply {
            onMousePressed = {
                app.hideMenuScene()
            }
    }

    /**
     * initialises the class
     */
    init {
        addComponents(ueberschrift, knopf)
    }




}