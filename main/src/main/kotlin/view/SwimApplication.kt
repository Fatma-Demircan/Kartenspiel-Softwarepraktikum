package view

import service.RootService
import tools.aqua.bgw.core.BoardGameApplication

/**
 * class for SwimApplication
 */
class SwimApplication: BoardGameApplication("Swim"), Refreshable {

    val rootService = RootService()
    val gameScene = SwimGameScene(rootService)
    val startScene = NewGameMenuScene(rootService)
    val lastScene = EndGameScene(rootService, this)
    val inBetweenScene = InBetweenScene(rootService, this)

    /**
     * initialises the class
     */
    init{
        rootService.addRefreshables(this,gameScene, startScene, lastScene)
        this.showGameScene(startScene)

    }

    /**
     * refresh function for StartNewGame
     */
    override fun refreshAfterStartNewGame() {
        showGameScene(gameScene)
    }

    /**
     * refresh function for EndGame
     */
    override fun refreshAfterEndGame() {
        showGameScene(lastScene)
    }

    /**
     * refresh function for NextPlayer
     */
    override fun refreshAfterNextPlayer() {
        if(!rootService.over) {

            val game = rootService.swim
            checkNotNull(game)
            inBetweenScene.ueberschrift.text = "The next player is ${game.currentPlayer.playerName} "
            showMenuScene(inBetweenScene)
        }
    }

}