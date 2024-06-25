package service

import entity.Card
import entity.CardDeck
import entity.CardSuit
import entity.CardValue
import kotlin.test.*
import kotlin.test.assertFalse
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

/**
 * Test cases for the [PlayerService]
 */
class PlayerServiceTest {
    /**
     * checks whether the knocking works
     */
   @Test
    fun knockTest(){
        val rs = RootService()
        assertFailsWith<IllegalStateException> { rs.playerService.knock() }
        rs.gameService.startGame(listOf("Lina", "Michel"))
        rs.playerService.knock()
        assertFalse(rs.over)
        rs.playerService.pass()
        assertTrue(rs.over)
    }


    /**
     * checks whether it exchanges cards when three passes in a row have been made
     */
    @Test
    fun passTest(){
        val rs = RootService()
        assertFailsWith<IllegalStateException> { rs.playerService.pass() }
        rs.gameService.startGame(listOf("Lina", "Michel"))
        val middleCards= rs.swim?.tableCards?.toMutableList()
        rs.playerService.pass()
        rs.playerService.pass()
        assertNotEquals(middleCards,rs.swim?.tableCards)
    }

    /**
     * checks that cards are not exchanged if not passed twice in a row
     */
    @Test
    fun passTest2(){
        val rs = RootService()
        assertFailsWith<IllegalStateException> { rs.playerService.pass() }
        rs.gameService.startGame(listOf("Lina", "Michel"))
        val middleCards= rs.swim?.tableCards?.toMutableList()
        rs.playerService.knock()
        rs.playerService.pass()
        assertEquals(middleCards,rs.swim?.tableCards)
    }

    /**
     * checks if the game is over when there are not enough cards left
     */
    @Test
    fun passTest3(){
        val rs = RootService()
        assertFailsWith<IllegalStateException> { rs.playerService.pass() }
        rs.gameService.startGame(listOf("Lina", "Michel"))
        rs.swim?.cardDeck= CardDeck(mutableListOf(Card(CardSuit.HEARTS, CardValue.NINE)))
        rs.playerService.pass()
        rs.playerService.pass()
       assertTrue(rs.over)
    }


    /**
     * checks whether a card is exchanged
     */
    @Test
    fun swapOneTest(){
        val rs = RootService()
       assertFailsWith<IllegalStateException>{rs.playerService.swapOne(1,2)}
        rs.gameService.startGame(listOf("Lina", "Michel"))
        assertFailsWith<IllegalArgumentException> {rs.playerService.swapOne(-1, 2) }
        assertFailsWith<IllegalArgumentException> {rs.playerService.swapOne(20, 2) }
        assertFailsWith<IllegalArgumentException> {rs.playerService.swapOne(1, -22) }
        assertFailsWith<IllegalArgumentException> {rs.playerService.swapOne(1, 32447) }
        for(i in 0..2){
            for(j in 0..2){
                val middleCards= rs.swim?.tableCards?.toMutableList()
                val handCards= rs.swim?.currentPlayer?.handCards?.toMutableList()
                val player= rs.swim?.currentPlayer
                rs.playerService.swapOne(i,j)
                if (player != null) {
                    assertEquals(player.handCards[i], middleCards?.get(j))
                    assertEquals(rs.swim?.tableCards?.get(j) , handCards?.get(i) )
                }
            }
        }
    }

    /**
     * checks whether all cards are exchanged
     */
    @Test
    fun swapAllTest(){
        val rs = RootService()
        assertFailsWith<IllegalStateException> { rs.playerService.swapAll() }
        rs.gameService.startGame(listOf("Lina", "Michel"))
        val middleCards = rs.swim?.tableCards?.toMutableList()
        val handCards = rs.swim?.currentPlayer?.handCards?.toMutableList()
        rs.playerService.swapAll()
        assertEquals(rs.swim?.tableCards, handCards)
        assertEquals(rs.swim?.players?.get(0)?.handCards,middleCards )
    }



}