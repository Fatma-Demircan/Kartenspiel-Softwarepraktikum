package view

import entity.Card
import entity.Player

    /**
    * Class for the refreshables
    */
interface Refreshable {
        /**
         *  refresh function for nextPlayer
         */
    fun refreshAfterNextPlayer(){}

        /**
         *  refresh function for SwapOne
         */
    fun refreshAfterSwapOne(handCard: Card, tableCard: Card){}

        /**
         *  refresh function for AfterSwapAll
         */
    fun refreshAfterSwapAll(){}

        /**
         *  refresh function for StartNewGame
         */
    fun refreshAfterStartNewGame(){}

        /**
         *  refresh function for Knock
         */
    fun refreshAfterKnock(){}

        /**
         *  refresh function for Pass
         */
    fun refreshAfterPass(){}

        /**
         *  refresh function for EndGame
         */
    fun refreshAfterEndGame(){}

        /**
         *  refresh function for SwapTableCards
         */
    fun refreshAfterSwapTableCards(){}

}