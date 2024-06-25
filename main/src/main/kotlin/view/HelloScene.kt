package view

import tools.aqua.bgw.components.uicomponents.Label
import tools.aqua.bgw.core.BoardGameScene
import tools.aqua.bgw.util.Font
import tools.aqua.bgw.visual.ColorVisual

    /**
    * HelloScene
    */
class HelloScene : BoardGameScene(500, 500) {

    private val helloLabel = Label(
        width = 500,
        height = 500,
        posX = 0,
        posY = 0,
        text = "Hello, SoPra!",
        font = Font(size = 20)
    )
    /**
     * initialises the class
     */
    init {
        background = ColorVisual(108, 168, 59)
        addComponents(helloLabel)
    }

}