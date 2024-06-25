package entity
import kotlin.test.*


class CardTest {

    var card: Card? = null

    /**
     * card wird initialisiert
     */
    @BeforeTest
    fun setUp(){
        card = Card(CardSuit.DIAMONDS, CardValue.EIGHT)
    }

    /**
     * überprüfen, ob die get-Methode für CardSuit funktioniert
     */

   @Test
   fun getCardSuitTest(){
       val resultExpected = CardSuit.DIAMONDS
       val result= card?.cardSuit
       assertEquals(resultExpected,result)
   }

    /**
     * überprüfen, ob die get-Methode für CardValue funktioniert
     */
    @Test
    fun getCardValueTest(){
        val resultExpected = CardValue.EIGHT
        val result= card?.cardValue
        assertEquals(resultExpected,result)
    }

    /**
     * überprüfen, ob die set-Methode für CardValue funktioniert
     */

    @Test
    fun setCardValueTest(){
        val resultExpected = CardValue.NINE
        card?.cardValue = CardValue.NINE
        val result= card?.cardValue
        assertEquals(resultExpected,result)
    }

    /**
     * überprüfen, ob die set-Methode für CardSuit funktioniert
     */

    @Test
    fun setCardSuitTest(){
        val resultExpected = CardSuit.HEARTS
        card?.cardSuit = CardSuit.HEARTS
        val result= card?.cardSuit
        assertEquals(resultExpected,result)
    }

}