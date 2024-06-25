package service

import entity.Card
import entity.CardSuit
import entity.CardValue
import entity.Player
import kotlin.test.*

/**
 * Test cases for the [GameService]
 */
class GameServiceTest {

    var rs: RootService = RootService()

    /**
     * Checks whether the game is finished by looking whether the score of a player has been calculated.
     */
    @Test
    fun endGame(){
        assertFailsWith<IllegalStateException> { rs.gameService.endGame() }
        rs.gameService.startGame(listOf("Kim","Ron", "Rufus" ))
        rs.gameService.endGame()
        for(i in 0..2) {
            rs.swim?.players?.get(i)?.let { assertNotEquals(it.score, 0.0) }
        }
    }

    /**
     * checks that the player's score is calculated correctly
     */
    @Test
    fun calculateScoreTest(){

        val player= Player(mutableListOf<Card>(Card(CardSuit.HEARTS, CardValue.NINE),
            Card(CardSuit.CLUBS, CardValue.EIGHT), Card(CardSuit.DIAMONDS, CardValue.JACK )), "Ben")
        rs.gameService.calculateScore(player)
        assertEquals(player.score, 10.0)

    }

    @Test
    fun calculateScoreTest2(){

        val player= Player(mutableListOf<Card>(Card(CardSuit.HEARTS, CardValue.NINE),
            Card(CardSuit.HEARTS, CardValue.SEVEN), Card(CardSuit.HEARTS, CardValue.ACE )), "Ben")
        rs.gameService.calculateScore(player)
        assertEquals(player.score, 27.0)

    }

    @Test
    fun calculateScoreTest3(){

        val player= Player(mutableListOf<Card>(Card(CardSuit.CLUBS, CardValue.ACE),
            Card(CardSuit.DIAMONDS, CardValue.ACE), Card(CardSuit.HEARTS, CardValue.ACE )), "Ben")
        rs.gameService.calculateScore(player)
        assertEquals(player.score, 30.5)

    }

    @Test
    fun startGameTest(){

        // prüft, ob mein require funktioniert
        assertFailsWith<IllegalArgumentException>{ rs.gameService.startGame(listOf("Kim")) }

        // prüft, ob mein 2. require funktioniert
        assertFailsWith<IllegalArgumentException>{ rs.gameService.startGame(listOf("Kim", "Kim", "Lena")) }

        //Sachen testen, die funktionieren
        rs.gameService.startGame(listOf("Kim","Ron", "Rufus" ))

        //cardDeck prüfen
        assertEquals(rs.swim?.cardDeck?.cards?.size,20 )

        //tableCards prüfen
        assertEquals(rs.swim?.tableCards?.size, 3)

        //handcarts prüfen
        for(player in rs.swim?.players!!){
            assertEquals(player.handCards.size, 3)
        }

    }

    @Test
    fun nextPlayerTest(){
        assertFailsWith<IllegalStateException> { rs.gameService.nextPlayer() }
        rs.gameService.startGame(listOf("Kim","Ron", "Rufus" ))

        assertEquals(rs.swim?.players?.size, 3)

        //passCounter prüfen
        assertEquals(rs.swim?.passCounter,0 )

        rs.playerService.pass()
        assertEquals(rs.swim?.passCounter,1 )

        rs.playerService.pass()
        assertEquals(rs.swim?.passCounter,2 )

        rs.playerService.pass()
        assertEquals(rs.swim?.passCounter,0 )

        rs.playerService.pass()
        assertEquals(rs.swim?.passCounter,1 )

        //knockCounter prüfen
        assertEquals(rs.swim?.knockCounter,-1 )

        rs.playerService.knock()
        assertEquals(rs.swim?.knockCounter,1 )

        rs.playerService.pass()
        assertEquals(rs.swim?.knockCounter,2 )

        rs.playerService.knock()
        assertEquals(rs.swim?.knockCounter,3 )

        assertTrue(rs.over)

    }





}