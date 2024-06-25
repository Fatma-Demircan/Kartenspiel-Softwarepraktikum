package service

import entity.Card
import entity.Player
import view.Refreshable

/**
 * Abstract service class that handles player actions
 *
 */
class PlayerService(private val rootService: RootService) : AbstractRefreshingService() {

    /**
     * knock and go to the next player
     * @throws IllegalStateException if swim is null
     */
    fun knock(){
        val swim = rootService.swim
        checkNotNull(swim)

        swim.passCounter=0;
        if(swim.knockCounter==-1){
            swim.knockCounter++;
        }
        rootService.gameService.nextPlayer()

    }


    /**
     * pass and go to the next player
     * @throws IllegalStateException if swim is null
     */
    fun pass(){

        val swim = rootService.swim
        checkNotNull(swim)
        swim.passCounter++
        rootService.gameService.nextPlayer()
    }

    /**
     * exchange a card and go to the next player
     * @param handCardIndex Index der Handkarten
     * @param tableCardIndex Index der Tischkarten
     * @throws IllegalStateException if swim is null
     * @throws IllegalArgumentException if an index is wrong
     */
    fun swapOne(handCardIndex: Int, tableCardIndex: Int){
        val swim = rootService.swim
        checkNotNull(swim)
        require(handCardIndex in 0..2)
        require(tableCardIndex in 0..2)

        swim.passCounter=0

        val card= swim.tableCards[tableCardIndex]
        swim.tableCards[tableCardIndex]= swim.currentPlayer.handCards[handCardIndex]
        swim.currentPlayer.handCards[handCardIndex]= card

        rootService.gameService.calculateScore(swim.currentPlayer)
        onAllRefreshables { refreshAfterSwapAll() }

        rootService.gameService.nextPlayer()


    }


    /**
     * exchange all cards and go to the next player
     * @throws IllegalStateException if swim is null
     */
    fun swapAll(){
        val swim = rootService.swim
        checkNotNull(swim)

        swim.passCounter=0
        for(i in 0..2) {
            val card = swim.tableCards[i]
            swim.tableCards[i] = swim.currentPlayer.handCards[i]
            swim.currentPlayer.handCards[i] = card

        }
        rootService.gameService.calculateScore(swim.currentPlayer)
        onAllRefreshables { refreshAfterSwapAll() }
        rootService.gameService.nextPlayer()


    }
}