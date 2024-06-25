package entity

import kotlin.test.*

class SwimTest {
    var swim: Swim?= null
    var player: Player? = null
    var card1 = Card(CardSuit.DIAMONDS, CardValue.EIGHT)
    var card2 = Card(CardSuit.DIAMONDS, CardValue.SEVEN)
    var card3 = Card(CardSuit.DIAMONDS, CardValue.NINE)
    var handcards = mutableListOf<Card>(card1, card2,card3)
    var cards = mutableListOf<Card>(card1, card2,card3)
    var currentPlayer = Player(handcards, "Franzi", 0.0 )
    var cardDeck= CardDeck(cards)
    var tableCards=  mutableListOf<Card>(card1, card2,card3)
    var players= mutableListOf<Player>(currentPlayer)
    /**
     *  Variablen für die Test initialisieren
     */
    @BeforeTest
    fun setUp(){

        swim= Swim(currentPlayer,players, tableCards,cardDeck )
    }

    /**
     *  Überprüfen, ob get-Methode für CurrentPlayer funktioniert
     */
    @Test
    fun getCurrentPlayerTest(){
        val resultExpected = players
        val result= swim?.players
        assertEquals(resultExpected,result)
    }
    /**
     *  Überprüfen, ob get-Methode für Player funktioniert
     */
    @Test
    fun getPlayersTest(){
        val resultExpected = currentPlayer
        val result= swim?.currentPlayer
        assertEquals(resultExpected,result)
    }

    /**
     *  Überprüfen, ob get-Methode für TableCards funktioniert
     */

    @Test
    fun getTableCardsTest(){
        val resultExpected = tableCards
        val result= swim?.tableCards
        assertEquals(resultExpected,result)
    }
    /**
     *  Überprüfen, ob get-Methode für CardDeck funktioniert
     */
    @Test
    fun getCardDeckTest(){
        val resultExpected = cardDeck
        val result= swim?.cardDeck
        assertEquals(resultExpected,result)
    }



}