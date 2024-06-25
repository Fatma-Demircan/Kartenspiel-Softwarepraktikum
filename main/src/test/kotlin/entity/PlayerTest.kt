package entity

import kotlin.test.*

/**
 * Spieler wird getestet
 */

class PlayerTest {

    var player: Player? = null
    var card1 = Card(CardSuit.HEARTS, CardValue.EIGHT)
    var card2 = Card(CardSuit.DIAMONDS, CardValue.SEVEN)
    var card3 = Card(CardSuit.HEARTS, CardValue.EIGHT)

    /**
     * Für den Test benötigte Variablen werden initialisiert
     */

    @BeforeTest
    fun setUp(){
        var handcards = mutableListOf<Card>(card1, card2,card3)
        player = Player(handcards, "Franzi", 0.0 )
    }
    /**
     * Überprüfung, ob der Spieler 3 Karten hat
     * Überprüfen, ob die get-Methode die richtigen Karten zurückgibt
     */
    @Test
    fun getHandCardsGetTest(){
        assertEquals(3,player?.handCards?.size)
        val resultExpected1 = mutableListOf<Card>(card1, card2, card3)
        val result= player?.handCards
        assertEquals(resultExpected1,result)
    }

    /**
     * Überprüfung, ob die get-Methode den richtigen Namen zurückgibt
     */
    @Test
    fun playerNameGetTest(){
        val resultExpected = "Franzi"
        val result= player?.playerName
        assertEquals(resultExpected,result)
    }
    /**
     * Überprüfen, ob die get-Methode den richtigen score-Wert zurückgibt
     */
    @Test
    fun scoreGetTest(){
        val resultExpected = 0.0
        val result= player?.score
        assertEquals(resultExpected,result)
    }
    /**
     * Überprüfen, ob die set-Methode die richtigen Werte für die HandCards setzt
     */
    @Test
    fun setHandCardsTest(){
        val resultExpected = mutableListOf<Card>(card1)
        player?.handCards = mutableListOf<Card>(card1)
        val result= player?.handCards
        assertEquals(resultExpected,result)
    }

    /**
     * Überprüfen, ob die set-Methode den richtigen Namen setzt
     */

    @Test
    fun setPlayerNameTest(){
        val resultExpected = "Tom"
        player?.playerName = "Tom"
        val result= player?.playerName
        assertEquals(resultExpected,result)
    }
    /**
     * Überprüfen, ob die set-Methode den richtigen Wert für Score setzt
     */
    @Test
    fun setScoreTest(){
        val resultExpected = 30.5
        player?.score = 30.5
        val result= player?.score
        assertEquals(resultExpected,result)
    }


}