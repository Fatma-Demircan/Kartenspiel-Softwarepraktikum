package entity

import kotlin.test.*


class CardSuitTest {
    /**
     * überprüfen, ob die get-Methode für CardSuit funktioniert
     */
    @Test
    fun cardsSuitTest(){
        val resultExpected = "♥"
        val result= CardSuit.HEARTS.toString()
        assertEquals(resultExpected,result)
    }

}