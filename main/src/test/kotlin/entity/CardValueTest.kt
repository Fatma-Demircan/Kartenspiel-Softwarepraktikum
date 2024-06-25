package entity

import kotlin.test.*


class CardValueTest {

    /**
     * überprüfen, ob die get_methode 9 zurückgibt
     */
        @Test
        fun cardsValueTest(){
            val resultExpected = "9"
            val result= CardValue.NINE.toString()
            assertEquals(resultExpected,result)
        }

}