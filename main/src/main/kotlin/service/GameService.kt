package service

import entity.*
import view.Refreshable

/**
 * Abstract service class that handles actions
 */
class GameService(private val rootService: RootService): AbstractRefreshingService() {
    /**
     * gets a list of names and creates a new game for it
     * @param names Liste der Namen
     * @throws IllegalStateException if swim is null
     */
    fun startGame(names: List<String>) {
        val cards: MutableList<Card> = emptyList<Card>().toMutableList()
        for (suits in CardSuit.values()){
            for(values in CardValue.values()){
                cards.add(Card(suits,values))
            }
        }
       cards.shuffle()

        // gültige Anzahl von Namen
        require(names.size in 2..4)

        // Eindeutigkeit prüfen
       require( names.distinct().count()== names.size)


        // Create cardDeck
        val cardDeck = CardDeck(cards)

        // Create tablecards
        val tableCards = cardDeck.draw(3).toMutableList()

        //List of players created
        val players: MutableList<Player> = listOf<Player>().toMutableList()

        // Create the individual players, i.e. players
        for (name in names) {
            //Create handCards
            val handCards = cardDeck.draw(3)
            val player = Player(handCards.toMutableList(), name, 0.0)
            players.add(player)
        }
        players.shuffle()

        rootService.swim = Swim(players[0] , players, tableCards, cardDeck)
        rootService.over=false

        onAllRefreshables { refreshAfterStartNewGame() }
    }

    /**
     * ends the game and calculates the points for each player
     * @throws IllegalStateException if swim is null
     */
    fun endGame() {
            val swim = rootService.swim
            checkNotNull(swim)

            rootService.over= true
            for(player in swim.players){
                calculateScore(player)
            }
        onAllRefreshables { refreshAfterEndGame() }
        }

    /**
     * goes through the three cards in hand and adds up the scores,
     * if suit is the same for all three cards, the score=30.5
     */
    fun calculateScore(player: Player) {
        var sum=0.0
        for(suit in CardSuit.values()){
           val temp = player.handCards.foldRight(0){

                   card, result -> if(card.cardSuit== suit)result + card.cardValue.toInt()else result}
            if(temp> sum){
                sum= temp.toDouble()
            }
        }
        if(player.handCards[0].cardValue==player.handCards[1].cardValue && player.handCards[1].cardValue==player.handCards[2].cardValue){
            sum=30.5
        }
        player.score= sum;
    }

    /**
     *selects the next player and checks the knock counter
     * @throws IllegalStateException if swim is null
     */
    fun nextPlayer() {
        val swim = rootService.swim
        checkNotNull(swim)

        if(swim.passCounter==swim.players.size){
           if(swim.cardDeck.cards.size<3){

               endGame()

           } else {
                for(i in 0..2){
                    swim.tableCards.set(i,swim.cardDeck.draw(1)[0])
                    onAllRefreshables { refreshAfterSwapTableCards() }
                 }
               swim.passCounter=0
           }
        }
        if(swim.knockCounter>=0){
          swim.knockCounter++;
         }
        if(swim.knockCounter==swim.players.size){
            endGame()
        }
        val index = swim.players.indexOf(swim.currentPlayer)
        if(index >= swim.players.size-1) {
            swim.currentPlayer =swim.players[0]
        }else
            swim.currentPlayer = swim.players[index + 1]

        onAllRefreshables { refreshAfterNextPlayer() }
         }

}




