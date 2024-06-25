package view

import tools.aqua.bgw.core.BoardGameApplication

/**
 * class for SopraApplication
 */
class SopraApplication : BoardGameApplication("SoPra Game") {

   private val helloScene = HelloScene()
    /**
     * initialises the class
     */
   init {
        this.showGameScene(helloScene)
    }

}

