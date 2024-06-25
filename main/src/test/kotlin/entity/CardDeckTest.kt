package entity

import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test
import kotlin.test.BeforeTest
import kotlin.test.assertEquals


/**
 * Test cases for the [CardDeck]
 */

class CardDeckTest {
    /**
     * The [CardDeck] that is tested with this test class
     */


    var card1 = Card(CardSuit.HEARTS, CardValue.EIGHT)
    var card2 = Card(CardSuit.DIAMONDS, CardValue.SEVEN)
    private var cardDeckSize1: CardDeck? = null
    private var cardDeckSize2: CardDeck? = null

    /**
     * Initialisierung der Variablen
     */
    @BeforeTest
    fun setUp(){

        // Create CardList
        cardDeckSize1 = CardDeck(mutableListOf<Card>(card1))
        cardDeckSize2 = CardDeck(mutableListOf<Card>(card1, card2))
    }
    /**
     * Überprüfung, ob die draw-Methode funktioniert für leere Liste
     */
    @Test
    fun drawCardFromEmptyDeckTest(){
        val resultExpected = mutableListOf<Card>()
        val cardDeckEmpty: CardDeck = CardDeck(mutableListOf<Card>())
        var result = cardDeckEmpty.draw(3)
        assertEquals(resultExpected,result)

    }
    /**
     * Überprüfung, ob die draw-Methode funktioniert für kleine Liste
     */
    @Test
    fun drawMoreCardsThanAvailable(){
        val resultExpected = mutableListOf<Card>()
        val result= cardDeckSize1?.draw(3)
        assertEquals(resultExpected,result)
    }
    /**
     * Überprüfung, ob die draw-Methode funktioniert
     */
    @Test
    fun drawCards(){
        val resultExpected = mutableListOf<Card>(card1)
        val result= cardDeckSize1?.draw(1)
        assertEquals(resultExpected,result)
    }



}