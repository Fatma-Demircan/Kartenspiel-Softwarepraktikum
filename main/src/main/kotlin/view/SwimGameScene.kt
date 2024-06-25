package view

import entity.Card
import service.CardImageLoader
import service.RootService
import tools.aqua.bgw.components.uicomponents.Button
import tools.aqua.bgw.components.uicomponents.Label
import tools.aqua.bgw.core.BoardGameScene
import tools.aqua.bgw.util.Font
import tools.aqua.bgw.visual.ColorVisual
import tools.aqua.bgw.visual.ImageVisual
import java.awt.Color

/**
 * class for game scene
 * @param ueberschrift specifies the next player
 * @param handkarte1 handcard
 * @param handkarte2 handcard
 * @param handkarte3 handcard
 * @param mittelkarte1 tablecard
 * @param mittelkarte2 tablecard
 * @param mittelkarte3 tablecard
 * @param stapel cards that are on the stack
 * @param swapAll button for swapAll
 * @param swapOne button for swapOne
 * @param knock button for knock
 * @param pass button for pass
 */
class SwimGameScene (rootService: RootService): BoardGameScene(1920, 1080, background = ColorVisual(Color(200,162,200))), Refreshable {
    val cardImageLoader= CardImageLoader()
    val factor = 1.4
    val rs = rootService
    var swap1 = false
    var handIndex = -1
    var mittelindex = -1


    val ueberschrift = Label(posX = 0, posY= 20, width = 1920, height = 100, text = "It's Player 1's turn",
        font= Font(size=60))

    val handkarte1 = Label(posX = 745, posY= 750, width = 130 *factor, height = 200 *factor,
        visual = ImageVisual(cardImageLoader.backImage))


    val handkarte2 = Label(posX = 950, posY= 750, width = 130*factor, height = 200*factor,
        visual = ImageVisual(cardImageLoader.backImage))

    val handkarte3 = Label(posX = 1150, posY= 750, width = 130*factor, height = 200*factor,
        visual = ImageVisual(cardImageLoader.backImage))


    val mittelkarte1 = Label(posX = 745, posY= 200, width = 130*factor, height = 200*factor,
        visual = ImageVisual(cardImageLoader.backImage))

    val mittelkarte2 = Label(posX = 950, posY= 200, width = 130*factor, height = 200*factor,
        visual = ImageVisual(cardImageLoader.backImage))

    val mittelkarte3 = Label(posX = 1150, posY= 200, width = 130*factor, height = 200*factor,
        visual = ImageVisual(cardImageLoader.backImage))

    val stapel = Label(posX = 500, posY= 200, width = 130*factor, height = 200*factor, text= "0",
        font = Font(size=90),
        visual = ImageVisual(cardImageLoader.blankImage))

    val swapAll = Button(posX = 120, posY= 700, width = 300, height = 100,text = "Swap all",
        font= Font(size=30), visual = ColorVisual.GRAY).apply {
            onMousePressed = {
                rs.playerService.swapAll()
            }
    }


    val swapOne = Button(posX = 120, posY= 900, width = 300, height = 100,text = "Swap one",
        font= Font(size=30), visual = ColorVisual.GRAY).apply {

        onMousePressed = {

            if(swap1){
                swap1 = false
               this.scale = 1.0
            }else{
                swap1 =true
                this.scale = 1.05
            }

            handkarte1.scale = 1.0
            handkarte2.scale = 1.0
            handkarte3.scale = 1.0

            mittelkarte1.scale = 1.0
            mittelkarte2.scale = 1.0
            mittelkarte3.scale = 1.0

        }
    }

    val pass = Button(posX = 1500, posY= 700, width = 300, height = 100,text = "Pass",
        font= Font(size=30), visual = ColorVisual.GRAY).apply {
        onMousePressed = {
            rs.playerService.pass()
        }
    }

    val knock = Button(posX = 1500, posY= 900, width = 300, height = 100,text = "Knock",
        font= Font(size=30), visual = ColorVisual.GRAY).apply {
        onMousePressed = {
            this.isDisabled=true
            rs.playerService.knock()

        }
    }

    /**
     * refresh function for AfterStartNewGame
     */
    override fun refreshAfterStartNewGame() {
        val game = rs.swim
        checkNotNull(game)
        ueberschrift.text = "It's ${game.currentPlayer.playerName}'s turn"
        stapel.text = "${game.cardDeck.cards.size}"
        var card = game.currentPlayer.handCards[0]
        handkarte1.visual = ImageVisual(cardImageLoader.frontImageFor(card.cardSuit, card.cardValue))

        card = game.currentPlayer.handCards[1]
        handkarte2.visual = ImageVisual(cardImageLoader.frontImageFor(card.cardSuit, card.cardValue))

        card = game.currentPlayer.handCards[2]
        handkarte3.visual = ImageVisual(cardImageLoader.frontImageFor(card.cardSuit, card.cardValue))


        card = game.tableCards[0]
        mittelkarte1.visual = ImageVisual(cardImageLoader.frontImageFor(card.cardSuit, card.cardValue))

        card = game.tableCards[1]
        mittelkarte2.visual = ImageVisual(cardImageLoader.frontImageFor(card.cardSuit, card.cardValue))

        card = game.tableCards[2]
        mittelkarte3.visual = ImageVisual(cardImageLoader.frontImageFor(card.cardSuit, card.cardValue))
    }

    /**
     * refresh function for AfterKnock
     */
    override fun refreshAfterKnock() {
        super.refreshAfterKnock()
    }

    /**
     * refresh fuction for AfterNextPlayer
     */
    override fun refreshAfterNextPlayer() {
        val game = rs.swim
        checkNotNull(game)
        ueberschrift.text = "It's ${game.currentPlayer.playerName}'s turn"

        stapel.text = "${game.cardDeck.cards.size}"

        var card = game.currentPlayer.handCards[0]
        handkarte1.visual = ImageVisual(cardImageLoader.frontImageFor(card.cardSuit, card.cardValue))

        card = game.currentPlayer.handCards[1]
        handkarte2.visual = ImageVisual(cardImageLoader.frontImageFor(card.cardSuit, card.cardValue))

        card = game.currentPlayer.handCards[2]
        handkarte3.visual = ImageVisual(cardImageLoader.frontImageFor(card.cardSuit, card.cardValue))

        handkarte1.scale = 1.0
        handkarte2.scale = 1.0
        handkarte3.scale = 1.0

        mittelkarte1.scale = 1.0
        mittelkarte2.scale = 1.0
        mittelkarte3.scale = 1.0

        swapOne.scale = 1.0

    }

    /**
     * refresh function for AfterSwapAll
     */
    override fun refreshAfterSwapAll() {
        val game = rs.swim
        checkNotNull(game)
        var card = game.currentPlayer.handCards[0]
        handkarte1.visual = ImageVisual(cardImageLoader.frontImageFor(card.cardSuit, card.cardValue))

        card = game.currentPlayer.handCards[1]
        handkarte2.visual = ImageVisual(cardImageLoader.frontImageFor(card.cardSuit, card.cardValue))

        card = game.currentPlayer.handCards[2]
        handkarte3.visual = ImageVisual(cardImageLoader.frontImageFor(card.cardSuit, card.cardValue))


        card = game.tableCards[0]
        mittelkarte1.visual = ImageVisual(cardImageLoader.frontImageFor(card.cardSuit, card.cardValue))

        card = game.tableCards[1]
        mittelkarte2.visual = ImageVisual(cardImageLoader.frontImageFor(card.cardSuit, card.cardValue))

        card = game.tableCards[2]
        mittelkarte3.visual = ImageVisual(cardImageLoader.frontImageFor(card.cardSuit, card.cardValue))

    }

    /**
     * refresh function for AfterSwapTableCards
     */
    override fun refreshAfterSwapTableCards() {

        val game = rs.swim
        checkNotNull(game)
        var card = game.tableCards[0]
        mittelkarte1.visual = ImageVisual(cardImageLoader.frontImageFor(card.cardSuit, card.cardValue))

        card = game.tableCards[1]
        mittelkarte2.visual = ImageVisual(cardImageLoader.frontImageFor(card.cardSuit, card.cardValue))

        card = game.tableCards[2]
        mittelkarte3.visual = ImageVisual(cardImageLoader.frontImageFor(card.cardSuit, card.cardValue))
    }

    /**
     * initialises the class
     */
    init {
        handkarte1.apply{
            onMousePressed = {
                if(handIndex>=0){
                    handkarte1.scale = 1.0
                    handkarte2.scale = 1.0
                    handkarte3.scale = 1.0
                }
                if(swap1) {
                    handkarte1.scale = 1.05
                    handIndex = 0
                    if(handIndex >=0 && mittelindex >= 0){
                        rs.playerService.swapOne(handIndex,mittelindex)
                        handIndex = -1
                        mittelindex= -1
                        swap1= false
                    }
                }else
                {
                    handkarte1.scale = 1.0
                    handIndex = -1
                }
            }
        }

        handkarte2.apply{
            onMousePressed = {
                if(handIndex>=0){
                    handkarte1.scale = 1.0
                    handkarte2.scale = 1.0
                    handkarte3.scale = 1.0
                }
                if(swap1) {
                    handkarte2.scale = 1.05
                    handIndex = 1
                    if(handIndex >=0 && mittelindex >= 0){
                        rs.playerService.swapOne(handIndex,mittelindex)
                        handIndex = -1
                        mittelindex= -1
                        swap1= false
                    }
                }else
                {
                    handkarte2.scale = 1.0
                    handIndex = -1
                }
            }
        }

        handkarte3.apply{

            onMousePressed = {
                if(handIndex>=0){
                    handkarte1.scale = 1.0
                    handkarte2.scale = 1.0
                    handkarte3.scale = 1.0
                }
                if(swap1) {
                    handkarte3.scale = 1.05
                    handIndex = 2
                    if(handIndex >=0 && mittelindex >= 0){
                        rs.playerService.swapOne(handIndex,mittelindex)
                        handIndex = -1
                        mittelindex= -1
                        swap1= false
                    }
                }else
                {
                    handkarte3.scale = 1.0
                    handIndex = -1
                }
            }
        }

        mittelkarte1.apply{
            onMousePressed = {
                if(mittelindex>=0){
                    mittelkarte1.scale = 1.0
                    mittelkarte2.scale = 1.0
                    mittelkarte3.scale = 1.0
                }
                if(swap1) {
                    mittelkarte1.scale = 1.05
                   mittelindex = 0
                    if(handIndex >=0 && mittelindex >= 0){
                        rs.playerService.swapOne(handIndex,mittelindex)
                        handIndex = -1
                        mittelindex= -1
                        swap1= false
                    }
                }else
                {
                    mittelkarte1.scale = 1.0
                    mittelindex = -1
                }
            }
        }

        mittelkarte2.apply{
            onMousePressed = {
                if(mittelindex>=0){
                    mittelkarte1.scale = 1.0
                    mittelkarte2.scale = 1.0
                    mittelkarte3.scale = 1.0
                }
                if(swap1) {
                    mittelkarte2.scale = 1.05
                    mittelindex = 1
                    if(handIndex >=0 && mittelindex >= 0){
                        rs.playerService.swapOne(handIndex,mittelindex)
                        handIndex = -1
                        mittelindex= -1
                        swap1= false
                    }

                }else
                {
                    mittelkarte2.scale = 1.0
                    mittelindex = -1
                }
            }
        }

        mittelkarte3.apply{
            onMousePressed = {
                if(mittelindex>=0){
                    mittelkarte1.scale = 1.0
                    mittelkarte2.scale = 1.0
                    mittelkarte3.scale = 1.0
                }
                if(swap1) {
                    mittelkarte3.scale = 1.05
                    mittelindex = 2
                    if(handIndex >=0 && mittelindex >= 0){
                        rs.playerService.swapOne(handIndex,mittelindex)
                        handIndex = -1
                        mittelindex= -1
                        swap1= false
                    }
                }else
                {
                    mittelkarte3.scale = 1.0
                    mittelindex = -1
                }
            }
        }

        addComponents(ueberschrift, handkarte1, handkarte2, handkarte3, mittelkarte1,
            mittelkarte2, mittelkarte3, stapel, swapAll, swapOne, pass, knock)
    }
}